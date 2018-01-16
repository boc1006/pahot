package cn.pahot.upms.facade;

import cn.pahot.upms.entity.DataIndexEntity;
import cn.pahot.upms.entity.SystemManagerEntity;
import com.boc.common.exception.BizException;
import com.boc.common.page.PageParams;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 系统管理及数据字典管理接口定义
 * <p>@Title: OrganizationFacade.java
 * <p>@Package cn.pahot.upms.facade
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com
 * <p>@date 2017年12月6日 下午1:16:31
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public interface SystemFacade {


    /**
     * 系统启用或者禁用
     * SystemManagerEntity中需要传入的参数:
     * <p>
     * 必传--->id(系统编号):
     * 必传--->state(启用或者禁用的状态):00表示禁用,01表示启用
     * 必传--->ab01(修改人id),格式:100000
     * 必传--->ab02(修改时间),格式20171121161823
     * </p>
     **/
    void enableOrdisableSys(SystemManagerEntity systemManagerEntity) throws BizException;

    /**
     * 添加一条数据到系统管理
     * ystemManagerEntity中传入的参数:
     * <p>
     * 必传--->id(新增的系统编号)
     * 必传--->name(新增的系统名称):必传
     * 必传--->type(系统类型,01管理系统,02业务系统,03运维系统)
     * 必传--->url(系统访问根路径),示例:http://admin.pahotest.cn/upms
     * 可以不传--->iremark(系统描述)
     * 可以不传--->ilogo(LOGO图标)
     * 可以不传--->isort(排序序号):默认为1
     * 必传--->iaa01(创建人),格式:100000
     * 必传--->iaa02(创建时间),格式20171121161823
     * </p>
     **/
    void addItemToSys(SystemManagerEntity systemManagerEntity) throws BizException;

    /**
     * 查询所有的系统列表
     * <p>
     * 可以不传--->state(根据状态系统状态查询(01启用,00停用)),如果传入null则表示查询所有
     * </p>
     **/
    List<SystemManagerEntity> getSysList(String state) throws BizException;

    /**
     * 获取一条数据
     * <p>
     * 必传--->id(系统编号)
     * </p>
     **/
    SystemManagerEntity getSysItem(String id) throws BizException;

    /**
     * 修该系统信息
     * <p>
     * 必传--->id(系统编号)
     * 可以不传--->name(系统名称):
     * 可以不传--->type(系统类型):
     * 可以不传--->url(系统访问根路径)
     * 可以不传--->remark(系统描述):
     * 可以不传--->logo(LOGO图标):
     * 可以不传--->sort(排序序号):
     * 可以不传--->ab01(修改人)
     * 可以不传--->ab02(修改时间)
     * </p>
     **/
    void editSys(SystemManagerEntity systemManagerEntity) throws BizException;


    /**
     * 增加一条数据字典
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
     * </p>
     **/
    void addDataIndexItem(DataIndexEntity dataIndexEntity);


    /**
     * 获取一条数据
     * <p>
     * 必传--->id(数据字典中的id)
     * </p>
     */
    DataIndexEntity getDataIndexItem(Long id);

    /**
     * 修改一条数据字典
     * <p>
     * 必传--->id
     * 可以不传--->sid(系统编号,对应系统管理的id)
     * 可以不传--->field(对照字段)
     * 可以不传--->fieldname(对照字段名称)
     * 可以不传--->code(代码)
     * 可以不传--->codedesc(代码描述)
     * 可以不传--->sort(排序号)
     * 可以不传--->remark(字典描述)
     * 必传--->ab01(修改人)
     * 必传--->ab02(修改时间)
     * </p>
     **/
    void editDataIndexItem(DataIndexEntity dataIndexEntity);

    /**
     * 启用或者禁用一条数据字典
     * <p>
     * 必传--->id(数据字典中的id)
     * 必传--->enabled(状态):00(禁用), 01(启用)
     * 必传--->ab01(修改人)
     * 必传--->ab02(修改时间)
     * </p>
     **/
    void enableOrDisableDataIndexItem(DataIndexEntity dataIndexEntity);

    /**
     * 禁止编辑某一条数据字典
     * <p>
     * 必传--->id(数据字典中的id)
     * 必传--->ab01(修改人)
     * 必传--->ab02(修改时间)
     */
    void disableEditDataIndexItem(DataIndexEntity dataIndexEntity);

    /**
     * 分页查询
     * 可以不传--->pageNum(页码):默认值为1
     * 可以不传--->pageSize(每页返回的数量):默认值为8
     * 可以不传--->sid(系统编号,关联PT_UPMS.PT_UPMS_SYSCONF.ID)
     * 可以不传--->field(对照字段)
     **/
    PageInfo<DataIndexEntity> getDataIndexList(PageParams<DataIndexEntity> pageParams);

}
