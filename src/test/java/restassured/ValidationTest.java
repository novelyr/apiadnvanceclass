package restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.model.ResponseObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidationTest {
  /*
   * 1. hit api update
   * 2. then validate response
   * - not empty
   * -
   */
  ResponseObject responseObject;

  @Test
  public void createObject() {
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
    System.out.println("Response API add object: " + response.asPrettyString());

    /*
     * response:
     * {
     * "id": "ff808181932badb6019513db7eb54455",
     * "name": "NOVEL Apple MacBook Pro 16",
     * "createdAt": "2025-02-17T12:21:46.805+00:00",
     * "data": {
     * "year": 2025,
     * "price": 9849.99,
     * "CPU model": "Intel Core i10",
     * "Hard disk size": "1 TB"
     * }
     * }
     */
    JsonPath addJsonPath = response.jsonPath();
    // String name = addJsonPath.getString("name");
    // String createdAt = addJsonPath.getString("createdAt");
    // String id = addJsonPath.getString("id");
    // Double price = addJsonPath.getDouble("data.price");
    // int year = addJsonPath.getInt("data.year");
    // String CPUModel = addJsonPath.getString("data.'CPU model'");
    // String HDSize = addJsonPath.getString("data.'Hard disk size'");

    // System.out.println("name is " + name);
    // System.out.println("price is " + price);
    // System.out.println("year is " + year);
    // System.out.println("CPU model is " + CPUModel);
    // System.out.println("HD Size is " + HDSize);

    // ini cara PERTAMA
    // Assert.assertEquals(response.statusCode(), 200);
    // Assert.assertNotNull(createdAt);
    // Assert.assertNotNull(id);
    // Assert.assertEquals(name, "NOVEL Apple MacBook Pro 16");
    // Assert.assertEquals(year, 2025);
    // Assert.assertEquals(price, 9849.99);
    // Assert.assertEquals(CPUModel, "Intel Core i10");
    // Assert.assertEquals(HDSize, "1 TB");

    /*
     * metode langsung hardcode path diatas, kelemahan:
     * 1. harus trace ulang semua path kalau ada perubahan
     * 2. susah di maintenance
     * 
     * di sinilah POJO bersinar
     * karena pakai class
     * mudah di maintenance
     */

    // ini cara POJO
    responseObject = addJsonPath.getObject("", ResponseObject.class);

    System.out.println("name is " + responseObject.name);
    // System.out.println("price is " + responseObject.data.get("price"));
    // System.out.println("year is " + responseObject.data.get("year"));
    // System.out.println("CPU model is " + responseObject.data.get("CPU model"));
    // System.out.println("HD Size is " + responseObject.data.get("Hard disk
    // size"));

    Assert.assertEquals(response.statusCode(), 200);
    Assert.assertNotNull(responseObject.createdAt);
    Assert.assertNotNull(responseObject.id);
    Assert.assertEquals(responseObject.name, "NOVEL Apple MacBook Pro 16");
    // Assert.assertEquals(responseObject.data.get("year"), 2025);
    // Assert.assertEquals(responseObject.data.get("price"), 9849.99);
    // Assert.assertEquals(responseObject.data.get("CPU model"), "Intel Core i10");
    // Assert.assertEquals(responseObject.data.get("Hard disk size"), "1 TB");

  }
}
