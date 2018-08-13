package cuex.web.integration;

import cuex.web.config.WebConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;

import static org.hamcrest.CoreMatchers.is;

@ActiveProfiles("mock")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
class SymbolControllerTest {

    @Autowired
    private WebApplicationContext ctx;
    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    void mvc() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URI.create("/api/symbols")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbols.AED", is("United Arab Emirates Dirham")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbols.AMD", is("Armenian Dram")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbols.ALL", is("Albanian Lek")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbols.AFN", is("Afghan Afghani")));
    }

}