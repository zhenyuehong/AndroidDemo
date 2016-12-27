package com.mtelnet.myview.zoomanim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.mtelnet.myview.R;

public class DetailActivity extends AppCompatActivity {
    private static final String EXTRA_FEED_ITEM = "extra_feed_item";
    private static final String EXTRA_ZOOM_INFO = "extra_zoom_info";
    private ImageView mImageView;
    private View mBackgroundView;
    private FeedItem mFeedItem;

    public static void startActivity(Activity from, View sharedView, FeedItem item){
        if (item == null) return;
        Intent intent = new Intent(from, DetailActivity.class);
        intent.putExtra(EXTRA_FEED_ITEM, item);
        intent.putExtra(EXTRA_ZOOM_INFO, ZoomAnimationUtils.getZoomInfo(sharedView));
        from.startActivity(intent);
        from.overridePendingTransition(0,0);
    }

    private ZoomAnimationUtils.ZoomInfo mZoomInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initArguments();
        initView();
    }

    private void initArguments() {
        mFeedItem = getIntent().getParcelableExtra(EXTRA_FEED_ITEM);
        mZoomInfo = getIntent().getParcelableExtra(EXTRA_ZOOM_INFO);
    }

    private void initView() {
        mBackgroundView = findViewById(android.R.id.content);
        mImageView = (ImageView) findViewById(R.id.iv_detail);
        Glide.with(this)
                .load(mFeedItem.getImageUrl()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                setBitmap(resource);
                tryEnterAnimation();
            }
        });
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setBitmap(Bitmap bitmap) {
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = (int) (width*(mFeedItem.getHeight()*1f/mFeedItem.getWidth()));
        ViewGroup.LayoutParams params = mImageView.getLayoutParams();
        params.width = width;
        params.height = height;
        mImageView.setLayoutParams(params);
        mImageView.setImageBitmap(bitmap);
    }

    private void tryEnterAnimation() {
        ZoomAnimationUtils.startZoomUpAnim(mZoomInfo, mImageView, null);
        ZoomAnimationUtils.startBackgroundAlphaAnim(mBackgroundView,
                new ColorDrawable(getResources().getColor(android.R.color.black)), 0, 255);
    }

    private void tryExitAnimation() {
        ZoomAnimationUtils.startBackgroundAlphaAnim(mBackgroundView,
                new ColorDrawable(getResources().getColor(android.R.color.black)), 255, 0);
        ZoomAnimationUtils.startZoomDownAnim(mZoomInfo, mImageView, new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        tryExitAnimation();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
