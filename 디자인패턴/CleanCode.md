# 클린코드
## 클린코드란?
- 코드의 스타일은 개발자마다 모두 다름
- 개발은 혼자하는 것이 아님
- 협업할 때 코드 스타일의 통일이 필요함
- 좋은 코드 스타일의 척도가 될만한 레퍼런스가 있음
- 아래는 로버트 C. 마틴의 클린코드(Clean Code) 책에 나오는 좋은 코드의 척도임

### 저자가 생각하는 깨끗한 코드는
- 보기에 즐거운 코드이다.
- 세세한 사항까지 꼼꼼하게 처리하는 코드다.
- 한 가지를 잘하는 코드다.
- 설계자의 의도를 숨기지 않는다.
- 단순하고 직접적이다.
- 잘 쓴 문장처럼 읽힌다.
- 다른 사람이 고치기 쉬운 코드이다.
- 주의 깊게 짰다는 느낌을 준다.

### 예시
```java
public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int num = Integer.parseInt(br.readLine());
    int[] alpa = new int[26];
    String before = "";
    int cnt = 0;
    boolean flag = true;
    for(int i = 0; i<num; i++) {
        String word = br.readLine();
        for(int j = 0; j<word.length(); j++) {
            alpa[(int)word.split("")[j].charAt(0) - 97]++;
            if(alpa[(int)word.split("")[j].charAt(0) - 97] > 1) {
                if(!before.equals(word.split("")[j])) {
                    flag = false;
                    break;
                }
            }
            before = word.split("")[j];
        }
        if(flag) {
            cnt++;
        }
        before = "";
        flag = true;
        alpa = new int[26];
    }
    System.out.println(cnt);
}
```

<br>

```java
public static void main(String []args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int result = 0;
    int t = Integer.parseInt(br.readLine());

    for (int i = 0; i < t; i++) {
        int[] alphabets = new int[26]; // 'a' = 97
        boolean isGroupWord = true; 
        String word = br.readLine();
        char prev = word.charAt(0);
        char curr;
        alphabets[prev-97]++;

        for (int j = 1; j < word.length(); j++) {
            curr = word.charAt(j);
            if(curr != prev) {
                if(alphabets[curr-97] > 0) {
                    isGroupWord = false;
                    break;
                }
                alphabets[curr-97]++;
            }
            prev = curr;
        }
        if(isGroupWord)
            result++;
    }

    System.out.println(result);
}
```

### 의미있는 이름
- 의도를 분명히 밝혀라
    - 변수 (혹은 함수나 클래스)의 존재 이유는?
    - 수행 기능은?
    - 사용 방법은?
    - 따로 주석이 필요하면 의도를 분명히 드러내지 못한 것이다.
```java
// 1
int d //경과 시간(단위: 날짜)

// 2
int elapsedTimeInDays;
```

<br>

### 그릇된 정보를 피하라
- 널리 쓰이는 의미가 있는 단어를 다른 의미로 사용하면 안된다.
- 자료구조를 이름에 사용하는 것은 좋지 않다. (헝가리안 표기법)
- 일관성있는 표기법을 사용하라
```java

// 1
int testCnt;
int result_cnt; //카멜케이스와 스네이크 케이스를 혼용하고 있음

// 2
int[] paperList;   //데이터타입은 배열이지만 이름에 List가 붙어서 혼돈을 줌 'papers' 정도가 적당함

```

<br>

### 의미 있게 구분하라
- 연속된 숫자를 붙이거나 불필요한 용어를 붙이는 건 좋지 못하다.
- 서로 구분되는 용어를 사용하라 (맨 아래 '이름짓기 어려운 이유'라는 영상 2분56초쯤에 예시가 나옴)

<br>

### 검색하기 쉬운 이름을 사용하라
- 긴 이름이 짧은 이름보다 좋다.
- 이름 길이는 범위 크기에 비례해야 한다.

<br>

## 함수

### 좋은 함수 만드는 규칙
- 작게 만들어라
- 한 가지만 해라
- 함수당 추상화 수준은 하나로
- switch문은 최대한 피해라
- 서술적인 이름을 사용하라
- 함수 인수는 최대한 적게 하라
- 플레그 인수는 사용하지 마라(한가지만 하라는 원칙에 유배된다.)
- 부수효과를 일으키지 마라
- 출력 인수는 가급적 피해라
- 명령과 조회를 분리하라
- 오류코드보다 예외를 사용하라
- try/catch 블록은 별도의 함수로 뽑아내라

<br>

## 주석
- 주석은 필요악
- 주석은 나쁜코드를 보완하지 못한다
- 코드의 의도를 표현하라
- 좋은 주석
    - 법적인 주석 - 저작권 정보, 소유권 정보 등
    - 정보를 제공하는 주석
    - 의도를 설명하는 주석
    - 의미를 명료하게 밝히는 주석
    - 결과를 경고하는 주석
    - TODO 주석 - 앞으로 할일을 남기는 주석
    - 중요성을 강조하는 주석
- 나쁜 주석
    - 주절거리는 주석
    - 같은 이야기 반복하는 주석
    - 오해할 여지가 있는 주석
    - 의무적으로 다는 주석
    - 이력을 기록하는 주석
    - 있으나 마나 한 주석
    - 함수나 변수로 표현할 수 있는 주석
    - 위치를 표시하는 주석
    - 닫는 괄호에 다는 주석
    - 공로를 돌리거나 저자를 표시하는 주석
    - 주석으로 처리한 코드
    - HTML 주석
    - 전역정보를 갖는 주석
    - 너무 많은 정보를 갖는 주석

<br>

## 레퍼런스는 참고용
- 위의 내용은 모든 상황에 적용해야하는 절대적인 규칙이 아니다.
- 회사마다 다르고 팀마다 다르게 적용할 수 있다.

<br>

## 봐두면 좋은 유튜브 영상
- [이름짓기가 어려운 이유 - 판교뚜벅초](https://youtu.be/xXYQDzkydws)

- [깨끗한 코드를 위한 5가지 팁 - 노마드코더](https://youtu.be/Jz8Sx1XYb04)

- [코딩 잘하는 팁 세가지 - 드림코딩](https://youtu.be/jafa3cqoAVM)