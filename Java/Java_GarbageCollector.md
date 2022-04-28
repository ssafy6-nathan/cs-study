# 프로그램 메모리 관리 기법
- C언어는 개발자가 직접 메모리를 관리해야 함 (`malloc()`, `free()`)
    - 메모리를 직접 관리해야 하다보니 어려움이 많았음
- 이러한 개발자의 고충을 줄여준 것이 GC(Garbage Collector)
    - GC가 알아서 사용하지 않는 메모리를 해제해줌

# Garbage Collector
- 정의 
    - 동적 할당된 메모리 영역 가운데 더 이상 사용할 수 없게 된 영역을 탐지하여 자동으로 해제해주는 기법
- 장점
    - 메모리 누수 방지
    - 해제된 메모리에 접근 방지
    - 해제된 메모리를 다시 해제하는 행동 방지
- 단점
    - GC 작업 자체는 프로그램 실행의 오버헤드가 됨
    - 개발자는 메모리가 해제되는 타이밍을 알기 힘듬

##  GC 알고리즘
- Reference Counting
    - GC의 초기 알고리즘
    - Garbage를 발견하는 것에 초점이 맞춰져 있음
    - 어느 한 메모리가 다른 메모리를 얼마나 많이 참조하는지 횟수를 세어서 메모리 접근 가능과 불가능을 나누는 방식
    - 객체가 참조되거나 역참조될 때마다 GC가 작동함
    - 순환 참조 문제를 해결하지 못하는 한계점을 갖고 있음

