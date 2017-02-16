package com.mtelnet.myview;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.mtelnet.myview.adapter.BannerPageAdapter;
import com.mtelnet.myview.tutorial.TutorialPresenter;

import java.util.ArrayList;
import java.util.List;

public class AutoChangeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    String imageUrl[] = {"http://devsvr6.mtel.ws/hkco/upload/banner/20161024_0436111036.png",
            "http://devsvr6.mtel.ws/hkco/upload/banner/20161024_0435501893.png",
            "http://devsvr6.mtel.ws/hkco/upload/banner/20161024_0436299053.png"};

    //Init for auto scroll viewpager
    private Handler mHandler = new Handler();

    private int bannerDelay = 2000;

    private ViewPager banner_viewpager;
    private LinearLayout ll_layout;


    private int numberOfBanners = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_change);
        initData();
        initView();

    }


    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        autoScrollBanner();
    }

    private void initView() {

        List<String> list = new ArrayList<>();
        list.add(imageUrl[0]);
        list.add(imageUrl[1]);
        list.add(imageUrl[2]);

        banner_viewpager = (ViewPager) findViewById(R.id.banner_viewpager);
        ll_layout = (LinearLayout) findViewById(R.id.ll_dot_container);

        BannerPageAdapter adapter = new BannerPageAdapter(this);
        adapter.setList(list);

        TutorialPresenter.initTutorialPanel(ll_layout, this, list.size());

        banner_viewpager.setAdapter(adapter);
        banner_viewpager.setOnPageChangeListener(this);
        numberOfBanners = list.size();
        if (numberOfBanners > 1) {
            banner_viewpager.setCurrentItem(banner_viewpager.getChildCount() * Integer.MAX_VALUE / 2, true);
            autoScrollBanner();
        }
    }

    private void initData() {

    }

    private void autoScrollBanner() {
        mHandler.removeCallbacksAndMessages(null);
        Runnable mRunnable = new Runnable() {
            @Override
            public void run() {
                if (banner_viewpager != null) {
                    int currentPage = banner_viewpager.getCurrentItem();
//                    if (currentPage == NUM_PAGES - 1) {
//                        currentPage = 0;
//                    } else

                    Log.d("currentPage:", currentPage+"");
                    currentPage = currentPage + 1;

                    banner_viewpager.setCurrentItem(currentPage, true);
                }
                mHandler.postDelayed(this, bannerDelay);
            }
        };
        mHandler.postDelayed(mRunnable, bannerDelay);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (numberOfBanners > 1)
            TutorialPresenter.redrawDotPanel(ll_layout, position % numberOfBanners, 5);
        else
            TutorialPresenter.redrawDotPanel(ll_layout, position, 5);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
