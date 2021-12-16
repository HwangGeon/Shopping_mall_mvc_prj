package kr.co.shopping_mall.controller.board;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.shopping_mall.service.board.Buy_Service;
import kr.co.shopping_mall.service.board.Cart_Service;
import kr.co.shopping_mall.vo.DeliveryVO;
import kr.co.shopping_mall.vo.OrderVO;
import kr.co.shopping_mall.vo.ProductVO;
import kr.co.shopping_mall.vo.UserInfoVO;
import kr.co.sist.util.cipher.DataDecrypt;

@Controller
@RequestMapping("board/") 
public class BuyController {
	
	@Autowired
	private Buy_Service bs;
	@Autowired
	private Cart_Service cs;
	
	@RequestMapping(value = "buyForm.do", method = POST)
	public String buyForm(HttpSession session, DeliveryVO dVO, Model model) {
		String user_id=(String)session.getAttribute("user_id");
		ArrayList<ProductVO> cart;
		UserInfoVO uVO;
		try {
			cart = cs.getCartSession(session);
			uVO = bs.userSearch(user_id);
			model.addAttribute("uVO", uVO);
			model.addAttribute("cart", cart);
			session.setAttribute("cart", cs.getCartSession(session));
		} catch (SQLException e2) {
			e2.printStackTrace();
			model.addAttribute("msg", "error");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "error");
		}

		return "board/buyForm";
	}
	
	@RequestMapping(value = "buy_proc.do", method = GET)
	public String buy_proc(HttpSession session, int cnt, DeliveryVO dVO, OrderVO oVO, Model model) {
		String user_id=(String)session.getAttribute("user_id");
		ArrayList<ProductVO> cart;
		UserInfoVO uVO;
		try {
			cart = cs.getCartSession(session);
			session.setAttribute("cart", cart);
			uVO = bs.userSearch(user_id);
			model.addAttribute("uVO", uVO);
			model.addAttribute("cart", cart);
			bs.buyCompl(dVO, cart, user_id);
		} catch (SQLException e2) {
			e2.printStackTrace();
			model.addAttribute("msg", "error");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "error");
		}

		return "board/buyForm";
	}

	
	
	@RequestMapping(value = "buyCompl.do", method = POST)
	public String buyCompl(HttpSession session,String dv_name, String dv_tel, String dv_addr, String dv_memo, Model model) {
		String user_id=(String)session.getAttribute("user_id");
		ArrayList<ProductVO> cartList;
		DeliveryVO dVO;
		String ordCd="";
		try {
			cartList = cs.getCartSession(session);
			session.setAttribute("cart", cartList);
			dVO =bs.addDeliveryVO(dv_name, dv_tel, dv_addr, dv_memo);
			bs.buyCompl(dVO, cartList, user_id);
			dVO.setDv_name(dv_name);
			dVO.setDv_tel(dv_tel);
			model.addAttribute("dVO", dVO);
			ordCd=bs.searchOrdcd(user_id);
			model.addAttribute("ordCd", ordCd);
			session.removeAttribute("cart");
		} catch (SQLException se) {
			se.printStackTrace();
			model.addAttribute("msg", "Error");
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "Error");
		}
		return "board/buyCompl";
	}

}//class
