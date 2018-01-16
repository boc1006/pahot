package cn.pahot.upms.biz;

import cn.pahot.upms.dao.SystemSettingMapper;
import cn.pahot.upms.entity.SystemSettingEntity;
import cn.pahot.upms.exception.SystemSettingException;
import com.boc.common.cache.redis.RedisKey;
import com.boc.common.core.biz.AbstractPageBuilder;
import com.boc.common.core.biz.DefaultPageBuilder;
import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import com.boc.common.page.PageParams;
import com.boc.common.utils.DateUtils;
import com.boc.common.utils.string.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @Author: yuzhiyun
 * @Date: 2017/12/11
 * @Description:
 */
@Service("systemSettingBiz")
public class SystemSettingBiz {

    @Autowired
    private SystemSettingMapper systemSettingMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /* 通过主键id查找*/
    public SystemSettingEntity findById(Integer id) {
        if (StringUtils.isEmpty(id))
            throw SystemSettingException.PARAM_IS_NULL.newInstance("系统变量ID");

        return systemSettingMapper.selectById(id);
    }

    /*查找指定系统的变量列表*/
    public List<SystemSettingEntity> findBySid(String sid) {
        if (StringUtils.isEmpty(sid))
            throw SystemSettingException.PARAM_IS_NULL.newInstance("系统变量SID");

        return systemSettingMapper.selectBySid(sid);
    }

    /* 根据系统变量key查找变量*/
    public SystemSettingEntity findByKey(String key) {
        if (StringUtils.isEmpty(key))
            throw SystemSettingException.PARAM_IS_NULL.newInstance("系统变量setKey");

        return systemSettingMapper.selectByKey(key);
    }

    /* 查找所有的系统变量列表*/
    public PageInfo<SystemSettingEntity> findAll(PageParams<DTO<String, String>> pageParams) {

        PageInfo<SystemSettingEntity> pi = DefaultPageBuilder.build(pageParams,
                new AbstractPageBuilder<SystemSettingEntity, DTO<String, String>>() {
                    @Override
                    public Page<SystemSettingEntity> build(DTO<String, String> param) throws BizException {
                        return systemSettingMapper.selectAll();
                    }
                });

        return pi;
    }


    public List<SystemSettingEntity> findAllWithoutPage() {
        return systemSettingMapper.selectAll();
    }


    /* 添加*/
    public void add(SystemSettingEntity systemSettingEntity) {
        if (StringUtils.isEmpty(systemSettingEntity))
            throw SystemSettingException.PARAM_IS_NULL.newInstance("系统变量信息");
        this.validateParams(systemSettingEntity);

        systemSettingEntity.setAa02(DateUtils.getCurrDateTimeToLong());
        systemSettingMapper.insert(systemSettingEntity);
        addStringItemToRedis(systemSettingEntity.getSetKey(), systemSettingEntity.getSetValue());
    }

    /* 修改*/
    public void update(SystemSettingEntity systemSettingEntity) {
        if (StringUtils.isEmpty(systemSettingEntity))
            throw SystemSettingException.PARAM_IS_NULL.newInstance("系统变量信息");

        Integer id = systemSettingEntity.getId();
        if (StringUtils.isEmpty(id))
            throw SystemSettingException.PARAM_IS_NULL.newInstance("系统变量ID");

        SystemSettingEntity ss = this.findById(id);
        if (Objects.isNull(ss)) {
            throw SystemSettingException.INSTANCE.newInstance("要修改的系统变量信息不存在！");
        } else if (ss.getEditMode().equals("00")) {
            throw SystemSettingException.INSTANCE.newInstance("当前id=" + ss.getId() + ",为不可编辑状态");
        }


        //key唯一性验证
        SystemSettingEntity sse = this.findByKey(systemSettingEntity.getSetKey());
        if (!Objects.isNull(sse) && !Objects.equals(sse.getId(), id))
            throw SystemSettingException.PARAM_HINT_ERROR.newInstance("key[" + systemSettingEntity.getSetKey() + "]已经存在！");

        systemSettingEntity.setAb02(DateUtils.getCurrDateTimeToLong());
        systemSettingMapper.update(systemSettingEntity);

        if (systemSettingEntity.getEnable().trim().equals("00")) {
            //如果是禁用状态则删除
            removeItemToRedis(systemSettingEntity.getSetKey());
        } else if (systemSettingEntity.getSetKey().equals(ss.getSetKey())) {
            //如果改变的数据和数据库里面数据的key相同则更新
            addStringItemToRedis(systemSettingEntity.getSetKey(), systemSettingEntity.getSetValue());
        } else {
            //如果改变的数据和数据库里面数据的key不同,则删除redis之前的key在插入一条新数据
            removeItemToRedis(ss.getSetKey());
            addStringItemToRedis(systemSettingEntity.getSetKey(), systemSettingEntity.getSetValue());
        }

    }

