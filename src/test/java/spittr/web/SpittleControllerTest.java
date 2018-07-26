package spittr.web;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;
import spittr.Spittle;
import spittr.data.SpittleRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class SpittleControllerTest {

  @Test
  public void shouldShowRecentSpittles() throws Exception {
    SpittleRepository mockSpittleRepository = Mockito.mock(SpittleRepository.class);
    List<Spittle> expectedSpittleList = createSpittleList(20);
    when(mockSpittleRepository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(expectedSpittleList);

    SpittleController spittleController = new SpittleController(mockSpittleRepository);

    MockMvc mockMvc = standaloneSetup(spittleController)
        .setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();

    mockMvc.perform(get("/spittles"))
        .andExpect(view().name("spittles"))
        .andExpect(model().attributeExists("spittleList"))
        .andExpect(model().attribute("spittleList", hasItem(expectedSpittleList.toArray())));
  }

  private List<Spittle> createSpittleList(int count) {
    return IntStream.range(0, count)
        .mapToObj(i->new Spittle("Spiltte " + i, new Date())).collect(Collectors.toList());
  }
}
