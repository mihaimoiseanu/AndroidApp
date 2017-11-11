package com.example.androidapp.ui.main;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.androidapp.R;
import com.example.androidapp.commons.models.Developer;
import com.example.androidapp.databinding.ItemDevBinding;
import com.example.androidapp.utils.ActionHandler;

import java.util.ArrayList;
import java.util.List;

public class DevRecyclerAdapter extends RecyclerView.Adapter<DevRecyclerAdapter.DevViewHolder> {

    private List<Developer> devs = new ArrayList<>();
    private ActionHandler<Developer> handler;

    public DevRecyclerAdapter(ActionHandler<Developer> handler) {
        this.handler = handler;
    }

    @Override
    public DevViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DevViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()), R.layout.item_dev, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(DevViewHolder holder, int position) {
        holder.bind(devs.get(position), handler);
    }

    @Override
    public int getItemCount() {
        return devs.size();
    }

    public void setDevs(List<Developer> devs) {
        this.devs = devs;
        notifyDataSetChanged();
    }

    class DevViewHolder extends RecyclerView.ViewHolder {
        private ItemDevBinding binding;

        public DevViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemDevBinding) binding;
        }

        public void bind(Developer developer, ActionHandler<Developer> handler) {
            binding.setItem(developer);
            binding.setHandler(handler);
            binding.executePendingBindings();
        }
    }
}
