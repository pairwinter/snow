package com.pairwinter.snow.web.test.contoller;

import com.pairwinter.snow.web.test.AbstractControllerTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by damon on 9/4/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ExceptionControllerTest extends AbstractControllerTest{

    @Before
    public void setUp(){
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void getException() throws Exception{
        this.mockMvc.perform(
                get("/exception/getException")
//                .header("X-Requested-With","XMLHttpRequest")
//                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error").value(true))
                ;
    }
}
