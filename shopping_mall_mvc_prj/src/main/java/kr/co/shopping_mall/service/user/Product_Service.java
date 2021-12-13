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
	
	public List<ProductVO> getProdList(ProductParamVO paramVO) throws SQLException{
		List<ProductVO> list=null;
		DecimalFormat fmt=new DecimalFormat("#,###,###,##0원");
		ProductPagingVO ppVO=new ProductPagingVO();
		ppVO=setPaging(paramVO);
	
		if(paramVO.getSearchValue() ==null) {
			list=pd.selectPro(paramVO.getCategory_cd(), ppVO.getStart(), ppVO.getLen());
			for(ProductVO pv : list){
				pv.setPro_price_fmt(fmt.format(pv.getPro_price()));
			}//end for
		}else {
			list=pd.selectSearchPro(paramVO.getSearchValue(), ppVO.getStart(), ppVO.getLen());
			for(ProductVO pv : list){
				pv.setPro_price_fmt(fmt.format(pv.getPro_price()));
			}//end for
		}//end else
		
		return list;
	}//getProdList
	
	//전체데이터수
	public String getProdCount(ProductParamVO paramVO) throws SQLException {
		String cnt="";
		
		if(paramVO.getSearchValue() == null ) {
			cnt=pd.countPro(paramVO.getCategory_cd());
		}else {
			cnt=pd.countSearchPro(paramVO.getSearchValue());
		}//end else
		
		return cnt;	
	}//getProdCount
	
	//페이징
	public ProductPagingVO setPaging(ProductParamVO paramVO) throws SQLException {
		ProductPagingVO ppVO=new ProductPagingVO();
		
		String tempPage=paramVO.getPage();
		int cnt=0;
		int category_cd=0;
		String searchValue=null;
		if(paramVO.getSearchValue() == null || paramVO.getSearchValue().length() == 0){
			category_cd=paramVO.getCategory_cd();
			cnt=Integer.parseInt(pd.countPro(category_cd));//전체 데이터 개수
		}else{
				searchValue=paramVO.getSearchValue();
				cnt=Integer.parseInt(pd.countSearchPro(searchValue));
		}//end else

		int cPage=0;//현재 페이지

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

		int pageLength=3;
		int currentBlock=cPage % pageLength ==0?cPage/pageLength : (cPage/pageLength)+1;
		int startPage=(currentBlock-1)*pageLength+1;
		int endPage=startPage+pageLength-1;
		if(endPage>totalPages){
			endPage=totalPages;
		}//end if
		
		ppVO.setcPage(cPage);
		ppVO.setEndPage(endPage);
		ppVO.setLen(len);
		ppVO.setStart(start);
		ppVO.setStartPage(startPage);
		ppVO.setTotalPages(totalPages);
		
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
