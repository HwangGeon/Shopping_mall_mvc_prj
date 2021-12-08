<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function chgMethod(){
	var obj=document.frm;
	let type="GET";
	
	if( obj.reqMethod[1].checked ){//선택된 요청방식의 비교.
		type="POST";	
	}//end if

	obj.method=type;//요청방식의 변경
	obj.submit();
		
}//chgMethod
<c:if test="${ param.login eq 'nil'}">
alert("로그인을 하지 않으셨습니다.");
</c:if>

</script>
</head>
<body>
<ul>
<li><a href="hello.do">안녕하세요? 요청</a></li>
<li><a href="get.do">GET요청</a></li>
<li>
	<form method="post" action="post.do">
	 <input type="submit" value="POST요청"/>
	</form>
</li>
<li>
	<form action="post_get.do" name="frm">
	<input type="radio" value="get" name="reqMethod" checked="checked"/>GET
	<input type="radio" value="post" name="reqMethod"/>POST
	<br/>
	 <input type="button" value="요청" onclick="chgMethod()"/>
	</form>
</li>
<li><a href="forward.do">view이동(forward)</a></li>
<li><a href="redirect.do">view이동(redirect)</a></li>
<li><a href="day1129/sub_path.do">경로구분.(하위경로가 존재)</a></li>
<li><a href="day1129_sub/exam.do">요청경로에 대한 응답연습</a></li>
<li><a href="day1129/use_resource.do">views안의 JSP가 webapp안의 자원 사용.</a></li>

<li><a href="param/param_form.do">web parameter(request)</a></li>
<li><a href="param/param_form2.do">web parameter(단일형 변수로 값 받기)</a></li>
<li><a href="param/param_form3.do">web parameter(VO)</a></li>

<li><a href="send/use_request.do">View로 데이터보내기(HttpRequestRequest)</a></li>
<li><a href="send/use_model.do">View로 데이터보내기(Model)</a></li>
<li><a href="send/use_model_and_view.do">View로 데이터보내기(ModelAndView)</a></li>

<li><a href="chain/chain_a.do">view이동_a (forward chain )</a></li>
<li><a href="chain/chain_b.do">view이동_b (forward chain )</a></li>

<li><a href="session/http_session_set.do">session 값설정 (HttpSession)</a></li>
<li><a href="session/http_session_get.do">session 값얻기 (HttpSession)</a></li>
<li><a href="session/http_session_remove.do">session 값삭제(HttpSession)</a></li>

<li><a href="session/session_attributes_set.do">session 값 설정(@SessionAttributes)</a></li>
<li><a href="session/session_attributes_get.do">session 값 얻기(@SessionAttributes)</a></li>
<li><a href="session/session_attributes_remove.do">session 값 삭제(@SessionAttributes)</a></li>

<li><a href="cookie/cookie_set.do">cookie 심기</a></li>
<li><a href="cookie/cookie_get.do">cookie 클래스읽기</a></li>
<li><a href="cookie/cookie_value_get.do">cookie @CookieValue읽기</a></li>
<li><a href="include/include.do">include</a></li>

<li><a href="exception/exception.do">예외처리(Exception Handling)</a></li>
<li><a href="exception/exception2.do">예외처리(Exception Handling)</a></li>

<li><a href="fileup/file_upload_frm.do">fileupload폼</a></li>

<li><a href="di/use_applicationContext.do">DI사용</a></li>
<li><a href="di/use_webApplicationContext.do">DI사용</a></li>
<li><a href="di/jdbc_template.do">DI사용</a></li>

<li><a href="ajax.do">ajax</a></li>


</ul>

</body>
</html>