![image](https://user-images.githubusercontent.com/47655983/165526093-29fa03ab-b296-4afb-ae7f-28dceaceb261.png)

<br>

- Mark And Sweep
    - Reference Counting 방식의 순환참조 문제를 해결한 알고리즘
    - root에서부터 해당 객체에 접근 가능한지를 해제의 기준으로 삼음
    - Mark단계와 Sweep단계로 나뉨
    - 의도적으로 GC를 실행시켜야 함
    - 어플리케이션 실행과 GC 실행이 병행됨

![MarkAndSweep1](https://user-images.githubusercontent.com/47655983/165549791-84fc6901-6159-48f7-8040-2499051fb5a4.png)

> 초기에 위와같은 메모리 구조가 있고 GC가 실행되면 Mark단계가 실행되어 root에서부터 접근할 수 있는 모든 객체를 확인함

<br>

![MarkAndSweep2](https://user-images.githubusercontent.com/47655983/165551109-64baa832-72fb-4163-80bf-4f94a63e46c2.png)

> Mark단계를 통해 접근할 수 있는 객체를 Reachable이라하고 연결이 끊어진 객체를 UnReachable이라고 부른다. Mark단계가 끝나면 Sweep단계로 넘어가서 UnReachable 객체들을 해제함

<br>

![MarkAndSweep3](https://user-images.githubusercontent.com/47655983/165551121-0fc262b2-cfb2-4b98-8b17-5e305be3c388.png)

> Sweep단계가 끝나고 메모리 파편화 현상을 막기 위해 Compation이라는 과정을 거치기도 하는데 이 과정을 거치면 Reachable 객체 메모리 주소가 응집 됨

<br>

![MarkAndSweep4](https://user-images.githubusercontent.com/47655983/165551129-4a9ce380-4743-440b-8ba0-790a5a9ebf99.png)

<br>

# Java Garbage Collector
- Java의 GC는 Mark And Sweep 알고리즘으로 동작함
- GC를 실행하기 위해선 어플리케이션의 실행을 멈춰야 하는데 이를 가리켜서 `stop-the-world`라고 함
    - GC튜닝이란 stop-the-world의 시간을 줄이는 것

## JVM 구조
![image](https://user-images.githubusercontent.com/47655983/165556975-2a5fbd69-07b7-4b4f-8f63-9dfd8e3af1cd.png)

- Class Loader
    - 바이트 코드를 읽고 클래스 정보를 메모리의 Heap/Method Area에 저장함
- Runtime Data Area
    - 실행중인 프로그램의 정보가 올라가 있는 메모리 영역
- Excution Engine
    - 바이트코드를 어셈블러 같은 네이티브코드로 변환시켜줌

### JVM 메모리 구조
![image](https://user-images.githubusercontent.com/47655983/165558390-7ab13975-d911-4d44-ae8b-a836e9bd3676.png)
- 전체쓰레드 공유
    - Method Area
        - 클래스의 필드, 메소드 정보, static 변수 등의 정보를 저장해둠
    - Heap
        - 클래스의 인스턴스와 배열이 할당되는 영역
        - 할당된 객체는 직접 해제가 불가능하며 오로지 GC에 의해 해제됨
- 쓰레드별 데이터 영역
    - PC Register
        - 쓰레드가 현재 실행할 스택 프레임의 주소를 저장
    - Stack
        - 메서드 호출을 스택 프레임이라는 블록으로 쌓으며 로컬 변수, 중간 연산 결과들이 저장되는 영역
    - Native Method Stack
        - C나 C++ 등의 Low Level 코드를 실행하는 스택(JNI 참조)
- `GC의 root space는 Stack, Native Method Stack, Method Area`

![image](https://user-images.githubusercontent.com/47655983/165561696-22133e8b-b9b8-4d46-8f9a-1bcb04188ef3.png)

- Young Generation
    - 생성된지 얼마 안된 객체들이 저장되는 장소 대부분의 객체가 이 영역에서 생성되었다가 Minor GC를 통해 사용되지 않는 객체가 제거됨
    - eden
        - 새롭게 생성된 객체들이 할당되는 영역
    - Survival0
        - eden에서 Minor GC로부터 살아남은 객체들이 S1과 번갈아가며 존재하는 영역
    - Survival1
        - eden에서 Minor GC로부터 살아남은 객체들이 S0과 번갈아가며 존재하는 영역
- Old Generation
    - 생성된지 오래된 객체들이 저장되는 장소, Young 영역에서 살아남은 객체가 이곳으로 옮겨지고 Major GC를 통해 사용되지 않는 객체가 제거됨
- Permanent Generation
    - 프로그램 코드가 올라가는 부분, Code가 모두 로딩되고 나면 거의 일정한 수치를 유지함

### Minor GC, Major GC
- Young Generation에서 사용하는 GC를 Minor GC라고 함
    - Copying garbage 알고리즘으로 동작
        - root에서 그래프순회 객체들을 mark하고 그대로 이동할 메모리 영역에 붙여넣는 방식
        
![image](https://user-images.githubusercontent.com/47655983/165769594-9b255c85-161c-4d76-b438-06702f7afc3f.png)

![image](https://user-images.githubusercontent.com/47655983/165769891-aaebc674-0845-42e7-a1cd-ffda4cb96673.png)

- Old Generation에서 사용하는 GC를 Major GC라고 함
    - Mark ANd Sweep 알고리즘으로 동작

### GC의 동작
![image](https://user-images.githubusercontent.com/47655983/165565058-64e5b0c4-5e32-4a59-bf38-c546d2b1f402.png)

> eden영역이 꽉 차면 Minor GC가 동작함

![image](https://user-images.githubusercontent.com/47655983/165565847-028bea2d-2072-48c6-816e-b905c779b7b6.png)

> 살아남은 객체는 S0로 이동하고 age-bit가 상승함

![image](https://user-images.githubusercontent.com/47655983/165566088-20585e36-1f99-4c18-8969-b3ae7964fc51.png)

> eden영역이 또 꽉 차면 Minor GC가 동작함

![image](https://user-images.githubusercontent.com/47655983/165566517-1b34cbaa-6699-4dfb-847e-2da579d1f8b4.png)

> 이전엔 S0에 이동했으므로 이번에 살아남은 객체들은 모두 S1영역으로 이동하고 age-bit가 상승함(S0에 살아남아있던 객체3개와 이번에 eden에서 살아남은 객체 2개)

> 이후에도 위와같이 S0과 S1을 번갈아가며 객체를 이동시키다가 age-bit가 특정 숫자이상 올라가면 오래도록 참조될 객체라고 판단하여 Old영역으로 객체를 이동시켜줌, 이 과정을 `Promotion`이라고 함 (Java8에서는 Parallel GC기준 age-bit가 15가 되면 이동)

![image](https://user-images.githubusercontent.com/47655983/165569701-13433187-602d-429b-92c4-2b80ee849973.png)

> Old영역도 가득차게 되면 Major GC가 발생하며 Mark And Sweep 방식으로 필요없는 메모리를 비워주게 됨 (Major GC가 Minor GC보다 오래걸림)

### 영역을 나눠서 GC를 하는 이유
- 대부분의 객체들은 금방 사라짐
- GC는 자체가 오버헤드
    - 따라서 특정 메모리영역만 탐색해서 해제하면 더 효율적

![image](https://user-images.githubusercontent.com/47655983/165576530-5883809e-9fad-461f-99d5-218fd263dd3d.png)

<br>

# 참고
- https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=kbh3983&logNo=220967456151
- https://hongsii.github.io/2018/12/20/jvm-memory-structure/
- https://rebelsky.cs.grinnell.edu/Courses/CS302/99S/Presentations/GC/
- https://steady-coding.tistory.com/305
- https://www.youtube.com/watch?v=FMUpVA0Vvjw
- https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/generations.html