package kr.co.shopping_mall.service.user;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.shopping_mall.dao.user.Cart_DAO;
import kr.co.shopping_mall.vo.ProductVO;

@Service
public class Cart_Service {
	@Autowired
	private Cart_DAO cd;

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
		
		Object obj=session.getAttribute("cart");

		if(obj==null){
			cart=new ArrayList<ProductVO>();
		}else{
			cart=(ArrayList<ProductVO>)obj;
		}//end else
		return cart;
	}//addCartSession
}//class
