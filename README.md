# http-1.1-With-Java

Http 1.1 통신 및 JSON 데이터 처리 어플리케이션 By Java

### Description
1. http-web-server
    - com.sun.net.httpserver 라이브러리를 이용한 Http 서버
    - http://localhost:9999 에서 대기
    - GET, POST 지원
    - 최대 10개 스레드를 이용한 요청 처리
2. http-web-clienty
    - Http Client 및 okHttp를 이용한 Http 클라이언트 
    - POST에서 Header 관리를 우해 okHttp 사용
3. http-json-example
    - com.googlecode.json-simple 이용한 JSON File Reader
4. http-json-example
    - com.googlecode.json-simple 이용한 JSON File Writer
    - GSON을 이용한 json prettify
5. http-json-example
    - JSON object 생성 및 JSON File 작성
6. http-json-example
    - JSON object 생성 & JSON File Write + Read
7. rest-server
    - Simple Rest Server with Spring Boot
    - JSON Object 사용해 클라이언트와 통신
    - memberId 기반 CRUD
8. rest-client
    - Simple Rest Client using HttpClient
    - 6 Test Cases

### Environment
- IDE : Intellij
- JDK : openjdk 11.0.14 
