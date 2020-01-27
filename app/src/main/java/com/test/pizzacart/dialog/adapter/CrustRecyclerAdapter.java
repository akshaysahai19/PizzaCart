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
import com.test.pizzacart.dialog.AddPizzaDialog;
import com.test.pizzacart.model.Crusts;

import java.util.List;

public class CrustRecyclerAdapter extends RecyclerView.Adapter<CrustRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Crusts> crustsList;
    private int selectedPos = 99;
    private CrustSelectInterface crustSelectInterface;

    public CrustRecyclerAdapter(Context context, List<Crusts> crustsList, int selectedPos, CrustSelectInterface crustSelectInterface) {
        this.context = context;
        this.crustsList = crustsList;
        this.selectedPos = selectedPos;
        this.crustSelectInterface = crustSelectInterface;

    }

    public interface CrustSelectInterface{

        void crustSelect(int id);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLayoutBinding itemLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_layout, parent, false);
        return new ViewHolder(itemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.itemLayoutBinding.price.setVisibility(View.GONE);
        holder.itemLayoutBinding.title.setText(crustsList.get(position).getName());
        if (selectedPos == position) {
            holder.itemLayoutBinding.selected.setVisibility(View.VISIBLE);
        } else {
            holder.itemLayoutBinding.selected.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crustSelectInterface.crustSelect((int)crustsList.get(position).getId());
                setSelectedPos(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return crustsList.size();
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
