package kr.co.shopping_mall.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.shopping_mall.service.user.OrderInfo_Service;
import kr.co.shopping_mall.vo.DeliveryVO;
import kr.co.shopping_mall.vo.OrderDetailVO;
import kr.co.shopping_mall.vo.OrderInfoVO;
import kr.co.shopping_mall.vo.ProductVO;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpSession;
@Controller
public class OrderInfo_Controller {
	@Autowired
	private OrderInfo_Service ois;
	
	@RequestMapping(value="user/myOrder.do", method=GET)
	public String orderList(HttpSession session, Model model) {
		String user_id=(String) session.getAttribute("user_id");
		List<OrderInfoVO> list=null;
		
		try {
			list=ois.getOrderList(user_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("dataCnt", list.size());
		model.addAttribute("orderData", list);
		return "user/myOrder";
	}//orderList
	
	@RequestMapping(value="user/myOrder_proc.do", method=POST)
	public String cancelOrder(String[] chk, Model model) {
		//체크된 항목 없으면
		if(chk == null) {
			return "user/myOrder_proc";
		}//end if
		
		model.addAttribute("chk", chk);
		//체크된 항목 주문취소로 변경
		ois.changeOrderState(chk);
		
		return "user/myOrder_proc";
	}//cancelOrder
	
	@RequestMapping(value = "user/order_detail.do", method = GET)
	public String OrderDetail(HttpSession session, String ord_cd, Model model) {
		List<OrderDetailVO> odList=null;
		List<ProductVO> prdList;
		DeliveryVO dVO;
		try {
			odList=ois.searchOrdDetail(ord_cd);
			dVO=ois.searchDVO(ord_cd);
			prdList=ois.searchPrdDetail(ord_cd);
			model.addAttribute("odList",odList);
			model.addAttribute("prdList",prdList);
			model.addAttribute("dVO",dVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "user/order_detail";
	}
}//class
