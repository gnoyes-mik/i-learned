package me.gnoyes.springexample.order.service;

import me.gnoyes.springexample.AppConfig;
import me.gnoyes.springexample.member.constants.Grade;
import me.gnoyes.springexample.member.model.Member;
import me.gnoyes.springexample.member.service.MemberService;
import me.gnoyes.springexample.order.model.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void init() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        // given
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);


        // when
        Order order = orderService.createOrder(member.getId(), member.getName(), 5000);

        // then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
        System.out.println(order);
    }

}