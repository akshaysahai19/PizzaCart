package com.test.pizzacart.dialog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.test.pizzacart.R;
import com.test.pizzacart.databinding.RemoveItemCardBinding;
import com.test.pizzacart.model.CurrentCart;
import com.test.pizzacart.view.MainActivity;

import java.util.List;

public class RemovePizzaAdapter extends RecyclerView.Adapter<RemovePizzaAdapter.ViewHolder> {

    Context context;
    List<CurrentCart> currentCartList;
    RemovePizzaInterface removePizzaInterface;

    public RemovePizzaAdapter(Context context, List<CurrentCart> currentCartList, RemovePizzaInterface removePizzaInterface) {
        this.context = context;
        this.currentCartList = currentCartList;
        this.removePizzaInterface = removePizzaInterface;
    }

    public interface RemovePizzaInterface {
        void updateRemove(CurrentCart currentCart);
    }

    @NonNull
    @Override
    public RemovePizzaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RemoveItemCardBinding removeItemCardBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.remove_item_card, parent, false);
        return new ViewHolder(removeItemCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final RemovePizzaAdapter.ViewHolder holder, int position) {
        final CurrentCart currentCart = currentCartList.get(position);
        holder.removeItemCardBinding.name.setText(currentCart.getTitle());
        holder.removeItemCardBinding.quant.setText("Quantity : " + currentCart.getQuantity());
        holder.removeItemCardBinding.price.setText("Price : ₹" + currentCart.getPrice());

        holder.removeItemCardBinding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentCart.getQuantity() > 0) {
                    removePizzaInterface.updateRemove(currentCart);
//                    holder.removeItemCardBinding.quant.setText("Quantity : " + (currentCart.getQuantity()));
                } else {
                    removePizzaInterface.updateRemove(currentCart);
//                    holder.removeItemCardBinding.quant.setText("Quantity : " + (0));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return currentCartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RemoveItemCardBinding removeItemCardBinding;

        public ViewHolder(@NonNull RemoveItemCardBinding removeItemCardBinding) {
            super(removeItemCardBinding.getRoot());
            this.removeItemCardBinding = removeItemCardBinding;
        }
    }
}
