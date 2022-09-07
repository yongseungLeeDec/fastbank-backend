# Project Fastbank 개요
**서비스 개요**
- Megabyte School K Digital Training 2기 프론트엔드 및 백엔드 개발자 양성과정 수강생의 협동 미니프로젝트
- 구현 목표: 고객이 자신에게 적합한 금융 상품을 탐색할 수 있는 서비스의 기본적인 기능 구현

**참고 링크**
- 서비스 랜딩페이지: https://fastbank.netlify.app 
- 프론트엔드 Repository: https://github.com/hyeonahc/fast-bank-FE 

# 프로젝트에 사용한 도구

**개발 환경**
- 프로그램 작성 언어: Java 11
- 서버 프레임워크: Spring Boot (Spring Web, Spring Data JPA)
- DBMS: MariaDB


**배포 환경**
- Amazon EC2
  - 프론트엔드와 백엔드 서버를 분리하여 배포
- Amazon RDS (MariaDB)
  - 클라우드 DBMS 사용
- Nginx 웹 서버
  - http/https 요청 프록시 및 포트 포워딩

# API 명세
| 관련 기능    | 요청 URL        | HTTP 메서드     | 요청 데이터 형식 | 응답 데이터 형식 | 토큰 필요 여부 |  
| :----------- | :------------: | :------------: | :------------: | :------------: | ------------:|
| git status   |   git status   |    git status  |                |                |              |
| git diff     |    git diff    |      git diff  |                |                |              |


# 구현 기능
**1. 인증/인가 기능**
- JSON Web Token(JWT)을 이용한 인가(authorization)
  - 로그인, 회원가입을 제외한 모든 클라이언트 요청은 유효한 토큰 문자열을 헤더에 포함하고 있어야 함.
- 로그인(인증) 기능
- 회원 가입 기능
  - 회원 가입 서식에 입력된 정보는 javax.validation.constraints API를 이용하여 검증

**2. 장바구니 관리 기능**
- 회원의 장바구니 목록 게시 기능
- 장바구니에 금융상품을 추가하고 삭제하는 기능

**3. 상품 검색 기능**
- 전체 상품 목록 게시 기능
- 금융상품 유형별/키워드별/검색어별 검색 기능

**4. 맞춤형 상품 제시 기능**
- 
- 

**5. 관심상품 관리 기능**
- 
- 

# 참여 인원
- 조혜진
  - 담당: 상품 검색 기능 구현, 맞춤형 상품 제시 기능 구현, 관심상품 관리 기능 구현
- 이용승
  - 담당: 인증/인가 기능 구현, 장바구니 관리 기능 구현, 서버 배포, 프론트엔드 연동 테스트
