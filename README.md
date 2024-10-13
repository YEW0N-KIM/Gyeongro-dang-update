# acornProject

# 국내 여행 루트 공유 게시판 및 국내 행사・관광지 추천 사이트

## 프로젝트 소개

이 프로젝트는 사용자들이 국내 여행 루트를 공유하고, 다양한 국내 행사, 관광지, 축제에 대한 정보를 얻을 수 있는 플랫폼입니다. 사용자들은 자유게시판을 통해 여행 경험을 나누고, 다른 사람들이 추천하는 여행지를 탐색할 수 있습니다.


## 주요 기능

- **여행 루트 공유 게시판**: 사용자들이 자신만의 여행 루트를 공유하고, 다른 사용자들의 루트를 확인할 수 있습니다. 
- **자유게시판**: 여행에 관련된 다양한 주제로 자유롭게 소통할 수 있는 공간입니다.
- **행사/관광지/축제 추천**: 최신 국내 행사, 관광지, 축제 정보를 제공하며 사용자들이 추천하는 장소를 소개합니다.
- **댓글 및 좋아요 기능**: 게시물에 댓글을 달고 좋아요를 누를 수 있어 사용자 간의 상호작용을 촉진합니다.

git clone https://github.com/yeominhye/acornProject.git

## git 초기 설정 방법 🐇
Ⅰ. Fork : 팀프로젝트 ( 지금 이 페이지) 를 fork한다 (폴더 복붙하는 느낌)

-> fork 하는 이유는? 팀프로젝트에 직접적인 영향 X. 실수로 올려도 팀프로젝트 기록에 남지 X


Ⅱ. clone : (포크한)리포지토리를 내 로컬에 복제하기

   -> git clone URL

  ⅰ. 내가 사용할 폴더에 들어가서 우클릭 -> git bash here 로 cmd 켜기.
   
  ⅱ. git clone http://포크한 당신의 리포지토리 URL // 민혜 거 X. 직접 포크한 당신의 리포지토리 url
   
  ⅲ. 자신의 로컬 폴더에 복제되었는지 확인하기. 


Ⅲ. remote add로 팀 버전 이력도 확인하기 

  -> git remote -v // 리모트중인 리포지토리 확인

  -> git remote add upstream(이 부분은 별칭.. 일반적으로 업스트림으로 씀) URL

   $ git remote add upstream https://github.com/yeominhye/acornProject.git    <- 가져다 쓰세요 



## project import 방법🐣
File > Import

Git > Projects from Git > next

clone URL

url : https://github.com/yeominhye/acornProject.git
next > next > finish

로그인 필요하다면
url에 https://github.com/yeominhye/acornProject.git 입력

user : 본인의 깃허브 아이디 ex)gpdldydaoa99

password : 본인의 token(토근을 다시 확인할 수 없으니 만약 저장해둔 것이 없다면 토근을 재발급 받고 꼭 저장해두기)

Store in Secure Store 체크하기

## ☛ token 발급 받는 방법

- **본인 프로필** - Settings Developer settings - personal access tokens - 필요한 항목 체크하고 토큰 만료 기간 최대로 설정해두기(본인 선택)

만약 비밀번호 힌트가 안 나오고 로그인 실패? 같은 문구가 뜬다면

-**1. 토큰이 만료됐을 수도?** - 토근 재발급
   
-**2. sts에 이미 저장된 계정 정보가 있을 수 있음**

Preferences > General > Security > Secure Storage > contents > delete

왼쪽 메뉴의 acornProject > Remotes > origin > 밑에 있는 거 change Credentials

user(id), password(token) 입력하면 됨


clone해서 import할 경우 에러 발생

프로젝트 폴더 우클릭 > configure > add Gradle Nature

🐹 여기까지 잘 받아와졌다면 branch로 작업해주세요. 🐹


## git branch 생성 방법

- project[acornProject Main] > team > switch to New branch

- Branch Name 설정(본인 작업 이름으로 해주세요)

- check out new branch 선택하면 branch 생성과 동시에 브랜치 전환

- project[acornProject Main] 여기 오른쪽에 main이 본인이 설정한 이름으로 바꼈는지 확인해주세요.

- project[acornProject branchTest] 등

🦄 혹은 terminal이나 gitbash/cmd에서도 작업 가능합니다.


## push를 하고 싶다면

