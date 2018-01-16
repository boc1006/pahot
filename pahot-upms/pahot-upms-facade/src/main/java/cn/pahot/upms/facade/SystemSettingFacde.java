package cn.pahot.upms.facade;

import cn.pahot.upms.entity.SystemSettingEntity;
import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import com.boc.common.page.PageParams;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: yuzhiyun
 * @Date: 2017/12/11
 * @Description:
 */
public interface SystemSettingFacde {

    /**
     * 通过主键id查找
     *
     * @param id 主键id(必填)
     * @return
     */
    SystemSettingEntity queryById(Integer id) throws BizException;

    /**
     * 查找系统变量列表
     *
     * @param sid 系统编号(必填)
     * @return
     */
    List<SystemSettingEntity> queryBySid(String sid) throws BizException;

    /**
     * 根据系统变量key查找变量
     *
     * @param key 系统变量key(必填)
     * @return
     */
    SystemSettingEntity queryByKey(String key) throws BizException;

    /**
     * 查找所有的系统变量列表
     *
     * @return
     */
    PageInfo<SystemSettingEntity> querySettingList(PageParams<DTO<String, String>> pageParams) throws BizException;

    /**
     * 添加
     *
     * @param systemSettingEntity systemSetting.sid 系统编号(必填)
     *                            systemSetting.setKey 变量KEY(必填)
     *                            systemSetting.setValue 变量VALUE(必填)
     *                            systemSetting.setDesc 变量描述
     *                            systemSetting.aa01 创建人(必填)
     */
    void saveSystemSetting(SystemSettingEntity systemSettingEntity) throws BizException;

    /**
     * 修改
     *
     * @param systemSettingEntity systemSetting.id 系统变量主键id(必填)
     *                            systemSetting.sid 系统编号
     *                            systemSetting.setKey 变量KEY
     *                            systemSetting.setValue 变量VALUE
     *                            systemSetting.setDesc 变量描述
     *                            systemSetting.enable 状态 00 表示禁用 01表示启用,默认值=01
     *                            systemSetting.editMode 编辑状态 00 表示不可编辑 01表示可编辑,默认值=01
     *                            systemSetting.ab01 修改人
     */
    void updateSystemSetting(SystemSettingEntity systemSettingEntity) throws BizException;

    /**
     * 启用或禁用
     *
     * @param id     系统变量主键id(必填)
     * @param enable 状态 00 表示禁用 01表示启用,默认值=01(必填)
     */
    void updateSystemSettingState(Integer id, String enable) throws BizException;

    /**
     * 将系统变量数据load到redis
     */
    void loadSystemSettingToRedis() throws BizException;

}
