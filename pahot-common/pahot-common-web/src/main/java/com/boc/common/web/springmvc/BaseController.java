package com.boc.common.web.springmvc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boc.common.web.cookie.CookieTools;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boc.common.biz.IdWorkerUtil;
import com.boc.common.enums.PropertiesHelper;
import com.boc.common.exception.BizException;
import com.boc.common.exception.BizSystemException;
import com.boc.common.exception.SDKException;
import com.boc.common.metatype.BaseEntity;
import com.boc.common.metatype.DTO;
import com.boc.common.metatype.impl.BaseDTO;
import com.boc.common.metatype.impl.SessionDTO;
import com.boc.common.utils.DateUtils;
import com.boc.common.utils.rsa.RSAEncryptByPrivateKey;
import com.boc.common.utils.rsa.RSAEncryptByPublicKey;
import com.boc.common.web.Constants;
import com.boc.common.web.WebResponse;
import com.boc.common.web.enums.MenuEnums;
import com.boc.common.web.permissions.SecurityUtils;
import com.boc.common.web.permissions.exception.PermissionsException;

import cn.pahot.logger.entity.ExceptionLogEntity;
import cn.pahot.xa.entity.MessageEntity;
import cn.pahot.xa.facade.CapXaFacade;

/**
 * 所有Controller都必须继承至该类
 * <p>@Title: BaseController.java
 * <p>@Package com.boc.common.web.springmvc
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com
 * <p>@date 2017年11月22日 下午1:30:56
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class BaseController {

    protected static final Logger logger = Logger.getLogger(BaseController.class);

    @Resource
    private CapXaFacade capXaFacade;

    protected static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    protected HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return request;
    }

    protected HttpServletResponse getResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        return response;
    }

    /**
     * 基于@ExceptionHandler异常处理
     */
    @ExceptionHandler
    @ResponseBody
    public WebResponse BaseExceptionHandler(HttpServletRequest request, Exception ex) {
        WebResponse res = new WebResponse();
        StackTraceElement ste = Thread.currentThread().getStackTrace()[1];
        ExceptionLogEntity entity = new ExceptionLogEntity();
        if (ex instanceof BizException) {
            res.setAjaxMsg(false, "【" + ((BizException) ex).getCode() + "】" + ((BizException) ex).getMsg(), null, null);
        } else if (ex instanceof BizSystemException) {
            res.setAjaxMsg(false, "【" + ((BizSystemException) ex).getCode() + "】" + ((BizSystemException) ex).getMsg(), null, null);
        } else if (ex instanceof SDKException) {
            res.setAjaxMsg(false, "【" + ((SDKException) ex).getCode() + "】" + ((SDKException) ex).getMsg(), null, null);
        } else if (ex instanceof RpcException) {
            res.setAjaxMsg(false, "【88888888】远程服务调用异常，请重试!", null, ex.getMessage());
        } else {
            res.setAjaxMsg(false, "【99999999】系统内部异常，请重试!", null, ex.getMessage());
        }
        Enumeration<String> paramNames = request.getParameterNames();
        JSONObject param = new JSONObject();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String paramValues = request.getParameter(paramName);
            param.put(paramName, paramValues);
        }
        entity.setId(IdWorkerUtil.getId());
        entity.setSid(SecurityUtils.getWebSecurityManager().sid);
        entity.setClassname(ste.getClassName());
        entity.setMethodname(ste.getMethodName());
        entity.setErrortime(DateUtils.getCurrDateTimeToLong());
        entity.setMessage(res.get(Constants.INFO).toString());
        entity.setIp(getRemoteHost(request));
        entity.setUrl(request.getRequestURL().toString());
        entity.setRequest(param.toJSONString());
        if (PropertiesHelper.LOGGER_RECORD.equals("file")) {
            logger.error("[EXCEPTION_LOGGER] " + JSONObject.toJSONString(entity));
        } else {
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setQueuename(PropertiesHelper.LOGGER_QUEUENAME_EXCEPTION);
            messageEntity.setContent(JSONObject.toJSONString(entity));
            try {
                capXaFacade.directSendMessage(messageEntity);
            } catch (BizException e) {
                logger.error(e.getMsg());
            }
        }
        return res;
    }

    public static String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    /**
     * RSA字符串私钥加密
     *
     * @return
     */
    protected String rsaEncryptByPrivateKey(String s) {
        return Constants.isRSA ? RSAEncryptByPrivateKey.getInstance(Constants.DEFAULT_PRIVATE_KEY).encryptPrivateRSA(s)
                : s;
    }

    @SuppressWarnings("rawtypes")
    protected WebResponse webResponse(MenuEnums me, ReturnInterface t) {
        try {
            SecurityUtils.getWebSecurityManager().checkAuthorization(me);
            return t.webResponse();
        } catch (PermissionsException e) {
            e.printStackTrace();
            WebResponse wb = new WebResponse();
            wb.put("status", false);
            wb.put("info", "您没有权限访问该功能!");
            wb.put("rows", new ArrayList());
            wb.put("total", 0);
            return wb;
        }
    }

    /**
     * 得到ModelAndView并获取所有的操作权限code
     *
     * @return
     */
    protected ModelAndView getModelAndView(MenuEnums me) {
        ModelAndView mv = new ModelAndView();
        List<String> codes = SecurityUtils.getWebSecurityManager().getArightsForCodes(me);
        mv.addObject("hrightsCodes", JSONArray.toJSONString(codes));
        return mv;
    }

    /**
     * 得到ModelAndView并获取所有的操作权限code
     *
     * @return
     */
    protected ModelAndView getModelAndView(String uri) {
        ModelAndView mv = new ModelAndView();
        List<String> codes = SecurityUtils.getWebSecurityManager().getArightsForCodes(new MenuEnums() {
            @Override
            public String getUri() {
                return uri;
            }

            @Override
            public String getCode() {
                return null;
            }
        });
        mv.addObject("hrightsCodes", JSONArray.toJSONString(codes));
        return mv;
    }

    /**
     * 校验权限 若没有权限则直接抛异常
     *
     * @param url
     * @param code
     * @return
     */
    protected void checkArights(String url, String code) {
        SecurityUtils.getWebSecurityManager().checkAuthorization(new MenuEnums() {
            @Override
            public String getUri() {
                return url;
            }

            @Override
            public String getCode() {
                return code;
            }
        });
    }

    /**
     * 得到ModelAndView并获取所有的操作权限code
     *
     * @return
     */
    protected ModelAndView getModelAndView(String uri, String code) {
        ModelAndView mv = new ModelAndView();
        List<String> codes = SecurityUtils.getWebSecurityManager().getArightsForCodes(new MenuEnums() {
            @Override
            public String getUri() {
                return uri;
            }

            @Override
            public String getCode() {
                return code;
            }
        });
        mv.addObject("hrightsCodes", JSONArray.toJSONString(codes));
        return mv;
    }

    /**
     * 得到ModelAndView并获取所有的操作权限code
     *
     * @return
     */
    protected ModelAndView getModelAndView(MenuEnums me, boolean isVerify) {
        ModelAndView mv = new ModelAndView();
        if (isVerify) {
            try {
                SecurityUtils.getWebSecurityManager().checkAuthorization(me);
                List<String> codes = SecurityUtils.getWebSecurityManager().getArightsForCodes(me);
                mv.addObject("hrightsCodes", JSONArray.toJSONString(codes));
                return mv;
            } catch (PermissionsException e) {
                e.printStackTrace();
                mv.setViewName("admin/system/nopermission");
                return mv;
            }
        } else {
            List<String> codes = SecurityUtils.getWebSecurityManager().getArightsForCodes(me);
            mv.addObject("hrightsCodes", JSONArray.toJSONString(codes));
            return mv;
        }

    }

    /**
     * 将请求参数封装为Dto
     *
     * @return
     */
    protected DTO<String, String> getParamAsDTO() {
        DTO<String, String> dto = new BaseDTO<String, String>();
        Enumeration<String> paramNames = this.getRequest().getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String paramValues = this.getRequest().getParameter(paramName);
            dto.put(paramName, paramValues);
        }
        return dto;
    }

    /**
     * 获取一个带用户会话状态的请求参数对象
     *
     * @return
     */
    protected DTO<String, String> getParamAsSessionDTO() {
        DTO<String, String> dto = new SessionDTO<String, String>();
        dto.put(DTO.SESSION_KEY_USERID, SecurityUtils.getSubject().getUserid());
        dto.put(DTO.SESSION_KEY_USERNAME, SecurityUtils.getSubject().getUsername());
        dto.put(DTO.SESSION_KEY_SYSTEMID, SecurityUtils.getWebSecurityManager().getSid());
        dto.put(DTO.SESSION_KEY_CLIENTIP, SecurityUtils.getSubject().getSession().getHost());
        dto.put(DTO.SESSION_KEY_CLIENTTIME, DateUtils.getCurrDateTime());
        dto.put("aa01", dto.get(DTO.SESSION_KEY_USERID));
        dto.put("aa11", dto.get(DTO.SESSION_KEY_USERNAME));
        dto.put("aa02", DateUtils.getCurrDateTime());
        dto.put("ab01", dto.get(DTO.SESSION_KEY_USERID));
        dto.put("ab11", dto.get(DTO.SESSION_KEY_USERNAME));
        dto.put("ab02", DateUtils.getCurrDateTime());
        dto.putAll(getParamAsDTO());
        return dto;
    }

    /**
     * 构建一个带用户状态信息的DTO
     *
     * @return
     */
    protected DTO<String, String> buildSessionDTO() {
        DTO<String, String> dto = new SessionDTO<String, String>();
        dto.put(DTO.SESSION_KEY_USERID, SecurityUtils.getSubject().getUserid());
        dto.put(DTO.SESSION_KEY_USERNAME, SecurityUtils.getSubject().getUsername());
        dto.put(DTO.SESSION_KEY_SYSTEMID, SecurityUtils.getWebSecurityManager().getSid());
        dto.put(DTO.SESSION_KEY_CLIENTIP, SecurityUtils.getSubject().getSession().getHost());
        dto.put(DTO.SESSION_KEY_CLIENTTIME, DateUtils.getCurrDateTime());
        return dto;
    }

    /**
     * 构建一个Entity
     *
     * @param entity
     * @return
     */
    protected <T extends BaseEntity> T buildEntity(Class<T> entity) {
        try {
            T t = entity.newInstance();
            t.setAa01(Integer.valueOf(SecurityUtils.getSubject().getUserid()));
            t.setAa02(DateUtils.getCurrDateTimeToLong());
            t.setAb01(Integer.valueOf(SecurityUtils.getSubject().getUserid()));
            t.setAb02(DateUtils.getCurrDateTimeToLong());
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 构建一个Entity
     *
     * @param entity
     * @return
     */
    protected <E extends BaseEntity> E buildEntity(E entity) {
        entity.setAa01(Integer.valueOf(SecurityUtils.getSubject().getUserid()));
        entity.setAa02(DateUtils.getCurrDateTimeToLong());
        entity.setAb01(Integer.valueOf(SecurityUtils.getSubject().getUserid()));
        entity.setAb02(DateUtils.getCurrDateTimeToLong());
        return entity;
    }

    /**
     * 将请求参数封装为Dto
     *
     * @return
     */
    protected Map<String, String> getParamAsMap() {
        Map<String, String> dto = new HashMap<String, String>();
        Enumeration<String> paramNames = this.getRequest().getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String paramValues = this.getRequest().getParameter(paramName);
            dto.put(paramName, paramValues);
        }
        return dto;
    }

    protected DTO<String, String> getParamAsDTO(String useridKey) {
        DTO<String, String> dto = getParamAsDTO();
        dto.put(useridKey, getToken(dto));
        return dto;
    }

    protected DTO<String, String> getDecodeParamAsDto(String useridKey) {
        DTO<String, String> dto = getParamAsDTO();
        dto.put(useridKey, getDecodeToken(dto));
        return dto;
    }

    protected static String getToken(DTO<String, String> dto) {
        if (dto.containsKey(Constants.tokenKey)) {
            if (Constants.isRSA) {
                String token = RSAEncryptByPublicKey.getInstance(Constants.DEFAULT_PUBLIC_KEY)
                        .decryptPublicRSA(dto.getAsString(Constants.tokenKey));
                return token == null ? "" : token;
            }
            return dto.getAsString(Constants.tokenKey);
        }
        return "";
    }

    protected static String getDecodeToken(DTO<String, String> dto) {
        if (dto.containsKey(Constants.tokenKey)) {
            if (Constants.isRSA) {
                try {
                    String token = RSAEncryptByPublicKey.getInstance(Constants.DEFAULT_PUBLIC_KEY)
                            .decryptPublicRSA(java.net.URLDecoder.decode(dto.getAsString(Constants.tokenKey), "utf-8"));
                    return token == null ? "" : token;
                } catch (Exception e) {
                    return "";
                }
            }
            return dto.getAsString(Constants.tokenKey);
        }
        return "";
    }

    /**
     * 获取项目的当前路径
     *
     * @return
     */
    protected String httpRequestUrl() {
        String path = this.getRequest().getContextPath();
        String basePath = this.getRequest().getScheme() + "://" + this.getRequest().getServerName() + ":" + this.getRequest().getServerPort() + path + "/";
        return basePath;
    }

    /**
     * 获取当前的系统时间，主要用于操作时间的传入
     *
     * @return
     */
    protected Long getCurrentTime() {
        return DateUtils.getCurrDateTimeToLong();
    }

    /**
     * 获取用户ID
     *
     * @return
     */
    protected int getUserId() {
        return Integer.parseInt(SecurityUtils.getSubject().getUserid());
    }

    /**
     * 获取用户名
     *
     * @return
     */
    protected String getUserName() {
        return SecurityUtils.getSubject().getUsername();
    }

    /**
     * 设置COKIE
     */
    protected void setCookie(String key, String value) {
        Cookie cookie = CookieTools.getCookieByName(getRequest(), key);
        if (null == cookie) {
            cookie = new Cookie(key, value);
        } else {
            cookie.setValue(value);
        }
        cookie.setHttpOnly(false);
        cookie.setDomain("");
        cookie.setPath("/");
        getResponse().addCookie(cookie);
    }

}
