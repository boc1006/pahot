package dubbo.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public void datalistPage(ClassPathXmlApplicationContext context) throws Exception{
		
//		TestFacade demoServer = (TestFacade) context.getBean("appTestFacade");
//		Page p = new Page();
//		DTO dto = new BaseDTO();
//		dto.put(Page.SHOWCOUNT, 2);
//		dto.put(Page.CURRENTPAGE, 1);
//		dto.put("username", "13458585957");
//		p.setPageDTO(dto);
//		BizPageResponse<List<DTO>> pr = demoServer.datalistPage(p);
//		System.out.println(JSONObject.fromObject(pr).toString());
		//{"page":{"currentPage":1,"currentResult":0,"dto":{"showCount":2,"currentPage":1},"entityOrField":true,"showCount":2,"totalPage":1,"totalResult":2},"result":[{"user_pwd":"7c4a8d09ca3762af61e59520943dc26494f8941b","pwd_error_time":{"date":30,"day":4,"hours":16,"minutes":11,"month":5,"nanos":0,"seconds":19,"time":1467274279000,"timezoneOffset":-480,"year":116},"remark":"超级管理员","user_type":"1","status":100,"last_login_time":{"date":30,"day":4,"hours":19,"minutes":22,"month":5,"nanos":0,"seconds":55,"time":1467285775000,"timezoneOffset":-480,"year":116},"main_user_id":0,"user_no":"admin","version":3,"id":1,"user_name":"超级管理员","create_time":{"date":30,"day":4,"hours":15,"minutes":0,"month":5,"nanos":0,"seconds":4,"time":1467270004000,"timezoneOffset":-480,"year":116},"pwd_error_count":0,"mobile_no":"13800138000","is_changed_pwd":0},{"id":2,"user_name":"test","user_pwd":"3d4f2bf07dc1be38b20cd6e46949a1071f9d0e3d","remark":"ff5r","status":100,"user_type":"2","create_time":{"date":30,"day":4,"hours":16,"minutes":12,"month":5,"nanos":0,"seconds":1,"time":1467274321000,"timezoneOffset":-480,"year":116},"user_no":"test","pwd_error_count":0,"is_changed_pwd":0,"mobile_no":"13455555555","version":0}]}
	}
//	
//	public void saveUser(ClassPathXmlApplicationContext context) {
//		try {
//			AppTestFacade demoServer = (AppTestFacade) context.getBean("appTestFacade");
//			Dto dto = new BaseDto();
//			dto.put("version", "1");
//			dto.put("user_no", "1");
//			dto.put("user_type", "1");
//			dto.put("user_pwd", "1");
//			dto.put("user_name", "name");
//			dto.put("status", "1");
//			System.out.println(demoServer.saveUser(dto));
//		} catch (Exception e) {
//			System.out.println("服务异常....");
//			e.printStackTrace();
//		}
//	}
//	
//	public void queryIndexForZC(ClassPathXmlApplicationContext context) {
//		AppIndexFacade demoServer = (AppIndexFacade) context.getBean("appIndexFacade");
//		System.out.println(JSONArray.fromObject(demoServer.queryIndexForZC()).toString());
//	}
//	
//	public void userLogin(ClassPathXmlApplicationContext context) {
//		AppUserFacade demoServer = (AppUserFacade) context.getBean("appUserFacade");
//		Dto dto = new BaseDto();
//		dto.put("username", "13458585958");
//		dto.put("password", "1472583692");
//		System.out.println(JSONArray.fromObject(demoServer.userLogin(dto)).toString());
//	}
	
	public void queryUserShop(ClassPathXmlApplicationContext context) {
//		AppShopFacade demoServer = (AppShopFacade) context.getBean("appShopFacade");
//		Dto dto = new BaseDto();
//		dto.put("userid", "1");
//		System.out.println(JSONArray.fromObject(demoServer.getUserShop(dto)).toString());
	}
//
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:spring/spring-context.xml" });
		context.start();
		//System.out.println("============================");
		try {
			new Test().datalistPage(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("============================");
//		new Test().saveUser(context);
//		new Test().queryIndexForZC(context);
//		new Test().userLogin(context);
//		new Test().queryUserShop(context);
	}
}
