package cn.pahot.upms.dao;

import cn.pahot.upms.entity.SystemSettingEntity;
import com.boc.common.metatype.DTO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: yuzhiyun
 * @Date: 2017/12/11
 * @Description:
 */
public interface SystemSettingMapper {

    /**
     * 通过主键id查找
     * @param id
     * @return
     */
    SystemSettingEntity selectById(Integer id);

    /**
     * 查找指定系统的变量列表
     * @return
     */
    List<SystemSettingEntity> selectBySid(String sid);

    /**
     * 根据系统变量key查找变量
     * @param setKey
     * @return
     */
    SystemSettingEntity selectByKey(String setKey);

    /**
     * 查找所有的系统变量列表
     * @return
     */
    Page<SystemSettingEntity> selectAll();

    /**
     * 添加
     * @param systemSettingEntity
     */
    void insert(SystemSettingEntity systemSettingEntity);

    /**
     * 修改
     * @param systemSettingEntity
     */
    void update(SystemSettingEntity systemSettingEntity);

    /**
     * 启用或禁用
     * @param id
     * @param enable
     */
    void updateState(@Param("id") Integer id, @Param("enable") String enable);

}
