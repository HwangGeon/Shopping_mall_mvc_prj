package kr.co.shopping_mall.vo;

public class DeliveryVO {
	
	private String ord_cd;
	private String dv_name;
	private String dv_tel;
	private String dv_addr;
	private String dv_memo;
	
	public DeliveryVO() {
	}
	
	public DeliveryVO(String ord_cd, String dv_name, String dv_tel, String dv_addr, String dv_memo) {
		this.ord_cd = ord_cd;
		this.dv_name = dv_name;
		this.dv_tel = dv_tel;
		this.dv_addr = dv_addr;
		this.dv_memo = dv_memo;
	}

	public String getOrd_cd() {
		return ord_cd;
	}
	public void setOrd_cd(String ord_cd) {
		this.ord_cd = ord_cd;
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

	@Override
	public String toString() {
		return "DeliveryVO [ord_cd=" + ord_cd + ", dv_name=" + dv_name + ", dv_tel=" + dv_tel + ", dv_addr=" + dv_addr
				+ ", dv_memo=" + dv_memo + "]";
	}
	
	
}
