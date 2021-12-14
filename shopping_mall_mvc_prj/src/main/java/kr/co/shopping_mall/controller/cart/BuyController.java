package kr.co.shopping_mall.controller.cart;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.shopping_mall.service.cart.Buy_Service;
import kr.co.shopping_mall.vo.DeliveryVO;
import kr.co.shopping_mall.vo.OrderVO;
import kr.co.shopping_mall.vo.ProductVO;
import kr.co.shopping_mall.vo.UserInfoVO;

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
	public String buy_proc(HttpSession session, int cnt, DeliveryVO dVO, OrderVO oVO, Model model) {
		String user_id=(String)session.getAttribute("user_id");
		ArrayList<ProductVO> cart=null;
		Object obj=session.getAttribute("cart");
		if(obj==null){//세션정보가 없으면 배열을 생성
			cart = new ArrayList<ProductVO>();
		}else{ //세션정보가 있으면 강제로 캐스팅 
			cart=(ArrayList<ProductVO>)obj;
		}
		UserInfoVO uVO=bs.userSearch(user_id);
		try {
			bs.buyCompl(dVO, cart, user_id);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "error");
		}
		model.addAttribute("uVO", uVO);
		model.addAttribute("cart", cart);
		return "board/buyForm";
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
