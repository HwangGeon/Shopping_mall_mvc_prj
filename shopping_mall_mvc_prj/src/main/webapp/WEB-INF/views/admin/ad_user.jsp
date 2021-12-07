<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%--     <c:if test="${empty sessionScope.admin_id}">
    <c:redirect url="ad_login.jsp"/>
    </c:if> --%>
<ul class="nav nav-tabs">
	<li class="nav-item"><a class="nav-link active" data-toggle="tab"
		href="#user_dash" onclick="userDashCount();" >대시보드</a></li>
	<li class="nav-item"><a class="nav-link" data-toggle="tab"
		href="#user_search" onclick="allUserPagenation();">회원검색</a></li>
</ul>

<div class="tab-content content">
	<div class="tab-pane active h-100" id="user_dash">
		<div class="container-fluid">
			<div class="row mt-4">
				<div class="col-12">
					<div class="contentlabel">회원 현황</div>
					<table class="table table-bordered">
						<thead>
							<tr class="tcol">
								<td>신규회원</td>
								<td>탈퇴회원</td>
								<td>총회원</td>
							</tr>
						</thead>
						<tbody id="userDashCount">
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="container-fluid">
			-오늘을 기준으로한 회원 수 입니다. <br> -신규회원/탈퇴회원/총회원의 수를 클릭시 하단에 해당 회원이 보여집니다.
		</div>
		<div class="container-fluid">
			<div class="row mt-4">
				<div class="col-12">
					<div class="contentlabel">오늘 신규/탈퇴 회원목록</div>
					<table class="table table-bordered">
						<thead>
							<tr class="tcol">								
								<td>번호</td>
								<td class="col-1">회원ID</td>
								<td class="col-1">이름</td>
								<td class="col-1">회원등급</td>
								<td class="col-2">휴대전화</td>
								<td class="col-2">주소</td>
								<td class="col-2">이메일</td>
								<td class="col-2">가입일자</td>
								<td>관리</td>
							</tr>
						</thead>
						<tbody id="userDashTbody">
						</tbody>
					</table>
				</div>
			</div>
				<ul class="pagination justify-content-md-center pager" id="userDashPageNumber">
				</ul>
		</div>
	</div>
	<div class="tab-pane h-100" id="user_search">
		<div class="container-fluid">
			<div class="row mt-4">
				<div class="col-12">
					<div class="contentlabel">회원정보 검색</div>
					<div class="col-12 mt-2">
						<form id="userSearchForm" method="post">
							<div class="form-group row">
								<label class="col-auto col-form-label text-center">회원정보</label>
								<div class="col-md-2">
									<select class="form-control" id="user_division">
										<option value="1">아이디</option>
										<option value="2">이름</option>
									</select>
								</div>
								<div class="col-md-3">
									<input type="text" id="user_id_name" class="form-control">
								</div>
								<div class="col-md-2">
									<input type="button" class="btn btn-dark" onclick="userPagenation(); return false;" value="검색" />
								</div>
							</div>
							<div class="row mt-3">
							<label class="col-auto col-form-label text-center mr-3">탈퇴상태</label>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="user_category" id="user_category1" value="a" checked="checked"> 
								<label class="form-check-label" for="user_category1">전체</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="user_category" id="user_category2" value="n"> 
								<label class="form-check-label" for="user_category2">정상회원</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="user_category" id="user_category3" value="y"> 
								<label class="form-check-label" for="user_category3">탈퇴회원</label>
							</div>
						  </div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="container-fluid mt-5">
			<input type="button" class="btn btn-dark" onclick="allUserPagenation(); return false;" value="회원 전체보기" />
		</div>
		<div class="container-fluid">
			<div class="row mt-4">
				<div class="col-12">
					<div class="contentlabel">회원검색결과</div>
					<table class="table table-bordered">
						<thead>
							<tr class="tcol">
								<td>번호</td>
								<td class="col-1">회원ID</td>
								<td class="col-1">이름</td>
								<td class="col-1">회원등급</td>
								<td class="col-2">휴대전화</td>
								<td class="col-2">주소</td>
								<td class="col-2">이메일</td>
								<td class="col-2">가입일자</td>
								<td>관리</td>
							</tr>
						</thead>
						<tbody id="userSearchTbody">
						</tbody>
					</table>
				</div>
			</div>
			<ul class="pagination justify-content-md-center pager" id="userSearchPageNumber">
			</ul>
		</div>
	</div>
</div>