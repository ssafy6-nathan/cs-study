1. 웹 브라우저에 URL 입력
2. DNS를 통해 브라우저는 도메인 네임의 ip address 찾아본다.
    - 로컬 컴터의 캐시 찾아봐
        - Browser Cache - 
          브라우저는 특정 기간동안 유저가 방문한 웹사이트의 DNS 기록을 보관한다. 따라서 브라우저는 가장 먼저 Browser Cache를 찾아본다.
        - OS Cache - 
          Browser Cache에 DNS 기록이 없다면, 브라우저는 OS에 시스템 콜(system call)을 생성해 DNS 기록을 조회한다.
        - Router Cache - 
        브라우저와 OS 등 유저의 컴퓨터에 기록이 없다면, 브라우저는 자체 DNS 캐시를 유지관리하는 라우터와 통신한다.
    - 캐시에 없으면
        - ISP(Internet Service Provider) Cache - 
        유저의 ISP는 DNS 레코드의 캐시를 포함하는 자체 DNS 서버를 유지관리한다.
        - ISP의 DNS 서버는 DNS 쿼리로 도메인 네임을 호스팅하는 서버의 IP 주소를 찾는다.
        - ISP란, 개인이나 기업체에게 인터넷 접속 서비스, 웹사이트 구축 및 웹호스팅 서비스 등을 제공하는 회사
3. 브라우저가 올바른 IP 주소를 찾은 경우, 브라우저는 서버와 정보를 교환하기 위해 TCP(Transfer Control Protocol) 연결을 시작한다.
4. 브라우저는 서버에 http request 보낸다. (GET이나 POST)
5. 서버는 브라우저에 요청 처리하고 http response 보낸다. (HTML, JSON, XML)
6. 브라우저는 html을 렌더링한다.
    
    HTML 렌더링 → html 이미지, css, js에 임베디드 된 다른 정보들을 가져오는거지.(by 추가적인 요청) 
    
    정적파일들은 브라우저에 캐싱되어 다음에 방문하면 다시 요청을 보내지 않는다.


PLUS!
**URI와 URL의 차이점**

> Uniform Resource Identifier (URI)>  consists of a string of characters used to identify or name a resource on the Internet
> 

네트워크 상에서 **자원을 식별하기 위한 문자열의 구성**이다. URI는 인터넷에서 요구되는 기본 조건으로써 인터넷 프로토콜에 항상 붙어 다닌다.

> Uniform Resource Locator (URL)>  colloquially termed a web address, is a reference to a web resource that specifies its location on a computer network and a mechanism for retrieving it.
> 

구어적으로 웹 주소라고 일컫고, 네트워크 상에서 웹 자원의 위치를 지정하는 웹 자원에 대한 참조이다. 즉, 쉽게 말하면 네트워크 상의 **자원의 위치를 나타내는 주소**이다.

### URL은 URI에 포함된다!

1. [https://example.io](https://example.io/) 
2. [https://example.io/images](https://example.io/images) 
3. [https://example.io/images/dog.jpeg](https://example.io/images/dog.jpeg) 
4. [https://example.io/user/123](https://example.io/user/123) 
5. [https://example.io/profile?id=11](https://example.io/profile?id=11)
