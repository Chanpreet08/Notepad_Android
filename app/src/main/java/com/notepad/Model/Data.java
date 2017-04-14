package com.notepad.Model;

/**
 * Created by cc on 14/4/17.
 */

public class Data {

    private int id;
    private String name;
    private String date;
    private String notes;

    public Data()
    {
        id=0;
        name="";
        date="";
        notes="";
    }

    public Data(String name, String date, String notes)
    {
        this.name= name;
        this.date = date;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}