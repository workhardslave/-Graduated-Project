package com.example.demo.reserve.controller;

import com.example.demo.reserve.service.ReserveService;
import com.example.demo.reserve.vo.ReserveUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReserveControllerApi {

    private final ReserveService reserveService;

    // 병원 예약 정보 수정 API
    @PutMapping("/api/member/reserve/settings/{id}")
    public Long ReserveUpdateForm(@PathVariable Long id, @RequestBody ReserveUpdateRequestDto requestDto) {

        return reserveService.update(id, requestDto);
    }


    // 병원 예약 정보 삭제 API
    @DeleteMapping("/api/member/reserve/delete/{id}")
    public Long delete(@PathVariable Long id) {
        reserveService.delete(id);
        return id;
    }
}
