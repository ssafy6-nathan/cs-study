## REST란? 
- **RE**presentational(표현) **S**tate(상태) **T**rasnfer(전달)
- 자원을 이름(표현)으로 구분하여 해당 자원의 상태(정보)를 주고 받는 모든 것
- 자원의 표현 : HTTP URI     ex)/board/:board_id
- 상태 전달 : HTTP Method
- HTTP URI를 통해 자원을 명시하고, HTTP Method(POST, GET, PUT, DELETE)를 통해 해당 자원에 대한 CRUD Operation을 적용하는 것을 의미함
####        

## REST 아키텍쳐의 제약조건
**1. Server - Client (서버-클라이언트 구조)**
- Client가 Server에게 Request보내고, Server가 Client에게 Response를 보내는 구조
- 서버 : API제공과 제공된 API를 이용하여 비즈니스 로직을 처리하거나 저장하는 역할
- 클라이언트 : 사용자 인증이나 컨텍스트(세션, 로그인 정보)등을 관리하는 역할
    
**2. Stateless (무상태)**
- 클라이언트와 서버의 통신에는 상태가 없어야 한다.
  = 모든 요청은 필요한 모든 정보를 담고 있어야 한다.
- 클라이언트의 컨텍스트를 서버에 저장하지 않는다 -> 세션과 쿠키 등의 정보를 신경쓰지 않아도 되므로 구현이 단순해짐
- 서버는 각각의 요청을 완전히 별개의 것으로 인식, 처리한다.
	= 이전 요청이 다음 요청의 처리에 연관되어서는 안된다.
    - 서버의 처리 방식에 일관성을 부여하고 부담이 줄어들며, 서비스의 자유도가 높아짐
    
**3. Cacheability(캐시 가능성)**
- 웹 표준 HTTP 프로토콜을 그대로 사용하므로 웹에서 사용하는 기존의 인프라를 그대로 활용할 수 있다.
	= HTTP가 가진 특징 중 하나인 캐싱 기능 적용 가능
    
**4. Uniform Interface (인터페이스 일관성)**
- URI로 지정한 리소스에 대한 조작을 통일되고 한정적인 인터페이스로 수행한다.
- 4가지 제약조건
 	- identification of resources(자원의 식별)
  	- manipulation of resources through representation(메시지를 통한 리소스의 조작)
 	- self-descriptive messages(자기서술적 메시지)
 	- hypermedia as the engine of application state(애플리케이션의 상태에 대한 엔진으로서 하이퍼미디어)

**5. Layered System (계층화)**
- 서버는 다중 계층으로 구성될 수 있다.
	- 보안, 로드 밸런싱, 암호화 계층을 추가해 구조상의 유연성을 둘 수 있다.
- 프록시, 게이트웨이 같은 네트워크 기반의 중간 매체를 사용할 수 있다.

**6. Code On Demand(optional)**
- 서버는 소프트웨어 프로그래밍 코드를 클라이언트에 전송하여 클라이언트 기능을 일시적으로 확장하거나 사용자 지정할 수 있다.
- 반드시 충족할 필요는 없음
- ex) 웹 사이트에서 등록 양식을 작성하면 브라우저는 잘못된 전화번호와 같은 실수를 즉시 강조 표시함 

####  
## RESTful란?
#### - REST라는 아키텍쳐 스타일의 제약조건을 모두 만족하는 시스템을 나타내기 위해 사용되는 용어  

####  
## RESTful API란?
**- API(Application Programming Interface)**  
- 데이터와 기능의 집합을 제공하여 컴퓨터 프로그램간 상호작용을 촉진하며, 서로 정보를 교환 가능하도록 하는 것

**- RESTful API**
- REST 기반으로 서비스 API를 구현한 것
- 최근 Open API, MSA 등을 제공하는 업체 대부분은 REST API를 제공한다.
- REST는 HTTP 표준을 기반으로 구현하므로, HTTP를 지원하는 프로그램 언어로 클라이언트, 서버를 구현할 수 있다.

## RESTful API 규칙
**1. 리소스와 행위를 명시적이고 직관적으로 분리한다.**
- URI는 정보의 자원을 표현해야 한다.(리소스명은 동사보다는 명사 사용)
```
	GET /members/delete/1    -> X
	DELETE /members/1        -> O
	-> URI는 자원을 표현하는데 중점을 두어야 하며, delete와 같은 행위에 대한 표현이 들어가면 안됨
```
- 자원에 대한 행위는 HTTP Method로 표현하고, GET, POST, PUT, PATCH, DELETE를 분명한 목적으로 사용해야 한다.

