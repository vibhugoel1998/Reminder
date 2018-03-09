package com.example.vibhu.reminder;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by VIBHU on 3/2/2018.
 */

public class Comments extends AppCompatActivity {
    EditText editText;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments);
        editText=findViewById(R.id.addcomments);
        Intent intent=getIntent();
        position=intent.getIntExtra("commentpos",-1);
    }



    public void NewComments(View view)
    {
       String comment=editText.getText().toString();
       Intent intent=new Intent();
       intent.putExtra("finalcomment",comment);
       intent.putExtra("commentpos",position);
       setResult(4,intent);
       finish();
    }
}
