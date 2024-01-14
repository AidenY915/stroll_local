<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="pages/css/gnb.css" />
<script src="pages/js/gnb.js"></script>
</head>
<body>
	<script>
	const logout = function() {
		location.assign("logout");
	}
	
	$(function() {
	const $loginA = $('#gnb li:eq(1) > a'); 
	<c:if test = "${sessionScope.id != null}">
		$loginA.text("로그아웃");
		$loginA.attr("onclick", "logout()");
	</c:if>	
	});
</script>
</body>
</html>
