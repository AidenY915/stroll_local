<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>더보기</title>
<link rel="icon" href="pages/images/favicon.png" />
<link rel="stylesheet" href="pages/css/frameCss.css" />
<link rel="stylesheet" href="pages/css/myPage/myPage.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<script src="pages/js/myPage/myPage.js">
	<script src="pages/js/footer.js">
</script>
</head>
<body>
	<%@include file="gnb.jsp"%>
	<header>
		<div class="container">
			<h1>마이페이지</h1>
		</div>
	</header>
	<div class="container">
		<aside class="sideLnb">
			<ul>
				<li><a href="mypage?list=wishList">내가 찜한 장소</a></li>
				<li><a href="mypage?list=myPlaces">내 리뷰</a></li>
				<li><a href="mypage?list=reviews">내 장소</a></li>
				<li id="withdrawLi">회원탈퇴</li>
			</ul>
		</aside>
		<section class="content">
			<h3>
				<c:choose>
					<c:when test="${param.list==null || param.list=='wishList'}">내가 찜한 장소</c:when>
					<c:when test="${param.list==null || param.list=='myPlaces'}">내 장소</c:when>
					<c:when test="${param.list==null || param.list=='reviews'}">내 리뷰</c:when>
				</c:choose>
			</h3>
			<hr/>
			<c:if test="${places != null}">
				<ul class="results">
					<c:forEach var="place" items="${places}">
						<li><a href='detail?no=${place.no}'> <img
								class="placeImg" src="resources/upload/imgs/${place.no}_1.jpg"
								onerror=this.src="http://placehold.it/180x240">
								<div>
									<p>
										<span class="placeName">${place.title}</span>
										<c:if test="${param.x != null && param.x!=0.0}">
											<span class="distance">${place.distance}m</span>
										</c:if>
									</p>
									<p>${place.address}${place.detailAddress}</p>
									<p class="star">★ ${place.star}</p>
								</div>
						</a></li>
					</c:forEach>
				</ul>
			</c:if>
			<c:if test="${reviews != null}"></c:if>
		</section>
	</div>
</body>
</html>
