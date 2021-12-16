package kr.co.shopping_mall.service.board;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.shopping_mall.dao.user.Cart_DAO;
import kr.co.shopping_mall.dao.user.Product_DAO;
import kr.co.shopping_mall.vo.ProductVO;

@Service
public class Cart_Service {
	@Autowired
	private Cart_DAO cd;
	@Autowired
	private Product_DAO pd;

	public ArrayList<ProductVO> addCartSession(HttpSession session, String pro_cd, int cnt) throws SQLException{
		ArrayList<ProductVO> cart=null;
		
		Object obj=session.getAttribute("cart");

		if(obj==null){
			cart=new ArrayList<ProductVO>();
		}else{
			cart=(ArrayList<ProductVO>)obj;
		}//end else
			
		int pos= -1; //등록된 제품이 없다
		//장바구니 세션에 동일한 제품이 있을 경우 : cnt 증가
		for(int i=0; i < cart.size(); i++){
			ProductVO pv=cart.get(i);
			if(pv.getPro_cd().equals(pro_cd)){
				pos=1;
				pv.setCnt(pv.getCnt()+cnt);
				break;
			}//end if
		}//end for

		//장바구니 세션에 동일한 제품이 없을 경우 : ProductVO 객체 생성하여 배열에 등록
		if(pos==-1){
			ProductVO pv=new ProductVO();
			//pro_cd를 통해 필요한 상품 데이터 조회
			ProductVO selectPv=cd.selectPro(pro_cd);
			
			pv.setPro_cd(pro_cd); //상품코드
			//이미지, 상품명, 수량, 가격
			pv.setPro_img(selectPv.getPro_img());
			pv.setPro_name(selectPv.getPro_name());
			pv.setCnt(cnt);
			pv.setPro_price(selectPv.getPro_price());
			//배열에 등록
			cart.add(pv);
		}//end if
		
		return cart;
	}
	
	public ArrayList<ProductVO> getCartSession(HttpSession session) throws SQLException{
		ArrayList<ProductVO> cart=null;
		DecimalFormat fmt=new DecimalFormat("#,###,###,##0");
		
		Object obj=session.getAttribute("cart");
		int totalSum = 0, total = 0;
		if(obj==null){
			cart=new ArrayList<ProductVO>();
		}else{
			cart=(ArrayList<ProductVO>)obj;
		}//end else
		for(ProductVO pv : cart){
			total = pv.getPro_price() * pv.getCnt();
			totalSum += total;
			pv.setPro_price_fmt(fmt.format(total));
			pv.setPro_price_sum_fmt(fmt.format(totalSum));
		}//end for
		return cart;
	}//addCartSession
	
	public String removeCart(HttpSession session, String pro_cd) throws SQLException {
		String msg="";
		if(pro_cd==null){
			return msg;
		}
		//해당 pro_cd 값을 이용해서 상품 상세정보를 얻어오는 코드
		ProductVO pv=pd.selectPro(pro_cd);
		if(pv==null){
			msg="장바구니에 삭제할 상품이 없습니다.";
			return msg;
		}
		ArrayList<ProductVO> cart=(ArrayList<ProductVO>)session.getAttribute("cart");
		ProductVO goodQnt = new ProductVO();
		for(int i=0;i<cart.size();i++){
			goodQnt=cart.get(i);
			if(goodQnt.getPro_cd().equals(pro_cd)){
				cart.remove(goodQnt);
			}
		}

	return msg;
	}
}//class
