package com.example.demo.hospital.controller;

import com.example.demo.hospital.service.HospitalService;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.service.MemberService;
import com.example.demo.member.vo.Member;
import com.example.demo.reserve.repository.ReserveRepository;
import com.example.demo.reserve.service.ReserveService;
import com.example.demo.reserve.vo.ReserveUpdateRequestDto;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Required;
import org.springframework.session.FindByIndexNameSessionRepository;
=======
>>>>>>> 1d7194144d484389004dbc8c2475dc55d4916830
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class HospitalApiController {

    private final HospitalService hospitalService;
    private final ReserveService reserveService;

    private final MemberRepository memberRepository;

    // 수의사, 동물병원 삭제 API
    @DeleteMapping("/api/vet/hospital/delete/{hospital_id}")
    public Long deleteVetHospital(@PathVariable Long hospital_id, Principal principal) {

        Member member = memberRepository.findEmailCheck(principal.getName());

        member.regHospital(null);
//        member.setHospital(null);                       // JPA에서 알아서 감지해서 null 값 세팅
        hospitalService.deleteHospital(hospital_id);

        return hospital_id;
    }

    // 수의사, 동물병원 예약 수정 API
    @PutMapping("/api/vet/hospital/reservation/{reserve_id}")
    public Long updateVetHospitalReserve(@PathVariable Long reserve_id, @RequestBody ReserveUpdateRequestDto requestDto) {
        return reserveService.update(reserve_id, requestDto);
    }

<<<<<<< HEAD
    // 병원등록 API
    @PostMapping(value = "/api/hospital/signup")
    public Long createHospital(@RequestBody HospitalSaveRequestDto Dto, Principal principal) {

        Member member = memberRepository.findEmailCheck(principal.getName());

        if(member.getHospital() != null){
            throw new IllegalStateException("병원등록은 하나만 됩니다.");
        }

        HospitalSaveRequestDto hospital = new HospitalSaveRequestDto();

        Long id = hospitalService.reg(hospital.builder()
                .name(Dto.getName())
                .address(Dto.getAddress())
                .tel(Dto.getTel())
                .member(member)
                .build(), member);


        return id;
    }

=======
    // 수의사, 동물병원 예약 삭제 API
    @DeleteMapping("/api/vet/hospital/reservation/delete/{id}")
    public Long delete(@PathVariable Long id) {
        reserveService.delete(id);
        return id;
    }
>>>>>>> 1d7194144d484389004dbc8c2475dc55d4916830
}
