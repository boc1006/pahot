package cn.pahot.upms.biz;

import cn.pahot.upms.dao.SystemMapper;
import cn.pahot.upms.entity.DataIndexEntity;
import cn.pahot.upms.entity.SystemManagerEntity;
import com.boc.common.core.biz.AbstractPageBuilder;
import com.boc.common.core.biz.DefaultPageBuilder;
import com.boc.common.exception.BizException;
import com.boc.common.page.PageParams;
import com.boc.common.utils.string.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.pahot.upms.consts.UPMSConst.EXCEPTION_PARAMS_Null;
import static cn.pahot.upms.consts.UPMSConst.PAGEINFO_DEFAULT_PAGENUM;
import static cn.pahot.upms.consts.UPMSConst.PAGEINFO_DEFAULT_PAGESIZE;

@Service("systemBiz")
public class SystemBiz {


    @Autowired
    private SystemMapper systemMapper;


    public void enableOrdisableSys(SystemManagerEntity systemManagerEntity) throws BizException {

        if (systemManagerEntity.getId() == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "参数id不能为空");
        } else if (systemManagerEntity.getState() == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "参数State不能为空");
        } else if (getSysItem(systemManagerEntity.getId()) == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "当前id=" + systemManagerEntity.getId() + ",对应的数据不存在");
        } else if (systemManagerEntity.getAb01() == null || systemManagerEntity.getAb02() == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "修改人信息没有传入");
        }


        systemMapper.enableOrSys(systemManagerEntity);
    }

    public void addItemToSys(SystemManagerEntity systemManagerEntity) throws BizException {
        if (systemManagerEntity == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "参数不能为空");
        } else if (systemManagerEntity.getType() == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "type参数不能为空");
        } else if (systemManagerEntity.getUrl() == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "url参数不能为空");
        } else if (systemManagerEntity.getName() == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "name参数不能为空");
        } else if (systemManagerEntity.getId() == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "id参数不能为空");
        } else if (getSysItem(systemManagerEntity.getId()) != null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "当前id=" + systemManagerEntity.getId() + "已经存在");
        } else if (systemManagerEntity.getAa01() == null || systemManagerEntity.getAa02() == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "添加人信息没有传入");
        }
        systemMapper.addItemToSys(systemManagerEntity);
    }

    public List<SystemManagerEntity> getSysList(String state) throws BizException {
        return systemMapper.getSysList(state);
    }

    public SystemManagerEntity getSysItem(String id) throws BizException {

        if (StringUtil.isEmpty(id)) {
            throw new BizException(EXCEPTION_PARAMS_Null, "id参数不能为空");
        }

        return systemMapper.getSysItem(id);
    }

    public void editSys(SystemManagerEntity systemManagerEntity) throws BizException {

        if (systemManagerEntity == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "参数不能为空");
        } else if (systemManagerEntity.getId() == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "参数id不能为空");
        } else if (getSysItem(systemManagerEntity.getId()) == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "当前id=" + systemManagerEntity.getId() + ",对应的数据不存在");
        } else if (systemManagerEntity.getAb01() == null || systemManagerEntity.getAb02() == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "修改人信息没有传入");
        }

        systemMapper.editSys(systemManagerEntity);
    }


    public void addDataIndexItem(DataIndexEntity dataIndexEntity) throws BizException {
        if (dataIndexEntity == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "参数不能为空");
        } else if (StringUtil.isEmpty(dataIndexEntity.getSid())) {
            throw new BizException(EXCEPTION_PARAMS_Null, "sid参数不能为空");
        } else if (StringUtil.isEmpty(dataIndexEntity.getField())) {
            throw new BizException(EXCEPTION_PARAMS_Null, "Field参数不能为空");
        } else if (StringUtil.isEmpty(dataIndexEntity.getFieldname())) {
            throw new BizException(EXCEPTION_PARAMS_Null, "Fieldname参数不能为空");
        } else if (StringUtil.isEmpty(dataIndexEntity.getCode())) {
            throw new BizException(EXCEPTION_PARAMS_Null, "code参数不能为空");
        } else if (StringUtil.isEmpty(dataIndexEntity.getCodedesc())) {
            throw new BizException(EXCEPTION_PARAMS_Null, "Codedesc参数不能为空");
        } else if (systemMapper.getSysItem(dataIndexEntity.getSid()) == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "对应系统管理中的id=" + dataIndexEntity.getSid() + "不存在");
        } else if (dataIndexEntity.getAa01() == null || dataIndexEntity.getAa02() == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "添加人信息没有传入");
        }

        systemMapper.addDataIndexItem(dataIndexEntity);
    }

    public DataIndexEntity getDataIndexItem(Long id) throws BizException {

        if (id == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "参数id不能为空");
        }

        return systemMapper.getDataIndexItem(id);
    }

    public void enableOrDisableDataIndexItem(DataIndexEntity dataIndexEntity) throws BizException {

        if (dataIndexEntity == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "参数不能为空");
        } else if (dataIndexEntity.getId() == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "参数id不能为空");
        } else if (dataIndexEntity.getAb01() == null || dataIndexEntity.getAb02() == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "修改人信息没有传入");
        } else {
            DataIndexEntity result = getDataIndexItem(dataIndexEntity.getId());
            if (result == null) {
                throw new BizException(EXCEPTION_PARAMS_Null, "字典库中id=" + dataIndexEntity.getId() + "的数据不存在");
            } else if (result.getEditmode().trim().equals("00")) {
                throw new BizException(EXCEPTION_PARAMS_Null, "禁止编辑的字典不能修改和停用");
            }
        }
        systemMapper.enableOrDisableDataIndexItem(dataIndexEntity);
    }

    public void disableEditDataIndexItem(DataIndexEntity dataIndexEntity) throws BizException {
        if (dataIndexEntity == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "参数不能为空");
        } else if (dataIndexEntity.getId() == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "参数id不能为空");
        } else if (getDataIndexItem(dataIndexEntity.getId()) == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "字典库中id=" + dataIndexEntity.getId() + "数据不存在");
        } else if (dataIndexEntity.getAb01() == null || dataIndexEntity.getAb02() == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "修改人信息没有传入");
        }
        systemMapper.disableEditDataIndexItem(dataIndexEntity);
    }

    public PageInfo<DataIndexEntity> getDataIndexList(PageParams<DataIndexEntity> pageParams) throws BizException {

        if (pageParams == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "分页参数不能为空");
        }
        if (pageParams.getPageNo() <= 0) {
            pageParams.setPageNo(PAGEINFO_DEFAULT_PAGENUM);
        }

        if (pageParams.getPageSize() <= 0) {
            pageParams.setPageSize(PAGEINFO_DEFAULT_PAGESIZE);
        }


        return DefaultPageBuilder.build(pageParams,
                new AbstractPageBuilder<DataIndexEntity, DataIndexEntity>() {
                    @Override
                    public Page<DataIndexEntity> build(DataIndexEntity param) throws BizException {
                        return systemMapper.getDataIndexList(param == null ? null : param.getField(), param == null ? null : param.getSid());
                    }
                });

    }

    public void editDataIndexItem(DataIndexEntity dataIndexEntity) throws BizException {

        if (dataIndexEntity == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "参数不能为空");
        } else if (dataIndexEntity.getId() == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "参数id不能为空");
        } else if (dataIndexEntity.getAb01() == null || dataIndexEntity.getAb02() == null) {
            throw new BizException(EXCEPTION_PARAMS_Null, "修改人信息没有传入");
        } else {

            DataIndexEntity result = getDataIndexItem(dataIndexEntity.getId());
            if (result == null) {
                throw new BizException(EXCEPTION_PARAMS_Null, "id=" + dataIndexEntity.getId() + "的数据字典不存在");
            } else if (result.getEditmode().equals("00")) {
                throw new BizException(EXCEPTION_PARAMS_Null, "id=" + dataIndexEntity.getId() + "的数据字典处于禁止编辑状态");
            }
        }

        systemMapper.editDataIndexItem(dataIndexEntity);

    }
}