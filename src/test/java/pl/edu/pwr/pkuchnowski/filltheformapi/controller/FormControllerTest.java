package pl.edu.pwr.pkuchnowski.filltheformapi.controller;

import com.google.api.services.script.model.Operation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.edu.pwr.pkuchnowski.filltheformapi.service.FormService;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for FormController
 */

@WebMvcTest(FormController.class)
class FormControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FormService formService;

    @Autowired
    private FormController formController;

    /**
     * Test if context loads
     */

    @Test
    void contextLoads() {
        assertNotNull(formService);
        assertNotNull(mockMvc);
        assertNotNull(formController);
    }

    /**
     * Test if getFormLink returns 200 OK
     * @throws Exception If an error occurs
     */

    @ParameterizedTest
    @CsvSource({
            "'test-token', 'testFormId', 'John', 'Doe', 'john.doe@example.com', '1234567890', '123', '2024-06-05T12:53:18', 'potato', '1'",
            "'test-token-2', 'testFormId-2', 'Jane', 'Doe', 'jane.doe@example.com', '0987654321', '321', '2024-06-06T12:53:18', 'tomato', '2'"
    })
    void getFormLink(String token, String formId, String firstName, String lastName, String email, String phone, String orderNumber, String orderDate, String element, String quantity) throws Exception {
        Operation operation = new Operation();
        operation.setResponse(new HashMap<>(Map.of("result", "https://example.com")));

        when(formService.getGoogleFormResponse(
                argThat(arg -> arg != null && !arg.isEmpty()),
                argThat(arg -> arg != null && !arg.isEmpty()),
                argThat(arg -> arg != null && !arg.isEmpty()),
                argThat(arg -> arg != null && !arg.isEmpty()),
                argThat(arg -> arg != null && !arg.isEmpty()),
                argThat(arg -> arg != null && !arg.isEmpty()),
                argThat(arg -> arg != null && !arg.isEmpty()),
                argThat(arg -> arg != null && !arg.isEmpty()),
                argThat(arg -> arg != null && arg.length > 0),
                argThat(arg -> arg != null && arg.length > 0)))
                .thenReturn(operation);

        mockMvc.perform(post("/api/forms/google-form-link")
                        .header("Authorization", "Bearer " + token)
                        .param("formId", formId)
                        .param("firstName", firstName)
                        .param("lastName", lastName)
                        .param("email", email)
                        .param("phone", phone)
                        .param("orderNumber", orderNumber)
                        .param("orderDate", orderDate)
                        .param("element", element)
                        .param("quantity", quantity)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}