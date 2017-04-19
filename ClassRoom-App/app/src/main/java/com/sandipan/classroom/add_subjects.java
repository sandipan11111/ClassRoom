package com.sandipan.classroom;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class add_subjects extends AppCompatActivity {

    Button b1;
    ListView l1;
    EditText e1,e2;
    final ArrayList<String> al=new ArrayList<String>();
    SQLiteDatabase sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subjects);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        l1=(ListView)findViewById(R.id.listView);
        e1=(EditText)findViewById(R.id.subject);
        e2=(EditText)findViewById(R.id.teacher);
        b1=(Button)findViewById(R.id.submit);

        sql=openOrCreateDatabase("mydatabase",MODE_PRIVATE,null);
        sql.execSQL("create table if not exists test(subject varchar,teacher varchar,present integer,total integer,percentage float)");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject=e1.getText().toString();
                String teacher=e2.getText().toString();
                sql.execSQL("insert into test values('"+subject+"','"+teacher+"','"+0+"','"+0+"','"+0.00+"')");
                Toast.makeText(getApplicationContext(), "RECORDED", Toast.LENGTH_SHORT).show();
                e1.setText("");
                e2.setText("");
                al.clear();
                Cursor c=sql.rawQuery("select subject,teacher from test",null);
                c.moveToFirst();
                do {
                    al.add("Subject:" + "\t" + c.getString(c.getColumnIndex("subject"))+"\tTeacher Name:"+c.getString(c.getColumnIndex("teacher")));
                }while (c.moveToNext());

                ArrayAdapter a=new ArrayAdapter(add_subjects.this,android.R.layout.simple_dropdown_item_1line,al);
                l1.setAdapter(a);




            }
        });



    }

}
