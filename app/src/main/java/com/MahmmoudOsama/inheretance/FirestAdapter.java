package com.MahmmoudOsama.inheretance;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MahmmoudOsama.inheretance.databinding.PersonLayoutBinding;


import java.util.*;
public class FirestAdapter extends RecyclerView.Adapter<FirestAdapter.ViewHolder> {
private ArrayList<Person> mData;
public FirestAdapter(ArrayList<Person> data) {
        this.mData = data;
        }
@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PersonLayoutBinding binding = PersonLayoutBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
        }
@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person currentData = mData.get(position);
    holder.binding.person.setText(MainActivity.heirsMapE.get(currentData.name));
    holder.binding.img.setImageResource(MainActivity.heirsImages.get(currentData.name));

        }
@Override
public int getItemCount() {
        return mData.size();
        }

static class ViewHolder extends RecyclerView.ViewHolder {
    PersonLayoutBinding binding;

    ViewHolder(PersonLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
        }
