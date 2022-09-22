# [Python] GIL (Global Interpreter Lock)

GIL을 이해하려면 먼저 Python 인터프리터란 것이 정확히 무엇인지 알아야 합니다.

## **1\. Python 인터프리터란?**

파이썬은 인터프리터에 의해 실행되는 스크립트 언어입니다.

인터프리터(interpreter)란 소스 코드를 처음부터 한 라인씩 차례대로 해석하며 실행하는 프로그램입니다.

그 프로그램의 구현체로는 여러 가지가 있을 수 있는데, 현재 Python 인터프리터의 표준 구현체로 받아들여지고 있는 것은 바로 CPython 입니다. CPython은 C 언어를 이용하여 구현한 Python 인터프리터 입니다.

<br>

## **2\. GIL (Global Interpreter Lock)**

> In CPython, the global interpreter lock, or GIL, is a mutex that protects access to Python objects, preventing multiple threads from executing Python bytecodes at once. This lock is necessary mainly because CPython's memory management is not thread-safe. (Python 위키에서 발췌)

해석하자면, Python의 객체들에 대한 접근을 보호하는 일종의 뮤텍스(Mutex)로서, 여러 개의 쓰레드가 파이썬 코드를 동시에 실행하지 못하도록 하는 것 이라고 합니다. 즉, 한 프로세스 내에서, Python 인터프리터는 한 시점에 하나의 쓰레드에 의해서만 실행될 수 있습니다. 멀티 쓰레딩이 불가능하다는 것은 아니지만, 원래 멀티 코어라면 멀티 쓰레딩 시에 여러 개의 쓰레드가 여러 코어 상에서 병렬(Parallel) 실행될 수 있는데, Python에서는 그러한 병렬 실행이 불가능하다는 것 입니다.

![image](https://user-images.githubusercontent.com/67899393/191700695-161f7ffb-a3f1-4b56-ba67-e0dc54ca5fa1.png)

<br>

## **3\. 왜 GIL을 사용하는가?**

멀티 쓰레딩시 병렬 실행이 불가능 한데, 왜 GIL을 사용해야 하는지는 Python 위키에서 GIL 정의에 해답이 있습니다. **GIL이 Python의 객체들에 대한 접근을 보호하는 뮤텍스** 라는 부분입니다.

Python에서 모든 것은 객체입니다. 각 객체는 참조 횟수(Reference Count)를 저장하는 필드를 가지고 있고 참조 될때 count 가 증가하고 참조가 끊어지면 count가 감소합니다. Python 에서의 GC는 이 참조 횟수가 0이 되면 객체를 메모리에서 삭제시키는 메커니즘으로 동작하고 있습니다.

```
여러 스레드가 하나의 공유 자원에 동시에 접근하면서 발생하는 문제를 race condition(경쟁 상태)이라 하며 이 문제를 해결하기 위해 뮤텍스 등을 사용합니다.
```

Python의 인터프리터 CPython은 C 언어를 이용하여 구현한 Python 인터프리터 입니다. 따라서 CPython의 메모리 관리 전략은 Thread Safte 하지 않아 Thread Safe를 위해 별도의 작업을 수행해야 race condition 이 발생하지 않습니다. 그래서 간단히 구현이 가능한 뮤텍스인 GIL이 사용됩니다.

<br>

## **4\. Python의 멀티스레딩은 무조건 느리다?**

Python의 멀티스레디은 GIL 이 적용되기 때문에 일반적으로 멀티스레드가 싱글스레드보다 느립니다. 하지만 sleep으로 멈추는 경우나  I/O 바운드 작업이라 요청을 걸고 대기해야 하는 경우 다른 스레드로 context switching하여 효율이 개선되어 싱글스레드 보다 멀티스르데의 성능이 더 좋을 수 있습니다.

<br>

## **결론**

결론적으로 GIL은 멀티 쓰레드에 개념이 잘 쓰이지 않았던 시절에 개발된 언어의 한계를 해결하깅 위해 도입된 개념으로 **안전**을 얻고 **성능**을 잃는 trade-off 정책입니다. (Python을 사용하려면 어쩔 수 없다.....)

이 GIL의 방해를 받지 않고 병렬 연산을 하기 위한 가장 대표적인 방법은 멀티 프로세싱을 사용하는 것입니다. 컨텍스트 스위칭 비용이 크다는 단점이 있지만 프로세스는 각자 독자적인 메모리를 가지기에 GIL의 영향을 받지 않습니다.  
  
이 외에도 ​CPython이 아닌 다른 인터프리터를 사용하는 방법 또한 가능합니다.
