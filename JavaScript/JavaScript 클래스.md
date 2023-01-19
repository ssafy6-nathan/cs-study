[블로그](https://jay-zone.tistory.com/entry/JavaScript-9-%ED%81%B4%EB%9E%98%EC%8A%A4-%EC%B5%9C%EC%8B%A0-%EB%AC%B8%EB%B2%95)

JavaScript | 9. 클래스, 최신 문법


9.1 클래스 소개
공통적인 구조를 가진 객체가 있다면 생성자 함수를 이용해 손쉽게 객체를 만들 수 있었지?

객체 만들 때 생성자 함수를 통해 만들고자하는 객체의 양식을 생성자 함수를 통해 정의해두고

함수 호출해 쓰듯이 필요한 데이터만 인자로 전달해 손쉽게 객체를 만들어냈다.

생성자 함수 : 붕어빵 틀 (템플릿)
객체 : 이 템플릿을 통해 데이터를 넣어 만든 붕어빵
이렇게 자바스크립트에선 생성자 함수를 통해 만들고자하는 객체를 쉽게 만들 수 있다.

HOW?

프로토타입 Prototype 을 베이스로 해서 객체지향 프로그래밍을 지원해주기 때문이다.

<aside> 💡 중요한 건,

(JS 내부 구현 사항은) 프로토타입을 이용해 내부 생성자 함수를 지원하는 것.

결국 우리가 개발자 입장에서 객체를 손쉽게 만들기 위해서는 생성자 함수를 이용해야 한다.

</aside>

그런데, 대부분의 객체지향 프로그래밍 언어에선 프로토타입을 사용하는 게 아니라 클래스를 기반으로 한 객체지향 프로그래밍을 가능하게 한다.

예전 자바스크립트 언어에서는 프로토 타입을 이용해서 이렇게 비슷한 구조를 가진 객체들을 찍어낼 수 있었다면,

이젠 ES6에 도입이 된, 이 Class라는 걸 이용해서 조금 더 손쉽게 객체를 만들 수 있다.

(자바, C#, swift, kotlin은 객체지향 프로그래밍 언어들이 클래스를 이용해 객체를 손쉽게 만들 수 있다.)

<aside> 💡 JS는 내부적으로는 프로토타입을 쓰고, 우리 개발자들은 그냥 클래스를 이용해도 자바스크립트 내부적으로 프로토타입을 이용하기 때문에, 프로토타입을 사알짝 감싸고 있는, 문법적으로 개발자들이 조금 더 편리하게 쓸 수 있도록 클래스인 것 처럼 보이게 더해진 이것을 신택스(신테틱?) 추가..? 신테틱 슈가라고 하는데. 문법적 설탕 ㅋㅋ 이라고 할 수 있다. —-

신테틱 슈가

프로토타입과 클래스와의 관계

class는 기존에 있었던 문법에 신테틱슈거(Syntactic Sugar)(보기좋은 코드)라고 보면 된다. 추가적으로 주는 기능이나 기존에 제공하던 성질을 바꾸지 않고 단순히 문법만 바뀐 것이다.

 

</aside>

<aside> 💡 JS는 원래는 프로토타입을 베이스로 하지만, ES6, 즉 최신 JS에서부터 우리가 클래스를 기반으로 객체지향 프로그래밍을 해나갈 수 있다! 그리고 프로토타입을 기반으로 한 객체지향 프로그래밍 언어는 흔하지 않기 때문에 요즘 모던한, 개발 세상에서는 객체지향 프로그래밍 언어라고 하면, 클래스를 기반으로 해서 많이 다룬다. (나중에 Type이 더해진, TS에서도 클래스 기반으로 프로그래밍 해나간다.)

</aside>

그래서, 객체지향 프로그래밍의 기본인 ‘클래스’에 대해서 자세하게 다뤄볼 것이다!

 

<aside> 💡 클래스?

생성자 함수와 마찬가지로 객체를 생성할 수 있는 템플릿(청사진, 틀, 양식)

클래스나 생성자 함수를 이용해 객체지향 프로그래밍을 할 수 있다.

</aside>

객체지향 프로그래밍

프로그래밍 시 그냥 절차적으로 코드를 작성하는 게 아니라

서로 밀접하게 연관있는 아이들을 객체로 구성해나가면서 객체끼리 서로 호환 가능하도록 할 수 있는 것

<aside> 💡 JS에서는 생성자 함수, 클래스를 이용해 객체를 생성할 수 있지만, 요즘은 모던한 클래스!를 이용해 객체를 생성한다. (현업에서는 이제 거의 대부분 생성자 함수는 쓰지 않는다.) 그리고 클래스가 프로토타입보다 사용하기 간편하다.

</aside>

용어 짚고 가기

생성자 함수와 동일하게 클래스를 이용해 객체를 만들 수 있는데,

이 때 클래스를 이용해 만들어진 객체를 인스턴스 라고 한다.

<aside> 💡 클래스를 통해 객체를 만들 수 있고, 이렇게 만들어진 객체를 이 클래스의 인스턴스 라고 한다.

</aside>

9.2 클래스의 기본
https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Statements/class

javascript/5.object/5.create.js의 생성자 함수 function Fruit을 클래스로 변환하자.
// 생성자 함수 function Fruit(name, emoji) { this.name = name; this.emiji = emoji; this.display = () => { console.log(`${this.name}: ${this.emoji}`); }; // return this; // 생략 가능 }
Copy
// 객체를 손쉽게 만들 수 있는 템플릿
// 1. 생성자 함수 (오래된 고전적인 방법) : 더이상 쓰이지 않는다.
// 2. 클래스 ✨

// 생성자 함수는 function이라는 키워드로 시작하지만 클래스는 다르다!
// 클래스 class
class Fruit {
  // Fruit이라는 템플릿은~ {} 중괄호로 묶어야하고

  // Fruit 클래스를 이용해 만드는 객체에 필요한 데이터를 채워줄 수 있는 constructor(생성자) 지정!
  // 생성자: new 키워드로 객체를 생성할 때 호출되는 함수
  constructor(name, emoji) { // 모든 class는 이렇게 기본적인 생성자가 들어있어야 한다. 
	**// 생성자 안에서 객체를 만드는 데에 필요한 데이터들을 채워줘야한다.**
    this.name = name;
    this.emoji = emoji;
  **// 클래스에 필요한 멤버함수는 보통 생성자 밖에 함수를 정의한다. (this 붙일 필요 없음!)**
	// this.display = () => {
	//	console.log(`${this.name}: ${this.emoji}`);
	//	};
  }

  **// 클래스에 필요한 '멤버함수'는 보통 생성자 밖에 함수를 정의한다. (this 붙일 필요 없음!)**
  // 이 때, function 이라는 키워드 붙이면 오류 발생!
		//function 키워드 제외하고 이렇게 함수 선언(?)식으로 작성해도 되고
		// display () {
		//	console.log(`${this.name}: ${this.emoji}`);
		//	};
		// 이렇게 arrow 함수로 멤버함수를 작성해도 된다.
  display = () => {
    console.log(`${this.name}: ${this.emoji}`);
  };
}

// apple은 Fruit 클래스의 인스턴스이다.
const apple = new Fruit('apple', '🍎');
// orange은 Fruit 클래스의 인스턴스이다.
const orange = new Fruit('orange', '🍊');

console.log(apple);
console.log(orange);
console.log(apple.name);
console.log(apple.emoji);
apple.display();

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다.
const obj = { name: 'ellie' };
<aside> 💡 정리해보자면, 클래스를 정의할 때 클래스라는 키워드를 통해 만들 수 있고 각각의 클래스는 생성자 함수가 있다. new 키워드로 객체를 생성할 때 이 생성자가 호출이 된다. 생성자에는 우리가 객체를 만드는 데에 필요한 데이터를 인자로 (외부로부터) 받아올 수 있도록 생성자를 이용한다. 이 안에 이 객체의 네임을 this.name은 name, this.emoji의 emoji는 이거! 라고 링크해주면 된다! 그리고 객체 안에서 사용하는 멤버 함수가 있다면 그냥 이렇게 이름만 작성해서 일반 함수나 애로우 펑션으로 작성하면 된다.

</aside>

끝내기 전 TIP!
<aside> 💡

여기서 apple이라는 객체는

// apple은 Fruit 클래스의 인스턴스이다.

Fruit이라는 클래스를 통해 만들어진 객체이기 때문에!

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다. const obj = { name: 'ellie' };

</aside>

Copy
// apple은 Fruit 클래스의 인스턴스이다.
const apple = new Fruit('apple', '🍎');
// orange은 Fruit 클래스의 인스턴스이다.
const orange = new Fruit('orange', '🍊');

console.log(apple);
console.log(orange);
console.log(apple.name);
console.log(apple.emoji);
apple.display();

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다.
const obj = { name: 'ellie' };

인스턴스 vs 객체

객체는 그냥 만들어진 객체이고

어떤 클래스를 통해 만들어진 객체라면 인스턴스라고 부른다

9.3 재사용성을 높이는 방법
클래스 : 비슷한 부류의, 비슷한 카테고리의 동일한 속성과 행동을 가지고 있는 객체들을 생성할 수 있는 템플릿을 만드는 클래스!

클래스에 정의된 프로퍼티와 메소드는 사실, 인스턴스 레벨의 프로퍼티와 메소드라고 볼 수 있다.

뭔 말이여~? 무슨 말이나면~

클래스를 이용해 만든 인스턴스에 클래스들에서 정의한 프로퍼티와 함수들이 중복적으로 만들어지기 때문이다.

우리가 템플릿을 만들어두고 필요한 데이터를 주입해서 동일한 것을 지속적으로 만들어나갈 수 있죠/

= 클래스를 이용해서 인스턴스를 찍어낼 수 있다.

클래스를 통해 인스턴스를 찍어낼 수 있었다. 이 때 동일한 프로퍼티와 메소드가 들어가기 때문에 이것들을 전부 다 인스턴스 레벨의 프로퍼티와 메소드다!라고 볼 수 있다

그래서 만들어진 각각의 인스턴스(객체)를 통해서 객체.속성, 객체.함수! 이렇게 호출할 수 있었지?

그런데 여기서, 지금은 각각의 객체마다 서로 다른 데이터를 가지고 있어서 인스턴스 레벨의 프로퍼티를 만드는 것은 맞지만 우리가 모든 객체마다 동일하게 참조해야하는 속성이나 행동이 있다면? 어떻게 해야할까?

⇒ 클래스 레벨의 프로퍼티와 메소드를 사용한다!

생성자 함수와 마찬가지로 객체를 생성할 수 있는 템플릿(청사진, 틀, 양식)

클래스나 생성자 함수를 이용해 객체지향 프로그래밍을 할 수 있다.

</aside>

객체지향 프로그래밍

프로그래밍 시 그냥 절차적으로 코드를 작성하는 게 아니라

서로 밀접하게 연관있는 아이들을 객체로 구성해나가면서 객체끼리 서로 호환 가능하도록 할 수 있는 것

<aside> 💡 JS에서는 생성자 함수, 클래스를 이용해 객체를 생성할 수 있지만, 요즘은 모던한 클래스!를 이용해 객체를 생성한다. (현업에서는 이제 거의 대부분 생성자 함수는 쓰지 않는다.) 그리고 클래스가 프로토타입보다 사용하기 간편하다.

</aside>

용어 짚고 가기

생성자 함수와 동일하게 클래스를 이용해 객체를 만들 수 있는데,

이 때 클래스를 이용해 만들어진 객체를 인스턴스 라고 한다.

<aside> 💡 클래스를 통해 객체를 만들 수 있고, 이렇게 만들어진 객체를 이 클래스의 인스턴스 라고 한다.

</aside>

9.2 클래스의 기본
https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Statements/class

javascript/5.object/5.create.js의 생성자 함수 function Fruit을 클래스로 변환하자.
// 생성자 함수 function Fruit(name, emoji) { this.name = name; this.emiji = emoji; this.display = () => { console.log(`${this.name}: ${this.emoji}`); }; // return this; // 생략 가능 }
Copy
// 객체를 손쉽게 만들 수 있는 템플릿
// 1. 생성자 함수 (오래된 고전적인 방법) : 더이상 쓰이지 않는다.
// 2. 클래스 ✨

// 생성자 함수는 function이라는 키워드로 시작하지만 클래스는 다르다!
// 클래스 class
class Fruit {
  // Fruit이라는 템플릿은~ {} 중괄호로 묶어야하고

  // Fruit 클래스를 이용해 만드는 객체에 필요한 데이터를 채워줄 수 있는 constructor(생성자) 지정!
  // 생성자: new 키워드로 객체를 생성할 때 호출되는 함수
  constructor(name, emoji) { // 모든 class는 이렇게 기본적인 생성자가 들어있어야 한다. 
	**// 생성자 안에서 객체를 만드는 데에 필요한 데이터들을 채워줘야한다.**
    this.name = name;
    this.emoji = emoji;
  **// 클래스에 필요한 멤버함수는 보통 생성자 밖에 함수를 정의한다. (this 붙일 필요 없음!)**
	// this.display = () => {
	//	console.log(`${this.name}: ${this.emoji}`);
	//	};
  }

  **// 클래스에 필요한 '멤버함수'는 보통 생성자 밖에 함수를 정의한다. (this 붙일 필요 없음!)**
  // 이 때, function 이라는 키워드 붙이면 오류 발생!
		//function 키워드 제외하고 이렇게 함수 선언(?)식으로 작성해도 되고
		// display () {
		//	console.log(`${this.name}: ${this.emoji}`);
		//	};
		// 이렇게 arrow 함수로 멤버함수를 작성해도 된다.
  display = () => {
    console.log(`${this.name}: ${this.emoji}`);
  };
}

// apple은 Fruit 클래스의 인스턴스이다.
const apple = new Fruit('apple', '🍎');
// orange은 Fruit 클래스의 인스턴스이다.
const orange = new Fruit('orange', '🍊');

console.log(apple);
console.log(orange);
console.log(apple.name);
console.log(apple.emoji);
apple.display();

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다.
const obj = { name: 'ellie' };
<aside> 💡 정리해보자면, 클래스를 정의할 때 클래스라는 키워드를 통해 만들 수 있고 각각의 클래스는 생성자 함수가 있다. new 키워드로 객체를 생성할 때 이 생성자가 호출이 된다. 생성자에는 우리가 객체를 만드는 데에 필요한 데이터를 인자로 (외부로부터) 받아올 수 있도록 생성자를 이용한다. 이 안에 이 객체의 네임을 this.name은 name, this.emoji의 emoji는 이거! 라고 링크해주면 된다! 그리고 객체 안에서 사용하는 멤버 함수가 있다면 그냥 이렇게 이름만 작성해서 일반 함수나 애로우 펑션으로 작성하면 된다.

</aside>

끝내기 전 TIP!
<aside> 💡

여기서 apple이라는 객체는

// apple은 Fruit 클래스의 인스턴스이다.

Fruit이라는 클래스를 통해 만들어진 객체이기 때문에!

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다. const obj = { name: 'ellie' };

</aside>

Copy
// apple은 Fruit 클래스의 인스턴스이다.
const apple = new Fruit('apple', '🍎');
// orange은 Fruit 클래스의 인스턴스이다.
const orange = new Fruit('orange', '🍊');

console.log(apple);
console.log(orange);
console.log(apple.name);
console.log(apple.emoji);
apple.display();

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다.
const obj = { name: 'ellie' };

인스턴스 vs 객체

객체는 그냥 만들어진 객체이고

어떤 클래스를 통해 만들어진 객체라면 인스턴스라고 부른다

9.3 재사용성을 높이는 방법
클래스 : 비슷한 부류의, 비슷한 카테고리의 동일한 속성과 행동을 가지고 있는 객체들을 생성할 수 있는 템플릿을 만드는 클래스!

클래스에 정의된 프로퍼티와 메소드는 사실, 인스턴스 레벨의 프로퍼티와 메소드라고 볼 수 있다.

뭔 말이여~? 무슨 말이나면~

클래스를 이용해 만든 인스턴스에 클래스들에서 정의한 프로퍼티와 함수들이 중복적으로 만들어지기 때문이다.

우리가 템플릿을 만들어두고 필요한 데이터를 주입해서 동일한 것을 지속적으로 만들어나갈 수 있죠/

= 클래스를 이용해서 인스턴스를 찍어낼 수 있다.

클래스를 통해 인스턴스를 찍어낼 수 있었다. 이 때 동일한 프로퍼티와 메소드가 들어가기 때문에 이것들을 전부 다 인스턴스 레벨의 프로퍼티와 메소드다!라고 볼 수 있다

그래서 만들어진 각각의 인스턴스(객체)를 통해서 객체.속성, 객체.함수! 이렇게 호출할 수 있었지?

그런데 여기서, 지금은 각각의 객체마다 서로 다른 데이터를 가지고 있어서 인스턴스 레벨의 프로퍼티를 만드는 것은 맞지만 우리가 모든 객체마다 동일하게 참조해야하는 속성이나 행동이 있다면? 어떻게 해야할까?

⇒ 클래스 레벨의 프로퍼티와 메소드를 사용한다!

생성자 함수와 마찬가지로 객체를 생성할 수 있는 템플릿(청사진, 틀, 양식)

클래스나 생성자 함수를 이용해 객체지향 프로그래밍을 할 수 있다.

</aside>

객체지향 프로그래밍

프로그래밍 시 그냥 절차적으로 코드를 작성하는 게 아니라

서로 밀접하게 연관있는 아이들을 객체로 구성해나가면서 객체끼리 서로 호환 가능하도록 할 수 있는 것

<aside> 💡 JS에서는 생성자 함수, 클래스를 이용해 객체를 생성할 수 있지만, 요즘은 모던한 클래스!를 이용해 객체를 생성한다. (현업에서는 이제 거의 대부분 생성자 함수는 쓰지 않는다.) 그리고 클래스가 프로토타입보다 사용하기 간편하다.

</aside>

용어 짚고 가기

생성자 함수와 동일하게 클래스를 이용해 객체를 만들 수 있는데,

이 때 클래스를 이용해 만들어진 객체를 인스턴스 라고 한다.

<aside> 💡 클래스를 통해 객체를 만들 수 있고, 이렇게 만들어진 객체를 이 클래스의 인스턴스 라고 한다.

</aside>

9.2 클래스의 기본
https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Statements/class

javascript/5.object/5.create.js의 생성자 함수 function Fruit을 클래스로 변환하자.
// 생성자 함수 function Fruit(name, emoji) { this.name = name; this.emiji = emoji; this.display = () => { console.log(`${this.name}: ${this.emoji}`); }; // return this; // 생략 가능 }
Copy
// 객체를 손쉽게 만들 수 있는 템플릿
// 1. 생성자 함수 (오래된 고전적인 방법) : 더이상 쓰이지 않는다.
// 2. 클래스 ✨

// 생성자 함수는 function이라는 키워드로 시작하지만 클래스는 다르다!
// 클래스 class
class Fruit {
  // Fruit이라는 템플릿은~ {} 중괄호로 묶어야하고

  // Fruit 클래스를 이용해 만드는 객체에 필요한 데이터를 채워줄 수 있는 constructor(생성자) 지정!
  // 생성자: new 키워드로 객체를 생성할 때 호출되는 함수
  constructor(name, emoji) { // 모든 class는 이렇게 기본적인 생성자가 들어있어야 한다. 
	**// 생성자 안에서 객체를 만드는 데에 필요한 데이터들을 채워줘야한다.**
    this.name = name;
    this.emoji = emoji;
  **// 클래스에 필요한 멤버함수는 보통 생성자 밖에 함수를 정의한다. (this 붙일 필요 없음!)**
	// this.display = () => {
	//	console.log(`${this.name}: ${this.emoji}`);
	//	};
  }

  **// 클래스에 필요한 '멤버함수'는 보통 생성자 밖에 함수를 정의한다. (this 붙일 필요 없음!)**
  // 이 때, function 이라는 키워드 붙이면 오류 발생!
		//function 키워드 제외하고 이렇게 함수 선언(?)식으로 작성해도 되고
		// display () {
		//	console.log(`${this.name}: ${this.emoji}`);
		//	};
		// 이렇게 arrow 함수로 멤버함수를 작성해도 된다.
  display = () => {
    console.log(`${this.name}: ${this.emoji}`);
  };
}

// apple은 Fruit 클래스의 인스턴스이다.
const apple = new Fruit('apple', '🍎');
// orange은 Fruit 클래스의 인스턴스이다.
const orange = new Fruit('orange', '🍊');

console.log(apple);
console.log(orange);
console.log(apple.name);
console.log(apple.emoji);
apple.display();

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다.
const obj = { name: 'ellie' };
<aside> 💡 정리해보자면, 클래스를 정의할 때 클래스라는 키워드를 통해 만들 수 있고 각각의 클래스는 생성자 함수가 있다. new 키워드로 객체를 생성할 때 이 생성자가 호출이 된다. 생성자에는 우리가 객체를 만드는 데에 필요한 데이터를 인자로 (외부로부터) 받아올 수 있도록 생성자를 이용한다. 이 안에 이 객체의 네임을 this.name은 name, this.emoji의 emoji는 이거! 라고 링크해주면 된다! 그리고 객체 안에서 사용하는 멤버 함수가 있다면 그냥 이렇게 이름만 작성해서 일반 함수나 애로우 펑션으로 작성하면 된다.

</aside>

끝내기 전 TIP!
<aside> 💡

여기서 apple이라는 객체는

// apple은 Fruit 클래스의 인스턴스이다.

Fruit이라는 클래스를 통해 만들어진 객체이기 때문에!

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다. const obj = { name: 'ellie' };

</aside>

Copy
// apple은 Fruit 클래스의 인스턴스이다.
const apple = new Fruit('apple', '🍎');
// orange은 Fruit 클래스의 인스턴스이다.
const orange = new Fruit('orange', '🍊');

console.log(apple);
console.log(orange);
console.log(apple.name);
console.log(apple.emoji);
apple.display();

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다.
const obj = { name: 'ellie' };

인스턴스 vs 객체

객체는 그냥 만들어진 객체이고

어떤 클래스를 통해 만들어진 객체라면 인스턴스라고 부른다

9.3 재사용성을 높이는 방법
클래스 : 비슷한 부류의, 비슷한 카테고리의 동일한 속성과 행동을 가지고 있는 객체들을 생성할 수 있는 템플릿을 만드는 클래스!

클래스에 정의된 프로퍼티와 메소드는 사실, 인스턴스 레벨의 프로퍼티와 메소드라고 볼 수 있다.

뭔 말이여~? 무슨 말이나면~

클래스를 이용해 만든 인스턴스에 클래스들에서 정의한 프로퍼티와 함수들이 중복적으로 만들어지기 때문이다.

우리가 템플릿을 만들어두고 필요한 데이터를 주입해서 동일한 것을 지속적으로 만들어나갈 수 있죠/

= 클래스를 이용해서 인스턴스를 찍어낼 수 있다.

클래스를 통해 인스턴스를 찍어낼 수 있었다. 이 때 동일한 프로퍼티와 메소드가 들어가기 때문에 이것들을 전부 다 인스턴스 레벨의 프로퍼티와 메소드다!라고 볼 수 있다

그래서 만들어진 각각의 인스턴스(객체)를 통해서 객체.속성, 객체.함수! 이렇게 호출할 수 있었지?

그런데 여기서, 지금은 각각의 객체마다 서로 다른 데이터를 가지고 있어서 인스턴스 레벨의 프로퍼티를 만드는 것은 맞지만 우리가 모든 객체마다 동일하게 참조해야하는 속성이나 행동이 있다면? 어떻게 해야할까?

⇒ 클래스 레벨의 프로퍼티와 메소드를 사용한다!

생성자 함수와 마찬가지로 객체를 생성할 수 있는 템플릿(청사진, 틀, 양식)

클래스나 생성자 함수를 이용해 객체지향 프로그래밍을 할 수 있다.

</aside>

객체지향 프로그래밍

프로그래밍 시 그냥 절차적으로 코드를 작성하는 게 아니라

서로 밀접하게 연관있는 아이들을 객체로 구성해나가면서 객체끼리 서로 호환 가능하도록 할 수 있는 것

<aside> 💡 JS에서는 생성자 함수, 클래스를 이용해 객체를 생성할 수 있지만, 요즘은 모던한 클래스!를 이용해 객체를 생성한다. (현업에서는 이제 거의 대부분 생성자 함수는 쓰지 않는다.) 그리고 클래스가 프로토타입보다 사용하기 간편하다.

</aside>

용어 짚고 가기

생성자 함수와 동일하게 클래스를 이용해 객체를 만들 수 있는데,

이 때 클래스를 이용해 만들어진 객체를 인스턴스 라고 한다.

<aside> 💡 클래스를 통해 객체를 만들 수 있고, 이렇게 만들어진 객체를 이 클래스의 인스턴스 라고 한다.

</aside>

9.2 클래스의 기본
https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Statements/class

javascript/5.object/5.create.js의 생성자 함수 function Fruit을 클래스로 변환하자.
// 생성자 함수 function Fruit(name, emoji) { this.name = name; this.emiji = emoji; this.display = () => { console.log(`${this.name}: ${this.emoji}`); }; // return this; // 생략 가능 }
Copy
// 객체를 손쉽게 만들 수 있는 템플릿
// 1. 생성자 함수 (오래된 고전적인 방법) : 더이상 쓰이지 않는다.
// 2. 클래스 ✨

// 생성자 함수는 function이라는 키워드로 시작하지만 클래스는 다르다!
// 클래스 class
class Fruit {
  // Fruit이라는 템플릿은~ {} 중괄호로 묶어야하고

  // Fruit 클래스를 이용해 만드는 객체에 필요한 데이터를 채워줄 수 있는 constructor(생성자) 지정!
  // 생성자: new 키워드로 객체를 생성할 때 호출되는 함수
  constructor(name, emoji) { // 모든 class는 이렇게 기본적인 생성자가 들어있어야 한다. 
	**// 생성자 안에서 객체를 만드는 데에 필요한 데이터들을 채워줘야한다.**
    this.name = name;
    this.emoji = emoji;
  **// 클래스에 필요한 멤버함수는 보통 생성자 밖에 함수를 정의한다. (this 붙일 필요 없음!)**
	// this.display = () => {
	//	console.log(`${this.name}: ${this.emoji}`);
	//	};
  }

  **// 클래스에 필요한 '멤버함수'는 보통 생성자 밖에 함수를 정의한다. (this 붙일 필요 없음!)**
  // 이 때, function 이라는 키워드 붙이면 오류 발생!
		//function 키워드 제외하고 이렇게 함수 선언(?)식으로 작성해도 되고
		// display () {
		//	console.log(`${this.name}: ${this.emoji}`);
		//	};
		// 이렇게 arrow 함수로 멤버함수를 작성해도 된다.
  display = () => {
    console.log(`${this.name}: ${this.emoji}`);
  };
}

// apple은 Fruit 클래스의 인스턴스이다.
const apple = new Fruit('apple', '🍎');
// orange은 Fruit 클래스의 인스턴스이다.
const orange = new Fruit('orange', '🍊');

console.log(apple);
console.log(orange);
console.log(apple.name);
console.log(apple.emoji);
apple.display();

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다.
const obj = { name: 'ellie' };
<aside> 💡 정리해보자면, 클래스를 정의할 때 클래스라는 키워드를 통해 만들 수 있고 각각의 클래스는 생성자 함수가 있다. new 키워드로 객체를 생성할 때 이 생성자가 호출이 된다. 생성자에는 우리가 객체를 만드는 데에 필요한 데이터를 인자로 (외부로부터) 받아올 수 있도록 생성자를 이용한다. 이 안에 이 객체의 네임을 this.name은 name, this.emoji의 emoji는 이거! 라고 링크해주면 된다! 그리고 객체 안에서 사용하는 멤버 함수가 있다면 그냥 이렇게 이름만 작성해서 일반 함수나 애로우 펑션으로 작성하면 된다.

</aside>

끝내기 전 TIP!
<aside> 💡

여기서 apple이라는 객체는

// apple은 Fruit 클래스의 인스턴스이다.

Fruit이라는 클래스를 통해 만들어진 객체이기 때문에!

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다. const obj = { name: 'ellie' };

</aside>

Copy
// apple은 Fruit 클래스의 인스턴스이다.
const apple = new Fruit('apple', '🍎');
// orange은 Fruit 클래스의 인스턴스이다.
const orange = new Fruit('orange', '🍊');

console.log(apple);
console.log(orange);
console.log(apple.name);
console.log(apple.emoji);
apple.display();

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다.
const obj = { name: 'ellie' };

인스턴스 vs 객체

객체는 그냥 만들어진 객체이고

어떤 클래스를 통해 만들어진 객체라면 인스턴스라고 부른다

9.3 재사용성을 높이는 방법
클래스 : 비슷한 부류의, 비슷한 카테고리의 동일한 속성과 행동을 가지고 있는 객체들을 생성할 수 있는 템플릿을 만드는 클래스!

클래스에 정의된 프로퍼티와 메소드는 사실, 인스턴스 레벨의 프로퍼티와 메소드라고 볼 수 있다.

뭔 말이여~? 무슨 말이나면~

클래스를 이용해 만든 인스턴스에 클래스들에서 정의한 프로퍼티와 함수들이 중복적으로 만들어지기 때문이다.

우리가 템플릿을 만들어두고 필요한 데이터를 주입해서 동일한 것을 지속적으로 만들어나갈 수 있죠/

= 클래스를 이용해서 인스턴스를 찍어낼 수 있다.

클래스를 통해 인스턴스를 찍어낼 수 있었다. 이 때 동일한 프로퍼티와 메소드가 들어가기 때문에 이것들을 전부 다 인스턴스 레벨의 프로퍼티와 메소드다!라고 볼 수 있다

그래서 만들어진 각각의 인스턴스(객체)를 통해서 객체.속성, 객체.함수! 이렇게 호출할 수 있었지?

그런데 여기서, 지금은 각각의 객체마다 서로 다른 데이터를 가지고 있어서 인스턴스 레벨의 프로퍼티를 만드는 것은 맞지만 우리가 모든 객체마다 동일하게 참조해야하는 속성이나 행동이 있다면? 어떻게 해야할까?

⇒ 클래스 레벨의 프로퍼티와 메소드를 사용한다!

생성자 함수와 마찬가지로 객체를 생성할 수 있는 템플릿(청사진, 틀, 양식)

클래스나 생성자 함수를 이용해 객체지향 프로그래밍을 할 수 있다.

</aside>

객체지향 프로그래밍

프로그래밍 시 그냥 절차적으로 코드를 작성하는 게 아니라

서로 밀접하게 연관있는 아이들을 객체로 구성해나가면서 객체끼리 서로 호환 가능하도록 할 수 있는 것

<aside> 💡 JS에서는 생성자 함수, 클래스를 이용해 객체를 생성할 수 있지만, 요즘은 모던한 클래스!를 이용해 객체를 생성한다. (현업에서는 이제 거의 대부분 생성자 함수는 쓰지 않는다.) 그리고 클래스가 프로토타입보다 사용하기 간편하다.

</aside>

용어 짚고 가기

생성자 함수와 동일하게 클래스를 이용해 객체를 만들 수 있는데,

이 때 클래스를 이용해 만들어진 객체를 인스턴스 라고 한다.

<aside> 💡 클래스를 통해 객체를 만들 수 있고, 이렇게 만들어진 객체를 이 클래스의 인스턴스 라고 한다.

</aside>

9.2 클래스의 기본
https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Statements/class

javascript/5.object/5.create.js의 생성자 함수 function Fruit을 클래스로 변환하자.
// 생성자 함수 function Fruit(name, emoji) { this.name = name; this.emiji = emoji; this.display = () => { console.log(`${this.name}: ${this.emoji}`); }; // return this; // 생략 가능 }
Copy
// 객체를 손쉽게 만들 수 있는 템플릿
// 1. 생성자 함수 (오래된 고전적인 방법) : 더이상 쓰이지 않는다.
// 2. 클래스 ✨

// 생성자 함수는 function이라는 키워드로 시작하지만 클래스는 다르다!
// 클래스 class
class Fruit {
  // Fruit이라는 템플릿은~ {} 중괄호로 묶어야하고

  // Fruit 클래스를 이용해 만드는 객체에 필요한 데이터를 채워줄 수 있는 constructor(생성자) 지정!
  // 생성자: new 키워드로 객체를 생성할 때 호출되는 함수
  constructor(name, emoji) { // 모든 class는 이렇게 기본적인 생성자가 들어있어야 한다. 
	**// 생성자 안에서 객체를 만드는 데에 필요한 데이터들을 채워줘야한다.**
    this.name = name;
    this.emoji = emoji;
  **// 클래스에 필요한 멤버함수는 보통 생성자 밖에 함수를 정의한다. (this 붙일 필요 없음!)**
	// this.display = () => {
	//	console.log(`${this.name}: ${this.emoji}`);
	//	};
  }

  **// 클래스에 필요한 '멤버함수'는 보통 생성자 밖에 함수를 정의한다. (this 붙일 필요 없음!)**
  // 이 때, function 이라는 키워드 붙이면 오류 발생!
		//function 키워드 제외하고 이렇게 함수 선언(?)식으로 작성해도 되고
		// display () {
		//	console.log(`${this.name}: ${this.emoji}`);
		//	};
		// 이렇게 arrow 함수로 멤버함수를 작성해도 된다.
  display = () => {
    console.log(`${this.name}: ${this.emoji}`);
  };
}

// apple은 Fruit 클래스의 인스턴스이다.
const apple = new Fruit('apple', '🍎');
// orange은 Fruit 클래스의 인스턴스이다.
const orange = new Fruit('orange', '🍊');

console.log(apple);
console.log(orange);
console.log(apple.name);
console.log(apple.emoji);
apple.display();

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다.
const obj = { name: 'ellie' };
<aside> 💡 정리해보자면, 클래스를 정의할 때 클래스라는 키워드를 통해 만들 수 있고 각각의 클래스는 생성자 함수가 있다. new 키워드로 객체를 생성할 때 이 생성자가 호출이 된다. 생성자에는 우리가 객체를 만드는 데에 필요한 데이터를 인자로 (외부로부터) 받아올 수 있도록 생성자를 이용한다. 이 안에 이 객체의 네임을 this.name은 name, this.emoji의 emoji는 이거! 라고 링크해주면 된다! 그리고 객체 안에서 사용하는 멤버 함수가 있다면 그냥 이렇게 이름만 작성해서 일반 함수나 애로우 펑션으로 작성하면 된다.

</aside>

끝내기 전 TIP!
<aside> 💡

여기서 apple이라는 객체는

// apple은 Fruit 클래스의 인스턴스이다.

Fruit이라는 클래스를 통해 만들어진 객체이기 때문에!

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다. const obj = { name: 'ellie' };

</aside>

Copy
// apple은 Fruit 클래스의 인스턴스이다.
const apple = new Fruit('apple', '🍎');
// orange은 Fruit 클래스의 인스턴스이다.
const orange = new Fruit('orange', '🍊');

console.log(apple);
console.log(orange);
console.log(apple.name);
console.log(apple.emoji);
apple.display();

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다.
const obj = { name: 'ellie' };

인스턴스 vs 객체

객체는 그냥 만들어진 객체이고

어떤 클래스를 통해 만들어진 객체라면 인스턴스라고 부른다

9.3 재사용성을 높이는 방법
클래스 : 비슷한 부류의, 비슷한 카테고리의 동일한 속성과 행동을 가지고 있는 객체들을 생성할 수 있는 템플릿을 만드는 클래스!

클래스에 정의된 프로퍼티와 메소드는 사실, 인스턴스 레벨의 프로퍼티와 메소드라고 볼 수 있다.

뭔 말이여~? 무슨 말이나면~

클래스를 이용해 만든 인스턴스에 클래스들에서 정의한 프로퍼티와 함수들이 중복적으로 만들어지기 때문이다.

우리가 템플릿을 만들어두고 필요한 데이터를 주입해서 동일한 것을 지속적으로 만들어나갈 수 있죠/

= 클래스를 이용해서 인스턴스를 찍어낼 수 있다.

클래스를 통해 인스턴스를 찍어낼 수 있었다. 이 때 동일한 프로퍼티와 메소드가 들어가기 때문에 이것들을 전부 다 인스턴스 레벨의 프로퍼티와 메소드다!라고 볼 수 있다

그래서 만들어진 각각의 인스턴스(객체)를 통해서 객체.속성, 객체.함수! 이렇게 호출할 수 있었지?

그런데 여기서, 지금은 각각의 객체마다 서로 다른 데이터를 가지고 있어서 인스턴스 레벨의 프로퍼티를 만드는 것은 맞지만 우리가 모든 객체마다 동일하게 참조해야하는 속성이나 행동이 있다면? 어떻게 해야할까?

⇒ 클래스 레벨의 프로퍼티와 메소드를 사용한다!

생성자 함수와 마찬가지로 객체를 생성할 수 있는 템플릿(청사진, 틀, 양식)

클래스나 생성자 함수를 이용해 객체지향 프로그래밍을 할 수 있다.

</aside>

객체지향 프로그래밍

프로그래밍 시 그냥 절차적으로 코드를 작성하는 게 아니라

서로 밀접하게 연관있는 아이들을 객체로 구성해나가면서 객체끼리 서로 호환 가능하도록 할 수 있는 것

<aside> 💡 JS에서는 생성자 함수, 클래스를 이용해 객체를 생성할 수 있지만, 요즘은 모던한 클래스!를 이용해 객체를 생성한다. (현업에서는 이제 거의 대부분 생성자 함수는 쓰지 않는다.) 그리고 클래스가 프로토타입보다 사용하기 간편하다.

</aside>

용어 짚고 가기

생성자 함수와 동일하게 클래스를 이용해 객체를 만들 수 있는데,

이 때 클래스를 이용해 만들어진 객체를 인스턴스 라고 한다.

<aside> 💡 클래스를 통해 객체를 만들 수 있고, 이렇게 만들어진 객체를 이 클래스의 인스턴스 라고 한다.

</aside>

9.2 클래스의 기본
https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Statements/class

javascript/5.object/5.create.js의 생성자 함수 function Fruit을 클래스로 변환하자.
// 생성자 함수 function Fruit(name, emoji) { this.name = name; this.emiji = emoji; this.display = () => { console.log(`${this.name}: ${this.emoji}`); }; // return this; // 생략 가능 }
Copy
// 객체를 손쉽게 만들 수 있는 템플릿
// 1. 생성자 함수 (오래된 고전적인 방법) : 더이상 쓰이지 않는다.
// 2. 클래스 ✨

// 생성자 함수는 function이라는 키워드로 시작하지만 클래스는 다르다!
// 클래스 class
class Fruit {
  // Fruit이라는 템플릿은~ {} 중괄호로 묶어야하고

  // Fruit 클래스를 이용해 만드는 객체에 필요한 데이터를 채워줄 수 있는 constructor(생성자) 지정!
  // 생성자: new 키워드로 객체를 생성할 때 호출되는 함수
  constructor(name, emoji) { // 모든 class는 이렇게 기본적인 생성자가 들어있어야 한다. 
	**// 생성자 안에서 객체를 만드는 데에 필요한 데이터들을 채워줘야한다.**
    this.name = name;
    this.emoji = emoji;
  **// 클래스에 필요한 멤버함수는 보통 생성자 밖에 함수를 정의한다. (this 붙일 필요 없음!)**
	// this.display = () => {
	//	console.log(`${this.name}: ${this.emoji}`);
	//	};
  }

  **// 클래스에 필요한 '멤버함수'는 보통 생성자 밖에 함수를 정의한다. (this 붙일 필요 없음!)**
  // 이 때, function 이라는 키워드 붙이면 오류 발생!
		//function 키워드 제외하고 이렇게 함수 선언(?)식으로 작성해도 되고
		// display () {
		//	console.log(`${this.name}: ${this.emoji}`);
		//	};
		// 이렇게 arrow 함수로 멤버함수를 작성해도 된다.
  display = () => {
    console.log(`${this.name}: ${this.emoji}`);
  };
}

// apple은 Fruit 클래스의 인스턴스이다.
const apple = new Fruit('apple', '🍎');
// orange은 Fruit 클래스의 인스턴스이다.
const orange = new Fruit('orange', '🍊');

console.log(apple);
console.log(orange);
console.log(apple.name);
console.log(apple.emoji);
apple.display();

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다.
const obj = { name: 'ellie' };
<aside> 💡 정리해보자면, 클래스를 정의할 때 클래스라는 키워드를 통해 만들 수 있고 각각의 클래스는 생성자 함수가 있다. new 키워드로 객체를 생성할 때 이 생성자가 호출이 된다. 생성자에는 우리가 객체를 만드는 데에 필요한 데이터를 인자로 (외부로부터) 받아올 수 있도록 생성자를 이용한다. 이 안에 이 객체의 네임을 this.name은 name, this.emoji의 emoji는 이거! 라고 링크해주면 된다! 그리고 객체 안에서 사용하는 멤버 함수가 있다면 그냥 이렇게 이름만 작성해서 일반 함수나 애로우 펑션으로 작성하면 된다.

</aside>

끝내기 전 TIP!
<aside> 💡

여기서 apple이라는 객체는

// apple은 Fruit 클래스의 인스턴스이다.

Fruit이라는 클래스를 통해 만들어진 객체이기 때문에!

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다. const obj = { name: 'ellie' };

</aside>

Copy
// apple은 Fruit 클래스의 인스턴스이다.
const apple = new Fruit('apple', '🍎');
// orange은 Fruit 클래스의 인스턴스이다.
const orange = new Fruit('orange', '🍊');

console.log(apple);
console.log(orange);
console.log(apple.name);
console.log(apple.emoji);
apple.display();

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다.
const obj = { name: 'ellie' };

인스턴스 vs 객체

객체는 그냥 만들어진 객체이고

어떤 클래스를 통해 만들어진 객체라면 인스턴스라고 부른다

9.3 재사용성을 높이는 방법
클래스 : 비슷한 부류의, 비슷한 카테고리의 동일한 속성과 행동을 가지고 있는 객체들을 생성할 수 있는 템플릿을 만드는 클래스!

클래스에 정의된 프로퍼티와 메소드는 사실, 인스턴스 레벨의 프로퍼티와 메소드라고 볼 수 있다.

뭔 말이여~? 무슨 말이나면~

클래스를 이용해 만든 인스턴스에 클래스들에서 정의한 프로퍼티와 함수들이 중복적으로 만들어지기 때문이다.

우리가 템플릿을 만들어두고 필요한 데이터를 주입해서 동일한 것을 지속적으로 만들어나갈 수 있죠/

= 클래스를 이용해서 인스턴스를 찍어낼 수 있다.

클래스를 통해 인스턴스를 찍어낼 수 있었다. 이 때 동일한 프로퍼티와 메소드가 들어가기 때문에 이것들을 전부 다 인스턴스 레벨의 프로퍼티와 메소드다!라고 볼 수 있다

그래서 만들어진 각각의 인스턴스(객체)를 통해서 객체.속성, 객체.함수! 이렇게 호출할 수 있었지?

그런데 여기서, 지금은 각각의 객체마다 서로 다른 데이터를 가지고 있어서 인스턴스 레벨의 프로퍼티를 만드는 것은 맞지만 우리가 모든 객체마다 동일하게 참조해야하는 속성이나 행동이 있다면? 어떻게 해야할까?

⇒ 클래스 레벨의 프로퍼티와 메소드를 사용한다!

생성자 함수와 마찬가지로 객체를 생성할 수 있는 템플릿(청사진, 틀, 양식)

클래스나 생성자 함수를 이용해 객체지향 프로그래밍을 할 수 있다.

</aside>

객체지향 프로그래밍

프로그래밍 시 그냥 절차적으로 코드를 작성하는 게 아니라

서로 밀접하게 연관있는 아이들을 객체로 구성해나가면서 객체끼리 서로 호환 가능하도록 할 수 있는 것

<aside> 💡 JS에서는 생성자 함수, 클래스를 이용해 객체를 생성할 수 있지만, 요즘은 모던한 클래스!를 이용해 객체를 생성한다. (현업에서는 이제 거의 대부분 생성자 함수는 쓰지 않는다.) 그리고 클래스가 프로토타입보다 사용하기 간편하다.

</aside>

용어 짚고 가기

생성자 함수와 동일하게 클래스를 이용해 객체를 만들 수 있는데,

이 때 클래스를 이용해 만들어진 객체를 인스턴스 라고 한다.

<aside> 💡 클래스를 통해 객체를 만들 수 있고, 이렇게 만들어진 객체를 이 클래스의 인스턴스 라고 한다.

</aside>

9.2 클래스의 기본
https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Statements/class

javascript/5.object/5.create.js의 생성자 함수 function Fruit을 클래스로 변환하자.
// 생성자 함수 function Fruit(name, emoji) { this.name = name; this.emiji = emoji; this.display = () => { console.log(`${this.name}: ${this.emoji}`); }; // return this; // 생략 가능 }
Copy
// 객체를 손쉽게 만들 수 있는 템플릿
// 1. 생성자 함수 (오래된 고전적인 방법) : 더이상 쓰이지 않는다.
// 2. 클래스 ✨

// 생성자 함수는 function이라는 키워드로 시작하지만 클래스는 다르다!
// 클래스 class
class Fruit {
  // Fruit이라는 템플릿은~ {} 중괄호로 묶어야하고

  // Fruit 클래스를 이용해 만드는 객체에 필요한 데이터를 채워줄 수 있는 constructor(생성자) 지정!
  // 생성자: new 키워드로 객체를 생성할 때 호출되는 함수
  constructor(name, emoji) { // 모든 class는 이렇게 기본적인 생성자가 들어있어야 한다. 
	**// 생성자 안에서 객체를 만드는 데에 필요한 데이터들을 채워줘야한다.**
    this.name = name;
    this.emoji = emoji;
  **// 클래스에 필요한 멤버함수는 보통 생성자 밖에 함수를 정의한다. (this 붙일 필요 없음!)**
	// this.display = () => {
	//	console.log(`${this.name}: ${this.emoji}`);
	//	};
  }

  **// 클래스에 필요한 '멤버함수'는 보통 생성자 밖에 함수를 정의한다. (this 붙일 필요 없음!)**
  // 이 때, function 이라는 키워드 붙이면 오류 발생!
		//function 키워드 제외하고 이렇게 함수 선언(?)식으로 작성해도 되고
		// display () {
		//	console.log(`${this.name}: ${this.emoji}`);
		//	};
		// 이렇게 arrow 함수로 멤버함수를 작성해도 된다.
  display = () => {
    console.log(`${this.name}: ${this.emoji}`);
  };
}

// apple은 Fruit 클래스의 인스턴스이다.
const apple = new Fruit('apple', '🍎');
// orange은 Fruit 클래스의 인스턴스이다.
const orange = new Fruit('orange', '🍊');

console.log(apple);
console.log(orange);
console.log(apple.name);
console.log(apple.emoji);
apple.display();

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다.
const obj = { name: 'ellie' };
<aside> 💡 정리해보자면, 클래스를 정의할 때 클래스라는 키워드를 통해 만들 수 있고 각각의 클래스는 생성자 함수가 있다. new 키워드로 객체를 생성할 때 이 생성자가 호출이 된다. 생성자에는 우리가 객체를 만드는 데에 필요한 데이터를 인자로 (외부로부터) 받아올 수 있도록 생성자를 이용한다. 이 안에 이 객체의 네임을 this.name은 name, this.emoji의 emoji는 이거! 라고 링크해주면 된다! 그리고 객체 안에서 사용하는 멤버 함수가 있다면 그냥 이렇게 이름만 작성해서 일반 함수나 애로우 펑션으로 작성하면 된다.

</aside>

끝내기 전 TIP!
<aside> 💡

여기서 apple이라는 객체는

// apple은 Fruit 클래스의 인스턴스이다.

Fruit이라는 클래스를 통해 만들어진 객체이기 때문에!

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다. const obj = { name: 'ellie' };

</aside>

Copy
// apple은 Fruit 클래스의 인스턴스이다.
const apple = new Fruit('apple', '🍎');
// orange은 Fruit 클래스의 인스턴스이다.
const orange = new Fruit('orange', '🍊');

console.log(apple);
console.log(orange);
console.log(apple.name);
console.log(apple.emoji);
apple.display();

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다.
const obj = { name: 'ellie' };

인스턴스 vs 객체

객체는 그냥 만들어진 객체이고

어떤 클래스를 통해 만들어진 객체라면 인스턴스라고 부른다

9.3 재사용성을 높이는 방법
클래스 : 비슷한 부류의, 비슷한 카테고리의 동일한 속성과 행동을 가지고 있는 객체들을 생성할 수 있는 템플릿을 만드는 클래스!

클래스에 정의된 프로퍼티와 메소드는 사실, 인스턴스 레벨의 프로퍼티와 메소드라고 볼 수 있다.

뭔 말이여~? 무슨 말이나면~

클래스를 이용해 만든 인스턴스에 클래스들에서 정의한 프로퍼티와 함수들이 중복적으로 만들어지기 때문이다.

우리가 템플릿을 만들어두고 필요한 데이터를 주입해서 동일한 것을 지속적으로 만들어나갈 수 있죠/

= 클래스를 이용해서 인스턴스를 찍어낼 수 있다.

클래스를 통해 인스턴스를 찍어낼 수 있었다. 이 때 동일한 프로퍼티와 메소드가 들어가기 때문에 이것들을 전부 다 인스턴스 레벨의 프로퍼티와 메소드다!라고 볼 수 있다

그래서 만들어진 각각의 인스턴스(객체)를 통해서 객체.속성, 객체.함수! 이렇게 호출할 수 있었지?

그런데 여기서, 지금은 각각의 객체마다 서로 다른 데이터를 가지고 있어서 인스턴스 레벨의 프로퍼티를 만드는 것은 맞지만 우리가 모든 객체마다 동일하게 참조해야하는 속성이나 행동이 있다면? 어떻게 해야할까?

⇒ 클래스 레벨의 프로퍼티와 메소드를 사용한다!

생성자 함수와 마찬가지로 객체를 생성할 수 있는 템플릿(청사진, 틀, 양식)

클래스나 생성자 함수를 이용해 객체지향 프로그래밍을 할 수 있다.

</aside>

객체지향 프로그래밍

프로그래밍 시 그냥 절차적으로 코드를 작성하는 게 아니라

서로 밀접하게 연관있는 아이들을 객체로 구성해나가면서 객체끼리 서로 호환 가능하도록 할 수 있는 것

<aside> 💡 JS에서는 생성자 함수, 클래스를 이용해 객체를 생성할 수 있지만, 요즘은 모던한 클래스!를 이용해 객체를 생성한다. (현업에서는 이제 거의 대부분 생성자 함수는 쓰지 않는다.) 그리고 클래스가 프로토타입보다 사용하기 간편하다.

</aside>

용어 짚고 가기

생성자 함수와 동일하게 클래스를 이용해 객체를 만들 수 있는데,

이 때 클래스를 이용해 만들어진 객체를 인스턴스 라고 한다.

<aside> 💡 클래스를 통해 객체를 만들 수 있고, 이렇게 만들어진 객체를 이 클래스의 인스턴스 라고 한다.

</aside>

9.2 클래스의 기본
https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Statements/class

javascript/5.object/5.create.js의 생성자 함수 function Fruit을 클래스로 변환하자.
// 생성자 함수 function Fruit(name, emoji) { this.name = name; this.emiji = emoji; this.display = () => { console.log(`${this.name}: ${this.emoji}`); }; // return this; // 생략 가능 }
Copy
// 객체를 손쉽게 만들 수 있는 템플릿
// 1. 생성자 함수 (오래된 고전적인 방법) : 더이상 쓰이지 않는다.
// 2. 클래스 ✨

// 생성자 함수는 function이라는 키워드로 시작하지만 클래스는 다르다!
// 클래스 class
class Fruit {
  // Fruit이라는 템플릿은~ {} 중괄호로 묶어야하고

  // Fruit 클래스를 이용해 만드는 객체에 필요한 데이터를 채워줄 수 있는 constructor(생성자) 지정!
  // 생성자: new 키워드로 객체를 생성할 때 호출되는 함수
  constructor(name, emoji) { // 모든 class는 이렇게 기본적인 생성자가 들어있어야 한다. 
	**// 생성자 안에서 객체를 만드는 데에 필요한 데이터들을 채워줘야한다.**
    this.name = name;
    this.emoji = emoji;
  **// 클래스에 필요한 멤버함수는 보통 생성자 밖에 함수를 정의한다. (this 붙일 필요 없음!)**
	// this.display = () => {
	//	console.log(`${this.name}: ${this.emoji}`);
	//	};
  }

  **// 클래스에 필요한 '멤버함수'는 보통 생성자 밖에 함수를 정의한다. (this 붙일 필요 없음!)**
  // 이 때, function 이라는 키워드 붙이면 오류 발생!
		//function 키워드 제외하고 이렇게 함수 선언(?)식으로 작성해도 되고
		// display () {
		//	console.log(`${this.name}: ${this.emoji}`);
		//	};
		// 이렇게 arrow 함수로 멤버함수를 작성해도 된다.
  display = () => {
    console.log(`${this.name}: ${this.emoji}`);
  };
}

// apple은 Fruit 클래스의 인스턴스이다.
const apple = new Fruit('apple', '🍎');
// orange은 Fruit 클래스의 인스턴스이다.
const orange = new Fruit('orange', '🍊');

console.log(apple);
console.log(orange);
console.log(apple.name);
console.log(apple.emoji);
apple.display();

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다.
const obj = { name: 'ellie' };
<aside> 💡 정리해보자면, 클래스를 정의할 때 클래스라는 키워드를 통해 만들 수 있고 각각의 클래스는 생성자 함수가 있다. new 키워드로 객체를 생성할 때 이 생성자가 호출이 된다. 생성자에는 우리가 객체를 만드는 데에 필요한 데이터를 인자로 (외부로부터) 받아올 수 있도록 생성자를 이용한다. 이 안에 이 객체의 네임을 this.name은 name, this.emoji의 emoji는 이거! 라고 링크해주면 된다! 그리고 객체 안에서 사용하는 멤버 함수가 있다면 그냥 이렇게 이름만 작성해서 일반 함수나 애로우 펑션으로 작성하면 된다.

</aside>

끝내기 전 TIP!
<aside> 💡

여기서 apple이라는 객체는

// apple은 Fruit 클래스의 인스턴스이다.

Fruit이라는 클래스를 통해 만들어진 객체이기 때문에!

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다. const obj = { name: 'ellie' };

</aside>

Copy
// apple은 Fruit 클래스의 인스턴스이다.
const apple = new Fruit('apple', '🍎');
// orange은 Fruit 클래스의 인스턴스이다.
const orange = new Fruit('orange', '🍊');

console.log(apple);
console.log(orange);
console.log(apple.name);
console.log(apple.emoji);
apple.display();

// obj는 객체이고, 그 어떤 클래스의 인스턴스도 아니다.
const obj = { name: 'ellie' };

인스턴스 vs 객체

객체는 그냥 만들어진 객체이고

어떤 클래스를 통해 만들어진 객체라면 인스턴스라고 부른다

9.3 재사용성을 높이는 방법
클래스 : 비슷한 부류의, 비슷한 카테고리의 동일한 속성과 행동을 가지고 있는 객체들을 생성할 수 있는 템플릿을 만드는 클래스!

클래스에 정의된 프로퍼티와 메소드는 사실, 인스턴스 레벨의 프로퍼티와 메소드라고 볼 수 있다.

뭔 말이여~? 무슨 말이나면~

클래스를 이용해 만든 인스턴스에 클래스들에서 정의한 프로퍼티와 함수들이 중복적으로 만들어지기 때문이다.

우리가 템플릿을 만들어두고 필요한 데이터를 주입해서 동일한 것을 지속적으로 만들어나갈 수 있죠/

= 클래스를 이용해서 인스턴스를 찍어낼 수 있다.

클래스를 통해 인스턴스를 찍어낼 수 있었다. 이 때 동일한 프로퍼티와 메소드가 들어가기 때문에 이것들을 전부 다 인스턴스 레벨의 프로퍼티와 메소드다!라고 볼 수 있다

그래서 만들어진 각각의 인스턴스(객체)를 통해서 객체.속성, 객체.함수! 이렇게 호출할 수 있었지?

그런데 여기서, 지금은 각각의 객체마다 서로 다른 데이터를 가지고 있어서 인스턴스 레벨의 프로퍼티를 만드는 것은 맞지만 우리가 모든 객체마다 동일하게 참조해야하는 속성이나 행동이 있다면? 어떻게 해야할까?

⇒ 클래스 레벨의 프로퍼티와 메소드를 사용한다!

 

이렇게 클래스 안에서 static이라는 키워드를 프로퍼티나 메소드 앞에 붙일 수 있는데

이 static이 붙은 프로퍼티나 메소드는

만들어진 인스턴스에 포함되지 않고 이 클래스에 그대로 남아있게 된다.

즉, 클래스에 딱! 한 번만 만들어지고 각각의 인스턴스에는 들어있지 않다. ⇒ 즉, 클래스에 한 번만 정의하고 재사용할 수 있다! 그래서 호출할때도 ~~만들어진 인스턴트.이 아니라~~ 클래스 이름. 즉, 클래스 레벨의 메소드를 호출할 수 있다.

지난시간에 배운 거!
static 정적 프로퍼티와 메서드에 대해 알아보자.

Copy
// static 정적 프로퍼티, 메서드
class Fruit {
  static MAX_FRUITS = 4;
  // 생성자: new 키워드로 객체를 생성할때 호출되는 함수
  // staic 붙이지 않으면 instance level => 클래스 자체에서는 접근이 안된다.

  // => console.log(Fruit.name);, Fruit.display(); 할 수 없다!!!!!!
  // 왜? 클래스 자체에는 이런 데이터들이 채워져있지 않은 상태인 템플릿이기 때문!
  // 이 자체만으로는 호출이 불가능, 속성을 접근해도 데이터가 없는 상태다.
  // TypeError: Fruit.display is not a function
  // => 즉, 객체 자체에 있는 함수는 우리가 호출할 수 없다!

  **// 이렇게 인스턴스 레벨의  프로퍼티와 메소드는 꼭! 생성된 인스턴스를 통해서 접근, 호출해야 한다!!**

  constructor(name, emoji) {  
    this.name = name;
    this.emoji = emoji;
  }

  // 클래스 레벨의 메서드
  static makeRandomFruit() {
    // 클래스 레벨의 메서드에서는 this를 참조할 수 없음
    return new Fruit('banana', '🍌');
  }

  // 인스턴스 레벨의 메서드
  display = () => {
    console.log(`${this.name}: ${this.emoji}`);
  };
}

const banana = Fruit.makeRandomFruit();
console.log(banana);
console.log(Fruit.MAX_FRUITS);
// apple은 Fruit 클래스의 인스턴스이다.
const apple = new Fruit('apple', '🍎');
// orange은 Fruit 클래스의 인스턴스이다.
const orange = new Fruit('orange', '🍊');

  // 이렇게 인스턴스 레벨의  프로퍼티와 메소드는 꼭! 생성된 인스턴스를 통해서 접근, 호출해야 한다!!
console.log(apple);
console.log(orange);
console.log(apple.name);
console.log(apple.emoji);
apple.display();

Math.pow();
Number.isFinite(1);
이제 static을 알아볼거다!

이 display() 함수는 만들어진 객체의 주어진 데이터에 접근해서 무언가를 호출해서 출력해야한다.

즉, 만들어진 인스턴스와 밀접하게 연관이 있기 때문에 ⇒ 인스턴스 레벨에 두는 게 좋다.

Copy
// static 정적 프로퍼티, 메서드
class Fruit {
  static MAX_FRUITS = 4;
  // 생성자: new 키워드로 객체를 생성할때 호출되는 함수
  // staic 붙이지 않으면 instance level => 클래스 자체에서는 접근이 안된다.
  // => console.log(Fruit.name);, Fruit.display(); 할 수 없다!!!!!!
  // 왜? 클래스 자체에는 이런 데이터들이 채워져있지 않은 상태인 템플릿이기 때문!
  // 이 자체만으로는 호출이 불가능, 속성을 접근해도 데이터가 없는 상태다.
  // TypeError: Fruit.display is not a function
  // => 즉, 객체 자체에 있는 함수는 우리가 호출할 수 없다!

  constructor(name, emoji) {  
    this.name = name;
    this.emoji = emoji;
  }

  // Fruit이라는 클래스를 통해 호출할 수 있는 함수 - static 붙었으니까!

  **// 클래스별로 공통적으로 사용할 수 있고 만들어진 인스턴스 데이터를 참조할 필요가 없는 함수는 클래스 레벨로 만든다! static!**
  // 클래스 레벨의 메서드
  static makeRandomFruit() {
    // 클래스 레벨의 메서드에서는 this를 참조할 수 없음
    // 주어진 데이터가 채워져 있지 않은 템플릿이기 때문에 this를 참조할 수 없다!
    // 왜? 클래스 자체로는 아무것도 채워지지 않은 템플릿이니까!
    return new Fruit('banana', '🍌');// 새로운 과일의 object를 만들어 리턴 
  }

  // 인스턴스 레벨의 메서드
  // 이 display() 함수는 만들어진 객체의 무언가를 호출해서 출력해야한다. 
  // 즉, 만들어진 인스턴스와 밀접하게 연관이 있기 때문에 ⇒ 인스턴스 레벨에 두는 게 좋다.
  display = () => {
    console.log(`${this.name}: ${this.emoji}`);
  };
}

// 만들어진 오브젝트에서 호출하는 것이 아니라! 
// banana는 Fruit이라는 클래스에 있는 static makeRandomFruit() 함수를 직접적으로 호출할 수 있다.
const banana = Fruit.makeRandomFruit();
console.log(banana);
console.log(Fruit.MAX_FRUITS);
// apple은 Fruit 클래스의 인스턴스이다.
const apple = new Fruit('apple', '🍎');
// orange은 Fruit 클래스의 인스턴스이다.
const orange = new Fruit('orange', '🍊');

  // 이렇게 인스턴스 레벨의  프로퍼티와 메소드는 꼭! 생성된 인스턴스를 통해서 접근, 호출해야 한다!!
console.log(apple);
console.log(orange);
console.log(apple.name);
console.log(apple.emoji);
apple.display();

Math.pow();
Number.isFinite(1);
<aside> 💡 클래스 레벨의 함수는 클래스 이름으로 접근이 가능하다. const banana = Fruit.makeRandomFruit(); console.log(banana); console.log(Fruit.MAX_FRUITS); 인스턴스 레벨의 프로퍼티와 함수는 만들어진 인스턴스를 통해 접근이 가능하다. console.log(apple); console.log(orange); console.log(apple.name); console.log(apple.emoji); apple.display(); 그리고 static이라는 키워드를 붙이면 클래스 레벨로 만들 수 있고 함수 앞에 붙이면 클래스 레벨의 함수가 되고 속성 앞에 메소드를 붙일 수 있다.

</aside>

<aside> 💡 Fruit이라는 클래스에 static을 붙인 함수는

⇒ 만들어진 인스턴스 안에는 들어있지 않고

⇒ Fruit이라는 이름을 통해서만 접근이 가능하다!!!!!

</aside>

<aside> 💡 이처럼 각각 찍어내는 인스턴스마다 변경되어야하는 데이터가 아니라

클래스 레벨에서 공통적으로 사용할 수 있는 속성과 함수들에 대해서는 static을 붙여주면 좋다!

</aside>

bonus tip - 빌트인 오프젝트! (static을 이용하면 우리가 객체를 별도로 만들지 않아도 비슷한 내용의 함수들을 묶어서 관리할 수 있다는 장점이 있다)

static의 사용 예제

-뒤에 빌트인 오브젝트에서 배우게 될텐데 Math라는 수학에 관련된 유용한 함수들을(API들을) 제공하는 클래스가 있다.

우리가 Math 이용해 객체를 만들지 않아도 바로 이렇게 유용한 유틸리티 함수들을 호출할 수 있다.

Number도 바로 함수에 접근할 수 있다~

이 static이 붙은 프로퍼티나 메소드는

만들어진 인스턴스에 포함되지 않고 이 클래스에 그대로 남아있게 된다.

즉, 클래스에 딱! 한 번만 만들어지고 각각의 인스턴스에는 들어있지 않다. ⇒ 즉, 클래스에 한 번만 정의하고 재사용할 수 있다! 그래서 호출할때도 ~~만들어진 인스턴트.이 아니라~~ 클래스 이름. 즉, 클래스 레벨의 메소드를 호출할 수 있다.

지난시간에 배운 거!
static 정적 프로퍼티와 메서드에 대해 알아보자.

Copy
// static 정적 프로퍼티, 메서드
class Fruit {
  static MAX_FRUITS = 4;
  // 생성자: new 키워드로 객체를 생성할때 호출되는 함수
  // staic 붙이지 않으면 instance level => 클래스 자체에서는 접근이 안된다.

  // => console.log(Fruit.name);, Fruit.display(); 할 수 없다!!!!!!
  // 왜? 클래스 자체에는 이런 데이터들이 채워져있지 않은 상태인 템플릿이기 때문!
  // 이 자체만으로는 호출이 불가능, 속성을 접근해도 데이터가 없는 상태다.
  // TypeError: Fruit.display is not a function
  // => 즉, 객체 자체에 있는 함수는 우리가 호출할 수 없다!

  **// 이렇게 인스턴스 레벨의  프로퍼티와 메소드는 꼭! 생성된 인스턴스를 통해서 접근, 호출해야 한다!!**

  constructor(name, emoji) {  
    this.name = name;
    this.emoji = emoji;
  }

  // 클래스 레벨의 메서드
  static makeRandomFruit() {
    // 클래스 레벨의 메서드에서는 this를 참조할 수 없음
    return new Fruit('banana', '🍌');
  }

  // 인스턴스 레벨의 메서드
  display = () => {
    console.log(`${this.name}: ${this.emoji}`);
  };
}

const banana = Fruit.makeRandomFruit();
console.log(banana);
console.log(Fruit.MAX_FRUITS);
// apple은 Fruit 클래스의 인스턴스이다.
const apple = new Fruit('apple', '🍎');
// orange은 Fruit 클래스의 인스턴스이다.
const orange = new Fruit('orange', '🍊');

  // 이렇게 인스턴스 레벨의  프로퍼티와 메소드는 꼭! 생성된 인스턴스를 통해서 접근, 호출해야 한다!!
console.log(apple);
console.log(orange);
console.log(apple.name);
console.log(apple.emoji);
apple.display();

Math.pow();
Number.isFinite(1);
이제 static을 알아볼거다!

이 display() 함수는 만들어진 객체의 주어진 데이터에 접근해서 무언가를 호출해서 출력해야한다.

즉, 만들어진 인스턴스와 밀접하게 연관이 있기 때문에 ⇒ 인스턴스 레벨에 두는 게 좋다.

Copy
// static 정적 프로퍼티, 메서드
class Fruit {
  static MAX_FRUITS = 4;
  // 생성자: new 키워드로 객체를 생성할때 호출되는 함수
  // staic 붙이지 않으면 instance level => 클래스 자체에서는 접근이 안된다.
  // => console.log(Fruit.name);, Fruit.display(); 할 수 없다!!!!!!
  // 왜? 클래스 자체에는 이런 데이터들이 채워져있지 않은 상태인 템플릿이기 때문!
  // 이 자체만으로는 호출이 불가능, 속성을 접근해도 데이터가 없는 상태다.
  // TypeError: Fruit.display is not a function
  // => 즉, 객체 자체에 있는 함수는 우리가 호출할 수 없다!

  constructor(name, emoji) {  
    this.name = name;
    this.emoji = emoji;
  }

  // Fruit이라는 클래스를 통해 호출할 수 있는 함수 - static 붙었으니까!

  **// 클래스별로 공통적으로 사용할 수 있고 만들어진 인스턴스 데이터를 참조할 필요가 없는 함수는 클래스 레벨로 만든다! static!**
  // 클래스 레벨의 메서드
  static makeRandomFruit() {
    // 클래스 레벨의 메서드에서는 this를 참조할 수 없음
    // 주어진 데이터가 채워져 있지 않은 템플릿이기 때문에 this를 참조할 수 없다!
    // 왜? 클래스 자체로는 아무것도 채워지지 않은 템플릿이니까!
    return new Fruit('banana', '🍌');// 새로운 과일의 object를 만들어 리턴 
  }

  // 인스턴스 레벨의 메서드
  // 이 display() 함수는 만들어진 객체의 무언가를 호출해서 출력해야한다. 
  // 즉, 만들어진 인스턴스와 밀접하게 연관이 있기 때문에 ⇒ 인스턴스 레벨에 두는 게 좋다.
  display = () => {
    console.log(`${this.name}: ${this.emoji}`);
  };
}

// 만들어진 오브젝트에서 호출하는 것이 아니라! 
// banana는 Fruit이라는 클래스에 있는 static makeRandomFruit() 함수를 직접적으로 호출할 수 있다.
const banana = Fruit.makeRandomFruit();
console.log(banana);
console.log(Fruit.MAX_FRUITS);
// apple은 Fruit 클래스의 인스턴스이다.
const apple = new Fruit('apple', '🍎');
// orange은 Fruit 클래스의 인스턴스이다.
const orange = new Fruit('orange', '🍊');

  // 이렇게 인스턴스 레벨의  프로퍼티와 메소드는 꼭! 생성된 인스턴스를 통해서 접근, 호출해야 한다!!
console.log(apple);
console.log(orange);
console.log(apple.name);
console.log(apple.emoji);
apple.display();

Math.pow();
Number.isFinite(1);
<aside> 💡 클래스 레벨의 함수는 클래스 이름으로 접근이 가능하다. const banana = Fruit.makeRandomFruit(); console.log(banana); console.log(Fruit.MAX_FRUITS); 인스턴스 레벨의 프로퍼티와 함수는 만들어진 인스턴스를 통해 접근이 가능하다. console.log(apple); console.log(orange); console.log(apple.name); console.log(apple.emoji); apple.display(); 그리고 static이라는 키워드를 붙이면 클래스 레벨로 만들 수 있고 함수 앞에 붙이면 클래스 레벨의 함수가 되고 속성 앞에 메소드를 붙일 수 있다.

</aside>

<aside> 💡 Fruit이라는 클래스에 static을 붙인 함수는

⇒ 만들어진 인스턴스 안에는 들어있지 않고

⇒ Fruit이라는 이름을 통해서만 접근이 가능하다!!!!!

</aside>

<aside> 💡 이처럼 각각 찍어내는 인스턴스마다 변경되어야하는 데이터가 아니라

클래스 레벨에서 공통적으로 사용할 수 있는 속성과 함수들에 대해서는 static을 붙여주면 좋다!

</aside>

bonus tip - 빌트인 오프젝트! (static을 이용하면 우리가 객체를 별도로 만들지 않아도 비슷한 내용의 함수들을 묶어서 관리할 수 있다는 장점이 있다)

static의 사용 예제

-뒤에 빌트인 오브젝트에서 배우게 될텐데 Math라는 수학에 관련된 유용한 함수들을(API들을) 제공하는 클래스가 있다.

우리가 Math 이용해 객체를 만들지 않아도 바로 이렇게 유용한 유틸리티 함수들을 호출할 수 있다.

Number도 바로 함수에 접근할 수 있다~


좋아요공감
통계게시글 관리
