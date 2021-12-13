/*
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
	 * orders, delivery, order_detail ���̺� ���� �߰�
	 * @param dVO 
	 * @throws DataAccessException
	 */
	/*
	@SuppressWarnings("resource")
	@Transactional
	public void insertOrder(DeliveryVO dVO,ArrayList<ProductVO> cartList,String user_id) throws Exception {
		
	
		ArrayList<ProductVO> cart = cartList;
		
		try {
			
			String seq="select concat('O_',lpad(ord_cd_seq.nextval,10,0)) from dual";
			String prdCode="";
			prdCode=jt.queryForObject(seq, new RowMapper () {
				public String mapRow(ResultSet rs, int rowNum) throws SQLException{
					String prdCode="";
					prdCode=rs.getString(1);
					return prdCode;
				}
			});
			
			//2. �ֹ����̺� �߰�
			String insertOrders =
					"insert into orders(ord_cd, user_id, ord_price) values(?,?,?)"; 
			int total=0;
			int totalSum=0;
			for(int i = 0; i < cart.size(); i++) {
				ProductVO pv = cart.get(i);
					total = pv.getPro_price() * pv.getCnt();
					totalSum += total;
			}
			
			//���� ����
			int cnt=jt.update(insertOrders, seq, user_id, totalSum);
			
			//3. ������̺� �߰� 
			String insertDelivery = "insert into delivery(ord_cd, dv_name, dv_tel, dv_addr, dv_memo) values(?,?,?,?,?)";
			pstmt=con.prepareStatement(insertDelivery);
			//�Ű������� ���޵� �����͸� �������� ����ǥ�� �� ����
			pstmt.setString(1, prdCode);
			pstmt.setString(2, dVO.getDv_name());
			pstmt.setString(3, dVO.getDv_tel());
			pstmt.setString(4, dVO.getDv_addr());
			pstmt.setString(5, dVO.getDv_memo());
			//���� ����
			int cnt2=pstmt.executeUpdate();
			
			//4.������ ���� ����
			String insertOrderDetail = "insert into order_detail(ordd_cd,ord_cd, pro_cd, ordd_qty) values(concat('OD_',lpad(ordd_cd_seq.nextval,9,0)),?,?,?)";
			//ArrayList<ProductVO> cart=cartList;
			
			pstmt=con.prepareStatement(insertOrderDetail);
			//cartList�� ����ִ� ������ insert ����
			int check=0;
			for(int i=0 ; i < cart.size() ;i++) {
				ProductVO pv = cart.get(i);
				pstmt.setString(1, prdCode);
				pstmt.setString(2, pv.getPro_cd());
				pstmt.setInt(3, pv.getCnt());
				//check4+=1;
				check+=pstmt.executeUpdate();
			}
			//int cnt3=pstmt.executeUpdate();
			//����� ����� list�� size�� ��
			if(check!=cart.size()) {
				System.out.println("�����߻�1");
				con.rollback();
				throw new Exception();
			}
			//1,2,3������ ���� Ƚ���� ���Ͽ� commit �� �����ϰų� rollback�� ����
			if(!(cnt==cnt2&&cnt2==check/cart.size())) {
				System.out.println("�����߻�2");
				con.rollback();
				throw new Exception();
			}else {
				con.commit();
			}
		} finally {
		 try {
			 if(rs!=null) rs.close();
			 if(pstmt!=null) pstmt.close();
			 if(con!=null) con.close();
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
}//insertOrder
}
	 */
