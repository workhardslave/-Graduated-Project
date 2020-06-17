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
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("병원이 이미 삭제되어 있습니다. id=" +id));
        hospitalRepository.delete(hospital);
    }

    /**
     * https://advenoh.tistory.com/15 : Optional 이해
     */

    //수정페이지
    @Transactional
    public HospitalResponseDto findById(Long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 병원이 없습니다. id=" + id)); //null일경우
        return new HospitalResponseDto(hospital);

    }
}
