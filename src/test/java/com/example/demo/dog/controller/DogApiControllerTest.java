package com.example.demo.dog.controller;


import com.example.demo.config.security.Role;
import com.example.demo.dog.domain.Dog;
import com.example.demo.dog.dto.DogUpdateRequestDto;
import com.example.demo.dog.repository.DogRepository;
import com.example.demo.member.domain.Member;
import com.example.demo.member.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class DogApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private DogRepository dogRepository;

    /* restTemplate */
    @Test
    public void 반려견수정_API() throws Exception {
        // given
        Member member = memberRepository.save(Member.builder()
                .role(Role.GUEST)
                .build());
        Dog dog = dogRepository.save(Dog.builder()
                .member(member)
                .name("별이")
                .build());
        Long dogId = dog.getId();
        DogUpdateRequestDto dto = DogUpdateRequestDto.builder()
                .name("달이")
                .build();

        String url = "http://localhost:" + port + "/api/member/dogs/settings/" + dogId;

        HttpEntity<DogUpdateRequestDto> requestEntity = new HttpEntity(dto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println("responseEntity.getStatusCode() = " + responseEntity.getStatusCode());
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        System.out.println("responseEntity.getBody() = " + responseEntity.getBody());

        List<Dog> dogList = dogRepository.findAllDesc(member);
        assertEquals(dogList.get(0).getName(), "달이");
        System.out.println("dogList.get(0).getName() = " + dogList.get(0).getName());
    }

    @Test(expected = AssertionError.class)
    public void 반려견삭제_API() throws Exception {
        // given
        Member member = memberRepository.save(Member.builder()
                .role(Role.GUEST)
                .build());
        Dog dog = dogRepository.save(Dog.builder()
                .member(member)
                .name("별이")
                .build());
        Long dogId = dog.getId();

        String url = "http://localhost:" + port + "/api/member/dogs/delete/" + dogId;

        // when
        restTemplate.delete(url);

        // then
        dogRepository.findById(dogId);
        fail("등록된 반려견이 없음");
    }
//
//    @Test
//    public void 반려견수정_WebFlux() throws Exception {
//        Member member = memberRepository.save(Member.builder()
//                .role(Role.GUEST)
//                .build());
//        Dog dog = dogRepository.save(Dog.builder()
//                .member(member)
//                .name("별이")
//                .build());
//        Long dogId = dog.getId();
//        DogUpdateRequestDto dto = DogUpdateRequestDto.builder()
//                .name("달이")
//                .build();
//
//
//        String url = "http://localhost:" + port + "/api/member/dogs/settings/" + dogId;
//
//        webTestClient.put().uri(url)
//                .body(dog, DogUpdateRequestDto.class)
//                .exchange()
//                .expectStatus()


}