# Spring Configuration

## 스프링의 핵심

- 값 주입을 스프링 프레임워크가 개발자 대신 처리

## 다양한 값 주입 방식

- 스프링은 다양한 방식으로 설정값 주입 가능
- 그래서 같은 설정값이 여러방식으로 설정이 되어있으면 우선수위에 따라 하나만 적용 됨

## 우선순위

- 낮은거부터 높은 순으로 나열
1. Default properties
    1. `SpringApplication.setDefaultProperties`
2. @PropertySource
    1. `@Configuration classes`
3. Config Data
    1. jar 패키지 안에 있는 application.properties, YAML
    2. jar 패키지 안에 있는 application-{profile}.properties, YAML
    3. jar 패키지 밖에 있는 application.properties, YAML
    4. jar 패키지 밖에 있는 application-{profile}.properties, YAML
4. RandomValuePropertySource
5. OS environment variables
6. Java System properties
7. JNDI attributes
8. ServletContext init parameters
9. ServletConfig init parameters
10. SPRING_APPLICATION_JSON
11. Command line arguments
12. @SpringBootTest
13. @TestPropertySource
14. Devtools global settings properties
    1. devtools를 사용하는 경우 `$HOME/.config/spring-boot`

## 빈 등록 방식

### XML파일로 등록

- 모든 Bean을 명시적으로 XML에 등록하는 방법
- 스프링 레거시부터 사용하던 고전적인 방법

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:p="http://www.springframework.org/schema/p"
    xsi:schemaLocation="http://www.springframework.org/schema/beans 
    http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
    http://www.springframework.org/schema/context 
    http://www.springframework.org/schema/context/spring-context-4.3.xsd">
    
    <!-- StringPrinter 클래스를 bean으로 등록 -->
    <bean id="stringPrinter" class="di.xml.StringPrinter"/>
    <!-- Console.Printer 클래스를 bean으로 등록 -->
    <bean id="consolePrinter" class="di.xml.ConsolePrinter"/>
    
    <!-- setter injection -->
    <!-- Hello 클래스를 bean으로 등록 -->
    <bean id="helloBean" class="di.xml.Hello" scope="singleton">
        <property name="name" value="상현"/>
        <property name="printer" ref="stringPrinter"/>
    </bean>
    
    <!-- constructor injection -->
    <bean id="helloConstructor" class="di.xml.Hello">
        <!-- constructor injection  -->
        <constructor-arg index="0" value="생성자" />
        <constructor-arg index="1" ref="consolePrinter" />
    </bean>
</beans>
```

- XML에 등록해주고 소스코드에서 빈 등록한 클래스를 선언해서 바로 사용하면 된다.

### 어노테이션으로 등록

- `@Configuration` 과 `@Bean` 등의 어노테이션을 이용해 소스코드상에서 빈을 등록하는 방법
- Spring 3.0부터는 기본적으로 어노테이션으로 빈 등록

```java
@Configuration
public class HelloJavaConfig {
    
    @Bean
    public Hello hello() {
        Hello hello = new Hello();
        hello.setName("Java컨피그");
        hello.setPrinter(printer());
        return hello;
    }
    
    @Bean
    @Qualifier("stringPrinter")
    public Printer printer() {
        Printer printer = new StringPrinter();
        return printer;
    }
    
    @Bean
    @Qualifier("consolePrinter")
    public Printer cPrinter() {
        Printer printer = new ConsolePrinter();
        return printer;
    }
}
```