    /*启用或禁用*/
    public void updateState(Integer id, String enable) {
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(enable))
            throw SystemSettingException.PARAM_IS_NULL.newInstance("系统变量ID或者ENABLE");

        SystemSettingEntity ss = this.findById(id);
        if (Objects.isNull(ss)) {
            throw SystemSettingException.INSTANCE.newInstance("要修改的系统变量信息不存在！");
        } else if (ss.getEditMode().equals("00")) {
            throw SystemSettingException.INSTANCE.newInstance("当前id=" + ss.getId() + ",为不可编辑状态");
        }
        systemSettingMapper.updateState(id, enable);

        if (enable.trim().equals("00")) {
            removeItemToRedis(ss.getSetKey());
        } else {
            addStringItemToRedis(ss.getSetKey(), ss.getSetValue());
        }

    }

    /* 将系统变量数据load到redis*/
    public void loadToRedis() {

        List<SystemSettingEntity> settings = findAllWithoutPage();

        if (Objects.isNull(settings))
            throw SystemSettingException.PARAM_HINT_WARN.newInstance("系统变量为空！");

        //删除之前的缓存数据
        stringRedisTemplate.delete(RedisKey.TABLE_NAME);

        for (SystemSettingEntity setting : settings) {

            if (StringUtil.equals(setting.getEnable(), "00")) {
                removeItemToRedis(setting.getSetKey());
            } else {
                addStringItemToRedis(setting.getSetKey(), setting.getSetValue());
            }
        }
    }

    /*添加一条数据到redis*/
    public void addStringItemToRedis(String key, String value) {
        stringRedisTemplate.opsForHash().put(RedisKey.TABLE_NAME, key, value);
    }

    /*移除一条数据从redis*/
    public void removeItemToRedis(String key) {
        if (stringRedisTemplate.opsForHash().hasKey(RedisKey.TABLE_NAME, key)) {
            stringRedisTemplate.opsForHash().delete(RedisKey.TABLE_NAME, key);
        }
    }

    /*新增数据校验*/
    private void validateParams(SystemSettingEntity systemSetting) {
        if (StringUtils.isEmpty(systemSetting.getSid())) throw SystemSettingException.PARAM_IS_NULL.newInstance("系统编号");
        if (StringUtils.isEmpty(systemSetting.getSetKey()))
            throw SystemSettingException.PARAM_IS_NULL.newInstance("变量KEY");
        if (StringUtils.isEmpty(systemSetting.getSetValue()))
            throw SystemSettingException.PARAM_IS_NULL.newInstance("变量VALUE");
        if (StringUtils.isEmpty(systemSetting.getAa01())) throw SystemSettingException.PARAM_IS_NULL.newInstance("创建人");

        //key唯一性验证
        SystemSettingEntity ss = this.findByKey(systemSetting.getSetKey());
        if (!Objects.isNull(ss))
            throw SystemSettingException.PARAM_HINT_ERROR.newInstance("key[" + systemSetting.getSetKey() + "]已经存在！");
    }

}