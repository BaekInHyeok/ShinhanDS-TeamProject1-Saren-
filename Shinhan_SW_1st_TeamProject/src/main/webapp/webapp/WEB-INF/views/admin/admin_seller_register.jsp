<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>판매자 등록 신청</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .sidebar {
            height: 100vh;
            position: fixed;
            top: 0;
            left: 0;
            width: 250px;
            background-color: #343a40;
            color: #fff;
            padding-top: 20px;
        }
        .sidebar a {
            color: #fff;
            text-decoration: none;
            display: block;
            padding: 10px 20px;
        }
        .sidebar a:hover {
            background-color: #495057;
        }
        .content {
            margin-left: 250px;
            padding: 20px;
        }
    </style>
</head>
<body>

    <div class="sidebar">
        <h2 class="text-center"><a href="adminpage">NiceAdmin</a></h2>
        <a href="adminpage">Dashboard</a>
        <a href="admin_seller_list">판매자 목록</a>
        <a href="admin_seller_register">판매자 등록</a>
        <a href="admin_seller_detail">판매자 상세</a>
        <a href="admin_faq">F.A.Q</a>
    </div>

    <div class="content">
        <div class="container-fluid">
            <h1>판매자 등록 신청 관리</h1>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>신청자명</th>
                            <th>브랜드</th>
                            <th>Ext.</th>
                            <th>City</th>
                            <th>Status</th>
                            <th>신청 날짜</th>
                            <th>허용/거부</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Brandon Jacob</td>
                            <td>나이키</td>
                            <td>28</td>
                            <td>2016-05-25</td>
                            <td>허용</td>
                            <td>2021/01/01</td>
                            <td>
                                <button class="btn btn-sm btn-success" onclick="approveSeller('Brandon Jacob')">허용</button>
                                <button class="btn btn-sm btn-danger" onclick="denySeller('Brandon Jacob')">거부</button>
                            </td>
                        </tr>
                        <tr>
                            <td>Bridie Kessler</td>
                            <td>매종키츠네</td>
                            <td>35</td>
                            <td>2014-12-05</td>
                            <td>허용</td>
                            <td>2021/02/15</td>
                            <td>
                                <button class="btn btn-sm btn-success" onclick="approveSeller('Bridie Kessler')">허용</button>
                                <button class="btn btn-sm btn-danger" onclick="denySeller('Bridie Kessler')">거부</button>
                            </td>
                        </tr>
                        <tr>
                            <td>Ashleigh Langosh</td>
                            <td>나이키</td>
                            <td>45</td>
                            <td>2011-08-12</td>
                            <td>허용</td>
                            <td>2021/03/10</td>
                            <td>
                                <button class="btn btn-sm btn-success" onclick="approveSeller('Ashleigh Langosh')">허용</button>
                                <button class="btn btn-sm btn-danger" onclick="denySeller('Ashleigh Langosh')">거부</button>
                            </td>
                        </tr>
                        <tr>
                            <td>Angus Grady</td>
                            <td>나이키</td>
                            <td>34</td>
                            <td>2012-06-11</td>
                            <td>허용</td>
                            <td>2021/04/20</td>
                            <td>
                                <button class="btn btn-sm btn-success" onclick="approveSeller('Angus Grady')">허용</button>
                                <button class="btn btn-sm btn-danger" onclick="denySeller('Angus Grady')">거부</button>
                            </td>
                        </tr>
                        <tr>
                            <td>Raheem Lehner</td>
                            <td>나이키</td>
                            <td>47</td>
                            <td>2011-04-19</td>
                            <td>허용</td>
                            <td>2021/05/05</td>
                            <td>
                                <button class="btn btn-sm btn-success" onclick="approveSeller('Raheem Lehner')">허용</button>
                                <button class="btn btn-sm btn-danger" onclick="denySeller('Raheem Lehner')">거부</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script>
        function approveSeller(name) {
            alert(name + ' 판매자 허용');
            // 여기에 판매자 허용 로직 추가
        }

        function denySeller(name) {
            alert(name + ' 판매자 거부');
            // 여기에 판매자 거부 로직 추가
        }
    </script>

</body>
</html>
