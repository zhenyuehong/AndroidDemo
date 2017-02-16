package com.mtelnet.myview.jsoup.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtelnet.myview.R;
import com.mtelnet.myview.jsoup.bean.Cmenu;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        getData(mMenu);
        return inflater.inflate(R.layout.fragment_food, container, false);
    }

    public void getData(List<Cmenu> menu) {
    }
}
