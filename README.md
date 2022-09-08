## :triangular_flag_on_post: Team 칠전팔기: Project Fastbank 개요

### 서비스 개요
- Megabyte School K Digital Training 2기 (프론트엔드 및 백엔드 개발자 각 양성과정) 수강생의 협동 미니프로젝트
- 구현 목표: 고객이 자신에게 적합한 금융 상품을 탐색할 수 있는 서비스의 기본적인 기능 구현

### 참고 링크
- 서비스 랜딩페이지: https://fastbank.netlify.app 
- 프론트엔드 Repository: https://github.com/hyeonahc/fast-bank-FE 


** **


## :hammer_and_pick: 프로젝트 백엔드 개발에 사용한 도구

### 개발 환경
- 프로그램 작성 언어: Java 11
- 서버 프레임워크: Spring Boot (Spring Web, Spring Data JPA)
- DBMS: MariaDB

### 배포 환경
- Amazon EC2
  - 프론트엔드와 백엔드 서버를 분리하여 배포
- Amazon RDS (MariaDB)
  - 클라우드 DBMS 사용
- Nginx 웹 서버
  - http/https 요청 리버스 프록시 처리


** **


## :speech_balloon: 서버 API 명세

| 관련 기능    | 요청 URL        | HTTP 메서드     | 
| :----------- | :------------ | :------------ | 
| 로그인 | /login | `POST` |         
| 회원가입 신청 | /signup | `POST` |          
| 회원 ID 중복확인 | /signup/check | `POST` |              
|    |     |       |             
|    |     |       |               
|    |     |       |                    
| 장바구니 목록 조회 | /cart | `GET` |             
| 장바구니 상품 추가 | /cart | `POST` |   
| 장바구니 상품 삭제 | /cart/delete | `POST` |            


** **


## :bulb: 서버에 구현된 기능

### 인증/인가 기능
- JSON Web Token(JWT)을 이용한 인가(authorization)
  - 로그인, 회원가입을 제외한 모든 클라이언트 요청은 유효한 토큰 문자열을 헤더에 포함하고 있어야 함.
  - JWT 검증(validation)은 어플리케이션 서버의 인터셉터 단계에서 작동 (Spring Security 미사용)
  - 주요 관여 클래스: `TokenProvider`, `JwtInterceptor`
- 로그인(인증) 기능
  - 주요 관여 클래스: `AuthController`, `AuthService`, `TokenProvider`, `MemberIdentityRepository`
- 회원 가입 기능
  - 회원 가입 서식에 입력된 정보는 `javax.validation.constraints` API를 이용하여 검증
  - 주요 관여 클래스: `MemberController`, `MemberService`, `MemberRepository`, `Encryptor`



### 상품 검색 기능
- 전체 상품 목록 게시 기능
- 금융상품 유형별/키워드별/검색어별 검색 기능

### 맞춤형 상품 제시 기능
- 
- 

### 관심상품 관리 기능
- 
- 

### 장바구니 관리 기능
- 회원의 장바구니 목록 게시 기능
- 장바구니에 금융상품을 추가하고 삭제하는 기능
- 주요 관여 클래스: `CartController`, `CartItemRepository`



** **


## :slightly_smiling_face: 프로젝트 백엔드 개발 참여자 (총 2명)

### 조혜진 (https://github.com/Johyejin-119)
  - 상품 검색 기능 구현
  - 맞춤형 상품 제시 기능 구현
  - 관심상품 관리 기능 구현
### 이용승 (https://github.com/yongseungLeeDec)
  - 인증/인가 기능 구현
  - 장바구니 관리 기능 구현
  - 서버 배포 및 프론트엔드 연동 테스트
