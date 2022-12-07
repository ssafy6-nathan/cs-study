# OAuth(Open Authorization)란

- 인증을 위한 개방형 표준 프로토콜
- Third-Party 프로그램에게 리소스 소유자를 대신해서 리소스 서버에 제공하는 자원에 대한 접근 권한을 위임하는 방식을 제공
- 간단하게 말하면 인증 관련된 부분을 OAuth 프로토콜을 제공하는 외부서버에서 대신 처리해줌

# OAuth 1.0

## 역사

- 2006년에 트위터의 개발자와 소셜 북마크 서비스인 Gnolia의 개발자가 만나 인증 방식을 논하면서 시작 됨
    - 그전까지 인증 위임에 대한 표준안이 없었음
    - 2007년 4월 OAuth 논의체를 만어짐
    - 2010년 IETF(국제 인터넷 표준화 기구)에서 OAuth 1.0 프로토콜 표준안 발표

## 용어 정리

- User (사용자)
    - 계정을 가지고 있는 개인
- Consumer (소비자)
    - OAuth를 사용하여 서비스 제공자에게 접근하는 애플리케이션
- Service Provider (서비스 제공자)
    - OAuth를 통해 접근을 지원하는 애플리케이션
- Consumer Secret (소비자 비밀번호)
    - 서비스 제공자에게 소지바임을 인증하기 위한 키
- Request Token (요청 토큰)
    - 소비자가 사용자에게 접근 권한을 인증받기 위해 필요한 정보가 담겨있는 토콘
- Access Token (접근 토큰)
    - 인증 후 사용자가 서비스 제공자가 아닌 소비자를 통해서 자원 접근을 허락하는 키를 갖고 있는 토큰

## 인증 방식

![](https://velog.velcdn.com/images/hsk2454/post/98f84300-dec3-4f8c-9bb2-4386340b6095/image.png)

![](https://velog.velcdn.com/images/hsk2454/post/f477855c-7731-459e-98b0-0ee2a631bcff/image.png)

1. 소비자가 서비스 제공자에게 요청 토큰을 요청한다.
2. 서비스 제공자가 소비자에게 요청 토큰을 발급해준다.
3. 소비자가 사용자를 서비스 제공자로 이동시킨다. 여기서 **사용자 인증이 수행**
4. 서비스 제공자가 사용자를 소비자로 이동시킨다.
5. 소비자가 접근 토큰을 요청한다.
6. 제공자가 접근 토큰을 발행한다.
7. 발급된 접근 토큰을 이용하여 소비자가 사용자 정보에 접근한다.

# OAuth 2.0

## OAuth 1.0과 달라진 점

- 기능의 단순화, 기능과 규모의 확장성 등을 지원하기 위해 만들어짐
- https를 통해 암호화를 하여 과정의 단순화
- 다양한 인증 방식 제공
- API서버에서 인증서버와 리소스 서버가 분리

## 용어 정리

- Resource server
    - 구글/페이스북같은 oauth 제공자
    - 어떤 사람의 자원(Resource)를 가지고 있음
- Client
    - 누군가 만든 애플리케이션, Resource server에 요청하는 객체이기 때문에 사용자에게는 서버지만 client라고 함
- Resource owner
    - client의 서비스를 이용하는 사람 (구글/페이스북 등에 가입된 사람)
- Authorization Server
    - 권한을 부여해주는 서버
    - 사용자는 이 서버로 ID, PW를 넘겨 Authorization Code를 발급 받을 수 있음
    - Client는 이서버로 Authorization Code를 넘겨 Token을 발급 받을 수 있음

## 인증 방식

![](https://velog.velcdn.com/images/hsk2454/post/7525c14d-656f-4be3-96d0-44a98067e055/image.png)

## 인증 종류

### Authorization Code Grant

![](https://velog.velcdn.com/images/hsk2454/post/942f263f-992b-40d5-95f1-2addfe2ef8d4/image.png)

- 서버 사이트 코드로 인증하는 방식
- 권한 서버가 클라이언트와 리소스서버간의 중재 역할
- AccessToken을 바로 클라이언트로 전달하지 않아 잠재적 유출 방식
- 로그인시 페이지 URL에 `response_type=code` 라고 넘김
- 간편 로그인 기능에서 사용
- Refresh Token 사용가능

### Implicit Grant

![](https://velog.velcdn.com/images/hsk2454/post/ec9ac8ab-edb4-436d-9860-6e68053b9965/image.png)

- Token과 Scope에 대한 스펙 등은 다르지만 OAuth1.0과 가장 비슷한 인증 방식
- Public Client인 브라우저 기반의 애플리케이션이나 모바일 애플리케이션에서 이 방식을 사용
- OAuth2.0에서 가장 많이 사용되는 방식
- 권한 코드 없이 바로 발급되서 보안에 취약
- 주로 Read Only인 서비스에 사용
- 로그인시 페이지 URL에 `response_type=token`라고 넘김

### Password Credentials Grant

![](https://velog.velcdn.com/images/hsk2454/post/2f37dbdb-5b1c-4a3b-b09b-d69b552e91d9/image.png)

- Client에 ID/PW를 저장해 놓고, ID/PW로 직접 accesstoken을 받아오는 방식
- Client를 믿을 수 없을 때에는 사용하기 위험해 API 서비스의 공식 어플리케이션이나 믿을 수 있는 Client에 한해서 사용하는 것을 추천
- 로그인시에 API에 POST로 `grant_type=credentails`라고 넘김

### Client Credentails Grant

![](https://velog.velcdn.com/images/hsk2454/post/977d9757-7893-4a47-822a-8ba113741c85/image.png)

- 클라이언트의 자격증명만으로 Access Token을 취득하는 방식
- 로그인시에 API에 POST로 `grant_type=client_credentails`라고 넘김