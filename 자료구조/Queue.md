# Queue

- Queue : 먼저 집어넣은 데이터가 먼저 나오는 **FIFO**(First In First Out)형식의 자료구조

- Java Collection에서 Queue는 인터페이스이다. 이를 구현하고 있는 우선순위큐 등을 사용할 수 있다.

- 연산

  - 가장 처음에 추가한 항목 = 가장 먼저 나오게 될 항목

  - java 라이브러리 Queue 관련 메서드

    - 수행이 실패했을 때 exception 발생 : add, element, remove

      - add : 해당 item을 queue에 삽입, 

        ​			삽입에 성공하면 true 반환, 공간이 없으면 예외(IllegalStateException)발생

      - element : queue의 head에 있는 item 삭제하지 않고 반환, queue가 비어있다면 예외발생

      - remove : queue의 head에 있는 item 삭제하고 반환, queue가 비어있다면 예외발생

    - 수행이 실패했을 때 null 또는 false 반환 : offer, peek, poll

      - offer : queue에 삽입, 성공 시 true 반환, 실패 시 false 반환
      - poll : 리스트의 첫 번째 항목 제거
      - peek : 큐에서 가장 위에 있는 항목 반환
      - isEmpty : 큐가 비어있을 때 true 반환

- 사용 방법

  - BFS
  - 우선순위가 같은 작업 예약(인쇄 대기열)
  - 선입 선출이 필요한 대기열(티켓 카운터)
  - 콜센터 고객 대기시간
  - 윈도우 시스템의 메시지 처리기
  - 프로세스 관리 : 프로세스는 일반적으로 여러개가 한번에 수행 -> 순서 필요
    - Job queue, Ready queue, Device queue
