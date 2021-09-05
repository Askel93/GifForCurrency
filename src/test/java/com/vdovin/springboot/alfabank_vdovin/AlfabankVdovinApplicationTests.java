package com.vdovin.springboot.alfabank_vdovin;

import com.vdovin.springboot.alfabank_vdovin.controller.CurrencyController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AlfabankVdovinApplicationTests {

    @Autowired
    CurrencyController currencyController;

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {
        assertThat(currencyController).isNotNull();
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/picture-for-currency?code=EUR"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("image/gif"));
    }

}
