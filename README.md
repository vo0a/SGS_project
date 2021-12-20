# [SGS] Authorization System
> 21.12.14 ~ 21.12.20

## 아키텍처
![image](https://user-images.githubusercontent.com/44438366/146734773-da3db1ac-e689-474a-a388-0577dc4945bb.png)


## 기능
- 사용자 회원가입/로그인 : auth
  - Spring security + JWT
  - MySQL
  - Password Encryption
- 유저 관리 페이지 : membership
  - 전체 사용자 목록 보기
  - 특정 사용자 정보 보기 
- MSA : eureka, gateway
  - eureka 가 모든 서버를 인식하고
  - gateway 가 라우팅한다.


## 보완사항
- 사용자 탈퇴
  - MySQL 에서 비활성 상태로 변경 후
  - redis 사용하여 사용자 정보 30일간 임시 저장, 30일 이후 삭제
- Password Encryption 직접 구현하기
- E-Mail 인증
  - 회원가입시 이메일 인증하기
  - 비밀번호 찾기