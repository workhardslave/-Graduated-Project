package com.example.demo.reserve.service;

import com.example.demo.member.vo.Member;
import com.example.demo.reserve.dao.ReserveRepository;
import com.example.demo.reserve.vo.ReserveResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class ReserveService {

    @Autowired
    ReserveRepository reserveRepository;

    //사용자가 본인의 예약정보조회
    @Transactional(readOnly = true)
    public List<ReserveResponseDto> findAllDesc(Member member) {
        return reserveRepository.findAllDesc(member).stream()
                .map(ReserveResponseDto::new)
                .collect(Collectors.toList());
    }
}
