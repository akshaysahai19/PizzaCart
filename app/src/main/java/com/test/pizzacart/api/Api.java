package com.test.pizzacart.api;


import com.test.pizzacart.model.PizzaInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface Api {

    String BASE_URL = "https://ipsator-android-test.free.beeceptor.com/";

    @Headers("Content-Type: application/json")
    @GET("pizzas")
    Call<PizzaInfo> getPizzaInfo();

}
