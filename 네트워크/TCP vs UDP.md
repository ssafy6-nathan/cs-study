## 1. Transport Layer  
	
- Application에서 내려온 data에 TCP/UDP 헤더를 추가하여 Segment화 만드는 것
- End Point간 신뢰성있는 데이터 전송을 담당하는 계층
	- 신뢰성 : 데이터를 순차적, 안정적으로 전달
    - 전송 : 포트 번호에 해당하는 프로세스에 데이터를 전달
  

- 전송 계층이 없다면?(전송 계층의 중요성)
	
    - 데이터의 순차 전송 원활하지 X
    - Flow(흐름 문제) : 원인 - 송수신자 간의 데이터 처리 속도 차이
    - congestion(혼잡 문제) : 원인 - 네트워크의 데이터 처리 속도(ex.라우터)
    
    	결과 -> 데이터의 손실 발생
    
 ## 2. TCP(Transmission Control Protocol)
 	
    - 신뢰성있는 데이터 통신을 가능하게 해주는 프로토콜
    - Connection 연결(3 way-handshake)- 양방향 통신
    - 데이터의 순차 전송을 보장
    - Flow Control(흐름 제어)
    - Congestion Control(혼잡 제어)
    - Error Detection(오류 감지)
    - 전이중(Full-Duplex), 점대점(Point to Point)방식
    - 서버와 클라이언트는 1대1로 연결된다
    - 스트림 전송으로 전송 데이터의 크기가 무제한이다
    - 패킷에 대한 응답을 해야하기 때문에 (시간 지연, CPU 소모) 성능이 낮다
    - 서버 소켓은 연결만을 담당한다
    - 연결 과정에서 반환된 클라이언트 소켓은 데이터의 송수신에 사용된 다형 서비스로 가상 회선 방식을 제공한다.
    
    ![](https://velog.velcdn.com/images/hanhs4544/post/82fbca3d-d75e-4b2f-aec2-6e5a000a2adf/image.png)

  
  - 문제점 
  	전송의 신뢰성은 보장하나 매번 connection을 연결해서 시간 손실 발생,
    패킷을 조금만 손실해도 재전송
    
  - 사용 예시 - 은행업무와 같이 데이터의 정확성이 필요한 곳에서 사용됨, 파일 전송
    
 ## 3. UDP(User Datagram Protocol) 
   - TCP보다 신뢰성이 떨어지지만 전송 속도가 일반적으로 빠른 프로토콜(순차 전송X, 흐름제어 X, 혼잡 제어 X)
   - Connectionless(3 way-handshake X) - 연결 유무 확인 안함
   - 한번에 전송되는 데이터의 크기가 정해져있어서 크기가 크다면 나눠서 전송해야 함
   - Error Detection - UDP 헤더의 체크섬 필드를 통해 최소한의 오류만 검출
   - 비교적 데이터의 신뢰성이 중요하지 않을 때 사용(ex. 영상 스트리밍, 게임 음성 프로그램 등에서 사용)
   - 소켓 대신 IP를 기반으로 데이터를 전송한다
   - 서버와 클라이언트는 1대1, 1대N, M대N 등으로 연결될 수 있다.
   - 데이터그램(메시지)단위로 전송, 크기 : 65535바이트 -> 초과시 잘라서 보냄

  | 프로토콜 종류  | TCP | UDP |
  | --- | --- | --- |
  | 연결 방식 | 연결형 서비스 | 비연결형 서비스 |
  | 패킷 교환 방식 | 가상 회선 방식 | 데이터그램 방식 | 
  | 전송 순서 | 전송 순서 보장 | 전송 순서 보장 안함 |
  | 수신 여부 확인 | 확인함 | 확인하지 않음 |
  | 통신 방식 | 1:1 | 1:1, 1:N, N:M |
  | 신뢰성 | 높음 | 낮음 |
  | 속도 | 느림 | 빠름 |
    

 ![](https://velog.velcdn.com/images/hanhs4544/post/5669be3c-e537-40c7-8a34-506aae6451bb/image.png)
    
 ![](https://velog.velcdn.com/images/hanhs4544/post/9d2c9f82-cb1d-46fd-98c1-440a8ffd5e95/image.png)

    
    
    
    https://www.youtube.com/watch?v=ikDVGYp5dhg
    https://www.youtube.com/watch?v=0y0aYIhH2gA
    https://mangkyu.tistory.com/15