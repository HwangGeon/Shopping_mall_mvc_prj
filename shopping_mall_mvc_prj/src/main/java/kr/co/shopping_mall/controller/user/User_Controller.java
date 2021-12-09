package kr.co.shopping_mall.controller.user;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.shopping_mall.service.user.User_Service;
import kr.co.shopping_mall.vo.UserVO;

@Controller
@RequestMapping("user/")
public class User_Controller {
	
	@Autowired
	private User_Service us;
	
	@RequestMapping(value = "login/loginForm.do", method = GET)
	public String userLoginForm() {
		return "user/user_login";
	}
	
	@RequestMapping(value = "login/userLogin.do", method = POST)
	public String userLogin(UserVO uVO, HttpSession session, Model model) {
		String user_id = us.checkAccount(uVO);

		if (!user_id.equals("")) {
			session.setAttribute("user_id", user_id);
			return "redirect:index.do";
		}

		model.addAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
		return "user/user_login";
	}
	
	@RequestMapping(value = "main/user_main.do", method = GET)
	public String mainPage() {
		return "index";
	}
	
	@RequestMapping(value = "login/userLogout.do", method = GET)
	public String userLogout(SessionStatus session, Model model) {
		((HttpSession) session).invalidate();
		return "index";
	}
	
}//class


