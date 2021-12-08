package kr.co.shopping_mall.service.admin;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import kr.co.shopping_mall.dao.admin.HomeDAO;
import kr.co.shopping_mall.vo.AdminVO;
import kr.co.sist.util.cipher.DataEncrypt;

@Service
public class HomeService {
	
	@Autowired
	private HomeDAO hd;
	
	public String checkAccount(AdminVO aVO) {
		
		String admin_id = "";

		try {
			aVO.setAdmin_pw(DataEncrypt.messageDigest("MD5", aVO.getAdmin_pw()));
			admin_id = hd.selectId(aVO);
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
		
		return admin_id;
	}
	
	public JSONObject changePass(AdminVO aVO, String newPass, String nowPass) {
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		boolean flag = false;
		
		try {
			newPass = DataEncrypt.messageDigest("MD5", newPass);
			nowPass = DataEncrypt.messageDigest("MD5", nowPass); 
			
			aVO.setAdmin_pw(nowPass);
			hd.selectId(aVO);
			
			aVO.setAdmin_pw(newPass);
			if(hd.updatePass(aVO) != 0) {
				flag = true;
			}
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		} catch(DataAccessException dae) {
			dae.printStackTrace();
		}
		
		hm.put("flag", flag);
		
		return new JSONObject(hm);
	}

	public JSONObject countHomeDash() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMM01");
		String NowDate = sdf.format(new Date());
		String monDate = sdf1.format(new Date());
		
		int countPrice1 = 0, countPrice2 = 0;
		String countOrder1 = null, countOrder2 = null;
		
		try {
			countPrice1 = hd.countHomeDashPrice(NowDate,NowDate);
			countOrder1 = hd.countHomeDashOrder(NowDate,NowDate);
			
			countPrice2 = hd.countHomeDashPrice(monDate,NowDate);
			countOrder2 = hd.countHomeDashOrder(monDate,NowDate);
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("countPrice1", countPrice1);
		hm.put("countOrder1", countOrder1);
		hm.put("countPrice2", countPrice2);
		hm.put("countOrder2", countOrder2);
		
		return new JSONObject(hm);
	}

	public JSONObject countHomeDash(String date1, String date2) {
		
		int countPrice = 0;
		String countOrder = null;
		
		try {
			countPrice = hd.countHomeDashPrice(date1,date2);
			countOrder = hd.countHomeDashOrder(date1,date2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("countPrice", countPrice);
		hm.put("countOrder", countOrder);
		
		return new JSONObject(hm);
	}
}
