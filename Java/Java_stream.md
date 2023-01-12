# 스트림 이란?

- 스트림이란 컬렉션 반복을 처리하는 기능
    - 컬랙션에 조건 필터링의 복잡한 과정을 줄익기 만들어짐
        - 거의 모든 자바 애플리케이션은 컬렉션을 만들고 처리하는 과정이 포함
        - 병렬처리를 별도의 멀티스레드 구현없이 쉽게 구현 가능
    - 수행을 선언적 으로 구현
        - 스트림은 데이터를 처리하는 부분을 질의문 형태로 처리하게 해주는 **선언형 코드 형태**
        - 복잡한 루프문을 사용하지 않음
### Stream 맛보기 (Java7 VS Java8 Stream)
#### 스트림 사용 X
스트림을 사용하지 않을때는 각 필터링 단계마다 코드를 작성해야 합니다.
```Java
// 빨간색 사과 필터링
List<Apple> redApples = forEach(appleList, (Apple apple) -> apple.getColor().equals("RED"));

// 무게 순서대로 정렬
redApples.sort(Comparator.comparing(Apple::getWeight));

// 사과 고유번호 출력
List<Integer> redHeavyAppleUid = new ArrayList<>();
for (Apple apple : redApples)
    redHeavyAppleUid.add(apple.getUidNum());
```
#### 스트림 사용 O
스트림을 사용하여 단 한줄로 표현 할수 있습니다.
```Java
List<Integer> redHeavyAppleUid = appleList.stream()
        .filter(apple -> apple.getColor().equals("RED"))        // 빨간색 사과 필터링
        .sorted(Comparator.comparing(Apple::getWeight))         // 무게 순서대로 정렬
        .map(Apple::getUidNum).collect(Collectors.toList());    // 사과 고유번호 출력
```

## 스트림 API의 특징 정리
- 선언형 : 더 간결하고 가독성이 좋아진다.
- 함수의 조립 : 유연성이 좋아진다.
- 병렬화 : 성능이 좋아진다.

## 스트림의 정의
```스트림이란 '데이터 처리연산을 지원하도록 소스에서 추출된 연속된 요소' ```
 
### 연속된 요소 (Sequence of element)
컬렉션 자료구조와 마찬가지로 스트림은 특정 요소 형식으로 이루어진 연속된 값 집합의 인터페이스를 제공합니다. 컬렉션에서는 시간, 공간의 복잡성과 관련된 요소 저장 및 연산이 이루어진다면 스트림에서는 filter, sorted, map 처럼 표현 계산식으로 이루어져 있습니다.

즉 컬렉션의 주제는 데이터, 스트림의 주제는 계산입니다.

### 소스 (Source)
스트림은 컬렉션, 배열, I/O 자원등의 소스로부터 데이터를 소비하고 정렬된 컬렉션으로 스트림을 생성하면 정렬이 그대로 유지됩니다. 즉 리스트로 스트림을 만들면 스트림의 요소는 리스트의 요소와 같은 순서를 유지합니다.


### 데이터 처리 연산
스트림은 함수형 프로그래밍에서 지원하는 연산과 데이터베이스의 SQL 질의형과 비슷한 연산을 처리할수 있습니다. filter, sort, map, match 등으로 데이터를 조작할수 있고 순차적 혹은 병렬로 실행할수 있습니다.

### 파이프라이닝 (Pipelining)
스트림 연산들은 서로 연결하여 큰 파이프 라인을 구성할수 있도록 스트림 자신을 반환합니다. 데이터 소스에 적용하는 데이터베이스 질의문과 비슷합니다.

### 내부 반복
반복자를 이용하여 명시적으로 반복하는 컬렉션과 다르게 스트림은 내부 반복 기능을 제공합니다.

```Java
List<String> highCaloriesFoodName = foodList.stream()   // 컬렉션에서 스트림을 얻는다.
        .filter(food -> food.getCalories() > 400)       // 고칼로리 음식 필터링
        .map(Food::getName)                             // 요리명 추출
        .limit(3)                                       // 위에서 3개만
        .collect(Collectors.toList());                  // 결과를 리스트로

System.out.println(highCaloriesFoodName);
```

