<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Detail</title>
    <link rel="icon" href="../images/favicon.png" />
    <!-- splider css -->
    <link
      href="
https://cdn.jsdelivr.net/npm/@splidejs/splide@4.1.4/dist/css/splide.min.css
"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="pages/css/gnb.css" />
    <link rel="stylesheet" href="pages/css/frameCss.css" />
    <link rel="stylesheet" href="pages/css/detail/detail.css" />
    <link rel="stylesheet" href="pages/css/login/login.css" />
    <!-- jQuery -->
    <script
      src="https://code.jquery.com/jquery-3.7.1.min.js"
      integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
      crossorigin="anonymous"
    ></script>
    <!-- jQuery UI -->
    <script
      src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"
      integrity="sha256-lSjKY0/srUM9BE3dPm+c4fBo1dky2v27Gdjm2uoZaL0="
      crossorigin="anonymous"
    ></script>
    <!-- splider -->
    <script src="
https://cdn.jsdelivr.net/npm/@splidejs/splide@4.1.4/dist/js/splide.min.js
"></script>
    <script src="pages/js/gnb.js"></script>
    <script src="pages/js/footer.js"></script>
    <script src="pages/js/login.js"></script>
    <script src="pages/js/detail/addReview.js"></script>
    <script src="pages/js/detail/splide.js"></script>
  </head>
  <body>
    <div class="container">
      <section class="about">
        <div class="imgSlider">
          <img class="displayedImg" />
          <section class="splide" aria-label="Splide Basic HTML Example">
            <div class="splide__track">
              <ul class="splide__list"></ul>
            </div>
          </section>
        </div>
        <article>
          <h2>${place.title}</h2>
          <p>별점</p>
          <p><span>${place.address} ${place.detailAddress}</span></p>
          <p>${place.content}</p>
        </article>
      </section>
      <section class="detailInfo"></section>
      <section class="reviewSection">
        <form class="commentWritingForm">
          <select class>
            <option value="1">★</option>
            <option value="2">★★</option>
            <option value="3" selected>★★★</option>
            <option value="4">★★★★</option>
            <option value="5">★★★★★</option>
          </select>
          <textarea></textarea>
          <button>등록</button>
        </form>
        <ul class="reviews"></ul>
      </section>
    </div>
  </body>
</html>
