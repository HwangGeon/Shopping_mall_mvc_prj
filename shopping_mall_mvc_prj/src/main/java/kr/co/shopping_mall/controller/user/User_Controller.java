package kr.co.shopping_mall.controller.user;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
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
			return "redirect:http://localhost/shopping_mall/index.do";
		}

		model.addAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
		return "user/user_login";
	}
	
	@RequestMapping(value = "main/user_main.do", method = GET)
	public String mainPage() {
		return "redirect:http://localhost/shopping_mall/index.do";
	}
	
	@RequestMapping(value = "login/userLogout.do", method = GET)
	public String userLogout(HttpSession session) {
		session.removeAttribute("user_id");
		session.invalidate();
		return "redirect:http://localhost/shopping_mall/index.do";
	}
	
	@RequestMapping(value = "member/joinForm.do", method = GET)
	public String joinPage() {
		return "user/user_join";
	}
	
	  @RequestMapping(value = "member/userJoin.do", method = POST)
	  public String userJoin(@ModelAttribute @Valid  UserVO uVO, Model model) {
		 us.addMember(uVO);
		 return "user/joinCompl";
	  }
	 
	  @RequestMapping(value = "member/id_dup.do", method = GET)
		public String idDuplication() {
			return "user/id_dup";
		}
	  
	  @RequestMapping(value = "member/id_dup_proc.do", method = GET)
	  public void idDup_proc(String user_id, Model model) {
		  
	  }
	  
	  @RequestMapping(value = "member/deleteForm.do", method = GET)
	  public String userDeleteForm() {
		  return "user/deleteForm";
	  }
	  
		 
	
}//class


