package com.test.pizzacart.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.test.pizzacart.R;
import com.test.pizzacart.dialog.adapter.CrustRecyclerAdapter;
import com.test.pizzacart.databinding.AddPizzaDialogBinding;
import com.test.pizzacart.dialog.adapter.SizesRecyclerAdapter;
import com.test.pizzacart.model.CurrentCart;
import com.test.pizzacart.model.PizzaInfo;
import com.test.pizzacart.view.MainActivity;


public class AddPizzaDialog extends Dialog implements CrustRecyclerAdapter.CrustSelectInterface {

    private AddPizzaDialogBinding addPizzaDialogBinding;
    private CrustRecyclerAdapter crustRecyclerAdapter;
    private SizesRecyclerAdapter sizesRecyclerAdapter;
    private Context context;
    private PizzaInfo pizzaInfo;

    public AddPizzaDialog(@NonNull Context context, PizzaInfo pizzaInfo) {
        super(context);
        View v = getWindow().getDecorView();
        v.setBackgroundResource(android.R.color.transparent);
        addPizzaDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.add_pizza_dialog, null, false);
        setContentView(addPizzaDialogBinding.getRoot());
        this.context = context;
        this.pizzaInfo = pizzaInfo;
        setupCrusts();
        setupSizes((int) pizzaInfo.getDefaultCrust(),
                (int) pizzaInfo.getCrusts().get((int) pizzaInfo.getDefaultCrust()).getDefaultSize());
        onClick();
    }

    private void onClick() {
        addPizzaDialogBinding.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = pizzaInfo.getCrusts().get(crustRecyclerAdapter.getSelectedPos() + 1).getName() + " - "
                        + pizzaInfo.getCrusts().get(crustRecyclerAdapter.getSelectedPos() + 1).getSizes().get(sizesRecyclerAdapter.getSelectedPos() + 1).getName();
                long price = pizzaInfo.getCrusts().get(crustRecyclerAdapter.getSelectedPos() + 1).getSizes().get(sizesRecyclerAdapter.getSelectedPos()).getPrice();
                String id = pizzaInfo.getCrusts().get(crustRecyclerAdapter.getSelectedPos() + 1).getId() + "" +
                        pizzaInfo.getCrusts().get(crustRecyclerAdapter.getSelectedPos() + 1).getSizes().get(sizesRecyclerAdapter.getSelectedPos() + 1).getId();

                CurrentCart currentCart = new CurrentCart(name, 1, price, id);
                ((MainActivity) context).addPizzaUpdateMap(currentCart);
                dismiss();
            }
        });
    }

    private void setupCrusts() {
        addPizzaDialogBinding.crusts.setLayoutManager(new LinearLayoutManager(context));
        crustRecyclerAdapter = new CrustRecyclerAdapter(context, pizzaInfo.getCrusts(),
                (int) pizzaInfo.getDefaultCrust(), this);
        addPizzaDialogBinding.crusts.setAdapter(crustRecyclerAdapter);
    }

    private void setupSizes(int startSelection, int selectedPos) {
        addPizzaDialogBinding.sizes.setLayoutManager(new LinearLayoutManager(context));
        sizesRecyclerAdapter = new SizesRecyclerAdapter(context,
                pizzaInfo.getCrusts().get(startSelection).getSizes(),
                selectedPos);
        addPizzaDialogBinding.sizes.setAdapter(sizesRecyclerAdapter);
    }

    @Override
    public void crustSelect(int id) {
        setupSizes(id,
                (int) pizzaInfo.getCrusts().get(id).getDefaultSize());
    }

}
