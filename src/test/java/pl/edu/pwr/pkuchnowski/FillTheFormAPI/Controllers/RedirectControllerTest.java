package pl.edu.pwr.pkuchnowski.FillTheFormAPI.Controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RedirectController.class)
class RedirectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RedirectController redirectController;

    @Test
    void contextLoads() {
        assertNotNull(redirectController);
    }

    @Test
    void testRedirectToJavadoc() throws Exception {
        this.mockMvc.perform(get("/javadoc"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void testRedirectToJavadocWithSlash() throws Exception {
        this.mockMvc.perform(get("/javadoc/"))
                .andExpect(status().is3xxRedirection());

    }

    @Test
    void testIfJavaDocPageExists() throws Exception {
        this.mockMvc.perform(get("/javadoc/index.html"))
                .andExpect(status().isOk());
    }
}