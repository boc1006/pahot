package cn.pahot.upms.controller;

import com.alibaba.fastjson.JSONObject;
import com.boc.annotation.AccessLog;
import com.boc.common.web.WebResponse;
import com.boc.common.web.cookie.CookieTools;
import com.boc.common.web.permissions.SecurityUtils;
import com.boc.common.web.permissions.Subject;
import com.boc.common.web.springmvc.BaseController;
import com.boc.common.web.utils.PasswordUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/")
public class LoginController extends BaseController {
	private static final Logger logger = Logger.getLogger(LoginController.class);
	/**
	 * 登录方法
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws Exception
	 */
	@AccessLog
	@ResponseBody
	@RequestMapping(value = "/doLogin.ak",method=RequestMethod.POST)
	public WebResponse login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
		String username = httpServletRequest.getParameter("username");
		String password = httpServletRequest.getParameter("password");
		SecurityUtils.getWebSecurityManager().login(username, PasswordUtils.encrypt(password),-1);
		WebResponse wr = new WebResponse();
		wr.setAjaxMsg(true, "", "./index.sec", "");
		return wr;
	}
	
	@AccessLog
	@RequestMapping(value = "/index.sec",method=RequestMethod.GET)
	public String index(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,Model model){
		model.addAttribute("username",  SecurityUtils.getSubject().getUsername());
		Subject subject = SecurityUtils.getSubject();
		Object o = subject.getSession().getAttribute("menu");
        String jsonStr = JSONObject.toJSONString(o);
        model.addAttribute("menu", jsonStr);
        model.addAttribute("menuOnTree", SecurityUtils.getSubject().getSession().getAttribute("menu"));
        model.addAttribute("userId",getUserId());
        if(subject.isSuperAdmin()){
			setCookie("isSupper","1");
		}else{
			setCookie("isSupper","0");
		}
        return "main/index";
	}
	
	/**
	 * 登录页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login.ak",method=RequestMethod.GET)
	public ModelAndView toLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/login");
		return mv;
	}

	/**
	 * 退出登录
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/logout.sec",method=RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest , HttpServletResponse httpServletResponse) throws Exception {
		SecurityUtils.getWebSecurityManager().logout();
		return "redirect:/";
    }
}
