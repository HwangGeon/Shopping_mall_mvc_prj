package kr.co.shopping_mall.service.user;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.shopping_mall.dao.user.OrderInfo_DAO;
import kr.co.shopping_mall.dao.user.Order_DAO;
import kr.co.shopping_mall.vo.DeliveryVO;
import kr.co.shopping_mall.vo.OrderDetailVO;
import kr.co.shopping_mall.vo.OrderInfoVO;
import kr.co.shopping_mall.vo.ProductVO;
import kr.co.sist.util.cipher.DataDecrypt;

@Service
public class OrderInfo_Service {
	@Autowired
	private OrderInfo_DAO oid;
	@Autowired
	private Order_DAO od;
	
	public List<OrderInfoVO> getOrderList(String user_id) throws SQLException{
		List<OrderInfoVO> list=null;
		
		list=oid.selectOrder(user_id);
		
		//ord_cd가 같은게 존재하면 pro_name에 += pro_name 
		String cur_ord_cd="";
		if(list.size() > 0){
			cur_ord_cd=list.get(0).getOrd_cd();
		}
		int flagNum=0;
		int cnt=0;
		for(int i=1; i < list.size(); i++){
			if(cur_ord_cd.equals(list.get(i).getOrd_cd())){
				list.get(i).setOrd_cd("xxx");
				cnt+=1;
			}else{
				if(cnt != 0){
					list.get(flagNum).setPro_name(list.get(flagNum).getPro_name() + " 외 " + cnt + "건" );
				}//end if
				cur_ord_cd=list.get(i).getOrd_cd();
				flagNum=i;
				cnt=0;
			}//end else
			if(i==list.size()-1){
				if(cnt != 0){
					list.get(flagNum).setPro_name(list.get(flagNum).getPro_name() + " 외 " + cnt + "건" );
				}//end if
			}//end if
		}//end for 
		
		return list;
	}//getOrderList
	
	//주문취소로 변경
	public int changeOrderState(String[] chk) {
		int cnt=0;
		
		for(int i=0; i < chk.length; i++){
			oid.updateOrderInfo(chk[i]);
		}//end for
		
		return cnt;
	}//changeOrderState
	
	//주문상세정보 테이블 조회
	public List<OrderDetailVO> searchOrdDetail(String ord_cd) throws SQLException {
		List<OrderDetailVO> odList=od.selectOrdDetail(ord_cd);
		List<ProductVO> prdList=od.selectPrd(ord_cd);
		DecimalFormat fmt=new DecimalFormat("#,###,###,##0");
		int totalSum = 0, total = 0;
		for(OrderDetailVO ov : odList){
			for(ProductVO pv : prdList){
				/* for(int i=0;i<prdList.size();i++) { */
			total = pv.getPro_price() * ov.getOrdd_qty();
			ov.setPro_price_fmt(fmt.format(total));
		}//end for
			totalSum += total;
			ov.setPro_price_sum_fmt(fmt.format(totalSum));
		}
		return odList;
	}
	
	////주문 상세코드로 상품정보 조회
	public List<ProductVO> searchPrdDetail(String ord_cd) throws SQLException {
		List<ProductVO> prdList=od.selectPrd(ord_cd);
		return prdList;
	}
	/*
	 * //가격 조회 public int[] total(String ord_cd) throws SQLException {
	 * List<OrderDetailVO> odList=od.selectOrdDetail(ord_cd); List<ProductVO>
	 * prdList=od.selectPrd(ord_cd); odList.getorddQty(); }
	 */
	
	//배송정보 조회
	public DeliveryVO searchDVO(String ord_cd) throws SQLException {
		DeliveryVO dVO=null;
		try {
			DataDecrypt dd = new DataDecrypt("AbcdEfgHiJkLmnOpQ");
			dVO=od.selectDelivery(ord_cd);
			dVO.setDv_name(dd.decryption(dVO.getDv_name()));
			dVO.setDv_tel(dd.decryption(dVO.getDv_tel()));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (GeneralSecurityException e) {
				e.printStackTrace();
			}	
		return dVO;
		}
}//class
