import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import example.RetryTest;
import example.StaticProvider;

public class TestNG2 {
  @DataProvider(name = "test1")
  public Object[][] personObjects() {
    return new Object[][] {
        { "Rudy", 34 },
        { "Isabella swan", 28 }
    };
  }

  // @Parameters(("program"))
  // @Test
  // public void parameter(String params){
  // Assert.assertEquals(params, "AP`I Automation");
  // }

  @Test(dataProvider = "test1")
  public void parameter(String name, int age) {
    System.out.println("Namanya adalah : " + name);
    System.out.println("Umurnya adalah : " + age);
  };

  @Test(dataProvider = "test1", dataProviderClass = StaticProvider.class)
  public void parameter2(String name, int age) {
    System.out.println("Namanya adalah : " + name);
    System.out.println("Umurnya adalah : " + age);
  };

  // ada fitur retry yg membuat test ng bisa ngulang test semisal karena internet
  // down
  @Test(retryAnalyzer = RetryTest.class)
  public void testRetry() {
    System.out.println("Ini test retry");
    assert false; // ketika ini true dia jalan sekali doank why???
  };
}
