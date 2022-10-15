## ES5와 ES6의 차이 이해 및 과거 버전에 이해 필요성
ES5와 ES6의 차이를 알고 있는가, 과거 버전은 왜 알아야 하는가?

ES5와 ES6의 차이 이해 및 과거 버전에 이해 필요성 납득

ES6+를 요구하고 있지만 과거 버전을 알아야 하는 이유? 기능 추가 !


# ES6 업그레이드 기능

ECMA스크립트(ECMAScript, 또는 ES)란, Ecma International이 ECMA-262 기술 규격에 따라 정의하고 있는 표준화된 스크립트 프로그래밍 언어를 말한다. 자바스크립트를 표준화하기 위해 만들어졌다.

## 1. let, const, block level scope

ES6 이전 var 키워드는 함수 레벨 스코프를 가지며 암묵적 재할당이 가능했다.

단점을 보완하기 위해 블록 레벨 스코프를 가지며 재할당이 가능한 let, const 키워드가 추가되었다.

변수에 생명주기에 영향을 끼치며, 키워드만 보아도 변수의 변화가 생기는지 안 생기는지 알 수 있게 되었다.

### 1.1 Block-level scope

```jsx
var foo = 123; // 전역 변수

console.log(foo); // 123

{
  var foo = 456; // 전역 변수
}

console.log(foo); // 456
```

var의 경우, 함수 내부에서는 지역변수로 사용되나, block 은 적용되지 않는다.

이 것을 막기 위해 let 키워드가 만들어짐.

### 1.2 호이스팅

```jsx
console.log(foo); // undefined
var foo;

console.log(bar); // Error: Uncaught ReferenceError: bar is not defined
let bar;
```

### 2. const

- 선언과 동시에 초기화. (cosnt ex;  ex = 100 이런 형태로 사용 불가)
- block level scope
- 상수
- const 로 만들어진 객체 (property (key) 삭제, 추가, 변경 가능)

```jsx
const user = { name: 'Lee' };

// const 변수는 재할당이 금지된다.
// user = {}; // TypeError: Assignment to constant variable.

// 객체의 내용은 변경할 수 있다.
user.name = 'Kim';

console.log(user); // { name: 'Kim' }
```


