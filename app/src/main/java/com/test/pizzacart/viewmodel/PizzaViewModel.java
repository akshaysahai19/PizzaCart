package com.test.pizzacart.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.pizzacart.api.Api;
import com.test.pizzacart.api.HttpClientService;
import com.test.pizzacart.model.PizzaInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PizzaViewModel extends ViewModel {

    private MutableLiveData<PizzaInfo> pizzaInfo;

    public LiveData<PizzaInfo> getPizzas() {
        pizzaInfo = new MutableLiveData<PizzaInfo>();
        loadPizzaApi();

        return pizzaInfo;
    }


    private void loadPizzaApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .client(HttpClientService.getUnsafeOkHttpClient())
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Api api = retrofit.create(Api.class);
        Call<PizzaInfo> call = api.getPizzaInfo();


        call.enqueue(new Callback<PizzaInfo>() {
            @Override
            public void onResponse(Call<PizzaInfo> call, Response<PizzaInfo> response) {

                System.out.println("Call: " + call);
                System.out.println("Response: " + response);
                pizzaInfo.setValue(response.body());

            }

            @Override
            public void onFailure(Call<PizzaInfo> call, Throwable t) {
                System.out.println("Throw: " + t.getLocalizedMessage());
            }
        });
    }



}
