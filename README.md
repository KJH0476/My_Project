# 알고리즘 공유 블로그
## 나의 첫번째 Spring 토이 프로젝트


### 💡 프로젝트를 진행하게된 이유
- 자신이 공부한 알고리즘에 대해 보다 효율적으로 기록하고 공유할 수 있는 사이트를 만들고 싶었습니다.
- 학교 수업 중 '모바일프로그래밍' 수업의 개인 텀프로젝트 과제를 하는 겸 공부 목적으로 개인 spring 토이프로젝트 시작하였습니다.
- 제출 이후에도 spring의 다양한 기능을 활용해 기능을 확장해보며 공부해보고 싶었습니다.
<br/>

### 👨 참여인원 및 기간
- 고진혁
- 2023.11.17 ~ 2023.12.07(모바일프로그래밍 텀프로젝트 제출완료)
<br/>

### 📘 학습목적
1. 직접 사이트를 만들어 보면서 Spring에 대해 배운내용을 직접 적용하고 응용하여 각종 기능과 MVC구조에 대한 이해.
2. Thymeleaf를 이용해 Spring에서 서버사이드 렌더링(SSR)을 학습
3. PRG 패턴과 인터셉터 직접 적용하여 학습
4. 기본적인 CRUD를 Jpa를 활용해 구현함으로써 Jpa 기초를 학습
<br/>

### 📁 DB 구성
<img width="753" alt="myproject_db구성" src="https://github.com/KJH0476/My_Project/assets/148829100/cccc53ec-2946-44f2-8c15-a62509fbfc71">
 
- 회원(Member), 게시글(Board), 댓글(Comment) 총 3개의 테이블로 구성
<br/>

### 👀 화면
<img width="196" alt="스크린샷 2023-12-22 오후 3 41 07" src="https://github.com/KJH0476/My_Project/assets/148829100/de1bedcb-06dd-4ec1-b783-7821451501ad">
<img width="196" alt="스크린샷 2023-12-22 오후 3 44 43" src="https://github.com/KJH0476/My_Project/assets/148829100/c9df8f4c-0786-4ab8-8f25-70c8ec25a3e2">
<img width="197" alt="스크린샷 2023-12-22 오후 3 53 03" src="https://github.com/KJH0476/My_Project/assets/148829100/5f6c37a6-6260-481b-b51c-bb395380cd61">
<img width="196" alt="스크린샷 2023-12-22 오후 3 54 57" src="https://github.com/KJH0476/My_Project/assets/148829100/8d0a6f7d-d158-41ae-ad86-e2da24090ada">
<img width="197" alt="스크린샷 2023-12-22 오후 3 58 08" src="https://github.com/KJH0476/My_Project/assets/148829100/dd2ce147-d1c0-4adf-90de-abc06c2c662b">
<br/>
<br/>

### 📅 프로젝트 진행
- 2023.11.17 - 프로젝트 생성 및 메인페이지 구현
- 2023.11.18 - 헤더 메뉴바 토글 공통기능 구현, 알고리즘 게시글 검색 및 저장 로직 구현
- 2023.11.19 - 로그인 화면 구현, 로그인 인터셉터 구현, 로그인 시 세션 적용 + 로그아웃(세션만료) 기능 추가
- 2023.11.20 - 로그인 id, password 예외처리, 회원가입 화면 및 기능 구현 + 회원가입 필드오류, 글로벌오류 예외처리
- 2023.11.22 - 게시글 작성 폼화면 및 게시글 작성과 저장 기능 구현
- 2023.11.23 - 게시글 검색, 마이페이지, 게시글 메뉴별 검색 기능 구현
- 2023.11.26 - db 사용 위한 기존 repository 함수 인터페이스 구현, 게시글을 보여주는 폼 구현
- 2023.11.27 - 댓글 작성 및 저장 기능 구현
- 2023.11.30 - 비밀번호 변경 기능 구현
- 2023.12.02 - html 페이지 중복 부분 제거, 댓글 삭제 기능 구현, 게시글 수정 및 삭제 기능 구현
- 2023.12.04 - Jpa, database 의존성 추가
- 2023.12.04 - Jpa 활용한 회원 repository(JpaMemberRepository)와 게시글 repository(JpaBoardRepository) 구현
- 2023.12.05 - Jpa 활용한 댓글 repository(JpaCommentRepository) 구현
- 2023.12.06 - Ai 질문 페이지 구현, chatGpt api 사용한 서비스 구현
- 2023.12.06 - chatGpt 컨트롤러 추가 (objectMapper, restTemplate, JsonNode 활용)
- 2023.12.07 - 프로그램 간단한 버그 수정, 모바일프로그래밍 텀프로젝트 제출
- ⭐ 추후에 공부 목적으로 더 진행할 예정
- 2023.12.22 - SpringData Jpa 사용한 Repository 추가, 어댑터 패턴을 이용해 기존 인터페이스에 주입
