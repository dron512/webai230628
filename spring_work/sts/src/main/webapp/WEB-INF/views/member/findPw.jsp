<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/member/findPwPro" method="post">
	<div>
		<label>아이디</label>
		<input type="text" name="member_loginId">
	</div>
	<div>
		<label>이메일</label>
		<input type="text" name="member_email">
	</div>
	<button type="submit">찾기</button>
</form>
</body>
</html>