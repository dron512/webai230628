<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/member/updatePwPro" method="post">
	<div>
		<label>새로운 비밀번호</label>
		<input type="text" id="member_password" name="member_password">
	</div>
	<div>
		<label>새로운 비밀번호 확인</label>
		<input type="text" name="member_passwordCheck">
	</div>
	<button type="submit">변경</button>
</form>
</body>
</html>