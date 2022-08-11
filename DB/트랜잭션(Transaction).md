# 트랜잭션(Transaction)

```
**트랜잭션(Transaction)** 이란, 데이터베이스의 상태를 변화시키기 위해서 수행하는 작업의 단위를 뜻합니다.

데이터베이스에서 상태 변화를 시킨다는 것은 질의어(SQL)를 이용하여 데이터베이스에 접근하는 것을 의미합니다.
```

-   SELECT
-   INSERT
-   DELETE
-   UPDATE

단, 작업의 단위는 질의어 한 문장이 아니라 많은 SQL 명령문들을 사람이 정하는 기준에 따라 정해집니다.

```
예시) 은행에서 A가 B에게 10,000원을 송금한다.

1.  A의 계좌에서 10,000원을 차감 : UPDATE 문을 사용해 A의 잔고를 변경
2.  B의 계좌에 10,000원을 추가 : UPDATE 문을 사용해 B의 잔고를 변경

작업 단위 : 출금 UPDATE문 + 입금 UPDATE문
```

## 트랜잭션의 특징

-   원자성 (Atomicity)
-   일관성 (Conosistency)
-   독립성 (Isolation)
-   지속성 (Durability)

#### **원자성 ( Atomicity )**

하나의 트랜잭션 작업이 그중에 일부분만 실행되거나 중단되지 않는것을 보장해주는 것을 말합니다.  
All or Noting 즉, 작업단위에 대해서 전체 성공 혹은 실패만을 보장하며 일부분만 작업하여 반영하지 않습니다.

#### **일관성 ( Consistency )**

트랜잭션 작업이 성공적으로 완료가 되더라도 작업 이전과 같이 같은 상태를 유지하는 것을 말합니다.  
예를들어 정수 타입의 컬럼에 문자열 값이 들어가지 않는것을 보장하여 줍니다.  
데이터는 미리 정의된 정책에 대해서만 수정이 가능하며 무결성원칙이 지켜지지 않는 작업은 바로 중단이 됩니다.

#### **격리성 ( Isolation )**

하나의 트랜잭션 작업이 수행되고 있을 때 다른 작업이 끼어들지 못하도록 보장해주는 것을 말합니다.  
원칙적으로는 트랜잭션이끼리는 서로 간섭을 할 수 없어야 하지만 성능 이슈들이 많아 가장 유연하게 설정이 가능한 제약 조건입니다.

#### **지속성 ( Durability )**

성공적으로 수행된 트랜잭션에 대해서 영구히(Persistent) 반영되어야 함을 말합니다.  
작업이 완료되어 COMMIT까지 된 작업은 시스템 문제나 DB 일관성 체크등을 하더라도 영구적으로 유지 되어야 합니다.  
전형적으로 모든 트랜잭션은 로그로 남고 시스템 장애 발생 전 상태로 되돌릴 수 있습니다.

<br>

## Isolation Level (격리 수준)

동시에 여러 트랜잭션이 처리될 때 특정 트랜잭션이 다른 트랜잭션에서 변경하거나 조회하는 데이터를 볼 수 있도록 혀용 할지 말지 결정하는 것(Lock)

실제로 ACID 원칙은 종종 지켜지지 않습니다. 왜냐하면 ACID 원칙을 지키려면 동시성이 매우 떨어지기 때문 입니다.

따라서 DB 엔진은 ACID 원칙을 희생하면서 동시성을 얻을 수 있는 방법을 제공합니다. 

Isolation 원칙을 덜 지키는 level을 사용할수록 문제가 발생할 가능성이 커지지만 더 높은 동시성을 얻을 수 있습니다.

DB 엔진은 Isolation Level에  따라 서로 다른 locking 전략을 취하며 level이 높아 질수록 더 많이, 더 빡빡하게 lock을 합니다.

### ANSI/ISO SQL standard 에서 정의한 isolation level

-   READ UNCOMMITTED
-   READ COMMITTED
-   REPEATABLE READ
-   SERIALIZABLE

#### **READ UNCOMMITTED (Level 0)**

-   각 트랜잭션에서의 변경 내용이 COMMIT이나 ROLLBACK 여부에 상관 없이 다른 트랜잭션에서 값을 읽을 수 있습니다.
-   정합성에 문제가 많은 격리 수준이기 때문에 사용하지 않는 것을 권장됩니다.
-   아래의 그림과 같이 Commit이 되지 않는 상태지만 Update된 값을 다른 트랜잭션에서 읽을 수 있습니다.

![image](https://user-images.githubusercontent.com/67899393/184124994-712c6df1-8e8a-41a7-861e-86bb76b87f3a.png)

**READ UNCOMMITTED의 문제**

-   **DIRTY READ** 현상 발생 (트랜잭션이 작업이 완료되지 않았는데도 다른 트랜젝션에서 볼 수 있게 되는 현상)

#### **READ COMMITTED (Level 1)**

-   대부분의 RDB 에서 기본적으로 사용되고 있는 격리 수준
-   Dirty Read 현상은 발생하지 않습니다.
-   실제 테이블 값을 가져오는 것이 아니라, Undo 영역의 백업된 레코드에서 값을 가져옵니다.

![image](https://user-images.githubusercontent.com/67899393/184125050-e388fcbe-b878-41b9-a109-7067610487dd.png)

**READ COMMITTED의 문제**

-   하나의 트랜잭션 안에서 SELECT를 수행 할 때마다 데이터가 동일하다는 보장을 해주지 않습니다.   
    (Non-REPEATABLE READ)

![image](https://user-images.githubusercontent.com/67899393/184125214-cb00c4d1-8992-48ad-8572-1a1606f48232.png)

#### **REPEATABLE READ (Level 2)**

-   MySQL에서는 트랜잭션마다 트랜잭션 ID를 부여하여 트랜잭션 ID보다 작은 트랜잭션 번호에서 변경한 것만 읽습니다.
-   Undo 공간에 백업해두고 실제 레코드 값을 변경합니다.
-   백업된 데이터는 불필요하다고 판단하는 시점에 주기적으로 삭제합니다.
-   Undo에 백업된 레코드가 많아지면 MySQL 서버의 처리 성능이 떨어질 수 있습니다.
-   이러한 변경방식은 MVCC(Multi Version Concurrency Control)라고 부릅니다.

![image](https://user-images.githubusercontent.com/67899393/184125250-462bb8ac-d3e8-4e81-801f-3701f56d083c.png)

**REPEATABLE READ의 문제**

-   PHANTOM READ 현상 발생  
    (다른 트랜잭션에서 수행한 변경 작업에 의해 레코드가 보였다가 안 보였다가 하는 현상)  
    이를 방지하기 위해서는 쓰기 잠금을 걸어야 한다.

![image](https://user-images.githubusercontent.com/67899393/184125290-b60134f3-2460-4bd8-8366-e91e9e95a446.png)

#### **SERIALIZABLE (Level 3)**

-   가장 단순한 격리 수준이지만 가장 엄격한 격리 수준입니다.
-   성능 측면에서는 동시 처리 성능이 가장 낮습니다.
-   SERIALIZABLE 에서는 PHANTOM READ 가 발새하지 않지만, DB 에서 거의 사용되지 않습니다.

![image](https://user-images.githubusercontent.com/67899393/184125376-04c75b31-a1a5-4b39-8d0a-c0bca30cbdd2.png)
