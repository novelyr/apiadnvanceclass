package com.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * disini pakai POJO
 * perlu dependency jackson org
 */

public class ResponseItem {

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

     // ini ke bawah salah semua
     @JsonProperty("data")
     public Object data;

     @JsonProperty("data.year")
     public int year;

     @JsonProperty("data.price")
     public Double price;

     @JsonProperty("data.'CPU model'")
     public String cpuModel;

     @JsonProperty("'Hard disk size'")
     public String HDSize;

}
