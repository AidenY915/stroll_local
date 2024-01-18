<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 장소</title>
<link rel='stylesheet' href="pages/css/frameCss.css">
<link rel='stylesheet' href="pages/css/newPlace/newPlace.css">
<script
      src="https://code.jquery.com/jquery-3.7.1.min.js"
      integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
      crossorigin="anonymous"
    ></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src = "pages/js/newPlace/newPlaceAddress.js"></script>
</head>
<body>
	<%@include file="gnb.jsp"%>
	<form class="container" action='insertPlace' method='post'
		enctype="multipart/form-data">
		<section class="about">
			<div class="imgSlider">
				<input type='file' name='placeImg'> <img
					class="displayedImg" />
				<section class="splide" aria-label="Splide Basic HTML Example">
					<div class="splide__track">
						<ul class="splide__list"></ul>
					</div>
				</section>
			</div>
			<article>
				<h2>
					장소 이름 : <input name="title" type="text">
				</h2>
				<div>
					카테고리 : <select name="category">
						<option value="pension">반려견 펜션</option>
						<option value="cafe">카페</option>
						<option value="grooming">미용</option>
						<option value="hospital">동물병원</option>
						<option value="playground">놀이터</option>
						<option value="kindergarden">반려견 유치원</option>
						<option value="etc">기타</option>
					</select>
				</div>
				<p>
					<span>주소</span> <input type="text" id="sample4_postcode"
						placeholder="우편번호"> <input type="button"
						onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
					<input type="text" id="sample4_roadAddress" name = "address" placeholder="도로명주소">
					<span id="guide" style="color: #999; display: none"></span> <input
						type="text" id="sample4_detailAddress" name = "detailAddress" placeholder="상세주소">
					<input type="text" id="sample4_extraAddress" placeholder="참고항목">
				</p>
				<p>
					설명 :
					<textarea name='content'></textarea>
				</p>
			</article>
		</section>
		<section class="detailInfo"></section>
		<button>등록</button>
	</form>
</body>
</html>