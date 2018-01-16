package cn.pahot.upms.service;

import cn.pahot.upms.biz.SystemBiz;
import cn.pahot.upms.entity.DataIndexEntity;
import cn.pahot.upms.entity.SystemManagerEntity;
import cn.pahot.upms.facade.SystemFacade;
import com.boc.common.exception.BizException;
import com.boc.common.page.PageParams;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("systemFacade")
public class SystemService implements SystemFacade {

    @Autowired
    private SystemBiz systemBiz;

    /**
     * 启用或者禁用这个系统
     *
     * @param systemManagerEntity 参数包括:
     * @throws BizException
     */
    @Override
    public void enableOrdisableSys(SystemManagerEntity systemManagerEntity) throws BizException {


        systemBiz.enableOrdisableSys(systemManagerEntity);
    }


    @Override
    public void addItemToSys(SystemManagerEntity systemManagerEntity) throws BizException {

        systemBiz.addItemToSys(systemManagerEntity);
    }

    @Override
    public List<SystemManagerEntity> getSysList(String state) throws BizException {
        return systemBiz.getSysList(state);
    }

    @Override
    public SystemManagerEntity getSysItem(String id) throws BizException {
        return systemBiz.getSysItem(id);
    }

    @Override
    public void editSys(SystemManagerEntity systemManagerEntity) throws BizException {
        systemBiz.editSys(systemManagerEntity);
    }

    @Override
    public void addDataIndexItem(DataIndexEntity dataIndexEntity) {
        systemBiz.addDataIndexItem(dataIndexEntity);
    }

    @Override
    public DataIndexEntity getDataIndexItem(Long id) {
        return systemBiz.getDataIndexItem(id);
    }

    @Override
    public void editDataIndexItem(DataIndexEntity dataIndexEntity) {
        systemBiz.editDataIndexItem(dataIndexEntity);
    }

    @Override
    public void enableOrDisableDataIndexItem(DataIndexEntity dataIndexEntity) {
        systemBiz.enableOrDisableDataIndexItem(dataIndexEntity);
    }

    @Override
    public void disableEditDataIndexItem(DataIndexEntity dataIndexEntity) {
        systemBiz.disableEditDataIndexItem(dataIndexEntity);
    }

    @Override
    public PageInfo<DataIndexEntity> getDataIndexList(PageParams<DataIndexEntity> pageParams) {
        return systemBiz.getDataIndexList(pageParams);
    }


}
