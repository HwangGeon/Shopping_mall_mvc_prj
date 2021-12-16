package kr.co.shopping_mall.service.user;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.shopping_mall.dao.user.UserInfo_DAO;
import kr.co.shopping_mall.vo.UserInfoParamVO;
import kr.co.shopping_mall.vo.UserInfoVO;
import kr.co.sist.util.cipher.DataDecrypt;
import kr.co.sist.util.cipher.DataEncrypt;

@Service
public class UserInfo_Service {
	@Autowired
	private UserInfo_DAO uid;
	
	//개인정보 조회
	public UserInfoVO getUserInfo(String id) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException {
		UserInfoVO uiVO=uid.selectInfo(id);
		
		uiVO.setUser_name(decryptInputData(uiVO.getUser_name()));
		uiVO.setUser_email(decryptInputData(uiVO.getUser_email()));
		uiVO.setUser_tel(decryptInputData(uiVO.getUser_tel()));
		
		return uiVO;
	}//getUserInfo
	
	//비밀번호 변경
	public int doPwUpdate(String curPass, String newPass, String id) throws NoSuchAlgorithmException, SQLException {
		int flag=-1;
		String inputCurPass=null;
		String inputNewPass=null;
		inputCurPass=curPass;
		inputNewPass=newPass;
		
		UserInfoVO uv=uid.selectInfo(id);

		//현재 비밀번호 암호화
		String cur_pass=encryptInputPw(inputCurPass);
		//조회된 비밀번호
		String cur_pw=uv.getUser_pw();
		//입력된 새비밀번호 암호화
		String user_pw=encryptInputPw(inputNewPass);

		if(!cur_pass.equals(cur_pw)){
			return flag;
		}else {
			int cnt=uid.updatePass(id, user_pw); 
			flag=cnt;
		}//end else

		return flag;
	}//doPwUpdate
	
	//개인정보수정
	public int updateInfo(String user_id, String user_email, String user_addr) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException {
		user_email=encryptInputData(user_email);
		int cnt=uid.updateInfo(user_id, user_email, user_addr);
		
		return cnt;
	}//updateInfo
	
	//비밀번호 조회
	public String searchPw(String user_id, String user_pw) throws SQLException, NoSuchAlgorithmException {
		String flag="f";
		
		UserInfoVO uv=uid.selectInfo(user_id);

		//현재 비밀번호 암호화
		String encryptPw=encryptInputPw(user_pw);
		//조회된 비밀번호
		String searchPw=uv.getUser_pw();
		
		if(encryptPw.equals(searchPw)){
			flag="t";
		}//end if
		
		return flag;
	}//searchPw
	
	//비밀번호암호화
	public String encryptInputPw(String pw) throws NoSuchAlgorithmException {
		String encryptPw="";
		encryptPw=DataEncrypt.messageDigest("MD5", pw);
				
		return encryptPw;
	}//encryptInputPw
	
	//개인정보암호화(복호화가능)
	public String encryptInputData(String data) throws UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException {
		DataEncrypt de=new DataEncrypt("AbcdEfgHiJkLmnOpQ");
		String encryptData=de.encryption(data);
		
		return encryptData;
	}//encryptInputData
	
	//복호화
	public String decryptInputData(String data) throws UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException {
		DataDecrypt dd=new DataDecrypt("AbcdEfgHiJkLmnOpQ");
		String decryptData=dd.decryption(data);
		
		return decryptData;
	}//decryptInputData
	
}//class
