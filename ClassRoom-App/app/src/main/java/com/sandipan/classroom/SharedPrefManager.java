package com.sandipan.classroom;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by sandipan on 4/3/2017.
 */

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "FCMSharedPref";
    private static final String KEY_ACCESS_TOKEN = "token";
    public static final String USER_ID = "userid";
    public static final String USER_NAME = "username";
    public static final String USER_EMAIL= "useremail";
    public static final String IS_LOGGED_IN= "is_logged_in";
    public static  String STATUS_MY="status";
    public static String SUBJECT="sub";

    public static final String PUSH_NOTIFICATION = "pushnotification";
    //public static final int NOTIFICATION_ID = 235;

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //this method will save the device token to shared preferences
    public boolean storeToken(String token){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ACCESS_TOKEN, token);
        editor.apply();
        return true;
    }

    //this method will fetch the device token from shared preferences
    public String getDeviceToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(KEY_ACCESS_TOKEN, null);
    }

    //This method will store the user data on sharedpreferences
    //It will be called on login
    public void loginUser(int id,String name, String email) {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(USER_ID,id);
        editor.putString(USER_EMAIL, email);
        editor.putString(USER_NAME, name);
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.apply();
    }

    public void storesubject(String subject){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(SUBJECT,subject);
        editor.apply();
    }

    public void storestatus(String status){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(STATUS_MY,status);
        editor.apply();
        //Toast.makeText(mCtx,status,Toast.LENGTH_LONG).show();
    }

    public void logout()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.clear();
        editor.apply();
    }

    //This method will check whether the user is logged in or not
    public boolean isLoggedIn() {
        return mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE).getBoolean(IS_LOGGED_IN, false);
    }

    //This method will return the user id of logged in user
    public int getUserId() {
        return mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE).getInt(USER_ID, -1);
    }

    //This method will return the username of logged in user
    public String getUserName() {
        return mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE).getString(USER_NAME, null);
    }
    public  String getEmail(){
        return mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE).getString(USER_EMAIL,null);
    }
    public String getstatus(){
        return mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE).getString(STATUS_MY,"No Status Yet!!!!!");
    }
    public String getsubject(){
        return mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE).getString(SUBJECT,null);
    }

}