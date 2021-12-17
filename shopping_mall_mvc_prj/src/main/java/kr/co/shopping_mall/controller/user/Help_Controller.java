package kr.co.shopping_mall.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.shopping_mall.service.user.Help_Service;
import kr.co.shopping_mall.vo.HelpParamVO;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@Controller
public class Help_Controller {
	@Autowired
	private Help_Service hs;
	
	@RequestMapping(value="help/idpwFind.do")
	public String idpwForm() {
		return "help/idpwFind";
	}//idpwForm
	
	@RequestMapping(value="help/idFind_result", method=POST)
	public String idFind(HelpParamVO hpVO, Model model) {
		String id="";
		
		try {
			try {
				id=hs.findId(hpVO);
				model.addAttribute("user_name", hpVO.getUser_name());
				model.addAttribute("id", id);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (GeneralSecurityException e) {
				e.printStackTrace();
			}
		}catch(EmptyResultDataAccessException e) {
			return "help/idpwFind_fail";
		}
		
		return "help/idFind_result";
	}//idFind
	
	@RequestMapping(value="help/pwFind_result", method=POST)
	public String pwFind(HelpParamVO hpVO, Model model) {
		String pw="";
		try {
		try {
			pw=hs.findPw(hpVO);
			model.addAttribute("user_pw", pw);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}catch(EmptyResultDataAccessException e) {
			return "help/idpwFind_fail";
		}
		
		return "help/pwFind_result";
	}//pwFind
	
}//class
