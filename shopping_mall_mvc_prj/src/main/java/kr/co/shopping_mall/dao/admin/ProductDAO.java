package kr.co.shopping_mall.dao.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.shopping_mall.vo.ProductSearchVO;
import kr.co.shopping_mall.vo.ProductVO;

@Repository
public class ProductDAO {

	@Autowired(required = false)
	private JdbcTemplate jt;
	
	//상품 RowMapper
	public class SelectPro implements RowMapper<ProductVO> {
		public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductVO pv = new ProductVO();
			pv.setNo(rs.getString("rnum"));
			pv.setPro_cd(rs.getString("pro_cd"));
			pv.setCategory_cd(rs.getInt("category_cd"));
			pv.setPro_name(rs.getString("pro_name"));
			pv.setPro_detail(rs.getString("pro_detail"));
			pv.setPro_img(rs.getString("pro_img"));
			pv.setPro_price(rs.getInt("pro_price"));
			pv.setSell_fl(rs.getString("sell_fl"));
			pv.setInput_date(rs.getString("input_date"));
			return pv;
		}
	}
	
	public void insertProduct(ProductVO pVO) throws DataAccessException  {
		StringBuilder insertProduct = new StringBuilder();
		insertProduct.append(" insert into product(pro_cd, category_cd, pro_name, pro_detail, pro_img, pro_price) ")
					 .append(" values(concat('P_',lpad(pro_cd_seq.nextval,10,'0')),?,?,?,?,?)" );
		jt.update(insertProduct.toString(), pVO.getCategory_cd(), pVO.getPro_name(), pVO.getPro_detail(), pVO.getPro_img(),
				pVO.getPro_price());
	}

	//페이징을 위한 데이터 레코드 수를 얻는 메소드
	public int countSearchPro(ProductSearchVO psVO) throws DataAccessException{
		int cnt = 0;
		
		StringBuilder countPro = new StringBuilder();
		countPro.append(" select count(pro_cd) from product ");
			if(psVO.getDivision().equals("1")) {
				countPro.append(" where pro_name ");
			} else {
				countPro.append(" where pro_cd ");	
			}
					
			countPro.append(" like '%' || ? || '%' ");
						
			if(!psVO.getCategory_cd().equals("0")) {
				countPro.append(" and category_cd=? ");
						
				cnt=jt.queryForObject(countPro.toString(), new Object[] {String.valueOf(psVO.getSearchValue()),String.valueOf(psVO.getCategory_cd())},Integer.class);
			} else {	
				cnt=jt.queryForObject(countPro.toString(), new Object[] {String.valueOf(psVO.getSearchValue())},Integer.class);
			}
		
		return cnt;
	}

	//상품 리스트 얻기
	public List<ProductVO> selectProductList(ProductSearchVO psVO, int start, int rowsPerPage) {
		List<ProductVO> list = null;

		StringBuilder selectPro = new StringBuilder();
		selectPro.append(" select * ") 
				 .append(" from (select rownum as rnum, p.* ")
		         		.append(" from ( select * ")
		         				.append(" from product ");
					if(psVO.getDivision().equals("1")) {
						selectPro.append("where pro_name");		 
					} else {
						selectPro.append("where pro_cd");
					}
						selectPro.append(" like '%' || ? || '%' ");
					if(!psVO.getCategory_cd().equals("0")) {
						selectPro.append(" and category_cd=? ");
					}
						selectPro.append(" order by input_date desc) p) ")
							 	 .append(" where rnum > ? and rnum <= ?+? ")	
							 	 .append(" order by rnum ");
		if (psVO.getCategory_cd().equals("0")) {
			// 코드가 0이면 전체분류의 상품을 조회
			list = jt.query(selectPro.toString(),new Object[] {String.valueOf(psVO.getSearchValue()), Long.valueOf(start), Long.valueOf(rowsPerPage), Long.valueOf(start) }, new SelectPro());
		} else {
			// 카테고리별 상품정보를 조회
			list = jt.query(selectPro.toString(), new Object[] {String.valueOf(psVO.getSearchValue()), String.valueOf(psVO.getCategory_cd()),Long.valueOf(start), Long.valueOf(rowsPerPage), Long.valueOf(start) },new SelectPro());
		} 
		return list;
	}

	public int countProDashData(String flag) {
		
		int cnt = 0;
		
		StringBuilder countPro = new StringBuilder();
		countPro.append(" select count(pro_cd) from product ");
			
		if(flag == "y" || flag == "n") {countPro.append(" where sell_fl=? ");}
						
		if(flag == "a") {
			cnt=jt.queryForObject(countPro.toString(),Integer.class);
		} else {	
			cnt=jt.queryForObject(countPro.toString(), new Object[] {String.valueOf(flag)},Integer.class);
		};
		
		return cnt;

	}

	public List<ProductVO> selectProductDashList(String flag, int start, int rowsPerPage) throws DataAccessException{
		
		List<ProductVO> list = null;

		StringBuilder selectPro = new StringBuilder();
		
		selectPro.append(" select * ");
		selectPro.append(" from	(select rownum as rnum, p.* ");
		selectPro.append("   	 from (select * from product ");
		if(!flag.equals("a")) {
			selectPro.append(" where sell_fl=? ");
		}
		selectPro.append(" order by input_date desc) p) ");  
		selectPro.append(" where  rnum > ? and rnum <= ?+? ");  
		selectPro.append(" order by rnum ");  
		
		if(!flag.equals("a")) {
			list = jt.query(selectPro.toString(),new Object[] {String.valueOf(flag), Long.valueOf(start), Long.valueOf(rowsPerPage), Long.valueOf(start) }, new SelectPro());
		} else {
			list = jt.query(selectPro.toString(),new Object[] {Long.valueOf(start), Long.valueOf(rowsPerPage), Long.valueOf(start) }, new SelectPro());				
		}

		return list;
	}

	public ProductVO selectProductInfo(String pro_cd) throws DataAccessException {
		ProductVO pv=null;

		String selectProInfo=" select rownum as rnum, p.* from product p where pro_cd=? ";
		pv=jt.queryForObject(selectProInfo, new Object[] { String.valueOf(pro_cd) }, new SelectPro());

		return pv;
	}

	public void updateProduct(ProductVO pVO) throws SQLException {

		String updatePro = " update product set pro_name=?, pro_price=?, sell_fl=?, input_date=sysdate where pro_cd=?";
		jt.update(updatePro, pVO.getPro_name(), pVO.getPro_price(), pVO.getSell_fl(), pVO.getPro_cd());

	}

	public void deleteProduct(ProductVO pVO) throws SQLException {
		String deletePro = " delete from product where pro_cd=? ";
		jt.update(deletePro, pVO.getPro_cd());
	}
}
