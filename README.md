Task

1. Implementasi validation menggunakan POJO di setiap endpoint yang ada di https://restful-api.dev/ (ada di file src/test/java/restassured/TugasRestAssuredPOJO.java)

2. Implementasi E2E Test yang ada di https://restful-api.dev/ dan lengkapi dengan validasi menggunakan POJO. Scenario nya tertera dibawah ini

Scenario Add Product

- Create new object (hit API add_object)
- Verify new object is added (hit API single_object)
- Delete product (hit API delete_object)
- Verify new object is deleted (hit API single_object)
  Note:

  1. Untuk E2E test buat dalam satu function test
  2. Submission buat dalam satu branch jangan di merge ke master

- ada di file src/test/java/scenario/RestE2ETest.java
- branch tugas "testng_rest"
