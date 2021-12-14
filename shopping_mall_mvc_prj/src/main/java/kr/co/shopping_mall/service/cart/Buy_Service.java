package kr.co.shopping_mall.service.cart;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.shopping_mall.dao.user.Order_DAO;
import kr.co.shopping_mall.dao.user.User_DAO;
import kr.co.shopping_mall.vo.DeliveryVO;
import kr.co.shopping_mall.vo.ProductVO;
import kr.co.shopping_mall.vo.UserInfoVO;


@Service
public class Buy_Service {
	@Autowired
	private Order_DAO od;
	private User_DAO ud;
	
	//회원조회
	public  UserInfoVO userSearch(String user_id) {
		UserInfoVO uv=null;
		try {
			ud.selectInfo(user_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uv;
	}//userSearch
	
	//주문추가
	public int buyCompl(DeliveryVO dVO, ArrayList<ProductVO> cartList, String user_id) throws Exception {
		int cnt=0;
		od.insertOrder(dVO, cartList, user_id);
		return cnt;
	}
	
	
}//class
