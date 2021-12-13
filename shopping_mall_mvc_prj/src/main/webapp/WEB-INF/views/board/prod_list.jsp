<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>상품게시판</title>

<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="http://localhost/shopping_mall/common/image/favicon.png" />
<!--jQuery CDN-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<!-- font -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@500&display=swap" rel="stylesheet">
<!-- Bootstrap icons-->
<link
   href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
   rel="stylesheet" />
<style type="text/css">
.title{ 
	text-align:left; color: #D09869; 
	font-weight: bold; 
	font-family: 'Sunflower', sans-serif; 
	margin-top:50px; 
}
#pro{
	text-decoration: none;
	color:#000;
}
#last{
	height:156px;
	width:100%;
	text-align: center;
}
</style>
<!-- Core theme CSS (includes Bootstrap)-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="../layout/header.jsp"/>

<!-- Section-->
<section class="py-5">
<div class="container px-4 px-lg-5">
<c:if test="${ searchValue eq null }">
	<c:if test="${ category_cd eq 0 }">
		<h2 class="title">전체상품</h2>
	</c:if>
	<c:if test="${ category_cd eq 1 }">
		<h2 class="title">농산물</h2>
	</c:if>
	<c:if test="${ category_cd eq 2 }">
		<h2 class="title">수산물</h2>
	</c:if>
	<c:if test="${ category_cd eq 3 }">
		<h2 class="title">축산물</h2>
	</c:if>
</c:if>
	<c:if test="${ searchValue ne null }">
		<h2 class="title">'${ searchValue }' 검색 결과</h2>
	</c:if>
	
</div>
	<div class="container px-4 px-lg-5 mt-5">
		<div
			class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-left">
			<!-- 상품 -->
		
		<c:if test="${  proCnt ne null }">
			<c:forEach var="pro" items="${ proData }">
			<c:set var="i" value="${ i+1 }"/>
			<div class="col mb-5">
			<a href="http://localhost/shopping_mall/board/prod_detail.do?pro_cd=${ pro.pro_cd }" id="pro">
				<div class="card h-100" id="pro">
					<!-- Product image-->
					<img class="card-img-top"
						src="http://localhost/shopping_mall/common/uploadImg/pro_img/${ pro.pro_img }" style="width:100%; height:300px;"/>
					<!-- Product details-->
					<div class="card-body p-4">
						<div class="text-center">
							<!-- Product name-->
							<h5 class="fw-bolder"><c:out value="${ pro.pro_name }"/></h5>
							<!-- Product price-->
							<c:out value="${ pro.pro_price_fmt }"/>
						</div>
					</div>
				</div>
			</a>
			</div>
			</c:forEach>

		</div>
	</div>
	
	<!-- 페이징 -->	
 	<nav aria-label="Page navigation example">
		<ul class="pagination pagination justify-content-center">
			<c:if test="${ ppVO.startPage eq 1 }">
			<li class="page-item disabled"><a class="page-link" href="#" tabindex="-1"
				aria-disable="true">Previous </a></li>
			</c:if>
			<c:if test="${ ppVO.startPage ne 1 }">
			<%-- <c:if test="${ category_cd ne null }">
			<li class="page-item"><a class="page-link" href=
			"http://localhost/shopping_mall/board/prod_list.do?category_cd=${ category_cd }&page=${ ppVO.startPage-1 }"
				 tabindex="-1" aria-disable="true">Previous </a></li>
			</c:if>
			<c:if test="${ searchValue ne null }">
			<li class="page-item"><a class="page-link" href=
			"http://localhost/shopping_mall/board/prod_list.do?searchValue=${ searchValue }&page=${ ppVO.startPage-1 }"
				 tabindex="-1" aria-disable="true">Previous </a></li>
			</c:if> --%>
			<c:choose>
			<c:when test="${ searchValue ne null }">
			<li class="page-item"><a class="page-link" href=
			"http://localhost/shopping_mall/board/prod_list.do?searchValue=${ searchValue }&page=${ ppVO.startPage-1 }"
				 tabindex="-1" aria-disable="true">Previous </a></li>
			</c:when>
			<c:otherwise>
			<li class="page-item"><a class="page-link" href=
			"http://localhost/shopping_mall/board/prod_list.do?category_cd=${ category_cd }&page=${ ppVO.startPage-1 }"
				 tabindex="-1" aria-disable="true">Previous </a></li>
			</c:otherwise>
			</c:choose>
			</c:if>
			
			<c:forEach var="i" begin="${ ppVO.startPage }" end="${ ppVO.endPage }" step="1">
			<c:choose>
			<c:when test="${ i eq ppVO.cPage }">
			<li class="page-item active">
			</c:when>
			<c:otherwise>
			<li class="page-item">			
			</c:otherwise>
			</c:choose>
			<%-- <c:if test="${ category_cd ne null }">
			<a class="page-link my" href="http://localhost/shopping_mall/board/prod_list.do?category_cd=${ category_cd }&page=${ i }">${ i }</a></li>
			</c:if>
			<c:if test="${ searchValue ne null }">
			<a class="page-link my" href="http://localhost/shopping_mall/board/prod_list.do?searchValue=${ searchValue }&page=${ i }">${ i }</a></li>
			</c:if> --%>
			<c:choose>
			<c:when test="${ searchValue ne null }">
			<a class="page-link my" href="http://localhost/shopping_mall/board/prod_list.do?searchValue=${ searchValue }&page=${ i }">${ i }</a></li>
			</c:when>
			<c:otherwise>
			<a class="page-link my" href="http://localhost/shopping_mall/board/prod_list.do?category_cd=${ category_cd }&page=${ i }">${ i }</a></li>
			</c:otherwise>
			</c:choose>
			</c:forEach>
			
			<c:choose>
			<c:when test="${ ppVO.totalPages eq ppVO.endPage }">
			<li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
			</c:when>
			<c:otherwise>
			<c:choose>
 			<c:when test="${ searchValue ne null }">
			<li class="page-item"><a class="page-link" 
			href="http://localhost/shopping_mall/board/prod_list.do?searchValue=${ searchValue }&page=${ ppVO.endPage + 1 }">Next</a></li>
			</c:when>
			<c:otherwise>
			<li class="page-item"><a class="page-link" 
			href="http://localhost/shopping_mall/board/prod_list.do?category_cd=${ category_cd }&page=${ ppVO.endPage + 1 }">Next</a></li>
			</c:otherwise>
			</c:choose>
			</c:otherwise>
			</c:choose>

		</ul>
	</nav> 
	</c:if>
		<c:if test="${ proCnt eq null }">
			<div id="last">해당 상품이 존재하지 않습니다.</div>
		</c:if>
</section>
 
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>