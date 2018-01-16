package cn.pahot.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boc.common.core.test.annotation.DubboProviderMethod;
import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;

import cn.pahot.member.biz.MemberBiz;
import cn.pahot.member.entity.AccountEntity;
import cn.pahot.member.entity.MemberWXBindEntity;
import cn.pahot.member.facade.MemberFacade;

@Service("memberFacade")
@DubboProviderMethod(ip="192.168.3.113",name="黄杰",systemNum="100004",system="会员管理子系统")
public class MemberService implements MemberFacade {
	
	@Autowired
	private MemberBiz memberBiz;

	@Override
	public AccountEntity bizRegistryMember(String phone, boolean isDefPwd, String passwd, boolean isSendSms, String regType,
			String terminal,String name,String tel) {
		return memberBiz.bizRegistryMember(phone, isDefPwd, passwd, isSendSms, regType, terminal,name,tel);
	}

	@Override
	public void bindMemberWX(MemberWXBindEntity entity) {
		memberBiz.bindMemberWX(entity);
	}

	@Override
	public int registryMember(String phone, String passwd, String name, String tel, String terminal)
			throws BizException {
		return memberBiz.registryMember(phone, passwd, name, tel, terminal);
	}

	@Override
	public void changeMemberPasswd(DTO<String, String> dto) throws BizException {
		memberBiz.changeMemberPasswd(dto);
	}

	@Override
	public void removeMemberWXBind(int memberId, String unionId,int aa01) throws BizException {
		memberBiz.removeMemberWXBind(memberId, unionId,aa01);
	}

}
