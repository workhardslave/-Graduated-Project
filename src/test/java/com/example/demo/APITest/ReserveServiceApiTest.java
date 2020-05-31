package com.example.demo.APITest;


import com.example.demo.member.vo.Member;
import com.example.demo.reserve.dao.ReserveRepository;
import com.example.demo.reserve.service.ReserveService;
import com.example.demo.reserve.vo.Reserve;
import com.example.demo.reserve.vo.ReserveResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReserveServiceApiTest {

    @Autowired
    private ReserveService reserveService;
    @Autowired
    private ReserveRepository reserveRepository;


    @Test
    public void 관리자_예약정보조회() throws Exception {
        List<ReserveResponseDto> reserveList = reserveService.findAll();
        System.out.println(reserveList.get(0).getAddress());

    }

}
