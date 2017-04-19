package com.sandipan.classroom;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.OutputStream;

public class Photo extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView buckysImageView;
    //MyDBHandler dbHandler;
    TextView statusText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        Button buckyButton = (Button) findViewById(R.id.buckysButton);
        buckysImageView = (ImageView) findViewById(R.id.buckysImageView);
        statusText= (TextView) findViewById(R.id.statustextid);
        // dbHandler = new MyDBHandler(this, null, null, 1);
        //printDatabse();
        //Disable the button if the user has no camera
        if(!hasCamera())
        {
            buckyButton.setEnabled(false);

        }
        try{
            File f = new File(getFilesDir(),"photo.png");
            if(f.exists()){
                Bitmap bmp = BitmapFactory.decodeFile(f.getAbsolutePath());
                buckysImageView.setImageBitmap(bmp);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Bundle statusData=getIntent().getExtras();
        if(statusData==null)
        {
            statusText.setText(SharedPrefManager.getInstance(getApplicationContext()).getstatus());
            return;
        }
        String statusmessage= statusData.getString("statusmessage");
        SharedPrefManager.getInstance(getApplicationContext()).storestatus(statusmessage);
        statusText.setText(SharedPrefManager.getInstance(getApplicationContext()).getstatus());
        //statusText.setText(statusmessage);
        //Products product = new Products(statusmessage);
        //dbHandler.addProduct(product);
    }

  /*  public void printDatabse()
    {
        String dbString = dbHandler.databaseToString();
        if(dbString.equals(""))
            return;
        statusText.setText(dbString);
    }*/
    //Check if the user has a camera
    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    //Launching the camera
    public void launchCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Take a picture and pass results along to onActivityResult
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    public void launchCross(View view) {
        buckysImageView.setImageResource(R.drawable.no_profile);
    }

    //If you want to return the image taken
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK && requestCode==REQUEST_IMAGE_CAPTURE) { //Get the photo
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            try {
                OutputStream outp = this.openFileOutput("photo.png", this.MODE_PRIVATE);
                photo.compress(Bitmap.CompressFormat.PNG, 100, outp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //buckysImageView.setImageBitmap(photo);

        try{
            File f = new File(getFilesDir(),"photo.png");
            if(f.exists()){
                Bitmap bmp = BitmapFactory.decodeFile(f.getAbsolutePath());
                buckysImageView.setImageBitmap(bmp);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

