package spittr.data;

import spittr.domain.Spittle;

import java.util.List;

public interface SpittleRepository {
  List<Spittle> findSpittles(long max, int count);

  Spittle findSpittle(long id);
}
