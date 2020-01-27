package com.test.pizzacart.api;


import com.test.pizzacart.model.PizzaInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface Api {

    String BASE_URL = "https://api.myjson.com/bins/";

    @Headers("Content-Type: application/json")
    @GET("11enle")
    Call<PizzaInfo> getPizzaInfo();

}
