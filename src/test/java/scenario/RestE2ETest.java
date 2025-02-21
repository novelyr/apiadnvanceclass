package scenario;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.model.response.ResponseObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/*
 * Scenario Add Product

- Create new object (hit API add_object)
- Verify new object is added (hit API single_object)
- Delete product (hit API delete_object)
- Verify new object is deleted (hit API single_object)
  Note:

  1. Untuk E2E test buat dalam satu function test
  2. Submission buat dalam satu branch jangan di merge ke master

 */

public class RestE2ETest {

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

                // 1st scenario, create new product

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
                System.out.println("Response API create new object: " + response.asPrettyString());

                JsonPath addJsonPath = response.jsonPath();
                ResponseObject responseObject = addJsonPath.getObject("", ResponseObject.class);
                String idNewObject = responseObject.id;

                Assert.assertEquals(response.statusCode(), 200);

                Assert.assertNotNull(idNewObject);
                Assert.assertEquals(responseObject.name, "NOVEL Apple MacBook Pro 16");
                Assert.assertNotNull(responseObject.createdAt);
                Assert.assertNotNull(responseObject.data);
                Assert.assertEquals(responseObject.data.year, 2025);
                Assert.assertEquals(responseObject.data.price, 9849.99);
                Assert.assertEquals(responseObject.data.cpuModel, "Intel Core i10");
                Assert.assertEquals(responseObject.data.HDSize, "1 TB");

                // 2nd scenario, verify new object is added. hit get single product

                Response responseGet = requestSpecification
                                .log()
                                .all()
                                .pathParam("path", "objects")
                                .pathParam("idProduct", idNewObject)
                                .body("")
                                .when()
                                .get("{path}/{idProduct}");

                System.out.println("verify single product is added: " + responseGet.asPrettyString());

                JsonPath addJsonPathGet = responseGet.jsonPath();
                ResponseObject responseObjectGet = addJsonPathGet.getObject("", ResponseObject.class);

                Assert.assertEquals(responseGet.statusCode(), 200);

                Assert.assertEquals(idNewObject, responseObjectGet.id);
                Assert.assertEquals(responseObjectGet.name, "NOVEL Apple MacBook Pro 16");
                Assert.assertNotNull(responseObjectGet.data);
                Assert.assertEquals(responseObjectGet.data.year, 2025);
                Assert.assertEquals(responseObjectGet.data.price, 9849.99);
                Assert.assertEquals(responseObjectGet.data.cpuModel, "Intel Core i10");
                Assert.assertEquals(responseObjectGet.data.HDSize, "1 TB");

                // 3rd scenario, delete product

                Response responseDelete = requestSpecification
                                .log()
                                .all()
                                .pathParam("path", "objects")
                                .pathParam("idProduct", idNewObject)
                                .body("")
                                .contentType("application/json")
                                .when()
                                .delete("{path}/{idProduct}");
                System.out.println("delete product" + responseDelete.asPrettyString());
                JsonPath addJsonPathDelete = responseDelete.jsonPath();
                String actualDeleteMessage = addJsonPathDelete.getString("message");
                String expectedDeleteMessage = String.format("Object with id = %s has been deleted.", idNewObject);

                Assert.assertEquals(responseDelete.statusCode(), 200);
                Assert.assertEquals(actualDeleteMessage, expectedDeleteMessage);

                // 4th verify that the new ID is deleted, hit single get product

                Response responseAfterDelete = requestSpecification
                                .log()
                                .all()
                                .pathParam("path", "objects")
                                .pathParam("idProduct", idNewObject)
                                .body("")
                                .when()
                                .get("{path}/{idProduct}");

                System.out.println("single Product response after deleted: " + responseAfterDelete.asPrettyString());

                JsonPath addJsonPathAfterDelete = responseAfterDelete.jsonPath();
                String actualErrorMessage = addJsonPathAfterDelete.getString("error");
                String expectedErrorMessage = String.format("Oject with id=%s was not found.", idNewObject);

                Assert.assertEquals(responseAfterDelete.statusCode(), 404);
                Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
        }
}

/*
 * GHERKIN
 * 1. feature
 * - Given, When, Then, But, And
 * 
 * Checkout barang
 * 
 * - Given
 * User di halaman cekout
 * 
 * When
 * - action --> cekout item dst
 * 
 * Then
 * - result --> berhasil cekout
 * 
 * And
 * - line line berikutnya, kejadian di 1 cekpoint (given / when / then), instead
 * of i.e. given given given, given and and. Kata sambung lah
 * 
 * But
 * - mirip and, tapi kontradiktif
 * 
 * 2. step definition
 * 3. runner
 * 
 */