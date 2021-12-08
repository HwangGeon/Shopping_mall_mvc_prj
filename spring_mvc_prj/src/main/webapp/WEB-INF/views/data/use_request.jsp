<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<!-- Controller에서 전달되는 데이터를 받아서 출력하는 일. -->
이름 : <strong><c:out value="${ name }" escapeXml="true"/></strong><br/>
이름 : <strong><c:out value="${ requestScope.name }" escapeXml="false"/></strong><br/>
<div style="width: 300px">
<table class="table">
<tr>
<th style="width: 80px">번호</th>
<th style="widows: 220px">과목명</th>
</tr>
<c:if test="${ empty subjects }">
<tr><td colspan="2">전달된 과목 없음.</td></tr>
</c:if>
<c:forEach var="subject" items="${ subjects }">
<c:set var="i" value="${ i+1 }"/>
<tr>
	<td><c:out value="${ i }"/></td>
	<td><c:out value="${ subject }" escapeXml="false"/></td>
</tr>
</c:forEach>
</table>
</div>
<a href="../index.do">메인</a>
</body>
</html>








