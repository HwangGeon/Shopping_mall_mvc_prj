package kr.co.shopping_mall.service.user;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.shopping_mall.dao.user.OrderInfo_DAO;
import kr.co.shopping_mall.vo.OrderInfoVO;

@Service
public class OrderInfo_Service {
	@Autowired
	private OrderInfo_DAO oid;
	
	//주문정보조회
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
	
}//class
