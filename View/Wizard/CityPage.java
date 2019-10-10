package com.cyberparkitsolutions.prajapatiassociates.view.wizard.model;

import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.cyberparkitsolutions.prajapatiassociates.fragments.PostFragmentCity;
import com.cyberparkitsolutions.prajapatiassociates.utils.AppUtils;

import java.util.ArrayList;

/**
 * Created by vishwajit on 11-06-2018.
 */

public class CityPage extends Page {

    public static final String DATA_CITY = "city";
    public static final String DATA_LOCALITY = "locality" ;

    public CityPage(ModelCallbacks callbacks, String title) {
        super(callbacks, title);
    }

    @Override
    public Fragment createFragment() {
        return PostFragmentCity.create(getKey());
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItem> dest) {
        dest.add(new ReviewItem("city", mData.getString(DATA_CITY), getKey(), -1));
        dest.add(new ReviewItem("locality", mData.getString(DATA_LOCALITY), getKey(), -1));
    }

    @Override
    public boolean isValidate() {

        if(TextUtils.isEmpty(mData.getString(DATA_CITY))){

            AppUtils.showAlert("Invalid","Please Enter City Name");
            return false;
        } if(TextUtils.isEmpty(mData.getString(DATA_LOCALITY))){

            AppUtils.showAlert("Invalid","Please Enter Locality");
            return false;

        }else{
            return true;
        }

    }

    @Override
    public boolean isCompleted() {



        if(TextUtils.isEmpty(mData.getString(DATA_CITY))){

            //AppUtils.showAlert("Invalid","Please Enter City Name");
            return false;
        } if(TextUtils.isEmpty(mData.getString(DATA_LOCALITY))){

            //AppUtils.showAlert("Invalid","Please Enter Locality");
            return false;

        }else{
            return true;
        }

    }



}
