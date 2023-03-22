## 기능 정리 및 사용 라이브러리 추가 정리

---
### 기능
 1. 블로그 검색
    - 키워드를 통한
    - sorting, pagenation
    - 현재는 kakao, naver 검색 API 사용  
        - kakao를 먼저 호출하며, kakao 에 문제가 있을 경우 naver를 호출
        - 추후 추가될 가능성 염두 
 2. 인기 검색어 목록
    - 많이 검색한 순서
    - 최대 10개
    - 검색 횟수도 함께 표기
    

---
### System Spec
 1. Spec
     - Java 17
     - Spring Boot 3.0
     - gradle
 1. 패키징 된 파일은 간단하게 실행이 가능해야 한다
    - java -jar ${FILENAME}.jar 로 실행
    - 이를 위해, db 는 in-memory db 사용
 1. 멀티 모듈로 구성
    - 모듈간 의존성 최소화
 1. 대용량 트래픽을 전제로 구성
 1. 비즈니스 로직은 Test 최대한

---
