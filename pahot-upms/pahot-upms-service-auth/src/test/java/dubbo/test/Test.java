package dubbo.test;

import cn.pahot.upms.auth.entity.MenuHashKeyBean;
import cn.pahot.upms.auth.entity.RoleEntity;
import cn.pahot.upms.auth.entity.SystemConfEntity;
import cn.pahot.upms.auth.entity.UserEntity;
import cn.pahot.upms.auth.facade.UserAuthFacade;
import cn.pahot.xa.entity.MessageEntity;
import cn.pahot.xa.facade.CapXaFacade;
import com.alibaba.fastjson.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dubbo-submsg-consumer.xml")
public class Test {
	@Autowired
	private UserAuthFacade userAuthFacade;
	@Autowired
	private CapXaFacade capXaFacade;
	
	@org.junit.Test
	public void directSendMessage(){
		System.out.println("=======================================");
		MessageEntity me = new MessageEntity();
		me.setQueuename("aaaa");
		me.setContent("text");
		try {
			capXaFacade.directSendMessage(me);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@org.junit.Test
	public void findByUserList(){
		System.out.println("=======================================");
//		try {
//			PageInfo<UserEntity> pb = userAuthFacade.findByUserList(1,3);
//			System.out.println(pb.toString());
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	@org.junit.Test
	public void queryUserByUsername(){
		System.out.println("=======================================");
		UserEntity ue = userAuthFacade.queryUserByUsername("510100");
		String jo = JSONObject.toJSONString(ue);
		System.out.println(jo);
	}

	@org.junit.Test
	public void queryUserRoleByUID() {
		System.out.println("++++++++++++++++++++++++++++++++++++");
		List<RoleEntity> list = userAuthFacade.queryUserRoleByUID(100000);
		System.out.println(JSONObject.toJSONString(list));
	}
	
	@org.junit.Test
	public void queryUserMenulistByArights() {
		System.out.println("++++++++++++++++++++++++++++++++++++");
		List<SystemConfEntity> list = userAuthFacade.queryUserMenulistByArights(true,new BigInteger("1"));
		System.out.println(JSONObject.toJSONString(list));
	}
	
	@org.junit.Test
	public void getUserMenuHashKey() {
		System.out.println("++++++++++++++++++++++++++++++++++++");
		MenuHashKeyBean list = userAuthFacade.getUserMenuHashKey(new BigInteger("1"),new BigInteger("1"));
		System.out.println(JSONObject.toJSONString(list));
	}

	public static void main(String[] args) {

	}

}
