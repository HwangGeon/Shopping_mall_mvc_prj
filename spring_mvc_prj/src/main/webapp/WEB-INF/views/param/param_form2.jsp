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
$(function(){
	$("#getBtn").click(function(){
		$("#getFrm").submit();
	});//click
	$("#postBtn").click(function(){
		$("#postFrm").submit();
	});//click
	
});//ready
</script>
</head>
<body>
<form method="get" id="getFrm" action="http://localhost/spring_mvc_prj/param/param_form_process2.do">
<label>이름</label>
<input type="text" name="name"/><br/>
<label>나이</label>
<input type="text" name="age"/><br/>
<label>취미</label>
<input type="checkbox" name="hobby" value="영화"/>영화감상<br/>
<input type="checkbox" name="hobby" value="스노우보드"/>스노우보드<br/>
<input type="checkbox" name="hobby" value="음악"/>음악감상<br/>
<input type="checkbox" name="hobby" value="독서"/>독서<br/>
<input type="button" value="GET전송" id="getBtn"/>
</form>
<form method="post" id="postFrm" action="http://localhost/spring_mvc_prj/param/param_form_process2.do">
<label>이름</label>
<input type="text" name="name"/><br/>
<label>나이</label>
<!-- <input type="text" name="age"/><br/> -->
<label>취미</label>
<input type="checkbox" name="hobby" value="영화"/>영화감상<br/>
<input type="checkbox" name="hobby" value="스노우보드"/>스노우보드<br/>
<input type="checkbox" name="hobby" value="음악"/>음악감상<br/>
<input type="checkbox" name="hobby" value="독서"/>독서<br/>
<input type="button" value="POST전송" id="postBtn"/>
</form>
</body>
</html>