# LDAP

# X.500

- OSI 7계층에서 응용계층에 속하는 프로토콜
- 디렉토리에 저장된 데이터에 접근할 수 있는 프로토콜
- 정보통신 서비스에 필요한 정보를 데이터베이스화해 효율적으로 관리하고 사용자가 편리하게 접근할 수 있는 기능을 제공하는 서비스
- 인터넷 사용자에게 다른 사용자나 서비스에 관련된 정보들을 검색할 수단을 제공하는 것
- X.500이 정의한 프로토콜
    - DAP (Directory Access Protocol)
    - DSP (Directory System Protocol)
    - DISP (Directory Infomation Shadowing Protocol)
    - DOP (Directory Operational Bindings Management Protocol)

# LDAP(Lightweight Directory Access Protocol)이란?

- Lightweight Directory Access Protocol
- 네트워크 상에서 조직이나 개인정보 혹은 파일이나 디바이스 정보 등을 찾아보는 것을 가능하게 만든 소프트웨어 프로토콜이다.
- 네트워크 상의 디렉토리 서비스 표준인 X.500의 DAP(Directory Access Protocol)를 기반으로한 경량화(Lightweight) 된 DAP 버전이다.
    - DAP는 OSI 전체 프로토콜 스택을 지원하며 운영에 매우 많은 컴퓨팅 자원을 필요로하는 아주 무거운 프로토콜
    - LDAP은 DAP의 복잡성을 줄이고 TCP/IP 레이어에서 더 적은 비용으로 DAP의 많은 기능적인 부분을 조작할 수 있도록 설계
    - 
    
    ![](https://velog.velcdn.com/images/hsk2454/post/102125bc-595f-4bdb-84eb-d7a6a5db83c0/image.png)
    

![](https://velog.velcdn.com/images/hsk2454/post/16bd7852-fc53-41ee-920f-fe66cb930b9a/image.png)

- Lightweight
    - 이 의미는 사용하기 간편하다는 의미가 아니라 통신 네트워크 대역폭 상의 가벼움을 의미
    - 인터넷 프로토콜로 데이터를 조금만 주고 받아도 되게끔 설계
    - LDAP의 요청의 99%는 검색에 대한 요청
    - 디렉토리 안에는 연락처, 사용자, 파일, code 등 무엇이든 넣을 수 있고, insert, update 보다는 검색 요청에 특화되어 있다.
    - 검색에 특화되다보니 트랜잭션이나 롤백이 없고 복잡한 관계 등을 설정할 수 없다.
    - 신뢰성이나 가용성을 개선하기 위해 쉽게 복제될 수 있는 아키텍처로 이루어져 있다.
- 기본적으로 바이너리 프로토콜이다.
    - ASN.1이라는 언어로 메시지를 표현
    - 메시지를 BER(Basic Encoding Rules)라는 포맷으로 인코딩하여 주고 받음
        - BER 인코딩이 바이너리라서 내용을 알아볼 순 없음
- 비동기 프로토콜이다.
    - 세션을 하나만 열어서 여러 메시지 요청을 보낼 수 있고, 각각의 요청에 대한 응답이 다른 시점에 올 수도 있음
    - 응답마다 어떤 요청의 응답인지 식별할 수 있는 아이디가 부여됨
- **LDAP Data Interchange Format(LDIF)으로**
    
    ```
    dn: dc=springframework,dc=org
    objectclass: top
    objectclass: domain
    objectclass: extensibleObject
    dc: springframework
     
    dn: ou=groups,dc=springframework,dc=org
    objectclass: top
    objectclass: organizationalUnit
    ou: groups
     
    dn: ou=subgroups,ou=groups,dc=springframework,dc=org
    objectclass: top
    objectclass: organizationalUnit
    ou: subgroups
    ```
    

# 용도

- 사용자, 시스템, 네트워크, 서비스, 애플리케이션 등의 정보를 트리 구조로 저장하여 조회하거나 관리
- 회사에서 구성원의 조직도나 팀별 이메일 주소 등도 LDAP 서비스로 관리
    - 특정 영역에서 이용자명과 패스워드를 확인하여 인증하는 용도로 쓰임
- 인증이든 무엇이든 트리 구조로 검색하고 편집하기 좋은 데이터들은 LDAP을 많이 사용
- LDAP은 서버에만 적용되는 프로토콜이 아니라 주소록 관리에 사용되거나 스마트폰 내에서도 LDAP 클라이언트가 포함되어 있음
- 특정 데이터를 중앙에서 일괄 관리하는 일반적인 경우에 사용
    - 유저 권한 관리, 주소록, 조직도, 사용자 정보 관리, 어플리케이션/시스템 설정 정보, 공개 키 인프라스트럭쳐, DHCP나 DNS등의 저장소, 문서 관리, 이미지 저장소, Code 등
        
        
        ![](https://velog.velcdn.com/images/hsk2454/post/1299b0e0-002d-469e-85cd-50cedee2f389/image.png)
        

# 주요 용어

- DN : Distinguish Name
- RDN : Relative Distinguished Name
- DIT : Directory Information Tree
- LDIF : LDAP Data Interchange Format
- UID : User ID
- DC : Domain Component
- OU : Organizational Unit