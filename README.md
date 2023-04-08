
# My Little Blog는 블로그 API 서버입니다!

## ✅ 사용 기술 분석 [2023/04/07 리뉴얼]

 * [[토이] 나의 작은 블로그 프로젝트 계획](https://www.notion.so/2bf997970a89499eb8f98a26c9e4fb55) `07/21`

 * [[토이] 프로젝트 개요 정리하기](https://www.notion.so/2cf661a9ff3e4e16ae21132f90a2ba91) `07/22`
    
  - Spring Boot Starter
      - Project : Gradle Project
      - Spring Boot Version : 2.7.10
      - Group : com.project
      - Artifact: mlb
      - Packaging : Jar
      - Java Version : 11
      - Dependencies
          - Spring Boot (2.7.10ver)
          - Lombok
          - MySQL Driver
          - Spring Data JPA
          - 추후 추가
  - 사용 기술
      - MySQL 8.0
      - Spring Data JPA (구현체는 Hibernate)
      - Java11
      - Spring Boot 2.7.10
      - Gradle
      - 추후 추가 

<br>

## ✅ 요구 사항 분석 및 DB 설계 과정 [2022/07/24 ~ 27]
- [[MLB] 요구 사항 분석, 트랜잭션 분석](https://www.notion.so/MLB-1daf2a960b074ce494d53a1ba5324410)
- [[MLB] 개념적 설계(ERD)](https://www.notion.so/MLB-ERD-764e69d67974466eab87a48fb2a769ca)  
- [[MLB] 개념적 설계(트랜잭션 모델링)](https://www.notion.so/MLB-5dde233abf04458eb8cc428192969d08) 
- [[MLB] 논리적 설계(릴레이션 스키마, 무결성 제약조건 정의), 물리적 설계(내부 스키마)](https://www.notion.so/MLB-914e6d0f29cb4b15a4d6fd9510892acd)


## ✅ 논리적 설계, 물리적 설계 점검 및 개편 [2023/04/09]
- 다대다 관계는 전부 일대다 다대일로 풀기
- 별도의 테이블로 분리해야 될 부분들 점검하기
### 점검사항
- ARTICLE → POST로 테이블명 변경
- 댓글은 대댓글 구현을 위해 계층형 테이블 구조로 변경험
- 카테고리를 별도의 테이블로 분리함, 또한 카테고리의 계층을 구현하기 위해 계층형 테이블 구조로 변경함

## ✅ 최종 ERD
![image](https://user-images.githubusercontent.com/66772624/230731706-48db905b-186f-4f72-bfba-da047b145f17.png)
