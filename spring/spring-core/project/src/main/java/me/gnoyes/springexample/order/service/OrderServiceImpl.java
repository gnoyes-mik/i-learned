package me.gnoyes.springexample.order.service;

import me.gnoyes.springexample.discountPolicy.DiscountPolicy;
import me.gnoyes.springexample.member.model.Member;
import me.gnoyes.springexample.member.repository.MemberRepository;
import me.gnoyes.springexample.order.model.Order;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
