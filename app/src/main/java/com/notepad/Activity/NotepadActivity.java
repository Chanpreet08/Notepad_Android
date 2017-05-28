package com.notepad.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.notepad.Adapter.Dbhelper;
import com.notepad.Model.Data;
import com.notepad.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NotepadActivity extends AppCompatActivity {

    private EditText title;
    private EditText content;
    private Calendar calender;
    private SimpleDateFormat simpleDateFormat;
    private String date;
    private String stringTitle="";
    private String stringContent="";
    private int id;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
        bundle = getIntent().getExtras();

        title =(EditText) findViewById(R.id.title);
        content = (EditText) findViewById(R.id.content);
        if(bundle!=null)
        {
            stringTitle=bundle.getString("title");
            stringContent= bundle.getString("content");
            id=bundle.getInt("id");
            title.setText(stringTitle);
            content.setText(stringContent);
        }
    }

    private void save()
    {
        calender = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        date = simpleDateFormat.format(calender.getTime());
        Dbhelper dbhelper = new Dbhelper(NotepadActivity.this);
        Data data = new Data();
        if(bundle!=null)
        {
            data.setTitle(title.getText().toString());
            data.setContent(content.getText().toString());
            data.setDateCreated(date);
            data.setDateModified(date);
            dbhelper.upDateRow(id,data);
        }
        else
        {

            data.setTitle(title.getText().toString());
            data.setContent(content.getText().toString());
            data.setDateCreated(date);
            data.setDateModified(date);
            dbhelper.add(data);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        save();
    }
}
