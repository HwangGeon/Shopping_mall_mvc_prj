package kr.co.shopping_mall.service.user;

import java.security.NoSuchAlgorithmException;

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
	
	public String checkAccount(UserVO uVO) {
		
		String user_id = "";

		try {
			uVO.setUser_pw(DataEncrypt.messageDigest("MD5", uVO.getUser_pw()));
			user_id = ud.selectId(uVO);
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
		return user_id;
	}
}
