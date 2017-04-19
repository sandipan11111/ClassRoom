package com.sandipan.classroom;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Attendance extends AppCompatActivity{
    SQLiteDatabase sql;//=openOrCreateDatabase("mydatabase",MODE_PRIVATE,null);;
    int pres,total;
    TextView Subject;
    TextView TeacherName;
    TextView Classes_1;
    TextView Total_classes;
    TextView Attendance;
    TextView Classes_You_can_leave;
    TextView result;
    EditText enter_classes;
    Button present_button , absent_button,button;
    String subject;
    int percentage=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        //Bundle statusData=getIntent().getExtras();
        //if(statusData==null)
        // {
        //statusText.setText(SharedPrefManager.getInstance(getApplicationContext()).getstatus());
        //   return;
        //}
        //subject= statusData.getString("subjectmsg");
        subject=SharedPrefManager.getInstance(getApplicationContext()).getsubject();
        //Toast.makeText(this,String.valueOf(subject.length()),Toast.LENGTH_LONG).show();
        //Toast.makeText(this,subject,Toast.LENGTH_LONG).show();
        //subject+="";
        Subject=(TextView)findViewById(R.id.subjectid);
        TeacherName=(TextView)findViewById(R.id.teachernameid);
        Classes_1=(TextView)findViewById(R.id.canoid);
        Total_classes=(TextView)findViewById(R.id.tcnoid);
        Attendance=(TextView)findViewById(R.id.anoid);
        Classes_You_can_leave=(TextView)findViewById(R.id.cyclnoid);
        enter_classes=(EditText)findViewById(R.id.eclnoid);
        present_button=(Button)findViewById(R.id.presentbuttonid);
        absent_button=(Button)findViewById(R.id.absentbuttonid);
        button=(Button)findViewById(R.id.button);
        result=(TextView)findViewById(R.id.cyclnoid);
        sql=openOrCreateDatabase("mydatabase",MODE_PRIVATE,null);
        //String q="Select * from test where subject = " + subject;
        //Cursor c=sql.rawQuery(q,new String[]{});
        Cursor c=sql.rawQuery("select * from test",null);
        c.moveToFirst();
        do {
            String q=c.getString(c.getColumnIndex("subject"));
            //Toast.makeText(this,String.valueOf(q.length()),Toast.LENGTH_LONG).show();
            //Toast.makeText(this,q,Toast.LENGTH_LONG).show();
            if(q.equals(subject)) {
                Subject.setText(c.getString(c.getColumnIndex("subject")));
                TeacherName.setText(c.getString(c.getColumnIndex("teacher")));
                pres=c.getInt(c.getColumnIndex("present"));
                total=c.getInt(c.getColumnIndex("total"));
                Classes_1.setText(String.valueOf(pres));
                Total_classes.setText(String.valueOf(total));

                if(total!=0)
                    percentage=(pres*100)/total;
                Attendance.setText(String.valueOf(percentage));

            }

        }while (c.moveToNext());
        /*Cursor c= null;
        try
        {

            c = sql.rawQuery("SELECT * FROM " + "test" + " WHERE " + "subject" + "=?", new String[] { subject + "" });

            if (c.getCount() > 0)
            {
                c.moveToFirst();
            }

        }
        finally // IMPORTANT !!! Ensure cursor is not left hanging around ...
        {
            if(c != null)
                c.close();
        }*/
        //if(c==null)
        //  Toast.makeText(this,"No record found",Toast.LENGTH_LONG);
        //Subject.setText(subject);
        //Subject.setText(c.getString(c.getColumnIndex("subject")));
        //TeacherName.setText(c.getString(c.getColumnIndex("teacher")));

        /*pres=c.getInt(c.getColumnIndex("present"));
        total=c.getInt(c.getColumnIndex("total"));
        Classes_1.setText(String.valueOf(pres));
        Total_classes.setText(String.valueOf(total));

        if(total!=0)
            percentage=(pres/ total)*100;
        Attendance.setText(String.valueOf(percentage));
        //eclnoid.getText();*/
    }

    public void getpres(View view)
    {
        pres++;
        total++;
        //Toast.makeText(this, String.valueOf(pres), Toast.LENGTH_SHORT).show();
        sql.execSQL("Update test SET present='"+pres+"' ,total='"+total+"' where subject='"+subject+"' ");
        Intent intent=getIntent();
        finish();
        startActivity(intent);
    }
    public void getabs(View view)
    {
        total++;
        sql.execSQL("Update test SET present='"+pres+"' ,total='"+total+"' where subject='"+subject+"' ");
        Intent intent=getIntent();
        finish();
        startActivity(intent);
    }
    public void getcalc(View view)
    {
        int a;
        a=Integer.parseInt(enter_classes.getText().toString());
        //a=enter_classes.getText();
        int res;
        res=((3*(total+a))/4);
        int k=pres+a;
        if(k-res<0)
            res=0;
        else
            res=k-res;
        result.setText(String.valueOf(res));



    }
}