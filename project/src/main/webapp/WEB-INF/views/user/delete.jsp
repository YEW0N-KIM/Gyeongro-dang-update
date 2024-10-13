<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 탈퇴</title>

<link rel="stylesheet" type="text/css"
   href="${pageContext.request.contextPath}/resources/css/reset.css">

<style>
    .wrap {
       width: 100%;
       height: 100%;
        margin: 0 auto;
    }


    .header {
        font-weight: bold;
        text-align: center;

        padding: 7px 20px 13px 20px;
        border-bottom: 1px solid black;
    }


    .content {
        padding: 0 25px;
    }


    .main {
        font-size: 22px;
        font-weight: bold;
        padding: 25px 0;
    }


    .notice-box {
        font-size: 14px;
        width: 100%;
        height: 160px;
        margin: 25px 0;
        padding: 20px;
        background-color: rgb(239, 239, 239);
        border-radius: 20px;
    }
    .notice {
        display: flex;
        margin-bottom: 10px;
    }
    .notice .img {
        height: 40px;
        display: inline-block;
    }
    .notice .img img {
        width: 14px;
        height: 14px;
        margin: 2px 5px 0 0;
    }


    .check {
        text-align: center;
        margin: 50px 0 25px 0;
        font-size: 16px;
        color: #5c5c5c;
    }
    .check label {
       cursor: pointer;
    }


    .button-section {
        margin: 0 auto;
        width: 130px;
        padding-bottom: 50px;
    }
    .button-section form {
        display: flex;
        justify-content: space-between;
    }
    .button-section button {
        width: 55px;
    }

</style>

</head>
<body>
   <div class="wrap">
        <div class="header">회원탈퇴</div>

        <div class="content">
            <div class="main">
                 <span>${user.nickname}</span>님 <br>
                정말 탈퇴하시겠어요?
            </div>

            <div class="notice-box">
                <div class="notice">
                    <div class="img"><img src="${pageContext.request.contextPath}/resources/img/delete-icon.png" alt=""></div>
                    <span>지금 탈퇴하시면 보유하신 포인트도 함께 사라져요. 추후에 동일 계정으로 재가입하셔도 포인트 내역은 복구되지 않습니다.</span>
                </div>
                <div class="notice">
                    <div class="img"><img src="${pageContext.request.contextPath}/resources/img/delete-icon.png" alt=""></div>
                    <span>탈퇴 후에는 작성하신 게시판이나 댓글을 수정 혹은 삭제하실 수 없습니다. 탈퇴 전에 꼭 확인해주세요!</span>
                </div>
                <div class="notice">
                    <div class="img"><img src="${pageContext.request.contextPath}/resources/img/delete-icon.png" alt=""></div>
                    <span>탈퇴 후에는 계정 복구가 불가능합니다.</span>
                </div>
            </div>

            <div class="check">
                <label>
                    <input type="checkbox" id="delUserCheck">
                    <span>회원 탈퇴 유의사항을 확인하였으며 동의합니다.</span>
                </label>
            </div>

            <div class="button-section">
                <form id="deleteForm"
                    action="${pageContext.request.contextPath}/user/delete_process.do"
                    method="post">
                    <input type="hidden" name="userId" value="${user.userId}">
                    <button class="delete" onclick="confirmDelete()">확인</button>
                    <button type="button" class="cancel" onclick="window.close()">취소</button>
                </form>
            </div>
        </div>
    </div>
    
<script>
document.addEventListener("DOMContentLoaded", function() {
    var checkBox = document.getElementById('delUserCheck');
    var deleteButton = document.querySelector('.delete');
    
    // 초기 상태 설정
    deleteButton.disabled = !checkBox.checked;

    // 체크박스 상태 변경 시 버튼 활성화/비활성화
    checkBox.addEventListener('change', function() {
        deleteButton.disabled = !this.checked;
    });

    // 폼 제출 이벤트 처리
    document.getElementById("deleteForm").addEventListener("submit", function(event) {
        event.preventDefault();
        fetch(this.action, {
            method: this.method,
            body: new FormData(this),
        })
        .then(response => {
            if (response.ok) {
                // 서버 응답이 성공일 때
                if (opener) {
                    opener.location.reload(); // 부모 창 새로고침
                    opener.goHome(); // 부모 창 홈으로 이동하는 함수 호출
                }
                alert("회원탈퇴가 완료되었습니다.");
                window.close(); // 창 닫기
            } else {
                // 서버 응답이 실패일 때
                alert("다시 시도하세요.");
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });
});
</script>
</body>
</html>