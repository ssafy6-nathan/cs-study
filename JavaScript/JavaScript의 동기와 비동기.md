## 동기와 비동기 프로그래밍의 등장 배경과 구현 방식

코드 실행하는 순서에 대한 정의!

![image](https://user-images.githubusercontent.com/57996351/189350754-b45fd14b-874a-4923-85f1-e29b1153addc.png)
- 동기
한 함수가 완전히 끝나야지만 다른 함수를 시작할 수 있다. (순차적으로 코드 실행) 하나의 코드가 반드시 끝나야만이 다음 코드를 실행할 수 있다.


😮 여기서 잠깐.


# 동기와 비동기
![image](https://user-images.githubusercontent.com/57996351/189351139-722948fa-1ca8-46d1-912e-7fd4ff52e5dd.png)

- 콜스택

자바 스크립트를 실행하는 브라우저의 구성 요소들 중 하나.

call - 함수. 함수를 콜한다.

stack - 이 함수들이 자료구조의 stack처럼 쌓여있다.

그런데 js 실행하는 브라우저 안에서는 콜스택이 하나밖에 없다.

여기서의 이 콜스택이 바로, syncronous에서 이 함수들을 스택처럼 담아서 실행시키는 구성요소라는 것을 알 수 있다.


🤨 그렇다면 왜 비동기가 등장한걸까?


![image](https://user-images.githubusercontent.com/57996351/189350754-b45fd14b-874a-4923-85f1-e29b1153addc.png)

이미지를 서버로부터 가져오는 함수와 화면에 그리는 함수가 있다고 가정하자.

⇒ 동기 - 이미지를 다 가져오기 전까지는 텍스트와 스크롤을 그릴 수 없다. 따라서 이 이미지들을 서버로부터 받아오는 동안 사용자는 그냥 백색 화면을 멍하니 바라봐야하는 것임.

그래서 비동기 등장.

반드시 어떤 함수가 끝나지 않더라도 다른 함수 실행 가능!!

⇒ 동시에 즉 병렬적으로 함수 실행 가능




여기서 의문점..

![image](https://user-images.githubusercontent.com/57996351/189351726-13575fef-7df9-4c25-b324-1619df61e8a5.png)


🤔 자바스크립트의 콜스택은 어떻게 함수를 병렬적으로 실행할 수 있을까?

콜스택 한개라며.. 여러개여야 비동기 가능한 거 아니야?

**Web APIs** 가 있다!!

DOM, AJAX, Timeout 등의 Web APIs들이 이 함수들을 대신 받아서 비동기적으로 실행할 수 있게끔 도와준다. 

따라서 서버로부터 이미지를 받아오는 작업은 AJAX http request에 해당하는데 이러한 것들이 마치 콜스택이 여러개인 것 처럼 js의 실행을 asynchronous하게 실행할 수 있게 한다. 

⇒ 이를 통해 사용자는 더이상 화면을 이미지 다 받아올 때까지 멍때리지 않아도 되는 것이다.

왜냐면,

콜스택은 분명히 한개지만 화면을 그려주고 있으면서 동시에 이미지를 받아오는 이 함수는 asynchronous하게 

마치 콜스택이 여러개인 것처럼 web api에게 부탁하는 것이다.

실행시점이 달라져도 괜찮다고. 병렬적으로 실행해도 된다고.



---

callback

비동기적으로 어떻게 콜백이 동작하는지에 대해 알아보자. 

콜백은 비동기적인 프로그래밍을 하는 데에 있어서 스케쥴링을 나의 마음대로 조절할 수 있게 해주는 하나의 기법으로 알고 있을거다.

> 콜백
> 

function을 인자로 넘겨줄 때 그 인자로 넘겨줄 function을 콜백이라고 부른다. 

그렇다면 어떻게 이 콜백이 비동기적인 프로그램과 연관이 되었을까?


# Callback 함수(Call + back)

- 요청한 작업이 다 끝나고, 작업 결과물을 콜백함수에서 실행 가능하다.
- 콜백함수로 네스팅을 미친듯이 하면 콜백지옥 등장 >_ㅇ

**Callback 지옥에 빠지지 않기 위해서 나타난게**

# Promise

되시겠다!

- `promise`의 뜻은 약속. 시간이 걸리는 어떤 작업을 하겠다고 약속하는 일
- 실패하든 성공하든 무조건 비동기로 응답
- ES2015부터 공식 지원
- promise → 객체

```jsx
const promise = new Promise((resolve, reject) => {
  try {
    ...비동기 작업
    resolve(결과);
  } catch (err) {
    reject(err);
  }
});
```

```jsx
promise.then((result) => {
  // result 처리
}).catch((err) => {
  console.error(err);
});
```

지금 코드에는 코드 양이 많지 않아서 `then`이나 `catch`한 모습이 많이 없으나 여전히 `then`의 체이닝으로 

인해 복잡도가 존재한다.

**체이닝 복잡도를 해결하기 위해 나타난 것이 바로바로!**

# async / await

이다!

- 비동기 요청이 가능
- 코드가 간결해지고, 가독성이 높아진다.
- error가 어디서 발생했는지 알기 쉽다.
- 비동기 코드이나 동기적인 코드로 유사하게 만들어준다.

### promise 사용

```jsx
const promise = new Promise((resolve, reject) => {
  try {
    ...비동기 작업
    resolve(결과);
  } catch (err) {
    reject(err);
  }
});

(async () => { 
  await promise()
  console.log('test1')
})();
```

### fetch 사용

```jsx
 selectJob = async e => {
	 const selectFetch = await fetch(`${URL.SMS_URL}/job`, {
	   method: "GET"
    });
    
   const selectFetchJson = await selectFetch.json();
    
   if(selectFetch.status === 200) {
      this.setState({
		    selectArr: await selectFetchJson.jobDate
			});
		}
 };

//에러시
// if문 아래에 작성
// throw new Error(selectFetch.status);
```

# axios

- 직접적으로 XMLHttpRequest 를 다루지 않고 `AJAX` 호출 가능
- 구형 브라우저 지원 가능
- 요청 중단 가능
- 응답 초과 시간 설정 가능
- JSON 데이터 자동변환

## 요청 메소드

`get`, `post`, `put`, `delete`, `patch`, `options` 로 요청가능

## Get 요청시

```jsx
axios.get('api address') 
        .then(res => {
            //성공 코드
            console.log(res);
        })
        .catch(err => {
            //실패
            console.log(err);
        })
        .then(() => {
            //성공했을때, 실패했을때 상관없이 띄어줌
            console.log('test');
        })
```

## Post 요청시

```jsx
const token = window.sessionStorage.getItem('token');

axios.post('api address', {
					account: state.id,
          password: state.pwd
        },
			headers: {
					Authorization: token
			  })
        .then(res => {
            console.log(res);
            this.userId = res.data.token;
        })
        .catch(err => {
            console.log(err);
        });
    }
```

<aside>
💡 **xmlhttpRequest**

xml은 html과 매우 비슷한 문자 기반의 마크업 언어이다.
xmlhttpRequest는 웹 브러우저와 서버 사이에 메소드가 존재해서 데이터를 전송하는 객체 폼의 API

</aside>

<aside>
💡 **Ajax**

Ajax는 Asynchronous Javascript And Xml(비동기식 자바스크립트와 xml)의 약자로
XMLHttpRequest 객체를 이용해서 데이터를 로드하는 기법 이며 
JavaScript를 사용한 비동기 통신, 클라이언트와 서버간에 XML 데이터를 주고받는 기술이라고 한다.

</aside>

# Fetch

- 서버에 요청할 때 쓰는 함수

```jsx
const token = window.sessionStorage.getItem('token');

fetch('http://address_api', {
      method: 'POST',
      headers: {
        Authorization:
          token
      },
      body: JSON.stringiafy({
        cart: [{ product_id: Number(this.props.no), quantity: 1 }],
      }),
    })
      .then(res => res.json())
      .then(response => console.log('Success:', JSON.stringify(response)))
      .catch(error => {
        alert('아이디 비밀번호를 다시 입력해주세요.')
        console.error('Error:', error)
      })
```




---

Fetch 함수

api를 호출하기 위한 함수


then 함수

호출이 오면 응답하는 함수
