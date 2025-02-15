package testng;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG {

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

  @BeforeTest
  public void beforeTestClass() {
    System.out.println("Ini adalah before test");
  }

  @BeforeSuite
  public void beforeSuiteClass() {
    System.out.println("Ini adalah before suite");
  }

  @BeforeClass
  public void setUpClass() {
    System.out.println("Ini adalah setup class");
  }

  @AfterClass
  public void afterClass() {
    System.out.println("Ini adalah after class");
  }

  @AfterSuite
  public void afterSuiteClass() {
    System.out.println("Ini adalah after suite");
  }

  @AfterTest
  public void afterTestClass() {
    System.out.println("Ini adalah after test");
  }

  @BeforeMethod
  public void beforeMethod() {
    System.out.println("Ini adalah before method");
  }

  @AfterMethod
  public void afterMethod() {
    System.out.println("Ini adalah after method");
  }

  // parameter
  @Test
  @Parameters(("program"))
  public void parameter(@Optional("API Automation") String params) {
    Assert.assertEquals(params, "API Automation");
  }

}
