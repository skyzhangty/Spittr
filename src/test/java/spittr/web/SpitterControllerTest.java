package spittr.web;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import spittr.data.SpitterRepository;
import spittr.domain.Spitter;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class SpitterControllerTest {

  private SpitterRepository mockedSpitterRepository = Mockito.mock(SpitterRepository.class);

  @Test
  public void testShowRegistrationForm() throws Exception {
    SpitterController controller = new SpitterController(mockedSpitterRepository);
    MockMvc mockMvc = standaloneSetup(controller).build();

    mockMvc.perform(get("/spitters/register"))
        .andExpect(view().name("registerForm"));
  }

  @Test
  public void testShowProcessRegistrationForm() throws Exception {
    Spitter unsaved = new Spitter("firstName", "lastName", "username", "password");

    SpitterController controller = new SpitterController(mockedSpitterRepository);
    MockMvc mockMvc = standaloneSetup(controller).build();

    mockMvc.perform(
        post("/spitters/register")
            .param("firstName", unsaved.getFirstName())
            .param("lastName", unsaved.getLastName())
            .param("username", unsaved.getUsername())
            .param("password", unsaved.getPassword())
    ).andExpect(redirectedUrl("/spitters/" + unsaved.getUsername()));
  }

  @Test
  public void testShowSpitterProfile() throws Exception {
    Spitter expectedSpitter = new Spitter();
    String username = "username";

    expectedSpitter.setUsername(username);
    when(mockedSpitterRepository.findByUserName(username)).thenReturn(expectedSpitter);

    SpitterController controller = new SpitterController(mockedSpitterRepository);

    MockMvc mockMvc = standaloneSetup(controller).build();

    mockMvc.perform(get("/spitters/" + username))
        .andExpect(view().name("profile"))
        .andExpect(model().attributeExists("spitter"))
        .andExpect(model().attribute("spitter", expectedSpitter));
  }
}
