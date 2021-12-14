package kr.co.shopping_mall.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.shopping_mall.service.user.UserInfo_Service;
import kr.co.shopping_mall.vo.UserInfoVO;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class UserInfo_Controller {
	@Autowired
	private UserInfo_Service uis;
	
	@RequestMapping(value="user/myInfo.do", method=GET)
	public String userInfo(HttpSession session, Model model) {
		String id="";
		
		id=(String)session.getAttribute("user_id");
		
		try {
			UserInfoVO uiVO=uis.getUserInfo(id);
			model.addAttribute("userVO", uiVO);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		
		return "user/myInfo";
	}//userInfo
	
	@RequestMapping(value="user/passUpdateForm", method=GET)
	public String pwUpdateForm() {
		return "user/passUpdateForm";
	}//pwUpdateForm
	
	@RequestMapping(value="user/passUpdate_proc.do", method=POST)
	public String pwUpdateProc(String cur_pass, String new_pass, HttpSession session) {
		String id="";
		int flag=0;
		
		id=(String) session.getAttribute("user_id");
		
		try {
			flag=uis.doPwUpdate(cur_pass, new_pass, id);
			
			if(flag==1) {
				return "user/passUpdateCompl";
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "user/passUpdate_proc";
	}//pwUpdateProc
	
}//class
