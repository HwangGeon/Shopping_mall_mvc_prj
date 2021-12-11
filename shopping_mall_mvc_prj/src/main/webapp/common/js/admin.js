$(function() {
	//홈대시정보 초기화
	homeDashCount();
	
	//==========================================================================================================================
	//datepicker	
	$("#home_datepicker1,#home_datepicker2,#order_datepicker1,#order_datepicker2").datepicker({
		dateFormat: 'yy-mm-dd' //달력 날짜 형태
		, showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
		, showMonthAfterYear: true // 월- 년 순서가아닌 년도 - 월 순서
		, changeYear: true //option값 년 선택 가능
		, changeMonth: true //option값  월 선택 가능                
		, showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
		, buttonImage: "http://localhost/shopping_mall/common/image/calendar.png" //버튼 이미지 경로
		, buttonImageOnly: false //버튼 이미지만 깔끔하게 보이게함
		, buttonText: "선택" //버튼 호버 텍스트              
		, yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
		, monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'] //달력의 월 부분 텍스트
		, monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'] //달력의 월 부분 Tooltip
		, dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'] //달력의 요일 텍스트
		, dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'] //달력의 요일 Tooltip
		, minDate: "-5Y" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
		, maxDate: "+0D" //최대 선택일자(+1D:하루후, +1M:한달후, +1Y:일년후)  
	});

	//초기값을 오늘 날짜로 설정
	$("#home_datepicker1,#home_datepicker2,#order_datepicker1,#order_datepicker2").datepicker("setDate", "today"); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)     

	//==========================================================================================================================
	//썸머노트 세팅
	$('#summernote').summernote({
		height: 450,                 // 에디터 높이
		minHeight: null,             // 최소 높이
		maxHeight: null,             // 최대 높이
		focus: false,                  // 에디터 로딩후 포커스를 맞출지 여부
		lang: "ko-KR",					// 한글 설정
		placeholder: '최대 2048자까지 쓸 수 있습니다',	//placeholder 설정
		toolbar: [
			// [groupName, [list of button]]
			['fontname', ['fontname']],
			['fontsize', ['fontsize']],
			['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
			['color', ['forecolor', 'color']],
			['table', ['table']],
			['para', ['ul', 'ol', 'paragraph']],
			['height', ['height']],
			['insert', ['picture', 'link', 'video']],
			['view', ['fullscreen', 'help']]
		],
		fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', '맑은 고딕', '궁서', '굴림체', '굴림', '돋움체', '바탕체'],
		fontSizes: ['8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '28', '30', '36', '50', '72'],
		callbacks: {
			onImageUpload: function(files) {
				uploadSummernoteImageFile(files[0], this);
			},
			onMediaDelete: function(target) {
				deleteSummernoteImageFile(target[0].src);
			}
		}
	});

	//==========================================================================================================================
	//비밀번호변경폼 새비밀번호 일치 확인
	$("#newPassCheck,#newPass").keyup(function() {
		if ($('#newPassCheck').val() != $('#newPass').val()) {
			$('font[name=passCheck]').text('');
			$('font[name=passCheck]').attr('color', 'red');
			$('font[name=passCheck]').html("새로운 비밀번호가 일치하지 않습니다");
			$('#adPassUpdateBtn').attr("disabled", "disabled");
		} else {
			$('font[name=passCheck]').text('');
			$('font[name=passCheck]').attr('color', 'blue');
			$('font[name=passCheck]').html("새로운 비밀번호가 일치합니다");
			$('#adPassUpdateBtn').removeAttr("disabled");
		}
	});
	
	//==========================================================================================================================
	//비밀번호변경
	$('#adPassUpdateBtn').click(function() {
		let newPass = $("#newPass").val();
		let nowPass = $("#nowPass").val();
		
		$.ajax({
			url: "passUpdateProc.do", 
			type: "POST",
			data: {newPass : newPass, nowPass : nowPass},
			dataType: 'json',
			success: function(flag) { 
				if(flag){
					alert("비밀번호 변경에 성공하셨습니다. 다음 로그인시 새로운 비밀번호로 로그인 해주세요.");
					location.replace("ad_main.do");
				} else {
					alert("비밀번호 변경에 실패했습니다. 현재 비밀번호를 확인해주세요");
				}
			} 
		});
	});

	//==========================================================================================================================
	//상품등록중 상품이미지 첨부파일 미리보기 처리
	$("input[name='pro_img']").on("change", function() {

		let fileInput = $("input[name='pro_img']");
		let fileList = fileInput[0].files;
		let fileObj = fileList[0];

		if (!imgFileCheck(fileObj.name, fileObj.size)) {
			$("input[name='pro_img']").val("");
			return false;
		} else {
			imgPreview(this);
		}
	});
}); //ready
//==========================================================================================================================
//input number 글자수 제한
    function numberMaxLength(e){
        if(e.value.length > e.maxLength){
            e.value = e.value.slice(0, e.maxLength);
        }
    }
//==========================================================================================================================
//상품등록중 상품이미지 첨부파일 유효성체크
function imgFileCheck(fileName, fileSize) {
	let regex = new RegExp("(.*?)\.(jpg|png)$");
	let maxSize = 1048576; //1MB

	if (fileSize >= maxSize) {
		alert("[1MB]이하의 파일만 선택해주세요");
		return false;
	}
	if (!regex.test(fileName)) {
		alert(".jpg 또는 .png 형식의 이미지 파일만 선택해주세요");
		return false;
	}
	return true;
}

//==========================================================================================================================
//상품등록중 등록된 상품이미지 첨부파일 미리보기
function imgPreview(fis) {
	let reader = new FileReader();
	reader.onload = function(e) {
		$('#loadImg').attr('src', e.target.result);
	}
	reader.readAsDataURL(fis.files[0]);
}

//==========================================================================================================================
//썸머노트 이미지 파일 업로드
function uploadSummernoteImageFile(file, el) {
	let data = new FormData();
	data.append("file", file);
	$.ajax({
		data: data,
		type: "POST",
		url: "uploadSummernoteImageFile.do",
		contentType: false,
		enctype: 'multipart/form-data',
		processData: false,
		success: function(url) {
			$(el).summernote('editor.insertImage', url); //url = 완전한 url이어야함 ../ 또는 c:// 안됨
		}
	});
}

//==========================================================================================================================
//썸머노트 이미지 파일 삭제
function deleteSummernoteImageFile(src) {
	let splitSrc = src.split("/");
	let fileName = splitSrc[splitSrc.length - 1];

	let fileData = { fileName: fileName };

	$.ajax({
		url: "deleteSummernoteImageFile.do",
		data: fileData,
		type: "post",
	});
}

//==========================================================================================================================
//상품등록
function addProduct() {
	if ($("input[name='pro_name']").val() == "") {
		alert("상품명을 입력하세요");
		$("input[name='pro_name']").focus();
		return false;
	}
	if ($("input[name='pro_price']").val() == "") {
		alert("상품가격을 입력하세요");
		$("input[name='pro_price']").focus();
		return false;
	}
	if ($("input[name='pro_img']").val() == "") {
		alert("상품이미지를 등록하세요");
		$("input[name='pro_img']").focus();
		return false;
	}
	if ($("input[name='category_cd']:radio:checked").length < 1) {
		alert("상품분류를 선택하세요");
		$("input[name='category_cd']").focus();
		return false;
	}
	if ($("#summernote").val() == "") {
		alert("상품설명을 입력하세요");
		$("#summernote").focus();
		return false;
	}

	let formData = new FormData($("#addProductForm")[0]);

	let answer = confirm("상품을 등록하시겠습니까?\n" +
		"\n상품명 : " + formData.get("pro_name") +
		"\n상품가격 : " + formData.get("pro_price") +
		"\n상품분류코드 : " + formData.get("category_cd")
	);
	if (!answer) {
		return false;
	}

	$.ajax({
		cache: false,
		url: "addProductProc.do",
		processData: false,
		contentType: false,
		type: 'POST',
		data: formData,
		success: function() {
			alert("등록 성공");
			$('#addProductForm')[0].reset();
			$('#summernote').summernote('reset');
			$('#loadImg').removeAttr("src");
		},
		error: function() {
			alert("등록 실패");
		}
	});
}

//==========================================================================================================================
//검색조건에 따라 상품갯수카운트하고 페이지버튼생성
function allProPagenation() {
	$("#searchValue").val("");
	$("#pro_category4").prop("checked", true);

	proPagenation();
}

function proPagenation() {
	let division = $("#pro_division").val();
	let searchValue = $("#searchValue").val();
	let category_cd = $("input[name='category_cd1']:radio:checked").val();

	let condition = { "division": division, "searchValue": searchValue, "category_cd": category_cd };

	$.ajax({
		cache: false,
		url: "productPagenation.do",
		type: 'get',
		data: condition,
		success: function(pageCount) {
			//페이징버튼 그려줄 태그의 선택자
			let paging = $("#productSearchPageNumber");
			paging.empty();
			//1,2,3 페이지 생성
			for (let i = 1; i <= pageCount; i++) {
				paging.append('<li class=\"page-item\"><a class=\"page-link\" href=\"javascript:void(0)\">' + i + '</a></li>');
			}
			paging.find('li:nth-child(1)').addClass('active');
			productSearch(1); //바로 첫번째 페이지 그려줌

			//상품 페이지 1,2,3...클릭시 active효과주고 검색
			paging.find('li').click(function() {
				paging.find('li').removeClass('active');
				$(this).addClass('active');
				let index = $(this).text();
				productSearch(index);
			});
		},
		error: function() {
			alert("실패");
		}

	});
}

//==========================================================================================================================
//검색조건에따라 상품테이블 그리기
function productSearch(index) {
	let division = $("#pro_division").val();
	let searchValue = $("#searchValue").val();
	let category_cd = $("input[name='category_cd1']:radio:checked").val();

	let condition = { "index": index, "division": division, "searchValue": searchValue, "category_cd": category_cd };

	$.ajax({
		cache: false,
		url: "searchProduct.do",
		type: 'post',
		data: condition,
		dataType: 'json',
		success: function(data) {
			$("#proSearchTbody").empty();
			let proSearchTbody = '';
			for (key in data) {
				proSearchTbody += '<tr class="trow">';
				proSearchTbody += '<td>' + data[key].no + '</td>';
				proSearchTbody += '<td>' + data[key].pro_cd + '</td>';
				proSearchTbody += '<td>' + data[key].pro_name + '</td>';
				proSearchTbody += '<td>' + data[key].pro_price + '</td>';
				proSearchTbody += '<td>' + data[key].input_date + '</td>';
				proSearchTbody += '<td>' + data[key].sell_fl + '</td>';
				proSearchTbody += '<td>' + '<a href=\"updateProductForm.do?pro_cd=' + data[key].pro_cd + '\" onclick=\"window.open(this.href,\'_blank\',\'width=1200,height=300,top=200,left=200\'); return false;\">수정</a></td>';
				proSearchTbody += '</tr>';
			}
			$("#proSearchTbody").append(proSearchTbody);
		},
		error: function() {
			alert("실패");
		}
	});
}

//==========================================================================================================================
//상품대시보드 등록상품,판매중,판매중이지않은상품 개수구하여 나타내기
function proDashCount() { //productdashinfo
	$.ajax({
		cache: false,
		url: "productDashInfo.do",
		type: 'post',
		dataType: 'json',
		success: function(data) {
			$("#proDashCount").empty();
			let proDashCount = '';
			proDashCount += '<tr class="trow">';
			proDashCount += '<td>' + '<a href=\'javascript:void(0)\' onclick=\'proDashPagenation(' + data.countAll + ',"a");\'>' + data.countAll + '개</a></td>';
			proDashCount += '<td>' + '<a href=\'javascript:void(0)\' onclick=\'proDashPagenation(' + data.countSellY +',"y");\'>' + data.countSellY + '개</a></td>';
			proDashCount += '<td>' + '<a href=\'javascript:void(0)\' onclick=\'proDashPagenation(' + data.countSellN +',"n");\'>' + data.countSellN + '개</a></td>';
			proDashCount += '</tr>'
			$("#proDashCount").append(proDashCount);
		}
	});
}

//==========================================================================================================================
//상품대시보드 페이지버튼 만들기
function proDashPagenation(cntData, flag) {
	let paging = $("#proDashPageNumber");
	let rowsPerPage = 8;
	let pageCount = Math.ceil(cntData / rowsPerPage);

	paging.empty();
	for (let i = 1; i <= pageCount; i++) {
		paging.append('<li class=\"page-item\"><a class=\"page-link\" href=\"javascript:void(0)\">' + i + '</a></li>');
	}
	paging.find('li:nth-child(1)').addClass('active');
	
	proDashSearch(1, flag);
	
	//상품 페이지 1,2,3...클릭시 active효과주고 검색
	paging.find('li').click(function() {
		paging.find('li').removeClass('active');
		$(this).addClass('active');
		let index = $(this).text();
		proDashSearch(index, flag);
	});
}
//==========================================================================================================================
//상품대시보드 상품테이블 만들기
function proDashSearch(index, flag) {
	
		let condition = {"index": index, "flag": flag};
	
		$.ajax({
		cache: false,
		url: "searchProductDash.do",
		type: 'post',
		data: condition,
		dataType: 'json',
		success: function(data) {
			$("#proDashTbody").empty();
			let proDashTbody = '';
			for (key in data) {
				proDashTbody += '<tr class="trow">';
				proDashTbody += '<td>' + data[key].no + '</td>';
				proDashTbody += '<td>' + data[key].pro_cd + '</td>';
				proDashTbody += '<td>' + data[key].pro_name + '</td>';
				proDashTbody += '<td>' + data[key].pro_price + '</td>';
				proDashTbody += '<td>' + data[key].input_date + '</td>';
				proDashTbody += '<td>' + data[key].sell_fl + '</td>';
				proDashTbody += '<td>' + '<a href=\"updateProductForm.do?pro_cd=' + data[key].pro_cd + '\" onclick=\"window.open(this.href,\'_blank\',\'width=1200,height=300,top=200,left=200\'); return false;\">수정</a></td>';
				proDashTbody += '</tr>';
			}
			$("#proDashTbody").append(proDashTbody);
		},
		error: function() {
			alert("실패");
		}
	});
}

//==========================================================================================================================
//유저대시보드 오늘을 기준으로한 신규회원 탈퇴회원 총회원수 구하기
function userDashCount(){
		$.ajax({
		cache: false,
		url: "userDashInfo.do",
		type: 'post',
		dataType: 'json',
		success: function(data) {
			$("#userDashCount").empty();
			let userDashCount = '';
			userDashCount += '<tr class="trow">';
			userDashCount += '<td>' + '<a href=\'javascript:void(0)\' onclick=\'userDashPagenation(' + data.countNewUser + ',"n");\'>' + data.countNewUser + '명</a></td>';
			userDashCount += '<td>' + '<a href=\'javascript:void(0)\' onclick=\'userDashPagenation(' + data.countSecUser +',"y");\'>' + data.countSecUser + '명</a></td>';
			userDashCount += '<td>' + '<a href=\'javascript:void(0)\' onclick=\'userDashPagenation(' + data.countAllUser +',"a");\'>' + data.countAllUser + '명</a></td>';
			userDashCount += '</tr>'
			$("#userDashCount").append(userDashCount);
		}
	});
} 

//==========================================================================================================================
//유저대시보드 페이지버튼 만들기
function userDashPagenation(cntData, flag){
	let paging = $("#userDashPageNumber");
	let rowsPerPage = 8;
	let pageCount = Math.ceil(cntData / rowsPerPage);

	paging.empty();
	for (let i = 1; i <= pageCount; i++) {
		paging.append('<li class=\"page-item\"><a class=\"page-link\" href=\"javascript:void(0)\">' + i + '</a></li>');
	}
	paging.find('li:nth-child(1)').addClass('active');
	
	userDashSearch(1, flag);
	
	//상품 페이지 1,2,3...클릭시 active효과주고 검색
	paging.find('li').click(function() {
		paging.find('li').removeClass('active');
		$(this).addClass('active');
		let index = $(this).text();
		userDashSearch(index, flag);
	});
}

//==========================================================================================================================
//유저대시보드 테이블만들기
function userDashSearch(index, flag) {
	
		let condition = {"index": index, "flag": flag};
	
		$.ajax({
		cache: false,
		url: "searchUserDash.do",
		type: 'post',
		data: condition,
		dataType: 'json',
		success: function(data) {
			$("#userDashTbody").empty();
			let userDashTbody = '';
			for (key in data) {
				userDashTbody += '<tr class="trow">';
				userDashTbody += '<td>' + data[key].no + '</td>';
				userDashTbody += '<td>' + data[key].user_id + '</td>';
				userDashTbody += '<td>' + data[key].user_name + '</td>';
				userDashTbody += '<td>' + data[key].grade_name + '</td>';
				userDashTbody += '<td>' + data[key].user_tel + '</td>';
				userDashTbody += '<td>' + data[key].user_addr + '</td>';
				userDashTbody += '<td>' + data[key].user_email + '</td>';
				userDashTbody += '<td>' + data[key].reg_date + '</td>';
				userDashTbody += '<td>' + '<a href=\"updateUserForm.do?user_id=' + data[key].user_id + '\" onclick=\"window.open(this.href,\'_blank\',\'width=1200,height=300,top=200,left=200\'); return false;\">수정</a></td>';
				userDashTbody += '</tr>';
			}
			$("#userDashTbody").append(userDashTbody);
		},
		error: function() {
			alert("실패");
		}
	});
}

//==========================================================================================================================
//검색조건에 따라 유저정보 카운트하고 페이지버튼생성
function allUserPagenation() {
	$("#user_id_name").val("");
	$("#user_category1").prop("checked", true);

	userPagenation();
}

function userPagenation() {
	let division = $("#user_division").val();
	let searchValue = $("#user_id_name").val();
	let user_category = $("input[name='user_category']:radio:checked").val();
	let condition = { "division": division, "searchValue": searchValue, "user_category": user_category };

	$.ajax({
		cache: false,
		url: "userPagenation.do",
		type: 'get',
		data: condition,
		success: function(pageCount) {
			//페이징버튼 그려줄 태그의 선택자
			let paging = $("#userSearchPageNumber");
			paging.empty();
			//1,2,3 페이지 생성
			for (let i = 1; i <= pageCount; i++) {
				paging.append('<li class=\"page-item\"><a class=\"page-link\" href=\"javascript:void(0)\">' + i + '</a></li>');
			}
			paging.find('li:nth-child(1)').addClass('active');
			userSearch(1); //바로 첫번째 페이지 그려줌

			//페이지 1,2,3...클릭시 active효과주고 검색
			paging.find('li').click(function() {
				paging.find('li').removeClass('active');
				$(this).addClass('active');
				let index = $(this).text();
				userSearch(index);
			});
		},
		error: function() {
			alert("실패");
		}
	});
}

//==========================================================================================================================
//검색조건에따라 유저테이블 그리기
function userSearch(index) {
	let division = $("#user_division").val();
	let searchValue = $("#user_id_name").val();
	let user_category = $("input[name='user_category']:radio:checked").val();

	let condition = { "index": index, "division": division, "searchValue": searchValue, "user_category": user_category };

	$.ajax({
		cache: false,
		url: "searchUser.do",
		type: 'post',
		data: condition,
		dataType: 'json',
		success: function(data) {
			$("#userSearchTbody").empty();
			let userSearchTbody = '';
			for (key in data) {
				userSearchTbody += '<tr class="trow">';
				userSearchTbody += '<td>' + data[key].no + '</td>';
				userSearchTbody += '<td>' + data[key].user_id + '</td>';
				userSearchTbody += '<td>' + data[key].user_name + '</td>';
				userSearchTbody += '<td>' + data[key].grade_name + '</td>';
				userSearchTbody += '<td>' + data[key].user_tel + '</td>';
				userSearchTbody += '<td>' + data[key].user_addr + '</td>';
				userSearchTbody += '<td>' + data[key].user_email + '</td>';
				userSearchTbody += '<td>' + data[key].reg_date + '</td>';
				userSearchTbody += '<td>' + '<a href=\"updateUserForm.do?user_id=' + data[key].user_id + '\" onclick=\"window.open(this.href,\'_blank\',\'width=1200,height=300,top=200,left=200\'); return false;\">수정</a></td>';
				userSearchTbody += '</tr>';
			}
			$("#userSearchTbody").append(userSearchTbody);
		},
		error: function() {
			alert("실패");
		}
	});
}

//==========================================================================================================================
//주문대시보드 주문완료, 배송중, 배송완료인 주문 개수구하여 나타내기
function orderDashCount(){
		$.ajax({
		cache: false,
		url: "orderDashInfo.do",
		type: 'post',
		dataType: 'json',
		success: function(data) {
			$("#orderDashCount").empty();
			let orderDashCount = '';
			orderDashCount += '<tr class="trow">';
			orderDashCount += '<td>' + '<a href=\'javascript:void(0)\' onclick=\'orderDashPagenation(' + data.countProcessing + ',"1");\'>' + data.countProcessing + '건</a></td>';
			orderDashCount += '<td>' + '<a href=\'javascript:void(0)\' onclick=\'orderDashPagenation(' + data.countInDelivery +',"2");\'>' + data.countInDelivery + '건</a></td>';
			orderDashCount += '</tr>'
			$("#orderDashCount").append(orderDashCount);
		}
	});
} 

//==========================================================================================================================
//주문대시보드 페이지버튼 만들기
function orderDashPagenation(cntData, flag){
	let paging = $("#orderDashPageNumber");
	let rowsPerPage = 8;
	let pageCount = Math.ceil(cntData / rowsPerPage);

	paging.empty();
	for (let i = 1; i <= pageCount; i++) {
		paging.append('<li class=\"page-item\"><a class=\"page-link\" href=\"javascript:void(0)\">' + i + '</a></li>');
	}
	paging.find('li:nth-child(1)').addClass('active');
	
	orderDashSearch(1, flag);
	
	//페이지 1,2,3...클릭시 active효과주고 검색
	paging.find('li').click(function() {
		paging.find('li').removeClass('active');
		$(this).addClass('active');
		let index = $(this).text();
		orderDashSearch(index, flag);
	});
}

//==========================================================================================================================
//주문대시보드 테이블만들기
function orderDashSearch(index, flag) {
	
		let condition = {"index": index, "flag": flag};
	
		$.ajax({
		cache: false,
		url: "searchOrderDash.do",
		type: 'post',
		data: condition,
		dataType: 'json',
		success: function(data) {
			$("#orderDashTbody").empty();
			let orderDashTbody = '';
			for (key in data) {
				orderDashTbody += '<tr class="trow">';
				orderDashTbody += '<td>' + data[key].no + '</td>';
				orderDashTbody += '<td>' + data[key].ord_cd + '</td>';
				orderDashTbody += '<td>' + data[key].ord_date + '</td>';
				orderDashTbody += '<td>' + data[key].ord_stat_name + '</td>';
				orderDashTbody += '<td>' + '<a href=\"updateOrderForm.do?ord_cd=' + data[key].ord_cd + '\" onclick=\"window.open(this.href,\'_blank\',\'width=2000,height=500,top=200,left=200\'); return false;\">상세</a></td>';
				orderDashTbody += '</tr>';
			}
			$("#orderDashTbody").append(orderDashTbody);
		},
		error: function() {
			alert("실패");
		}
	});
}

//==========================================================================================================================
//검색조건에 따라 상품갯수카운트하고 페이지버튼생성
function ordPagenation() {
	let division = $("#ord_division").val();
	let searchValue = $("#order_cd_id").val();
	let order_stat_cd = $("input[name='order_stat_cd']:radio:checked").val();
	let order_date1 = $("#order_datepicker1").val().replaceAll("-","");
	let order_date2 = $("#order_datepicker2").val().replaceAll("-","");
	
	let condition = { "division": division, 
					  "searchValue": searchValue, 
					  "order_stat_cd": order_stat_cd,
					  "order_date1": order_date1,
					  "order_date2": order_date2 };

	$.ajax({
		cache: false,
		url: "orderPagenation.do",
		type: 'get',
		data: condition,
		success: function(pageCount) {
			//페이징버튼 그려줄 태그의 선택자
			let paging = $("#orderSearchPageNumber");
			paging.empty();
			//1,2,3 페이지 생성
			for (let i = 1; i <= pageCount; i++) {
				paging.append('<li class=\"page-item\"><a class=\"page-link\" href=\"javascript:void(0)\">' + i + '</a></li>');
			}
			paging.find('li:nth-child(1)').addClass('active');
			orderSearch(1); //바로 첫번째 페이지 그려줌

			//상품 페이지 1,2,3...클릭시 active효과주고 검색
			paging.find('li').click(function() {
				paging.find('li').removeClass('active');
				$(this).addClass('active');
				let index = $(this).text();
				orderSearch(index);
			});
		},
		error: function() {
			alert("실패");
		}
	});
}

//==========================================================================================================================
//검색조건에따라 주문테이블 그리기
function orderSearch(index) {
	let division = $("#ord_division").val();
	let searchValue = $("#order_cd_id").val();
	let order_stat_cd = $("input[name='order_stat_cd']:radio:checked").val();
	let order_date1 = $("#order_datepicker1").val().replaceAll("-","");
	let order_date2 = $("#order_datepicker2").val().replaceAll("-","");

	let condition = { "index": index,
					  "division": division, 
					  "searchValue": searchValue, 
					  "order_stat_cd": order_stat_cd,
					  "order_date1": order_date1,
					  "order_date2": order_date2 };

	$.ajax({
		cache: false,
		url: "searchOrder.do",
		type: 'post',
		data: condition,
		dataType: 'json',
		success: function(data) {
			$("#ordSearchTbody").empty();
			let ordSearchTbody = '';
			for (key in data) {
				ordSearchTbody += '<tr class="trow">';
				ordSearchTbody += '<td>' + data[key].no + '</td>';
				ordSearchTbody += '<td>' + data[key].ord_cd + '</td>';
				ordSearchTbody += '<td>' + data[key].ord_date + '</td>';
				ordSearchTbody += '<td>' + data[key].ord_stat_name + '</td>';
				ordSearchTbody += '<td>' + '<a href=\"updateOrderForm.do?ord_cd=' + data[key].ord_cd + '\" onclick=\"window.open(this.href,\'_blank\',\'width=2000,height=500,top=200,left=200\'); return false;\">수정</a></td>';
				ordSearchTbody += '</tr>';
			}
			$("#ordSearchTbody").append(ordSearchTbody);
		},
		error: function() {
			alert("실패");
		}
	});
}

//==========================================================================================================================
//홈대시보드 오늘 이번달 매출 건수 구하여 나타내기
function homeDashCount(){
		$.ajax({
		cache: false,
		url: "homeDashInfo.do",
		type: "post",
		dataType: 'json',
		success: function(data) {
			$("#homeDashTbody").empty();
			let homeDashTbody = '';
			homeDashTbody += '<tr class="trow">';
			homeDashTbody += '<td>' + data.countPrice1.toLocaleString('ko-KR') +'원 (' + data.countOrder1 + '건)' +'</td>';
			homeDashTbody += '<td>' + data.countPrice2.toLocaleString('ko-KR') +'원 (' + data.countOrder2 + '건)' +'</td>';
			homeDashTbody += '</tr>'
			$("#homeDashTbody").append(homeDashTbody);
		}
	});
} 

function homeDashPeriod(){
	
		let date1 = $("#home_datepicker1").val().replaceAll("-","");
		let date2 = $("#home_datepicker2").val().replaceAll("-","");
		
		let date = { "date1": date1, "date2": date2}; 
	
		$.ajax({
		cache: false,
		url: "homeDashPeriod.do",
		type: "post",
		data: date,
		dataType: 'json',
		success: function(data) {
			$("#homeDashPeriod").empty();
			let homeDashPeriod = '';
			homeDashPeriod += '<div class="period">'+ data.countPrice.toLocaleString('ko-KR') +'원 ('+ data.countOrder + '건)' + '</div>';
			$("#homeDashPeriod").append(homeDashPeriod);
		}
	});
} 
