package kr.co.shopping_mall.service.user;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.shopping_mall.dao.user.Product_DAO;
import kr.co.shopping_mall.vo.ProductPagingVO;
import kr.co.shopping_mall.vo.ProductParamVO;
import kr.co.shopping_mall.vo.ProductVO;

@Service
public class Product_Service {
	@Autowired
	private Product_DAO pd;
	
	public List<ProductVO> getProdList(ProductParamVO paramVO) throws NullPointerException, SQLException{
		List<ProductVO> list=null;
		ProductPagingVO ppVO=null;
		ppVO=setPaging(paramVO);
		DecimalFormat fmt=new DecimalFormat("#,###,###,##0원");
		
		if(paramVO.getSearchValue()==null) {
			list=pd.selectPro(paramVO.getCategory_cd(), ppVO.getStartPage(), ppVO.getLen());
			for(ProductVO pVO : list) {
				pVO.setPro_price_fmt(fmt.format(pVO.getPro_price()));
			}//end for
			
		}else {
			//검색
			list=pd.selectSearchPro(paramVO.getSearchValue(), ppVO.getStartPage(), ppVO.getLen());
			for(ProductVO pVO : list) {
				pVO.setPro_price_fmt(fmt.format(pVO.getPro_price()));
			}//end for
		}//end else
		
		return list;
	}//getProdList
	
	
	
	//전체데이터수
	public String getProdListCount(int category_cd) throws SQLException {
		String cnt=null;
		
		cnt=pd.countPro(category_cd);
		
		return cnt;
	}//getProdListCount
	
	//전체데이터수(검색)
	public String getSearchProdListCount(String searchValue) throws SQLException {
		String cnt=null;
		
		cnt=pd.countSearchPro(searchValue);
		
		return cnt;
	}//getProdListCount
	
	
	//페이징
	@SuppressWarnings("null")
	public ProductPagingVO setPaging(ProductParamVO paramVO) throws NullPointerException, SQLException {
		ProductPagingVO ppVO=null;
		
		String tempPage=paramVO.getPage();
		int cnt=0;
		if(paramVO.getSearchValue() == null){
			cnt=Integer.parseInt(pd.countPro(paramVO.getCategory_cd()));//전체 데이터 개수
		}else{
				cnt=Integer.parseInt(pd.countSearchPro(paramVO.getSearchValue()));
		}//end else
			

		int cPage;//현재 페이지

		//현재 페이지 정하기
		if(tempPage ==null || tempPage.length() == 0){
			cPage=1;
		}//end if
		try{
			cPage=Integer.parseInt(tempPage);
		}catch(NumberFormatException e){
			cPage=1;
		}//end catch

		//전체 데이터 개수(행수) 구하기
		int totalRows=cnt;

		//총 페이지 수 구하기
		int len=12; //한 페이지에 보여줄 데이터의 수
		int totalPages=(totalRows%len==0?totalRows/len:(totalRows/len)+1);
		if(totalPages==0){
			totalPages=1;
		}//end if
		if(cPage>totalPages){
			cPage=1;
		}//end if

		//각 페이지 데이터 시작점 구하기
		int start=(cPage-1)*len;

//		//각 페이지의 시작점으로 len개의 데이터 가져오기
//		List<ProductVO> list=null;
//		if(request.getParameter("category_cd") != null){
//			list=pd.selectPro(Integer.parseInt(category_cd), start, len);
//		}else{
//			list=pd.selectSearchPro(searchValue, start, len);
//		}//end else

		int pageLength=3;
		int currentBlock=
				cPage % pageLength ==0?cPage/pageLength : (cPage/pageLength)+1;
		int startPage=(currentBlock-1)*pageLength+1;
		int endPage=startPage+pageLength-1;
		if(endPage>totalPages){
			endPage=totalPages;
		}//end if
		
		ppVO.setcPage(cPage);
		ppVO.setEndPage(endPage);
		ppVO.setStartPage(startPage);
		ppVO.setTotalPages(totalPages);
		ppVO.setLen(len);
		
		return ppVO;
	}
	
	//상품 상세
	public ProductVO getProdDetail(String pro_cd) throws SQLException {
		ProductVO pVO=null;
		DecimalFormat fmt=new DecimalFormat("#,###,###,##0원");
		
		pVO=pd.selectPro(pro_cd);
		pVO.setPro_price_fmt(fmt.format(pVO.getPro_price()));
		
		return pVO;
	}//getProdDetail

}//class
