# 목차
- [1. 개발환경](#개발환경)
- [2. 설계](#설계)
- [3. 과제기능 요구사항](#과제기능-요구사항)
- [4. 추가요건 문제해결 전략](#추가요건-문제해결-전략)


# 개발환경

### ▶기본환경

분야| stack |
--|--|
 |언어 | Java 11|
 |프레임워크 | springBoot 2.7.9
 |DB | h2-2.1.212
 |빌드 툴 | Gradle
 |API 테스트 툴 | Postman |
 |ERD 작성 툴 | StarUML |
 | IDE | Intelij |
 | 테스트 | JUNIT |
 

### ▶활용 오픈소스

- DB 접근 기술 : ORM[h2 + spring boot data jpa + hibernate]
  - 검색 키워드 저장소로 h2를 사용하고 이에 접근하기 위한 기술로 ORM framework (jpa + hibernate)을 활용했습니다. MyBatis와 같은 mapper framework와 비교해 유지보수성이 뛰어나
    고, 무엇보다 OOP(도메인 모델 구조)에 적합하기 때문에 선택했습니다.
- 분산 Lock : [redisson]
  - 동시성 이슈 해결을 위해 분산 Lock 기술을 채택했고 그 구현체로 redisson을 선택했습니다. 어플리케이션 레벨에서 해결하는 일반적인 동시성 기술이 분산 환경에서 갖는 한계점을 고려하
    여 선택했습니다.
- API 클라이언트 : [spring boot webflux]
  - 외부 API 호출 클라이언트 기술로 webflux를 활용했습니다. 추후 카카오 API 이외에 다른 API도 함께 호출하는 것을 고려했을 때 병렬 호출도 지원해주는 webflux를 선택했습니다.
- 캐시 : [spring boot cache + spring boot data redis]
  - 동일한 키워드에 대한 검색 결과가 변경되는 시점까지 짧지 않은데, API 호출 시 발생하는 병목 지점을 해결하기 위해 캐시를 선택했고, 분산 환경을 고려하여 redis를 선택했습니다.
- API Document : [springdoc openapi]
  - API 문서화와 브라우저에서 테스트 가능하게끔 openapi(swagger)를 적용했습니다.
- 기타 : [lombok]
  - 개발 편의성을 위해 lombok을 사용했습니다.

---

# 설계

### ▶시스템 설계

![시스템구성도](https://user-images.githubusercontent.com/20380910/226425635-a57c8c5e-1948-4022-a82f-f17aa8608054.png)


### ▶어플리케이션 설계

![애플리케이션](https://user-images.githubusercontent.com/20380910/226426067-73e4253d-03de-417c-8d03-72ce9dc9bf1c.png)

- 어플리케이션은 4계층 구조로 **의존성 역전(DIP)**를 적극적으로 활용하여 domain, application과 같은 고수준 모듈이 repository, infrastructure와 같은 저수준 모듈의 변경으로부터 자유롭도록 설계하였습니다.
 
- presentation layer
  - 사용자에게 요청을 받아 application layer에 요청을 위임하고 결과를 응답합니다. api-doc 등의 요청/응답과 관련된 기술 요소를 구현합니다. 필요한 요청을 위임하기 위해 domain layer에서 제공하는 추상화된 Service에 의존합니다.

- application layer
  - 시스템이 사용자에게 제공할 어플리케이션 로직 구현을 담당합니다. 이를 위해 domain layer의 도메인 모델을 사용합니다. 또한 상세 구현(키워드 검색 결과에 필요한 로직 등)을 위해    
    domain layer에서 제공하는 추상화된 function 등을 구현합니다. 필요한 도메인 객체를 얻기 위하여 domain layer 에서 제공하는 추상화된 Repository에 의존합니다.

- domain layer
  - 비즈니스/도메인 규칙과 관련된 핵심 로직을 수행하는 Keywords, Blogs과 같은 도메인 모델이 위치합니다. 또한 각 도메인에서 사용되는 인터페이스(Service, Repository, 
    Function 등)를 제공합니다. 외부 저장소, 영속성, 화면 등과 같은 기술 요소에 의존하지 않기 떄문에 스프링, DB 등의 기술 요소에 대한 의존성이 없습니다.

- infrastructure layer
  - 도메인 모델을 저장하거나 가져오기 위해 h2, JPA, Cache, WebClient 등과 같은 기술 요소들을 이용하여 구현합니다. domain layer에서 제공하는 추상화된 repository를 구현합니다.

  
### ▶오류 및 장애처리
- 에러 코드와 Exception에 대한 추상 타입을 두고 각 계층에서 상속 및 구현하여 사용할 수 있도록 표준화를 했습니다.
  이에 따라 도메인/비즈니스 규칙에 대한 예외, 서비스 호출에 대한 예외, 기술적인 예외 구분과 후처리에 대한 판단이 용이합니다.

---

# 과제기능 요구사항

### ▶블로그 검색
- 카카오 블로그 검색 API의 입출력 레이아웃에 맞추어 설계했습니다.
  - 필수값 : query(검색질의어) / 선택값 : sort(정렬방식), page(결과 페이지번호) , size(한페이지당 문서수)
- 추후 카카오 API 이외에 새로운 검색 소스가 추가될 수 있음을 고려하여 API CLIENT의 추상타입을 정의했습니다.
  - 템플릿 메서드 패턴을 적용한 API의 추상 타입(ClientMonoBuilder) 정의. 새로운 API가 추가될 때 ClientMonoBuilder를 상속하여 추상 메서드를 구현하도록 설계했습니다.
    - 추가된 API 제공자 별 필요한 정보(host, path, header 등) 저장
    - 추가된 API 제공자의 응답 결과 Response Body를 BlogsEntities로 파싱


### ▶인기 검색어 목록
- 후행처리 모듈(postProcessor)을 정의하여 검색순위, 최대표출 갯수를 filter하여 제공합니다.

---

# 추가요건 문제해결 전략

### ▶동시성 이슈
검색 키워드 횟수 증가에 대한 동시성 문제를 **분산 Lock**을 활용하여 제어하는 방향으로 설계 적용했습니다.
일반적으로 어플리케이션 코드 레벨에서 동시성을 제어하는 경우 분산 환경에서 적합하지 않고, DB lock에 의존하는 경우 성능 및 과부화 이슈가 존재하는데, **redisson**은 분산 환경에서도 적합하고 spin lock 구조가 아니기 때문에 과부화 이슈도 줄어들어 효과적으로 동시성을 제어할 수 있습니다.


### ▶대용량 트래픽 이슈
- 키워드 검색 시, 검색 횟수를 저장하기 위한 서비스 호출에 대해 **별도의 스레드를 생성 및 비동기로 수행**하여 대용량 트래픽 환경에서 검색 결과에 대한 응답 속도을 개선했습니다.
- 키워드 검색 시 kakao외 여러 API가 동시에 고려하여 WebClient를 활용했습니다. Non-Blocking 방식으로 네트워킹의 병목현상이 줄어들고, 추후 병렬 처리를 수행한다면 응답속도과 처리량을 개선이 될 것입니다.
- 키워드 검색 시, 결과를 Cache에 저장(만료 1시간)하여 동일한 검색 키워드에 대해 API 호출을 하지 않고 Cache 결과를 사용하기 때문에 API 호출을 위한 병목 지점을 줄여서 응답속도와
  처리량이 개선될 것입니다.
