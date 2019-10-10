package com.yourpackagename;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ServerValue;

@IgnoreExtraProperties
public class LastActivity {

    @Exclude
    public  String activity_id;

    public  String uid;
    public String message;
    public  Object timestamp;

    public LastActivity() {
    }

    public LastActivity(String uid, String message) {
        this.uid = uid;
        this.message = message;
        this.timestamp = ServerValue.TIMESTAMP;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    @Exclude
    public long getTimestampLong(){
        return (long)timestamp;
    }

}
