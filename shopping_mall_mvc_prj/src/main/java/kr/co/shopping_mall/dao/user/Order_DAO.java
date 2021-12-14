
package kr.co.shopping_mall.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.co.shopping_mall.vo.DeliveryVO;
import kr.co.shopping_mall.vo.ProductVO;

@Repository
public class Order_DAO {
	
	@Autowired(required = false)
	private JdbcTemplate jt;
	
	/**
	 * orders, delivery, order_detail 테이블에 정보 추가
	 * @param dVO 
	 * @throws DataAccessException
	 */
	
	@Transactional(rollbackFor=Exception.class)
	public void insertOrder(DeliveryVO dVO,ArrayList<ProductVO> cartList,String user_id) throws Exception {
		
	
		ArrayList<ProductVO> cart = cartList;
		
		try {
			
			String seq="select concat('O_',lpad(ord_cd_seq.nextval,10,0)) from dual";
			String prdCode="";
			prdCode=jt.queryForObject(seq, new Object[] {}, String.class);
			
			
			//2. 주문테이블에 추가
			String insertOrders =
					"insert into orders(ord_cd, user_id, ord_price) values(?,?,?)"; 
			int total=0;
			int totalSum=0;
			for(int i = 0; i < cart.size(); i++) {
				ProductVO pv = cart.get(i);
					total = pv.getPro_price() * pv.getCnt();
					totalSum += total;
			}
			
			//쿼리 수행
			int cnt=jt.update(insertOrders, prdCode, user_id, totalSum);
			
			//3. 배송테이블에 추가 
			String insertDelivery = "insert into delivery(ord_cd, dv_name, dv_tel, dv_addr, dv_memo) values(?,?,?,?,?)";
			int cnt2=jt.update(insertDelivery.toString(), prdCode, dVO.getDv_name(), dVO.getDv_tel(), dVO.getDv_addr(), dVO.getDv_memo());
		
			
			//4.수행할 쿼리 정의
			String insertOrderDetail = "insert into order_detail(ordd_cd,ord_cd, pro_cd, ordd_qty) values(concat('OD_',lpad(ordd_cd_seq.nextval,9,0)),?,?,?)";
			
			//cartList에 들어있는 내용을 insert 수행
			int check=0;
			for(int i=0 ; i < cart.size() ;i++) {
				ProductVO pv = cart.get(i);
				check+=jt.update(insertOrderDetail.toString(), prdCode, pv.getPro_cd(), pv.getCnt());
			}
			//수행된 행수를 list의 size와 비교
			if(check!=cart.size()) {
				System.out.println("문제발생1");
				throw new Exception();
			}
			//1,2,3쿼리의 수행 횟수를 비교하여 commit 을 수행하거나 rollback을 수행
			if(!(cnt==cnt2&&cnt2==check/cart.size())) {
				System.out.println("문제발생2");
				throw new Exception();
			}
		}catch(Exception e){
				e.printStackTrace();
		}
	}//insertOrder
}//class
	
