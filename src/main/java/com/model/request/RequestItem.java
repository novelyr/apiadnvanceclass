package com.model.request;
/*
 * ("addItem2", "{\n" +
        "   \"name\": \"2 NOVEL Apple MacBook Pro 16\",\n" +
        "   \"data\": {\n" +
        "      \"year\": 2025,\n" +
        "      \"price\": 2222.22,\n" +
        "      \"CPU model\": \"Intel Core i10\",\n" +
        "      \"Hard disk size\": \"1 TB\"\n" +
        "   }\n" +
        "}");
 */

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestItem {
  @JsonProperty("name")
  public String name;

  @JsonProperty("data")
  public DataItem data;

  public class DataItem {

    @JsonProperty("year")
    public int year;
    @JsonProperty("price")
    public Double price;
    @JsonProperty("CPU model")
    public String cpuModel;
    @JsonProperty("Hard disk size")
    public String HDSize;

  }
}
