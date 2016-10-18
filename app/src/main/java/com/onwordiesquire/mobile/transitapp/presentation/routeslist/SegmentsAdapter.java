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

//            Glide.with(context).load(R.drawable.ic_bus).placeholder(R.drawable.ic_bus).into(segmentIcon);
            //set icon
            segmentIcon.setImageResource(bindIconResource(segment.iconUrl()));

            //set name

            name_txt.setText(segment.name());
            int color = Color.parseColor(segment.color());

            //set background color
            background.setBackgroundColor(color);


//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                segmentIcon.setImageTintList(ColorStateList.valueOf(color));
//            }

        }

        /**
         * returns the appropriate resource id based on the provided url
         *
         * @param segmentIconURl
         * @return
         */
        private int bindIconResource(String segmentIconURl) {
            int resID;
            switch (segmentIconURl) {
                case "https://d3m2tfu2xpiope.cloudfront.net/vehicles/subway.svg":
                    resID = R.drawable.ic_subway;
                    break;
                case "https://d3m2tfu2xpiope.cloudfront.net/vehicles/walking.svg":
                    resID = R.drawable.ic_walking;
                    break;
                case "https://d3m2tfu2xpiope.cloudfront.net/vehicles/bus.svg":
                    resID = R.drawable.ic_bus;
                    break;
                case "https://d3m2tfu2xpiope.cloudfront.net/vehicles/change.svg":
                    resID = R.drawable.ic_change;
                    break;
                case "https://d3m2tfu2xpiope.cloudfront.net/vehicles/driving.svg":
                    resID = R.drawable.ic_car;
                    break;
                case "https://d3m2tfu2xpiope.cloudfront.net/vehicles/parking.svg":
                    resID = R.drawable.ic_park;
                    break;
                case "https://d3m2tfu2xpiope.cloudfront.net/vehicles/setup.svg":
                    resID = R.drawable.ic_setup;
                    break;
                case "https://d3m2tfu2xpiope.cloudfront.net/vehicles/bike_sharing.svg":
                    resID = R.drawable.ic_bike_sharing;
                    break;
                case "https://d3m2tfu2xpiope.cloudfront.net/vehicles/cycling.svg":
                    resID = R.drawable.ic_cycling;
                default:
                    resID = R.drawable.ic_not_available;

            }

            return resID;
        }
    }
}
