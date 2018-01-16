package cn.pahot.upms.controller;

import cn.pahot.upms.access.AccessCode;
import cn.pahot.upms.access.AccessUrl;
import cn.pahot.upms.entity.DataIndexEntity;
import cn.pahot.upms.facade.SystemFacade;
import com.boc.annotation.AccessControl;
import com.boc.common.metatype.DTO;
import com.boc.common.page.PageParams;
import com.boc.common.web.WebResponse;
import com.boc.common.web.permissions.SecurityUtils;
import com.boc.common.web.springmvc.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;


/**
 * 数据字典
 */
@Controller
@RequestMapping("/code")
public class CodeController extends BaseController {

    @Resource
    private SystemFacade systemFacade;


    @RequestMapping("/index.sec")
    @AccessControl(url=AccessUrl.CODE_INDEX)
    public ModelAndView index() {
        ModelAndView mv = getModelAndView(AccessUrl.CODE_INDEX);
        mv.setViewName("code/index");
        return mv;
    }

    /**
     * 获取数据字典列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCodeList.sec")
    public WebResponse getCodeList() {
        WebResponse wb = new WebResponse();
        DTO params = getParamAsDTO();
        PageParams<DataIndexEntity> pageParams = new PageParams<>();
        pageParams.setPageNo(params.getAsInteger("page"));
        pageParams.setPageSize(params.getAsInteger("pagesize"));
        DataIndexEntity die = new DataIndexEntity();
        die.setSid(params.getAsString("sysid"));
        die.setField(params.getAsString("key"));
        pageParams.setParams(die);
        PageInfo<DataIndexEntity> rst = systemFacade.getDataIndexList(pageParams);
        wb.setDataGridMsg(true, "查询成功", rst.getList(), rst.getTotal());
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
    @RequestMapping("addoredit.sec")
    public WebResponse addoredit() {
        WebResponse wb = new WebResponse();
        DataIndexEntity obj = new DataIndexEntity();
        DTO params = getParamAsDTO();
        String state = params.getAsString("state");
        String sid = params.getAsString("type");
        String field = params.getAsString("field");
        String fieldname = params.getAsString("fieldname");
        String code = params.getAsString("code");
        String codedesc = params.getAsString("codedesc");
        Integer sort = params.getAsInteger("sort");
        String remark = params.getAsString("remark");


        int userId = Integer.parseInt(SecurityUtils.getSubject().getUserid());
        obj.setAa01(userId);
        obj.setAa02(Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis())));

        obj.setSid(sid);
        obj.setField(field);
        obj.setFieldname(fieldname);
        obj.setCode(code);
        obj.setCodedesc(codedesc);
        obj.setSort(sort);
        obj.setRemark(remark);

        if ("2".equals(state)) {
            checkArights(AccessUrl.CODE_INDEX,AccessCode.EDIT);
            Long id = params.getAsLong("id");
            obj.setId(id);
            obj.setAb01(userId);
            obj.setAb02(Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis())));
            systemFacade.editDataIndexItem(obj);
        } else if ("1".equals(state)) {
            checkArights(AccessUrl.CODE_INDEX,AccessCode.ADD);
            systemFacade.addDataIndexItem(obj);
        } else {
            wb.setAjaxMsg(false, "失败【state】非法", "", "");
            return wb;
        }
        wb.setAjaxMsg(true, "成功", "", "");
        return wb;
    }

    /**
     * 跟新数据字典的状态
     * 1 禁用
     * 2 启用
     * 3 禁用编辑
     * 5 删除
     */
    @ResponseBody
    @RequestMapping("/updatestate.sec")
    public WebResponse updateItem() {
        WebResponse wb = new WebResponse();
        DTO params = getParamAsDTO();
        String state = params.getAsString("state");
        DataIndexEntity code = new DataIndexEntity();
        code.setId(params.getAsLong("id"));
        int userId = Integer.parseInt(SecurityUtils.getSubject().getUserid());
        code.setAb01(userId);
        code.setAb02(Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis())));
        if ("1".equals(state)) {
            checkArights(AccessUrl.CODE_INDEX,AccessCode.DISABLED);
            code.setEnabled("00");
            systemFacade.enableOrDisableDataIndexItem(code);
        }else if ("2".equals(state)) {
            checkArights(AccessUrl.CODE_INDEX,AccessCode.ENABLED);
            code.setEnabled("01");
            systemFacade.enableOrDisableDataIndexItem(code);
        }else if ("3".equals(state)) {
            checkArights(AccessUrl.CODE_INDEX,AccessCode.DISABLED);
            systemFacade.disableEditDataIndexItem(code);
        }else if("4".equals(state)){

        }else if("5".equals(state)){

        } else {
            wb.setAjaxMsg(false, "失败【state】非法", "", "");
            return wb;
        }
        wb.setAjaxMsg(true, "成功", "", "");
        return wb;
    }
}
