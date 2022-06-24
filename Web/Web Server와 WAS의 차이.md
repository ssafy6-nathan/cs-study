# **Web Server와 WAS의 차이**

<br>

## **웹 서버(Web Server)**

<br>

#### 소프트웨어와 하드웨어로 구분된다.

-   1) 하드웨어 : Web 서버가 설치되어 있는 컴퓨터
-   2) 소프트웨어 : 웹 브라우저 클라이언트로부터 HTTP 요청을 받아 정적인 컨텐츠(.html .jpeg .css 등)를 제공하는 컴퓨터 프로그램

<br>
<br>


## **WAS(Web Application Serve)**

-   WAS는 웹 애플리케이션을 실행시켜 필요한 기능을 수행하고 그 결과를 웹 서버에게 전달하는 일종의 미들웨어를 말한다.

<br>

#### 웹 서버 + 웹 컨테이너

-   php, jsp, asp와 같은 언어들을 사용해 동적인 페이지를 만들어낼 수 있는 서버이다.
-   프로그램 실행 환경과 데이터베이스 접속 기능을 제공한다.
-   비즈니스 로직 수행이 가능하다.

![image](https://user-images.githubusercontent.com/67899393/175442548-da286f31-c03d-44d7-bd41-ae7199123cea.png)


<br>
<br>

# **Web Server와 WAS의 차이** 

<br>

## **Static Pages와 Dynamic Pages**

![image](https://user-images.githubusercontent.com/67899393/175442648-79bef683-b93c-4a12-b803-135ea74f56f2.png)


<br>

#### Static Pages

-   Web Server는 파일 경로 이름을 받아 경로와 일치하는 file contents를 반환한다.
-   항상 동일한 페이지를 반환한다.
-   Ex) image, html, css, javascript 파일과 같이 컴퓨터에 저장되어 있는 파일들

<br>

#### Dynamic Pages

-   인자의 내용에 맞게 동적인 contents를 반환한다.
-   즉, 웹 서버에 의해서 실행되는 프로그램을 통해서 만들어진 결과물 \* Servlet: WAS 위에서 돌아가는 Java Program
-   개발자는 Servlet에 doGet()을 구현한다.

<br>
<br>

## **웹 서버와 WAS 종류**

<br>

#### 웹 서버 종류

-   아파치
-   Microsoft IIS
-   Ngnix

<br>

#### WAS 종류

-   톰캣
-   JEUS
-   IBM WebSphere

<br>
<br>

## **웹 서버와 WAS 차이**

-   웹 서버는 정적인 컨텐츠만 줄 수 있다.
-   WAS는 어떤 애플리케이션을 돌리고, DB를 연결하고, 어떤 로직을 수행해서, 만든 데이터를 전달할 수 있다.
-   요약: "요청에 따라 변하는 정보를 제공할 수 있는가?"
