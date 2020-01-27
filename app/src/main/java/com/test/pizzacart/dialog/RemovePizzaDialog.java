package com.test.pizzacart.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.test.pizzacart.R;
import com.test.pizzacart.databinding.RemovePizzaDialogBinding;
import com.test.pizzacart.dialog.adapter.RemovePizzaAdapter;
import com.test.pizzacart.model.CurrentCart;
import com.test.pizzacart.view.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemovePizzaDialog extends Dialog implements RemovePizzaAdapter.RemovePizzaInterface {

    private RemovePizzaDialogBinding removePizzaDialogBinding;
    private Context context;
    private RemovePizzaAdapter removePizzaAdapter;
    private HashMap<String, CurrentCart> currentCartHashMap;

    public RemovePizzaDialog(@NonNull Context context, HashMap<String, CurrentCart> currentCartHashMap) {
        super(context);
        View v = getWindow().getDecorView();
        v.setBackgroundResource(android.R.color.transparent);
        removePizzaDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.remove_pizza_dialog, null, false);
        setContentView(removePizzaDialogBinding.getRoot());
        this.context = context;
        this.currentCartHashMap = currentCartHashMap;
        loadRecyclerView();
        onClick();
    }

    private void onClick() {
        removePizzaDialogBinding.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void loadRecyclerView() {
        removePizzaDialogBinding.items.setLayoutManager(new LinearLayoutManager(context));
        removePizzaAdapter = new RemovePizzaAdapter(context, getList(), this);
        removePizzaDialogBinding.items.setAdapter(removePizzaAdapter);
    }

    private List<CurrentCart> getList() {
        List<CurrentCart> currentCartList = new ArrayList<>();
        for (Map.Entry<String, CurrentCart> entries : currentCartHashMap.entrySet()) {
            currentCartList.add(entries.getValue());
        }
        return currentCartList;
    }

    @Override
    public void updateRemove(CurrentCart currentCart) {
        dismiss();
        ((MainActivity) context).removePizzaUpdateView(currentCart);
        Toast.makeText(context, context.getResources().getString(R.string.removed), Toast.LENGTH_SHORT).show();
    }


}
