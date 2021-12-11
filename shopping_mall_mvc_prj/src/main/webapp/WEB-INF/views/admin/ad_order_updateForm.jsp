<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
    <c:if test="${empty sessionScope.admin_id}">
    <c:redirect url="ad_login.jsp"/>
    </c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>주문정보수정</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="http://team1.aws.sist.co.kr/common/css/custom_updateForm.css" >
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
	integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<script type="text/javascript">
$(function() {
	//주문상태 설정
	let ord_stat_cd = $('#ord_stat_cd').val();
	$("#ord_stat").val(ord_stat_cd).prop("selected", true);
});

function btnClick(str) {
	
	if(str == 'update') {
		let answer = confirm("수정완료하시겠습니까?");
		if (!answer) {
			return false;
		}
		
		let ord_cd = $("#ord_cd").val();
		let dv_addr = $("#dv_addr").val();
		let ord_stat_cd = $("#ord_stat").val();
		
		let formdata = { "ord_cd": ord_cd,
				  		  "dv_addr": dv_addr, 
				  	 	  "ord_stat_cd": ord_stat_cd};
		
	     $.ajax({
	       	cache: false,
	          url: "updateOrderProc.do", 
	        type : "post", 
	        dataType: 'json',
	        data : formdata, 
	     	 success: function(msg) {
	     		alert(msg.msg);
	            opener.parent.orderDashCount();
	            self.close();
	          },
			error: function() {
				alert("주문정보를 변경할 수 없습니다.");
				self.close();
			}
	      });
		} 
	
		if(str == 'cancle'){
			self.close();
		}
}

</script>
</head>
<body>
	<div class="container-fluid">
		<form method="post" id="updateForm">
			<table class="table table-bordered text-center">
				<thead class="thead-dark">
					<tr>
						<th class="col-1">주문코드</th>
						<th class="col-1">받는분</th>
						<th class="col-2">배송주소</th>
						<th class="col-2">주문일자</th>
						<th class="col-1">주문상태</th>
						<th class="col-1">상품</th>
						<th class="col-1">수량</th>
						<th class="col-1">단가</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td rowspan="0" class="readOnly"><input type="text" class="form-control" name="ord_cd" id="ord_cd" value="${ oVO.ord_cd }" readonly="readonly"/></td>
						<td rowspan="0" class="readOnly"><input type="text" class="form-control" name="dv_name" value="${ oVO.dv_name }" readonly="readonly"/></td>
						<td rowspan="0" ><input type="text" class="form-control" name="dv_addr" id="dv_addr" value="${ oVO.dv_addr }"/></td>
						<td rowspan="0" class="readOnly"><input type="text" class="form-control" name="ord_date" value="${ oVO.ord_date }" readonly="readonly"/></td>
						<td rowspan="0" width="12%">
							<input type="hidden" id="ord_stat_cd" value="${ oVO.ord_stat_cd }">
							<select class="custom-select" name="ord_stat" id="ord_stat">
  								<option value="1">주문완료</option>
 						 		<option value="2">배송중</option>
  								<option value="3">구매완료</option>
  								<option value="4">주문취소</option>
								</select>
						</td>
					</tr>
					<c:forEach var="od" items="${ odList }">
					<tr>
						<td class="readOnly"><input type="text" class="form-control" name="pro_name" value="${ od.pro_name }" readonly="readonly"/></td>
						<td class="readOnly"><input type="text" class="form-control" name="ordd_qty" value="${ od.ordd_qty }" readonly="readonly"/></td>
						<td class="readOnly"><input type="text" class="form-control" name="pro_price" value="${ od.pro_price }" readonly="readonly"/></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<div>
				<input type="text" class="form-control" name="dv_memo" value="${ oVO.dv_memo }" readonly="readonly"/>
			</div>
			<div class="mt-2" align="right"><h2>총 주문금액 : <span style="color: orange;">${ oVO.ord_price }</span>원</h2></div>
			<div class="row mt-3">
			<input type="button" class="btn btn-primary col-auto ml-auto mr-3" onclick="btnClick('update');" value="수정완료">
			<input type="button" class="btn btn-outline-primary mr-3" onclick="btnClick('cancle');" value="취소">
			</div>
		</form>
	</div>
</body>
</html>