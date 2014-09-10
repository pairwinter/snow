package com.pairwinter.snow.web.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by damon on 9/9/14.
 */
@WebAppConfiguration
@ContextConfiguration({
        "file:portal/portal-webapp/src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "classpath*:springxml/*.xml"
})
public class AbstractControllerTest {
    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;
}
