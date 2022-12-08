# JavaScript 는 무엇을 하는 언어인가?

## 간략하게 보는 JavaScript 연대기

### Java"Script"

자바스크립트는 본래 스트립트 언어이다. 스크립트 언어란 `이미 존재하는 프로그램을 제어하기 위해 사용되는 언어` 를 말한다. 본래 JavaScript는 브라우저를 제어하기 위해 탄생한 언어이다. HTML이 웹페이지의 기본 구조, CSS이 웹페이지의 디자인, JavaScript가 웹페이지의 동작을 담당한다.

### V8 엔진의 등장

2008년 구글에서 V8 자바스크립트 엔진을 발표했다. V8 엔진은 C++로 작성된 자바스크립트 엔진으로 JavaScript의 실행속도를 획기적으로 빠르게 만들었다. 또한 V8 엔진은 독립형으로 개발되었기 때문에 브라우저 뿐만 아니라 다른 C++ 프로그램에 내장해서 사용할 수 있다. V8 엔진 덕분에 브라우저 내 웹페이지의 동작 속도가 매우 빨라졌을 뿐 아니라 브라우저 밖에서도 JavaScript를 사용할 수 있게 되는 기반이 된다.

### Node.js

V8의 출현으로 우리가 흔히 사용하는 Node.js가 등장하게 되었다. Node.js 는 V8 엔진을 기반으로 하는 JavaScript 런타임 프로그램이다. JavaScript는 원래 브라우저라는 실행환경에서만 동작하는 언어였는데 이제 Node.js라는 실행환경 위에서도 작동할 수 있게 된 것이다. 덕분에 우리는 Node.js 로 웹서버를 구축하는 등 자체 어플리케이션을 만들 수 있게 되었다. JavaScript 하나로 프론트엔드, 백엔드를 모두 다룰 수 있게 되었기 때문에 JavaScript는 최고의 인기 언어로 떠오르게 되었다.

## 그래서 JavaScript 는 무엇을 하는 언어인가요?

> 본래 브라우저를 제어하는 언어였으나 Node.js 라는 새로운 실행 환경의 등장으로 자체 어플리케이션을 만들 수 있게 된 언어.
프론트엔드와 백엔드 코드를 모두 작성할 수 있다.
> 

# JavaScript는 어떻게 쓰는 언어인가?

프로그래밍 언어가 설계 될 때 각자 지향하는 패러다임이 있다.
JavaScript는 명령형, 함수형, 객체지향 프로그래밍을 지향하는 멀티 패러다임 언어이다.

## 명령형 vs 선언형 프로그래밍

명령형 프로그래밍은 내가 *어떻게(how)* 할 것인지,
선언형 프로그래밍은 내가 _어떤(what) 일_을 할 것인지를 말한다.

예를 들어서 마트에 가서 당근을 1개 산다면
명령형 프로그래밍은
`두 블록을 지나 신호등을 건너 마트에 간다.`

`가장 안쪽으로 들어가 야채코너에서 당근을 찾는다.`

`입구쪽으로 돌아와 계산대에서 당근을 구매한다`

선언형 프로그래밍은
`마트에 간다.`

`당근을 구매한다.` 

라고 작성할 것이다.

명령형 프로그래밍은 절차적, 객채지향적 프로그래밍을 포함하는 개념이며
선언형 프로그래밍은 논리형, **함수형 프로그래밍**을 포함하는 개념이다.

그렇다면 명령형과 선언형(함수형) 패러다임을 모두 가지는 JavaScript는 대체 어떻게 써야할까?

### 예시

배열에서 'banana' 라는 문자열을 포함하는 요소들을 추출하는 코드를 작성한다고 생각해보자

```jsx
// 명령형 프로그래밍

const arr = ['yellow banana', 'red apple', 'black banana'];
function getBananas() {
  const bananas = [];
  for (let i = 0; i < arr.length; i++){
  	if (arr[i].includes('banana'))
      bananas.push(arr[i]);
  }
  return bananas;
}
getBananas(); // ["yellow banana", "black banana"]

```

