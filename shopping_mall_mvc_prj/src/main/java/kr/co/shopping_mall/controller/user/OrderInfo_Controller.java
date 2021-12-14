package kr.co.shopping_mall.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.shopping_mall.service.user.OrderInfo_Service;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpSession;
@Controller
public class OrderInfo_Controller {
	@Autowired
	private OrderInfo_Service ois;
	
	@RequestMapping(value="user/myOrder.do", method=GET)
	public String orderList(HttpSession session, Model model) {
		return "user/myOrder";
	}
	
}//class
