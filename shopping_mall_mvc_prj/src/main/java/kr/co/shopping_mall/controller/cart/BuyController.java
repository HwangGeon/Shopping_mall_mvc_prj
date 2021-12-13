package kr.co.shopping_mall.controller.cart;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.shopping_mall.service.cart.Buy_Service;
import kr.co.shopping_mall.vo.DeliveryVO;
import kr.co.shopping_mall.vo.OrderVO;
import kr.co.shopping_mall.vo.ProductVO;

@Controller
@RequestMapping("board/")
public class BuyController {
	
	@Autowired
	private Buy_Service bs;
	
	@RequestMapping(value = "board/buyForm.do", method = GET)
	public String buyForm(HttpSession session, Model model) {
		return "board/buyForm";
	}
	
	@RequestMapping(value = "board/buy_proc.do", method = GET)
	public String buy_proc(int cnt, DeliveryVO dVO, OrderVO oVO, Model model) {
		return "board/buy_proc";
	}
	
	@RequestMapping(value = "board/buyCompl.do", method = GET)
	public String buyCompl() {
		return "board/buyCompl";
	}
	
	@RequestMapping(value = "board/buy_compl_proc.do", method = GET)
	public String userLoginForm(ProductVO pVO, Model model) {
		model.addAttribute("msg", "Error");
		return "board/buy_compl_proc";
	}
}