## 스트림과 컬렉션

- 데이터 계산 시점 차이
    - 컬렉션 : 모든 요소는 컬렉션에 추가하기전에 계산되어야 한다.
    - 스트림 : 요청할때만 요소를 계산하는 고정된 자료구조
- 반복의 일회성
    - 컬랙션 : 여러번 반복 처리 가능
    - 스트림 : 단 한번만 반복문을 처리
        - 한 번 탐색한 요소를 다시 탐색하려면 초기 데이터 소스에서 새로운 스트림을 만들어야 함
- 외부 반복과 내부 반복
    - 외부 반복
        - 사용자가 직접 요소를 반복
    - 내부 반복
        - 반복을 알아서 처리하고 결과 스트림값을 어디가에 저장
        - 함수에 어떤 작업을 수행하지만 알아서 모든것을 처리해 줌
    - 내부 반복이 좋은 이유
        - 작업을 투명하게 처리
            - 병렬 처리 쉽게 구현
            - 최적화된 순서로 처리
- 자바 8 스트림에서 내부 반복을 반복하는 이유
    - 스트림 라이브러리의 내부 반복은 데이터 표현과 하드웨어를 활용하는 병렬성 구현을 자동으로 선택
    - 외부 반복에서는 병렬성을 스스로 관리해야함(스스로 관리한다는 것은 병렬성을 포기 or synchronized 처리)


## 스트림 연산
java.util.stream.Stream 에는 스트림 API에서 제공하는 여러가지 연산이 정이 되있는데 스트림 연산들은 크게 중간연산, 최종연산으로 구분할수 있습니다.
- 중간연산 : 파이프라인으로 연결할수 있는 연산들
- 최종연산 : 파이프라인을 실행한다음 닫는 연산

### 증간연산
filter나 map 같은 중간연산은 다른 스트림을 반환하기 때문에 여러개의 중간연산을 연결하여 질의를 만들수 있습니다. 중요한 특징은 최종연산을 실행하기 전까지는 아무 연산도 수행하지 않는다는것입니다.
- filter
- map
- limit
- sorted
- distinct

### 최종연산
최종연산은 파이프라인 연산의 결과를 출력합니다. 예제에서는 List 형태로만 결과를 받았지만 이 외에도 Integer, void 등 다양한 형태로 출력할수 있습니다.
- forEach : 스트림의 각 요소를 소비하면서 람다를 적용 (void 반환)
- count : 스트림의 요소 갯수를 반환 (long 반환)
- collect : 스트림을 리뷰스해서 리스트, 매, 정수 형식의 컬렉션을 반환

