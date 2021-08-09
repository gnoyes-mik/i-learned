package me.gnoyes.springexample.member.repository;

import me.gnoyes.springexample.member.model.Member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long id);
}
