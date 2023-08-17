<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/member/login" method="post">
	<p>아이디 : <input type="text" name="member_loginId">
	<p>비밀번호 : <input type="password" name="member_password">
	<button type="submit">로그인</button>
</form>
<button type="button" onclick="location.href='/member/findId'">아이디 찾기</button>
<button type="button" onclick="location.href='/member/findPw'">비밀번호 찾기</button>
</body>
</html>