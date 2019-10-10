package com.cyberparkitsolutions.prajapatiassociates.view.wizard.model;

import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.cyberparkitsolutions.prajapatiassociates.fragments.PostFragmentMain;

import java.util.ArrayList;

/**
 * Created by vishwajit on 11-06-2018.
 */

public class MainPage extends Page {

    public static final String ACTION_DATA_KEY = "action_type";
    public static final String ACTION_DATA_ID = "rb_id" ;


    public MainPage(ModelCallbacks callbacks, String title) {
        super(callbacks, title);
    }

    @Override
    public Fragment createFragment() {
        return PostFragmentMain.create(getKey());
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItem> dest) {
        dest.add(new ReviewItem("action_type", mData.getString(ACTION_DATA_KEY), getKey(), -1));
        //dest.add(new ReviewItem("rb_id", mData.getString(ACTION_DATA_ID), getKey(), -1));
    }

    @Override
    public boolean isCompleted() {
        return !TextUtils.isEmpty(mData.getString(ACTION_DATA_KEY));
    }

}
