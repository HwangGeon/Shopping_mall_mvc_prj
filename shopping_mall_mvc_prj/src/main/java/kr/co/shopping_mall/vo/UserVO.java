package kr.co.shopping_mall.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class UserVO {
	
	@NotEmpty
	@Length(min=5, max=12)
	//@Pattern()
	private String user_id;
	
	@NotEmpty
	private String user_pw;
	
	@NotEmpty
	private String user_name;
	
	@NotEmpty
	private String user_tel;
	
	@NotEmpty
	@Email
	private String user_email;
	
	@NotEmpty
	private String user_addr;
	
	private String grade_no;
	private String grade_name;
	private String reg_date;
	private String del_fl;
	private String sec_date;
	private String no;
	
	public UserVO() {
	}
	
	public UserVO(String user_id, String user_pw, String user_name, String user_tel, String user_email,
			String user_addr, String grade_no, String grade_name, String reg_date, String del_fl, String sec_date,
			String no) {
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.user_tel = user_tel;
		this.user_email = user_email;
		this.user_addr = user_addr;
		this.grade_no = grade_no;
		this.grade_name = grade_name;
		this.reg_date = reg_date;
		this.del_fl = del_fl;
		this.sec_date = sec_date;
		this.no = no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_tel() {
		return user_tel;
	}

	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_addr() {
		return user_addr;
	}

	public void setUser_addr(String user_addr) {
		this.user_addr = user_addr;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getDel_fl() {
		return del_fl;
	}

	public void setDel_fl(String del_fl) {
		this.del_fl = del_fl;
	}

	public String getSec_date() {
		return sec_date;
	}

	public void setSec_date(String sec_date) {
		this.sec_date = sec_date;
	}

	public String toString() {
		return "UserVO [user_id=" + user_id + ", user_pw=" + user_pw + ", user_name=" + user_name + ", user_tel="
				+ user_tel + ", user_email=" + user_email + ", user_addr=" + user_addr + ", grade_name=" + grade_name
				+ ", reg_date=" + reg_date + ", del_fl=" + del_fl + ", sec_date=" + sec_date + "]";
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	public String getGrade_name() {
		return grade_name;
	}

	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}
	

	public String getGrade_no() {
		return grade_no;
	}

	public void setGrade_no(String grade_no) {
		this.grade_no = grade_no;
	}
}