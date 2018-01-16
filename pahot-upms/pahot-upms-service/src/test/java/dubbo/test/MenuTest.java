package dubbo.test;

import cn.pahot.upms.entity.MenuEntity;
import cn.pahot.upms.entity.MenuRightsEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.pahot.upms.facade.MenuFacade;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dubbo-upms-consumer.xml")
public class MenuTest {
	@Autowired
	private MenuFacade menuFacade;

	@Test
	public void queryMenuById() throws Exception {
		MenuEntity menu = menuFacade.queryMenuById(1);
		Assert.assertNotNull(menu);
	}

	@Test
	public void queryMenuBySid() throws Exception {
		List<MenuEntity> menuList = menuFacade.queryMenuBySid("100000");
		Assert.assertTrue(menuList.size() > 0);
	}

	@Test
	public void saveMenu() throws Exception {
		MenuEntity menu = new MenuEntity();
		menu.setSid("100003");
		menu.setParentid(0);
		menu.setName("test1");
		menu.setMtype("01");
		menu.setBtype("01");
		menu.setIcon("123");
		menu.setJump("01");
		menu.setUri("http://www.baidu.com");
		menu.setSort(1);
		menu.setLevel("1");
		menu.setAa01(1);
		menu.setAb01(2);
		menuFacade.saveMenu(menu);
	}

	@Test
	public void updateMenu() throws Exception {
		MenuEntity menu = new MenuEntity();
		menu.setId(14);
		menu.setName("test3");
		menuFacade.updateMenu(menu);
	}

	@Test
	public void updateMenuState() throws Exception {
		menuFacade.updateMenuState(13, "02");
	}

	@Test
	public void queryMenuRightsById() throws Exception {
		MenuRightsEntity menuRights = menuFacade.queryMenuRightsById(1);
		Assert.assertNotNull(menuRights);
	}

	@Test
	public void queryMenuRightsByMid() throws Exception {
		List<MenuRightsEntity> list = menuFacade.queryMenuRightsByMid(2);
		Assert.assertTrue(list.size() > 0);
	}

	@Test
	public void saveMenuRights() throws Exception {
		MenuRightsEntity menuRights = new MenuRightsEntity();
		menuRights.setMid(11);
		menuRights.setSid("100000");
		menuRights.setCode("DELETE");
		menuRights.setName("删除");
		menuRights.setType("01");
		menuRights.setSort(02);
		menuRights.setAa01(1);
		menuFacade.saveMenuRights(menuRights);
	}

	@Test
	public void updateMenuRights() throws Exception {
		MenuRightsEntity menuRights = new MenuRightsEntity();
		menuRights.setId(15);
		menuRights.setName("删除1");
		menuFacade.updateMenuRights(menuRights);
	}

	@Test
	public void updateMenuRightsState() throws Exception {
		menuFacade.updateMenuRightsState(14, "02");
	}
}
