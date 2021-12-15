<%-- <%@page import="kr.co.sist.util.cipher.DataEncrypt"%>
<%@page import="kr.co.shopping_mall.model.UserInfoVO"%>
<%@page import="kr.co.shopping_mall.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String userId=(String)session.getAttribute("user_id");
request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="uVO" class="kr.co.shopping_mall.model.UserInfoVO" scope="page"/>
<jsp:setProperty property="*" name="uVO"/>
<c:catch var="e">
<%
//입력된 비밀번호 암호화
uVO.setUser_pw(DataEncrypt.messageDigest("MD5", uVO.getUser_pw()));
UserDAO ud=new UserDAO();
UserInfoVO uv=ud.selectInfo(userId);

//입력한 비밀번호가 맞는지 조회
String curPw=uv.getUser_pw();
String inputPw=uVO.getUser_pw();
if(!curPw.equals(inputPw)){ %>
	<script>
	alert("비밀번호를 알맞게 입력해주세요.");
	location.href="http://localhost/shopping_mall_prj/views/user/infoUpdateForm.jsp";
	</script>
<%}

if(curPw.equals(inputPw)){
	//이메일과 주소 수정
	//이메일 암호화
	DataEncrypt de=new DataEncrypt("AbcdEfgHiJkLmnOpQ");
	uVO.setUser_email(de.encryption(uVO.getUser_email()));
	String email=uVO.getUser_email();
	String addr=uVO.getUser_addr();
	int cnt=ud.updateInfo(userId, email, addr);	%>
	<script>
		location.href="http://localhost/shopping_mall_prj/views/user/infoUpdateCompl.jsp";
	</script>
	
<%}//end if
%>
</c:catch>
<c:if test="${ not empty e }">
<script type="text/javascript">
	alert("회원정보 수정에 실패하였습니다.");
	location.href="myInfo.jsp";
</script>
</c:if> --%>