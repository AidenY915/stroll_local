<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<c:if test = "${sessionScope.id != null}">
		$('#gnb>li:eq(2)').innerText = "로그아웃"		
	</c:if>	
</script>
</body>
</html>