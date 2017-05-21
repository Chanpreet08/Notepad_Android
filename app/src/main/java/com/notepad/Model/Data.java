package com.notepad.Model;

/**
 * Created by cc on 14/4/17.
 */

public class Data {

    private int id;
    private String title;
    private String content;
    private String dateCreated;
    private String dateModified;

    public Data()
    {
        id=0;
        title="";
        content="";
        dateCreated="";
        dateModified="";
    }

    public Data(String title, String content, String dateCreated,String dateModified)
    {
        this.title= title;
        this.content = content;
        this.dateCreated = dateCreated;
        this.dateModified=dateModified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }
}