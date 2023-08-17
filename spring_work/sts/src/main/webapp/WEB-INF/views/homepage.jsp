<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	HttpSession s = request.getSession();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	if(s.getAttribute("res") != null){
%>
	<a href="/member/myPage">마이페이지</a>
	<a href="/logout">로그아웃</a>
<%
	}else{
%>
	<a href="/login">로그인</a>
	<a href="/member/joinPage">회원가입</a>
<%
	} 
%>
</body>
</html>