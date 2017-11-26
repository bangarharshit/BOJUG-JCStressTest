package org.sample;

import java.util.HashMap;
import java.util.Map;
import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Description;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.LongResult2;

public class RaceConditionInHashMapOfStrings {

  @State
  public static class MyState {
    final Map<String, String> map = new HashMap<>();
  }

  @Description("test racily getting String from HashMap")
  @JCStressTest
  @Outcome(id="[0, 1]", expect = Expect.ACCEPTABLE, desc="get back expected character 'a' and character 'b'")
  public static class StressTest2  {

    @Actor
    public void actor1(MyState myState, LongResult2 r1) {
      myState.map.put("k","a");
      String r = myState.map.get("k");
      r1.r1 = (r == null) ? -1 : (r.equals("a") ? 0 : (r.equals("b") ? 1 : -1));
    }

    @Actor
    public void actor2(MyState myState, LongResult2 r1) {
      myState.map.put("k", "b");
      String r = myState.map.get("k");
      r1.r2 = (r == null) ? -1 : (r.equals("a") ? 0 : (r.equals("b") ? 1 : -1));
    }
  }


}