### SQL과 JAVA STREAM
![image](https://user-images.githubusercontent.com/67899393/211719199-b0bfce0c-3d4f-4c08-9dbe-1d9a271bfb70.png)

## 스트림 이용하기 요약
스트림을 사용하는 단계는 다음과 같이 3단계에 걸쳐서 진행됩니다.
- 질의를 수행할 데이터소스 (ex 컬렉션)
- 스트림 파이프라인을 구성할 중간 연산
- 스트림 연산을 실행하고 결과로 출력할 최종연산


# 스트림 활용

### 필터링

- 필터링
    - filter(Predicate<> predicate)
        - T -> boolean 형식의 Predicate 객체를 이용해서 필터링할 조건을 걸어준다.
        - 람다 및 메서드 참조로 이용 가능
    
    ```java
    List<Dish> vegetarianMenu = menu.stream()
                                  .filter(Dish::isVegetarian)  //  채식 인지 확인하는 메서드 레퍼런스 
                                  .collect(toList());
    ```
    
- 고유 요소 필터링
    - distinct()
    
    ```java
    List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
    numbers.stream()
          .filter(i -> i % 2 == 0)        // 짝수 선택 
          .distinct()                     // 중복 제거 
          .forEach(System.out::println);
    ```
### 슬라이싱
- Predicate를 이용한 슬라이싱
    - takeWhile(Predicate<> predicate)
        - predicate를 적용해서 필터링 하되 false가 되는 순간 반복을 멈춘다.
    ```Java
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

      numbers.stream()
              .takeWhile(i-> i < 3)
              .forEach(System.out::println);
              // 결과 : 1, 2
    ```
    - dropWhile(Predicate<> predicate)
        - predicate가 처음으로 false가 되는 지점까지 발견된 요소를 버린다.
    ```Java
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 1 ,2);

      numbers.stream()
              .dropWhile(i-> i < 3)
              .forEach(System.out::println);
              // 결과 : 3, 4, 5, 1, 2
    ```


- 스트림 축소
    - 주어진 사이즈 이하의 크기를 갖는 새로운 스트림을 반환하는 limit(n) 메서드를 지원
    
    ```java
    List<Dish> dishes = menu.stream()
                          .filter(d -> d.getCalories() > 300)    // 300 칼로리 이상 선택 
                          .limit(3) 				             // 3개만 선택 
                          .collect(toList());
    ```
    
- 요소 건너뛰기
    - 처음 n개 요소를 제외한 스트림을 반환하는 skip(n) 메서드를 지원
    
    ```java
    List<Dish> dishes = menu.stream()
                          .filter(d -> d.getCalories() > 300)
                          .skip(2)
                          .collect(toList());
    ```
    

### 매핑

- 스트림의 각 요소에 함수 적용하기
    - map(Funtion<> mapper)
        - 함수를 인수로 받아 각 요소에 함수를 적용하고 그 결과가 새로운 요소로 매핑된다.

```java
    List<String> dishNames = menu.stream()
                                .map(Dish::getName)     // 요리명을 map 메서드로 전달(추출). Stream<String> 타입 
                                .collect(toList());

//요리명의 각 길이를 알아내고자 한다면 아래와 같이 channing을 할 수 있다.

    List<String> dishNames = menu.stream()
                                .map(Dish::getName)
                                .map(String::length)
                                .collect(toList());
```

### 스트림 평면화
- map 에서 반환되는 값은 Stream<String[]> 이 되는데, 이를 문자열 스트림 (Stream<String>) 으로 변환해야 한다면 flatMap 메서드를 통해 해결 가능

```java
List<String> words = Arrays.asList("Hello", "World");

List<String> uniqueCharacters =
   words.stream()
      .map(word -> word.split(""))      // 각 단어를 개별 문자열 배열로 변환
      .flatMap(Arrays::stream)          // 생성된 스트림을 하나의 스트림으로 평면화
      .distinct()
      .collect(Collectors.toList());

System.out.println(uniqueCharacters);
```

- flatMap은 스트림의 각 값을 다른 스트림으로 만든 다음에 모든 스트림을 하나의 스트림으로 연결하는 기능을 수행
- flatmap을 사용했을 경우 내부 프로세스

### 검색과 매칭
- anyMatch(Predicate<> predicate)
    - predicate가 주어진 스트림에서 적어도 한 요소와 일치하는지 확인, boolean값 반환
- allMatch(Predicate<> predicate)
    - predicate가 주어진 스트림에서 모든 요소와 일치하는지 확인, boolean값 반환
- noneMatch(Predicate<> predicate)
    - predicate가 주어진 스트림에서 모든 요소와 일치하지 않는지 확인, boolean값 반환
- findAny()
    - 현재 스트림에서 임의의 요소를 반환
- findFirst()
    - 스트림에서 첫번째 요소를 반환

### 리듀싱
모든 스트림 요소를 처리해서 값으로 도출하는 리듀싱 연산

- 요소의 합
    - reduce(초깃값,BinaryOperator<T> accumulator)
        - 초깃값이 없는 경우 Optional 객체를 반환
    ```Java
       Optional<Integer> sum1 = Arrays.stream(array)
                .reduce(Integer::sum);
        System.out.println(sum1); // Optional[15]

        Integer sum2 = Arrays.stream(array)
                .reduce(1, (a, b) -> a + b);
        System.out.println(sum2); // 16
    ```
- 최댓값과 최솟값
    ``` Java

      Integer[] array = {1,2,3,4,5};

       Optional<Integer> max = Arrays.stream(array)
               .reduce(Integer::max);

       Optional<Integer> min = Arrays.stream(array)
               .reduce(Integer::min);

       System.out.println(max); // 5
       System.out.println(min); // 1
    ```
