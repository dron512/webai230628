<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.0.js"
	integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
	crossorigin="anonymous">
</script>
<script>
	// 아이디 중복 검사 여부
	var checkDuplication = false;

	function CheckId() {
		// 아이디 이력란에 입력한 데이터 저장
		var loginId = $('#member_loginId').val();
		if (loginId == '') {
			alert('아이디를 입력해 주세요.');
			$('#member_loginId').focus();
			return; // 함수 종료
		}

		// 아이디 유효성 검사
		// 영문자로 시작하는 영문자 또는 숫자 6~20자 
		var reg = /^[a-z0-9]{5,19}$/;
		if (!reg.test(loginId)) {
			alert('아이디는 영문자 또는 숫자를 사용해  6~20자 입력해주세요.');
			$('#member_loginId').focus();
			return;
		}

		// 비동기통신을 통해 아이디 존재 여부 확인
		$.ajax({
			url : "http://localhost:8080/member/checkId",
			type : "POST",
			data : {
				loginId : loginId
			},
			error : function(e) {
				console.log(e);
				alert(e.statusText + '서버와 통신하는데 실패하였습니다.');
			},
			success : function(result) {
				console.log("result = " + result);
				if (result == "success") {
					alert('사용할 수 있는 아이디입니다.');
					checkDuplication = true;
				} else {
					alert('이미 존재하는 아이디입니다.');
					$('#member_loginId').focus();
				}
			}
		});
	}

	function initCheck() {
		checkDuplication = false;
	}
	
	function is_checked(){
		var checkbox_rg = document.getElementById('member_category_regular');
		var checkbox1_cp = document.getElementById('member_category_company');
		if(!checkbox_rg.checked && !checkbox1_cp.checked){
			alert('가입유형을 체크해 주세요.');
			return;
		}
	}

	function joinMember() {
		var loginId = $('#member_loginId');
		var password = $('#member_password');
		var passwordCheck = $('#member_password_check');
		var name = $('#member_name');
		var tel = $('#member_tel');
		var email = $('#member_email');

		// 아이디 입력 여부 확인
		if (loginId.val() == '') {
			alert('아이디를 입력해 주세요.');
			$('#member_loginId').focus();
			return;
		}

		// 비밀번호 입력 여부 확인
		if (password.val() == '') {
			alert('비밀번호를 입력해 주세요.');
			$('#member_password').focus();
			return;
		}

		// 비밀번호확인 입력 여부 확인
		if (passwordCheck.val() == '') {
			alert('비밀번호확인을 입력해 주세요.');
			$('#member_password_check').focus();
			return;
		}

		// 성명 입력 여부 확인
		if (name.val() == '') {
			alert('성명을 입력해 주세요.');
			$('#member_name').focus();
			return;
		}

		// 전화번호 입력 여부 확인
		if (tel.val() == '') {
			alert('전화번호를 입력해 주세요.');
			$('#member_tel').focus();
			return;
		}

		// 이메일 입력 여부 확인
		if (email.val() == '') {
			alert('이메일을 입력해 주세요.');
			$('#member_email').focus();
			return;
		}

		// 가입유형 입력 여부 확인
		is_checked();

		// 아이디 유효성 검사
		// 5 ~ 19자 영문, 숫자를 조합
		var reg = /^[a-z0-9]{5,19}$/;
		if (!reg.test(loginId.val())) {
			alert('아이디는 영문자 또는 숫자를 사용해  6~20자 입력해 주세요.');
			$('#member_loginId').focus();
			return;
		}

		// 비밀번호 유효성 검사
		// 8 ~ 16자 영문, 숫자, 특수문자를  조합
		var reg = /^[A-Za-z0-9!@#$]{8,16}$/;
		console.log(reg.test(password.val()));
		if (!reg.test(password.val())) {
			alert('비밀번호는 영문, 숫자, 특수문자를 최소 한가지씩 조합하여 8~16자 입력해 주세요.');
			$('#member_password').focus();
			return;
		}

		// 성명 유효성 검사
		var reg = /^[가-힣]{2,10}$/;
		if (!reg.test(name.val())) {
			alert('성명은 2~10자의 한글로 입력해 주세요.');
			$('#member_name').focus();
			return;
		}

		// 전화번호 유효성 검사
		// 3자리-3~4자리-4자리
		var reg = /^[0-9]{10,12}$/;
		if (!reg.test(tel.val())) {
			alert('전화번호를 입력해 주세요.');
			$('#member_tel').focus();
			return;
		}

		// 이메일 유효성 검사
		var reg = /^.{1,50}$/;
		if (!reg.test(email.val())) {
			alert('이메일은  @포함하여 입력해 주세요.');
			$('#member_email').focus();
			return;
		}

		if (!checkDuplication) {
			alert('아이디 중복 체크를 진행해 주세요.');
			return;
		}

		if (password.val() != passwordCheck.val()) {
			alert('비밀번호와 동일하게 입력해 주세요.');
			return;
		}

		$('#joinForm').submit();
	}
</script>
</head>
<body>
	<form action="/member/join" id="joinForm" method="post">
		<div>
			아이디: <input type="text" id="member_loginId" name="member_loginId"
				oninput="initCheck()">
			<button type="button" onclick="CheckId()">중복확인</button>
		</div>
		<div>
			비밀번호: <input type="password" id="member_password" name="member_password">
		</div>
		<div>
			비밀번호 확인: <input type="password" id="member_password_check" name="member_password_check">
		</div>
		<div>
			이름: <input type="text" id="member_name" name="member_name">
		</div>
		<div>
			전화번호: <input type="text" id="member_tel" name="member_tel">
		</div>
		<div>
			이메일: <input type="text" id="member_email" name="member_email">
		</div>
		<div>
			가입 유형 <br> <input type="checkbox" id="member_category_regular" name="member_category"
				value="regular"> 개인 <input type="checkbox" id="member_category_company" name="member_category" value="company"> 기업
		</div>
	</form>
	<button type="button" onclick="joinMember()">가입</button>
	<button onclick="location.href='/'">취소</button>
</body>
</html>