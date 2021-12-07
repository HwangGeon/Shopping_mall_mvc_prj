package kr.co.shopping_mall.vo;

public class ProductVO {
	private String pro_cd;
	private int category_cd;
	private String pro_name;
	private String pro_detail;
	private String pro_img;
	private int pro_price;
	private String sell_fl;
	private String input_date;
	private int cnt;
	private String pro_price_fmt;
	private String no;

	public ProductVO() {
	}

	public ProductVO(String pro_cd, int category_cd, String pro_name, String pro_detail, String pro_img, int pro_price,
			String sell_fl, String input_date) {
		this.pro_cd = pro_cd;
		this.category_cd = category_cd;
		this.pro_name = pro_name;
		this.pro_detail = pro_detail;
		this.pro_img = pro_img;
		this.pro_price = pro_price;
		this.sell_fl = sell_fl;
		this.input_date = input_date;
	}

	public String getPro_cd() {
		return pro_cd;
	}

	public void setPro_cd(String pro_cd) {
		this.pro_cd = pro_cd;
	}

	public int getCategory_cd() {
		return category_cd;
	}

	public void setCategory_cd(int category_cd) {
		this.category_cd = category_cd;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public String getPro_detail() {
		return pro_detail;
	}

	public void setPro_detail(String pro_detail) {
		this.pro_detail = pro_detail;
	}

	public String getPro_img() {
		return pro_img;
	}

	public void setPro_img(String pro_img) {
		this.pro_img = pro_img;
	}

	public int getPro_price() {
		return pro_price;
	}

	public void setPro_price(int pro_price) {
		this.pro_price = pro_price;
	}

	public String getSell_fl() {
		return sell_fl;
	}

	public void setSell_fl(String sell_fl) {
		this.sell_fl = sell_fl;
	}

	public String getInput_date() {
		return input_date;
	}

	public void setInput_date(String input_date) {
		this.input_date = input_date;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getPro_price_fmt() {
		return pro_price_fmt;
	}

	public void setPro_price_fmt(String pro_price_fmt) {
		this.pro_price_fmt = pro_price_fmt;
	}
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
}
