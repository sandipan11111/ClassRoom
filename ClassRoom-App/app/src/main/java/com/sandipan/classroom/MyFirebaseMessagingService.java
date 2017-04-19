package com.sandipan.classroom;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.content.SharedPreferencesCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sandipan on 4/3/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                //LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast();
                sendPushNotification(json);
                //startActivity(new Intent(this,Navigation.class));
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }

    }

    private void sendPushNotification(JSONObject json) {
        //optionally we can display the json into log
        Log.e(TAG, "Notification JSON " + json.toString());
        try {
            //getting the json data
            JSONObject data = json.getJSONObject("data");

            //parsing json data
            String title = data.getString("title");
            String message = data.getString("message");
            String id=data.getString("id");
            Intent pushNotification = new Intent(SharedPrefManager.PUSH_NOTIFICATION);
            //Adding notification data to the intent
            pushNotification.putExtra("message", message);
            pushNotification.putExtra("name", title);
            pushNotification.putExtra("id", id);
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(pushNotification);

            //creating MyNotificationManager object
            MyNotificationManager mNotificationManager = new MyNotificationManager(getApplicationContext());



            //creating an intent for the notification
            Intent intent = new Intent(getApplicationContext(), Navigation.class);


            //if there is no image

                //displaying small notification
                mNotificationManager.showSmallNotification(title, message, intent);


        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

}

