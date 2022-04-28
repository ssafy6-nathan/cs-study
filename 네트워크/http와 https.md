### ********\[ HTTP(Hyper Text Transfer Protocol)란? \]********
<br>
HTTP(Hyper Text Transfer Protocol)란 **서버/클라이언트 모델을 따라 데이터를 주고 받기 위한 프로토콜**로 웹사이트에서 기본적으로 사용되었던 프로토콜이다.

HTTP는 텍스트, 이미지,영상, JSON 등등 거의 **모든 형태의 데이터**를 전송할수 있다.

![image](https://user-images.githubusercontent.com/67899393/165755262-d29a6f5f-a0bd-45a8-833f-ce319224dab5.png)

<br>
<br>

### ********\[ HTTP의 구조 \]********
<br>
HTTP는 애플리케이션 레벨의 프로토콜로 TCP/IP 위에서 작동한다. HTTP는 상태를 가지고 있지 않는 Stateless 프로토콜이며 Method, Path, Version, Headers, Body 등으로 구성된다.

![image](https://user-images.githubusercontent.com/67899393/165755309-7dc3d980-c7d0-4318-ae6e-d556cc11351a.png)

하지만 HTTP는 암호화가 되지 않은 평문 데이터를 전송하는 프로토콜이기 때문에, 네트워크 신호를 가로챈다면 HTTP 내용이 그대로 노출된다. 그리고 이러한 문제를 해결하기 위해 HTTPS가 등장하게 되었다.

<br>
<br>

### ********\[ HTTPS(Hyper Text Transfer Protocol Secure)란? \]********
<br>
 HTTPS는 **SSL(TLS) 인증서를 사용하여 HTTP에 데이터 암호화가 추가된 프로토콜**로 네트워크 상에서 중간에 제3자가 정보를 볼 수 없도록 암호화를 지원하고 있다.

SSL은 서버와 브라우저 사이에서 안전하게 암호화된 연결을 만들 수 있게 도와주고, 서버와 브라우저가 민감한 정보를 주고받을 때, 해당 정보가 도난당하는 것을 막아준다.

HTTPS는 HTTP 자체를 암호화하는 것은 아니다.

HTTP를 사용해서 운반하는 내용, 즉 HTTP Message body를 암호화 한다.

이때, HTTP header는 암호화되지 않는다.

<br>
<br>

### **SSL 인증서 발급 과정**
<br>
1.  서비스 기업은 자신의 공개 키를 CA 기업에 전달하여 SSL 인증서 생성을 요청한다.
2.  CA 기업은 자신의 비공개 키로 서비스 기업의 공개 키와 함께 몇 가지 정보를 함께 암호화한다.
3.  생성한 SSL 인증서를 서비스 기업에게 전달한다.

![image](https://user-images.githubusercontent.com/67899393/165755447-7a52c377-3248-4fee-8e3d-f22142666d51.png)


<br>
<br>

### **서버-클라이언트 SSL 인증서 전달 과정**
<br>
1.  클라이언트(브라우저)가 서버에게 데이터 요청한다.
2.  서버는 클라이언트에게 SSL 인증서를 전달한다.
3.  클라이언트는 자신이 관리하는 공인된 CA 목록 중에 해당 SSL 인증서를 암호화한 CA가 있는지 확인한다.
4.  목록에 존재하는 CA라면 클라이언트는 **이미 알고 있는 해당 CA의 공개 키**를 이용해 해당 인증서를 복호화한다.

![image](https://user-images.githubusercontent.com/67899393/165755503-c309efa8-bc22-4375-a8c5-310e46a9d314.png)

<br>
<br>

### **HTTPS 통신과정 및 원리**
<br>
대칭키와 공개키(비대칭키) 방식을 전부 사용하는 하이브리드 방식

<br>
<br>

**데이터 전송을 위해 대칭키 방식을 사용하며 대칭키를 안전하게 전달하기 위해 공개키 방식을 사용한다.**
<br>
![image](https://user-images.githubusercontent.com/67899393/165755552-072ff65c-dc54-4661-bc4f-7300f43ae87b.png)


1.  Client Hello : 말 그대로, 클라이언트가 인사를 건넨다는 의미이다. 즉, 클라이언트에서 통신하고 싶은 서버로 연결을 시도하는 패킷을 전송한다. 패킷에는 클라이언트에서 사용 가능한 cipher suite 목록과 이 cipher suite에 어떤 프로토콜(TLS/SSL)을 사용할지, 인증서 검증 또는 데이터를 어떤 방식으로 암호화할지 등이 표시된다.
2.  Server Hello : 1에서 받은 인사에 대해 서버가 응답을 한다. cipher suite 목록 중 1개의 cipher suite, 사용할 프로토콜의 버전 정보 등을 응답으로 보낸다.
3.  Certificate : CA로부터 발급받은 인증서를 전송한다.
4.  Server Key Exchange : 3번의 Certificate 패킷을 전달할 때 SSL 인증서 내부에 서버의 public key가 존재한다면 해당 과정은 생략된다. 하지만 SSL 인증서 내부에 public key가 존재하지 않는다면 서버에서 직접 public key를 전달하는 과정이 이에 해당한다.
5.  Certificate Request : client에서 server의 SSL 인증서 검증이 이루어진다. 클라이언트는 CA의 공개키를 통해 암호화된 인증서를 복호화한다. 이때, CA의 공개키를 이용해 인증서를 복호화했을 때, 복호화가 가능했다는 것은 인증서가 해당 CA에서 발급되었다는 것을 검증하는 의미를 갖는다.
6.  Server Hello Done : Server Hello 절차가 완료되었음을 client에 알리는 Server Hello Done 메시지를 client에 전송한다.
7.  Client Key Exchange, Change Cipher Spec : client에서 pre-master-key라는 것을 전송한다. 이 key는 1, 2 단계에서의 난수를 조합하여 생성하며 대칭키로 사용하게 된다. 이 대칭키를 서버에 안전하게 전달하기 위해 비대칭키 암호화 방식을 사용한다. 즉, 서버의 public key로 암호화 하여 서버에 전달한다. 그러면 서버의 private key로만 복호화가 가능하여 서버 쪽에서만 대칭키를 전달받을 수 있다.  
    서버의 public key는 4번 과정에서 SSL 인증서를 복호화해 받는다. SSL 인증서 내부에 없을 때에는 서버로부터 직접 전달받는다.  
    이제 서버와 client는 대칭키를 나누어 갖게 된다.
8.  Change Cipher Spec : client로부터 전송받은 pre-master-key를 정상적으로 복호화 한 후 master-key로 승격한 뒤 통신할 준비가 완료되었다는 Change Cipher Spec을 보낸다.

<br>
<br>

### HTTP 발전 과정
<br>

|   | HTTP/1 | HTTP/2 | HTTP/3 |
| --- | --- | --- | --- |
| 적용시기 | 1999 | 2015 | 2021 |
| 통신기반 | TCP | TCP | QUIC (4 layer) base on Reliable UDP |
| 보안 | N/A | TLS 요구 | TLS 필수   Chrome, Firefox, Safari (Bigsur) |
| File Transfer | 1 of 1 | 1 of N | 1 of N |
| Header Compress | N/A | Compress | Compress |
| Prioritization (우선순위) | N/A | Y | Y |
| 비고 | Keep-Alive 기능필요   PipeLining | HTTP/1 대비 속도향상 큼   \- async | 외부 모니터링 불가. |
| 상태 |   |   | Draft |
| 추세 | 대다수 | 증가중 | 극소수 Google, Youtube, Facebook 등… |
