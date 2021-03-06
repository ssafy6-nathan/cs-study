# 동기와 비동기, blocking&nonblocking

<br>

## 동기와 비동기의 차이

동기/비동기는 작업들이 시간을 맞춰서 실행되는지 아닌지에 대한 이야기이다

<br>

### 비유를 통한 쉬운 설명

해야할 일(task)가 빨래, 설거지, 청소 세 가지가 있다고 가정한다. 이 일들을 동기적으로 처리한다면 빨래를 하고 설거지를 하고 청소를 한다. 비동기적으로 일을 처리한다면 빨래하는 업체에게 빨래를 시킨다. 설거지 대행 업체에 설거지를 시킨다. 청소 대행 업체에 청소를 시킨다. 셋 중 어떤 것이 먼저 완료될지는 알 수 없다. 일을 모두 마친 업체는 나에게 알려주기로 했다.

<br>

#### 동기(synchronous : 동시에 일어나는)

 - 동기는 말 그대로 동시에 일어난다는 뜻이다. 요청과 그 결과가 동시에 일어난다. 바로 요청을 하면 시간이 얼마가 걸리던지 요청한 자리에서 결과가 주어진다.

-   요청과 결과가 한 자리에서 동시에 일어남
-   A노드와 B노드 사이의 작업 처리 단위(transaction)를 동시에 맞추겠다.

![image](https://user-images.githubusercontent.com/67899393/173006785-f9acf5e1-2c4e-46de-805c-89d6be1f2a33.png)

<br>

#### 비동기(Asynchronous : 동시에 일어나지 않는)

 - 비동기는 동시에 일어나지 않는다를 의미한다. 요청과 결과가 동시에 일어나지 않는다. 

-   요청한 그 자리에서 결과가 주어지지 않음
-   노드 사이의 작업 처리 단위를 동시에 맞추지 않아도 된다.

![image](https://user-images.githubusercontent.com/67899393/173006813-01b03e3a-03dd-4cce-9349-4e6688542697.png)

<br>

#### 동기와 비동기는 상황에 따라서 각각의 장단점이 있습니다. 

**동기방식**은 설계가 매우 간단하고 직관적이지만 결과가 주어질 때까지 아무것도 못하고 대기해야 하는 단점이 있고, 

**비동기방식**은 동기보다 복잡하지만 결과가 주어지는데 시간이 걸리더라도 그 시간 동안 다른 작업을 할 수 있으므로 자원을 효율적으로 사용할 수 있다는 장점이 있다.

<br>

## 블로킹(blocking) vs 논블로킹(non-blocking)

블로킹/논블로킹은 한 작업이 처리되는 동안 다른 작업도 처리될 수 있는지에 대한 이야기이다.

블로킹과 논블로키을 설명하기 위해서는 **제어권**, **결과값** 이라는 개념을 잘 알아야 한다.

```
제어권 = 함수 내용을 실행시킬 수 있는 권리 (행동의 자유)
결과값 = 함수의 리턴값
```

<br>

### 블로킹(blocking)

A 함수가 B 함수를 호출하면, 제어권을 A가 호출한 B 함수에 넘겨준다.

![image](https://user-images.githubusercontent.com/67899393/173006850-f27185c2-6a88-4a14-a23b-01a06dd51d85.png)

-   A함수가 B함수를 호출하면 B에게 제어권을 넘긴다.
-   제어권을 넘겨받은 B는 열심히 함수를 실행한다. A는 B에게 제어권을 넘겨주었기 때문에 함수 실행을 잠시 멈춘다.
-   B함수는 실행이 끝나면 자신을 호출한 A에게 제어권을 돌려준다.

<br>

### 논블로킹(non-blocking)

A 함수가 B 함수를 호출해도 제어권은 그대로 자신이 가지고 있는다.

![image](https://user-images.githubusercontent.com/67899393/173006885-df443d55-5de0-4c8c-924f-2fdec7c9ecf3.png)

-   A함수가 B함수를 호출하면, B 함수는 실행되지만, 제어권은 A 함수가 그대로 가지고 있는다.
-   A함수는 계속 제어권을 가지고 있기 때문에 B함수를 호출한 이후에도 자신의 코드를 계속 실행한다.

<br>

## 동기&비동기 + 블로킹&논블록킹 조합

블로킹/논블로킹은 한 작업이 처리되는 동안 다른 작업도 처리될 수 있는지에 대한 이야기이고, 동기/비동기는 작업들이 시간을 맞춰서 실행되는지 아닌지에 대한 이야기이다.

![image](https://user-images.githubusercontent.com/67899393/173006927-79808d49-e973-4693-9a6a-afe4ac225d15.png)

<br>

위 그림처럼 총 4가지의 경우가 나올 수 있다. 이걸 좀 더 이해하기 쉽게 Case 별로 예시를 통해 보면서 이해하고 넘어가보자

<br>

```
상황 : 치킨집에 직접 치킨을 사러감
```

<br>

### 1) Blocking & Synchronous

```
나 : 사장님 치킨 한마리만 포장해주세요
사장님 : 네 금방되니까 잠시만요!
나 : 넹
-- 사장님 치킨 튀기는 중--
나 : (아 언제 되지?..궁금한데 그냥 멀뚱히 서서 치킨 튀기는거 보면서 기다림)
```

<br>

### 2) Blocking & Asynchronous

```
나 : 사장님 치킨 한마리만 포장해주세요
사장님 : 네 금방되니까 잠시만요!
나 : 앗 넹
-- 사장님 치킨 튀기는 중--
나 : (언제 되는지 안 궁금함, 잠시만이래서 다 될때까지 서서 붙잡힌 상황)
```

<br>

### 3) Non-blocking & Synchronous

```
나 : 사장님 치킨 한마리만 포장해주세요
사장님 : 네~ 주문 밀려서 시간 좀 걸리니까 볼일 보시다 오세요
나 : 넹
-- 사장님 치킨 튀기는 중--
(5분뒤) 나 : 제꺼 나왔나요?
사장님 : 아직이요
(10분뒤) 나 : 제꺼 나왔나요?
사장님 : 아직이요ㅠ
(15분뒤) 나 : 제꺼 나왔나요?
사장님 : 아직이요ㅠㅠ
```

<br>

### 4) Non-blocking & Asynchronous

```
나 : 사장님 치킨 한마리만 포장해주세요
사장님 : 네~ 주문 밀려서 시간 좀 걸리니까 볼일 보시다 오세요
나 : 넹
-- 사장님 치킨 튀기는 중--
나 : (앉아서 다른 일 하는 중)
...
사장님 : 치킨 나왔습니다
나 : 잘먹겠습니다~
```

<br>
