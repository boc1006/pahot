package cn.pahot.member.dao;

import com.boc.common.metatype.DTO;

import cn.pahot.member.entity.AccountEntity;
import cn.pahot.member.entity.MemberWXBindEntity;

/**
 * 会员账号管理DAO接口定义
 * <p>@Title: MemberMapper.java 
 * <p>@Package cn.pahot.member.dao 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月18日 上午11:47:43 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public interface MemberMapper {
	
	/**
	 * 根据手机号码查询会员账号信息
	 * @param username
	 * @return
	 */
	AccountEntity getMemberByPhoneOrId(DTO<String,String> dto);
	
	/**
	 * 注册会员新用户,并返回会员ID
	 * @param entity
	 * @return 会员ID
	 */
	int registryMember(AccountEntity entity);
	
	/**
	 * 修改会员密码
	 * @param dto
	 */
	void changeMemberPasswd(DTO<String,String> dto);
	
	/**
	 * 根据会员编号或UNIONID查询会员与微信绑定信息
	 * @param dto
	 * @return
	 */
	MemberWXBindEntity getMemberWXBind(DTO<String,String> dto);
	
	/**
	 * 保存会员与微信的绑定信息
	 * @param entity
	 */
	void saveMemberWXBind(MemberWXBindEntity entity);
	
	/**
	 * 解除会员与微信的绑定关系
	 * @param id 会员与微信绑定ID
	 */
	void removeMemberWXBind(Long id);
}
