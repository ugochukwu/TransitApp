package com.onwordiesquire.mobile.transitapp.presentation.routedetails;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by michelonwordi on 10/19/16.
 */

public class RouteCardView extends CardView {
    public RouteCardView(Context context) {
        super(context);
    }

    public RouteCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RouteCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        return super.onRequestFocusInDescendants(direction, previouslyFocusedRect);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return super.onInterceptTouchEvent(ev);
        return true;

    }
}