- project 우클릭 Team > Push branch '생성한 branch 이름' > next > finish

- pushed to https://github.com/~~ 확인

- 한 번 테스트 해보시고 github에서 잘 올라갔나 확인해주세요~!!

## DB 복붙용
drop table usertbl;

drop table boardtbl;

drop table commenttbl;

drop table archivetbl;

drop table reporttbl;

drop table liketbl;

drop table pointtbl;

------------
--usertbl--

CREATE TABLE usertbl(

    userCode VARCHAR(255) PRIMARY KEY,
    
    userId VARCHAR(255) NOT NULL,
    
    userPw VARCHAR(255) NOT NULL,
    
    userName VARCHAR(255) NOT NULL,
    
    nickname VARCHAR(255) NOT NULL,
    
    userBirth DATE,
    
    userTel VARCHAR(20),
    
    userPoint INT DEFAULT 0
    
);


DELIMITER //

CREATE TRIGGER before_insert_usertbl

BEFORE INSERT ON usertbl FOR EACH ROW

BEGIN

    DECLARE next_userCode INT;
    
    SELECT COALESCE(MAX(CAST(SUBSTRING(userCode, 2) AS UNSIGNED)), 0) + 1 INTO next_userCode
    FROM usertbl;

    SET NEW.userCode = CONCAT('u', LPAD(next_userCode, 4, '0'));
    
END //

DELIMITER ;

DELIMITER //

CREATE TRIGGER before_insert_nickname_usertbl

BEFORE INSERT ON usertbl FOR EACH ROW

BEGIN

    DECLARE next_nickname INT;

    SELECT COALESCE(MAX(CAST(SUBSTRING(nickname, 2) AS UNSIGNED)), 0) + 1 INTO next_nickname
    FROM usertbl;

    SET NEW.nickname = CONCAT('n', LPAD(next_nickname, 4, '0'));
    
END //

DELIMITER ;

INSERT INTO usertbl (userId, userPw, userName, userTel)

VALUES ('example_id', 'example_pw', 'John Doe','010-1234-5678');

INSERT INTO usertbl (userId, userPw, userName, userTel)

VALUES ('1', '1', 'John 1','1');



------------
--boardtbl--

CREATE TABLE boardtbl(

   boardCode VARCHAR(255) PRIMARY KEY,
    
	userCode VARCHAR(255) NOT NULL,
 
   FOREIGN KEY (userCode)
   
   REFERENCES usertbl(userCode) 
   
   ON UPDATE CASCADE
   
   ON DELETE CASCADE,
   
   boardImgOrigin VARCHAR(255),
   
   boardImgReal VARCHAR(255),
   
   boardTitle VARCHAR(255) NOT NULL,
   
   boardContent LONGTEXT NOT NULL,

   boardTheme INT,
   
   boardTourdays INT,
   
	boardWritedate DATETIME NOT NULL,
 
   boardViews INT,
   
   boardPoint INT,
   
   boardType INT,
   
	boardRegion INT,
 
	days JSON
 
);


DELIMITER //

CREATE TRIGGER before_insert_boardtbl

BEFORE INSERT ON boardtbl FOR EACH ROW

BEGIN

    DECLARE next_board_code INT;
   
    SELECT COALESCE(MAX(CAST(SUBSTRING(boardCode, 2) AS UNSIGNED)), 0) + 1 INTO next_board_code
    FROM boardtbl;

    SET NEW.boardCode = CONCAT('b', LPAD(next_board_code, 4, '0'));
    
END //

DELIMITER ;




------------
--commenttbl--

CREATE TABLE commenttbl(

   commentCode VARCHAR(255) PRIMARY KEY,
    
   boardCode varchar(255),
   
   FOREIGN KEY (boardCode)
    
   REFERENCES boardtbl(boardCode) 
   
   ON UPDATE CASCADE
   
   ON DELETE CASCADE,
   
   userCode varchar(255),
   
   FOREIGN KEY (userCode)
   
   REFERENCES usertbl(userCode) 
   
   ON UPDATE CASCADE
   
   ON DELETE CASCADE,
   
   commentContent varchar(1600) NOT NULL,
   
   commentDate datetime
   
);

DELIMITER //

CREATE TRIGGER before_insert_commenttbl

BEFORE INSERT ON commenttbl FOR EACH ROW

