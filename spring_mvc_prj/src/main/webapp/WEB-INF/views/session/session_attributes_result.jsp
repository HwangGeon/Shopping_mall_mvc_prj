<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="true"
    info="@SessionAttributes를 사용한 세션 저장값 사용"
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
<c:if test="${ empty sessionScope.data }">
alert("세션이 존재하지 않습니다.");
location.href="../index.do";
</c:if>
</script>
</head>
<body>
<div>
<strong>requestScope</strong><br/>
data : <c:out value="${ requestScope.data }"/><br/>
id : <c:out value="${ requestScope.id }"/><br/>
<c:if test="${ not empty requestScope.namesArr }">
	<ul>
	<c:forEach var="name" items="${ requestScope.namesArr }">
	<li><c:out value="${ name }"/></li>	
	</c:forEach>
	</ul>
</c:if>
</div>
<div>
<strong>sessionScope</strong><br/>
data : <c:out value="${ sessionScope.data }"/><br/>
id : <c:out value="${ sessionScope.id }"/><br/>
<c:if test="${ not empty sessionScope.namesArr }">
	<ul>
	<c:forEach var="name" items="${ sessionScope.namesArr }">
	<li><c:out value="${ name }"/></li>	
	</c:forEach>
	</ul>
</c:if>
</div>
<div>
<a href="../index.do">메인</a>
</div>
</body>
</html>