```jsx
// 선언형 프로그래밍

const arr = ['yellow banana', 'red apple', 'black banana'];
const getBananas = array => array.filter(el => el.includes('banana'));
getBananas(arr); // ["yellow banana", "black banana"]

```

`명령형 프로그래밍` 으로 작성한 코드는 개발자들에게 친숙한 `if`, `for` 등의 예약어들을 사용한다. for 문을 사용하기 때문에 매 반복문마다 제어하기가 쉽다(ex: 반복문 중간에 중단). 또한 어떤 코드에서는 선언형 프로그래밍에서 사용하는 함수들보다 속도가 빠르기도 하다. ***하지만*** 코드의 길이가 길어져서 가독성이 떨어진다. for 문에서 변수를 초기화하고 조건을 명시적으로 작성해야하기 때문에 잘못된 코드 작성으로 버그가 발생할 가능성이 높아진다.

`선언형 프로그래밍` 으로 작성한 코드는 코드 길이가 짧아서 함수가 무엇을 하려는지 빠르게 파악할 수 있다.
***하지만*** 배열의 반복 중 섬세한 제어가 힘들고, 어떤 함수는 속도가 느리다는 단점이 있다.

JavaScript 코드를 명령형으로 작성하느냐, 선언형으로 작성하느냐는 개인의 취향이기도 하지만 대부분의 개발자들이 `선언형(함수형) 프로그래밍` 을 지향한다. `React` 의 상태관리 측면에서 함수형 프로그래밍이 확실한 이점을 가지기도 한다.

그렇다고해서 명령형으로 작성할 수 없으며 모든 코드를 함수형으로 작성해야하는 것은 아니다. 애초에 JavaScript는 함수형 프로그래밍만을 위해서 나온 것이 아니기 때문에 함수형만으로 작성하는 것은 불가능하다. JavaScript는 다양한 선택지를 제공하는 언어이며 멀티패러다임은 서로를 상호보완할 수 있다. 다만 C 와 Java 를 통해 익숙해졌던 명령형 프로그래밍의 틀에서 어느정도 벗어나야 한다는 점을 기억해야겠다.

## 함수형 프로그래밍

선언형 프로그래밍이 무엇인지는 알겠는데, 선언형 프로그래밍중 하나라는 함수형 프로그래밍은 도대체 무엇일까?

함수형 프로그래밍에 대해 알기위해서는 우선 JavaScript 의 함수는 일급객체라는 사실을 알아야한다.

### JavaScript 의 함수는 일급객체

다음과 같은 조건을 만족하는 객체를 일급 객체라고 한다.

> 무명의 리터럴로 생성할 수 있다. 즉 런타임에 생성이 가능하다.
> 

> 변수나 자료구조(객체, 배열 등)에 저장할 수 있다.
> 

> 함수의 매개변수에 전달할 수 있다.
> 

> 함수의 반환값으로 사용할 수 있다.
> 

자바스크립트의 함수는 위 조건을 모두 만족하므로 일급객체다.
함수가 일급객체라는 말은 함수를 객체와 동일하게 사용할 수 있다는 의미다.
객체는 값으로 평가되므로 함수도 값으로 취급할 수 있다.

일급객체로서 함수가 가지는 가장 큰 특징은 일반 객체와 같이 함수를
`함수의 매개변수에 전달할 수 있으며`, `함수의 반환값으로도 사용할 수 있다` 는 것이다.
이는 함수형 프로그래밍을 가능하게한다.

다시 함수형 프로그래밍으로 돌아와서,
함수형 프로그래밍의 필수요소는 다음과 같다.

> 순수함수를 사용하여 side-effect를 지양할 것
> 

> 데이터의 불변성을 유지할 것
> 

> 순수함수를 조합하여 선언적 패턴을 가질 것
> 

### 순수함수 vs side-effect

`side-effect(부수효과)` 란 함수 외부의 데이터를 변경하는 것을 말한다.
`순수함수` 는 `side-effect` 가 없으며, 인풋에 대한 아웃풋이 항상 같은 함수를 말한다.

