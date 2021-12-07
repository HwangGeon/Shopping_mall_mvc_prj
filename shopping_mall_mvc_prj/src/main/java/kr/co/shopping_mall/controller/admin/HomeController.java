package kr.co.shopping_mall.controller.admin;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.shopping_mall.service.admin.HomeService;
import kr.co.shopping_mall.vo.AdminVO;

@Controller
@RequestMapping("admin/")
public class HomeController {

	@Autowired
	private HomeService hs;

	@RequestMapping(value = "loginForm.do", method = GET)
	public String adminLoginForm() {
		return "admin/ad_login";
	}

	@RequestMapping(value = "adminLogin.do", method = POST)
	public String adminLogin(AdminVO aVO, HttpSession session, Model model) {
		String admin_id = hs.checkAccount(aVO);

		if (!admin_id.equals("")) {
			session.setAttribute("admin_id", admin_id);
			return "redirect:ad_main.do";
		}

		model.addAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
		return "admin/ad_login";
	}

	@RequestMapping(value = "ad_main.do", method = GET)
	public String mainPage() {
		return "admin/ad_main";
	}
	
	@ResponseBody
	@RequestMapping(value = "passUpdateProc.do",method = POST)
	public boolean passUpdateProc(AdminVO aVO, String newPass, String nowPass, HttpSession session) {
		aVO.setAdmin_id((String)session.getAttribute("admin_id"));
		boolean flag = hs.changePass(aVO, newPass, nowPass);
		
		return flag;
	}
	
	@ResponseBody
	@RequestMapping(value = "homeDashInfo.do", method = POST)
	public String homeDashInfo() {
		JSONObject jo = hs.countHomeDash();
		return jo.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "homeDashPeriod.do", method = POST)
	public String homeDashPeriod(String date1, String date2) {
		JSONObject jo = hs.countHomeDash(date1,date2);
		return jo.toString();
	}
}
