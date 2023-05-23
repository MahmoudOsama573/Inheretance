package com.MahmmoudOsama.inheretance;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MahmmoudOsama.inheretance.databinding.ItemLayoutBinding;


import java.util.*;
public class FinalAdapter extends RecyclerView.Adapter<FinalAdapter.ViewHolder> {
    private ArrayList<Person> mData;

    public FinalAdapter(ArrayList<Person> data) {
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemLayoutBinding binding = ItemLayoutBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person currentData = mData.get(position);


            holder.binding.person.setText(MainActivity.heirsMapE.get(currentData.name));
            if (MainActivity.heirs_map.get(currentData.name)>1&&currentData.getFraction()!="الباقي للذكر مثل حظ الانثيين")holder.binding.fraction.setText("مشترك في "+currentData.fraction);
            else holder.binding.fraction.setText(currentData.fraction);
            holder.binding.amount.setText(currentData.amount + "");
           holder.binding.img.setImageResource(MainActivity.heirsImages.get(currentData.name));
           double h=100*currentData.amount/MainActivity.totalMoney;
        h=Math.round(h* 100.0) / 100.0;
        holder.binding.hundred.setText(h+" %");
//arr[0]+"."+arr[1].charAt(0)+arr[1].charAt(1)+

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemLayoutBinding binding;

        ViewHolder(ItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
