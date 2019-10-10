package com.cyberparkitsolutions.prajapatiassociates.utils;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.cyberparkitsolutions.prajapatiassociates.AppLifeApp;
import com.cyberparkitsolutions.prajapatiassociates.activities.FirstActivity;

/**
 * Created by vishwajit on 18-06-2017.
 */

public class DefaultExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler defaultUEH;
    Activity activity;

    public DefaultExceptionHandler(Activity activity) {
        this.activity = activity;

    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        Log.e("APPUE",ex.getMessage());
        ex.printStackTrace();

        Crashlytics.logException(ex);
        Crashlytics.log(ex.getMessage());

        Intent intent = new Intent(activity, FirstActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);



        PendingIntent pendingIntent = PendingIntent.getActivity(AppLifeApp.getInstance().getBaseContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        //Restart your app after 2 seconds
        AlarmManager mgr = (AlarmManager) AppLifeApp.getInstance().getBaseContext()
        .getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100,pendingIntent);
        //finishing the activity.
        activity.finish();
        //Stopping application
        System.exit(2);


    }
}