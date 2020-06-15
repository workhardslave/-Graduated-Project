package com.example.demo.hospital.service;


import com.example.demo.hospital.repository.HospitalRepository;
import com.example.demo.hospital.vo.Hospital;
import com.example.demo.hospital.vo.HospitalResponseDto;
import com.example.demo.hospital.vo.HospitalSaveRequestDto;
import com.example.demo.member.vo.Member;
import com.example.demo.member.vo.MemberResponseDto;
import com.example.demo.member.vo.MemberSaveRequestDto;
import com.example.demo.member.vo.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    //관리자 전체병원조회
    @Transactional(readOnly = true)
    public List<HospitalResponseDto> findAllDesc() {
        return hospitalRepository.findAllDesc().stream()
                .map(HospitalResponseDto::new)
                .collect(Collectors.toList());
    }

    //병원등록
    @Transactional
    public Long reg(HospitalSaveRequestDto hosDto) {

        return hospitalRepository.save(hosDto.toEntity()).getId();
    }

    //병원삭제
    @Transactional
    public void delete(Long id) {
        Hospital hospital = hospitalRepository.findOne(id);
        hospitalRepository.delete(hospital);
    }
}
