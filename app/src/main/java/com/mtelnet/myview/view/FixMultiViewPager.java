package com.mtelnet.myview.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by hongzhenyue on 16/12/27.
 */

public class FixMultiViewPager extends ViewPager {
    private static final String TAG = FixMultiViewPager.class.getSimpleName();

    public FixMultiViewPager(Context context) {
        super(context);
    }

    public FixMultiViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            Log.w(TAG, "onInterceptTouchEvent() ", ex);
            ex.printStackTrace();
        }
        return false;
    }

}
