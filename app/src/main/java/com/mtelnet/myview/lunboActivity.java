package com.mtelnet.myview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mtelnet.myview.view.FlyBanner;

import java.util.ArrayList;
import java.util.List;
//图片轮播
public class lunboActivity extends AppCompatActivity {

    String imageUrl[] = {"http://devsvr6.mtel.ws/hkco/upload/banner/20161024_0436111036.png",
            "http://devsvr6.mtel.ws/hkco/upload/banner/20161024_0435501893.png",
            "http://devsvr6.mtel.ws/hkco/upload/banner/20161024_0436299053.png"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunbo);

//        ViewPager viewPager = (ViewPager) findViewById(R.id.fortess_banner_viewpager);
//        LinearLayout ll_dot_container = (LinearLayout) findViewById(R.id.ll_dot_container);

        List<String> list = new ArrayList<>();
        list.add(imageUrl[0]);
        list.add(imageUrl[1]);
        list.add(imageUrl[2]);

        FlyBanner fly_banner = (FlyBanner) findViewById(R.id.fly_banner);
        fly_banner.setImagesUrl(list);

        fly_banner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Log.d("FlyBanner", "onItemClick: "+position);
            }
        });


    }
}
