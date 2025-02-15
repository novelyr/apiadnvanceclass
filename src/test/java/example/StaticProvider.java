package example;

import org.testng.annotations.DataProvider;

public class StaticProvider {
  @DataProvider(name = "test1")
  public static Object[][] personObjects(){ //ini kenapa pakai static???
    return  new Object[][]{
      {"Novell", 29},
      {"Ixa", 1}
    };
  }
}
