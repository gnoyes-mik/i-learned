package me.gnoyes.springexample.discountPolicy;

import me.gnoyes.springexample.member.model.Member;

public interface DiscountPolicy {

    int discount(Member member, int price);
}
