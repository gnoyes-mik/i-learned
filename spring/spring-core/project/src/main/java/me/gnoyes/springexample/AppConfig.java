package me.gnoyes.springexample;

import me.gnoyes.springexample.discountPolicy.DiscountPolicy;
import me.gnoyes.springexample.discountPolicy.RateDiscountPolicy;
import me.gnoyes.springexample.member.repository.MemberRepository;
import me.gnoyes.springexample.member.repository.MemoryMemberRepository;
import me.gnoyes.springexample.member.service.MemberService;
import me.gnoyes.springexample.member.service.MemberServiceImpl;
import me.gnoyes.springexample.order.service.OrderService;
import me.gnoyes.springexample.order.service.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
