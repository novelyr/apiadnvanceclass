package stepdefinitions;

import java.util.List;

import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.request.RequestItem;
import com.model.response.ResponseObject;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.DataRequest;

public class StepDefinitionsImpl {

  ResponseObject responseObject;
  RequestItem requestItem;
  String idNewObject;
  DataRequest dataRequest;
  String json;

  @Given("A list of item are available")
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

  @When("I add item to list {string}")
  public void addNewProducts(String payload) throws JsonMappingException, JsonProcessingException {
    // Implementation
    dataRequest = new DataRequest();

    // System.out.println("Add new product-1" + payload);
    RestAssured.baseURI = "https://api.restful-api.dev/";
    RequestSpecification requestSpecification = RestAssured
        .given();

    // for (Map.Entry<String, String> entry :
    // dataRequest.addItemCollection().entrySet()) {
    // if (entry.getKey().equals(payload)) {
    // json = entry.getValue();
    // break;
    // }
    // }

    json = dataRequest.addItemCollection().get(payload);
    if (json == null) {
      throw new IllegalArgumentException("Payload not found: " + payload);
    }

    Response response = requestSpecification
        .log()
        .all()
        .pathParam("path", "objects")
        .body(json)
        .contentType("application/json")
        .when()
        .post("{path}");
    System.out.println("add product with CUCUMBER : " + response.asPrettyString());

    ObjectMapper requestAddItem = new ObjectMapper();
    requestItem = requestAddItem.readValue(json, RequestItem.class);

    // Validation
    JsonPath addJsonPath = response.jsonPath();
    responseObject = addJsonPath.getObject("", ResponseObject.class);
    idNewObject = responseObject.id;

    Assert.assertEquals(response.statusCode(), 200);
    // Assert.assertEquals(responseObject.id, requestItem.id);
    Assert.assertNotNull(responseObject.id);
    Assert.assertEquals(responseObject.name, requestItem.name);
    Assert.assertEquals(responseObject.data.year, requestItem.data.year);
    Assert.assertEquals(responseObject.data.price, requestItem.data.price);
    Assert.assertEquals(responseObject.data.cpuModel, requestItem.data.cpuModel);

  }

  @Then("The item is available")
  public void getSingleProduct() {

    RestAssured.baseURI = "https://api.restful-api.dev/";
    RequestSpecification requestSpecification = RestAssured
        .given();

    Response response = requestSpecification
        .log()
        .all()
        .pathParam("path", "objects")
        .pathParam("idProduct", idNewObject)
        .when()
        .get("{path}/{idProduct}");

    System.out.println("Get Single Product " + response.asPrettyString());

    // Validation
    JsonPath addJsonPath = response.jsonPath();
    responseObject = addJsonPath.getObject("", ResponseObject.class);

    Assert.assertEquals(response.statusCode(), 200);
    Assert.assertEquals(responseObject.id, idNewObject);
    Assert.assertNotNull(responseObject.data);
    Assert.assertEquals(responseObject.name, requestItem.name);
    Assert.assertEquals(responseObject.data.year, requestItem.data.year);
    Assert.assertEquals(responseObject.data.price, requestItem.data.price);
    Assert.assertEquals(responseObject.data.cpuModel, requestItem.data.cpuModel);
  }

}
