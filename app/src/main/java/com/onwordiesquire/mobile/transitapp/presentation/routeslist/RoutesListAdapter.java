package com.onwordiesquire.mobile.transitapp.presentation.routeslist;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onwordiesquire.mobile.transitapp.R;
import com.onwordiesquire.mobile.transitapp.data.model.Route;
import com.onwordiesquire.mobile.transitapp.data.model.Segment;
import com.onwordiesquire.mobile.transitapp.databinding.RouteListItemBinding;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by michelonwordi on 10/17/16.
 */

public class RoutesListAdapter extends RecyclerView.Adapter<RoutesListAdapter.RoutesItemViewHolder> {


    private List<RouteViewModel> data;
    private ArrayMap<Route,List<Segment>> segmentsData;
    private SegmentsAdapter segmentsAdapter;

    @Override
    public RoutesItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.route_list_item,parent,false);
        RecyclerView segmentsRecycler = ButterKnife.findById(view, R.id.segments_recycler);
        segmentsRecycler.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        segmentsRecycler.setAdapter(new SegmentsAdapter());
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
            //pass view model to databinding code in layout
            binding.setRoute(rvm);

            //assign data to adapter and notify
            SegmentsAdapter adapter = (SegmentsAdapter) binding.segmentsRecycler.getAdapter();
            adapter.setData(rvm.segments());
            adapter.notifyDataSetChanged();
        }
    }
}
