package com.example.demo.APITest;


import com.example.demo.dog.controller.DogController;
import com.example.demo.dog.service.DogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.thymeleaf.engine.IterationStatusVar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@WebMvcTest(DogController.class)
public class DogServiceApiTest {



    @Autowired
    MockMvc mockMvc;

    @MockBean
    DogService mockdogService;

    @MockBean
    EnableJpaAuditing enableJpaAuditing;


    @Test
    public void  강아지_TEST() throws  Exception{
        when(mockdogService.getName()).thenReturn("demo");
       mockMvc.perform(get("/dogtest"))
               .andExpect(content().string("Hellodemo"));
    }


}
