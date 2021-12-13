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
		DecimalFormat fmt=new DecimalFormat("#,###,###,##0��");
		
		if(paramVO.getSearchValue()==null) {
			list=pd.selectPro(paramVO.getCategory_cd(), ppVO.getStartPage(), ppVO.getLen());
			for(ProductVO pVO : list) {
				pVO.setPro_price_fmt(fmt.format(pVO.getPro_price()));
			}//end for
			
		}else {
			//�˻�
			list=pd.selectSearchPro(paramVO.getSearchValue(), ppVO.getStartPage(), ppVO.getLen());
			for(ProductVO pVO : list) {
				pVO.setPro_price_fmt(fmt.format(pVO.getPro_price()));
			}//end for
		}//end else
		
		return list;
	}//getProdList
	
	
	
	//��ü�����ͼ�
	public String getProdListCount(int category_cd) throws SQLException {
		String cnt=null;
		
		cnt=pd.countPro(category_cd);
		
		return cnt;
	}//getProdListCount
	
	//��ü�����ͼ�(�˻�)
	public String getSearchProdListCount(String searchValue) throws SQLException {
		String cnt=null;
		
		cnt=pd.countSearchPro(searchValue);
		
		return cnt;
	}//getProdListCount
	
	
	//����¡
	@SuppressWarnings("null")
	public ProductPagingVO setPaging(ProductParamVO paramVO) throws NullPointerException, SQLException {
		ProductPagingVO ppVO=null;
		
		String tempPage=paramVO.getPage();
		int cnt=0;
		if(paramVO.getSearchValue() == null){
			cnt=Integer.parseInt(pd.countPro(paramVO.getCategory_cd()));//��ü ������ ����
		}else{
				cnt=Integer.parseInt(pd.countSearchPro(paramVO.getSearchValue()));
		}//end else
			

		int cPage;//���� ������

		//���� ������ ���ϱ�
		if(tempPage ==null || tempPage.length() == 0){
			cPage=1;
		}//end if
		try{
			cPage=Integer.parseInt(tempPage);
		}catch(NumberFormatException e){
			cPage=1;
		}//end catch

		//��ü ������ ����(���) ���ϱ�
		int totalRows=cnt;

		//�� ������ �� ���ϱ�
		int len=12; //�� �������� ������ �������� ��
		int totalPages=(totalRows%len==0?totalRows/len:(totalRows/len)+1);
		if(totalPages==0){
			totalPages=1;
		}//end if
		if(cPage>totalPages){
			cPage=1;
		}//end if

		//�� ������ ������ ������ ���ϱ�
		int start=(cPage-1)*len;

//		//�� �������� ���������� len���� ������ ��������
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
	
	//��ǰ ��
	public ProductVO getProdDetail(String pro_cd) throws SQLException {
		ProductVO pVO=null;
		DecimalFormat fmt=new DecimalFormat("#,###,###,##0��");
		
		pVO=pd.selectPro(pro_cd);
		pVO.setPro_price_fmt(fmt.format(pVO.getPro_price()));
		
		return pVO;
	}//getProdDetail

}//class
