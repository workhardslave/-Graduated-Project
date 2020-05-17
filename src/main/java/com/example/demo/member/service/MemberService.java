package com.example.demo.member.service;

import com.example.demo.member.dao.MemberRepository;
import com.example.demo.member.vo.Member;
import com.example.demo.member.vo.MemberSaveRequestDto;
import com.example.demo.member.vo.MemberUpdateRequestDto;


import com.example.demo.member.vo.MemberResponseDto;
import com.example.demo.member.vo.Role;

import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private Logger logger = LoggerFactory.getLogger(MemberService.class);


    //회원가입 아이디 중복체크
    @Transactional
    public void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findEmailCheck(member.getEmail());
        if (findMember!=null) {
            throw new IllegalStateException("회원가입된 사람입니다.");
        }
    }

    @Transactional
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }


    //회원가입
    @Transactional
    public Long SignUp(MemberSaveRequestDto memberDto) {
        validateDuplicateMember(memberDto.toEntity());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        memberDto.setRole(Role.GUEST);
        // memberDto.setRole(Role.ADMIN);
        return memberRepository.save(memberDto.toEntity()).getId();

    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Member userEntityWrapper = memberRepository.findEmailCheck(userEmail); //이메일 값 반환
        logger.info("여기까지?");
        logger.info(userEntityWrapper.getEmail());
        logger.info(userEntityWrapper.getRole().getValue());
        logger.info(userEntityWrapper.getPassword());
        if(userEntityWrapper == null ){
            throw new UsernameNotFoundException("User not authorized.");
        }

        GrantedAuthority authority = new SimpleGrantedAuthority(userEntityWrapper.getRole().getValue());
        UserDetails userDetails = (UserDetails)new User(userEntityWrapper.getEmail(),
                userEntityWrapper.getPassword(), Arrays.asList(authority));
        logger.info(userDetails.getPassword());
        return userDetails;
    }

    // 회원 정보수정
    @Transactional
    public Long update(Long id, MemberUpdateRequestDto requestDto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id=" + id));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePwd = passwordEncoder.encode(requestDto.getPassword());
        member.update(encodePwd, requestDto.getCity(), requestDto.getStreet(), requestDto.getZipcode(), requestDto.getPhone());


        return id;
    }

    //수정 페이지
    @Transactional(readOnly = true)
    public MemberResponseDto findById(Long id) {
        Member entity = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id=" + id));

        return new MemberResponseDto(entity);
    }

    //삭제 api
    @Transactional
    public void delete (Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        memberRepository.delete(member);
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAllDesc() {
        return memberRepository.findAllDesc().stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }


}
