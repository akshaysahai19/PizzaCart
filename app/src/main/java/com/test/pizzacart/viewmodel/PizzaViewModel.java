package com.test.pizzacart.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.pizzacart.api.Api;
import com.test.pizzacart.model.PizzaInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PizzaViewModel extends ViewModel {

    //this is the data that we will fetch asynchronously
    private MutableLiveData<PizzaInfo> pizzaInfo;
    private String description = "";
    private String title = "";

    //we will call this method to get the data
    public LiveData<PizzaInfo> getPizzas() {
        //if the list is null
        pizzaInfo = new MutableLiveData<PizzaInfo>();
        //we will load it asynchronously from server in this method
        loadPizzaApi();

        //finally we will return the list
        return pizzaInfo;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadPizzaApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
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
                //finally we are setting the list to our MutableLiveData
                pizzaInfo.setValue(response.body());
                setDescription(pizzaInfo.getValue().getDescription());
                setTitle(pizzaInfo.getValue().getName());

            }

            @Override
            public void onFailure(Call<PizzaInfo> call, Throwable t) {
                System.out.println("Throw: " + t.getLocalizedMessage());
            }
        });
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }


    public String getTitle() {
        return this.title;
    }


}
