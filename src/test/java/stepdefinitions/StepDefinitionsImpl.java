package stepdefinitions;

import java.util.List;

import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.request.RequestItem;
import com.model.response.ResponseObject;

import apiengine.restfulapidev.Endpoints;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.DataRequest;

public class StepDefinitionsImpl {

  ResponseObject responseObject;
  RequestItem requestItem;
  String idProduct;
  DataRequest dataRequest;
  String json;
  Endpoints endpoints;

  // kalau ada problem, anotasi bisa diganti @BeforeStep
  @Before
  public void setUp() {
    endpoints = new Endpoints();
  }

  @Given("A list of item are available")
  public void getAllProduct() {
    Response response = endpoints.getAllProducts("objects");

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
    json = DataRequest.addItemCollection().get(payload);
    if (json == null) {
      throw new IllegalArgumentException("Payload not found: " + payload);
    }

    Response response = endpoints.addNewProduct("objects", json);

    System.out.println("add product with CUCUMBER : " + response.asPrettyString());

    ObjectMapper requestAddItem = new ObjectMapper();
    requestItem = requestAddItem.readValue(json, RequestItem.class);

    // Validation
    JsonPath addJsonPath = response.jsonPath();
    responseObject = addJsonPath.getObject("", ResponseObject.class);
    idProduct = responseObject.id;

    Assert.assertEquals(response.statusCode(), 200);
    // Assert.assertEquals(responseObject.id, requestItem.id);
    Assert.assertNotNull(responseObject.id);
    Assert.assertEquals(responseObject.name, requestItem.name);
    Assert.assertEquals(responseObject.data.year, requestItem.data.year);
    Assert.assertEquals(responseObject.data.price, requestItem.data.price);
    Assert.assertEquals(responseObject.data.cpuModel, requestItem.data.cpuModel);

  }

  @And("The item is available")
  public void getSingleProduct() {

    Response response = endpoints.getSingleProduct("objects", idProduct);

    System.out.println("single Product " + response.asPrettyString());
    JsonPath addJsonPath = response.jsonPath();
    responseObject = addJsonPath.getObject("", ResponseObject.class);

    Assert.assertEquals(response.statusCode(), 200);
    Assert.assertEquals(responseObject.id, idProduct);

    Assert.assertEquals(response.statusCode(), 200);
    Assert.assertEquals(responseObject.id, idProduct);
    Assert.assertNotNull(responseObject.data);
    Assert.assertEquals(responseObject.name, requestItem.name);
    Assert.assertEquals(responseObject.data.year, requestItem.data.year);
    Assert.assertEquals(responseObject.data.price, requestItem.data.price);
    Assert.assertEquals(responseObject.data.cpuModel, requestItem.data.cpuModel);
  }

  @Then("I can update item {string}")
  public void updateProducts(String update) throws JsonMappingException, JsonProcessingException {
    // Implementation
    json = DataRequest.updateItemCollection().get(update);
    if (json == null) {
      throw new IllegalArgumentException("Update not found: " + update);
    }

    Response response = endpoints.updateProduct("objects", idProduct, json);

    System.out.println("update product with CUCUMBER : " + response.asPrettyString());

    ObjectMapper requestAddItem = new ObjectMapper();
    requestItem = requestAddItem.readValue(json, RequestItem.class);

    // Validation
    JsonPath addJsonPath = response.jsonPath();
    responseObject = addJsonPath.getObject("", ResponseObject.class);
    idProduct = responseObject.id;

    Assert.assertEquals(response.statusCode(), 200);
    // Assert.assertEquals(responseObject.id, requestItem.id);
    Assert.assertNotNull(responseObject.id);
    Assert.assertNotNull(responseObject.updatedAt);
    Assert.assertEquals(responseObject.name, requestItem.name);
    Assert.assertEquals(responseObject.data.year, requestItem.data.year);
    Assert.assertEquals(responseObject.data.price, requestItem.data.price);
    Assert.assertEquals(responseObject.data.cpuModel, requestItem.data.cpuModel);

  }
}
