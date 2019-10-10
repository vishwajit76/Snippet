package com.cyberparkitsolutions.jbcc.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ServerValue;

@IgnoreExtraProperties
public class Gallery {

  @Exclude
  public  String gallery_id;

  public  String uid;
  public  String name;
  public  String download_url;
  public  Object timestamp;


  public Gallery() {
  }

  public Gallery(String uid,String name, String download_url) {
    this.uid = uid;
    this.name = name;
    this.download_url = download_url;
    this.timestamp = ServerValue.TIMESTAMP;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  @Exclude
  public String getGallery_id() {
    return gallery_id;
  }

  @Exclude
  public void setGallery_id(String gallery_id) {
    this.gallery_id = gallery_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDownload_url() {
    return download_url;
  }

  public void setDownload_url(String download_url) {
    this.download_url = download_url;
  }

  @Exclude
  public long getTimestampLong(){
    return (long)timestamp;
  }

}
