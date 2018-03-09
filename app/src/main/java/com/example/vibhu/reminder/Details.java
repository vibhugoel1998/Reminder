package com.example.vibhu.reminder;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by VIBHU on 3/1/2018.
 */

public class Details extends AppCompatActivity {
    TextView textView;
    int position;
    ArrayList<CommentsObject> CommentsArray;
    ExpenseOpenHelper openHelper;
    commentscustomadapter commentscustom;
    ListView commentslist;
    int itemid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        textView=findViewById(R.id.detailsTextView);
        openHelper=ExpenseOpenHelper.getInstance(this);
        CommentsArray=fetchfromDB();
        commentslist=findViewById(R.id.commentslistview);
        commentscustom=new commentscustomadapter(CommentsArray,this);
        commentslist.setAdapter(commentscustom);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        position=intent.getIntExtra("position",-1);
        textView.setText(name);
        itemid=intent.getIntExtra("itemid",-1);
        Log.d("itemitent",itemid+"");
    }

    private ArrayList<CommentsObject> fetchfromDB() {
        SQLiteDatabase database=openHelper.getReadableDatabase();
        ArrayList<CommentsObject> helper=new ArrayList<>();
        //String args[]={itemid+""};
        Log.d("itemfetch",itemid+"");
       // String arg=Constants.Comments.POS+"=?";
        Cursor cursor=database.query(Constants.Comments.TableName,null,null,null,null,null,null);
        while (cursor.moveToNext())
        {
            int column=cursor.getColumnIndex(Constants.Comments.Comment);
            String comment=cursor.getString(column);
            int id=cursor.getInt(cursor.getColumnIndex(Constants.Comments.ID));
            int pos=cursor.getInt(cursor.getColumnIndex(Constants.Comments.POS));
            CommentsObject commentsObject=new CommentsObject(comment,id,pos);
            helper.add(commentsObject);
        }
        ArrayList<CommentsObject> helper1=new ArrayList<>();
        for(int m=0;m<helper.size();m++)
        {
            CommentsObject tryobject=helper.get(m);
            if(tryobject.getPosition()==itemid)
            {
                helper1.add(tryobject);
            }
        }
        return helper1;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent(this,Edit.class);
        intent.putExtra("editname",textView.getText());
        intent.putExtra("position",position);
        startActivityForResult(intent,1);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            if(resultCode==1)
            {
                String afterEdit=data.getStringExtra("afteredit");
                textView.setText(afterEdit);
                data.putExtra("itemid",itemid);
                setResult(2,data);
            }
        }
        if(requestCode==4)
        {
            if(resultCode==4)
            {
                String comment=data.getStringExtra("finalcomment");
                SQLiteDatabase database=openHelper.getWritableDatabase();
                ContentValues contentValues=new ContentValues();
                CommentsObject object=new CommentsObject(comment,itemid);
                contentValues.put(Constants.Comments.Comment,object.getComment());
               // contentValues.put(Constants.Comments.POS,itemid);
                Log.d("pos",itemid+"");
                long id=database.insert(Constants.Comments.TableName,null,contentValues);
                Log.d("id",id+"");
                object.setId((int)id);
                CommentsArray.add(object);
                commentscustom.notifyDataSetChanged();
            }
        }
    }
    public void AddComments(View view)
    {
        Intent intent=new Intent(this,Comments.class);
        intent.putExtra("commentpos",itemid);
        startActivityForResult(intent,4);
    }
}
