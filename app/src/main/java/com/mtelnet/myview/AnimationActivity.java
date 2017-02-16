package com.mtelnet.myview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

public class AnimationActivity extends AppCompatActivity {
    private View animView;
    private ImageView showImage;
    private Animation scaleAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        animView = LayoutInflater.from(this).inflate(R.layout.anim_layout, null);

        showImage = (ImageView) findViewById(R.id.img);

    }

    public void fromLeft(View view) {
        showImage.setVisibility(View.VISIBLE);
        showImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.in_from_left));
    }

    public void fromRight(View view) {
        showImage.setVisibility(View.VISIBLE);
        showImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.in_from_right));
    }

    public void fromUp(View view) {
        showImage.setVisibility(View.VISIBLE);
        showImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.in_from_up));
    }

    public void fromDown(View view) {
        showImage.setVisibility(View.VISIBLE);
        showImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.in_from_down));
    }

    public void scaleToScreen(View view) {
        showImage.setVisibility(View.VISIBLE);
        scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scaleanim);
        showImage.startAnimation(scaleAnimation);
    }

    public void scaleToAllWidth(View view){
        showImage.setVisibility(View.VISIBLE);

    }
    public void scaleToAllHeight(View view){
        showImage.setVisibility(View.VISIBLE);

    }
    public void scaleToAllScreen(View view){
        showImage.setVisibility(View.VISIBLE);

        final int[] locOfHeadlineView = new int[2];
        showImage.getLocationOnScreen(locOfHeadlineView);

        ObjectAnimator anim=ObjectAnimator.ofFloat(showImage,"scale",
                0,1f);
        anim.setDuration(5000);
        anim.setTarget(showImage);
        anim.setInterpolator(new LinearInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i("scale", "scaleAnimator" );
                int width = getResources().getDisplayMetrics().widthPixels;
                int height =getResources().getDisplayMetrics().heightPixels;
                ViewGroup.LayoutParams params = showImage.getLayoutParams();
                params.width = width;
                params.height = height;
                showImage.setLayoutParams(params);
            }
        });

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(AnimationActivity.this,"end",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        anim.start();



    }


}
