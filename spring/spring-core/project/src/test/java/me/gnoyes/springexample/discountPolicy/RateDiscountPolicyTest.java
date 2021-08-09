package me.gnoyes.springexample.discountPolicy;

import me.gnoyes.springexample.member.constants.Grade;
import me.gnoyes.springexample.member.model.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다")
    void vip_OK() {
        // given
        Member member = new Member(1L, "VIP", Grade.VIP);

        // when
        int discountPrice = discountPolicy.discount(member, 10000);

        // then
        assertThat(discountPrice).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
    void vip_X() {
        // given
        Member member = new Member(1L, "BASIC", Grade.BASIC);

        // when
        int discountPrice = discountPolicy.discount(member, 10000);

        // then
        assertThat(discountPrice).isEqualTo(0);
    }

}