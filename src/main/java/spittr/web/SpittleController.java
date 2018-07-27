package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import spittr.domain.Spittle;
import spittr.data.SpittleRepository;

import java.util.List;

@Controller
@RequestMapping("/spittles")
public class SpittleController {
  private SpittleRepository spittleRepository;

  @Autowired
  public SpittleController(SpittleRepository spittleRepository) {
    this.spittleRepository = spittleRepository;
  }

  @RequestMapping(method = RequestMethod.GET)
  public List<Spittle> spittles(@RequestParam(value="max", defaultValue=Long.MAX_VALUE+"") long max,
                                @RequestParam(value = "count", defaultValue = "20") int count) {
    return spittleRepository.findSpittles(max, count);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String showSpilttle(@PathVariable("id") long id, Model model) {
    model.addAttribute("spittle", spittleRepository.findSpittle(id));
    return "spittle";
  }
}
