package com.cyberparkitsolutions.prajapatiassociates.view.wizard.model;

import android.support.v4.app.Fragment;

import com.cyberparkitsolutions.prajapatiassociates.fragments.PostFragmentLast;
import com.cyberparkitsolutions.prajapatiassociates.utils.AppUtils;

import java.util.ArrayList;

/**
 * Created by vishwajit on 11-06-2018.
 */

public class LastPage extends Page {

    public static final String DATA_PS_UC = "under_construction";
    public static final String DATA_PS_NC = "newly_construction";
    public static final String DATA_PS_RESALE = "resale";
    public static final String DATA_AREA_FROM = "area_from";
    public static final String DATA_AREA_TO = "area_to";
    public static final String DATA_AREA_UNIT = "area_unit";
    public static final String DATA_ADD_REQUIREMENT = "addition_requirement";
    public static final String DATA_PB_OWNER = "post_by_owner";
    public static final String DATA_PB_AGENTS = "post_by_agents";
    public static final String DATA_PB_DEVELOPER = "post_by_developer";
    public static final String DATA_CONTACT_AGENT = "contact_agent";


    public LastPage(ModelCallbacks callbacks, String title) {
        super(callbacks, title);
    }

    @Override
    public Fragment createFragment() {
        return PostFragmentLast.create(getKey());
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItem> dest) {
        dest.add(new ReviewItem("under_construction", mData.getBoolean(DATA_PS_UC), getKey(), -1));
        dest.add(new ReviewItem("newly_construction", mData.getBoolean(DATA_PS_NC), getKey(), -1));
        dest.add(new ReviewItem("resale", mData.getBoolean(DATA_PS_RESALE), getKey(), -1));
        dest.add(new ReviewItem("area_from", mData.getString(DATA_AREA_FROM), getKey(), -1));
        dest.add(new ReviewItem("area_to", mData.getString(DATA_AREA_TO), getKey(), -1));
        dest.add(new ReviewItem("area_unit", mData.getString(DATA_AREA_UNIT), getKey(), -1));
        dest.add(new ReviewItem("addition_requirement", mData.getString(DATA_ADD_REQUIREMENT), getKey(), -1));
        dest.add(new ReviewItem("post_by_owner", mData.getBoolean(DATA_PB_OWNER), getKey(), -1));
        dest.add(new ReviewItem("post_by_agents", mData.getBoolean(DATA_PB_AGENTS), getKey(), -1));
        dest.add(new ReviewItem("post_by_developer", mData.getBoolean(DATA_PB_DEVELOPER), getKey(), -1));
        dest.add(new ReviewItem("contact_agent", mData.getBoolean(DATA_CONTACT_AGENT), getKey(), -1));

    }

    @Override
    public boolean isValidate() {

        if (mData.getBoolean(DATA_PB_OWNER) || mData.getBoolean(DATA_PB_AGENTS) || mData.getBoolean(DATA_PB_DEVELOPER)){


            /*&& !TextUtils.isEmpty(mData.getString(DATA_RK))
                    && !TextUtils.isEmpty(mData.getString(DATA_BUDGET_MIN))
                    && !TextUtils.isEmpty(mData.getString(DATA_BUDGET_MAX))*/

            return true;
        } else {
            AppUtils.showAlert("Invalid","Please select posted by");
            return false;
        }

    }

    @Override
    public boolean isCompleted() {

        if (mData.getBoolean(DATA_PB_OWNER) || mData.getBoolean(DATA_PB_AGENTS) || mData.getBoolean(DATA_PB_DEVELOPER)){

            //AppUtils.showAlert("Invalid","Please select posted by");
            /*&& !TextUtils.isEmpty(mData.getString(DATA_RK))
                    && !TextUtils.isEmpty(mData.getString(DATA_BUDGET_MIN))
                    && !TextUtils.isEmpty(mData.getString(DATA_BUDGET_MAX))*/

            return true;
        } else {
            return false;
        }

    }
}
