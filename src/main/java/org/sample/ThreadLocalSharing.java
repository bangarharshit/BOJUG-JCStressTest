package org.sample;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.IntResult2;

@JCStressTest
@Outcome(id = {"0, 0", "0, 1", "1, 1"}, expect = Expect.ACCEPTABLE,             desc = "All normal and racy results")
@Outcome(id = "1, 0",   expect = Expect.ACCEPTABLE_INTERESTING, desc = "No memory effects")
@State
public class ThreadLocalSharing {

  private int x;
  private final Wrapper wrapper = new Wrapper();
  private final ThreadLocal<Wrapper> tl = new ThreadLocal<>();

  public static class Wrapper {
    public int y;
  }

  @Actor
  public void actor1() {
    tl.set(wrapper);
    x = 1;
    tl.get().y = 1;
  }

  @Actor
  public void actor2(IntResult2 r) {
    tl.set(wrapper);
    r.r1 = tl.get().y;
    r.r2 = x;
  }
}
