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
public class Product_DAO {
	@Autowired(required = false)
	private JdbcTemplate jt;
	
	///////////// inner class : 카테고리별 상품정보를 저장할 목적의 클래스 시작////////////
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
	
	//상품정렬을 위한 카테고리 코드, 페이징을 위한 시작데이터위치, 정렬개수를 매개변수로 받는 select
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
			//코드가 0이면 모든 상품정보를 조회
			list=jt.query(selectPro.toString(),new Object[] {Long.valueOf(start),Long.valueOf(len),Long.valueOf(start)},new SelectPro());
		}else {
			//카테고리별 상품정보를 조회
			list=jt.query(selectPro.toString(),new Object[] {Long.valueOf(start),Long.valueOf(len),String.valueOf(category_cd),Long.valueOf(start),String.valueOf(category_cd)},new SelectPro());		
		}//end else
		
		return list;
	}//selectDeptEmp
	
	//페이징을 위한 전체 데이터 레코드 수를 얻는 메소드(카테고리별)
	public String countPro(int category_cd) throws SQLException{
		String cnt=null;
		
		//3. 쿼리문 실행
		String count=null;
		
		if(category_cd==0) {
			count="select count(pro_cd) from product where sell_fl='y'";
			cnt=jt.queryForObject(count, String.class);
		}else {
			count="select count(pro_cd) from product where sell_fl='y' and category_cd=?";
			cnt=jt.queryForObject(count, new Object[] {String.valueOf(category_cd)},String.class);
		}//end else
		
		return cnt;
	}//countPro	
	
	//페이징을 위한 전체 데이터 레코드 수를 얻는 메소드(검색)
	public String countSearchPro(String searchValue) throws SQLException{
		String cnt=null;
		
		String count=null;
		
		count="select count(pro_cd) from product where sell_fl='y' and pro_name like '%' || ? || '%'";
		cnt=jt.queryForObject(count, new Object[] {String.valueOf(searchValue)},String.class);
		
		return cnt;
	}//countPro	
	
	
	//검색한 값을 포함한 데이터 정렬
	public List<ProductVO> selectSearchPro(String searchValue, int start, int len) throws SQLException{
		List<ProductVO> list=null;
		
		StringBuilder selectSearch=new StringBuilder();
		selectSearch.append("	select * 	")
		.append("	from(select ROWNUM AS RNUM, P.* 	")
		.append("	from product P	")
		.append("	where ROWNUM <=?+? and pro_name like '%' || ? || '%')	")
		.append("   where sell_fl='y'   ")
		.append("	and RNUM > ?");
		
		list=jt.query(selectSearch.toString(), 
				new Object[] {Long.valueOf(start),Long.valueOf(len),searchValue,Long.valueOf(start)},new SelectPro());

		return list;
	}//selectSearchPro
	
	//pro_cd에 해당하는 상품상세정보조회
	public ProductVO selectPro(String pro_cd) throws SQLException{
		ProductVO pv=null;
		
		String selectDetail="select * from product where pro_cd=?";
		pv=jt.queryForObject(selectDetail, new Object[] { String.valueOf(pro_cd) }, new SelectPro());
		
		return pv;
	}//selectPro
	
}//class
