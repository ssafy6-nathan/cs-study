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
	
#### * 흐름제어(flow Control)
   - 송신측의 속도가 수신 속도보다 빠르면 수신측에 제한된 저장용량이 초과됨
      -> 데이터의 손실 -> 불필요한 응답, data 전송이 빈번하게 발생
      => 이를 위해 흐름제어 필요
   - Flow Control은 receiver가 packet을 지나치게 많이 받지 않도록 조절하는 것
   - 기본 개념은 receiver가 sender에게 현재 자신의 상태를 feedback 한다는 점


  #### * 혼잡제어(Congestion Control)
  - 송신측의 데이터 전달과 네트워크의 데이터 처리 속도 차이를 해결하기 위한 기법
  - 만약 한 라우터에 데이터가 몰리면 자신에게 온 데이터를 모두 처리할 수 없게 됨 -> 호스트들은 재전송 => 오버플로우, 데이터 손실 발생
  - 이러한 네트워크의 혼잡을 피하기 위해 송신측에서 보내는 데이터의 전송속도를 강제로 줄이는 것 = 혼잡제어
  - 흐름제어가 송신측과 수신측 사이의 전송 속도를 다루는데 반해, 혼잡제어는 호스트와 라우터를 포함한 보다 넓은 관점에서 전송 문제를 다루게 됨

#### * 오류 감지(Error detection)
- 통신 도중에 데이터가 유실되거나 잘못된 데이터가 수신되었을 경우 대처하는 방법
    
 ## 2. TCP(Transmission Control Protocol)
 	
    - 신뢰성있는 데이터 통신을 가능하게 해주는 프로토콜
    - Connection 연결(3 way-handshake)- 양방향 통신
    - 데이터의 순차 전송을 보장
    - Flow Control(흐름 제어)
    - Congestion Control(혼잡 제어)
    - Error Detection(오류 감지)
    - 스트림 전송으로 전송 데이터의 크기가 무제한이다
    - 패킷에 대한 응답을 해야하기 때문에 (시간 지연, CPU 소모) 성능이 낮다
    - 서버와 클라이언트는 1:1연결

<img src="https://velog.velcdn.com/images/hanhs4544/post/82fbca3d-d75e-4b2f-aec2-6e5a000a2adf/image.png" width="50%" height="50%"/>
  
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
   - DNS에서 사용

  | 프로토콜 종류  | TCP | UDP |
  | --- | --- | --- |
  | 연결 방식 | 연결형 서비스 | 비연결형 서비스 |
  | 패킷 교환 방식 | 가상 회선 방식 | 데이터그램 방식 | 
  | 전송 순서 | 전송 순서 보장 | 전송 순서 보장 안함 |
  | 수신 여부 확인 | 확인함 | 확인하지 않음 |
  | 통신 방식 | 1:1 | 1:1, 1:N, N:M |
  | 신뢰성 | 높음 | 낮음 |
  | 속도 | 느림 | 빠름 |
    
<img src="https://velog.velcdn.com/images/hanhs4544/post/5669be3c-e537-40c7-8a34-506aae6451bb/image.png" width="50%" height="50%"/>
<img src="https://velog.velcdn.com/images/hanhs4544/post/9d2c9f82-cb1d-46fd-98c1-440a8ffd5e95/image.png" width="50%" height="50%"/>


    
 #### 참고 자료   
    
    https://www.youtube.com/watch?v=ikDVGYp5dhg
    https://www.youtube.com/watch?v=0y0aYIhH2gA
    https://mangkyu.tistory.com/15
