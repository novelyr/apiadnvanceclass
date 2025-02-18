package restassured;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.model.ResponseObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

// Readme
/*
 * 1. semua sudah ditambah @Test
 * 2. beberapa test ada dependency ke test sebelumnya
 * 3. test passed semua kalau di run langsung seluruh kelasnya (line 22)
 */

public class TugasRestAssuredPOJO {

  ResponseObject responseObject;
  String idNewObject;

  @Test
  public void getAllobjects() {
    RestAssured.baseURI = "https://api.restful-api.dev/";
    RequestSpecification requestSpecification = RestAssured
        .given();

    Response response = requestSpecification
        .log()
        .all()
        .when()
        .get("objects");

    // System.out.println("Hasilnya adalah " + response.asPrettyString());
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

  @Test
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

    // System.out.println("single Product " + response.asPrettyString());
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
    // Assert.assertEquals(responseObject.data.get("year"), "2019");
    // Assert.assertEquals(responseObject.data.get("price"), "1849.99");
    // Assert.assertEquals(responseObject.data.get("CPU model"), "Intel Core i9");
    // Assert.assertEquals(responseObject.data.get("Hard disk size"), "1 TB");

  }

  @Test
  public void listObjectByIds() {
    /*
     * https://api.restful-api.dev/objects?id=3&id=5&id=10
     */
    RestAssured.baseURI = "https://api.restful-api.dev/";
    RequestSpecification requestSpecification = RestAssured
        .given();

    Response response = requestSpecification
        .log()
        .all()
        .pathParam("path", "objects")
        .queryParam("id", "3, 5, 10")
        .when()
        .get("{path}");
    // System.out.println("Ini adalah hasil search by ID " +
    // response.asPrettyString());

    JsonPath addJsonPath = response.jsonPath();
    List<ResponseObject> responseList = addJsonPath.getList("", ResponseObject.class);

    // assert response
    Assert.assertEquals(response.statusCode(), 200);

    // assert size
    Assert.assertEquals(responseList.size(), 3);

    // assert 1st object
    Assert.assertEquals(responseList.get(0).id, "3");
    Assert.assertEquals(responseList.get(0).name, "Apple iPhone 12 Pro Max");
    Assert.assertNotNull(responseList.get(0).data);
    Assert.assertEquals(responseList.get(0).data.color, "Cloudy White");
    Assert.assertEquals(responseList.get(0).data.capacity, "512");

    // assert 3rd object
    Assert.assertEquals(responseList.get(2).id, "10");
    Assert.assertEquals(responseList.get(2).name, "Apple iPad Mini 5th Gen");
    Assert.assertNotNull(responseList.get(2).data);
    Assert.assertEquals(responseList.get(2).data.capacity, "64 GB");
    Assert.assertEquals(responseList.get(2).data.screenSize, "7.9");

  }

  @Test
  public void addProduct() {

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
    // System.out.println("add product" + response.asPrettyString());

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

  @Test(dependsOnMethods = "addProduct")
  public void updateProduct() {
    String json = "{\n" +
        "   \"name\": \"NEW NOVEL Apple MacBook Pro 16\",\n" +
        "   \"data\": {\n" +
        "      \"year\": 2026,\n" +
        "      \"price\": 7777.77,\n" +
        "      \"CPU model\": \"Intel Core i11\",\n" +
        "      \"Hard disk size\": \"2 TB\"\n" +
        "   }\n" +
        "}";

    RestAssured.baseURI = "https://api.restful-api.dev/";
    RequestSpecification requestSpecification = RestAssured
        .given();

    Response response = requestSpecification
        .log()
        .all()
        .pathParam("path", "objects")
        .pathParam("idProduct", idNewObject)
        .body(json)
        .contentType("application/json")
        .when()
        .put("{path}/{idProduct}");
    System.out.println("update product" + response.asPrettyString());

    JsonPath addJsonPath = response.jsonPath();
    responseObject = addJsonPath.getObject("", ResponseObject.class);

    Assert.assertEquals(response.statusCode(), 200);

    // cek apakah ID masih sama
    Assert.assertEquals(idNewObject, responseObject.id);

    // cek perubahan terbaru
    Assert.assertEquals(responseObject.name, "NEW NOVEL Apple MacBook Pro 16");
    Assert.assertNotNull(responseObject.updatedAt);
    Assert.assertNotNull(responseObject.data);
    Assert.assertEquals(responseObject.data.year, 2026);
    Assert.assertEquals(responseObject.data.price, 7777.77);
    Assert.assertEquals(responseObject.data.cpuModel, "Intel Core i11");
    Assert.assertEquals(responseObject.data.HDSize, "2 TB");
  }

  @Test(dependsOnMethods = "updateProduct")
  public void partialUpdateProduct() {
    String json = "{\n" +
        "   \"name\": \"UPDATED Partial Apple MacBook Pro 16\"\n" +
        "}";

    RestAssured.baseURI = "https://api.restful-api.dev/";
    RequestSpecification requestSpecification = RestAssured
        .given();

    Response response = requestSpecification
        .log()
        .all()
        .pathParam("path", "objects")
        .pathParam("idProduct", idNewObject)
        .body(json)
        .contentType("application/json")
        .when()
        .patch("{path}/{idProduct}");
    System.out.println("update product" + response.asPrettyString());

    JsonPath addJsonPath = response.jsonPath();
    responseObject = addJsonPath.getObject("", ResponseObject.class);

    Assert.assertEquals(response.statusCode(), 200);
    Assert.assertEquals(responseObject.name, "UPDATED Partial Apple MacBook Pro 16");

  }

  @Test(dependsOnMethods = "partialUpdateProduct")
  public void deleteProduct() {
    RestAssured.baseURI = "https://api.restful-api.dev/";
    RequestSpecification requestSpecification = RestAssured
        .given();

    Response response = requestSpecification
        .log()
        .all()
        .pathParam("path", "objects")
        .pathParam("idProduct", idNewObject)
        .contentType("application/json")
        .when()
        .delete("{path}/{idProduct}");
    System.out.println("delete product" + response.asPrettyString());

    Assert.assertEquals(response.statusCode(), 200);
  }
}
