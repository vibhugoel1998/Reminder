package com.example.vibhu.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by VIBHU on 3/1/2018.
 */

public class Edit extends AppCompatActivity {
    EditText editText;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editingpage);
        editText=findViewById(R.id.edithere);
        Intent intent=getIntent();
        String edit=intent.getStringExtra("editname");
        position=intent.getIntExtra("position",-1);
        editText.setText(edit);
    }
    public void Submit(View view)
    {
        String afteredit=editText.getText().toString();
        Intent data=new Intent();
        data.putExtra("afteredit",afteredit);
        data.putExtra("position",position);
        setResult(1,data);
        finish();
    }
}
