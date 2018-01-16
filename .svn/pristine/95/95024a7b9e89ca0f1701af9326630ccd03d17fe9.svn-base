package cn.pahot.upms.controller;

import cn.pahot.upms.access.AccessCode;
import cn.pahot.upms.access.AccessUrl;
import cn.pahot.upms.enums.UPMSMenuEnum;
import cn.pahot.xa.entity.MessageSubEntity;
import cn.pahot.xa.facade.CapXaFacade;
import com.boc.annotation.AccessControl;
import com.boc.common.metatype.DTO;
import com.boc.common.web.WebResponse;
import com.boc.common.web.permissions.SecurityUtils;
import com.boc.common.web.springmvc.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * 数据字典
 */
@Controller
@RequestMapping("/sub")
public class SubController extends BaseController {

    @Resource
    private CapXaFacade capXaFacade;

    @RequestMapping("/index.sec")
    @AccessControl(url = AccessUrl.SUB_INDEX)
    public ModelAndView index() {
        ModelAndView mv = getModelAndView(AccessUrl.SUB_INDEX);
        mv.setViewName("sub/index");
        return mv;
    }

    /**
     * 获取数据字典列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getSubList.sec")
    public WebResponse getSubList() {
        WebResponse wb = new WebResponse();
        DTO params = getParamAsDTO();
        String idStr = params.getAsString("id");
        String subname = params.getAsString("subname");
        String queuename = params.getAsString("queuename");

        MessageSubEntity mse = new MessageSubEntity();
        if (idStr != null && idStr.matches("\\d+"))
            mse.setId(Integer.valueOf(idStr));
        if (subname != null)
            mse.setSubname(subname.trim());
        if (queuename != null)
            mse.setQueuename(queuename.trim());

        List<MessageSubEntity> mseList = capXaFacade.querySubMessage(mse);
        wb.setDataGridMsg(true, "查询成功", mseList, (long) mseList.size());
        return wb;
    }

    @ResponseBody
    @RequestMapping("/updateSubMessage.sec")
    @AccessControl(url = AccessUrl.SUB_INDEX, code = AccessCode.EDIT)
    public WebResponse updateSubMessage() {
        WebResponse wb = new WebResponse();
        MessageSubEntity obj = new MessageSubEntity();
        DTO params = getParamAsDTO();
        /**
         * 保存订阅消息
         * @param sub
         * int id, 订阅消息编号-必传
         * String subname, 订阅消息名称-必传
         * String type,消息类型：1-json,2-xml 必传
         * String source,消息来源-必传
         * String queuename,队列名称，多个用','分隔-必传
         * String dbid,资源编号-必传
         * String remark,备注-非必传
         * String aa01,创建人编号
         * String aa11,创建人姓名
         */
        Integer id = params.getAsInteger("id1");
        obj.setId(id);
        String subname = params.getAsString("subname");
        obj.setSubname(subname);
        String type = params.getAsString("type");
        obj.setType(type);
        String source = params.getAsString("source");
        obj.setSource(source);
        String queuename = params.getAsString("queuename");
        obj.setQueuename(queuename);
        String delays = params.getAsString("delays");
        delays = delays.replaceAll("\\s+", "");
        obj.setDelays(delays);
        String dbid = params.getAsString("dbid");
        obj.setDbid(dbid);
        String remark = params.getAsString("remark");
        obj.setRemark(remark);
        int userId = Integer.parseInt(SecurityUtils.getSubject().getUserid());
        obj.setAb01(String.valueOf(userId));
        obj.setAb11(SecurityUtils.getSubject().getUsername());
        obj.setAb02(Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis())));
        capXaFacade.updateSubMessage(obj);
        wb.setAjaxMsg(true, "成功", "", "");
        return wb;
    }

    /**
     * 新增或修改数据字典 2 edit ,1add
     * <p>
     * 必传--->sid(系统编号,对应系统管理的id)
     * 必传--->field(对照字段)
     * 必传--->fieldname(对照字段名称)
     * 必传--->code(代码)
     * 必传--->codedesc(代码描述)
     * 可以不传--->sort(排序号):默认为1
     * 可以不传--->remark(字典描述)
     * 必传--->aa01(创建人)
     * 必传--->aa02(创建时间)
     */
    @ResponseBody
    @RequestMapping("/addSubMessage.sec")
    @AccessControl(url = AccessUrl.SUB_INDEX, code = AccessCode.ADD)
    public WebResponse addSubMessage() {
        WebResponse wb = new WebResponse();
        MessageSubEntity obj = new MessageSubEntity();
        DTO params = getParamAsDTO();

        /**
         * 保存订阅消息
         * @param sub
         * int id, 订阅消息编号-必传
         * String subname, 订阅消息名称-必传
         * String type,消息类型：1-json,2-xml 必传
         * String source,消息来源-必传
         * String queuename,队列名称，多个用','分隔-必传
         * String dbid,资源编号-必传
         * String remark,备注-非必传
         * String aa01,创建人编号
         * String aa11,创建人姓名
         */
        Integer id = params.getAsInteger("id1");
        obj.setId(id);
        String subname = params.getAsString("subname");
        obj.setSubname(subname);
        String type = params.getAsString("type");
        obj.setType(type);
        String source = params.getAsString("source");
        obj.setSource(source);
        String queuename = params.getAsString("queuename");
        obj.setQueuename(queuename);
        String delays = params.getAsString("delays");
        obj.setDelays(delays);
        String dbid = params.getAsString("dbid");
        obj.setDbid(dbid);
        String remark = params.getAsString("remark");
        obj.setRemark(remark);
        int userId = Integer.parseInt(SecurityUtils.getSubject().getUserid());
        obj.setAa01(String.valueOf(userId));
        obj.setAa11(SecurityUtils.getSubject().getUsername());
        obj.setAa02(Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis())));
        capXaFacade.saveSubMessage(obj);
        wb.setAjaxMsg(true, "成功", "", "");
        return wb;
    }

    /**
     * 更新状态
     * 1 启用
     * 2 禁用
     * 0 删除
     */
    @ResponseBody
    @RequestMapping("/updatestate.sec")
    public WebResponse updateItem() {
        WebResponse wb = new WebResponse();
        DTO params = getParamAsDTO();
        String state = params.getAsString("state");
        MessageSubEntity mse = new MessageSubEntity();
        mse.setId(params.getAsInteger("id"));
        int userId = Integer.parseInt(SecurityUtils.getSubject().getUserid());
        mse.setAb01(String.valueOf(userId));
        mse.setAb11(SecurityUtils.getSubject().getUsername());
        if ("1".equals(state) || "2".equals(state) || "0".equals(state)) {
            if ("1".equals(state)) {
                checkArights(AccessUrl.SUB_INDEX,AccessCode.ENABLED);
            } else if ("2".equals(state)) {
                checkArights(AccessUrl.SUB_INDEX,AccessCode.DISABLED);
            } else if ("0".equals(state)) {
                checkArights(AccessUrl.SUB_INDEX,AccessCode.DELETE);
            }
            mse.setState(state);
        } else {
            wb.setAjaxMsg(false, "失败【state】非法", "", "");
            return wb;
        }
        capXaFacade.updateSubMessageStateForId(mse);
        wb.setAjaxMsg(true, "成功", "", "");
        return wb;
    }
}
