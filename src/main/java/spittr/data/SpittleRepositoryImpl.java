package spittr.data;

import org.springframework.stereotype.Component;
import spittr.domain.Spittle;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component("spittleRepositoryImpl")
public class SpittleRepositoryImpl implements SpittleRepository{
  @Override
  public List<Spittle> findSpittles(long max, int count) {
    return Collections.singletonList(new Spittle("A message", new Date()));
  }

  @Override
  public Spittle findSpittle(long id) {
    return null;
  }
}
