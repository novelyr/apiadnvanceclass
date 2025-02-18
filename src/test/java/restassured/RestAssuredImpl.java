package restassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

// PR
/*
 * 1. tambahin assert pakai pojo di tiap API (ada 7 itu), tinggal tambahin
 * annotaion @Test di tiap hit API
 * 2. nanti ada scenario E2E baru, add object, update, delete
 * 3. buat branch baru aja ? TestNG Rest?
 */

public class RestAssuredImpl {
  public static void main(String[] args) {
    // System.out.println("ini adalah novel ");
    // getAllobjects();
    // getSingleProduct();
    // listObjectByIds();
    // addProduct();
    // updateProduct();
    // partialUpdateProduct();

    // deleteProduct();

    // hati2 setelah delete product nanti id nya hilang, perlu ulang dari awal add
    // product dan masukin ID ulang
  }

  public static String auth() {

    String token;
    String json = "{\n" + //
        "  \"username\": \"emilys\",\n" + //
        "  \"password\": \"emilyspass\",\n" + //
        "  \"expiresInMins\": 30\n" + //
        "}";
    RestAssured.baseURI = "https://api.restful-api.dev/";
    RequestSpecification requestSpecification = RestAssured
        .given();

    Response response = requestSpecification
        .log()
        .all()
        .body(json)
        .contentType("application/json")
        .pathParam("path", "auth")
        .pathParam("section", "login")
        .when()
        .post("{path}/{section}");

    JsonPath jsonPath = response.jsonPath();

    token = jsonPath.get("accessToken");

    return token;
  }

  public static void getAllobjects() {
    // Define baseURI
    /*
     * "https://api.restful-api.dev/objects"
     * baseURI = https://api.restful-api.dev/
     * path = objects
     */

    RestAssured.baseURI = "https://api.restful-api.dev/";
    RequestSpecification requestSpecification = RestAssured
        .given();

    Response response = requestSpecification.log().all().get("objects");

    Response response2 = requestSpecification
        .log()
        .all()
        .when()
        .get("objects");

    System.out.println("Hasilnya adalah " + response2.asPrettyString());
    System.out.println("Hasilnya adalah " + response.asPrettyString());
  }

  public static void getSingleProduct() {
    /*
     * 'https://api.restful-api.dev/objects/7'
     */

    // String token;

    // token = auth();

    RestAssured.baseURI = "https://api.restful-api.dev/";
    RequestSpecification requestSpecification = RestAssured
        .given();
    // .header("Accept", "application/json");

    Response response = requestSpecification
        .log()
        .all()
        .pathParam("path", "objects")
        .pathParam("idProduct", 7)
        // .header("Authorization", "Bearer " + token)
        .when()
        .get("{path}/{idProduct}");

    System.out.println("single Product " + response.asPrettyString());
  }

  public static void listObjectByIds() {
    /*
     * 'https://api.restful-api.dev//objects/search?q=phone'
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
    System.out.println("Ini adalah hasil search by ID " + response.asPrettyString());
  }

  // public static void searchProduct() {
  // /*
  // * 'https://api.restful-api.dev//objects/search?q=phone'
  // */
  // RestAssured.baseURI = "https://api.restful-api.dev/";
  // RequestSpecification requestSpecification = RestAssured
  // .given();

  // Response response = requestSpecification
  // .log()
  // .all()
  // .pathParam("path", "objects")
  // .pathParam("method", "search")
  // .queryParam("q", "iPhone 5s")
  // .when()
  // .get("{path}/{method}");
  // System.out.println("Ini adalah hasil search" + response.asPrettyString());
  // }

  public static void addProduct() {

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
  }

  public static void updateProduct() {
    String json = "{\n" +
        "   \"name\": \"NEW NOVEL Apple MacBook Pro 16\",\n" +
        "   \"data\": {\n" +
        "      \"year\": 2025,\n" +
        "      \"price\": 7777.77,\n" +
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
        .pathParam("idProduct", "ff808181932badb60195092c48d132ba")
        .body(json)
        .contentType("application/json")
        .when()
        .put("{path}/{idProduct}");
    System.out.println("update product" + response.asPrettyString());
  }

  public static void partialUpdateProduct() {
    String json = "{\n" +
        "   \"name\": \"UPDATED Partial Apple MacBook Pro 16 (Updated Name)\"\n" +
        "}";

    RestAssured.baseURI = "https://api.restful-api.dev/";
    RequestSpecification requestSpecification = RestAssured
        .given();

    Response response = requestSpecification
        .log()
        .all()
        .pathParam("path", "objects")
        .pathParam("idProduct", "ff808181932badb60195092c48d132ba")
        .body(json)
        .contentType("application/json")
        .when()
        .patch("{path}/{idProduct}");
    System.out.println("update product" + response.asPrettyString());
  }

  public static void deleteProduct() {
    RestAssured.baseURI = "https://api.restful-api.dev/";
    RequestSpecification requestSpecification = RestAssured
        .given();

    Response response = requestSpecification
        .log()
        .all()
        .pathParam("path", "objects")
        .pathParam("idProduct", "ff808181932badb60195092c48d132ba")
        .contentType("application/json")
        .when()
        .delete("{path}/{idProduct}");
    System.out.println("delete product" + response.asPrettyString());

  }
}
