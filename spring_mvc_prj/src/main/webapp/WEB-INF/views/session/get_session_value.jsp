<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="true"
    info="세션에 저장된 값 사용."
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="http://localhost/spring_mvc_prj/common/css/main_v20211012.css">

<style type="text/css">

</style>
<!-- jQuery CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">

</script>
</head>
<body>
<c:if test="${ empty ses_msg }">
<!-- 세션을 사용하는 페이지에서는 세션이 없을 때의 처리 코드를 작성해야한다.  -->
<c:redirect url="../index.do"/>
</c:if>
<marquee scrollamount="10" behavior="slide"><c:out value="${ ses_msg }"/></marquee>
<marquee scrollamount="10" behavior="alternate"><c:out value="${ sessionScope.ses_msg }"/></marquee>

<div style="width: 300px">
<table class="table">
<tr>
	<th>이름</th>
</tr>
<c:if test="${ empty ses_link }">
<tr>
	<td>데이터가 존재하지 않습니다.</td>
</tr>
</c:if>
<c:forEach var="data" items="${ ses_link }">
<tr>
<td><a href="http://${  data.url }"><c:out value="${ data.name }"/></a></td>
</tr>
</c:forEach>
</table>
</div>
<div>
requestScope msg : <c:out value="${ req_msg }"/>
</div>
<a href="../index.do">메인</a>
</body>
</html>










