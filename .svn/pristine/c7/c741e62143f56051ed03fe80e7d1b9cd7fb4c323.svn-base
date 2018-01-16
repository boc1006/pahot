package cn.pahot.business.front.controller;

import cn.pahot.business.front.utils.WXUitls;
import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import com.boc.common.metatype.impl.BaseDTO;
import com.boc.common.utils.Logger;
import com.boc.common.utils.string.StringUtil;
import com.boc.common.web.springmvc.BaseController;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.Map;

@Controller
public class LoginWxController extends BaseController {

    //使用redis
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    final protected static String TABLE_NAME = "pt_upms_system_setting";

    @RequestMapping("login.ak")
    public ModelAndView login(){
        ModelAndView mv =new ModelAndView();

        HttpServletRequest request = getRequest();
        DTO dto = getParamAsDTO();
        String appId = dto.getAsString("appId");
        if(StringUtil.isEmpty(appId)){
            appId = stringRedisTemplate.opsForHash().get(TABLE_NAME,"wx_appId").toString();
            if(StringUtil.isEmpty(appId)){
                throw BizException.INSTANCE.newInstance("appid为空");
            }
        }
        String path = request.getContextPath();
        String baseUrl = request.getScheme()+"://"+request.getServerName()+path+"/";
        baseUrl=baseUrl+"wxlogincallback/"+appId+".ak";
        try{
            baseUrl = URLEncoder.encode(baseUrl,"utf-8");
        }catch (Exception e){

        }

        //原始参数传递
        StringBuffer buffer = new StringBuffer();
        for (Object object : dto.entrySet()) {
            Map.Entry<String,String> entry = (Map.Entry) object;
            String key = entry.getKey();
            if (key.startsWith("p_")){
                String v = entry.getValue();
                String k=key.substring(2);
                buffer.append(":"+k+"-"+v);
            }
        }

        String scope = "snsapi_userinfo";

        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
                +appId+"&redirect_uri="+baseUrl+"&response_type=code&scope="
                +scope+"&state=key#wechat_redirect";
        mv.setViewName("redirect:"+url);
        return mv;
    }


    @RequestMapping("/wxlogincallback/{appId}.ak")
    public ModelAndView baseForwardTwo(@PathVariable("appId") String appId){
        DTO dto = getParamAsDTO();
        //微信授权返回code
        String code = dto.getAsString("code");
        String appsecret = stringRedisTemplate.opsForHash().get(TABLE_NAME,"wx_app_secret").toString();
        if(StringUtil.isEmpty(appsecret)){
            throw BizException.INSTANCE.newInstance("appsecret为空");
        }
        //换取openId
        Map<String,Object> openiduser = WXUitls.getWxUser(appId,appsecret,code);
        //提取原来参数
        String state = dto.getAsString("state");
        DTO params = new BaseDTO();
        for (String kv : state.split(":")){
            String str[] = kv.split("-");
            if (str.length==2){
                params.put(str[0],str[1]);
            }
        }
        ModelAndView mv = new ModelAndView();
        if(null == openiduser){
            System.out.print("openiduser====null");
            mv.setViewName("redirect:/error");
            return mv;
        }
        String access_token = openiduser.get("access_token").toString();
        String openId = openiduser.get("openid").toString();
        System.out.print("access_token====" + access_token +"---------openId===="+openId);
        //获取用户unionId
        Map<String,Object> userInfo= WXUitls.getWxUnionId(access_token,openId);
        if (userInfo!=null){
            //能获取到微信用户信息
            DTO user = new BaseDTO<>(userInfo);
            String unionId = user.getAsString("unionid");
            params.put("unionId",unionId);
            params.put("headerImg",user.getAsString("headimgurl"));
            params.put("nickname",user.getAsString("nickname"));
            System.out.print("unionId====" + unionId +"---------headerImg===="+user.getAsString("headimgurl"));
            System.out.print("\nnickname====" + user.getAsString("nickname") );
        }
        //获取转发url
        /*String url=wxFacade.loadForwardUrl(appId,params.getAsString("key"));
        params.put("openId",openId);
        for (Object object : params.keySet()) {
            String value = params.getAsString(object.toString());
            url = url.replace("["+object.toString()+"]",value);
        }*/

        mv.setViewName("redirect:/index.ak");
        return mv;
    }

    @RequestMapping("/index.ak")
    public ModelAndView page(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index/index");
        return mv;
    }

}
