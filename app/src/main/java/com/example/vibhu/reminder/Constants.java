package com.example.vibhu.reminder;

/**
 * Created by VIBHU on 3/1/2018.
 */

public class Constants {
    public static final String DatabaseName="items_db";
    public static final int Version=1;
    static class Items
    {
        public static final String TableName="ItemsList";
        public static final String ID="id";
        public static final String Name="name";
    }
    static class Comments
    {
        public static final String TableName="CommentsList";
        public static final String Comment="commentsinDB";
        public static final String ID="id";
        public static final String POS="position";
    }

}
