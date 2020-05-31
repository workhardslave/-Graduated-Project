package com.example.demo.admin.service;

import com.example.demo.admin.dao.AdminRepository;
import com.example.demo.admin.vo.Admin;
import com.example.demo.member.vo.Member;
import com.example.demo.admin.vo.AdminResponseDto;
import com.example.demo.admin.vo.AdminUpdateRequestDto;

import com.example.demo.member.dao.MemberRepository;
import com.example.demo.member.vo.MemberResponseDto;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private Logger logger = LoggerFactory.getLogger(AdminService.class);

    // 관리자 정보수정
    @Transactional
    public Long updateAdmin(Long id, AdminUpdateRequestDto requestDto) {
        Member admin = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 관리자가 없습니다. id=" + id));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePwd = passwordEncoder.encode(requestDto.getPassword());
        admin.updateAdmin(encodePwd, requestDto.getPhone());

        return id;
    }

    @Transactional
    public MemberResponseDto findById(Long id) {
        Member entity = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 관리자가 없습니다. id=" + id));

        return new MemberResponseDto(entity);
    }

    // 관리자 탈퇴
    @Transactional
    public void delete(Long id) {
        Member admin = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 관리자가 없습니다. id=" + id));

        memberRepository.delete(admin);
    }

    @Transactional
    public List<MemberResponseDto> findAllDesc() {
        return memberRepository.findAllDesc().stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String adminEmail) throws UsernameNotFoundException {
        Member adminEntityWrapper = memberRepository.findEmailCheck(adminEmail);

        logger.info("여기까지?");
        logger.info(adminEntityWrapper.getEmail());
        logger.info(adminEntityWrapper.getRole().getValue());
        logger.info(adminEntityWrapper.getPassword());

        if(adminEntityWrapper == null) {
            throw new UsernameNotFoundException("Admin not authorized.");
        }

        GrantedAuthority authority = new SimpleGrantedAuthority(adminEntityWrapper.getRole().getValue());
        UserDetails adminDetails = (UserDetails)new User(adminEntityWrapper.getEmail(), adminEntityWrapper.getPassword(), Arrays.asList(authority));

        logger.info(adminDetails.getPassword());

        return adminDetails;
    }
}
