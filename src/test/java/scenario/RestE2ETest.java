package scenario;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.ResponseObject;

public class RestE2ETest {
  /*
   * scenario e2e test
   * 1. hit add product (verify response)
   * 2. hit get product (verify response)
   * 3. hit update product (verify response)
   */

  @Test
  public void scenarioE2ETest() {
    String json = "{\n" +
        "   \"name\": \"NOVEL Apple MacBook Pro 16\",\n" +
        "   \"data\": {\n" +
        "      \"year\": 2025,\n" +
        "      \"price\": 9849.99,\n" +
        "      \"CPU model\": \"Intel Core i10\",\n" +
        "      \"Hard disk size\": \"1 TB\"\n" +
        "   }\n" +
        "}";

    // add product

    ResponseObject responseObject;

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
    System.out.println("Response API add object: " + response.asPrettyString());

    JsonPath addJsonPath = response.jsonPath();
    responseObject = addJsonPath.getObject("", ResponseObject.class);

    Assert.assertEquals(response.statusCode(), 200);
    Assert.assertNotNull(responseObject.createdAt);
    Assert.assertNotNull(responseObject.id);
    Assert.assertEquals(responseObject.name, "NOVEL Apple MacBook Pro 16");
    Assert.assertEquals(responseObject.dataItem.year, 2025);
    Assert.assertEquals(responseObject.dataItem.price, 9849.99);
    Assert.assertEquals(responseObject.dataItem.cpuModel, "Intel Core i10");
    Assert.assertEquals(responseObject.dataItem.HDSize, "1 TB");

    String idObject = responseObject.id;

    // get product

    Response response2 = requestSpecification
        .log()
        .all()
        .pathParam("path", "objects")
        .pathParam("idProduct", idObject)
        // .header("Authorization", "Bearer " + token)
        .when()
        .get("{path}/{idProduct}");

    System.out.println("single Product response2: " + response2.asPrettyString());

    // validation pojo

    // update product
    String jsonUpdate = "{\n" +
        "   \"name\": \"NEW NOVEL Apple MacBook Pro 16\",\n" +
        "   \"data\": {\n" +
        "      \"year\": 2025,\n" +
        "      \"price\": 7777.77,\n" +
        "      \"CPU model\": \"Intel Core i10\",\n" +
        "      \"Hard disk size\": \"1 TB\"\n" +
        "   }\n" +
        "}";

    Response responseUpdate = requestSpecification
        .log()
        .all()
        .pathParam("path", "objects")
        .pathParam("idProduct", idObject)
        .body(jsonUpdate)
        .contentType("application/json")
        .when()
        .put("{path}/{idProduct}");
    System.out.println("update product" + responseUpdate.asPrettyString());

  }
}
