//package com.example.demo.APITest;
//
//import com.example.demo.hospital.repository.HospitalRepository;
//import com.example.demo.hospital.vo.Hospital;
//import com.example.demo.member.repository.MemberRepository;
//import com.example.demo.member.service.MemberService;
//import com.example.demo.member.vo.Member;
//import com.example.demo.member.vo.MemberSaveRequestDto;
//import com.example.demo.member.vo.Role;
//import com.example.demo.member.domain.Address;
//import com.nimbusds.jose.util.JSONObjectUtils;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertEquals;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//@Transactional
//public class hospitalApiTest {
//
//
//    @Autowired
//    private MemberService memberService;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private HospitalRepository hospitalRepository;
//
//
//    @Test
//    public void 병원저장() throws Exception{
//
//
//
//        MemberSaveRequestDto member = new MemberSaveRequestDto();
//        memberService.SignUp(member.builder()
//                .name("hye")
//                .role(Role.GUEST)
//                .phone("101")
//                .email("yusa2@naver.com")
//                .birth("1995")
//                .password("12345")
//                .build());
//
//        Hospital hospital = new Hospital();
//
//        Member member2 = memberRepository.findEmailCheck("yusa2@naver.com");
//        hospitalRepository.save(hospital.builder()
//                .address("동네")
//                .name("나는야의사")
//                .tel("010")
//                .build());
////        assertThat(hospital.getName(), "나는야의사");
//
//        assertEquals(hospital, hospitalRepository.getOne(1L));
//
//
//
//    }
//
//    @Test
//    public void 병원삭제() throws Exception{
//        Hospital hospital = hospitalRepository.findById(1L)
//                .orElseThrow(()-> new IllegalArgumentException("병원정보 없음"));
//
//        System.out.println(hospital.getName());
//        //지울경우!
//        hospitalRepository.delete(hospital);
//    }
//
//
//
//
//}