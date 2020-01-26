package com.test.pizzacart.api;


import com.test.pizzacart.model.PizzaInfo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://ipsator-android-test.free.beeceptor.com/pizzas/";

    @GET("1/")
    Call<PizzaInfo> getPizzaInfo();

}
