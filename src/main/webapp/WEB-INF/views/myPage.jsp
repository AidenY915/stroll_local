<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>더보기</title>
    <link rel="icon" href="pages/images/favicon.png" />
    <link rel="stylesheet" href="pages/css/frameCss.css" />
    <script
      src="https://code.jquery.com/jquery-3.7.1.min.js"
      integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
      crossorigin="anonymous"
    ></script>
    <script src="pages/js/footer.js"></script>
  </head>
  <body>
  <%@include file = "gnb.jsp"%>
    <header>
      <div class="container">
        <h1>공지사항</h1>
      </div>
    </header>
    <div class="container">
      <aside class="sideLnb">
        <ul>
          <li>내가 찜한 장소</li>
          <li>내 리뷰</li>
          <li>내 장소</li>
        </ul>
      </aside>
      <section class="content">
      </section>
    </div>
  </body>
</html>
