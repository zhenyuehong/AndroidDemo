package com.mtelnet.myview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mtelnet.myview.view.ColorfulRingProgressView;


public class ColorRingProgressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_ring_progress);

        ColorfulRingProgressView crpv = (ColorfulRingProgressView) findViewById(R.id.crpv);
        crpv.setPercent(75);
        crpv.setStartAngle(0);
        crpv.setFgColorStart(0xffC01207);
        crpv.setFgColorEnd(0xff950403);
        crpv.setStrokeWidthDp(12);

    }
}
