# JAVA Naming Convention
1. 대소문자가 구분되어야하고, 길이에 제한이 없다 <br>
    - 길이에 제한이 없다가 중요한 것 같다(변수명으로 의미부여를 명확하게 -> 주석사용 지양)
2. 예약어 사용 x <br>
    - java에서 사용하고있는 명들 (ex - class, import, char, break)
3. 숫자로 시작하면 안된다
4. 특수문자는 '_(언더바)' 와 '$' 만 허용한다.
5. 파스칼 표기법 (PascalCase)과 카멜 표기법(camelCase)를 사용한다.<br>
    - PascalCase : 모든 단어에서 첫 번째 문자는 대문자이며 나머지는 소문자이다.
    - camelCase : 최초에 사용된 단어를 제외한 첫 번째 문자가 대문자이며 나머지는 소문자이다.
6. 반의어는 반드시 대응하는 개념으로 사용해야 한다.

간단 정리
- [패키지](#패키지) : 소문자
- [클래스](#클래스) : 파스칼
- [상수](#상수) : 대문자, 밑줄
- [변수](#변수) : 소문자 or 카멜
- [매서드](#매서드) : 카멜


## 패키지
<hr>

> 패키지 이름은 소문자로 지정해야 합니다.
> - 각 점 뒤에는 영어 단어 1개만 있어야 한다.
> - 패키지 이름은 항상 단수 형식이어야 한다. (클래스는 필요하면 복수형 가능)
<br>

## 클래스
<hr>

> 클래스 이름은 DO, BO, DTO, VO 등 도메인 모델을 제외하고 UpperCamelCase로 명사로 작성해야 합니다.
> - 명사 혹은 명사구를 사용하라.(Customer, WikiPage, Account, AddressParser)
> - Manager, Processor, Data, Info와 같은 단어는 피하자
> - 동사는 사용하지 않는다.


|*예외사항|
|---|
| 테스트 클래스에는 Test로 끝나는 이름이 있습니다(예: HashIntegrationTest).<br>단일 클래스를 포함하는 경우 해당 이름은 해당 클래스의 이름에 테스트를 더한 것입니다(예: HashImplTest). |

### Constants
```Java
class MarcoPolo {...}
class UserDO {...}
class HtmlDTO {...}
class XmlService {...}
class TcpUdpDeal {...}
class TaPromotion {...}
```
<br>

## 상수
<hr>

> 상수는 내용이 완전히 변경 불가능하고 메서드에 부작용이 감지되지 않는 정적 최종 필드입니다. 예를 들면 프리미티브, 문자열, 변경할 수 없는 값 클래스 및 null로 설정된 모든 항목이 포함됩니다.<br>
> 인스턴스의 관찰 가능한 상태가 변경될 수 있는 경우 이는 상수가 아닙니다.<br>
> 상수를 사용하고 싶으면 static final을 사용합니다. 그리고 그 객체가 deeply immutable 이어야 합니다.(아닐경우 mutable으로 인한 side effect 발생 가능)
> 상수의 이름은 대문자와 밑줄로(UPPER_SNAKE_CASE) 작성 해야합니다.<br>
> - static final 필드여야 한다.
> - 절대 불변의 (immutable) 변수여야 한다.
> - 원시타입, 문자열, immutable type, immutable type의 collection 이 될 수 있다.
> - mutate로 선언되었지만 거의 변하지 않는다고 해도 상수가 아니다.
```Java
private static final String STOCK_OPTION_KEY = "STOCK_OPTION_KEY:"; 
```
|*예외사항|
|---|
| 상수가 아닌경우의 final은 일반 변수와 같이 CamelCase로 작성합니다. |

### Not constants
```Java
// Not constants
static String nonFinal = "non-final";
final String nonStatic = "non-static";
static final Set<String> mutableCollection = new HashSet<String>();
static final ImmutableSet<SomeMutableType> mutableElements = ImmutableSet.of(mutable);
static final ImmutableMap<String, SomeMutableType> mutableValues =
    ImmutableMap.of("Ed", mutableInstance, "Ann", mutableInstance2);
static final Logger logger = Logger.getLogger(MyClass.getName());
static final String[] nonEmptyArray = {"these", "can", "change"};
```
- 상수 final을 잘못 사용한 예)
- 상수는 static final을 사용해야 합니다.
- 상수는 mutalbe 이어서는 안됩니다.
- Logger 객체는 mutable입니다. 따라서 static final을 적용할 수 없습니다.
- 배열은 원소의 수정이 가능한 mutable 객체입니다. 그러므로 static final을 적용할 수 없습니다.
<br>

### Constants
```Java
// Constants
static final int NUMBER = 5;
static final ImmutableList<String> NAMES = ImmutableList.of("Ed", "Ann");
static final ImmutableMap<String, Integer> AGES = ImmutableMap.of("Ed", 35, "Ann", 32);
static final Joiner COMMA_JOINER = Joiner.on(','); // because Joiner is immutable
static final SomeMutableType[] EMPTY_ARRAY = {};
```

## 변수
<hr>

> 변수는 LowerCamelCase로 작성합니다.

|*예외사항|
|---|
| Boolean 변수를 정의할 때 접두사로 'is'를 추가하지 마십시오. 일부 Java 프레임워크에서 직렬화 예외가 발생할 수 있습니다.<br> Boolean isProcessFailed; 매서드 이름은 isProcessFailed()가 되면 RPC프레임워크는 변수 이름을 'processFailed'로 추론함 |

### Not constants
```Java
// Not constants
boolean isProcessFailed = false;        // Boolean 변수를 정의할 때 접두사로 'is'를 추가하면 일부 Java 프레임워크에서 직렬화 예외가 발생할 수 있다.
```
- 대응하는 매서드 이름은 isProcessFailed()가 되면 RPC프레임워크는 변수 이름을 'processFailed'로 추론함
### Constants
```Java
// Constants
boolean failed = false;
boolean flagFailed = false;
```
<br>

## 매서드
<hr>

> 메서드는 lowerCamelCase로 작성한다.
> - 동사나 동사구로 짓는다.(append, drawImage)
> - boolean값을 반환하는 메서드라면 보통 is나 has로 시작하고 명사나 명사구, 혹은 형용사로 기능하는 아무 단어나 구로 끝나도록 짓는다. (isDigit, isProbablePrime, isEmpty, isEnabled ..)
> - 반환 타입이 boolean이 아니거나 해당 인스턴스의 속성을 반환하는 메서드의 이름은 보통 명사, 명사구, 혹은 get으로 시작하는 동사구로 짓는다.(size, hashCode, getTime)
> - 객체의 타입을 바꿔서, 다른 타입의 또 다른 객체를 반환하는 인스턴스 메서드의 이름은 보통 toType 형태로 짓는다.(toString, toArray)
> - 객체의 값을 기본 타입 값으로 반환하는 메서드의 이름은 보통 typeValue 형태로 짓는다.(intValue)
> - 정적 팩터리의 이름은 다양하지만 from, of, valueOf, instance, getInstance, newInstance, getType, newType을 흔히 사용한다.

|*예외사항|
|---|
| 언터스코어(_)는 JUnit 테스트 메소드에서 의미를 구분할 때 쓰며, 구분되는 그 각각의 단어 또한 lowerCamelCase를 사용합니다.<br>(예: transferMoney_deductsFromSource). 테스트 방법의 이름을 지정하는 올바른 방법은 없습니다.|

### Constants
```Java
// Constants
public void getUserByName(){}       // getter/setter 관련하여 자바빈 규약이 있다.
public void setDisplayName(){}
public void initData(){}            // init는 데이터를 초기화하는 메서드 명에 쓰인다.
public boolean isNumber(){}         // is는 맞는지 틀린지 판단하는 메서드 명에 쓰인다.
public boolean hasData(){}          // has는 데이터를 가지고 있는지 확인하는 메서드 명에 쓰인다.
public boolean canOrder(){}         // can는 할 수 있는지 없는지 확인하는 메서드 명에 쓰인다.
public String toString(){}          // to는 객체를 다른 형태의 객체로 변환해주는 메서드 명에 쓰인다.
public void getUserByName(String name){}    // A-By-B는 B를 기준으로 A를 하겠다는 메소드명에 쓰인다.
public Element findElement(int number){}    // find는 데이터를 찾는 메서드 명에 쓰인다.
```