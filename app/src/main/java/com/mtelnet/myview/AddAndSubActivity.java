package com.mtelnet.myview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mtelnet.myview.view.AddAndSubView;

public class AddAndSubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_and_sub);

        final AddAndSubView mView= (AddAndSubView) findViewById(R.id.add_and_sub);
        mView.setNum(10000);
        mView.setAddAmount(1000);
    }
}
