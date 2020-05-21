package com.example.demo.APITest;


import com.example.demo.dog.dto.Dog;
import com.example.demo.dog.dao.DogRepository;
import com.example.demo.dog.dto.DogSaveRequestDto;
import com.example.demo.dog.service.DogService;
import com.example.demo.member.dao.MemberRepository;
import com.example.demo.member.service.MemberService;
import com.example.demo.member.vo.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DogServiceApiTest {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Autowired
    private WebApplicationContext context;

    @MockBean
    DogService mockdogService;

    @MockBean
    DogRepository mockdogRepsitory;

    @After
    public void tearDown() throws Exception {
//        postsRepository.deleteAll();
    }

    @MockBean
    EnableJpaAuditing enableJpaAuditing;

    @Autowired
    DogService dogService;

    @Autowired
    DogRepository dogRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;



    private MockMvc mvc;

    /**
     * 사용자 기준
     */
    @Test //저장
    public void 반려견저장() throws Exception{
        Member member = memberRepository.findOne(2L);

        //given
        DogSaveRequestDto requestDto = DogSaveRequestDto.builder()
               .age(Age.생후12개월미만)
                .gender("숫컷")
                .value("포메")
                .birth("2001-1")
                .name("몽몽")
                .member(member)
                .build();

        String url = "http://localhost:" + port + "/member/dog/save";


        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Dog> all = dogRepository.findAll();
        assertThat(all.get(0).getAge()).isEqualTo(12);
        assertThat(all.get(0).getName()).isEqualTo("몽몽");

    }
    @Test//조회
    public void 반려견조회() throws Exception{

     ;

    }
    @Test //수정
    public void 반려견수정() throws Exception{

    }
    @Test //삭제
    public void 반려견삭제() throws Exception{

    }

}
