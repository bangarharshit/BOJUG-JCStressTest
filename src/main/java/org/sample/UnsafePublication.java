package org.sample;


import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Description;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.IntResult1;

@JCStressTest
@Description("Tests if unsafe publication is unsafe.")
@Outcome(id = "-1", expect = Expect.ACCEPTABLE, desc = "The object is not yet published")
@Outcome(id = "[0]", expect = Expect.FORBIDDEN, desc = "The object is published, but all fields are 0.")
@Outcome(id = "[1]", expect = Expect.FORBIDDEN, desc = "The object is published, at least 1 field is visible.")
@Outcome(id = "[2]", expect = Expect.FORBIDDEN, desc = "The object is published, at least 2 fields are visible.")
@Outcome(id = "[3]", expect = Expect.FORBIDDEN, desc = "The object is published, at least 3 fields are visible.")
@Outcome(id = "[4]", expect = Expect.ACCEPTABLE, desc = "The object is published, all fields are visible.")
@State
// For 64 bit vm run with -XX:-UseCompressedOops. Check comments below for details.
public class UnsafePublication {

    /*
       Implementation notes:
         * This showcases how compiler can move the publishing store past the field stores.
         * We need to provide constructor with some external value. If we put the constants in the
           constructor, then compiler can store all the fields with a single bulk store.
         * This test is best to be run with either 32-bit VM, or 64-bit VM with -XX:-UseCompressedOops:
           it seems the compressed references mechanics moves the reference store after the field
           stores, even though not required by JMM.
     */

  int x = 1;

  MyObject o;

  @Actor
  public void publish() {
    o = new MyObject(x);
  }

  @Actor
  public void consume(IntResult1 res) {
    MyObject lo = o;
    if (lo != null) {
      res.r1 = lo.x00 + lo.x01 + lo.x02 + lo.x03;
    } else {
      res.r1 = -1;
    }
  }

  static class MyObject {
    final int x00, x01, x02, x03;
    public MyObject(int x) {
      x00 = x;
      x01 = x;
      x02 = x;
      x03 = x;
    }
  }

}
