package com.example.demo.reserve.controller;

import com.example.demo.reserve.service.ReserveService;
import com.example.demo.reserve.vo.ReserveUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReserveControllerApi {

    private final ReserveService reserveService;

    // 사용자 병원 예약 정보 수정 API
    @PutMapping("/api/member/reserve/settings/{id}")
    public Long ReserveUpdateForm(@PathVariable Long id, @RequestBody ReserveUpdateRequestDto requestDto) {

        return reserveService.update(id, requestDto);
    }

    // 관리자 - > 사용자 병원 예약 정보 수정 API
    @PutMapping("/api/admin/reserve/settings/{id}")
    public Long ReserveUpdateFormAdmin(@PathVariable Long id, @RequestBody ReserveUpdateRequestDto requestDto) {

        return reserveService.update(id, requestDto);
    }


    // 사용자 병원 예약 정보 삭제 API
    @DeleteMapping("/api/member/reserve/delete/{id}")
    public Long delete(@PathVariable Long id) {
        reserveService.delete(id);
        return id;
    }

    // 관리자 - > 사용자 병원 예약 정보 삭제 API
    @DeleteMapping("/api/admin/reserve/delete/{id}")
    public Long deleteAdmin(@PathVariable Long id) {

        reserveService.delete(id);
        return id;
    }
}
