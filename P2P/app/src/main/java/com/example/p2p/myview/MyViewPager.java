package com.example.p2p.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class MyViewPager extends ViewPager {
    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean dispatchHoverEvent(MotionEvent event) {
        
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                getParent().requestDisallowInterceptTouchEvent(true);

                break;
        }
        
        return super.dispatchHoverEvent(event);
    }
}
