# Java Throwable
![java](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FAzN0V%2Fbtq85l2AIqV%2FrPed1M3iDiaaRukEzEBkfk%2Fimg.png)

## Throwable이란
- Java의 오류 및 예외 계층에 대한 루트 역할

## Error 와 Exception
- error는 프로그램 코드로 처리할 수 없는 심각한 상황(시스템 자원 부족, 스택 오버플로우, 시스템 충돌 등)
- exception은 프로그램의 코드로 처리 할 수 있는 예외적인 상황


<br>

# Checked Exception vs UnChecked Exception

## Checked Exception
- 예외처리가 되어있지 않으면 컴파일이 되지 않는 exception, 즉 무조건 체크해야 되는 exception
- `RuntimeException`와 그 자식 클래스를 제외한 나머지 클래스들 (`IOException`, `FileNotFoundException` 등등)

## UnChecked Exception
- 예외처리가 되어있지 않아도 컴파일은 되는 exception, 즉 예외처리가 개발자의 재량인 exception
- `RuntimeException`을 포함한 그 자식 클래스들

<br>

# 예외 처리

## try-catch
- Checked Exception을 처리해주기 위해 사용하는 문법
```java
try {
    예외처리가 필요한 코드
} catch(예외클래스 e) {
    예외가 발생했을 때 실행할 코드
} finally {
    예외가 발생했든 안했든 무조건 실행할 코드
}
```

## try-with-resource
- 리소스 작업을 하는 코드의 예외처리를 도와주는 문법 자동 `close` 작업을 해준다.
```java
try (FileWriter f = new FileWriter("data.text")) {
    f.write("Hello");
} catch(IOException e) {
    e.printStackTrace();
}

```
- 파일 입출력해주는 클래스는 무조건 `close()`를 실행시켜주어야 한다. 하지만 클래스에 `AutoCloseable` 인터페이스가 구현되어 있으면 try-with-resource 문법을 통해 위 코드처럼 닫는 것을 명시해주지 않아도 된다. (`AutoCloseable`이 알아서 close해준다.)
- 사용하기 위해선 try문에 매개변수를 넣어주면 된다.

## throws
- 메소드에서 잠재적으로 어떤 예외가 발생할 수 있는지 명시해주는 키워드
- checked exception을 try-catch로 처리해주지 않으려면 사용해주면 된다.

## throw
- 예외를 명시적으로 발생시키는 키워드
- 발생시킨 예외를 적절히 처리하지 못하면 프로그램이 죽거나 오동작하게 됨


# 예외 처리 가이드

## 예외는 진짜 예외 상황에만 사용
예외 사용의 나쁜 예시
```java
try {
    int i = 0;
    while(true)
        range[i++].climb();
} catch (ArrayIndexOfBoundsException e) {
    ~~~
}
```


## 검사(Checked) 예외를 비검사(UnChecked) 예외로 전환

```java
try {
    String str = null;
    System.out.println(str.toString());
} catch (NullPointerException e) { 
    System.out.println("--NullPointerException 발생--"); 
} finally {
    System.out.println("예외처리 코드가 오류없이 진행되었습니다.");
}
```


```java
String str = null;
if(str != null) {
    System.out.println(str.toString());
} else {
    //예외처리
}
```

## 표준 예외를 사용
- 재사용하기 좋은 예외들
    - `IllegalStateException` : 대상 객체의 상태가 호출된 메서드를 수행하기 적합하지 않을 경우
    - `IllegalArgumentException` : 호출자가 인수로 부적절한 값을 넘기는 경우
    - `ConcurrentModificationException` : 단일스레드에서 사용하려고 설계한 객체를 여러 스레드에서 동시 수정하려 할 경우
    - `IndexOutOfBoundsException` : 인덱스가 범위를 넘어섰을 떄
- 표준 예외를 오버라이딩해서 사용하면 다른 사람이 익히고 사용하기 쉬워짐
- 단 `Exception, RuntimeException, Throwalbe, Error`는 직접 오버라이딩 하지 않는게 좋음
