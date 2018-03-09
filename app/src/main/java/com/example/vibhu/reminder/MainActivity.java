package com.example.vibhu.reminder;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    ListView rootListView;
    customadapter newAdapter;
    ArrayList<itemdetails> arrayList;
    ExpenseOpenHelper openHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootListView=findViewById(R.id.rootListView);
        arrayList=new ArrayList<>();
        openHelper=ExpenseOpenHelper.getInstance(this);
        arrayList=fetchFromDB();
        newAdapter=new customadapter(arrayList,this);
        rootListView.setAdapter(newAdapter);
        rootListView.setOnItemClickListener(this);
        rootListView.setOnItemLongClickListener(this);


    }

    private ArrayList<itemdetails> fetchFromDB() {
        ArrayList<itemdetails> helper=new ArrayList<>();
        SQLiteDatabase database=openHelper.getReadableDatabase();
        Cursor cursor=database.query(Constants.Items.TableName,null,null,null,null,null,null);
        while (cursor.moveToNext())
        {
            int columnindex=cursor.getColumnIndex(Constants.Items.Name);
            String name=cursor.getString(columnindex);
            int id=cursor.getInt(cursor.getColumnIndex(Constants.Items.ID));
            itemdetails newItem=new itemdetails(name,id);
            helper.add(newItem);
        }
        return helper;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.add)
        {
            int addsize=arrayList.size()+1;
            itemdetails addItem=new itemdetails("new title"+addsize);
            SQLiteDatabase addDB=openHelper.getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put(Constants.Items.Name,addItem.getName());
            long id1=addDB.insert(Constants.Items.TableName,null,contentValues);
            addItem.setId((int) id1);
            arrayList.add(addItem);
            newAdapter.notifyDataSetChanged();
        }
        else if(id==R.id.exit)
        {
            finish();
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        itemdetails sendItem=arrayList.get(i);
        String sendName=sendItem.getName();
        int itemid=sendItem.getId();
        Log.d("itemmain",itemid+"");
        Intent intent=new Intent(this,Details.class);
        intent.putExtra("name",sendName);
        intent.putExtra("position",i);
        intent.putExtra("itemid",sendItem.getId());
        startActivityForResult(intent,2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2)
        {
            if(resultCode==2)
            {
                String Name=data.getStringExtra("afteredit");
                int position=data.getIntExtra("position",-1);
                itemdetails finalitem=arrayList.get(position);
                finalitem.setName(Name);
                SQLiteDatabase editDB=openHelper.getWritableDatabase();
                String[] where={finalitem.getId()+""};
                int itemId=data.getIntExtra("itemid",-1);
                Log.d("check",itemId+"");
                editDB.delete(Constants.Items.TableName,Constants.Items.ID+"=?",where);
                ContentValues contentValues=new ContentValues();
                contentValues.put(Constants.Items.Name,Name);
                contentValues.put(Constants.Items.ID,itemId);
                editDB.insert(Constants.Items.TableName,null,contentValues);
                newAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        SQLiteDatabase removeDB=openHelper.getWritableDatabase();
        itemdetails newItem=arrayList.get(i);
        String name=newItem.getName();
        int itemid=newItem.getId();
        String[] wheregs={itemid+""};
        removeDB.delete(Constants.Items.TableName,Constants.Items.ID+"=?",wheregs);
        arrayList.remove(i);
        newAdapter.notifyDataSetChanged();
        return true;
    }
}
