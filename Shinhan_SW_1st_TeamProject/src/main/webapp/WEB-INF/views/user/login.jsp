<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인 페이지</title>
    <script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #ffffff;
            color: #fff;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .login-container {
            background-color: #ffffff;
            color: #000;
            width: 400px;
            padding: 20px;
            border-radius: 10px;
            margin: 50px 0;
            text-align: center;
        }
        .login-container h1 {
            margin-bottom: 20px;
        }
        .login-container .tab {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }
        .login-container .tab div {
            padding: 10px 80px;
            cursor: pointer;
        }
        .login-container .tab .active {
            border-bottom: 2px solid #000;
        }
        .login-container form {
            display: none;
        }
        .login-container form.active {
            display: block;
        }
        .login-container .input-group {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 10px;
        }
        .login-container .input-fields {
            display: flex;
            flex-direction: column;
            width: 260px; /* 버튼 간격 조정 */
        }
        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .login-container input[type="checkbox"] {
            margin-right: 5px;
        }
        .login-container .login-btn {
            background-color: #000;
            color: #fff;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            height: 87px; /* 높이를 맞춰줍니다 */
            width: 100px;
            margin-left: 0px; /* 버튼 간격 조정 */
        }
        .login-container .login-btn:hover {
            background-color: #444;
        }
        .login-container .links {
            display: flex;
            justify-content: space-between;
            margin-top: 30px;
            padding: 30px;
            border: none;
            background-color: #D9D9D9;
        }
        .login-container .links a {
            color: #000;
            font-size: 12px;
            text-decoration: none;
        }
        .login-container .checkbox-container {
            display: flex;
            align-items: left;
            justify-content: flex-start; /* 왼쪽 정렬 */
            margin-top: 10px;
        }
        
        .login-container .input-fields .password-container {
            display: flex;
            align-items: center;
            position: relative;
        }
        .login-container .input-fields .password-container input {
            width: 100%;
            padding-right: 40px; /* 눈 아이콘에 공간을 만듭니다 */
        }
        .login-container .input-fields .password-container .toggle-password {
            position: absolute;
            right: 10px;
            cursor: pointer;
            width: 20px;
            height: 20px;
        }
        
        
    </style>
</head>
<body>

<header>
</header>

<div class="login-container">
    <h1>로그인</h1>
    <div class="tab">
        <div id="customer-tab" class="active">고객</div>
        <div id="seller-tab">판매자</div>
    </div>
    <form id="customer-form" class="active" action="customerLogin.html" method="post">
        <div class="input-group">
            <div class="input-fields">
                <input type="text" name="customerId" placeholder="아이디">
                <div class="password-container">
                    <input type="password" id="customerPassword" name="customerPassword" placeholder="비밀번호">
                    <img src="eye-icon.png" class="toggle-password" onclick="togglePassword('customerPassword')">
                </div>
                
            </div>
            <button class="login-btn" type="submit">로그인</button>
        </div>
        <div class="checkbox-container">
            <input type="checkbox" id="remember-me" name="rememberMe">
            <label for="remember-me">아이디 저장</label>
        </div>
    </form>
    <form id="seller-form" action="sellerLogin.html" method="post">
        <div class="input-group">
            <div class="input-fields">
                <input type="text" name="sellerId" placeholder="사업자등록번호">
                <div class="password-container">
                    <input type="password" id="sellerPassword" name="sellerPassword" placeholder="비밀번호">
                    <img src="lower_half.png" class="toggle-password" onclick="togglePassword('sellerPassword')">
                </div>
            </div>
            <button class="login-btn" type="submit">로그인</button>
        </div>
        <div class="checkbox-container">
            <input type="checkbox" id="remember-me-seller" name="rememberMeSeller">
            <label for="remember-me-seller">사업자등록번호 저장</label>
        </div>
    </form>
    <div class="links">
        <a href="#">아이디 찾기</a>
        <a href="#">비밀번호 찾기</a>
        <a href="#">회원가입</a>
    </div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        fetch('footer.html')
            .then(response => response.text())
            .then(data => {
                document.getElementById('footer').innerHTML = data;
            });

        const customerTab = document.getElementById('customer-tab');
        const sellerTab = document.getElementById('seller-tab');
        const customerForm = document.getElementById('customer-form');
        const sellerForm = document.getElementById('seller-form');

        customerTab.addEventListener('click', function() {
            customerTab.classList.add('active');
            sellerTab.classList.remove('active');
            customerForm.classList.add('active');
            sellerForm.classList.remove('active');
        });

        sellerTab.addEventListener('click', function() {
            sellerTab.classList.add('active');
            customerTab.classList.remove('active');
            sellerForm.classList.add('active');
            customerForm.classList.remove('active');
        });
    });

    function togglePassword(id) {
        const passwordInput = document.getElementById(id);
        const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
        passwordInput.setAttribute('type', type);
    }
    
</script>
</body>
</html>
