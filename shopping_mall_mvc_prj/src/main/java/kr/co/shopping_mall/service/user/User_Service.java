package kr.co.shopping_mall.service.user;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import kr.co.shopping_mall.dao.user.User_DAO;
import kr.co.shopping_mall.vo.UserVO;
import kr.co.sist.util.cipher.DataEncrypt;

@Service
public class User_Service {

	@Autowired
	private User_DAO ud;
	
	//로그인
	public String checkAccount(String user_id, String user_pw) {
		
		String userId = "";

		try {
			user_pw=(DataEncrypt.messageDigest("MD5", user_pw));
			userId = ud.selectId(user_id, user_pw);
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
		return userId;
	}
	
	public void addMember(UserVO uVO) {
		
		try {
			uVO.setUser_pw(DataEncrypt.messageDigest("MD5", uVO.getUser_pw()));
			//복호화 가능한 : AES 암호화
			DataEncrypt de;
			de = new DataEncrypt("AbcdEfgHiJkLmnOpQ");
			uVO.setUser_name(de.encryption(uVO.getUser_name()));
			uVO.setUser_tel(de.encryption(uVO.getUser_tel()));  
			uVO.setUser_email(de.encryption(uVO.getUser_email())); 
			//DB 작업  
			ud.insertMember(uVO);//추가성공 예외
			} catch (UnsupportedEncodingException uee) {
				uee.printStackTrace();
			} catch (GeneralSecurityException gse) {
				gse.printStackTrace();
			} 
		
		}
	
		 public String idSearch(String user_id) {
			 String returnId="";
			 try {
				 returnId=ud.idSearch(user_id);
			 }catch(SQLException se){
				 se.printStackTrace();
			 }
			 return returnId;
		 }
		 
		 public int deleteMember(String user_id, String user_pw) throws SQLException {
			 int cnt=0;
			 try {
				user_pw=(DataEncrypt.messageDigest("MD5", user_pw));
				cnt=ud.updateDelFl(user_id, user_pw);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			 return cnt;
		 }
}//class
