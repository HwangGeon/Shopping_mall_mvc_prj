<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

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
<!-- Core theme CSS (includes Bootstrap)-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<style>
	#numBox{ 
		width: 40px; 
		border: solid #eff1f5;
    	border-width: 0 1px;
    	vertical-align: top;
    	font-family: tahoma, helvetica, "\B098\B214\ACE0\B515", "Nanum Gothic", "\B098\B214\C2A4\D018\C5B4", "Nanum Square", "Apple SD Gothic Neo", "Malgun Gothic", "\B9D1\C740\ACE0\B515", dotum, "\B3CB\C6C0", sans-serif;
    	color: #333333;
    	font-size: 12px;
    	font-weight: bold;
    	line-height: 34px;
    	text-align: center;
    }
	#plus{ 
		border:none; 
		background:#f1f2f4; 
		width: 40px; 
		height:40px;
	}
	#minus{ 
		border:none; 
		background:#f1f2f4; 
		width: 40px; 
		height:40px;
	}
	#deImgSize{
		text-align: center; 
		max-width:70%; 
		height:auto; 
	}
	#imgSize{ 
		text-align: center; 
		max-width:100%; 
		height:400px;  
	}
	#h2Size{ 
		text-align: center; 
		margin: 30px 0; 
		color: #D09869; 
		font-weight: bold; 
		font-family: 'Sunflower', sans-serif; 
	}
	#cartBtn{ 
		width:45%; 
		height:56px; 
		background:#D09869; 
		color:#FFFFFF; 
		border-color:#FFFFFF; 
		font-size:15px;
	}

	#numPrice{ 
		float: right; 
		margin: 8px 15px 0 0; 
	}
	#detailDiv{
		border-top:1px solid #D09869; 
		text-align: center;
	}
	#btnDiv{
		vertical-align:middle; 
		border-top:1px solid #DDD; 
		margin-top:20%;
	}
	#totalPriceDiv{
		border-top:1px solid #D09869;
		border-bottom:1px solid #D09869; 
		padding-top:5%;
	}
	#totalPriceText{
		font-size:1rem; 
		margin-left:15px;
	}
	#totalPrice{
		font-size:1.5rem; 
		font-weight:bold; 
		margin-bottom:0; 
		float: right; 
		padding-bottom:10px; 
		margin: -10px 15px 8px 10px;
	}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<title>상품상세</title>
<script>
$(function(){
	var cnt=$("#numBox").val();
	$("#totalPrice").text(cnt*${ proData.pro_price }+"원");
	
	$("#plus").click(function() {
		var num = $("#numBox").val();
		var plusNum = Number(num) + 1;

		if (plusNum >= 20) {
			$("#numBox").val(num);
		} else {
			$("#numBox").val(plusNum);
		}
		
		cnt=$("#numBox").val();
		$("#totalPrice").text(cnt*${ proData.pro_price }+"원");
	});//click

	$("#minus").click(function() {
		var num = $("#numBox").val();
		var minusNum = Number(num) - 1;

		if (minusNum <= 0) {
			$("#numBox").val(num);
		} else {
			$("#numBox").val(minusNum);
		}
		
		cnt=$("#numBox").val();
		$("#totalPrice").text(cnt*${ proData.pro_price }+"원");
	});//click
	
});//ready


function moveCart(){
	location.href="http://localhost/shopping_mallj/views/board/cart_list.do";
	/* location.href="http://localhost/shopping_mall_prj/views/board/test.jsp" */
}

function addCart(){
	if(confirm("장바구니에 담으시겠습니까?")){
		location.href="http://localhost/shopping_mall/board/cart_proc.do?pro_cd=${ proData.pro_cd }&cnt=" + document.getElementById("numBox").value;
/* 		location.href="http://localhost/shopping_mall/board/cart_proc.do?pro_cd=${ proData.pro_cd }&cnt=" + document.getElementById("numBox").value; */
	}//end if
}
<c:if test="${ not empty msg }">
alert("${ msg }");
</c:if>
</script>
<body>
<jsp:include page="../layout/header.jsp"/>
<div class="container px-4 px-lg-5 mt-5">
	<form id="frm">
	<div class="row" style="padding: 3%;">
		<div class="col-md-6" style="text-align: center;">
			<img src="http://localhost/shopping_mall/common/uploadImg/pro_img/${ proData.pro_img }" id="imgSize">
			<!-- <img src="http://placehold.it/400" id="imgSize"> -->
		</div>
		<div class="col-md-6">
		<div>
			<h2>${ proData.pro_name }</h2>
			
			<h6>무료배송</h6>
			
			<div id="btnDiv">
				<button type="button" id="minus">-</button>
				<input type="number" id="numBox" min="1" max="20" value="1"
					readonly="readonly"/>
				<button type="button" id="plus">+</button>
				<span id="numPrice">${ proData.pro_price_fmt }</span>
			</div>
			
			<div id="totalPriceDiv">
				<span id="totalPriceText">총 상품 금액</span>
				<span id="totalPrice"></span>
				<!-- <input type="text" id="totalPrice" value=""/> -->
			</div>
			
			<div style="padding-top: 5%; text-align: center; max-width:100%;">
				<button type="button" class="btn btn-default btn-lg" id="cartBtn" onclick="addCart()">장바구니 넣기</button>
				<button type="button" class="btn btn-default btn-lg" id="cartBtn" onclick="moveCart()">장바구니 이동</button>
			</div>
			</div>
		</div>
	</div>
	</form>
	<div class="py-5" id="detailDiv">
		<!-- <img src="http://placehold.it/500" id="imgSize"> -->
		<h2 id="h2Size">상품상세</h2>
		${ proData.pro_detail }
	</div>
	<div class="py-5" id="detailDiv">

		<h2 id="h2Size">배송안내</h2>
		<img src="http://localhost/shopping_mall/common/image/delivery.png" id="deImgSize">
	</div>
</div>

<jsp:include page="../layout/footer.jsp"/>
</body>
</html>