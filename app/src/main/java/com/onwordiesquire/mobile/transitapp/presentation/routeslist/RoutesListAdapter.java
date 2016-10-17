package com.onwordiesquire.mobile.transitapp.presentation.routeslist;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onwordiesquire.mobile.transitapp.R;
import com.onwordiesquire.mobile.transitapp.data.model.Route;
import com.onwordiesquire.mobile.transitapp.databinding.RouteListItemBinding;

import java.util.List;

/**
 * Created by michelonwordi on 10/17/16.
 */

public class RoutesListAdapter extends RecyclerView.Adapter<RoutesListAdapter.RoutesItemViewHolder> {


    private List<RouteViewModel> data;

    @Override
    public RoutesItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.route_list_item,parent,false);
        return new RoutesItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RoutesItemViewHolder holder, int position) {
        holder.bindData(data.get(position));
    }

    public void setData(List<RouteViewModel> routes) {
        this.data = routes;
    }

    @Override
    public int getItemCount() {
        return this.data == null ? 0 : this.data.size();
    }

    class RoutesItemViewHolder extends RecyclerView.ViewHolder {

        private final RouteListItemBinding binding;

        public RoutesItemViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bindData(RouteViewModel rvm)
        {
            binding.setRoute(rvm);
        }
    }
}