[let, const | PoiemaWeb](https://poiemaweb.com/es6-block-scope)

## 2. 템플릿 리터럴(Template literal)

### 백틱(backtick) 문자 ` 를 사용한다.

```jsx
const first = 'JiHee';
const last = 'Han';

// ES5: 문자열 연결
console.log('My name is ' + first + ' ' + last + '.');

// ES6: String Interpolation
console.log(`My name is ${first} ${last}.     
이렇게
여러줄도 가능해졌다 !
`);

console.log(`1 + 1 = ${1 + 1}`); // "1 + 1 = 2"
```



[Template Literals | PoiemaWeb](https://poiemaweb.com/es6-template-literals)

## 3. 화살표 함수

화살표 함수가 추가되어 함수를 간결하게 나타낼 수 있다. 가독성 및 유지 보수성이 올라갔다고 판단된다. 

화살표 함수에선, 매개변수가 하나일 때 () 괄호 생략 가능, {} 소괄호 및 return도 생략 가능하다.

익명함수이다.

```jsx
// 매개변수 지정 방법
    () => { ... } // 매개변수가 없을 경우
     x => { ... } // 매개변수가 한 개인 경우, 소괄호를 생략할 수 있다.
(x, y) => { ... } // 매개변수가 여러 개인 경우, 소괄호를 생략할 수 없다.

// 함수 몸체 지정 방법
x => { return x * x }  // single line block
x => x * x             // 함수 몸체가 한줄의 구문이라면 중괄호를 생략할 수 있으며 암묵적으로 return된다. 위 표현과 동일하다.

// 익명 함수
() => { return { a: 1 }; }
() => ({ a: 1 })  // 위 표현과 동일하다. 객체 반환시 소괄호를 사용한다.

() => {           // multi line block.
  const x = 10;
  return x * x;
};

function sum
(a,b) { return 
```

사용하는 이유는 간결해서 주로 사용한다. (function 선언이 필요 없음)

### 3.1 **렉시컬 스코프**

렉시컬 스코프는 함수를 어디서 호출하는지가 아니라 어디에 선언하였는지에 따라 결정된다.

- new 연산자로 생성된 함수는 새로 빈객체를 만들어서 this에 할당한다.
- 일반함수를 쓰면, 생기는 문제 때문에 생선된 this 객체를 가리키게 하기 위해서 다른 방법을 쓸 수 있다.
    - bind(), this = this, return 시 this 포함

[Arrow function | PoiemaWeb](https://poiemaweb.com/es6-arrow-function#31-%EC%9D%BC%EB%B0%98-%ED%95%A8%EC%88%98%EC%9D%98-this)

### 3.2 **화살표 함수의 this 는 항상 상위 스코프 this(객체)를 가리킨다.**

```jsx
function Prefixer(prefix) {
  this.prefix = prefix;
}

Prefixer.prototype.prefixArray = function (arr) {
  // this는 상위 스코프인 prefixArray 메소드 내의 this를 가리킨다.
  return arr.map(x => `${this.prefix}  ${x}`);
};

const pre = new Prefixer('Hi');
console.log(pre.prefixArray(['Lee', 'Kim']));
```

### 3.3 화살표 함수를 사용하면 안되는 경우

화살표 함수는 항상 빈 객체를 만들어서 this에 할당하기 때문에, 새 객체가 만들어지면 안되는 상황에서는 사용하지 않는다.

- 메소드
    - 메소드에서는 일반함수를 사용한다.
- prototype
    
    ```jsx
    const person = {
      name: 'Lee',
    };
    
    const person1 = {
      name: 'Han',
    };
    
    Object.prototype.sayHi = function() {
      console.log(`Hi ${this.name}`);
    };
    
    person.sayHi(); // Hi Lee
    person1.sayHi(); // Hi Lee
    ```
    
- new
    - function으로 함수를 생성해야 prototype property를 가지게 된다.
- addEventListener


[Arrow function | PoiemaWeb](https://poiemaweb.com/es6-arrow-function#4-%ED%99%94%EC%82%B4%ED%91%9C-%ED%95%A8%EC%88%98%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%B4%EC%84%9C%EB%8A%94-%EC%95%88%EB%90%98%EB%8A%94-%EA%B2%BD%EC%9A%B0)

## 4. 모듈 (Module)

모듈이란 애플리케이션을 구성하는 개별적 요소로서 재사용 가능한 코드 조각을 말한다. 모듈은 세부 사항을 캡슐화하고 공개가 필요한 API만을 외부에 노출한다.

모듈기능과 함께 모듈 스코프 기능이 ES6에서 추가됨.

<aside>
💡 일반적으로 모듈은 파일 단위로 분리 되어 있으며
애플리케이션은 필요에 따라 명시적으로 모듈을 로드하여 재사용한다.

</aside>

### 4.1 export

```jsx
// lib.mjs
// 변수의 공개
export const pi = Math.PI;

// 함수의 공개
export function square(x) {
  return x * x;
}

// 클래스의 공개
export class Person {
  constructor(name) {
    this.name = name;
  }
}
```

```jsx
// lib.mjs
const pi = Math.PI;

function square(x) {
  return x * x;
}

class Person {
  constructor(name) {
    this.name = name;
  }
}

// 변수, 함수 클래스를 하나의 객체로 구성하여 공개
export { pi, square, Person };
```

### 4.2 import

```jsx
// app.mjs
// 같은 폴더 내의 lib.mjs 모듈을 로드.
// lib.mjs 모듈이 export한 식별자로 import한다.
// ES6 모듈의 파일 확장자를 생략할 수 없다.
import { pi, square, Person } from './lib.mjs';

console.log(pi);         // 3.141592653589793
console.log(square(10)); // 100
console.log(new Person('Leah')); // Person { name: 'Leah' }
```

```jsx
// app.mjs
import * as lib from './lib.mjs';

console.log(lib.pi);         // 3.141592653589793
console.log(lib.square(10)); // 100
console.log(new lib.Person('Leah')); // Person { name: 'Leah' }
```

```jsx
// app.mjs
import { pi as PI, square as sq, Person as P } from './lib.mjs';

console.log(PI);    // 3.141592653589793
console.log(sq(2)); // 4
console.log(new P('Kim')); // Person { name: 'Kim' }
```

```jsx
// 모듈이 1개인 경우
// lib.mjs
export default function (x) {
  return x * x;
}
```


[Module | PoiemaWeb](https://poiemaweb.com/es6-module)
