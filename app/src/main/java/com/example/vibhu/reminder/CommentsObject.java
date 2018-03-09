package com.example.vibhu.reminder;

/**
 * Created by VIBHU on 3/2/2018.
 */

public class CommentsObject {
    private String comment;
    private int id;
    private int position;

    public CommentsObject(String comment, int id,int position) {
        this.comment = comment;
        this.id = id;
        this.position=position;
    }

    public CommentsObject(String comment,int position) {
        this.comment = comment;
        this.position=position;
        this.id=-1;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
