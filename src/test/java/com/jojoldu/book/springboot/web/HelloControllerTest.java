package com.jojoldu.book.springboot.web;


import org.apache.catalina.security.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class,//@repository, @Service, @Component는 스캔대상 아니므로
        excludeFilters = {//securityconfig 읽어도 이 안에서 사용하는 애들 못읽어서 얘 그냥 제외함.
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                        classes = SecurityConfig.class)
        })
public class HelloControllerTest {

    @Autowired
    //스프링 MVC test 시작점.
    //이 MockMvc 클래스를 통해 API 테스트가 가능해진다.
    private MockMvc mvc;

//    @WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())//결과 검증(200인지 체크)
                .andExpect(content().string(hello));//컨텐츠가 string hello반환하는지 체크.
    }
}
