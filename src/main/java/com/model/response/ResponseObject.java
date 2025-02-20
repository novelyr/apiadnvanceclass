package com.model.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * disini pakai POJO
 * perlu dependency jackson org
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class ResponseObject {

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

  @JsonProperty("id")
  public String id;

  @JsonProperty("name")
  public String name;

  @JsonProperty("createdAt")
  public String createdAt;

  @JsonProperty("updatedAt")
  public String updatedAt;

  // @JsonProperty("data")
  // public Map<String, String> data;

  @JsonProperty("data")
  public DataItem data;

  // @JsonIgnoreProperties(ignoreUnknown = true)
  public class DataItem {

    @JsonProperty("year")
    public int year;

    @JsonAlias({ "price", "Price" })
    public Double price;

    @JsonAlias({ "Color", "color" })
    public String color;

    @JsonAlias({ "Description", "description" })
    public String description;

    @JsonAlias({ "generation", "Generation" })
    public String generation;

    @JsonAlias({ "Capacity", "capacity", "capacity GB" })
    public String capacity;

    @JsonProperty("Screen size")
    public String screenSize;

    @JsonAlias({ "Case size", "Case Size" })
    public String caseSize;

    @JsonProperty("Strap Colour")
    public String strapColour;

    @JsonProperty("CPU model")
    public String cpuModel;

    @JsonProperty("Hard disk size")
    public String HDSize;

  }

}
