package kr.co.shopping_mall.vo;

public class ProductParamVO {
	private int category_cd;
	private String searchValue;
	private String cPage;
	private String page;
	private int len;
	private int start;
	
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getcPage() {
		return cPage;
	}
	public void setcPage(String cPage) {
		this.cPage = cPage;
	}
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getCategory_cd() {
		return category_cd;
	}
	public void setCategory_cd(int category_cd) {
		this.category_cd = category_cd;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	

	
	
}
