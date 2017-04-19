 package com.sandipan.classroom;

 import android.content.Intent;
 import android.content.SharedPreferences;
 import android.os.Bundle;
 import android.support.v7.app.AppCompatActivity;
 import android.view.View;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.TextView;

 public class Status extends AppCompatActivity {
     /*Navigate n= new Navigate();*/
     //MyDBHandler dbHandler;
     TextView recordsTextView;
     EditText statusinput;
     Button nonabutton;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_status);
          nonabutton=(Button) findViewById(R.id.statusbutton);
         //recordsTextView = (TextView) findViewById(R.id.yourstatusid);
        /* dbHandler=new MyDBHandler(this, null, null, 1);
         String dbString = dbHandler.databaseToString();
         recordsTextView.setText(dbString);*/
     }

     public void onClick(View view) {
         //statusinput = (EditText) findViewById(R.id.statusinput);
         //String usermessage = statusinput.getText().toString();
         //Products product = new Products(usermessage);
         //dbHandler.addProduct(product);
         //String dbString = dbHandler.databaseToString();
         //recordsTextView.setText(dbString);
         Intent i = new Intent(this, Photo.class);
         statusinput = (EditText) findViewById(R.id.statusinput);
         String usermessage = statusinput.getText().toString();
         //SharedPrefManager.getInstance(getApplicationContext()).storestatus(usermessage);
         i.putExtra("statusmessage",usermessage);
           startActivity(i);
     }
 }