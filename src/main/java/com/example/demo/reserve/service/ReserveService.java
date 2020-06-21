package com.example.demo.reserve.service;

import com.example.demo.hospital.repository.HospitalRepository;
import com.example.demo.hospital.vo.Hospital;
import com.example.demo.member.vo.Member;
import com.example.demo.reserve.repository.ReserveRepository;
import com.example.demo.reserve.vo.Reserve;
import com.example.demo.reserve.vo.ReserveResponseDto;
import com.example.demo.reserve.vo.ReserveSaveRequestDto;
import com.example.demo.reserve.vo.ReserveUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReserveService {

    private final ReserveRepository reserveRepository;
    private final HospitalRepository hospitalRepository;


    //사용자가 본인의 병원예약정보조회
    @Transactional(readOnly = true)
    public List<ReserveResponseDto> findAllDesc(Member member) {
        return reserveRepository.findAllMemberDesc(member).stream()
                .map(ReserveResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long save(ReserveSaveRequestDto Dto) {
        return reserveRepository.save(Dto.toEntity()).getId();
    }

    //관리자가 모든 예약정보 조회
    @Transactional(readOnly = true)
    public List<ReserveResponseDto> findAll() {
        return reserveRepository.findAll().stream()
                .map(ReserveResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReserveResponseDto> findAllHospital(Hospital hospital) {
        return reserveRepository.findAllReserveDesc(hospital).stream()
                .map(ReserveResponseDto::new)
                .collect(Collectors.toList());
    }

    //사용자가 본인의 병원예약정보수정 홈페이지 GET
    @Transactional(readOnly = true)
    public ReserveResponseDto findById(Long id) {
        Reserve entity = reserveRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 예약이 없습니다. id=" + id));
        return new ReserveResponseDto(entity);
    }

    //사용자가 본인의 병원예약정보수정 PUT
    @Transactional
    public Long update(Long id, ReserveUpdateRequestDto requestDto) {
        Reserve reserve = reserveRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 예약이 없습니다. id=" + id));

        reserve.update(requestDto.getDate(), requestDto.getDescription());

        return id;
    }

    //사용자가 본인의 병원예약정보삭제 DELETE
    @Transactional
    public void delete(Long id) {
        Reserve reserve = reserveRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 예약이 없습니다. id=" + id));
        reserveRepository.delete(reserve);
    }
    @Transactional
    public void delete_member(Member member) {
        List<Reserve> reserve = reserveRepository.findAllMemberDesc(member);
        if(reserve != null) {
            for (Reserve re : reserve) {
                reserveRepository.delete(re);
            }
        }
    }

    // 사용자 병원예약 POST
    @Transactional
    public Long Reserve(ReserveSaveRequestDto reserveDto,Member member) {
        Hospital hospital =  hospitalRepository.findname(reserveDto.getName());
        System.out.println("정보 들어오는지 확인 " + hospital.getName());
        reserveDto.Reserve_Hospital(hospital);
        reserveDto.Reserve_Member(member);
        return reserveRepository.save(reserveDto.toEntity()).getId();
    }
}
