package com.notepad.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.notepad.Model.Data;

import java.util.ArrayList;

/**
 * Created by cc on 14/4/17.
 */

public class Dbhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="notepad";
    public static final int DATABASE_VERSION=1;
    public static final String TABLE_NAME="notes";
    public static final String _id="_id";
    public static final String name="name";
    public static final String date = "date";
    public static final String notes ="text";
    private static Context context;
    SQLiteDatabase db;

    Dbhelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase dbname) {
        db = dbname;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                + _id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + name
                + " TEXT, " + date+ " TEXT, "+notes +" TEXT )";
        dbname.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+TABLE_NAME);
        onCreate(db);
    }

    public void add(Data data)
    {
        ContentValues cv = new ContentValues();
        cv.put(name,data.getName());
        cv.put(date,data.getDate());
        cv.put(notes,data.getNotes());
        db.insert(TABLE_NAME,null,cv);
    }

    public ArrayList<Data> getData()
    {
        ArrayList<Data> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selected = " SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(selected,null);
        if(cursor.moveToFirst())
        {
            do{
                Data d = new Data();
                d.setId(cursor.getInt(0));
                d.setName(cursor.getString(1));
                d.setDate(cursor.getString(2));
                d.setNotes(cursor.getString(3));
                list.add(d);
            }
            while(cursor.moveToNext());
        }
        return list;
    }
}
