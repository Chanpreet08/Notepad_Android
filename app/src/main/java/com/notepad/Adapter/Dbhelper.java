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
    public static final String title="title";
    public static final String dateCreated = "dateCreated";
    public static final String dateModified ="dateModified";
    public static final String content ="content";
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
                + _id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + title
                + " TEXT, " + content+ " TEXT, "+dateCreated +" TEXT "+dateModified+" TEXT )";
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
        cv.put(title,data.getTitle());
        cv.put(content,data.getContent());
        cv.put(dateCreated,data.getDateCreated());
        cv.put(dateModified,data.getDateModified());
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
                d.setTitle(cursor.getString(1));
                d.setContent(cursor.getString(2));
                d.setDateCreated(cursor.getString(3));
                d.setDateModified(cursor.getString(4));
                list.add(d);
            }
            while(cursor.moveToNext());
        }
        return list;
    }
}
