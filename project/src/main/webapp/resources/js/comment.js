/**
 *  댓글 기능
 */

var boardUserCode = document.querySelector('.boardUsercode').value;
var userCode = document.querySelector('.userCode').value;
var adminCode = document.querySelector('.admin').value;
var boardCode = document.querySelector('.boardCode').value;

$(document).ready(function() {
    // 각 댓글에 대해 사용자 코드를 확인하여 버튼을 표시하거나 숨기는 작업 수행
    $('.comment').each(function() {
        var commentUserCode = $(this).data('user-code');
        checkUserCode(commentUserCode, $(this));
    });

    // 각 댓글에 대해 사용자 코드를 확인하여 버튼을 표시하거나 숨기는 함수
    function checkUserCode(commentUserCode, $comment) {
        var $btnModi = $comment.find('.btnModi');
        var $btnDel = $comment.find('.btnDel');

        if (userCode === commentUserCode || adminCode=='admin') {	
            // 해당 댓글의 작성자와 현재 사용자의 코드가 일치할 경우 버튼을 보이도록 설정
            $btnModi.show();
            $btnDel.show();
        } else {
            // 해당 댓글의 작성자와 현재 사용자의 코드가 일치하지 않을 경우 버튼을 숨기도록 설정
            $btnModi.hide();
            $btnDel.hide();
        }
    }
});

function regComment() {
    let userCode = $("#regUserCode").val();
    let commentDate = $("#regCommentDate").val();
    let commentContent = $("#regComment").val();
    let comment = {
        boardCode: boardCode,
        userCode: userCode,
        commentDate: commentDate,
        commentContent: commentContent
    }; // JSON 객체
    console.log(comment);
       
    // JSON 객체를 문자열로 변환
    let commentString = JSON.stringify(comment);
    console.log(commentString);
       
    $.ajax({
        type: "post",
        url: "/project/board/"+boardCode,
        data: commentString,
        contentType: "application/json",
        success: function(response) {
            if (response.status === 'success') {
                if (response.redirect) {
                    window.location.href = response.redirect; 
                }
            } else {
                console.error('Error: ' + response.message);
            }
        },
        error: function(xhr, status, error) {
            if (xhr.status === 401) {
                window.location.href = "http://localhost:8080/project/user/login.do";
            } else {
                console.error('AJAX Error: ' + status + ' ' + error);
            }
        }
    });
}

function editComment(btn) {
    var $commentDiv = $(btn).closest('.comment');
    var $commentContent = $commentDiv.find('.comment_content');
    var $editInput = $('<textarea class="edit_comment_input">' + $commentContent.text() + '</textarea>');
    $commentContent.replaceWith($editInput);
    $(btn).text('저장')
         .removeClass('btnModi')
         .addClass('btnSave')
         .attr('onclick', 'saveComment(this)');
    
    // document 전체에 클릭 이벤트 등록
    $(document).on('click', function(e) {
        // 수정 중인 댓글을 제외한 영역을 클릭했을 때
        if (!$(e.target).closest('.comment').is($commentDiv)) {
            // 수정 상태 종료
            $editInput.replaceWith('<div class="comment_content">' + $editInput.val() + '</div>');
            $(btn).text('수정')
                 .removeClass('btnSave')
                 .addClass('btnModi')
                 .attr('onclick', 'editComment(this)');
            // document 전체에 등록된 클릭 이벤트 제거
            $(document).off('click');
        }
    });
}

function saveComment(btn) {
    var $commentDiv = $(btn).closest('.comment');
    var $editInput = $commentDiv.find('.edit_comment_input');
    var commentContent = $editInput.val(); 
    var commentCode = $commentDiv.find('.modiCommentCode').val(); 
    
    var comment = {
        commentCode: commentCode,
        commentContent: commentContent
    }; 
    
    var commentString = JSON.stringify(comment);

    $.ajax({
        type: "PUT", // PUT 요청 사용
        url: "/project/board/"+boardCode, // boardCode 변수를 사용하여 URL을 지정
        data: commentString,
        contentType: "application/json",
        success: function(data) {
            console.log(data);
        },
        error: function(xhr, status, error) {
            console.log(xhr, status, error);
        }
    });
    
    $editInput.replaceWith('<div class="comment_content">' + commentContent + '</div>');
    $(btn).text('수정')
         .removeClass('btnSave')
         .addClass('btnModi')
         .attr('onclick', 'editComment(this)');
    // document 전체에 클릭 이벤트 제거
    $(document).off('click');
}


function delComment(btn) {
    // 댓글 요소를 찾습니다.
    var $commentDiv = $(btn).closest('.comment');
    
    // 댓글의 commentCode 값을 가져옵니다.
    var commentCode = $commentDiv.find('.modiCommentCode').val();
    
    // 삭제를 확인하는 다이얼로그를 표시하거나 직접 삭제 요청을 보낼 수 있습니다.
    if (confirm("정말로 이 댓글을 삭제하시겠습니까?")) {
        // AJAX를 사용하여 삭제 요청을 보냅니다.
        $.ajax({
            type: "DELETE",
            url: "/project/board/"+boardCode, // 댓글을 삭제하는 URL을 지정합니다. 확인이 필요합니다.
            data: commentCode,
            success: function(data) {
                console.log(data); // 성공적으로 삭제된 경우 로그에 출력합니다.
                
                // 삭제된 댓글을 화면에서 숨깁니다.
                location.reload(true);
            },
            error: function(xhr, status, error) {
                console.error(xhr, status, error); // 오류가 발생한 경우 콘솔에 오류를 출력합니다.
            }
        });
    }
}

document.addEventListener("DOMContentLoaded", function() {
   var userCode = document.querySelector('.userCode').value;   
   
    if (userCode === 'null') {
        // #regComment 요소가 없는 경우 실행됩니다.
        // .my_comment_sender 요소의 내용을 변경합니다.
        var commentSender = document.querySelector('.my_comment_sender');
        commentSender.textContent = "로그인 후 댓글을 입력하세요.";
        commentSender.style.fontWeight="500";
        
        // .my_comment_content 요소의 placeholder를 변경합니다.
        var commentTextarea = document.querySelector('.my_comment_content');
        commentTextarea.placeholder = " ";
    }
});

   function checkComments() {
      var comments = document.getElementsByClassName("comment");
      var noCommentsMessage = document.getElementById("no-comments-message");

      if (comments.length === 0) {
         noCommentsMessage.style.display = "block";
      } else {
         noCommentsMessage.style.display = "none";
      }
   }

   document.addEventListener("DOMContentLoaded", function() {
      checkComments();
   });
   
   $(document).ready(function(){
       var boardUserCode = document.querySelector('.boardUsercode').value;
       var userCode = document.querySelector('.userCode').value;
       var boardModi = document.querySelector('#boardModi');
       
       if (userCode === boardUserCode || adminCode == 'admin'){
           boardModi.style.display = "inline";
       } else {
           boardModi.style.display = "none";
       }
   });

