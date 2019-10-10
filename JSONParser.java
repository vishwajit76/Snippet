package com.cyberparkitsolutions.prajapatiassociates.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.crashlytics.android.Crashlytics;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;

import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;


/**
 * Created by vishwajit on 08-06-2018.
 */

public class JSONParser {

    /********
     * URLS
     *******/
    //private static final String MAIN_URL = "http://cyberparkitsolutions.com/prajapati/app/";
    private static final String MAIN_URL = "http://prajapatiassociate.com/admin/app/";
    /**
     * TAGs Defined Here...
     */
    public static final String TAG = "JSONParser";
    /**
     * Key to Send
     */
    private static final String KEY_USER_ID = "user_id";
    /**
     * Response
     */
    private static Response response;

    private static String json = "";

    private static JSONObject jsonObject = null;

    public boolean retryOnConnectionFailure = false;
    /**
     * Get Data From WEB
     *
     * @return JSON Object
     */
    public JSONObject getDataFromWeb(String url) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(MAIN_URL+url)
                    .build();

            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(response.body().toString() != null){
                jsonObject = new JSONObject(response.body().string());
            }

            //response = client.newCall(request).execute();
            return jsonObject ;
        } catch (@NonNull IOException | JSONException e) {
            Crashlytics.logException(e);
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }


    public JSONObject postDataToWeb(String url, RequestBody body) {
        try {
            OkHttpClient client = new OkHttpClient();
            client.setRetryOnConnectionFailure(retryOnConnectionFailure);
            Request request = new Request.Builder()
                    .url(MAIN_URL+url)
                    .post(body)
                    .build();

            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(response.body().toString() != null){
                jsonObject = new JSONObject(response.body().string());
            }

            //response = client.newCall(request).execute();
            return jsonObject ;
        } catch (@NonNull IOException | JSONException e) {
            Crashlytics.logException(e);
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }


    public String uploadImage(String url, File sourceFile,File sourceFile2 , String key , String value, String img ,String isEdit ) {

        try {
            //File sourceFile = new File(new URI(sourceImageFile));
           // File sourceFile  = new File(sourceImageFile);

            Log.d(TAG, "File...:::: uti - "+sourceFile.getPath()+" file -" + sourceFile + " : " + sourceFile.exists());

            final MediaType MEDIA_TYPE = sourceFile.getPath().endsWith("png") ?
                    MediaType.parse("image/png") : MediaType.parse("image/jpeg");

            final MediaType MEDIA_TYPE2 = sourceFile2.getPath().endsWith("png") ?
                    MediaType.parse("image/png") : MediaType.parse("image/jpeg");


            RequestBody requestBody = new MultipartBuilder()
                    .type(MultipartBuilder.FORM)
                    .addFormDataPart(key, value)
                    .addFormDataPart("img", img)
                    .addFormDataPart("isEdit",isEdit)
                    .addFormDataPart("image", sourceFile.getName(), RequestBody.create(MEDIA_TYPE, sourceFile))
                    .addFormDataPart("image2", sourceFile2.getName(), RequestBody.create(MEDIA_TYPE2, sourceFile2))
                    .build();



            Request request = new Request.Builder()
                    .url(MAIN_URL+url)
                    .post(requestBody)
                    .build();

            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            return  response.body().string();

        } catch (UnknownHostException | UnsupportedEncodingException e) {
            Crashlytics.logException(e);
            Log.e(TAG, "Error: " + e.getLocalizedMessage());
        } catch (Exception e) {
            Crashlytics.logException(e);
            Log.e(TAG, "Other Error: " + e.getLocalizedMessage());
        }
        return null;
    }


    public String uploadImage(Context context,RequestProgress requestProgress,String uid,String url, File sourceFile, String key , String value, String img) {

        try {
            //File sourceFile = new File(new URI(sourceImageFile));
            // File sourceFile  = new File(sourceImageFile);

            Log.d(TAG, "File...:::: uti - "+sourceFile.getPath()+" file -" + sourceFile + " : " + sourceFile.exists());

            /*final MediaType MEDIA_TYPE = sourceFile.getPath().endsWith("png") ?
                    MediaType.parse("image/png") : MediaType.parse("image/jpeg");*/

            //Uri uri = Uri.fromFile(sourceFile);
            //ContentResolver cR = context.getContentResolver();
            //String mime_type = cR.getType(uri);
            String mime_type = getMimeType(Uri.fromFile(sourceFile),context);
            final MediaType MEDIA_TYPE = MediaType.parse(mime_type);



            RequestBody requestBody = new MultipartBuilder()
                    .type(MultipartBuilder.FORM)
                    .addFormDataPart(key, value)
                    .addFormDataPart("img", img)
                    .addFormDataPart("uid", uid)
                    .addFormDataPart("mime",mime_type)
                    //.addFormDataPart("image", sourceFile.getName(), RequestBody.create(MEDIA_TYPE, sourceFile))
                    .addFormDataPart("image", sourceFile.getName(), createCustomRequestBody(requestProgress,MEDIA_TYPE, sourceFile))
                    .build();



            Request request = new Request.Builder()
                    .url(MAIN_URL+url)
                    .post(requestBody)
                    .build();

            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            return  response.body().string();

        } catch (UnknownHostException | UnsupportedEncodingException e) {
            Crashlytics.logException(e);
            Log.e(TAG, "Error: " + e.getLocalizedMessage());
        } catch (Exception e) {
            Crashlytics.logException(e);
            Log.e(TAG, "Other Error: " + e.getLocalizedMessage());
        }
        return null;
    }

    public static RequestBody createCustomRequestBody(final RequestProgress requestProgress,final MediaType contentType, final File file) {
        return new RequestBody() {
            @Override public MediaType contentType() {
                return contentType;
            }
            @Override public long contentLength() {
                return file.length();
            }
            @Override public void writeTo(BufferedSink sink) throws IOException {
                Source source = null;

                try {
                    source = Okio.source(file);
                    //sink.writeAll(source);
                    Buffer buf = new Buffer();
                    Long remaining = contentLength();
                    requestProgress.onProgressStart(remaining);
                    for (long readCount; (readCount = source.read(buf, 2048)) != -1; ) {
                        sink.write(buf, readCount);
                        float progress = Math.round(((contentLength() - remaining) * 100)/contentLength());
                        requestProgress.onProgressChanged(file.getName(),readCount,remaining,progress,0);
                        Log.d(TAG,"progress: "+progress + "source size: " + contentLength() + " remaining bytes: " + (remaining -= readCount));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }


    public void setRetryOnConnectionFailure(boolean retryOnConnectionFailure) {
        this.retryOnConnectionFailure = retryOnConnectionFailure;
    }

    public String getMimeType(Uri uri,Context context) {
        String mimeType = null;
        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            ContentResolver cr = context.getContentResolver();
            mimeType = cr.getType(uri);
        } else {
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
                    .toString());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    fileExtension.toLowerCase());
        }
        return mimeType;
    }

    public interface RequestProgress {

        void onProgressStart(long totalBytes);
        void onProgressChanged(String fileName,long numBytes, long totalBytes, float percent, float speed);
        void onUIProgressFinish();


    }
}
