package com.hwagae.market;

import com.hwagae.market.inquiry.InquiryService;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(HomeController.class) // Specify the controller class to be tested
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenAdminUser_whenAccessingAdminURL_thenRedirectToAdminMenu() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/adminMenu")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("views/admin/adminMenu"))
                .andExpect(MockMvcResultMatchers.content().string("관리자 페이지 입장 쑈쑈쑈"));
    }

    @Test
    void givenRegularUser_whenAccessingHomeURL_thenRedirectToUserIndex() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("views/user/index"))
                .andExpect(MockMvcResultMatchers.content().string("글 목록"));
    }
}