package kr.co.shopping_mall.vo;

public class ProductPagingVO {
	private int startPage, endPage, cPage, totalPages, len, start;
	
	public ProductPagingVO() {
		
	}

	public ProductPagingVO(int startPage, int endPage, int cPage, int totalPages, int len, int start) {
		this.startPage = startPage;
		this.endPage = endPage;
		this.cPage = cPage;
		this.totalPages = totalPages;
		this.len = len;
		this.start = start;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getcPage() {
		return cPage;
	}

	public void setcPage(int cPage) {
		this.cPage = cPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
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
	
	
}
