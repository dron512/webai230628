<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/login" method="post">
	<p>아이디 : <input type="text" name="username">
	<p>비밀번호 : <input type="password" name="password">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<button type="submit">로그인</button>
</form>
<button type="button" onclick="location.href='/member/findId'">아이디 찾기</button>
<button type="button" onclick="location.href='/member/findPw'">비밀번호 찾기</button>
</body>
</html>