package apiengine.restfulapidev;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Endpoints {
  public static Response getAllProducts() {
    RestAssured.baseURI = "https://api.restful-api.dev/";
    RequestSpecification requestSpecification = RestAssured
        .given();

    Response response = requestSpecification
        .log()
        .all()
        .when()
        .get("objects");
    return response;
  }

  public static Response getSingleProduct(String idProduct) {
    RestAssured.baseURI = "https://api.restful-api.dev/";
    RequestSpecification requestSpecification = RestAssured
        .given();
    // .header("Accept", "application/json");

    Response response = requestSpecification
        .log()
        .all()
        .pathParam("path", "objects")
        .pathParam("idProduct", idProduct)
        // .header("Authorization", "Bearer " + token)
        .when()
        .get("{path}/{idProduct}");
    return response;
  }

  public static Response listObjectByIds(String... idProducts) {
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
        .queryParam("id", (Object[]) idProducts)
        .when()
        .get("{path}");
    return response;
  }

  public static Response addNewProduct(String json) {
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
    return response;
  }

  public static Response updateProduct(String idProduct, String json) {
    RestAssured.baseURI = "https://api.restful-api.dev/";
    RequestSpecification requestSpecification = RestAssured
        .given();

    Response response = requestSpecification
        .log()
        .all()
        .pathParam("path", "objects")
        .pathParam("idProduct", idProduct)
        .body(json)
        .contentType("application/json")
        .when()
        .put("{path}/{idProduct}");
    return response;
  }

  public static Response partialUpdateProduct(String idProduct, String json) {

    RestAssured.baseURI = "https://api.restful-api.dev/";
    RequestSpecification requestSpecification = RestAssured
        .given();

    Response response = requestSpecification
        .log()
        .all()
        .pathParam("path", "objects")
        .pathParam("idProduct", idProduct)
        .body(json)
        .contentType("application/json")
        .when()
        .patch("{path}/{idProduct}");

    return response;
  }

  public static Response deleteProduct(String idProduct) {
    RestAssured.baseURI = "https://api.restful-api.dev/";
    RequestSpecification requestSpecification = RestAssured
        .given();

    Response response = requestSpecification
        .log()
        .all()
        .pathParam("path", "objects")
        .pathParam("idProduct", idProduct)
        .contentType("application/json")
        .when()
        .delete("{path}/{idProduct}");
    return response;

  }
}
