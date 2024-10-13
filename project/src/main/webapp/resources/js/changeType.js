 /*
 	FreeboardDeatil에 boardType 변경
 */
 
 function changeType(){
        let typeElement = document.querySelector(".boardType");
        let value = parseInt(typeElement.innerHTML, 10);

        switch (value) {
            case 0:
                typeElement.innerHTML = "루트게시판";
                break;
            case 1:
                typeElement.innerHTML = "여행후기";
                break;
            case 2:
                typeElement.innerHTML = "꿀팁공유";
                break;
            case 3:
                typeElement.innerHTML = "질문있어요!";
                break;
            case 4:
                typeElement.innerHTML = "수다방";
                break;
            case 5:
                typeElement.innerHTML = "동행 구해요!";
                break;
            case 6:
                typeElement.innerHTML = "문의";
                break;
            default:
                typeElement.innerHTML = "에러";
        }
    }
    window.onload = function() {
        changeType(); // 페이지가 로드될 때 자동으로 함수 호출
    };
    
    