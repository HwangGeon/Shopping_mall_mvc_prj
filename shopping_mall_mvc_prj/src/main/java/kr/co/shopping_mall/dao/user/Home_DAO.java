package kr.co.shopping_mall.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.shopping_mall.vo.ProductVO;

@Repository
public class Home_DAO {
	@Autowired(required = false)
	private JdbcTemplate jt;
	
	///////////// inner class : ī�װ��� ��ǰ������ ������ ������ Ŭ���� ����////////////
	public class SelectPro implements RowMapper<ProductVO>{
		public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException{
			ProductVO pv=new ProductVO();
			pv.setPro_cd(rs.getString("pro_cd"));
			pv.setCategory_cd(rs.getInt("category_cd"));
			pv.setPro_name(rs.getString("pro_name"));
			pv.setPro_detail(rs.getString("pro_detail"));
			pv.setPro_img(rs.getString("pro_img"));
			pv.setPro_price(rs.getInt("pro_price"));
			pv.setSell_fl(rs.getString("sell_fl"));
			pv.setInput_date(rs.getString("input_date"));
			return pv;
		}//mapRow
	}//SelectPro	
	
	//��ǰ������ ���� ī�װ� �ڵ�, ����¡�� ���� ���۵�������ġ, ���İ����� �Ű������� �޴� select
	public List<ProductVO> selectPro(int category_cd, int start, int len) throws SQLException{
		List<ProductVO> list=null;
		
		StringBuilder selectPro=new StringBuilder();
		selectPro.append("select *	")
		.append("	from(select ROWNUM AS RNUM, P.*		")
		.append("	from product P	");
		if(category_cd == 0) {
			selectPro.append("	where ROWNUM <= ?+? and sell_fl='y')		");
		}else {
			selectPro.append("	where ROWNUM <= ?+? and P.category_cd=? and sell_fl='y')		");
		}//end else
		selectPro.append("	where RNUM > ?		")
		.append("	and sell_fl='y'	");
		
		//Dynamic query
		if(category_cd != 0) {
			selectPro.append("	and category_cd=?");
		}//end if
				   
		if(category_cd == 0) {
			//�ڵ尡 0�̸� ��� ��ǰ������ ��ȸ
			list=jt.query(selectPro.toString(),new Object[] {Long.valueOf(start),Long.valueOf(len),Long.valueOf(start)},new SelectPro());
		}else {
			//ī�װ��� ��ǰ������ ��ȸ
			list=jt.query(selectPro.toString(),new Object[] {Long.valueOf(start),Long.valueOf(len),String.valueOf(category_cd),Long.valueOf(start),String.valueOf(category_cd)},new SelectPro());		
		}//end else
		
		return list;
	}//selectPro
}
