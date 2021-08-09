package me.gnoyes.springexample.member.service;

import me.gnoyes.springexample.member.model.Member;

public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);
}
