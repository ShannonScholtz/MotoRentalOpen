package za.ac.cput.MotoRental.api.mocktests;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import za.ac.cput.MotoRental.api.CrudPageMockExample;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by student on 2015/09/15.
 */
public class CrudMockPageTest {
    final String BASE_URL = "http://localhost:8080/";
    private MockMvc mockMvc;

  //  @Before
    public void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(new CrudPageMockExample())
                .build();
    }

  //  @Test
    public void read() throws Exception {

        mockMvc
                .perform(get(BASE_URL+"api/test/read/{id}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"name\":\"john\",\"phoneNumber\":0834564754}"));
    }

 //   @Test
    public void readAll() throws Exception {

        mockMvc
                .perform(get(BASE_URL+"api/test/readall"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        content().string(
                                "{\"customers\":[{\"name\":\"john\",\"phoneNumber\":0834564754},"
                                        + "{\"name\":\"peter\",\"phoneNumber\":0613453145}]}"));
    }

 //   @Test
    public void create() throws Exception {

        mockMvc
                .perform(
                        post(BASE_URL+"api/test/create").contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"mary\",\"phoneNumber\":0613453145}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        content().string("{\"name\":\"mary\",\"phoneNumber\":0613453145}"));
    }

  //  @Test
    public void update() throws Exception {

        mockMvc
                .perform(
                        put(BASE_URL+"api/test/update").contentType(MediaType.APPLICATION_JSON).content(
                                "{\"name\":\"mary\",\"phoneNumber\":0612345636}")).andExpect(
                status().isOk());
    }

 //   @Test
    public void remove() throws Exception {

        mockMvc
                .perform(
                        delete(BASE_URL+"api/test/delete").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}