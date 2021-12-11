package kr.co.shopping_mall.controller.admin;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.shopping_mall.service.admin.OrderService;
import kr.co.shopping_mall.vo.OrderDetailVO;
import kr.co.shopping_mall.vo.OrderSearchVO;
import kr.co.shopping_mall.vo.OrderVO;

@Controller
@RequestMapping("admin/")
public class OrderController {
	
	@Autowired
	private OrderService os;
	
	@ResponseBody
	@RequestMapping(value = "orderDashInfo.do", method = POST )
	public String orderDashInfo() {
		
		JSONObject jo = os.countUserDash();
		
		return jo.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "searchOrderDash.do", method = POST, produces="text/plain;charset=UTF-8" )
	public String searchOrderDash(OrderSearchVO osVO) {
		
		String orderList = os.getSearchOrderDashList(osVO);
		
		return orderList;
	}
	
	@ResponseBody
	@RequestMapping(value = "orderPagenation.do", method = GET)
	public String orderPagenation(OrderSearchVO osVO) {
		
		String pageCnt = os.countSearchOrder(osVO);
		
		return pageCnt;
	}
	
	@ResponseBody
	@RequestMapping(value = "searchOrder.do", method = POST, produces="text/plain;charset=UTF-8")
	public String searchOrder(OrderSearchVO osVO) {
		
		String orderList = os.getSearchOrderList(osVO);
		
		return orderList;
	}
	
	@ResponseBody
	@RequestMapping(value = "updateOrderProc.do", method = POST, produces="text/plain;charset=UTF-8")
	public String updateOrderProc(OrderVO oVO) {

		JSONObject jo = os.updateOrder(oVO);
		
		return jo.toString();
	}

	@RequestMapping(value = "updateOrderForm.do", method = GET)
	public String updateOrderForm(String ord_cd, Model model) {
		
		OrderVO oVO = os.getOrderInfo(ord_cd);
		List<OrderDetailVO> odList = os.getOrderDetailInfo(ord_cd);
		
		model.addAttribute("oVO",oVO);
		model.addAttribute("odList",odList);
		
		return "admin/ad_order_updateForm";
	}

}
