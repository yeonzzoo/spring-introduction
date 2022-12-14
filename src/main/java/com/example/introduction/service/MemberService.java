package com.example.introduction.service;

import com.example.introduction.domain.Member;
import com.example.introduction.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public long join(Member member) {
        validateDuplicateMember(member);     // 중복되는 이름이 있는지 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> { // ifPresent() 만약에 null이 아니면
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 각각의 회원 조회
    public Optional<Member> findOne(long memberId) {
        return memberRepository.findById(memberId);
    }
}