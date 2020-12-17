package gamehub.demo.integration.home;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    private final MockMvc mockMvc;

    @Autowired
    public HomeControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    //Test access for home page
    @Test

    public void testAuthenticationForHome() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/user/login"));
    }

    @Test
    @WithMockUser(roles={""})
    public void testEventsInHome() throws Exception {

        //TODO impls testcase with mock repository
        //arrange - create events
        //act - perform search from mock repository
        //assert - check answers 
    }
}
