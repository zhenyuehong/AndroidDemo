package com.mtelnet.myview.jsoup.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mtelnet.myview.jsoup.bean.Cmenu;
import com.mtelnet.myview.jsoup.fragment.FoodFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongzhenyue on 16/12/29.
 */

public class JsoupPageAdapter extends FragmentStatePagerAdapter {
    private List<FoodFragment> mFragmentList = new ArrayList<>();
    private List<Cmenu> mMenu;


    public JsoupPageAdapter(FragmentManager fm, List<Cmenu> menu, List<FoodFragment> fragment) {
        super(fm);
        this.mFragmentList = fragment;
        this.mMenu = menu;
    }


    @Override
    public Fragment getItem(int position) {
        FoodFragment mFragment = null;
        if (mFragment == null) {
            mFragment = mFragmentList.get(position);
            mFragment.getData(mMenu);
        }
        return mFragment;
    }

    @Override
    public int getCount() {
        return mFragmentList.size() > 0 ? mFragmentList.size() : 0;
    }
}