BEGIN

    DECLARE next_comment_code INT;
    
    SELECT COALESCE(MAX(CAST(SUBSTRING(commentCode, 2) AS UNSIGNED)), 0) + 1 INTO next_comment_code
    FROM commenttbl;

    SET NEW.commentCode = CONCAT('c', LPAD(next_comment_code, 4, '0'));
    
END //

DELIMITER ;





------------
--pointtbl--

CREATE TABLE pointtbl(

    pointCode VARCHAR(255) PRIMARY KEY,
    
    userCode VARCHAR(255) NOT NULL,
    
    boardCode VARCHAR(255),
    
    pointStatus INT NOT NULL, -- 0: 사용 1: 포인트_구매 2: 환급
    
    pointAmount int NOT NULL,
    
    pointDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    
);

DELIMITER //

CREATE TRIGGER before_insert_pointtbl

BEFORE INSERT ON pointtbl FOR EACH ROW
BEGIN
    DECLARE next_pointCode INT;

    SELECT COALESCE(MAX(CAST(SUBSTRING(pointCode, 2) AS UNSIGNED)), 0) + 1 INTO next_pointCode
    FROM pointtbl;

    SET NEW.pointCode = CONCAT('p', LPAD(next_pointCode, 4, '0'));
    
END //

DELIMITER ;





------------
--liketbl--

CREATE TABLE liketbl(

   userCode VARCHAR(255) NOT NULL,
   
    FOREIGN KEY (userCode)
    
    REFERENCES usertbl(userCode) 
    
    ON UPDATE CASCADE
    
    ON DELETE CASCADE,
    
    boardCode VARCHAR(255) NOT NULL,
    
    FOREIGN KEY (boardCode)
    
    REFERENCES boardtbl(boardCode) 
    
    ON UPDATE CASCADE
    
    ON DELETE CASCADE
    
);



------------
--archivetbl--

CREATE TABLE archivetbl(

   archiveCode varchar(255) primary key,
   
    userCode VARCHAR(255) NOT NULL,
    
    FOREIGN KEY (userCode)
    
    REFERENCES usertbl(userCode) 
    
    ON UPDATE CASCADE
    
    ON DELETE CASCADE,
    
    boardCode VARCHAR(255) NOT NULL,
    
    FOREIGN KEY (boardCode)
    
    REFERENCES boardtbl(boardCode) 
    
    ON UPDATE CASCADE
    
    ON DELETE CASCADE
    
);

DELIMITER //

CREATE TRIGGER before_insert_archivetbl

BEFORE INSERT ON archivetbl FOR EACH ROW
BEGIN
    DECLARE next_archiveCode INT;

    SELECT COALESCE(MAX(CAST(SUBSTRING(archiveCode, 2) AS UNSIGNED)), 0) + 1 INTO next_archiveCode
    FROM archivetbl;

    SET NEW.archiveCode = CONCAT('a', LPAD(next_archiveCode, 4, '0'));
    
END //

DELIMITER ;




------------
--reporttbl--

CREATE table reporttbl(

   reportCode varchar(255) primary key,
   
   boardCode VARCHAR(255) NOT NULL,
   
   FOREIGN KEY (boardCode)
   
   REFERENCES boardtbl(boardCode) 
   
   ON UPDATE CASCADE
   
   ON DELETE CASCADE,
   
   reportingUserCode VARCHAR(255) NOT NULL,
   
   FOREIGN KEY (reportingUserCode)
   
   REFERENCES usertbl(userCode) 
   
   ON UPDATE CASCADE
   
   ON DELETE CASCADE,
   
   reportedUserCode VARCHAR(255) NOT NULL,
   
   FOREIGN KEY (reportedUserCode)
   
   REFERENCES usertbl(userCode) 
   
   ON UPDATE CASCADE
   
   ON DELETE CASCADE,
   
   reportDate datetime,
   
   reportContent varchar(255)
   
);

DELIMITER //

CREATE TRIGGER before_insert_reporttbl

BEFORE INSERT ON reporttbl FOR EACH ROW

BEGIN
    DECLARE next_reportCode INT;

    SELECT COALESCE(MAX(CAST(SUBSTRING(reportCode, 2) AS UNSIGNED)), 0) + 1 INTO next_reportCode
    FROM reporttbl;

    SET NEW.reportCode = CONCAT('r', LPAD(next_reportCode, 4, '0'));
    
END //

DELIMITER ;
