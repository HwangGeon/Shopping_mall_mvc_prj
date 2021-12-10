package kr.co.shopping_mall.service.admin;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import kr.co.shopping_mall.dao.admin.OrderDAO;
import kr.co.shopping_mall.vo.OrderDetailVO;
import kr.co.shopping_mall.vo.OrderSearchVO;
import kr.co.shopping_mall.vo.OrderVO;
import kr.co.sist.util.cipher.DataDecrypt;

@Service
public class OrderService {

	@Autowired
	private OrderDAO od;

	public JSONObject countUserDash() {

		int countProcessing = od.selectOrderDashCnt("1");
		int countInDelivery = od.selectOrderDashCnt("2");
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("countProcessing", countProcessing);
		hm.put("countInDelivery", countInDelivery);

		return new JSONObject(hm);
	}

	public String getSearchOrderDashList(OrderSearchVO osVO) {
		
		int index = Integer.valueOf(osVO.getIndex());

		int rowsPerPage = 8; //페이지당 보여줄갯수
		//데이터시작점
		int start = (index-1) * rowsPerPage;

		List<OrderVO> orderList = od.selectOrderDashList(osVO.getFlag(), start, rowsPerPage);

		Gson gson = new Gson();
		
		return gson.toJson(orderList);
	}

	public String countSearchOrder(OrderSearchVO osVO) {

		int totalRows = od.selectOrderCnt(osVO);

		int rowsPerPage = 8; //페이지당 보여줄갯수
		int pageCount = (int)Math.ceil((double)totalRows/rowsPerPage);
		
		return String.valueOf(pageCount);
	}

	public String getSearchOrderList(OrderSearchVO osVO) {

		int index = Integer.valueOf(osVO.getIndex());
		int rowsPerPage = 8; //페이지당 보여줄갯수
		//데이터시작점
		int start = (index-1) * rowsPerPage;

		List<OrderVO> ordList = od.selectOrderList(osVO, start, rowsPerPage);

		Gson gson = new Gson();
		
		return gson.toJson(ordList);
	}

	public JSONObject updateOrder(OrderVO oVO) {
		
		String msg = oVO.getOrd_cd()+" 주문의 정보가 변경되었습니다.";
		
		int cnt = od.updateOrder(oVO);
		
		if(cnt == 0) {
			msg = oVO.getOrd_cd()+" 주문의 정보를 변경할 수 없습니다.";
		}
		
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("msg", msg);
		
		return new JSONObject(hm);
	}

	public OrderVO getOrderInfo(String ord_cd) {

		OrderVO oVO = od.selectOrderInfo(ord_cd);

		try {
			
			DataDecrypt dd = new DataDecrypt("AbcdEfgHiJkLmnOpQ");
			oVO.setDv_name(dd.decryption(oVO.getDv_name()));

		} catch (UnsupportedEncodingException uee) {
			// TODO Auto-generated catch block
			uee.printStackTrace();
		} catch (NoSuchAlgorithmException nsae) {
			// TODO Auto-generated catch block
			nsae.printStackTrace();
		} catch (GeneralSecurityException gse) {
			// TODO Auto-generated catch block
			gse.printStackTrace();
		}
		
		return oVO;
	}

	public List<OrderDetailVO> getOrderDetailInfo(String ord_cd) {
		
		List<OrderDetailVO> orderDetailList = od.getOrderDetailInfo(ord_cd);
		
		return orderDetailList;
	}
}
