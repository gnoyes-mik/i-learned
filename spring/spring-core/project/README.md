# 스프링 핵심 원리 

> 🔔 이 프로젝트는 김영한님의 스프링 핵심 원리 강의를 보고 정리한 프로젝트입니다

## 비즈니스 요구사항과 설계

### 회원

- 회원을 가입하고 조회할 수 있다.
- 회원은 일반과 VIP 두 가지 등급이 있다.
- 회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다.(미확정)

### 주문과 할인 정책

- 회원은 상품을 주문할 수 있다.
- 회원 등급에 따라 할인 정책을 적용할 수 있다.
- 할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용해달라.(나중에 변경될 수 있음)
- 할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 미루고 싶다. 최악의 경우 할인을 적용하지 않을 수 도 있다.

<br>
요구사항을 보면 회원 데이터, 할인 정책 같은 부분은 지금 결정하기 어려운 부분이 있다. 그렇다고 이런 정책이 결정될 때 까지 개발을 무기한 기다릴 수 도 없다.
<br>

> 참고: 프로젝트 환경설정을 편리하게 하려고 스프링 부트를 사용한 것.<br>
> 지금은 스프링 없는 순수한 자바로만 개발을 진행한다.

## 초기 구현 단계의 문제점

- ex) MemberServiceImpl.java

```java
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // ...
}
```

- 역할과 구현을 충실하게 분리를 했다 → OK

- 다형성도 활용하고, 인터페이스와 구현 객체를 분리했다 → OK

- OCP, DIP와 같은 객체지향 설계 원칙을 준수했다 → 그렇게 보이지만 사실은 아니다

    - DIP : 멤버 서비스 클라이언트(MemberServiceImpl)은 인터페이스(MemberRepository)뿐만아니라 구현체(MemoryMemberRepository)도 의존하고 있다 → DIP 위반

    - OCP : 변경하지 않고 확장 가능해야하는데, 필연적으로 코드 수정이 필요하다

## 해결 방법

- 이 문제를 해결하려면 무언가가 클라이언트인 MemberServiceImpl에 MemberRepository의 구현 객체를 대신 `생성`하고 `주입`해주어야 한다

### 관심사의 분리

> 관심사를 분리하자 <br>
> 배우는 본인의 역할인 배역을 수행하는 것에만 집중해야 한다. <br>
> 디카프리오는 어떤 여자 주인공이 선택되더라도 똑같이 공연을 할 수 있어야 한다. <br>
> 공연을 구성하고, 담당 배우를 섭외하고, 역할에 맞는 배우를 지정하는 책임을 담당하는 별도의 공연 기획자가 나올시점이다.<br>
> 공연 기획자를 만들고, 배우와 공연 기획자의 책임을 확실히 분리하자.

<br>
즉, 어플리케이션의 전체 동작 방식을 구성(config)하기 위해, `구현 객체를 생성하고, 연결하는 책임`을 가지는 별도의 설정 클래스를 만들어야 한다
<br>

```java
// AppConfig.java
public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    // ...
}

// MemberServiceImpl.java
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    
    // ...
}
```
<br>

- MemberServiceImpl은 더이상 구현체를 의존하지 않으며, MemberRepository 인터페이스에 정의된 메소드만 알면 된다

- 의존 관계는 외부(AppConfig)에 온전히 맡긴다

- MemberRepository의 구현체가 바뀌더라도 외부(AppConfig)에서만 다르게 주입해주면 된다

- 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다


(계속)