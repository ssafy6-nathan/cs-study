# 1. 원시타입(Primitive Type)
### 정수, 실수, 문자, 논리 리터럴 등의 실제 데이터 값을 저장하는 타입
|종류|데이터형|크기(byte / bit)|표현범위|
|---|---|---|---|
|논리형|boolean|1 / 8|true 또는 false|
|문자형|char|2 / 16|'\u0000' ~ 'uFFFF' (16비트 유니코드 문자 데이터)|
|정수형|byte|1 / 8|-128 ~ 127|
|정수형|short|2 / 16|-32768 ~ 32767|
|정수형|int|4 / 32|-2147483648 ~ 2147483647( -21억 ~ + 21억)|
|정수형|long|8 / 64|-9223372036854775808 ~ 9223372036854775807(-100경 ~ + 100경)|
|실수형|float|4 / 32|1.4E-45 ~ 3.4028235E38|
|실수형|double|8 / 64|4.9E-324 ~ 1.7976931348623157E308|


- char
	- Java에서 유일하게 제공되는 unsigned 형태(음수 X, 0부터 시작하는 데이터 형태)
    - char형이 1111 1111 1111 1111의 bit를 가지고 있을 때 10진수 값 
    => 65535 (맨 앞 bit를 가지고 음수 양수 나타내지 않음)
    - char c1 = 'A'; c2 = 'B'; 일 때 두 수의 대소비교가 가능한 이유 
    => char형은 유니코드 정수형태로 저장하기 때문에 c1에는 'A'의 정수 값인 65가 들어간다.

- 정수형
	- 정수형 데이터를 사용하게 되면 JVM에서 기본적으로 int형 데이터타입의 데이터로 인식함
    => int보다 작은 자료형의 값을 계산시 int형으로 형변환되어 연산이 수행된다.
 	=> int형 데이터 타입의 범위를 넘어서는 long 데이터 타입을 사용할 때는 정수 데이터 맨 뒤 쪽에 'L'을 붙여줘야한다.
    
- 실수형
	- 실수형 데이터를 사용하게 되면 double형 데이터타입이 기본 데이터타입이다.
    => float형 데이터 타입을 사용하기 위해서는 f를 붙여줘야 한다.
    
----

# 2. 참조타입(Reference Type)
### 원시타입을 제외한 타입들(열거, 배열, 클래스, 인터페이스)을 말한다.
#### java에서 **실제 객체는 힙영역**에 저장되며, **참조 타입 변수는 스택영역**에 실제 객체들의 주소를 저장하여, 객체를 사용할 때마다 참조 변수에 저장된 객체의 주소를 불러와 사용하는 방식이다.

![pointer](https://velog.velcdn.com/images/hanhs4544/post/e50ac8be-ee86-4956-8704-1bb276f63c85/image.png)

### Boxing / Unboxing
- Boxing : 원시타입 -> 참조타입
- Unboxing : 참조타입 -> 원시타입
자바 1.5 이전에는 일일히 변환 과정을 거쳐주어야 했으나, 1.5부터 추가된 Auto Boxing/Unboxing 기능으로 인해 자동으로 Boxing/Unboxing 된다.

``` java
Integer iA = new Integer(123);
Integer iB = new Integer(123);

int ia = (int)iA;   // 언박싱
int ib = iB; // 오토 언박싱
Integer iC = (Integer)456; // 박싱
Integer iD = ia; // 오토 박싱
```
---

# 3. 원시타입(Primitive Type) VS 참조타입(Reference Type)
### (1) Null 포함 가능 여부
- 원시타입은 null이 불가하지만, 참조타입은 가능하다.
```java
int a = null; // 불가능
Integer A = null; //가능
```

### (2) 제너릭 타입에서의 사용 가능 여부
- 원시타입은 제너릭 타입에서 사용할 수 없으나, 참조타입은 가능하다.
``` java
List<int> list; //불가능
List<Integer> list;//가능
```

### (3) 접근 속도 비교
![speed](https://velog.velcdn.com/images/hanhs4544/post/08cf6e61-d254-4c34-a0a7-07f68a7d1c9b/image.png)
- 원시타입의 값은 스택메모리에 존재한다
반면 참조타입은 스택메모리에 참조값만 있고, 실제 값은 힙메모리에 존재하며, 값을 필요로 할 때마다 언박싱 과정을 거쳐야하기 때문에 원시타입과 비교하여 접근 속도가 느려지게 된다.

### (4) 메모리양 비교
	
|원시타입이 사용하는 메모리|참조타입이 사용하는 메모리|
|---|---|
|boolean - 1bit | Boolean – 128 bits|
|byte - 8bits | Byte - 128bits|
|short, char - 16bits | Short, Charater - 128bits|
|int, float - 32bits | Integer, Float - 128bits|
|long, double - 64bits | Long, Double - 196bits|

---
### ∴ Null을 다뤄야하거나 제네릭 타입에서 사용되어야 한다면 참조타입 사용, 그렇지 않다면 성능과 메모리에 장점이 있는 원시타입 사용

``` text
[참고자료]
https://siyoon210.tistory.com/139
https://studymake.tistory.com/420
```
