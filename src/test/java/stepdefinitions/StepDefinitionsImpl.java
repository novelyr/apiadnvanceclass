package stepdefinitions;

import java.util.List;

import org.testng.Assert;

import com.model.ResponseObject;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefinitionsImpl {

  ResponseObject responseObject;
  String idNewObject;

  @Given("A list of products are available")
  public void getAllProduct() {
    RestAssured.baseURI = "https://api.restful-api.dev/";
    RequestSpecification requestSpecification = RestAssured
        .given();

    Response response = requestSpecification
        .log()
        .all()
        .when()
        .get("objects");

    System.out.println("Hasilnya adalah " + response.asPrettyString());
    JsonPath addJsonPath = response.jsonPath();
    List<ResponseObject> responseList = addJsonPath.getList("", ResponseObject.class);

    // assert size
    Assert.assertEquals(responseList.size(), 13);

    // assert first object
    Assert.assertEquals(responseList.get(0).id, "1");
    Assert.assertEquals(responseList.get(0).name, "Google Pixel 6 Pro");
    Assert.assertNotNull(responseList.get(0).data);
    Assert.assertEquals(responseList.get(0).data.color, "Cloudy White");
    Assert.assertEquals(responseList.get(0).data.capacity, "128 GB");

    // assert 9th object
    Assert.assertEquals(responseList.get(8).id, "9");
    Assert.assertEquals(responseList.get(8).name, "Beats Studio3 Wireless");
    Assert.assertNotNull(responseList.get(8).data);
    Assert.assertEquals(responseList.get(8).data.color, "Red");
    Assert.assertEquals(responseList.get(8).data.description,
        "High-performance wireless noise cancelling headphones");
  }

  @When("I add a new {string} to etalase")
  public void addNewProduct2(String payload) {
    System.out.println("Adding new product: " + payload);
  }

  @When("I add a new product to etalase")
  public void addNewProduct() {
    String json = "{\n" +
        "   \"name\": \"NOVEL Apple MacBook Pro 16\",\n" +
        "   \"data\": {\n" +
        "      \"year\": 2025,\n" +
        "      \"price\": 9849.99,\n" +
        "      \"CPU model\": \"Intel Core i10\",\n" +
        "      \"Hard disk size\": \"1 TB\"\n" +
        "   }\n" +
        "}";

    RestAssured.baseURI = "https://api.restful-api.dev/";
    RequestSpecification requestSpecification = RestAssured
        .given();

    Response response = requestSpecification
        .log()
        .all()
        .pathParam("path", "objects")
        .body(json)
        .contentType("application/json")
        .when()
        .post("{path}");
    System.out.println("add product" + response.asPrettyString());

    JsonPath addJsonPath = response.jsonPath();
    responseObject = addJsonPath.getObject("", ResponseObject.class);
    idNewObject = responseObject.id;

    Assert.assertEquals(response.statusCode(), 200);

    Assert.assertNotNull(idNewObject);
    Assert.assertEquals(responseObject.name, "NOVEL Apple MacBook Pro 16");
    Assert.assertNotNull(responseObject.createdAt);
    Assert.assertNotNull(responseObject.data);
    Assert.assertEquals(responseObject.data.year, 2025);
    Assert.assertEquals(responseObject.data.price, 9849.99);
    Assert.assertEquals(responseObject.data.cpuModel, "Intel Core i10");
    Assert.assertEquals(responseObject.data.HDSize, "1 TB");

  }

  @Then("Product is available")
  public void getSingleProduct() {

    RestAssured.baseURI = "https://api.restful-api.dev/";
    RequestSpecification requestSpecification = RestAssured
        .given();

    Response response = requestSpecification
        .log()
        .all()
        .pathParam("path", "objects")
        .pathParam("idProduct", 7)
        .when()
        .get("{path}/{idProduct}");

    System.out.println("single Product " + response.asPrettyString());
    JsonPath addJsonPath = response.jsonPath();
    responseObject = addJsonPath.getObject("", ResponseObject.class);

    Assert.assertEquals(response.statusCode(), 200);
    Assert.assertEquals(responseObject.id, "7");
    Assert.assertEquals(responseObject.name, "Apple MacBook Pro 16");
    Assert.assertNotNull(responseObject.data);
    Assert.assertEquals(responseObject.data.year, 2019);
    Assert.assertEquals(responseObject.data.price, 1849.99);
    Assert.assertEquals(responseObject.data.cpuModel, "Intel Core i9");
    Assert.assertEquals(responseObject.data.HDSize, "1 TB");
  }

}
