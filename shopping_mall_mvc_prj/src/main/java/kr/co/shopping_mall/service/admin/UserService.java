package kr.co.shopping_mall.service.admin;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.shopping_mall.dao.admin.UserDAO;

@Service
public class UserService {
	
	@Autowired
	private UserDAO uDAO;

	public JSONObject countUserDash() {
		
		/*
		 * String countNewUser = uDAO.countUserDashData("n"); String countSecUser =
		 * uDAO.countUserDashData("y"); String countAllUser =
		 * uDAO.countUserDashData("a");
		 * 
		 * HashMap<String, Object> hm = new HashMap<String, Object>();
		 * hm.put("countNewUser", countNewUser); hm.put("countSecUser", countSecUser);
		 * hm.put("countAllUser", countAllUser);
		 */
		
		return new JSONObject(/* hm */);
	}

}
