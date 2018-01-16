package dubbo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.boc.common.metatype.DTO;
import com.boc.common.metatype.impl.SessionDTO;

import cn.pahot.member.entity.AccountEntity;
import cn.pahot.member.entity.MemberWXBindEntity;
import cn.pahot.member.enums.RegTypeEnum;
import cn.pahot.member.enums.TerminalEnum;
import cn.pahot.member.facade.MemberFacade;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dubbo-member-consumer.xml")
public class MemberTest {
    @Autowired
    private MemberFacade memberFacade;

    @Test
    public void bizRegistryMember() {
    	AccountEntity member = memberFacade.bizRegistryMember("13458585957", true, null, true, RegTypeEnum.REG_TYPE_03.key, TerminalEnum.TERMINAL_OTHER.key, "黄杰", "13458585957");
    	System.out.println("会员ID="+JSONObject.toJSONString(member));
    }

    @Test
    public void bindMemberWX() {
    	MemberWXBindEntity bindEntity = new MemberWXBindEntity();
    	bindEntity.setAid(10000000);
    	bindEntity.setOpenid("wx_openid_100100");
    	bindEntity.setUnionid("wx_union_100100");
    	memberFacade.bindMemberWX(bindEntity);
    }

    @Test
    public void registryMember() {
    	int memberId = memberFacade.registryMember("13458585959", "123456", "黄杰", "13800000000", TerminalEnum.TERMINAL_WAP.key);
    	System.out.println("会员ID="+memberId);
    }
    
    @Test
    public void changeMemberPasswd() {
    	DTO<String,String> dto = new SessionDTO<String,String>();
    	dto.put(DTO.SESSION_KEY_USERID, "100000");
    	dto.put("id", "10000001");
    	dto.put("oldPasswd", "123456");
    	dto.put("newPasswd", "abcdef");
    	memberFacade.changeMemberPasswd(dto);
    }
    
    @Test
    public void removeMemberWXBind() {
    	memberFacade.removeMemberWXBind(10000000, "wx_union_100100", 100000);
    }
}
