package com.example.demo.dog.repository;

import com.example.demo.config.security.Role;
import com.example.demo.dog.domain.Dog;
import com.example.demo.dog.dto.DogTypeCountDto;
import com.example.demo.member.domain.Member;
import com.example.demo.member.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DogRepositoryTest {

    @Autowired
    DogRepository dogRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 반려견등록_조회() throws Exception {
        // given
        Member member = memberRepository.save(Member.builder()
                .role(Role.GUEST)
                .build());
        dogRepository.save(Dog.builder()
                .member(member)
                .name("별이")
                .gender("암컷")
                .type("말티즈")
                .build());

        // when
        List<Dog> dogs = dogRepository.findAllDesc(member);

        // then
        assertEquals(dogs.get(0).getName(),"별이");
    }

    @Test
    public void 견종수() throws Exception {
        // given
        Member member = memberRepository.save(Member.builder()
                .role(Role.GUEST)
                .build());
        dogRepository.save(Dog.builder()
                .member(member)
                .name("별이")
                .gender("암컷")
                .type("말티즈")
                .build());

        // when
        List<DogTypeCountDto> dogCount = dogRepository.findDogCount();

        // then
        assertEquals(dogCount.get(0).getType(), "말티즈");
    }



}