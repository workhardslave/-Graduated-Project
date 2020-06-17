package com.example.demo.hospital.service;


import com.example.demo.hospital.repository.HospitalRepository;
import com.example.demo.hospital.vo.Hospital;
import com.example.demo.hospital.vo.HospitalResponseDto;
import com.example.demo.hospital.vo.HospitalSaveRequestDto;
import com.example.demo.member.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    // 관리자, 전체 병원 조회
    @Transactional(readOnly = true)
    public List<HospitalResponseDto> findAllDesc() {
        return hospitalRepository.findAllDesc().stream()
                .map(HospitalResponseDto::new)
                .collect(Collectors.toList());
    }

    // 수의사, 병원 등록
    @Transactional
    public Long regHospital(HospitalSaveRequestDto hosDto, Long id) {
        memberRepository.InsertUpdateHospital(hosDto, id);

        return hospitalRepository.save(hosDto.toEntity()).getId();
    }

    // 수의사, 병원 삭제
    @Transactional
    public void deleteHospital(Long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 동물병원이 이미 삭제되어 있습니다. id=" +id));

        hospitalRepository.delete(hospital);
    }

    public HospitalResponseDto findById(Long id) {
        Hospital entity = hospitalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 동물병원이 없습니다. id=" + id));

        return new HospitalResponseDto(entity);
    }

    /**
     * https://advenoh.tistory.com/15 : Optional 이해
     */
}