```jsx
let number = 0;

function pureFunc(num, offset) {
  return num + offset;
}

function sideEffect(offset) {
  number += offset;
  return number;
}

console.log(pureFunc(number, 1)); // 1
console.log(number); // 0 : pureFunc로 인해 값이 달라지지는 않음
console.log(sideEffect(1)); // 1
console.log(sideEffect(1)); // 2: 바로 위 sideEffect()와 input 이 같으나 결과는 다름
console.log(number); // 2 : sideEffect 함수가 외부의 변수를 변경함

```

위 코드에서 pureFunc 함수는 인자 num 가 같으면 항상 같은 값을 반환하며, 외부의 상태를 변경하지 않는 함수이다.
하지만 sideEffect 함수는 외부 상태에 의존하기 때문에 같은 인자에 대해 다른 값을 반환할 수 있다. 뿐만 아니라 외부의 상태를 함수 내부에서 바꾸게 된다. 이런 `side-effect` 는 상태변경을 추적하기 어렵게 만들어서 가독성을 해친다. 코드를 자세히 읽어야 상태변경이 일어나는지 확인할 수 있기 때문이다.

하지만 프론트엔드에서 필수적인 작업인 DOM 조작, 서버와의 통신도 `side-effect` 에 해당한다.
따라서 오로지 순수함수만 사용하는 것은 불가능하다.
최대한 순수함수 위주로 작성하여 의도치 않은 상태변경을 막고 코드를 간결하게 만드는 것이 JavaScript 의 함수형 프로그래밍이다. 논리를 간단하게 만들어야 버그가 스며들지 못한다.

### 데이터의 불변성

JavaScript 에서 함수에 인수를 전달할 때 원시값(number, string, boolean 등) 은 passed by value 로 전달되지만 객체는 passed by "copy of reference" 방식으로 전달된다. 객체를 함수에 전달하면 함수에서 객체의 내부를 바꿀 수 있다는 뜻이다.

```jsx
const obj = {name : "sujeong", id: "sj_dev_js"};
const addNickname = (o) => {
  o.nickname = "sujpark";
}
addNickname(obj);
console.log(obj); // {name: "sujeong", id: "sj_dev_js", nickname: "sujpark"}

```

위에서 말한 바와 같이 순수함수는 외부의 상태(`obj`)를 변경해서는 안된다.
위 코드를 순수함수로 작성하면 다음과 같다.

```jsx
const obj = {name : "sujeong", id: "sj_dev_js"};
const addNicknamePure = (o) => {
  return {...o, nickname: "sujpark"}; // 새로운 객체 생성
}
const newObj = addNicknamePure(obj);
console.log(obj); // {name: "sujeong", id: "sj_dev_js"}
console.log(newObj); // {name: "sujeong", id: "sj_dev_js", nickname: "sujpark"}

```

JavaScript 에서는 배열 또한 객체이기 때문에 같은 방식을 따른다.
JavaScript 는 함수형 프로그래밍을 위한 고차함수들 (`map`, `filter`, `slice` 등) 을 제공하기 때문에 적절히 사용하면 된다.

```jsx
const arr = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9];
const mappedArr = arr.map(el => el + 1);
const filteredArr = arr.filter(el => el % 2 === 0)
const slicedArr = arr.slice(0, 2);

console.log(mappedArr); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
console.log(filteredArr); // [0, 2, 4, 6, 8]
console.log(slicedArr); // [0, 1]
console.log(arr); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
// 원본함수 arr는 변하지 않는다.

```

### 선언적 패턴을 통한 관심사 분리

버튼을 클릭하면 화면의 숫자가 증가하는 코드를 `React` 로 작성한다고 생각해보자

```jsx
function App() {
  const [count, setCount] = useState(0);
  const onClick = () => setCount(count + 1);

  return <div>
    <div>{count}</div>
    <button onClick={onClick}>+</button>
  <div>
}

```

이 코드는 겉보기에 별 문제가 없어보인다.
하지만 화면의 숫자가 감소하는 버튼을 넣는다면 어떨까?

```jsx
function App() {
  const [count, setCount] = useState(0);
  const onIncreaseClick = () => setCount(count + 1);
  const onDecreaseClick = () => setCount(count - 1);

  return <div>
    <div>{count}</div>
    <button onClick={onIncreaseClick}>+</button>
    <button onClick={onDecreaseClick}>-</button>
  <div>
}

```

