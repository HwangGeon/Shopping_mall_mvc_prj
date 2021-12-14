package kr.co.shopping_mall.service.user;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.shopping_mall.dao.user.UserInfo_DAO;
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
