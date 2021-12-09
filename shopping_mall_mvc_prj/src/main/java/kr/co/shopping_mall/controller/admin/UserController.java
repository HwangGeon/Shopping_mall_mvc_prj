package kr.co.shopping_mall.controller.admin;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.shopping_mall.service.admin.UserService;
import kr.co.shopping_mall.vo.UserSearchVO;
import kr.co.shopping_mall.vo.UserVO;

@Controller
@RequestMapping("admin/")
public class UserController {
	
	@Autowired
	private UserService us;
	
	@ResponseBody
	@RequestMapping(value = "userDashInfo.do", method = POST)
	public String userDashInfo() {
		
		JSONObject jo = us.countUserDash();
		
		return jo.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "searchUserDash.do", method = POST, produces="text/plain;charset=UTF-8")
	public String searchUserDash(UserSearchVO usVO) {
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "userPagenation.do", method = GET)
	public String userPagenation(UserSearchVO usVO) {
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "searchUser.do", method = POST, produces="text/plain;charset=UTF-8")
	public String searchUser(UserSearchVO usVO) {
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "updateUserProc.do", method = POST, produces="text/plain;charset=UTF-8")
	public String updateUserProc(UserVO uVO, String work) {
		return "";
	}
	
	@RequestMapping(value = "updateUserForm.do",method = GET)
	public String updateUserForm(String user_id, Model model) {
		return "";
	}
	
}
