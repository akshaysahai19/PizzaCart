package com.test.pizzacart.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.test.pizzacart.R;
import com.test.pizzacart.databinding.ActivityMainBinding;
import com.test.pizzacart.dialog.AddPizzaDialog;
import com.test.pizzacart.dialog.RemovePizzaDialog;
import com.test.pizzacart.model.CurrentCart;
import com.test.pizzacart.model.PizzaInfo;
import com.test.pizzacart.viewmodel.PizzaViewModel;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    PizzaViewModel pizzaViewModel;
    public PizzaInfo pizzaInfo;
    HashMap<String, CurrentCart> currentCartHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        currentCartHashMap = new HashMap<>();
        pizzaViewModel = ViewModelProviders.of(this).get(PizzaViewModel.class);
        pizzaViewModel.getPizzas().observe(this, new Observer<PizzaInfo>() {
            @Override
            public void onChanged(@Nullable PizzaInfo pizzaInfoResponse) {

                pizzaInfo = pizzaInfoResponse;
                System.out.println("pizzas - " + pizzaInfoResponse);
                if (pizzaInfoResponse.isVeg()) {
                    activityMainBinding.vegNonveg.setColorFilter(ContextCompat.getColor(MainActivity.this,
                            R.color.veg), android.graphics.PorterDuff.Mode.SRC_IN);
                } else {
                    activityMainBinding.vegNonveg.setColorFilter(ContextCompat.getColor(MainActivity.this,
                            R.color.nonveg), android.graphics.PorterDuff.Mode.SRC_IN);
                }
            }
        });

        onClicks();
    }

    private void onClicks() {

        activityMainBinding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddPizzaDialog addPizzaDialog = new AddPizzaDialog(MainActivity.this, pizzaInfo);
                addPizzaDialog.show();
            }
        });

        activityMainBinding.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RemovePizzaDialog removePizzaDialog = new RemovePizzaDialog(MainActivity.this, currentCartHashMap);
                removePizzaDialog.show();
            }
        });
    }

    private void updateView() {
        activityMainBinding.done.setText("Quantity (" + getTotalQuantities() + " Pizzas) : Total - " + "₹" + getTotal());
    }

    private String getTotal() {
        long total = 0;
        for (Map.Entry<String, CurrentCart> entries
                : currentCartHashMap.entrySet()) {
            total += (entries.getValue().getPrice() * entries.getValue().getQuantity());
        }
        return String.valueOf(total);
    }

    private String getTotalQuantities() {
        long quant = 0;
        for (Map.Entry<String, CurrentCart> entries
                : currentCartHashMap.entrySet()) {
            quant += entries.getValue().getQuantity();
        }
        return String.valueOf(quant);
    }

    public void addPizzaUpdateMap(CurrentCart currentCart) {
        if (currentCartHashMap.containsKey(currentCart.getId())) {
            currentCartHashMap.get(currentCart.getId())
                    .setQuantity(currentCartHashMap.get(currentCart.getId()).getQuantity() + 1);
        } else {
            currentCartHashMap.put(currentCart.getId(), currentCart);
        }
        updateView();
    }


    public void removePizzaUpdateView(CurrentCart currentCart) {
        if (currentCartHashMap.containsKey(currentCart.getId())) {
            if (currentCartHashMap.get(currentCart.getId()).getQuantity() > 1) {
                currentCartHashMap.get(currentCart.getId())
                        .setQuantity(currentCartHashMap.get(currentCart.getId()).getQuantity() - 1);
            } else {
                currentCartHashMap.remove(currentCart.getId());
            }
        }
        updateView();
    }


}
