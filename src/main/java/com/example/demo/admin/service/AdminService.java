package com.example.demo.admin.service;

import com.example.demo.admin.dao.AdminRepository;
import com.example.demo.admin.vo.Admin;
import com.example.demo.admin.vo.AdminResponseDto;
import com.example.demo.admin.vo.AdminUpdateRequestDto;

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

    private final AdminRepository adminRepository;
    private Logger logger = LoggerFactory.getLogger(AdminService.class);

    // 관리자 정보수정
    @Transactional
    public Long update(Long id, AdminUpdateRequestDto requestDto) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 관리자가 없습니다. id=" + id));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePwd = passwordEncoder.encode(requestDto.getPassword());
        admin.update(encodePwd, requestDto.getPhone());

        return id;
    }

    @Transactional
    public AdminResponseDto findById(Long id) {
        Admin entity = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 관리자가 없습니다. id=" + id));

        return new AdminResponseDto(entity);
    }

    // 관리자 탈퇴
    @Transactional
    public void delete(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 관리자가 없습니다. id=" + id));
        adminRepository.delete(admin);
    }

    @Transactional
    public List<AdminResponseDto> findAllDesc() {
        return adminRepository.findAllDesc().stream()
                .map(AdminResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String adminEmail) throws UsernameNotFoundException {
        Admin adminEntityWrapper = adminRepository.findEmailCheck(adminEmail);
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
