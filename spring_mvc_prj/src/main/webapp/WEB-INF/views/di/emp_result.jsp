<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="DBMS에서 조회한 결과 출력"
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
<th style="width: 50px">번호</th>
<th style="width: 100px">사원번호</th>
<th style="width: 150px">사원명</th>
<th style="width: 100px">연봉</th>
<th style="width: 120px">직무</th>
<th style="width: 40px">부서번호</th>
<th style="width: 180px">입사일</th>
</tr>
<c:if test="${ empty empList }">
<tr>
	<td colspan="7">조회된 사원 정보가 존재하지 않습니다.</td>
</tr>
</c:if>
<c:forEach var="emp" items="${ empList }">
<c:set var="i" value="${ i+1 }"/>
<tr>
<td><c:out value="${ i }"/></td>
<td><c:out value="${ emp.empno }"/></td>
<td><c:out value="${ emp.ename }"/></td>
<td><c:out value="${ emp.sal }"/></td>
<td><c:out value="${ emp.job }"/></td>
<td><c:out value="${ emp.deptno }"/></td>
<td><c:out value="${ emp.hiredate }"/></td>
</tr>
</c:forEach>
</table>
<br/>
<a href="../index.do">메인</a>
</body>
</html>



