<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <style>
        .wrap {
            font-size: 32px;
            font-weight: bold;
            width: 100%;
            display: flex;
            justify-content: center;
            margin-top: 300px;
        }
        .content {
            text-align: center;
        }
        .content div {
            margin: 20px 0;
        }
        button {
            width: 55px;
            border: 0px;
            background-color: #5c5c5c;
            color: white;
            padding: 5px;
            border-radius: 5px;
            cursor: pointer;
        }
        p {
            margin: 5px;
            font-weight: 200;
            font-size: 14px;
            color: gray;
        }
    </style>
</head>
<body>
    <div class="wrap">
        <div class="content">
            <img src="${pageContext.request.contextPath}/resources/img/error.png" alt="">
            <div>요청하신 페이지를 찾을 수 없습니다.</div>
            <p>방문하려는 페이지의 주소가 잘못 입력되었거나, 페이지의 주소가 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다.</p>
            <p>입력하신 주소가 정확한지 다시 한 번 확인해 주시기 바랍니다.</p>
            <p>감사합니다.</p>
            <a href="/project/"><button>홈</button></a>
        </div>
    </div>
</body>
</html>