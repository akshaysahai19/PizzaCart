package com.test.pizzacart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.test.pizzacart.R;
import com.test.pizzacart.databinding.ItemLayoutBinding;
import com.test.pizzacart.model.Crusts;
import com.test.pizzacart.model.PizzaInfo;

import java.util.List;

public class CrustRecyclerAdapter extends RecyclerView.Adapter<CrustRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Crusts> crustsList;

    public CrustRecyclerAdapter(Context context, List<Crusts> crustsList) {
        this.context = context;
        this.crustsList = crustsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLayoutBinding itemLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_layout, parent, false);
        return new ViewHolder(itemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemLayoutBinding.title.setText(crustsList.get(position).getName()+" ");
    }

    @Override
    public int getItemCount() {
        return crustsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemLayoutBinding itemLayoutBinding;

        public ViewHolder(@NonNull ItemLayoutBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
        }
    }
}
