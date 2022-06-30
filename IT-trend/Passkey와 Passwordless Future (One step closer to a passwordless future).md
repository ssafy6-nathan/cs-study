# Passkey와 Passwordless Future (One step closer to a passwordless future)

# 암호 없는 미래는 어떻게 작동할까?

휴대전화에서 웹사이트나 앱에 로그인하면 휴대전화의 잠금이 해제되어 더 이상 계정에 비밀번호가 필요하지 않다.

대신 휴대전화는 온라인 계정 잠금을 해제하는 데 사용되는 passkey라고 하는 FIDO 자격 증명을 저장한다. 암호 키는 공개 키 암호화를 기반으로 하고 휴대전화의 잠금을 해제할 때만 온라인 계정에 표시되기 때문에 로그인을 훨씬 더 안전하게 만든다.

컴퓨터에서 웹사이트에 로그인하려면 휴대전화가 근처에 있어야 하며 액세스를 위해 잠금을 해제하라는 메시지가 표시된다. 이 작업을 완료하면 휴대전화가 더 이상 필요하지 않으며 컴퓨터의 잠금을 해제하기만 하면 로그인할 수 있다. 휴대전화를 분실하더라도 클라우드 백업에서 비밀번호가 새 휴대전화로 안전하게 동기화되므로 이전 기기에서 중단된 부분부터 다시 찾을 수 있다.

![https://storage.googleapis.com/gweb-uniblog-publish-prod/images/Frame_2610059.max-1000x1000.png](https://storage.googleapis.com/gweb-uniblog-publish-prod/images/Frame_2610059.max-1000x1000.png)

# Passkey와 암호 없는 미래

오늘날 암호는 온라인 암호에 필수적이지만 피싱, 사기, 잘못된 암호 위생과 같은 위협은 계속해서 사용자에게 위험을 초래한다.(Google은 이러한 문제를 오랫동안 인식해 2단계 인증 및 Google 비밀번호 관리자와 같은 방어책을 만들었다.)

### **Password is the problem**

- 평균 60% 온라인 사용자가 공통된 비밀번호를 다양한 계정에서 사용
- 45% 가 평균적으로 쉬운 비밀번호 사용
- 56% 가 비밀번호를 종이나 파일에 기록해 놓고 사용
- 온라인 해킹 81% 가 비밀번호 탈취, 유추를 통해서 발생
- 온라인 사용자 5명 중 1명은 계정이 탈취된 경험을 했음

그러나 암호 문제를 실제로 해결하려면 암호를 완전히 넘어서야 한다. 

### **Focused scope to address the problem**

- 온라인 인증에만 집중한 것이 성공의 핵심
- 우리의 비밀이 서버로 공유되지 않아야 하고(Not Shared), 인증장치에서(Authenticators) 다양한 인증 요소로 확인해야함(Multi-Factors)
    - Not Shared : 패스워드 기반 온라인 로그인은 서버 내에 비밀이 공우되지만 FIDO는 내 인증장치에만 안전하게 보관
    - Multi-Factors : 나 자신, 내가 가진 것, 내가 아는 것으로 인증
    - Authenticators : Platform authenticators, Roaming authenticators

Google은 피싱이나 스캠, 간단한 비밀번호 등으로 비밀번호가 취약한 문제를 해결하기 위해 password가 필요 없도록 하기 위한 계획을 발표했다. Android와 Chrome에서 FIDO 로그인 표준을 패스워드 없이 지원하도록 구현하는 계획을 발표했고 Apple과 Microsoft는 또한 플랫폼에 대한 지원을 제공할 것이라고 발표했다. 

⇒ 단일 암호 없이 플랫폼에 관계없이 장치, 웹 사이트 및 응용 프로그램에서 로그인을 간소화할 수 있다.

## 왜 FIDO인가?

사용성도 높이고 보안성도 높이는 최적의 방법을 찾기 위함이다. 

### **방향**

> 다만 Sigle Gesture, Public key cryptography 을 중심으로 함
> 
- Sigle Gesture : POLICY 설정을 통해 두 개 이상의 FACTOR를 조합하여 확인하지만 사용자 입장에서는 편의성 향상
- Power of Passwordless : 패스워드를 사용하지 않는 다중요소인증은 편리성과 보안성이라는 두 마리 토끼를 잡을 수 있음
- **사례와 이익**
    - 구글 직원은 패스워드 없이 로그인 -> 패스워드와 아이디를 리셋해야하는 **리소스 비용을 줄임**
    - sk텔레콤 t아이디, 비밀번호 없는 인증 지원 -> 크리덴셜 스터핑 원천 차단
    *크리덴셜 스터핑(Credential Stuffing) - 계정 정보(ID, password)를 확보한 후 임의의 다른 장비(네트워크, 서버 등)에 대입하는 공격
    - Secure QR Code -> UAF 적용, 택배 또는 문서에서 개인정보가 노출되는데 이를 QR코드로 만들어 주고 받는 사람들끼리만 보게된다면 안전

## Passwordless?

`passwordless`는 핸드폰에서 FIDO 인증을 하면 passkey를 저장하고 이 passkey를 이용해서 온라인 계정을 로그인할 수 있게 된다. 컴퓨터에서는 근처에 있는 폰을 이용해서 로그인할 수 있고 한번 로그인하고 나면 이후부터는 컴퓨터의 잠금만 풀면 핸드폰이 없어도 로그인할 수 있고 폰에 저장된 passkey는 클라우드 백업으로 새 폰에서도 이용할 수 있다.

즉, **단일 암호 없이 플랫폼에 관계없이 장치, 웹 사이트 및 응용 프로그램에서 로그인을 간소화할 수 있다.**

- Passkey로 어떻게 근처에 있는 폰을 인식하고 로그인하는가.
    
    passkey는 **블루투스**를 사용해 로그인을 요구하는 장치와 인증을 하는 장치 2개가 물리적으로 가까운 거리에 존재한다는 걸 확인한다. 통신 자체도 **공개키 암호 방식**에 의한 암호화가 실시되고 있기 때문에 비밀번호 재사용이나 피싱 등 사기에 의해 도난당하기 쉽다는 문제를 안고 있는 기존 방식보다 보안상 견고해진다고 한다.
    
- 디바이스 분실 시의 대책
    
    passkey를 이용하는 인증 정보는 애플이나 구글 등 플랫폼 측이 *클라우드 백업*을 실시해 기기간 동기화나 디바이스를 분실하면 계정 회복에 대응할 예정이다.
    
    # 암호 없는 길을 열다

> passkey는 우리가 10년 넘게 그려온 암호 없는 미래에 훨씬 더 가까이 다가갈 수 있도록 해준다.
>

![https://storage.googleapis.com/gweb-uniblog-publish-prod/images/17420___Blog_graphic_timeline_V3-01_1_Nhcv.max-1000x1000.jpg](https://storage.googleapis.com/gweb-uniblog-publish-prod/images/17420___Blog_graphic_timeline_V3-01_1_Nhcv.max-1000x1000.jpg)

### 한계

하지만 이 기술을 모든 사람의 기기에서 사용할 수 있고 웹사이트 및 앱 개발자가 이를 활용하는 데는 여전히 시간이 걸릴 것이다. Google은 이 전환 과정에서 비밀번호는 계속 우리 삶의 일부가 될 것이므로 [Google 비밀번호 관리자](https://passwords.google.com/) 와 같은 기존 제품을 통해 기존 로그인을 더 안전하고 쉽게 만드는 데 계속 전념할 것이다.




참고
[One step closer to a passwordless future](https://blog.google/technology/safety-security/one-step-closer-to-a-passwordless-future/)
