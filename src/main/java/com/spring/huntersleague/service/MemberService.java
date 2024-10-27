package com.spring.huntersleague.service;

import com.spring.huntersleague.domain.Member;
import com.spring.huntersleague.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {


    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

     public List<Member> findAll() {
         return memberRepository.findAll();
     }

     public Optional<Member> findById(int id) {
         return memberRepository.findById(id);
     }

     public Member save(Member member) {
         return memberRepository.save(member);
     }

    public void deleteById(Long id) {
        memberRepository.deleteById(Math.toIntExact(id));
    }

}