`count + 1` 과 `count - 1` 만 다를 뿐인데 외부의 상태 `count`를 변경하는 함수를 두개나 만들어버렸다. 이는 함수의 관심사를 확실하게 분리하지 않았기 때문이다. "계산" 기능을 click 함수들로부터 분리해보자.

```jsx
function App() {
  const [count, setCount] = useState(0);
  const increase = (value) => value + 1;
  const decrease = (value) => value - 1;
  const onClick = (calculate) => setCount(calculate(count));

  return <div>
    <div>{count}</div>
    <button onClick={() => onClick(increase)}>+</button>
    <button onClick={() => onClick(decrease)}>-</button>
  <div>
}

```

함수의 관심사를 `계산` 과 `상태관리` 를 기준으로 나누었다. JavaScript 에서 함수는 일급객체이기 때문에 다른 함수의 매개변수에 전달할 수 있다. 이제 onClick 은 상태를 변경하는 일을 맡고, increase 와 decrease 는 onClick의 매개변수에 전달되어 계산을 담당한다. 계산 과정이 바뀔때 상태관리 함수를 다시 작성할 필요없이 계산 과정만 바꾸어주면된다.

우리가 분리해낸 함수를 살펴보면 `순수함수` 라는 것을 알 수 있다. 그리고 상태 `count`를 변경하는 onClick 함수는 여전히 `side-effect` 를 가지고 있다. 위 코드와 같이 순수함수를 `side-effect` 로부터 최대한 분리해 계층화 하면 `선언적 패턴을 통한 관심사 분리` 가 된다. 우리는 이 패턴을 통해 함수를 간략하게 만들어 가독성을 높이고, 관심사를 분리하여 재사용성을 높일 뿐 아니라 의도치 않은 상태변경을 방지하여 버그를 최소화할 수 있다.

함수형 프로그래밍에 대해 더 자세히 알고 싶다면 아래 글을 적극 추천한다.
*[다시 쓰는 함수형 프로그래밍](https://velog.io/@teo/functional-programming#%ED%95%A8%EC%88%98%ED%98%95-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%EC%9A%A9%EC%96%B4-%EB%8B%A4%EC%8B%9C%EC%93%B0%EA%B8%B0)*

## 프로토타입기반 객체지향 프로그래밍

많은 개발자들이 Java의 Class기반 객체지향에 익숙할 것이라고 생각한다. 하지만 JavaScript 는 **Java 의 Class 와는 다른** 프로토타입 기반의 객체지향 프로그래밍을 지원한다. 많은 과정을 거쳐서 JavaScript 또한 Java의 Class 와 비슷한 모양새를 갖추게 되었지만 그 안에 든 객체지향에 대한 철학은 결코 같지 않다.

Java 에서 class 를 통해 생성된 instance 는 class 라는 설계도면을 따라서 만든 것 같은 형태를 하고 있다.
class Bird 에 fly 라는 메소드가 있다면 class Bird를 통해 생성된 인스턴스는 모두 fly라는 메소드를 가지고 있다.

JavaScript 에서 prototype 을 통해 생성된 instance 는 instance 가 내부에 메소드를 가질 수도 있지만 그보다 prototype 에 메소드를 두고 instance 와 연결해서 사용하는 방식을 사용한다.

```jsx
function Bird() {
  this.eat = function () {
    console.log('eat');
  }
}

Bird.prototype.fly = function () {
  console.log('fly!');
}

const bird = new Bird();
console.log(bird); // Bird {eat: ƒ}
// intance 내부에는 eat 함수밖에 없다.
bird.eat(); // eat
bird.fly(); // fly!
// prototype 에만 선언된 fly 함수를 instance에서 사용할 수 있다.

Bird.prototype.jump = function () {
  console.log('jump');
}

bird.jump(); // jump
// instance가 생성된 후에도 prototype 에 추가된 함수를 사용할 수 있다.

```

이런 방식을 통해 메모리에서 인스턴스가 차지하는 공간을 줄일 수 있다.
뿐만 아니라 prototype 이 또다른 prototype 을 참조하는 프로토타입 체인을 통해서
상위 prototype 의 메소드를 사용할 수 있다.
