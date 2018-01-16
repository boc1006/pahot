package cn.pahot.sample.dao;

import java.util.List;

import cn.pahot.sample.entity.TabEntity;
import com.github.pagehelper.Page;

import cn.pahot.sample.entity.UserEntity;

/**
 * Dao业务接口
 * <p>@Title: SampleMapper.java 
 * <p>@Package cn.pahot.sample.dao 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月1日 上午11:53:07 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public interface SampleMapper {

	void addUser(UserEntity ue);
	
	void updUser(UserEntity ue);
	
	List<UserEntity> findByUserList();
	
	Page<UserEntity> findByUserListPage();

	void insertA(TabEntity tab);
	void insertB(TabEntity tab);
	void insertC(TabEntity tab);

	void updateA(TabEntity tab);
	void updateB(TabEntity tab);
	void updateC(TabEntity tab);

	void deleteA(TabEntity tab);
	void deleteB(TabEntity tab);
	void deleteC(TabEntity tab);

	List<TabEntity> queryA(TabEntity tab);
	List<TabEntity> queryB(TabEntity tab);
	List<TabEntity> queryC(TabEntity tab);
}
