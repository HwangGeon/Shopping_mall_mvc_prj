package kr.co.shopping_mall.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.shopping_mall.service.user.Cart_Service;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.sql.SQLException;

@Controller
public class Cart_Controller {
	@Autowired
	private Cart_Service cs;
	
	@RequestMapping(value="board/cart_proc.do", method=GET)
	public String getCartSession(HttpSession session, String pro_cd, int cnt) {
		try {
			session.setAttribute("cart", cs.addCartSession(session, pro_cd, cnt));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "board/cart_proc";
	}
}
