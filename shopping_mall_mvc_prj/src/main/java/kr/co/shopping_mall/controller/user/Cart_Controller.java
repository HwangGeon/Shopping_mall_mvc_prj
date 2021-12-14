package kr.co.shopping_mall.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.shopping_mall.service.user.Cart_Service;
import kr.co.shopping_mall.service.user.Product_Service;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.sql.SQLException;

@Controller
public class Cart_Controller {
	@Autowired
	private Cart_Service cs;
	@Autowired
	private Product_Service ps;
	
	@RequestMapping(value="board/cart_proc.do", method=GET)
	public String getCartSession(HttpSession session, String pro_cd, int cnt, Model model) {
		try {
			session.setAttribute("cart", cs.addCartSession(session, pro_cd, cnt));
			model.addAttribute("msg","장바구니에 담았습니다."); 
			model.addAttribute("proData", ps.getProdDetail(pro_cd));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "board/prod_detail"; 		 	
	}//getCartSession
	
	@RequestMapping(value="board/cart_list.do", method=GET)
	public String cart_list(HttpSession session, String pro_cd, Model model) {
		model.addAttribute("pro_cd",pro_cd);
		try {
			session.setAttribute("cart", cs.getCartSession(session));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "board/cart_list";
	}//getCartSession
	
}
