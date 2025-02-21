package apiengine.restfulapidev;

import com.constants.Constants;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Endpoints {
  RequestSpecification requestSpecification;

  public Endpoints() {
    RestAssured.baseURI = Constants.BASE_URL_REST_API_DEV;
    requestSpecification = RestAssured
        .given().log().all();
  }

  public Response getAllProducts(String path) {

    Response response = requestSpecification
        .when()
        .get(path);
    return response;
  }

  public Response getSingleProduct(String path, String idProduct) {
    Response response = requestSpecification
        .pathParam("path", path)
        .pathParam("idProduct", idProduct)
        .when()
        .get("{path}/{idProduct}");
    return response;
  }

  public Response listObjectByIds(String path, String... idProducts) {
    /*
     * https://api.restful-api.dev/objects?id=3&id=5&id=10
     */

    Response response = requestSpecification
        .pathParam("path", path)
        .queryParam("id", (Object[]) idProducts)
        .when()
        .get("{path}");
    return response;
  }

  public Response addNewProduct(String path, String json) {

    Response response = requestSpecification
        .pathParam("path", path)
        .body(json)
        .contentType("application/json")
        .when()
        .post("{path}");
    return response;
  }

  public Response updateProduct(String path, String idProduct, String json) {

    Response response = requestSpecification
        .pathParam("path", path)
        .pathParam("idProduct", idProduct)
        .body(json)
        .contentType("application/json")
        .when()
        .put("{path}/{idProduct}");
    return response;
  }

  public Response partialUpdateProduct(String path, String idProduct, String json) {

    Response response = requestSpecification
        .pathParam("path", path)
        .pathParam("idProduct", idProduct)
        .body(json)
        .contentType("application/json")
        .when()
        .patch("{path}/{idProduct}");

    return response;
  }

  public Response deleteProduct(String path, String idProduct) {

    Response response = requestSpecification
        .pathParam("path", path)
        .pathParam("idProduct", idProduct)
        .contentType("application/json")
        .when()
        .delete("{path}/{idProduct}");
    return response;

  }
}
