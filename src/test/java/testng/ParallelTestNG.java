package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ParallelTestNG {
  String name = "ApiAuto";

  @Test // anotasi
  public void scenarioTest1() {
    System.out.println("ini adalah scenario test 1");
    Assert.assertEquals(1, 1);
    Assert.assertEquals(name, "ApiAuto");
    System.out.println("Thread 1 : " + Thread.currentThread().getId());
  }

  @Test
  public void scenarioTest2() {
    System.out.println("ini adalah scenario test 2");
    System.out.println("Thread 2 : " + Thread.currentThread().getId());

  }

  @Test
  public void scenarioTest3() {
    System.out.println("ini adalah scenario test 3");
    System.out.println("Thread 3 : " + Thread.currentThread().getId());

  }
}
