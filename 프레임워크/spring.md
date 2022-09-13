# 목차
- [Web개발에서 자바 진영의 역사](#web개발에서-자바-진영의-역사)
- [Spring](#spring)
- [Ioc Di 컨테이너](#ioc-di-컨테이너)
- [Spring boot](#spring-boot)
- [Spring MVC](#spring-mvc)
- [Filter vs Interceptor](#filter-vs-interceptor)
- [Spring Bean](#spring-bean)
- [Spring Security](#spring-security)



# Web개발에서 자바 진영의 역사

- Spring 프레임워크가 나오기 이전의 자바 진영에서는 EJB(Enterprise JavaBeans)를 표준으로 사용해서 Web을 개발했음
- 복잡하고 프레임워크 종속적인 EJB에 반하는 개념으로 순수 자바 객체(POJO - Plain Old Java Object)라는 용어가 등장함
- 2002년 로드 존슨이라는 개발자가 EJB의 문제점을 지적하면서 `J2EE Design and Development`라는 책을 냄
    - 이 책에서는 EJB 없이도 충분히 고품질의 확장 가능한 애플리케이션을 개발할 수 있음을 보여줌
    - 이 책에 지금의 스프링 핵심 개념과 기반 코드가 들어 있음
    - BeanFactory, ApplicationContext, POJO(Plain Old Java Object), 제어의 역전, 의존관계 주입
- 책 출간 후 유겐 휠러, 얀 카로프가 로드 존슨에게 오픈소스 프로젝트를 제안
- 스프링 이름은 전통적인 J2EE(EJB)라는 겨울을 넘어 새로운 시작이라는 뜻으로 지음

<br>

# Spring
- 자바 플랫폼을 위한 오픈소스 애플리케이션 프레임워크로 엔터프라이즈급 애플리케이션 개발을 위한 경량화된 솔루션
- 자바 언어의 큰 특징은 객체 지향 언어 -> 스프링은 객체 지향 언어가 가진 강력한 특징을 살려내는 프레임워크

## 좋은 객체 지향 설계의 5가지 원칙 (SOLID)
- 클린코드의 저자 로버트 마틴이 정리한 5원칙
1. SRP(Single Responsibility Principle) : 단일 책임 원칙
    - 한 클래스는 하나의 책임만 가져야 한다.
    - 중요한 기준은 `변경`, 변경이 있을 때 파급 효과가 적으면 SRP원칙을 잘 따른 것
2. OCP(Open/Closed Principle) : 개방-폐쇄 원칙
    - 소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.
    - 다형성 활용(단 다형성만 사용해선 원칙 지킬 수 없음)
    - 객체를 생성하고, 연관관계를 맺어주는 별도의 조립, 설정자가 필요함
3. LSP(Liskov Substitution Principle) : 리스코프 치환 원칙
    - 프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다.
    - 다형성에서 하위 클래스는 인터페이스 규약을 다 지켜야 한다, 다형성을 지원하기 위한 원칙, 인터페이스를 구현한 구현체는 믿고 사용하려면, 이 원칙이 필요 (ex - 자동차 인터페이스의 엑셀은 앞으로 가라는 기능, 뒤로 가게 구현하면 LSP 위반, 느리더라고 앞으로 가야함)
4. ISP(Interface Segregation Principle) : 인터페이스 분리 원칙
    - 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.
    - ex) 자동차 인터페이스 -> 운전 인터페이스, 정비 인터페이스로 분리
    - 인터페이스가 명확해지고, 대체 가능성이 높아진다.
5. DIP(Dependency Inversion Principle) : 의존관계 역전 원칙
    - 프로그래머는 "추상화에 의존해야지, 구체화에 의존하면 안된다." 의존성 주입은 이 원칙을 따르는 방법 중 하나
    - 구현 클래스에 의존하지 말고 인터페이스에 의존하라는 뜻
- 다형성만 갖고 OCP와 DIP를 지킬 수 없음 뭔가 더 필요

## 스프링과 객체 지향
- 스프링은 다음 기술로 다형성 + OCP, DIP를 가능하게 지원
    - DI(Dependency Injection) : 의존관계, 의존성 주입
    - DI 컨테이너 제공
- 클라이언트 코드의 변경 없이 확장 가능

<br>

# IoC, DI, 컨테이너
## 제어의 역전 IoC(Inversion of Control)
- 기존의 프로그램은 클라이언트 구현 객체가 스스로 필요한 서버 구현 객체를 생성하고, 연결하고, 실행함, 한마디로 구현 객체가 프로그램의 제어 흐름을 스스로 조종, 개발자 입장에서는 자연스러운 흐름임

## 프레임워크 vs 라이브러리
- 내가 작성한 코드를 제어하고, 대신 실행하면 프레임워크(프레임워크가 지정한 규칙에 따라서 코드를 작성하면)
- 내가 작성한 코드가 직접 제어의 흐름을 담당한다면 라이브러리(라이브러리의 클래스나 메소드를 내 입맞에 맞춰서 가져다 사용하면)

## 의존관계 주입 DI(Dependency Injection)
### 의존관계
- 의존관계란 의존대상 B가 변하면 그것이 A에 영향을 미치는 것이라고 표현할 수 있음
```java
class A {
  public void func() {
    ~~~
  }
}

class B {
  static A a = new A();

  public void func2() {
    a.func();
  }
}
```
> B클래스는 A클래스의 `func()` 함수 내용이 변하면 영향을 받으므로 B는 A에 의존하고 있음

- 의존관계 주입을 사용하면 클라이언트 코드를 변경하지 않고, 클라이언트가 호출하는 대상의 타입 인스턴스를 변경할 수 있음

## IoC 컨테이너, DI 컨테이너
- 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것을 IoC 컨테이너 또는 `DI 컨테이너`라고 함 (또는 어셈블러, 오브젝트 팩토리 등으로 불림)
- IoC컨테이너는 `ApplicationContext`에서 관리됨

## Bean
- IoC 컨테이너가 관리하는 자바 객체를 빈이라고 부름
- 우리가 그저 `new`연산자로 생성한 객체는 빈이 아님
- `ApplicationContext.getBean()`으로 얻을 수 있는 객체가 빈

![img](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FnsE6w%2Fbtq9flCz2G4%2FumjyApnZqSkHTQZ4Aa1f60%2Fimg.png)

## BeanFactory
- 스프링 컨테이너의 최상위 인터페이스
- Bean을 등록, 생성, 조회, 반환 관리 기능이 존재
- 보통은 BeanFactory를 바로 사용하지 않고 이를 확장한 ApplicationContext를 사용

## ApplicationContext
- BeanFactory를 확장한 인터페이스
- 다양한 부가 기능을 추가로 제공함
  - 국제화가 지원되는 텍스트 메시지 관리
  - 이미지같은 파일 자원을 로드할 수 있는 포괄적인 방법 제공
  - 리스너로 등록된 빈에게 이벤트 발생을 알려줌

<br>

# Spring boot
- 스프링은 기능이 많은만큼 환경설정이 복잡함 -> 이것을 편리하게 만들어준 것이 스프링 부트

## Spring보다 편리한 점
- dependency 설정 간소화
- 손쉬운 빌드 구성을 위한 starter dependency 제공
- Tomcat 같은 웹 서버를 내장해서 별도의 웹 서버를 설치하지 않아도 됨
- 스프링과 3rd party 라이브러리 자동 구성
- 메트릭, 상태 확인, 외부 구성 같은 프로덕션 준비 기능 제공

<br>

# Spring MVC
- Spring MVC란 Spring에서 제공하는 웹 모듈로 `Model`, `View`, `Controller` 세가지 구성요소를 사용해 사용자의 다양한 HTTP Request를 처리할 수 있게 만든 프레임워크 (MVC 패턴 적용)

## MVC 패턴

- 컴포넌트의 변경이 다른 컴포넌트에 영향을 미치지 않음
    - 유지보수 용이
- 컴포넌트 간의 결합성이 맞아 프로그램 수정이 용이
    - 확장성 뛰어남
- `Model`
    - 어플리케이션의 데이터, 정보, DB 등을 말함
- `View`
    - 사용자에게 보여지는 화면, UI
- `Controller`
    - 데이터와 비즈니스 로직 사이의 상호 동작을 관리
    - 어플리케이션의 행위 정의
    

## Spring MVC 구조

![image](https://user-images.githubusercontent.com/47655983/183898143-61d40d36-646d-4c8b-b825-208fea3dc8f2.png)

![image](https://user-images.githubusercontent.com/47655983/183898215-79efc6c5-24f3-46b2-803f-efd63892dfde.png)

- MVC 패턴에서 Controller는 제일 앞단에서 클라이언트의 요청을 받아 적절한 처리를 해줌
    - 하지만 Spring에서는 클라이언트의 요청을 `DispatcherServlet`이라는 녀석이 제일 먼저 받아서 적절한 Controller로 요청을 위임함 (Front Controller 패턴)
- `HandlerMapping`
    - 클라이언틔 요청을 어떤 Controller가 처리할지 결정
    - URL과 요청 정보를 기준으로 어떤 핸들러 객체를 사용할지 결정함
- `ModelAndView`
    - Controller가 처리한 데이터 및 화면에 대한 정보를 보유한 객체
- `ViewResolver`
    - Controller가 리턴한 View 이름을 기반으로 Controller의 처리 결과를 보여줄 View 결정

<br>

# Filter vs Interceptor
![image](https://user-images.githubusercontent.com/47655983/183898401-7b54cb7c-3bfa-41e2-b2bb-3f8f05d30694.png)

## Filter
- J2EE 표준 스펙 기능
- `DispatcherServlet`에 요청이 전달되기 전/후에 url 패턴에 맞는 모든 요청에 대해 부가작업을 처리하는 기능 제공 (ex - 인코딩 변환 처리, XSS 방어, 공통된 보안 인증)
- `DispatcherServlet`은 Spring의 맨 앞단이기 대문에 `Filter`는 Spring 범위 밖에서 처리가 됨
- 즉 스프링 컨테이너가 아닌 톰캣과 같은 웹 컨테이너에 의해 관리(스프링 빈으로 등록은 됨)
- `web.xml`에서 설정
- Request/Response 객체 조작 가능

![image](https://user-images.githubusercontent.com/47655983/183898421-6f5dd987-6fc0-48a8-a841-e7c8ff17fb41.png)

## Interceptor
- J2EE 표준 스펙이 아닌 Spring이 제공하는 기술
- `DispatcherServlet`이 컨트롤러를 호출하기 전과 후에 요청과 응답을 참조하거나 가공할 수 있는 기능 제공 (ex - 로그인 체크, 권한 체크, 세부적인 보안 인증, `Controller`로 넘어가는 데이터 가공)
- `spring-servlet.xml`에서 설정
- Controller 마다 Handler가 매핑되어 있는데 권한 체크 등을 핸들러에서 해준다고 하면 Controller가 많아질수록 작성해야하는 코드량이 많아짐 → `Interceptor` 클래스에 위임해서 작업하면 반복되는 코드를 줄일 수 있음
- Request/Response 객체 조작 불가능

# Spring Bean

- Spring IoC 컨테이너가 관리하는 자바 객체를 Bean이라고 부름

![image](https://user-images.githubusercontent.com/47655983/189898967-599d7f26-27b9-41d7-a263-39d80e7572b4.png)

- 개발자가 만든 자바 객체를 사용하려면 Bean으로 Spring IoC 컨테이너에 등록해야 함
- Bean 등록 방법
    - XML 파일에 정의하는 방법
    - 소스코드에서 어노테이션(`@Bean`, `@Conponent`)을 이용한 방법 (Spring boot)
    
    User Controller, User Service, UserRepository 1
    
    Notice Service 
    

## Spring Bean Scope

- Spring은 기본적으로 모든 Bean을 싱글톤으로 생성하여 관리
    - Spring IoC 컨테이너를 통해 Bean을 주입받으면 언제나 동일한 객체를 받음
- 하지만 모든 Bean이 싱글톤인 것은 아니고 Bean Scope에 따라 달라짐
- Bean Scope 종류
    - singleton
    - prototype
    - request
    - session
    - application
    - websocket

### Singleton

- Spring IoC 컨테이너에서 Bean 객체가 딱 한 번만 생성되는 Scope
- 생성된 하나의 인스턴스는 Single Beans Cache에 저장되고, 해당 Bean에 대한 요청과 참조가 있으면 캐시된 객체를 반환
- 기본적으로 모든 Bean은 Scope가 명시적으로 지정되지 않으면 Singleton Scope

### Prototype

- 모든 요청에서 새로운 객체를 생성하는 것을 의미
    - 즉 Prototype Bean은 의존성 관계의 bean에 주입 될 때 새로운 객체가 생성되어 주입 됨

## Web Scope

- Singleton, ProtoType Scope는 스프링 자체에서 제공하는 Scope이고 Request, Session, Application, WebSocket Scope는 Spring MVC Web Application에서 제공하는 Web Scope

### Request

- HTTP 요청 하나가 들어오고 나갈 때까지 유지되는 Scope
- 각각의 HTTP 요청마다 별도의 Bean 객체가 생성되고 관리 됨

### Session

- HTTP Session과 동일한 생명 주기를 가지는 Scope

### Application

- Servlet Context와 동일한 생명 주기를 가지는 Scope

### WebSocket

- WebSocket과 동일한 생명 주기를 가지는 Scope

# Spring Security

- Spring 기반의 어플리케이션 보안(인증과 권한, 인가 등)을 담당하는 스프링 하위 프레임워크
- 인증과 권한에 대한 부분을 Filter의 흐름에 따라 처리하고 있음
- 보안 관련해서 체계적으로 많은 옵션을 제공
    - 개발자가 일일이 보안관련 로직을 작성하지 않아도 됨

![image](https://user-images.githubusercontent.com/47655983/189899116-6d4dc42d-3dc9-4038-baf2-99d10fc0c931.png)

## 인증과 인가

- 인증(Authorization)
    - 해당 사용자가 본인이 맞는지를 확인하는 절차
- 인가(Authentication)
    - 인증된 사용자가 요청한 자원에 접근 가능한지를 결정하는 절차
    
    ![image](https://user-images.githubusercontent.com/47655983/189899183-72dc84da-1f8c-4eb5-bbaf-df0ecc6d51ac.png)
    
- Spring Security는 기본적으로 인증 절차를 거친 후에 인가 절차를 진행하게 됨
- 인가 과정에서 해당 리소스에 대한 접근 권한이 있는지 확인함
- 인증과 인가를 위해 Principal을 아이디로, Credential을 비밀번호로 사용하는 Credential 기반의 인증 방식을 사용
- Principal(접근 주체)
    - 보호받는 Resource에 접근하는 대상
- Credential(비밀번호)
    - Resource에 접근하는 대상의 비밀번호
    

## Spring Security 모듈

![image](https://user-images.githubusercontent.com/47655983/189899255-41be0e73-3500-4aa8-8aaf-31c83b2963eb.png)

- SecurityContextHolder
    - 보안 주체의 세부 정보를 포함하여 응용프로그램의 현재 보안 컨텍스트에 대한 세부 정보를 저장
    - 기본적으로 `SecurityContextHolder.MODE_INHERITABLETHREADLOCAL` , `SecurityContextHolder.MODE_THREADLOCAL` 방법을 제공
- SecurityContext
    - Authentication을 보관하는 역할을 하며, SecurityContext를 통해 Authentication 객체를 꺼내올 수 있음
- Authentication
    - 현재 접근하는 주체의 정보와 권한을 담는 인터페이스
    - Authentication 객체는 Security Context에 저장 됨
- UsernamePasswordAuthenticationToken
    - Authentication을 implements한 AbstractAuthenticationToken의 하위 클래스
    - User ID가 Principal 역할을 하고 Password가 Credential 역할을 함
- AuthenticationProvider
    - 실제 인증에 대한 부분을 처리
    - 인증 전의 Authentication 객체를 받아서 인증이 완료된 객체를 반환하는 역할
- Authentication Manager
    - 인증에 대한 부분은 SpringSecurity의 AuthenticationManager를 통해서 처리되는데 실질적으로는 AuthenticationManager에 등록된 AuthenticationProvider에 의해 처리
    - 인증이 성공하면 2번째 생성자를 이용해 인증이 성공한 객체를 생성하여 Security Context에 저장
- UserDetails
    - 인증에 성공하여 생성된 UserDetails 객체는 Authentication 객체를 구현한 UsernamePasswordAuthenticationToken을 생성하기 위해 사용됨
- UserDetailsService
    - UserDetails 객체를 반환하는 메소드 단 한개를 가진 인터페이스
    - 일반적으로 이를 구현한 클래스 내부에 UserRepository를 주입받아 DB와 연결하여 처리함