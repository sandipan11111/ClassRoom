package com.sandipan.classroom;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class List extends AppCompatActivity {
    ListView list;
    String subject;
    final ArrayList<String> al=new ArrayList<String>();
    SQLiteDatabase sql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        sql=openOrCreateDatabase("mydatabase",MODE_PRIVATE,null);
        list=(ListView)findViewById(R.id.list);

        al.clear();
        Cursor c=sql.rawQuery("select subject,teacher from test",null);
        c.moveToFirst();
        do {
            al.add(c.getString(c.getColumnIndex("subject")));
        }while (c.moveToNext());

        ArrayAdapter a=new ArrayAdapter(List.this,android.R.layout.simple_dropdown_item_1line,al);
        list.setAdapter(a);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                subject = String.valueOf(parent.getItemAtPosition(position));
                //Intent i=new Intent(List.this,Attendance.class);
                //i.putExtra("subjectmsg",subject);
                SharedPrefManager.getInstance(getApplicationContext()).storesubject(subject);
                Toast.makeText(List.this,subject,Toast.LENGTH_LONG).show();
                Intent i=new Intent(List.this,Attendance.class);
                startActivity(i);

            }
        });

    }

}
