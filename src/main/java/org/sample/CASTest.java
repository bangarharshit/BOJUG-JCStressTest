package org.sample;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Description;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.LongResult2;

public class CASTest {

  @State
  public static class S extends CASValue { }

  @JCStressTest
  @Description("Tests correctness of CASValue CAS operations.")
  @Outcome(id = "5, 2", expect = Expect.ACCEPTABLE, desc = "T1 -> T2 execution.")
  @Outcome(id = "1, 10", expect = Expect.ACCEPTABLE, desc = "T1 -> T2 execution.")
  public static class ValCas_ValCas {
    @Actor public void actor1(S s, LongResult2 r) {
      r.r1 = s.cas(0, 5) ? 5 : 1;
    }
    @Actor public void actor2(S s, LongResult2 r) {
      r.r2 = s.cas(0, 10) ? 10 : 2;
    }
  }

  public static class CASValue {
    private int i = 0;
    public boolean cas(int expected, int newValue) {
      boolean res = false;
      if (i == expected) {
        i = newValue;
        res = true;
      }
      return res;
    }
  }
}
