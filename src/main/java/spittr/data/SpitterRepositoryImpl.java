package spittr.data;

import org.springframework.stereotype.Component;
import spittr.domain.Spitter;

@Component("spitterRepositoryImpl")
public class SpitterRepositoryImpl implements SpitterRepository{
  @Override
  public Spitter save(Spitter spitter) {
    return null;
  }

  @Override
  public Spitter findByUserName(String username) {
    return null;
  }
}