**2. Message는 Header와 Body를 명확하게 분리해서 사용한다.**
- Entity에 대한 내용은 body에 담는다.
- API 버전 정보, 응답받고자 하는 MIME타입 등은 header에 담는다.
- header와 body는 http header와 http body로 나눌 수도 있고, http body에 들어가는 json 구조로 분리할 수도 있다.

**3. API 버전을 관리한다**
- 특정 API를 변경할 때는 반드시 하위호환성을 보장해야 한다.

**4. 서버와 클라이언트가 같은 방식을 사용해서 요청하도록 한다.**
- 브라우저는 form-data로 보내고 서버는 json 형태로 보내는 식의 분리보다는 하나로 통일한다.

### * 설계 방법
**1. 슬래시 구분자(/)는 계층 관계를 나타내는데 사용하며, URI 마지막 문자로 슬래시(/)를 포함하지 않는다.**
```
   http://restapi.example.com/houses/apartments    -> O
   http://restapi.example.com/houses/apartments/   -> X
```
**2. 하이픈(-)은 URI 가독성을 높이는데 사용한다.**
- URI를 쉽게 읽고 해석하기 위해 불가피하게 긴 URI경로를 사용하게 된다면 하이픈을 사용하여 가독성을 높일 수 있다.

**3. 밑줄(__)은 URI에 사용하지 않는다.**
- 글꼴에 따라 밑줄은 보기 어렵거나 문자를 가릴 수 있기에 밑줄 대신 하이픈(-)을 사용한다.

**4. URI 경로에는 소문자가 적합하다.**
- 대소문자에 따라 다른 리소스로 인식하게 되기 때문에 대문자 사용은 피해야 한다.

**5. 파일 확장자는 URI에 포함시키지 않는다.**

**6. 리소스 간 연관 관계가 있고 관계가 복잡하다면 서브 리소스에 명시적으로 표현한다.**
```
	GET : /users/{userId}/devices (일반적으로 소유'has'의 관계 표현 시)
    GET : /users/{userId}/likes/devices (관계명이 애매하거나 구체적 표현 필요 시)
```
**7. Collection은 복수, Document는 단수로 한다면 좀 더 직관적인 URI 설계가 가능하다.**
- Collection : 문서들의 집합, 객체들의 집합
- Document : 문서, 객체
```
	http:// restapi.example.com/sports/soccer/players/13
    -> sports와 players는 컬렉션이며 복수형으로 표현
       soccer와 13은 도큐먼트이며 단수형으로 표현
```
####  

## RESTful API의 장점
#### 1. 확장성
REST가 **클라이언트-서버** 상호 작용을 최적화하기 때문에 효율적으로 크기를 조정할 수 있다.  
**Stateless**는 서버가 과거 클라이언트 요청 정보를 유지할 필요가 없기 때문에 서버 로드를 제거한다.  
잘 관리된 **캐싱**은 일부 클라이언트-서버 상호작용을 부분적으로 또는 완전히 제거한다.  
-> 이러한 모든 기능은 성능을 저하시키는 통신 병목 현상을 일으키지 않으면서 확장성을 지원한다.
#### 2. 유연성
완전한 클라이언트-서버 분리를 지원하기에 각 부분이 독립적으로 발전할 수 있도록 서버 구성 요소를 단순화하고 분리한다.  
-> 서버 애플리케이션의 플랫폼 또는 기술 변경은 클라이언트에게 영향을 주지 않는다. 애플리케이션 함수를 계층화하는 기능은 유연성을 더욱 향상시킨다.
#### 3. 독립성
REST API는 사용되는 기술과 독립적이다.  
API 설계에 영향을 주지 않고 다양한 프로그래밍 언어로 클라이언트 및 서버 애플리케이션을 모두 작성할 수 있으며, 또한 통신에 영향을 주지 않고 양쪽의 기본 기술을 변경할 수 있다.
####
####  
####  

    
```
참고자료
https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html
https://aws.amazon.com/ko/what-is/restful-api
https://github.com/JaeYeopHan/Interview_Question_for_Beginner/tree/master/Development_common_sense#restful-api
https://meetup.toast.com/posts/92
```
