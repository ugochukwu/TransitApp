package com.onwordiesquire.mobile.transitapp.presentation.routedetails;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onwordiesquire.mobile.transitapp.R;
import com.onwordiesquire.mobile.transitapp.data.model.Segment;
import com.onwordiesquire.mobile.transitapp.util.Utilities;
import com.vipul.hp_hp.timelineview.TimelineView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by michelonwordi on 10/19/16.
 */

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.TimeLineViewHolder> {

    private List<Segment> data;

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline_list_item, parent, false);
        return new TimeLineViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(List<Segment> segments) {
        this.data = segments;
    }

    public class TimeLineViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.time_marker)
        TimelineView tlView;


        public TimeLineViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Segment segment)
        {
            int color = Color.parseColor(segment.color());
            Drawable drawable = tlView.getContext().getResources().getDrawable(Utilities.bindIconResource(segment.iconUrl()));
            Drawable wrap = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(wrap,color);
            tlView.setMarker(drawable);
            tlView.setMarkerSize(56);
            tlView.setEndLine(new ColorDrawable(color));
//            tlView.

        }
    }
}
