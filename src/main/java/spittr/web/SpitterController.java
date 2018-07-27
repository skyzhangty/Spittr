package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spittr.data.SpitterRepository;
import spittr.domain.Spitter;

import javax.validation.Valid;

@Controller
@RequestMapping("/spitters")
public class SpitterController {
  private SpitterRepository spitterRepository;

  @Autowired
  public SpitterController(SpitterRepository spitterRepository) {
    this.spitterRepository = spitterRepository;
  }

  @RequestMapping(value = "/register", method= RequestMethod.GET)
  public String showRegistrationForm() {
    return "registerForm";
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public String processRegistration(@Valid Spitter spitter, Errors errors) {

    if (errors.hasErrors()) {
      return "registerForm";
    }
    spitterRepository.save(spitter);
    return "redirect:/spitters/" + spitter.getUsername();
  }

  @RequestMapping(value="/{username}", method = RequestMethod.GET)
  public String showSpitterProfile(@PathVariable("username") String username, Model model) {
    Spitter spitter = spitterRepository.findByUserName(username);
    model.addAttribute("spitter", spitter);

    return "profile";
  }
}
