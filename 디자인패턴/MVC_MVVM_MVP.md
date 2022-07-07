# 1. MVC
## MVC란?
- Model, View, Controller -> 하나의 애플리케이션, 프로젝트를 구성할 때 그 구성요소를 세가지의 역할로 구분한 패턴

### Model
- 애플리케이션의 정보, 데이터
- 데이터들의 가공을 책임지는 컴포넌트
- 컨트롤러가 호출할 때, 요청에 맞는 역할 수행(비즈니스 로직 구현)
- 상태의 변화가 있을 때, 컨트롤러와 뷰에 통보해 후속 조치 명령을 받을 수 있게 함

### View
- 컨트롤러로부터 받은 모델의 결과값을 가지고 사용자에게 출력할 화면을 만듦
- 만들어진 화면을 웹 브라우저에 전송하여 웹 브라우저 출력
- 사용자와의 상호작용을 위한 인터페이스 표시 영역

### Controller
- 일종의 조정자
- 클라이언트의 요청을 받았을 때, 그 요청에 대해 실제 업무를 수행하는 모델 컴포넌트 호출
- 클라이언트가 보낸 데이터가 있다면, 모델에 전달하기 쉽게 데이터 가공
- 모델이 업무를 마치면 그 결과를 뷰에게 전달

![MVC](https://velog.velcdn.com/images/hanhs4544/post/1323f44a-760d-46c4-be48-b11f54672c5e/image.png)

### MVC 패턴을 이용하는 이유는?
- 서로 분리되어 각자의 역할에 집중할 수 있게 개발이 됨
- 유지보수성, 애플리케이션의 확장성, 유연성 증가
- 중복코딩 사라짐
- 가장 널리 사용되고 있는 패턴이며, 가장 단순함

### MVC 패턴의 단점
- View와 Model 사이의 의존성이 높음 -> 애플리케이션이 커질수록 복잡하고 유지보수 어렵게 될 수 있음

# 2. MVVM
## MVVM이란?
- Model + View + View Model

### View Model
- View를 표현하기 위해 만든 View를 위한 Model
- View와는 Binding을 하여 연결 후 View에게서 액션을 받고 또한 View를 업데이트함
ex) textView에 보여줄 내용을 담당하는 함수 등, View에서 변화가 일어나는 ViewController의 역할 담당

### 동작 과정
1. View에 입력이 들어오면 ViewModel에게 명령
2. ViewModel은 필요한 데이터를 Model에게 요청
3. Model은 ViewModel에게 요청된 데이터 응답
4. ViewModel은 응답 받은 데이터 가공하여 저장
5. View는 ViewModel과의 Data Binding으로 인해 자동 갱신

![MVVM 패턴](https://velog.velcdn.com/images/hanhs4544/post/d678a418-37c5-4c97-b508-b2bb775ac143/image.png)



### MVVM 패턴의 장점
- View와 Model의 독립성 유지 가능 -> 효율적인 유닛 테스트 가능
- View와 ViewModel을 바인딩하기 때문에 코드의 양 감소

### MVVM 패턴의 단점
- 간단한 UI에서 오히려 ViewModel 설계시 어려울 수 있음
- 데이터 바인딩 필수적으로 요구
- 복잡해질수록 Controller처럼 ViewModel이 비대해짐
- 표준화된 틀이 존재하지 않아 사람마다 이해가 다름

### Data Binding
- Model과 UI 요소간의 싱크를 맞춰주는 것
- View와 로직이 분리되어 있어도 한 쪽이 바뀌면 다른 쪽도 업데이트가 이루어져 **데이터의 일관성** 유지 가능


# 3. MVP
## MVP란?
- Model + View + Presenter

### Presenter
- View에서 요청한 정보로 Model을 가공하여 View에 전달

### 동작 과정
1. View로 입력이 들어옴
2. View는 데이터를 Presenter에 요청
3. Presenter는 Model에게 데이터 요청
4. Model은 Presenter에서 요청받은 데이터 응답
5. Presenter는 View에게 데이터 응답
6. View는 Presenter가 응답한 데이터를 이용하여 화면에 나타냄

![MVP패턴](https://velog.velcdn.com/images/hanhs4544/post/c1f99dae-4c57-430c-9174-a1343ff08a27/image.png)

### MVP 패턴의 장점
- View와 Model의 의존성이 없음

### MVP 패턴의 단점
- View와 Model사이의 의존성은 해결되었으나, View와 Presenter사이의 의존성이 높아짐
- 애플리케이션이 복잡해질 수록 View와 Presenter 사이의 의존성이 강해지며 Presenter가 거대해짐
- View와 Presenter가 1:1 관계를 유지해야하기에 Presenter 재사용 불가

----------------



```
참고자료
https://m.blog.naver.com/jhc9639/220967034588
https://asfirstalways.tistory.com/180
https://beomy.tistory.com/43
https://jhtop0419.tistory.com/m/21
https://velog.io/@seokgyuhong/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98-%ED%8C%A8%ED%84%B4-MVP
```
