package spittr.web;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;
import spittr.domain.Spitter;
import spittr.domain.Spittle;
import spittr.data.SpittleRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        .andExpect(model().attribute("spittleList", expectedSpittleList));
  }

  @Test
  public void shouldShowPagedRecentSpittles() throws Exception {
    long maxId = 12345L;
    int count = 20;
    SpittleRepository mockSpittleRepository = Mockito.mock(SpittleRepository.class);
    List<Spittle> expectedSpittleList = createSpittleList(count);
    when(mockSpittleRepository.findSpittles(maxId, count)).thenReturn(expectedSpittleList);

    SpittleController spittleController = new SpittleController(mockSpittleRepository);

    MockMvc mockMvc = standaloneSetup(spittleController)
        .setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();

    mockMvc.perform(get("/spittles?max=" + maxId + "&count=" + count))
        .andExpect(view().name("spittles"))
        .andExpect(model().attributeExists("spittleList"))
        .andExpect(model().attribute("spittleList", expectedSpittleList));

  }

  @Test
  public void testShowSpittle() throws Exception {
    long id = 1L;
    Spittle exceptedSpittle = new Spittle("message", new Date());
    SpittleRepository spittleRepository = Mockito.mock(SpittleRepository.class);

    when(spittleRepository.findSpittle(id)).thenReturn(exceptedSpittle);

    SpittleController controller = new SpittleController(spittleRepository);

    MockMvc mockMvc = standaloneSetup(controller).build();

    mockMvc.perform(get("/spittles/" + id))
        .andExpect(view().name("spittle"))
        .andExpect(model().attributeExists("spittle"))
        .andExpect(model().attribute("spittle", exceptedSpittle));
  }

  private List<Spittle> createSpittleList(int count) {
    return IntStream.range(0, count)
        .mapToObj(i->new Spittle("Spiltte " + i, new Date())).collect(Collectors.toList());
  }
}
