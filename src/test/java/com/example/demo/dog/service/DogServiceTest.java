package com.example.demo.dog.service;

import com.example.demo.config.security.Role;
import com.example.demo.dog.dto.DogResponseDto;
import com.example.demo.dog.dto.DogSaveRequestDto;
import com.example.demo.dog.dto.DogUpdateRequestDto;
import com.example.demo.member.domain.Member;
import com.example.demo.member.repository.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DogServiceTest {

    private static Member member = new Member();
    private static Long savedId;

    @Autowired
    private DogService dogService;
    @Autowired
    private MemberRepository memberRepository;

    @Before
    public void 더미데이터추가() {
        member = memberRepository.save(Member.builder()
                .role(Role.GUEST)
                .build());

        // when
        savedId = dogService.dog_SignUp(DogSaveRequestDto.builder()
                .name("별이")
                .member(member)
                .build());
    }

//    @After
//    public void 실행후삭제() {
//        System.out.println("after---------------------------");
//        dogRepository.deleteAll();
//        memberRepository.deleteAll();
//        System.out.println("after2---------------------------");
//
//    }

    @Test
    public void 반려견_등록() throws Exception {
        // given


        // then
        List<DogResponseDto> dogs = dogService.findAllDesc(member);
        DogResponseDto dog = dogService.findById(savedId);
        System.out.println("dog.getName() = " + dog.getName());
        System.out.println("dogs.get(0).getName() = " + dogs.get(0).getName());
        assertEquals(dogs.get(0).getName(),"별이");
        assertEquals(dog.getName(),"별이");
    }
    
    @Test
    public void 반려견_정보수정() throws Exception {
        // given


        // when
        Long updatedId = dogService.update(savedId, DogUpdateRequestDto.builder()
                .name("달이")
                .build());

        // then
        List<DogResponseDto> dogs = dogService.findAllDesc(member);
        DogResponseDto dog = dogService.findById(updatedId);
        System.out.println("dog.getName() = " + dog.getName());
        System.out.println("dogs.get(0).getName() = " + dogs.get(0).getName());
        assertEquals(dogs.get(0).getName(),"달이");
        assertEquals(dog.getName(),"달이");

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void 반려견_정보삭제() throws Exception {
        // given

        // when
        dogService.delete(savedId);

        // then
        List<DogResponseDto> dogs = dogService.findAllDesc(member);
        assertEquals(dogs.get(0).getName(),"별이");
        fail("반려견이 없으니까 예외처리 발생");
    }
}