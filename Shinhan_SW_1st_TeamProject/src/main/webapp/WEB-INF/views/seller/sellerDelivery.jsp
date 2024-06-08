<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매자_판매&배송</title>
<!-- 본문 css -->
    <link rel="stylesheet" href="${path}/resources/css/style.css">
     <!-- 헤더,푸터 css -->
    <link rel="stylesheet" href="${path}/resources/css/header_footer.css">
    <!-- jquery 연결 -->
    <script src="${path}/resources/js/jquery-3.7.1.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!-- Slick 불러오기 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.min.css">
    <!-- Slick 직접 연결 -->
    <link rel="stylesheet" href="${path}/resources/slick/slick-theme.css">
    <link rel="stylesheet" href="${path}/resources/slick/slick.css">
    <!-- 페이지용 css -->
    <link rel="stylesheet" href="${path}/resources/css/seller_Delivery.css" />
    <script src="${path}/resources/slick/slick.min.js"></script>
</head>
<body>
<main>
      <aside>
        <ul>
          <li><a href="#">통계</a></li>
          <li><a href="#">판매 상품 목록</a></li>
          <li><a href="#">대여 상품 목록</a></li>
          <li><a href="#">주문/배송</a></li>
          <li><a href="#">문의 목록</a></li>
        </ul>
      </aside>
      <section>
        <h2>마이페이지</h2>
        <hr />
        <div class="user-info">
          <p>김철수</p>
          <p>573-50-00882</p>
        </div>
        <!-- 주문/배송 -->
        <div class="product-list">
          <h3>주문/배송</h3>
          <table>
            <thead>
              <tr>
                <th></th>
                <th>주문ID</th>
                <th>결제금액(원)</th>
                <th>주문상태</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td><input type="checkbox" /></td>
                <td><a href="#">1187</a></td>
                <td>78000</td>
                <td>결제대기</td>
              </tr>
              <tr>
                <td><input type="checkbox" /></td>
                <td><a href="#">1188</a></td>
                <td>78000</td>
                <td>결제대기</td>
              </tr>
              <tr>
                <td><input type="checkbox" /></td>
                <td><a href="#">1189</a></td>
                <td>78000</td>
                <td>결제대기</td>
              </tr>
              <tr>
                <td><input type="checkbox" /></td>
                <td><a href="#">1190</a></td>
                <td>78000</td>
                <td>결제대기</td>
              </tr>
              <tr>
                <td><input type="checkbox" /></td>
                <td><a href="">1191</a></td>
                <td>78000</td>
                <td>결제대기</td>
              </tr>
            </tbody>
          </table>
          <div class="actions">
            <select>
              <option>결제대기</option>
              <option>결제완료</option>
              <option>배송준비중</option>
              <option>배송중</option>
              <option>배송완료</option>
            </select>
            <button>일괄처리</button>
            <button>선택 내역 삭제</button>
          </div>
        </div>
      </section>
      <aside class="notifications">
        <div class="icon">
          <span>15</span>
        </div>
        <div class="popup">
          <p>예약한 공용공간에서 대기 중입니다.</p>
          <p>공용공간 3층 카운터에서 확인하세요.</p>
          <p>문의사항은 전화로 연락 바랍니다. 02-0000-0000</p>
        </div>
      </aside>
    </main>
</body>
</html>