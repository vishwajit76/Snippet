package com.cyberparkitsolutions.prajapatiassociates.view.wizard.model;

import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.cyberparkitsolutions.prajapatiassociates.fragments.PostFragmentProperty;
import com.cyberparkitsolutions.prajapatiassociates.utils.AppUtils;

import java.util.ArrayList;


/**
 * Created by vishwajit on 11-06-2018.
 */

public class PropertyPage extends Page {

    public static final String DATA_PROPERTY_TYPE = "property_type";
    public static final String DATA_BHK = "bhk";
    public static final String DATA_RK = "rk";
    public static final String DATA_BUDGET_MIN = "budget_min";
    public static final String DATA_BUDGET_MAX = "budget_max";

    public PropertyPage(ModelCallbacks callbacks, String title) {
        super(callbacks, title);
    }

    @Override
    public Fragment createFragment() {
        return PostFragmentProperty.create(getKey());
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItem> dest) {
        dest.add(new ReviewItem("property_type", mData.getString(DATA_PROPERTY_TYPE), getKey(), -1));
        dest.add(new ReviewItem("bhk", mData.getString(DATA_BHK), getKey(), -1));
        dest.add(new ReviewItem("rk", mData.getString(DATA_RK), getKey(), -1));
        dest.add(new ReviewItem("budget_min", mData.getString(DATA_BUDGET_MIN), getKey(), -1));
        dest.add(new ReviewItem("budget_max", mData.getString(DATA_BUDGET_MAX), getKey(), -1));

    }

    @Override
    public boolean isValidate() {

        if (TextUtils.isEmpty(mData.getString(DATA_PROPERTY_TYPE))) {
            AppUtils.showAlert("Invalid","Please select property type");
            return false;
        }

        if (TextUtils.isEmpty(mData.getString(DATA_BHK)) && TextUtils.isEmpty(mData.getString(DATA_RK))) {

            AppUtils.showAlert("Invalid","Please select BHK or RK");
            return false;

        }else{

            return true;

        }



    }


    @Override
    public boolean isCompleted() {

        if (TextUtils.isEmpty(mData.getString(DATA_PROPERTY_TYPE)) ) {

            return false;

        }

        if (TextUtils.isEmpty(mData.getString(DATA_BHK)) && TextUtils.isEmpty(mData.getString(DATA_RK))){

            return false;

        }else{
            return true;
        }

    }
}
