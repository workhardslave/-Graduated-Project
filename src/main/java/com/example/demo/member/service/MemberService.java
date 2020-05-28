package com.example.demo.member.service;

import com.example.demo.member.dao.MemberRepository;
import com.example.demo.member.vo.Member;
import com.example.demo.member.vo.MemberSaveRequestDto;
import com.example.demo.member.vo.MemberUpdateRequestDto;


import com.example.demo.member.vo.MemberResponseDto;
import com.example.demo.member.vo.Role;

import net.minidev.json.JSONObject;
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

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private Logger logger = LoggerFactory.getLogger(MemberService.class);


    //회원가입 아이디 중복체크
    @Transactional
    public int validateDuplicateMember(String user_email) {
//        String st = user_email.substring()
        String value = user_email;
        value = value.substring(1,value.length()-1);
        HashMap<String, String> hashMap = new HashMap<>();

        String[] entry = value.split(":");
//        System.out.println("키값확인0"+entry[0]);
//        System.out.println("키값확인1"+entry[1]);
        hashMap.put(entry[0].trim(), entry[1].trim());
//        System.out.println("맵값확인"+hashMap.values().toString());
        String value2 = hashMap.values().toString().substring(2, hashMap.values().toString().length()-2);
//        System.out.println("맵값확인1"+value2);

//        System.out.println(hashMap.values());
//        System.out.println(hashMap.keySet());

        Member findMember = memberRepository.findEmailCheck(value2);

//        System.out.println(findMember.getEmail());
        if (findMember!=null) {
//            throw new IllegalStateException("회원가입된 사람입니다.");
            return 1;
        }else{
            return 0;
        }
    }

    //회원가입
    @Transactional
    public Long SignUp(MemberSaveRequestDto memberDto) {
//        validateDuplicateMember(memberDto.toEntity());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        memberDto.setRole(Role.GUEST);
        return memberRepository.save(memberDto.toEntity()).getId();

    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Member userEntityWrapper = memberRepository.findEmailCheck(userEmail); //이메일 값 반환
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
        System.out.println("입력한 : "+ requestDto.getPassword());
        System.out.println("본래 : " +member.getPassword());
        System.out.println(member.getPassword().getClass());
        System.out.println(requestDto.getPassword().getClass());

        if(!requestDto.getPassword().equals(member.getPassword())) {
            System.out.println("패스워드가다른경우 암호화 시킨후 저장한다.!!");
            String encodePwd = passwordEncoder.encode(requestDto.getPassword());
            member.update(encodePwd, requestDto.getCity(), requestDto.getStreet(), requestDto.getZipcode(), requestDto.getPhone());
        }
        else {
            System.out.println("패스워드가같은경우!!!");
            member.update(member.getPassword(), requestDto.getCity(), requestDto.getStreet(), requestDto.getZipcode(), requestDto.getPhone());
        }

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
