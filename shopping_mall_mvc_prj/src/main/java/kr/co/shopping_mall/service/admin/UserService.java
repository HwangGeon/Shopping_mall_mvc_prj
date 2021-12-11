package kr.co.shopping_mall.service.admin;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import kr.co.shopping_mall.dao.admin.UserDAO;
import kr.co.shopping_mall.vo.UserSearchVO;
import kr.co.shopping_mall.vo.UserVO;
import kr.co.sist.util.cipher.DataDecrypt;
import kr.co.sist.util.cipher.DataEncrypt;

@Service
public class UserService {

	@Autowired
	private UserDAO ud;

	public JSONObject countUserDash() {

		
		 int countNewUser = ud.selectUserDashCnt("n"); 
		 int countSecUser = ud.selectUserDashCnt("y"); 
		 int countAllUser = ud.selectUserDashCnt("a");
		 
		 HashMap<String, Object> hm = new HashMap<String, Object>();
		 hm.put("countNewUser", countNewUser); hm.put("countSecUser", countSecUser);
		 hm.put("countAllUser", countAllUser);
		

		return new JSONObject(hm);
	}

	public String getSearchUserDashList(UserSearchVO usVO) {
		
		int index = Integer.valueOf(usVO.getIndex());
		int rowsPerPage = 8; //페이지당 보여줄갯수
		//데이터시작점
		int start = (index-1) * rowsPerPage;

		List<UserVO> userList = ud.selectUserDashList(usVO.getFlag(), start, rowsPerPage);
		
		try {
			DataDecrypt dd = new DataDecrypt("AbcdEfgHiJkLmnOpQ");
			UserVO uVO = null;
			for (int i = 0; i < userList.size(); i++) {
				uVO = userList.get(i);
				try {	
					uVO.setUser_name(dd.decryption(uVO.getUser_name()));
					uVO.setUser_tel(dd.decryption(uVO.getUser_tel()));
					uVO.setUser_email(dd.decryption(uVO.getUser_email()));		
				} catch (NullPointerException npe) {
					uVO.setUser_name("삭제됨");
					uVO.setUser_tel("삭제됨");
					uVO.setUser_email("삭제됨");
				}
				userList.set(i, uVO);
			}
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		} catch (GeneralSecurityException gse) {
			gse.printStackTrace();
		}		

		Gson gson = new Gson();	
		return gson.toJson(userList);
	}

	public String countSearchUser(UserSearchVO usVO) {
		
		int totalRows = ud.selectUserCnt(usVO);
		int rowsPerPage = 8; //페이지당 보여줄갯수
		int pageCount = (int)Math.ceil((double)totalRows/rowsPerPage);
		
		return String.valueOf(pageCount);
	}

	public String getSearchUserList(UserSearchVO usVO) {
		int index = Integer.valueOf(usVO.getIndex());
		int rowsPerPage = 8; //페이지당 보여줄갯수
		//데이터시작점
		int start = (index-1) * rowsPerPage;
		
		List<UserVO> userList = null;
		UserVO uVO = null;	
		try {
			DataEncrypt de = new DataEncrypt("AbcdEfgHiJkLmnOpQ");
			DataDecrypt dd = new DataDecrypt("AbcdEfgHiJkLmnOpQ");

			if(usVO.getDivision().equals("2")){
				usVO.setSearchValue(de.encryption(usVO.getSearchValue()));
			}
			
			userList = ud.selectUserList(usVO, start, rowsPerPage);
			for (int i = 0; i < userList.size(); i++) {
				uVO = userList.get(i);
				try {	
					uVO.setUser_name(dd.decryption(uVO.getUser_name()));
					uVO.setUser_tel(dd.decryption(uVO.getUser_tel()));
					uVO.setUser_email(dd.decryption(uVO.getUser_email()));		
				} catch (NullPointerException e) {
					uVO.setUser_name("삭제됨");
					uVO.setUser_tel("삭제됨");
					uVO.setUser_email("삭제됨");
				}
				userList.set(i, uVO);
			}
			
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		} catch (NoSuchAlgorithmException nae) {
			nae.printStackTrace();
		} catch (GeneralSecurityException gse) {
			gse.printStackTrace();
		}
		
		Gson gson = new Gson();
		
		return gson.toJson(userList);
	}

	public UserVO getUserInfo(String user_id) {
		UserVO uVO = ud.selectUserInfo(user_id);

		try {	
			DataDecrypt dd = new DataDecrypt("AbcdEfgHiJkLmnOpQ");
			uVO.setUser_name(dd.decryption(uVO.getUser_name()));
			uVO.setUser_tel(dd.decryption(uVO.getUser_tel()));
			uVO.setUser_email(dd.decryption(uVO.getUser_email()));		
		} catch (NullPointerException npe) {
			uVO.setUser_name("삭제됨");
			uVO.setUser_tel("삭제됨");
			uVO.setUser_email("삭제됨");
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		} catch (GeneralSecurityException gse) {
			gse.printStackTrace();
		}
		
		return uVO;
	}

	public JSONObject updateUser(UserVO uVO, String work) {
		
		try {
			
			DataEncrypt de = new DataEncrypt("AbcdEfgHiJkLmnOpQ");
			
			uVO.setUser_name(de.encryption(uVO.getUser_name()));
			uVO.setUser_tel(de.encryption(uVO.getUser_tel()));
			uVO.setUser_email(de.encryption(uVO.getUser_email()));
			
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		} catch (GeneralSecurityException gsee) {
			gsee.printStackTrace();
		}
		
		
		String msg = uVO.getUser_id()+"님의 정보가 변경되었습니다.";
		int cnt = 0;
		
		if(work.equals("update")) {
			cnt = ud.updateUser(uVO);
		} 

		if(work.equals("secession")) {
			cnt = ud.secessionUser(uVO.getUser_id());
		}
		
		if(cnt == 0) {
			msg = uVO.getUser_id()+"님의 정보를 변경할 수 없습니다.";
		}
		
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("msg", msg);
		
		return new JSONObject(hm);
	}
}
