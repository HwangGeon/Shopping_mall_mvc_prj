<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="service가 제공하는 업무로직 결과를 받아서 View 제공"
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
<table class="table">
<tr>
	<th>과목-특성</th>
</tr>
<c:if test="${ empty subjectList }">
<tr>
<td>과목에 대한 정보가 없습니다.</td>
</tr>
</c:if>
<c:forEach var="subject" items="${ subjectList }">
<tr>
<td><c:out value="${ subject }"/></td>
</tr>
</c:forEach>
</table>
<a href="../index.do">메인</a>
</body>
</html>