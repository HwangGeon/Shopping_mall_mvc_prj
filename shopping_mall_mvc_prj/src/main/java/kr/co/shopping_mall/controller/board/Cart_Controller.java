package kr.co.shopping_mall.controller.board;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.shopping_mall.service.board.Cart_Service;
import kr.co.shopping_mall.service.user.Product_Service;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
	
	@RequestMapping(value="board/removeCart.do", method=POST)
	public String removeCart(HttpSession session, String pro_cd, Model model) {
		try {
			String msg=cs.removeCart(session, pro_cd);
			if(!("".equals(msg))){
			model.addAttribute("msg",msg);
			}
		} catch (SQLException e) {
			model.addAttribute("msg","Error");
			e.printStackTrace();
		}
		return "board/cart_list"; 		 	
	}//getCartSession
	
}
