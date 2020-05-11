package com.example.demo.member.service;

import com.example.demo.member.dao.MemberRepository;
import com.example.demo.member.vo.Member;
import com.example.demo.member.dao.MemberSaveRequestDto;
import com.example.demo.member.dao.MemberUpdateRequestDto;


import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private Logger logger = LoggerFactory.getLogger(MemberService.class);

    @Transactional
    public Long SingUp(Member member) {
        logger.debug(member.getEmail());
        logger.debug(member.getName());
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //회원가입 아이디 중복체크
    @Transactional
    public void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findEmailCheck(member.getEmail());
        if (findMember!=null) {
            throw new IllegalStateException("회원가입된 사람입니다.");
        }
    }

    //아이디 패스워드 확인 후 로그인
    @Transactional
    public String Check_Login(Object email, Object password){
        Member member = memberRepository.findByEmailPassword(email, password);
        if (member == null) {
//            throw new IllegalStateException("아이디와 비밀번호를 다시 확인해 주세요.");
            return "NO";
        }

        return "YES";
    }


    //정보 수정
    @Transactional
    public void InfoUpdate(String email, MemberUpdateRequestDto requestDto){
        Member m = memberRepository.findEmailCheck(email);
        m.update(requestDto.getPassword());
    }


    //아이디찾기
    @Transactional(readOnly =  true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly =  true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


}
