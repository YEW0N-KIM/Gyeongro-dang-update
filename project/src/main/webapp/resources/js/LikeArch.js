/**
 *  좋아요, 스크렙 
 */

var boardUserCode = document.querySelector('.boardUsercode').value;
var userCode = document.querySelector('.userCode').value;
var boardCode = document.querySelector('.boardCode').value;

 // 좋아요
 $(document).ready(function() {
    const likeBtn = document.querySelector('.like-btn');
    const likeIcon = likeBtn.querySelector('.like-icon');
    const likedIcon = likeBtn.querySelector('.liked-icon');

     if(userCode!=null){
        
         let like = {
                 userCode: userCode,
                 boardCode: boardCode
                 
             }; // JSON 객체
            //  console.log(like);
             
             // JSON 객체를 문자열로 변환
             let likeString = JSON.stringify(like);
            //  console.log(likeString);
             
             $.ajax({
                 type: "post",
                 url: "/project/board/"+boardCode+"/likeCheck",
                 data: likeString,
                 contentType: "application/json",
                 
                 success: function(data) {
                    //  console.log('데이터 가져오기 성공:', data);
                     if(data!=0){
                         $('.like-icon').hide();
                         $('.liked-icon').show();
                         likeIcon.classList.add('hidden');
                         likedIcon.classList.remove('hidden');
                     }else{
                         $('.like-icon').show();
                         $('.liked-icon').hide(); 
                         likedIcon.classList.add('hidden');
                         likeIcon.classList.remove('hidden');
                     }
                    
                 },
                 error: function(xhr, status, error) {
                    //  console.error('데이터 가져오기 실패:', status, error);
                 }
             });
         
     }else{
         $('.like-icon').show();
         $('.liked-icon').hide();
     }
     

 });

// 스크랩
$(document).ready(function() {
      const scrapBtn = document.querySelector('.scrap-btn');
      const scrapIcon = scrapBtn.querySelector('.scrap-icon');
      const scrapedIcon = scrapBtn.querySelector('.scraped-icon');

      if (userCode != null) {
          let archive = {
              userCode: userCode,
              boardCode: boardCode
          }; // JSON 객체
        //   console.log(archive);

          // JSON 객체를 문자열로 변환
          let archiveString = JSON.stringify(archive);
        //   console.log(archiveString);

          $.ajax({
              type: "post",
              url: "/project/board/"+boardCode + "/archCheck",
              data: archiveString,
              contentType: "application/json",

              success: function(data) {
                //   console.log('데이터 가져오기 성공:', data);
                  if (data != 0) {
                      $('.scrap-icon').hide();
                      $('.scraped-icon').show();
                      scrapIcon.classList.add('hidden');
                      scrapedIcon.classList.remove('hidden');
                  } else {
                      $('.scrap-icon').show();
                      $('.scraped-icon').hide();
                      scrapedIcon.classList.add('hidden');
                      scrapIcon.classList.remove('hidden');
                  }
              },
              error: function(xhr, status, error) {
                //   console.error('데이터 가져오기 실패:', status, error);
              }
          });

      } else {
          $('.scrap-icon').show();
          $('.scraped-icon').hide();
      }
  });
  

  // 좋아요 클릭했을 시
  document.querySelector('.like-btn').addEventListener('click', function() {
    const likeBtn = document.querySelector('.like-btn');
    const likeIcon = likeBtn.querySelector('.like-icon');
    const likedIcon = likeBtn.querySelector('.liked-icon');

    if (userCode != null && userCode !="" && userCode !='null') {
        let like = {
            userCode: userCode,
            boardCode: boardCode
        };
        let likeString = JSON.stringify(like);
        // console.log(likeString);
        // 좋아요 버튼의 상태를 확인하여 변경합니다.
        if (likeIcon.classList.contains('hidden')) {
            // 좋아요를 이미 누른 상태이면 좋아요를 취소합니다.
            likeIcon.classList.remove('hidden');
            likedIcon.classList.add('hidden');

            $.ajax({
                type: "delete",
                url: "/project/board/"+boardCode + "/likes",
                data: likeString,
                contentType: "application/json",
                success: function(response) {
                    // console.log('성공');
                    console.log(response);
                    window.location.href = response.redirect;
                     
                },
                error: function(xhr, status, error) {
                    // console.error('실패:');
                    
                }
            });

        } else {
            // 좋아요를 누르지 않은 상태이면 좋아요를 누릅니다.
            likeIcon.classList.add('hidden');
            likedIcon.classList.remove('hidden');

            $.ajax({
                type: "post",
                url: "/project/board/"+boardCode + "/likes",
                data: likeString,
                contentType: "application/json",
                success: function( response) {
                
                    console.log(response);
                    // console.log('성공');
                   window.location.href = response.redirect; 
                   
                    console.log(boardCode);
                    //console.log(response);
                    
                },
                error: function(xhr, status, error) {
                    // console.error('실패:');
                }
            });
        }
    } else {
        window.location.href = "http://localhost:8080/project/user/login.do";
    }
});


 // 스크랩 버튼 클릭 시
 document.querySelector('.scrap-btn').addEventListener('click', function() {
    // 스크랩 버튼 요소를 가져옵니다.
    const scrapBtn = document.querySelector('.scrap-btn');
    const scrapIcon = scrapBtn.querySelector('.scrap-icon');
    const scrapedIcon = scrapBtn.querySelector('.scraped-icon');
    
    if (userCode != null && userCode !="" && userCode !='null') {
        let archive = {
            userCode: userCode,
            boardCode: boardCode
        };
        let archiveString = JSON.stringify(archive);
        // console.log(archiveString);

        // 좋아요 버튼의 상태를 확인하여 변경합니다.
        if (scrapIcon.classList.contains('hidden')) {
            // 좋아요를 이미 누른 상태이면 좋아요를 취소합니다.
               scrapIcon.classList.remove('hidden');
            scrapedIcon.classList.add('hidden');

            $.ajax({
                type: "delete",
                url: "/project/board/"+boardCode + "/arch",
                data: archiveString,
                contentType: "application/json",
                success: function(response) {
                    // console.log('성공');
                   window.location.href = response.redirect;
                },
                error: function(xhr, status, error) {
                    // console.error('실패:');
                }
            });

        } else {
            // 좋아요를 누르지 않은 상태이면 좋아요를 누릅니다.
            scrapIcon.classList.add('hidden');
            scrapedIcon.classList.remove('hidden');

            $.ajax({
                type: "post",
                url: "/project/board/"+boardCode + "/arch",
                data: archiveString,
                contentType: "application/json",
                success: function(response) {
                    // console.log('성공');
                   window.location.href = response.redirect;
                },
                error: function(xhr, status, error) {
                    // console.error('실패:');
                }
            });
        }
    } else {
        window.location.href = "http://localhost:8080/project/user/login.do";
    }
    
});