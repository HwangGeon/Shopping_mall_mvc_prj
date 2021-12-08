package kr.co.sist.vo;

/**
 * 주의 : HTML Form Control의 이름과 VO instance 변수명이 같을 것.
 * @author user
 */
public class ParamVO {
	private String name,age,ip;
	private String[] hobby;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	
	
}
