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
			
		int pos= -1; //��ϵ� ��ǰ�� ����
		//��ٱ��� ���ǿ� ������ ��ǰ�� ���� ��� : cnt ����
		for(int i=0; i < cart.size(); i++){
			ProductVO pv=cart.get(i);
			if(pv.getPro_cd().equals(pro_cd)){
				pos=1;
				pv.setCnt(pv.getCnt()+cnt);
				break;
			}//end if
		}//end for

		//��ٱ��� ���ǿ� ������ ��ǰ�� ���� ��� : ProductVO ��ü �����Ͽ� �迭�� ���
		if(pos==-1){
			ProductVO pv=new ProductVO();
			//pro_cd�� ���� �ʿ��� ��ǰ ������ ��ȸ
			ProductVO selectPv=cd.selectPro(pro_cd);
			
			pv.setPro_cd(pro_cd); //��ǰ�ڵ�
			//�̹���, ��ǰ��, ����, ����
			pv.setPro_img(selectPv.getPro_img());
			pv.setPro_name(selectPv.getPro_name());
			pv.setCnt(cnt);
			pv.setPro_price(selectPv.getPro_price());
			//�迭�� ���
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
		//�ش� pro_cd ���� �̿��ؼ� ��ǰ �������� ������ �ڵ�
		ProductVO pv=pd.selectPro(pro_cd);
		if(pv==null){
			msg="��ٱ��Ͽ� ������ ��ǰ�� �����ϴ�.";
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
