- 들어가기 전에.. stack, heap, queue
    
    ![https://blog.kakaocdn.net/dn/CpacG/btrlpWYqAvi/Ftqu4E9kkC2gv0CHVJw5U1/img.jpg](https://blog.kakaocdn.net/dn/CpacG/btrlpWYqAvi/Ftqu4E9kkC2gv0CHVJw5U1/img.jpg)
    
    **스택** : 출입구가 하나뿐인 깊은 우물 같은 데이터 구조, 저장할 수 있는 스택이 넘칠 때 에러가 남
    
    (ex. 데이터를 a, b, c, d 순으로 저장했다면 d, c, b, a 순으로 꺼냄, 후입 선출)
    
    *이것이 스택오버플로우의 그 '스택'!!*
    
    **큐** : 파이프처럼 양쪽에 입구와 출구가 있는 구조
    
    (ex. 데이터를 a,b,c,d 순으로 저장했다면 a, b, c, d 순으로 꺼냄, 선입선출)
    

### 스택

JS는 스택을 가진다. 함수가 실행되면 이 스택에 들어가고 실행이 종료되면 스택에서 빠진다.

콜스택은 자바스크립트 엔진에서 단 하나만 존재하고 위에서 아래로 실행된다.

→ 콜스택은 **동기적**으로 처리된다.(한번에 하나씩만 처리됨, 한번에 병렬처리 불가능)

<aside>
💡 콜백함수는 이벤트 루프에 의해서 콜스택에 삽입된다.

</aside>

함수가 실행되면 스택에서 스택프레임 하나가 생기고 그곳에 삽입된다.

스택프레임속에는 실행 컨텍스트가 들어있고 그안에는 변수객체가 있다. 

변수객체 속에는 함수에서 사용하는 지역변수나 매개변수 this 바인딩 등의 정보가 들어가있다.

그리고 함수가 리턴되면 스택프레임도 반납된다.

### 자바스크립트 동기 처리 방식

자바스크립트 엔진은 콜스택에 스택 프레임들을 생성한다고 했다. 엔진은 각 프레임들의 위치를 알고 있기 때문에 스택의 탑에서부터 바텀으로 내려오면서 하나하나 함수를 실행해 갈 수 있는 것이다.

### 다음 코드가 순서대로 실행되었을 때 어떻게 동작하는지 살펴보자.

```jsx
function firstFunction(){ console.log("Hello from firstFunction");}
function secondFunction(){ firstFunction(); console.log("The end from secondFunction");}

secondFunction();
```

1. `secondFunction()`이 호출되면 빈 스택프레임이 생성된다.
2. `secondFunction()`이 `firstFunction`을 호출하면 `firstFunction`이 스택에 삽입된다.
3. `firstFunction`속에서 콘솔이 찍히고 리턴된다.
4. `firstFunction`는 스택에서 삭제된다.
5. 그다음 `secondFunction()`에 대해서도 위와 같은 과정이 반복된다.

<aside>
💡 재귀함수 호출을 잘못 사용하게 되면 스택이 꽉차게 되어`Stack Overflow`가 발생할 수 있다.

</aside>

무한루프에 빠져서 콜스택이 꽉차버리게 되면 브라우저는 에러를 던진다.

## 힙

- 힙은 구조화 되지 않은 메모리이다.
- 객체나 변수는 이곳에 할당된다.

## 큐

만약 어떤 이벤트에 핸들러가 등록되어 있다고 해보자. 

이때, 그 이벤트가 발생하게 되면 큐에 그 이벤트에 관한 메세지가 쌓인다.

이 메세지는 스택에 충분한 공간이 있을때 큐에서 하나씩 꺼내서 처리된다.

이벤트 핸들러가 등록되지 않은 이벤트는 큐에 삽입되지 않는다.

**실행 컨텍스트가 왜 중요할까?**

EC는 scope, hoisting, this, function, closure 등의 동작 원리를 담고 있는 JS의 핵심 원리이다. 이를 이해하지 못하면 코드 독해가 어려워지며 디버깅도 곤란해질 것이다.

### 실행 컨텍스트

**실행 컨텍스트*execution context*** : 실행할 코드에 제공할 환경 정보들을 모아놓은 객체

> Execution context (abbreviated form — EC) is the abstract concept used by ECMA-262 specification for typification and differentiation of an executable code.
- ECMAScript
> 

실행 콘텍스트는 실행가능한 코드를 형상화하고 구분하는 추상적인 개념이다.
즉, EC는 JS 엔진이 실행 가능한 코드가 실행되기 위해 필요한 환경(Environment)!

**실행 컨텍스트가 생성되는 3가지 경우**

브라우저나 노드에서 js를 실행시키는 순간 글로벌 실행 컨텍스트가 생성된다.

<aside>
💡 유명한 자바스크립트 엔진 2가지 : `구글 V8,` `SpiderMonkey`

</aside>

- 실행 가능한 코드 ⇒ 이 때 실행 컨텍스트가 생성된다.
    - 전역 코드 : 전역에 존재하는 코드
    - eval 코드 : eval 함수로 실행되는 코드
    - 함수 코드 : 함수 내에 존재하는 코드

동일한 환경에 있는 코드들을 실행할 때 필요한 환경 정보들을 모아 컨텍스트를 구성하고, 이를 콜 스택*call stack*에 쌓아올렸다가, 가장 위에 쌓여있는 컨텍스트와 관련 있는 코드들을 실행하는 식으로 전체 코드 환경과 순서를 보장한다.

- ***※ 동일한 환경이란?***
    
    하나의 실행 컨텍스트를 구성할 수 있는 방법으로 전역공간, eval() 함수, 함수 등이 있다. 자동으로 생성되는 전역공간과 eval을 제외하면 **흔히 실행 컨텍스트를 구성하는 방법은 함수를 실행하는 것뿐이다.**
    

```jsx
var num = 2;

function pow(num){
	return num * num;
}
```

## 자바스크립트 엔진은 위 코드를 어떻게 해석할까?

1. top to bottom으로 훑으면서 변수와 함수가 있다는걸 알아챈다.
2. 이 변수와 함수를 메모리에 올린다. (함수를 호출하는게 아니다.)
→ **변수는 이 과정에서 이미 초기화 된다.**

---

어떤 실행 컨텍스트가 활성화될 때 자바스크립트 엔진은 해당 컨텍스트에 관련된 코드들을 실행하는 데 필요한 환경 정보들을 수집해서 실행 컨텍스트 객체에 저장한다.

(이 객체는 자바스크립트 엔진이 활용할 목적으로 생성할 뿐 개발자가 코드를 통해 확인할 수는 없음.)

- **VariableEnvironment**  : enviromentRecord(snapshot) / outerEnvironmentReference(snapshot) 현재 컨텍스트 내의 식별자들에 대한 정보 + 외부 환경 정보, 선언 시점의 LexicalEnvironment의 스냅샷*snapshot*으로 변경사항은 반영되지 않음
- **LexicalEnvironment** : environmentRecord / outerEnviromentReference 처음에는 VariableEnvironment와 같지만 변경 사항이 실시간으로 반영됨.
- **ThisBinding** : this 식별자가 바라봐야 할 대상 객체

### VariableEnvironment

VariableEnvironment에 담기는 내용은 LexicalEnvironment와 같지만 **최초 실행 시에 스냅샷을 유지**한다는 차이가 있다. VariableEnvironment에 정보를 먼저 담은 다음, 이를 그대로 복사해서 LexicalEnvironment 를 만들고, 이후에는 LexicalEnvironment 를 주로 활용한다.

### LexicalEnvironment

문서에는 '어휘적 환경', '정적 환경'으로 많이 등장하지만 이보다는 백과사전에서 단어를 검색하면 단어에 대한 설명이 나오는 것과 비슷한 '사전적인' 느낌으로 이해하면 좋다. 즉, "현재 컨텍스트 내부에는 a, b, c와 같은 식별자들이 있고 그 외부 정보는 D를 참조하도록 구성돼있다"라는 컨텍스트를 구성하는 환경 정보들을 사전에서 접하는 느낌으로 모아놓은 것이다.

### environmentRecord와 호이스팅

**environmentRecord**에는 현재 컨텍스트와 관련된 코드의 식별자 정보들이 저장된다. 컨텍스트를 구성하는 함수에 지정된 매개변수 식별자, 선언한 함수가 있을 경우 그 함수 자체, var로 선언된 변수의 식별자 등이 식별자에 해당한다. 컨텍스트 내부 전체를 처음부터 끝까지 쭉 훑어 나가며  **순서대로 수집**한다**.** 변수 정보를 수집하는 과정을 모두 마쳤더라도 아직 실행 컨텍스트가 관여할 코드들은 실행되기 전의 상태인데, 코드가 실행되기 전임에도 자바스크립트 엔진은 이미 해당 환경에 속한 코드의 변수명들을 모두 알고 있게 되는 셈이다. 이 말은,

⇒ '자바스크립트 엔진은 식별자들을 최상단으로 끌어올려놓은 다음 실제 코드를 실행한다'라고 생각할 수 있다.

(실제로 끌어올리는 것은 아닌 가상의 개념)

'끌어올리다' 라는 의미의 **호이스팅*hoisting*** 이 여기에서 나온 것.

※ **전역 실행 컨텍스트**는 변수 객체를 생성하는 대신 자바스크립트 구동 환경이 별도로 제공하는 객체, 즉 전역 객체 *global object*를 활용한다. 전역 객체에는 브라우저의 **window**, Node.js의 **global** 객체 등이 있다. 이들은 자바스크립트 내장 객체*native object*가 아닌 호스트 객체*host object*로 분류된다.

**호이스팅 규칙**

변수 : 선언부와 할당부를 나누어 선언부만 끌어올림 (변수의 할당부는 그자리에 둠)

함수 선언문 : 함수 전체를 끌어올림

함수 표현식 : 변수 선언부만 끌어올림

### 함수 선언문과 함수 표현식

**함수 선언문*function declaration*** : function 정의부만 존재하고 별도의 할당 명령이 없는 것, 반드시 함수명이 정의되어 있어야 함, 기명 함수 표현식

**함수 표현식*function expression*** : 정의한 function을 별도의 변수에 할당하는 것(=함수를 다른 변수의 값으로써 '할당'), 함수명 없어도 됨, 상대적으로 안전, 익명 함수 표현식

```jsx
function a () { }// 함수 선언문. 함수명 a가 곧 변수명.
a();// 실행 OKvar b =function () { }// (익명) 함수 표현식. 변수명 b가 곧 함수명.
b();// 실행 OKvar c =functiond () { }// 기명 함수 표현식. 변수명은 c, 함수명은 d.
c();// 실행 OK
d();// 에러!
```

**※기명 함수 표현식 주의점** : 외부에서는 함수명으로 함수를 호출할 수 없음! 함수명은 오직 함수 내부에서만 접근할 수 있음!

**override** : 동일한 변수명에 서로 다른 값을 할당할 경우 나중에 할당한 값이 먼저 할당한 값을 덮어씌움

---

### 스코프, 스코프 체인, outerEnvironmentReference

**스코프*scope*** : **식별자에 대한 유효범위**

**스코프 체인scope chain** : **'식별자의 유효 범위'를 안에서부터 바깥으로 차례로 검색해나가는 것**

그리고 이것을 가능케 하는 것이 LexicalEnvironment의 두 번째 수집자료인 outerEnvironmentReference 이다.

outerEnvironmentReference 는 현재 호출된 함수가 **선언될 당시**의 LexicalEnvironment를 참조한다. '선언'이라는 행위가 일어날 수 있는 시점이란 콜 스택 상에서 어떤 실행 컨텍스트가 활성화 된 상태일 때뿐이다. 어떤 함수를 선언(정의)하는 행위 자체가 하나의 코드에 지나지 않으며, **모든 코드는 실행 컨텍스트가 활성화 상태일 때 실행**되기 때문이다.

예를 들어 a함수 내에 b함수, b 함수 내에 c 함수를 선언한 경우, 함수 C의 outerEnvironmentReference 는 함수 B LexicalEnvironment를 참조하는 식이다. 이렇게 계속 찾아 올라가면 마지막엔 전역 컨텍스트의 LexicalEnvironment가 있을 것이고, 각 outerEnvironmentReference는 오직 자신이 선언된 시점의 LexicalEnvironmen만 참조하고 있으므로 가장 가까운 요소부터 차례대로 접근하고, 다른 순서로 접근하는 것은 불가능하다. 이런 구조적 특성 덕분에 **여러 스코프에서 동일한 식별자를 선언한 경우에는 무조건 스코프 체인 상에서 가장 먼저 발견된 식별자에만 접근 가능**하게 된다.

---

---

## 5. 정리

**실행 컨텍스트**

- 제공할 환경 정보들을 모아 놓은 객체
- 실행 컨텍스트 객체는 활성화 되는 시점에 VariableEnvironment, LexicalEnvironment, ThisBinding의 세 가지 정보 수집
- VariableEnvironment와 LexicalEnvironment는 environmentRecord(매개 변수명, 식별자, 함수명등 수집)와 바로 직전 컨텍스트의 LexicalEnvironment 정보를 참조하는 outerEnvironmentReference로 구성된다
- 실행 컨텍스트 생성할 때, VariableEnvironment는 초기상태 유지, LexicalEnvironment는 실행 도중 변경사항 즉시 반영된다.

**호이스팅**

- 실행 컨텍스트가 관여하는 코드의 최상단에 끌어올린다
- 변수 언건과 할당이 동시에 이루어지면 '선언부'만 호이스팅한다 => 선언문과 표현식의 차이 발생

**스코프**

- 변수의 유효범위.
- outerEnvironmentReference는 해당 함수 선언된 위치인 LexicalEnvironment를 참조
- 전역 컨텍스트의 LexicalEnvironment까지 탐색해도 변수 찾지못하면 undefined 반환
- 전역변수: 전역 컨텍스트의 LexicalEnvironment 담긴 변수

**this**

- 실행 컨텍스트 활성화하는 당시 지정된 this가 저장
- 지정되지 않으면 전역 객체 저장

---


JS를 실행시키면 JS 엔진은 callStack에 전역 실행 컨텍스트들을 담는다. (Global Execution Context)

전역에 있는 코드가 마지막 라인까지 모두 실행되면 전역 실행 컨텍스트도 사라진다. 

## 1. Record로 JS 호이스팅 이해하기

선언 라인 전에 콘솔을 찍어보면 에러나는 게 아니고 undefined!

- 선언 라인 전에도 에러가 나지 않고 **변수를 참조**할 수 있는 현상!
- 선언문이 마치 최상단에 끌어올려진 듯한 현상!
- Hoisting!!

이 현상이 발생하는 이유는 선언문이 있는 코드라인을 물리적으로 최상단에 끌어올렸기 때문이 아니라! **js 엔진이 먼저 전체 코드를 스캔하면서 변수 같은 정보를 실행 컨텍스트 어딘가에 미리 기록해놓기 때문이다.** 이 때 기록해 놓는 곳이 Record.

에크마 스크립트 스펙에 나오는 정식 명칭은 Environment Record. 환경 레코드로, 식별자와 식별자에 바인딩된 값을 기록해두는 객체이다. 

- 환경레코드에 변수가 어떻게 저장되는지! 에 주목!

- 변수 호이스팅, 함수 호이스팅
    

### Variable Hoisting

- 변수 호이스팅
- **`생성 단계`**: Execution Context를 생성하고 선언문만 실행해서 Environment Record에 기록
    
    - JavaScript Engine은 코드를 실행하면 우선 Global Execution Context(전역실행컨텍스트)의 한 칸을 생성해서 Call Stack에 넣음
   
    - 그 후, 전체 코드를 스캔하면서 선언할 것이 있는지 찾아보고 있으면 먼저 선언을 함
   
    - 선언하는 과정에는 생성해둔 Execution Context 안에 있는 Enviroment Record에 새로운 식별자 TV Channel 을 기록
    
    - var 키워드의 경우 undefined로 값을 초기화 해 둠
   
    
    = 이렇게 본격적인 실행에 앞서 스캔하고 준비하는 단계를 **생성 단계**라고 한다.
    
    <aside>
    💡 실행 컨텍스트를 생성하고, 선언문만 먼저 실행해서 환경레코드에 미리 기록해두는 단계!
    
    </aside>
    
- `**실행단계**` : 선언문 외 나머지 코드를 순차적으로 실행 단계
- 필요한 경우 생성 단계에서 환경 레코드에 기록해둔 정보를 참고하거나 업데이트 하게 된다.
- 더 자세히..
    
    - 첫 번째로 TV Channel의 값을 출력하는 console.log가 실행된다.
    - TVChannel에 바인딩 된 값은?
    - js 엔진은 현재 활성화된 실행 컨텍스트 내에 환경 레코드를 보고 이미 기록된 TVChannel의 값을 참조해서 문제없이 값을 출력해낸다.
    
    - 선언은 아까 생성단계에서 이미 했으니 이 라인의 할당만 실행해준다.
    
    - TVChannel에 바인딩 된 값을 Netflix로 업데이트해서 기록해둔다.
        
    - 이후 마지막 라인을 실행하면 js 엔진은 역시 환경레코드를 참조해서 TVChannel의 값을 Netflix로 결정해낸다.
    - **실행 단계**: 선언문 외 나머지 코드를 순차적으로 실행, Eviroment Record를 참조하거나 업데이트
        - 변수가 사용이 되면, 해당 변수에 바인딩된 값을 JavaScript Engine이 현재 활성화된 Execution Context의 Enviroment Record 내에서 찾음
        - 만약 var 키워드 대신 ES6에서 추가된 const 키워드로 변수를 선언하면 어떻게 될까?
        - var의 경우 변수 값이 할당이 된 시점에서 업데이트

### const, let

- JavaScript Engine이 식별자를 기록하지만 값을 초기화하지는 않음
- 선언문 이전에 변수를 참조하려고 하면, Reference Error가 발생
- **Temporal Dead Zone**(일시적 사각지대)
    - let 또는 const으로 선언했을 때, 선언 이전에 식별자를 참조할 수 없는 구역
- **Declaration**(선언): 메모리 공간을 확보하고 식별자와 연결
- **Initialization**(초기화): 할당문 직전까지는 초기화를 하지 않음

### var

- **var 키워드는 선언과 초기화가 동시에 이루어짐**
- **Declaration**(선언): 메모리 공간을 확보하고 식별자와 연결
- **Initialization**(초기화): 식별자에 암묵적으로 undefined 값 바인딩

### 호이스팅 종류

호이스팅의 종류에는 변수 호이스팅과 함수 호이스팅이 있다.

### 1. 변수 호이스팅 (var, let/const)

### 변수를 var로 선언한 경우

자바스크립트 엔진이 전체 코드를 스캔할 때 **undefined라는 값을 할당하여 환경 레코드에 기록**한다.

(위의 이미지: TVChannel 변수를 var 키워드로 선언하여 코드 스캔 시undefined 값을 할당받아 전역 컨텍스트의 환경 레코드에 기록된 상태)

### 예시 코드 실행

1. 첫 번째 줄: 스캔이 끝난 코드를 바로 출력하여 전역 컨텍스트의 환경 레코드에 기록된 undefined를 그대로 출력한다.
    
    > 🙋‍♀️ 참고: console.log()도 log() 함수이기 때문에자바스크립트 엔진에 의해 호출되면console.log 함수에 대한 실행 컨텍스트가 생성된다.
    > 
2. 두 번째 줄: 같은 변수(식별자)에 'Netflix'라는 값이 재할당되었다.
3. 마지막 3번째 줄: 재할당된 값('Netflix')을 출력한다.

### 변수를 let 또는 const로 선언한 경우

자바스크립트 엔진이 환경 레코드에 **식별자를 기록만 해두고 값을 초기화하지는 않는다.** (= undefined로 미리 특정 값을 할당하지 않는다)

때문에, 선언하기 이전에 식별자를 '참조'(= 출력 또는 사용)하려고 하면 Reference Error가 발생한다.

> 일시적 사각지대 (Temporal Dead Zone)let이나 const로 선언하여 선언 라인 이전에 식별자를 참조할 수 없게 되는 구역
> 

### var와 let/const의 차이 추가 정리

var 키워드로 변수를 선언하는 경우에는 **선언과 동시에 초기화**가 이루어진다.

**선언 단계**에서는 메모리 공간을 확보한 뒤 메모리 주소에 **식별자를 연결**하고,**초기화 단계**에서는 식별자를 암묵적으로 **undefined 값으로 초기화**한다.

변수를 let/const 키워드로 선언하면 선언 단계에서 메모리에 식별자는 연결해두지만, **값은 undefined로 초기화하지 않는다.**

따라서 **재할당 전까지는 변수에 아무 값이 담기지 않아서** 선언만 했을 때는 값을 읽어올 수가 없다. (일시적 사각지대 발생)

### 추가 정보

let/const는 비교적 최근에 추가되었다. 이는 '선언문 이전에는 변수를 참조할 수 없다'는 일반적인 프로그래밍 방식을 자바스크립트도 추구하도록 '언어적 차원에서 보완된 것'이라 할 수 있다.

---

### const, let

- JavaScript Engine이 식별자를 기록하지만 값을 초기화하지는 않음
- 선언문 이전에 변수를 참조하려고 하면, Reference Error가 발생
- **Temporal Dead Zone**(일시적 사각지대)
    - let 또는 const으로 선언했을 때, 선언 이전에 식별자를 참조할 수 없는 구역
- **Declaration**(선언): 메모리 공간을 확보하고 식별자와 연결
- **Initialization**(초기화): 할당문 직전까지는 초기화를 하지 않음

### var

- **var 키워드는 선언과 초기화가 동시에 이루어짐**
- **Declaration**(선언): 메모리 공간을 확보하고 식별자와 연결
- **Initialization**(초기화): 식별자에 암묵적으로 undefined 값 바인딩

---

### 2. 함수 호이스팅    

자바스크립트에서는 **함수를 변수에 담을 수 있다**는 특징이 있다. 이를 함수 표현식이라고 한다.

### 함수 표현식(Function Expression)

함수를 변수에 담고 있기 때문에 기본적으로 변수 호이스팅과 똑같이 동작하지만 var의 경우에는 차이가 있다.

**var 변수에 함수를 담아 선언문 이전에 실행하려고 하면 undefined가 뜨는 게 아니라 Type error가 뜬다.**(아래 예제 해설에서 추가 설명)

let/const 키워드의 함수 표현식은 그냥 변수 호이스팅의 경우와 똑같다.(=> 아직 환경 레코드에서 기록된 값이 없어서 Reference error 발생)

### 함수 선언문 (Function Declaration)

var, let/const와 같은 키워드가 아닌 그냥 **function 키워드로 함수를 선언**하는 방식이다.

함수 선언문은 자바스크립트 엔진이 코드를 스캔할 때 **완성된 함수 전체를 한 번에 환경 레코드에 기록해둔다.**

그래서 코드 상의 어디에서 호출해도 **이미 key 역할을 하는 식별자 함수가 그 함수의 value인 함수로 초기화되어 있기 때문에** 에러가 발생하지 않는다.

이러한 특징 때문에 사용을 지양하려는 목소리도 있다.

### 함수 표현식 Quiz

다음 예제들의 결과를 생각해보자.

### 1번째 예제

```jsx
count();

var count = function() {
    console.log('count는 1이다.');
}
```

### 2번째 예제

```jsx
var count = function() {
    console.log('count는 1이다.');
}

count();
```

### 3번째 예제

```jsx
count();

let count = function() {
    console.log('count는 1이다.');
}
```

...

첫번째 결과는 TypeError 이다. var 는 호이스팅의 영향을 받으므로 위로 끌어올려진다.그러므로 var count; 가 가장 먼저 실행되는데 undefined이다.그 후로 count를 바로 '호출'하는데 호출은 함수 타입에만 가능하다.하지만 undefined 데이터 타입은 함수가 아니므로 Type error를 발생시킨다.

```jsx
var count;    // undefined

count();      // count는 함수가 아닌데 왜 함수를 호출하니?

var count = function() {
    console.log('count는 1이다.');
}
```

두번째 결과는 정상적으로 console.log가 동작한다. var count가 호이스팅으로 인해 위로 끌어올려지지만, count() 호출 전에 undefined 대신 함수를 재할당하므로 count를 '호출'하였을 때 함수임을 인식하여 정상 작동한다.

```jsx
var count;    // undefined

var count = function() {  // count에 함수 재할당
    console.log('count는 1이다.');
}

count(); // 함수 호출 (정상 작동)
```

세번째 결과는 Reference Error 이다.키워드가 let이어서 count 식별자의 값이 아직 정의되지 않은 상태이므로호출이든 출력이든 어떤 형태로든 이 식별자를 '사용'하려고 하면Reference Error가 발생하는 것이다.

```jsx
count(); // 값이 없어...

let count = function() {
    console.log('count는 1이다.');
}
```

### 함수 선언문 Quiz

다음 예제들의 결과를 생각해보자.

```jsx
count();

function count() {
    console.log('count는 2이다.');
}
```

```jsx
function count() {
    console.log('count는 2이다.');
}

count();
```

...

첫번째 두번째 모두 정상 작동한다.

처음에 코드를 스캔할 때 **함수 선언문은 완성된 상태로 환경 레코드에 기록**되기 때문에호출이 함수 선언문의 위에 있든 아래쪽에 있든 상관없이 잘 작동하게 된다.

즉, 선언과 동시에 작성된 함수로 값도 초기화하여,선언하기 이전에도 함수를 사용할 수 있는 것이다.

## 2. Outer로 JS 스코프체이닝 이해하기

Outer의 정식 명칭은 **'외부 환경 참조(Outer Environment Reference)'**이다.

실행 컨텍스트의 내부에 **Lexical Environment(렉시컬 환경, 정적 환경)**가 존재하고 그 안에 Record(환경 레코드)와 Outer(외부 환경 참조)가 들어있다.

Outer는 내부 함수의 실행 컨텍스트를 실행할 때 참조할 값을 외부 함수의 실행 컨텍스트로 범위를 한 단계씩 넓혀 찾는 것이다.

위의 그림에서는 3층(가장 안쪽에 선언된 함수)에서 필요한 값을2층(3층의 외부 함수)에서 찾아보고,없으면 1층(전역 컨텍스트)까지 내려가면서 찾는다.

Outer를 통해 **스코프 체이닝(Scope Chaining)**이 일어나는 것이다.

> 스코프 체이닝 (Scope Chaining)식별자를 찾기 위해 점점 범위(스코프)를 넓혀가며 찾는 과정(cf. 스코프 체인: 이 과정에서 스코프들이 연결된 리스트)
> 

### 식별자 결정 (Identifier Resolution)

**코드 실행 시 특정 변수나 함수의 값을 결정하는 것**이다.

처음에 전역 변수를 선언한 뒤 **내부 함수에서 값을 재할당**하면 코드 상에서 **동일한 식별자가 여러 개가 된다.** 이러한 상황에서 자바스크립트 엔진이 어느 값을 참조(사용)할지 결정하는 것이다.

3층 함수에서 lamp 변수의 값을 출력하는 코드가 있지만 3층 함수 내부에는 lamp 식별자가 없다. 그래서 lamp 식별자의 값을 찾기 위해 사다리를 하나 타고 내려간다. (바로 바깥의 외부 함수로 이동)

2층에는 lamp가 있고 값은 on이다. 그런데 1층에도 lamp가 있고, 다른 값이 할당되어 있다. (예: off)

이러한 경우 자바스크립트 엔진은 lamp의 값을 무엇으로 결정할까?

'2층에서 값을 찾으면 1층까지 안 가고 바로 출력해버린다.'

자바스크립트 엔진은 lamp라는 변수의 값을 찾기 위해 한 칸씩 바깥 스코프로 이동하다가lamp라는 식별자를 발견하면 바로 그 값을 출력한다. 더 아래로 내려가서 또 다른 lamp가 있어도 **어차피 같은 식별자이기 때문에 그 값은 인식하지 않는다.**

> 변수 섀도잉 (Variable Shadowing)
동일한 식별자 선언으로 인해 더 상위 스코프(=더 아래층)에서 선언된 식별자의 값이 가려지는 현상


