package cn.pahot.upms.service;

import cn.pahot.upms.biz.SystemSettingBiz;
import cn.pahot.upms.entity.SystemSettingEntity;
import cn.pahot.upms.facade.SystemSettingFacde;
import com.boc.common.metatype.DTO;
import com.boc.common.page.PageParams;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yuzhiyun
 * @Date: 2017/12/11
 * @Description:
 */
@Service("systemSettingFacade")
public class SystemSettingService implements SystemSettingFacde {

    @Autowired
    private SystemSettingBiz systemSettingBiz;

    @Override
    public SystemSettingEntity queryById(Integer id) {
        return systemSettingBiz.findById(id);
    }

    @Override
    public List<SystemSettingEntity> queryBySid(String sid) {
        return systemSettingBiz.findBySid(sid);
    }

    @Override
    public SystemSettingEntity queryByKey(String key) {
        return systemSettingBiz.findByKey(key);
    }

    @Override
    public PageInfo<SystemSettingEntity> querySettingList(PageParams<DTO<String, String>> pageParams) {
        return systemSettingBiz.findAll(pageParams);
    }

    @Override
    public void saveSystemSetting(SystemSettingEntity systemSettingEntity) {
        systemSettingBiz.add(systemSettingEntity);
    }

    @Override
    public void updateSystemSetting(SystemSettingEntity systemSettingEntity) {
        systemSettingBiz.update(systemSettingEntity);
    }

    @Override
    public void updateSystemSettingState(Integer id, String enable) {
        systemSettingBiz.updateState(id, enable);
    }

    @Override
    public void loadSystemSettingToRedis() {
        systemSettingBiz.loadToRedis();
    }
}