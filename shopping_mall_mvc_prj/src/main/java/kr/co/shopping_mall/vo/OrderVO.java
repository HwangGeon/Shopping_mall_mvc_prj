package kr.co.shopping_mall.vo;

public class OrderVO {
	
	private String no;
	private String ord_cd;
	private int ord_stat_cd;
	private String ord_stat_name;
	private String user_id;
	private int ord_price;
	private String ord_date;
	private String dv_name;
	private String dv_tel;
	private String dv_addr;
	private String dv_memo;
	
	public OrderVO() {
	}

	public OrderVO(String ord_cd, int ord_stat_cd, String user_id, int ord_price, String ord_date, String dv_name,
			String dv_tel, String dv_addr, String dv_memo) {
		this.ord_cd = ord_cd;
		this.ord_stat_cd = ord_stat_cd;
		this.user_id = user_id;
		this.ord_price = ord_price;
		this.ord_date = ord_date;
		this.dv_name = dv_name;
		this.dv_tel = dv_tel;
		this.dv_addr = dv_addr;
		this.dv_memo = dv_memo;
	}
	
	

	@Override
	public String toString() {
		return "OrderVO [no=" + no + ", ord_cd=" + ord_cd + ", ord_stat_name=" + ord_stat_name + ", ord_date="
				+ ord_date + "]";
	}

	public String getOrd_cd() {
		return ord_cd;
	}

	public void setOrd_cd(String ord_cd) {
		this.ord_cd = ord_cd;
	}

	public int getOrd_stat_cd() {
		return ord_stat_cd;
	}

	public void setOrd_stat_cd(int ord_stat_cd) {
		this.ord_stat_cd = ord_stat_cd;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getOrd_price() {
		return ord_price;
	}

	public void setOrd_price(int ord_price) {
		this.ord_price = ord_price;
	}

	public String getOrd_date() {
		return ord_date;
	}

	public void setOrd_date(String ord_date) {
		this.ord_date = ord_date;
	}

	public String getDv_name() {
		return dv_name;
	}

	public void setDv_name(String dv_name) {
		this.dv_name = dv_name;
	}

	public String getDv_tel() {
		return dv_tel;
	}

	public void setDv_tel(String dv_tel) {
		this.dv_tel = dv_tel;
	}

	public String getDv_addr() {
		return dv_addr;
	}

	public void setDv_addr(String dv_addr) {
		this.dv_addr = dv_addr;
	}

	public String getDv_memo() {
		return dv_memo;
	}

	public void setDv_memo(String dv_memo) {
		this.dv_memo = dv_memo;
	}

	public String getOrd_stat_name() {
		return ord_stat_name;
	}

	public void setOrd_stat_name(String ord_stat_name) {
		this.ord_stat_name = ord_stat_name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
}

