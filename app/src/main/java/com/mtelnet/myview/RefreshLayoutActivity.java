package com.mtelnet.myview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.mtelnet.myview.view.RefreshLayout;

public class RefreshLayoutActivity extends AppCompatActivity {
    RefreshLayout mRefreshLayout;        TextView tv_refresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_layout);

        mRefreshLayout = (RefreshLayout) findViewById(R.id.refresh_layout);

        tv_refresh = (TextView) findViewById(R.id.tv_refresh);
        tv_refresh.setText("haha");

        mRefreshLayout.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e("RefreshLayoutActivity", "onRefresh(): " + "正在刷新");
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        Log.e("MainActivity", "63行-run(): " + "刷新完毕");
//                        if (getActivity() != null) {
//                            Toast.makeText(getActivity(), "刷新完毕", Toast.LENGTH_SHORT).show();
//                            mRefreshLayout.setRefreshing(false);
//                        }
                        tv_refresh.setText("刷新完毕");
//                        Toast.makeText(RefreshLayoutActivity.this, "刷新完毕", Toast.LENGTH_SHORT).show();
                        mRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

    }
}
