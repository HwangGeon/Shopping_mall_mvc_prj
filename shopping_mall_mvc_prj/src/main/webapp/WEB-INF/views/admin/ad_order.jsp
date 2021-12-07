<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%--     <c:if test="${empty sessionScope.admin_id}">
    <c:redirect url="ad_login.jsp"/>
    </c:if> --%>
<ul class="nav nav-tabs">
	<li class="nav-item"><a class="nav-link active" data-toggle="tab"
		href="#order_dash" onclick="orderDashCount();">대시보드</a></li>
	<li class="nav-item"><a class="nav-link" data-toggle="tab"
		href="#order_search" onclick="ordPagenation();">주문검색</a></li>
</ul>

<div class="tab-content content">
	<div class="tab-pane active h-100" id="order_dash">
		<div class="container-fluid">
			<div class="row mt-4">
				<div class="col-12">
					<div class="contentlabel">주문현황</div>
					<table class="table table-bordered">
						<thead>
							<tr class="tcol">
								<td class="col-1">주문완료</td>
								<td class="col-1">배송중</td>
							</tr>
						</thead>
						<tbody id="orderDashCount">
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="container-fluid">
			-주문을 처리해주세요. <br> -각 항목의 숫자를 클릭하면 하단에서 주문상태별 주문을 확인하고 처리하실 수
			있습니다.
		</div>
		<div class="container-fluid">
			<div class="row mt-4">
				<div class="col-12">
					<div class="contentlabel">주문목록</div>
					<table class="table table-bordered">
						<thead>
							<tr class="tcol">
								<td class="col-1">번호</td>
								<td class="col-3">주문코드</td>
								<td class="col-3">주문일자</td>
								<td class="col-2">주문상태</td>
								<td class="col-1">관리</td>
							</tr>
						</thead>
						<tbody id="orderDashTbody">
						</tbody>
					</table>
				</div>
			</div>
				<ul class="pagination justify-content-md-center" id="orderDashPageNumber">
				</ul>
		</div>
	</div>
	<div class="tab-pane h-100" id="order_search">
		<div class="container-fluid">
			<div class="row mt-4">
				<div class="col-12">
					<div class="contentlabel">주문검색</div>
					<div class="col-12 mt-2">
						<form action="#" method="post">
							<div class="form-group row">
								<label class="col-auto col-form-label text-center">주문검색</label>
								<div class="col-md-2">
									<select class="form-control" id="ord_division">
										<option value="1">주문코드</option>
										<option value="2">회원아이디</option>
									</select>
								</div>
								<div class="col-md-3">
									<input type="text" name="order_cd_id" class="form-control" id="order_cd_id">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-auto col-form-label text-center">주문상태</label>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="order_stat_cd"
										id="order_stat" value="0" checked="checked"> <label
										class="form-check-label" for="order_stat">전체</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="order_stat_cd"
										id="order_stat1" value="1"> <label
										class="form-check-label" for="order_stat1">주문완료</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="order_stat_cd"
										id="order_stat2" value="2"> <label
										class="form-check-label" for="order_stat2">배송중</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="order_stat_cd"
										id="order_stat3" value="3"> <label
										class="form-check-label" for="order_stat3">배송완료</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="order_stat_cd"
										id="order_stat4" value="4"> <label
										class="form-check-label" for="order_stat4">주문취소</label>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-auto col-form-label text-center">주문일자</label>
								<input type="text" id="order_datepicker1"> ~ <input
									type="text" id="order_datepicker2">
								<div class="col-2 ml-4">
									<input type="button" class="btn btn-dark" onclick="ordPagenation(); return false;" value="검색" />
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="container-fluid">
			<div class="row mt-4">
				<div class="col-12">
					<div class="contentlabel">주문목록</div>
					<table class="table table-bordered">
						<thead>
							<tr class="tcol">
								<td class="col-1">번호</td>
								<td class="col-3">주문코드</td>
								<td class="col-3">주문일자</td>
								<td class="col-2">주문상태</td>
								<td class="col-1">관리</td>
							</tr>
						</thead>
						<tbody id="ordSearchTbody">
						</tbody>
					</table>
				</div>
			</div>
				<ul class="pagination justify-content-md-center" id="orderSearchPageNumber">
				</ul>
		</div>
	</div>
</div>
