package kr.co.shopping_mall.service.board;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.shopping_mall.dao.user.Cart_DAO;
import kr.co.shopping_mall.dao.user.Order_DAO;
import kr.co.shopping_mall.dao.user.User_DAO;
import kr.co.shopping_mall.vo.DeliveryVO;
import kr.co.shopping_mall.vo.ProductVO;
import kr.co.shopping_mall.vo.UserInfoVO;
import kr.co.sist.util.cipher.DataDecrypt;
import kr.co.sist.util.cipher.DataEncrypt;


@Service
public class Buy_Service {
	@Autowired
	private Order_DAO od;
	@Autowired
	private User_DAO ud;
	
	//회원조회
	public  UserInfoVO userSearch(String user_id) throws SQLException {
		UserInfoVO uv=ud.selectInfo(user_id);
		try {
			DataDecrypt dd=new DataDecrypt("AbcdEfgHiJkLmnOpQ");
			uv.setUser_name(dd.decryption(uv.getUser_name()));
			uv.setUser_tel(dd.decryption(uv.getUser_tel()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		return uv;
	}//userSearch
	
	//주문추가
	public void buyCompl(DeliveryVO dVO, ArrayList<ProductVO> cartList, String user_id) throws Exception {
		DataEncrypt de;
		 de = new DataEncrypt("AbcdEfgHiJkLmnOpQ");
		 dVO.setDv_name(de.encryption(dVO.getDv_name()));
		 dVO.setDv_tel(de.encryption(dVO.getDv_tel()));
		od.insertOrder(dVO, cartList, user_id);
		
	}

	public DeliveryVO addDeliveryVO( String dv_name, String dv_tel, String dv_addr, String dv_memo) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException{
		
		DeliveryVO dVO=new DeliveryVO();
		dVO.setDv_name(dv_name);
		dVO.setDv_tel(dv_tel);
		dVO.setDv_addr(dv_addr);
		dVO.setDv_memo(dv_memo);
		
		return dVO;
	}
	
	public String searchOrdcd(String user_id) throws SQLException {
		String ordCd=od.selectOrdCd(user_id);
		return ordCd;
	}
	
}//class
