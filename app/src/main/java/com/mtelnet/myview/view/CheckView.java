package com.mtelnet.myview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hongzhenyue on 16/12/7.
 */

public class CheckView extends View {
    private Context mContext;           // 上下文
    private int mWidth, mHeight;        // 宽高
    private Handler mHandler;           // handler

    private Paint mPaint;
    private Bitmap okBitmap;

    public CheckView(Context context) {
        super(context,null);
    }

    public CheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext=context;

        mPaint=new Paint();
        mPaint.setColor(0xffFF5317);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);


    }
}
