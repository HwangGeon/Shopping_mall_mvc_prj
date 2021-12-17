<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
<c:choose>
<c:when test="${ empty chk }">
alert("항목을 체크해주세요.");
location.href="http://localhost/shopping_mall/user/myOrder.do";
</c:when>
<c:otherwise>
alert("취소되었습니다.")
location.href="http://localhost/shopping_mall/user/myOrder.do";
</c:otherwise>
</c:choose>
</script>