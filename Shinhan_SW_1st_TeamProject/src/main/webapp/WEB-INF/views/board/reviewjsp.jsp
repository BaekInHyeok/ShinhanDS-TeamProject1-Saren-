<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Header</title>
<%-- css --%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
<%-- ���� css --%>
<link rel="stylesheet" href="${path}/resources/css/style.css">
<%-- ���,Ǫ�� css --%>
<link rel="stylesheet" href="${path}/resources/css/header_footer.css">
<%-- jquery ���� --%>
<script src="${path}/resources/js/jquery-3.7.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .inner {
            width: 80%;
            margin: 0 auto;
        }

        .review-container {
            margin-top: 40px;
            display: flex;
        }

        .sidebar {
            width: 150px;
            margin-right: 20px;
        }

        .sidebar ul {
            list-style: none;
            padding: 0;
        }

        .sidebar ul li {
            margin: 10px 0;
        }

        .sidebar ul li a {
            text-decoration: none;
            color: #000;
            font-size: 16px;
        }

        .review-content {
            flex-grow: 1;
            margin-left: 20px;
        }

        .review-header {
            text-align: center;
            border-bottom: 2px solid #000;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }

        .review-header h2 {
            margin: 0;
            font-size: 20px;
            font-weight: bold;
        }

        .review-form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .review-form label {
            width: 100%;
            margin-bottom: 10px;
        }

        .review-form label span {
            display: inline-block;
            width: 100px;
            font-weight: bold;
        }

        .review-form input[type="text"],
        .review-form textarea {
            width: calc(100% - 110px);
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
        }

        .review-form textarea {
            height: 100px;
        }

        .review-form .rating {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }

        .review-form .rating span {
            font-weight: bold;
            margin-right: 10px;
        }

        .review-form .rating input[type="radio"] {
            margin-right: 5px;
        }

        .review-form img {
            width: 200px;
            height: auto;
            margin-bottom: 10px;
        }

        .review-form button {
            background-color: #6a0dad;
            color: #fff;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
        }

        .review-form button:hover {
            background-color: #5a0cac;
        }
    </style>
</head>

<body>
<header>
	<div class="header_top inner">
		<div class="leftGnb">
			<h1 class="logo">
				<a href="${path}/">
					<img src="${path}/resources/images/logo_main.png" alt="�žȷΰ�">
				</a>
			</h1>
			<%-- ��ǰ �˻�â --%>
			<form action="${path}/" class="search_form">
				<label for="search_wrap"> 
					<input name="search_input" type="search" /> 
					<img src="${path}/resources/images/icon_serch.png" alt="������ ������" class="search_img" />
				</label>
			</form>
		</div>
		<div class="rightGnb">
			<ul>
				<li>
					<a href="${path}/user/login"> <img src="${path}/resources/images/icon-login.gif" alt="�α���">�α���</a>
				</li>
				<li>
					<a href="${path}/user/signup"><img src="${path}/resources/images/icon-sign-in.png" alt="ȸ������">ȸ������</a>
				</li>
				<li>
					<a href="${path}/customer/myPage.do"><img src="${path}/resources/images/icon-user.png" alt="����������">����������</a>
					<%-- ���� �亯���� ��Ÿ���� �˸� --%>
					<%-- <span class="hidden">N</span> --%>
				</li>
				<li>
					<a href="${path}/cart/cart"><img src="${path}/resources/images/icon-shopping.png" alt="��ٱ���">��ٱ���</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="header_bottom">
		<div class="menu_wrap inner">
			<%-- ���� �޴� ī�װ� --%>
			<ul class="left_menu">
				<li data-menu="0">
					<a href="${path}/customer/productlist"
					class="category_name">����</a>
					</li>
				<li data-menu="1">
					<a href="${path}/customer/productlist"
					class="category_name">����</a>
				</li>
				<li data-menu="2">
					<a href="${path}/customer/productlist"
					class="category_name">Ű��</a>
					</li>
				<li data-menu="3">
					<a href="${path}/customer/productlist"
					class="category_name">���Ÿ�</a>
					</li>
				<li data-menu="4">
					<a href="${path}/customer/productlist"
					class="category_name">Ű��</a>
				</li>
				<li data-menu="5">
					<a href="${path}/customer/productlist"
					class="category_name">������</a>
					</li>
				<li data-menu="6">
					<a href="${path}/customer/productlist"
					class="category_name">����&�Ź�</a>
				</li>
			</ul>
			<%-- ������ �޴� --%>
			<ul class="right_menu">
				<li><a href="javascript:#void" class="highlight">AI��õ�ڵ�</a></li>
				<li><a href="${path}/customer/productlist" class="highlight">�ʴ뿩</a></li>
				<li><a href="${path}/board/qna">Q&A</a></li>
				<li><a href="{path}/board/board">��������</a></li>
			</ul>
		</div>
		<%-- ���� �޴� �κ�  --%>
		<div class="menu_pan inner">
			<div class="dropdown_nav">
				<ul>
					<li><a href="#">�Ż�ǰ</a></li>
					<li><a href="#">��ü ��ǰ</a></li>
					<li><a href="#">�ƿ���</a></li>
					<li><a href="#">��Ŷ/����Ʈ</a></li>
					<li><a href="#">��Ʈ</a></li>
					<li><a href="#">����/���콺</a></li>
					<li><a href="#">Ƽ����</a></li>
					<li><a href="#">���ǽ�</a></li>
					<li><a href="#">����</a></li>
					<li><a href="#">��ĿƮ</a></li>
					<li><a href="#">���/�ð�</a></li>
				</ul>
				<ul>
					<li><a href="#">�Ż�ǰ</a></li>
					<li><a href="#">��ü ��ǰ</a></li>
					<li><a href="#">����</a></li>
					<li><a href="#">�ƿ���</a></li>
					<li><a href="#">��Ŷ/����Ʈ</a></li>
					<li><a href="#">��Ʈ</a></li>
					<li><a href="#">����/���콺</a></li>
					<li><a href="#">Ƽ����</a></li>
					<li><a href="#">���ǽ�</a></li>
					<li><a href="#">����</a></li>
				</ul>
				<ul>
					<li><a href="#">�Ż�ǰ</a></li>
					<li><a href="#">��ü ��ǰ</a></li>
					<li><a href="#">����</a></li>
					<li><a href="#">����</a></li>
				</ul>
				<ul>
					<li><a href="#">�Ż�ǰ</a></li>
					<li><a href="#">��ü ��ǰ</a></li>
					<li><a href="#">�����Ƿ�</a></li>
					<li><a href="#">��������/����</a></li>
					<li><a href="#">��������</a></li>
					<li><a href="#">���� ���/�ð�</a></li>
					<li><a href="#">�����Ƿ�</a></li>
					<li><a href="#">��������/����</a></li>
					<li><a href="#">��������</a></li>
					<li><a href="#">���۶�/�Ȱ���</a></li>
				</ul>
				<ul>
					<li><a href="#">�Ż�ǰ</a></li>
					<li><a href="#">��ü ��ǰ</a></li>
					<li><a href="#">�ƿ���</a></li>
					<li><a href="#">��Ŷ/����Ʈ</a></li>
					<li><a href="#">��Ʈ</a></li>
					<li><a href="#">����/���콺</a></li>
					<li><a href="#">Ƽ����</a></li>
					<li><a href="#">���ǽ�</a></li>
					<li><a href="#">����</a></li>
					<li><a href="#">��ĿƮ</a></li>
					<li><a href="#">���/�ð�</a></li>
				</ul>
				<ul>
					<li><a href="#">�Ż�ǰ</a></li>
					<li><a href="#">��ü ��ǰ</a></li>
					<li><a href="#">�ƿ�����/ķ��</a></li>
					<li><a href="#">��Ʈ�Ͻ�</a></li>
				</ul>
				<ul>
					<li><a href="#">�Ż�ǰ</a></li>
					<li><a href="#">��ü ��ǰ</a></li>
					<li><a href="#">���� ����</a></li>
					<li><a href="#">���� ����</a></li>
					<li><a href="#">���� ����</a></li>
					<li><a href="#">���� ����</a></li>
				</ul>
			</div>
		</div>
	</div>
</header>
<script>
	$(function() {
		//$.noConflict(); //�������� �浹����
		//�޴� ��� �ٿ�
		var isFirstHover = true; // ó�� ���콺�� �ø����� ���θ� ����

		$(".left_menu > li").on("mouseenter", function() {
			var menuIndex = $(this).data("menu"); // data-menu �Ӽ� ���� ������

			if (isFirstHover) {
				$(".dropdown_nav ul").stop().animate({
					height : "0"
				}, 500); // ��� ����޴� ���� 0���� ����
				$(".dropdown_nav ul").eq(menuIndex).stop().animate({
					height : "400px"
				}, 500);
				$(".dropdown_nav").stop().animate({
					height : "400px"
				}, 500); // ����޴� ǥ��
				isFirstHover = false; // ���� ���콺 �̵� �� �ִϸ��̼� ����
			} else {
				$(".dropdown_nav ul").css("height", "0"); // ��� ����޴� ���� 0���� ����
				$(".dropdown_nav ul").eq(menuIndex).css("height", "400px");
				$(".dropdown_nav").css("height", "400px"); // ����޴� ǥ��
			}
		});

		$(".menu_pan").on("mouseleave", function() {
			// ���콺�� ������ �� ����޴� ���� �ʱ�ȭ
			$(".dropdown_nav ul").css("height", "0");
			$(".dropdown_nav").css("height", "0");
			isFirstHover = true; // ���콺�� ������ �� �ٽ� �ִϸ��̼� Ȱ��ȭ
		});

	});
</script>

    <div class="review-container inner">
        <div class="sidebar">
            <ul>
                <li><a href="#">���� �ֹ�</a></li>
                <li><a href="#">���� �뿩</a></li>
                <li><a href="#">��ٱ���</a></li>
                <li><a href="#">���� ��</a></li>
                <ul>
                    <li><a href="#">���Ǳ�</a></li>
                    <li><a href="#">����</a></li>
                </ul>
                <li><a href="#">ȸ������</a></li>
                <ul>
                    <li><a href="#">���� ����</a></li>
                    <li><a href="#">ȸ�� Ż��</a></li>
                </ul>
            </ul>
        </div>
        <div class="review-content">
            <div class="review-header">
                <h2>���� ����</h2>
            </div>
            <div class="review-form">
                <label>
                    <span>��ǰ��/�ɼ�</span>
                    <input type="text" name="product_name" placeholder="��ǰ��/�ɼ��� �Է��ϼ���">
                </label>
                <label>
                    <span>�ۼ�����</span>
                    <input type="text" name="review_date" placeholder="2024.05.29" disabled>
                </label>
                <label>
                    <span>����</span>
                    <input type="file" name="image">
                </label>
                <img src="../images/tshirt.jpg" alt="Ƽ���� �̹���">
                <label>
                    <span>����</span>
                    <div class="rating">
                        <input type="radio" name="rating" value="1"> 1
                        <input type="radio" name="rating" value="2"> 2
                        <input type="radio" name="rating" value="3"> 3
                        <input type="radio" name="rating" value="4"> 4
                        <input type="radio" name="rating" value="5" checked> 5
                    </div>
                </label>
                <label>
                    <span>����</span>
                    <textarea name="content" placeholder="������ �Է��ϼ���">���밨�� �ʹ� ���� ���� �ִ� ������� ��� �԰� �ٴ� �� ���ƿ�! ������õ�մϴ�~~</textarea>
                </label>
                <button>����ϱ�</button>
            </div>
        </div>
    </div>
</body>

</html>
    