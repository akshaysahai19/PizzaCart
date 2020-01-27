package com.test.pizzacart.dialog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.test.pizzacart.R;
import com.test.pizzacart.databinding.ItemLayoutBinding;
import com.test.pizzacart.model.Sizes;

import java.util.List;

public class SizesRecyclerAdapter extends RecyclerView.Adapter<SizesRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Sizes> sizesList;
    private int selectedPos = 99;

    public SizesRecyclerAdapter(Context context, List<Sizes> sizesList, int selectedPos) {
        this.context = context;
        this.sizesList = sizesList;
        this.selectedPos = selectedPos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLayoutBinding itemLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_layout, parent, false);
        return new ViewHolder(itemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.itemLayoutBinding.price.setVisibility(View.VISIBLE);
        holder.itemLayoutBinding.title.setText(sizesList.get(position).getName());
        holder.itemLayoutBinding.price.setText("₹" + sizesList.get(position).getPrice());
        if (selectedPos == position) {
            holder.itemLayoutBinding.selected.setVisibility(View.VISIBLE);
        } else {
            holder.itemLayoutBinding.selected.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedPos(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sizesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemLayoutBinding itemLayoutBinding;

        public ViewHolder(@NonNull ItemLayoutBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
            this.itemLayoutBinding = itemLayoutBinding;
        }
    }

    public void setSelectedPos(int selectedPos) {
        this.selectedPos = selectedPos;
        notifyDataSetChanged();
    }

    public int getSelectedPos() {
        return selectedPos;
    }
}