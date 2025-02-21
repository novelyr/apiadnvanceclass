package resources;

import java.util.HashMap;
import java.util.Map;

public class DataRequest {
  public static Map<String, String> addItemCollection() {
    Map<String, String> dataCollection = new HashMap<>();
    dataCollection.put("addItem", "{\n" +
        "   \"name\": \"NOVEL Apple MacBook Pro 16\",\n" +
        "   \"data\": {\n" +
        "      \"year\": 2025,\n" +
        "      \"price\": 9849.99,\n" +
        "      \"CPU model\": \"Intel Core i10\",\n" +
        "      \"Hard disk size\": \"1 TB\"\n" +
        "   }\n" +
        "}");

    dataCollection.put("addItem2", "{\n" +
        "   \"name\": \"2 NOVEL Apple MacBook Pro 16\",\n" +
        "   \"data\": {\n" +
        "      \"year\": 2025,\n" +
        "      \"price\": 2222.22,\n" +
        "      \"CPU model\": \"Intel Core i10\",\n" +
        "      \"Hard disk size\": \"1 TB\"\n" +
        "   }\n" +
        "}");

    return dataCollection;
  }

  public static Map<String, String> updateItemCollection() {
    Map<String, String> dataCollection = new HashMap<>();
    dataCollection.put("updateItem", "{\n" +
        "   \"name\": \"Updated NOVEL Apple MacBook Pro 16\",\n" +
        "   \"data\": {\n" +
        "      \"year\": 2025,\n" +
        "      \"price\": 9849.99,\n" +
        "      \"CPU model\": \"NEW Intel Core i10\",\n" +
        "      \"Hard disk size\": \"1 TB\"\n" +
        "   }\n" +
        "}");

    dataCollection.put("updateItem2", "{\n" +
        "   \"name\": \"Updated 2 NOVEL Apple MacBook Pro 16\",\n" +
        "   \"data\": {\n" +
        "      \"year\": 2025,\n" +
        "      \"price\": 2222.22,\n" +
        "      \"CPU model\": \"NEW Intel Core i10\",\n" +
        "      \"Hard disk size\": \"1 TB\"\n" +
        "   }\n" +
        "}");

    return dataCollection;
  }
}
