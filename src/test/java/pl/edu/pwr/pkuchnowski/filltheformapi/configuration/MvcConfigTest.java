package pl.edu.pwr.pkuchnowski.filltheformapi.configuration;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MvcConfig.class)
class MvcConfigTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MvcConfig mvcConfig;

    @Test
    void contextLoads() {
        assertNotNull(mvcConfig);
        assertNotNull(mockMvc);
    }

    @Test
    void testIfStaticResourcesAreConfigured() throws Exception {
        mockMvc.perform(get("/javadoc/index.html"))
                .andExpect(status().isOk());

    }
}