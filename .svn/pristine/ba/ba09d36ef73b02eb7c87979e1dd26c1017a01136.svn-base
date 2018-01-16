package cn.pahot.upms.dao;

import cn.pahot.upms.entity.DataIndexEntity;
import cn.pahot.upms.entity.SystemManagerEntity;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemMapper {
    /*启用,禁用系统*/
    void enableOrSys(SystemManagerEntity systemManagerEntity);

    /* 查询所有的系统列表*/
    List<SystemManagerEntity> getSysList(@Param("state") String state);

    /* 修该系统信息*/
    void editSys(SystemManagerEntity systemManagerEntity);

    /*添加一条数据到系统管理中*/
    void addItemToSys(SystemManagerEntity systemManagerEntity);

    /*根据id获取一条数据*/
    SystemManagerEntity getSysItem(String id);

    /*增加一条数据字典*/
    void addDataIndexItem(DataIndexEntity dataIndexEntity);

    DataIndexEntity getDataIndexItem(@Param("id") Long id);

    /*修改一条数据字典*/
    void editDataIndexItem(DataIndexEntity dataIndexEntity);

    /* 启用或者禁用一条数据字典*/
    void enableOrDisableDataIndexItem(DataIndexEntity dataIndexEntity);

    /*禁止编辑一条数据字典*/
    void disableEditDataIndexItem(DataIndexEntity dataIndexEntity);

    /*分页查询*/
    Page<DataIndexEntity> getDataIndexList(@Param("field") String field, @Param("sid") String sid);
}
