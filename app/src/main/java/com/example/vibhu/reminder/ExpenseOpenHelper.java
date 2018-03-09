package com.example.vibhu.reminder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VIBHU on 3/1/2018.
 */

public class ExpenseOpenHelper extends SQLiteOpenHelper {
    private static ExpenseOpenHelper openHelper;
    public static ExpenseOpenHelper getInstance(Context context)
    {
        if(openHelper==null)
        {
            openHelper=new ExpenseOpenHelper(context.getApplicationContext());
        }
        return openHelper;
    }
    private ExpenseOpenHelper(Context context) {
        super(context,Constants.DatabaseName,null,Constants.Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String expensesSql= " CREATE TABLE " + Constants.Items.TableName + " ( " + Constants.Items.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Constants.Items.Name + " TEXT)";
        sqLiteDatabase.execSQL(expensesSql);
        String commentsSql= " CREATE TABLE " + Constants.Comments.TableName + " ( " + Constants.Comments.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Constants.Comments.Comment + " TEXT, " + Constants.Comments.POS + " INTEGER)";
        sqLiteDatabase.execSQL(commentsSql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
