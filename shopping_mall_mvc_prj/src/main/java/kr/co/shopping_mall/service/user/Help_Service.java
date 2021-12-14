package kr.co.shopping_mall.service.user;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import kr.co.shopping_mall.dao.user.Help_DAO;
import kr.co.shopping_mall.vo.HelpParamVO;
import kr.co.sist.util.cipher.DataDecrypt;
import kr.co.sist.util.cipher.DataEncrypt;

@Service
public class Help_Service {
	@Autowired
	private Help_DAO hd;
	
	public String findId(HelpParamVO hpVO) throws EmptyResultDataAccessException, UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException{
		String id="";
		hpVO.setUser_name(encryptInputData(hpVO.getUser_name()));
		hpVO.setUser_email(encryptInputData(hpVO.getUser_email()));
		id=hd.searchFindId(hpVO.getUser_name(), hpVO.getUser_email());
		
		hpVO.setUser_name(decryptInputData(hpVO.getUser_name()));
		return id;
	}//findId
	
	public String findPw(HelpParamVO hpVO) throws UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException, SQLException {
		String tempPw="";
		String de_tempPw="";
		String id="";
		
		hpVO.setUser_name(encryptInputData(hpVO.getUser_name()));
		hpVO.setUser_email(encryptInputData(hpVO.getUser_email()));
		id=hd.searchFindPw(hpVO.getUser_name(), hpVO.getUser_email(), hpVO.getUser_id());
		
		//임시 비밀번호 발급, 해당 난수로 비밀번호 변경
		tempPw=getTempPass(8);
		de_tempPw=encryptInputPw(tempPw);
		int flag=hd.updatePass(id, de_tempPw);
		
		return tempPw;
	}//findPw
	
	public String getTempPass(int size) {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();

		String chars[] = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,0,1,2,3,4,5,6,7,8,9"
				.split(",");

		for (int i = 0; i < size; i++) {
			buffer.append(chars[random.nextInt(chars.length)]);
		}

		return buffer.toString();
	}//temporaryPassword
	
	//암호화
	public String encryptInputData(String data) throws UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException {
		DataEncrypt de=new DataEncrypt("AbcdEfgHiJkLmnOpQ");
		String encryptData=de.encryption(data);
		
		return encryptData;
	}//encryptInputData
	
	//비밀번호암호화
	public String encryptInputPw(String pw) throws NoSuchAlgorithmException {
		String encryptPw="";
		encryptPw=DataEncrypt.messageDigest("MD5", pw);
				
		return encryptPw;
	}//encryptInputPw
	
	//복호화
	public String decryptInputData(String data) throws UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException {
		DataDecrypt dd=new DataDecrypt("AbcdEfgHiJkLmnOpQ");
		String decryptData=dd.decryption(data);
		
		return decryptData;
	}//decryptInputData
	
}//class
