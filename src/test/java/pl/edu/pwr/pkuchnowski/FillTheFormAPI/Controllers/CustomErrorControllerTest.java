package pl.edu.pwr.pkuchnowski.FillTheFormAPI.Controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomErrorController.class)
class CustomErrorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomErrorController customErrorController;

    @Test
    void contextLoads() {
        assertNotNull(customErrorController);
    }

    @Test
    void testHandleErrorRedirect() throws Exception {
        this.mockMvc.perform(get("/error"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void testIfErrorPageExists() throws Exception {
        this.mockMvc.perform(get("/404.html"))
                .andExpect(status().isOk());
    }

    @Test
    void testIfErrorPageContainsLinkToJavadoc() throws Exception {
        this.mockMvc.perform(get("/404.html"))
                .andExpect(content().string(containsString("href=\"/javadoc\"")));
    }
}