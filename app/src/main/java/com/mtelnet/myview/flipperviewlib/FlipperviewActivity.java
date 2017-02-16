package com.mtelnet.myview.flipperviewlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mtelnet.myview.R;

public class FlipperviewActivity extends AppCompatActivity {
    private FlipperView mFlipperView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipperview);

        mFlipperView = (FlipperView) findViewById(R.id.flipper_view);
        View prevView = findViewById(R.id.prev_btn);
        View nextView = findViewById(R.id.next_btn);

        prevView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFlipperView.previous();
            }
        });
        nextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFlipperView.next();
            }
        });
    }
}
