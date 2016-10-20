package com.onwordiesquire.mobile.transitapp.presentation.routeslist;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.onwordiesquire.mobile.transitapp.R;
import com.onwordiesquire.mobile.transitapp.data.model.Segment;
import com.onwordiesquire.mobile.transitapp.data.model.Stop;
import com.onwordiesquire.mobile.transitapp.util.Utilities;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by michelonwordi on 10/18/16.
 */

public class SegmentsAdapter extends RecyclerView.Adapter<SegmentsAdapter.SegmentsItemHolder> {

    private List<Segment> segments;

    @Override
    public SegmentsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.segment_list_item, parent, false);

        return new SegmentsItemHolder(view);
    }

    @Override
    public void onBindViewHolder(SegmentsItemHolder holder, int position) {
        holder.bind(segments.get(position));
    }

    public void setData(List<Segment> segments) {
        this.segments = segments;
    }

    @Override
    public int getItemCount() {
        return segments == null ? 0 : segments.size();
    }

    public class SegmentsItemHolder extends RecyclerView.ViewHolder {

        private Segment segment;

        @BindView(R.id.segment_icon)
        ImageView segmentIcon;
        @BindView(R.id.name)
        TextView name_txt;
        @BindView(R.id.background)
        LinearLayout background;

        public SegmentsItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Segment segment) {
            Context context = segmentIcon.getContext();
            this.segment = segment;

            //set icon
            segmentIcon.setImageResource(Utilities.bindIconResource(segment.iconUrl()));

            //set name

            name_txt.setText(segment.name());
            int color = Color.parseColor(segment.color());

            //set background color
            background.setBackgroundColor(color);

        }


    }
}
