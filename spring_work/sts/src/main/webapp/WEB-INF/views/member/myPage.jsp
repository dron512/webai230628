<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script src="https://code.jquery.com/jquery-3.7.0.js"
	integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
	crossorigin="anonymous">
</script>
<script type="text/javascript">
	function updateMember(){
		var updateConfirm = confirm('수정하시겠습니까?');
		if(updateConfirm){
			$('#myPage').submit();
		}
	}
	
	function deleteMember(){
		var deleteConfirm = confirm('탈퇴를 하시겠습니까?');
		if(deleteConfirm){
			alert('${res.member_idx}');
			location.href="/member/delete?idx=" + ${res.member_idx};
		}
	}
</script>
</head>
<body>
	<h1>마이페이지</h1>
	<form id="myPage" action="/member/update">
		<input type="hidden" value="${res.member_idx}" id="member_idx" name="member_idx" />
		<div>
			<label>아이디</label> <input type="text" value="${res.member_loginId}"
				name="member_loginId" readonly>
		</div>
		<div>
			<label>성명</label> <input type="text" value="${res.member_name}"
				name="member_name" disabled>
		</div>
		<div>
			<label>전화번호</label> <input type="text" value="${res.member_tel}"
				name="member_tel">
		</div>
		<div>
			<label>이메일</label> <input type="text" value="${res.member_email}"
				name="member_email">
		</div>
		<div>
			<label>가입유형</label> 
			<c:set var="category" value="${res.member_category}" />
			<c:choose>
				<c:when test="${category eq 'regular'}">
					<input type="checkbox" value="regular" name="member_category" checked disabled/>일반
					<input type="checkbox" value="company" name="member_category" disabled>기업
				</c:when>
				<c:otherwise>
					<input type="checkbox" value="regular" name="member_category" disabled/>일반
					<input type="checkbox" value="company" name="member_category" checked disabled/>기업
				</c:otherwise>
			</c:choose>
		</div>
	</form>
	<button type="button" onclick="updateMember()">수정</button>
	<button type="button" onclick="deleteMember()">탈퇴</button>
	<button type="button" onclick="location.href='/'">홈으로</button>
</body>
</html>