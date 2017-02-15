<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DMS</title>
    <link rel="stylesheet" href="default.css">
    <!--css파일 위치로 수정필요-->
</head>

<body>
    <header>
        <div class="logo" style="color: white">
            <p>로고</p>
            <!--수정필요-->
        </div>
    </header>
    <nav class="remote">
        <div class="inner">
          <h1><a href="index.html">DSM</a></h1>
          <div class="category">
              <a href="#">
                  <p>신청</p>
              </a>
              <div class="application children">
                  <a href="default_extention.html">연장신청</a>
                  <a href="../go_home_apply.html">귀가신청</a>
                  <a href="../go_out_apply.html">외출신청</a>
                  <a href="default_point.html">상점신청</a>
                  <a href="default_after">방과후신청</a>
              </div>
              <a href="#">
                  <p>기숙사</p>
              </a>
              <div class="dom children">
                  <a href="default_dorm_main.html">공지사항</a>
                  <a href="default_dorm_faq.html">FAQ</a>
                  <a href="default_dorm_rule.html">기숙사규칙</a>
                  <a href="default_dorm_qna.html">문의하기</a>
              </div>
              <a href="#">
                  <p>마이페이지</p>
              </a>
          </div>
        </div>
    </nav>
    <div class="main">
        <div class="frame left articlecontainer">
          <div class="frametitle">
              <h2>${title}</h2>
              <p>${date}</p>
              <div class="underline puple">
              </div>
          </div>
          <div class="article">
              ${content}
          </div>
          <hr>
        </div>
        <!-- <div class="frame right">
            <div class="frametitle">
                <h1>Right Frame</h1>
                <div class="underline"></div>
            </div>
        </div> -->
    </div>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript" src="../js/remote.js"></script>
    <!--remote.js로 수정필요-->
</body>

</html>
