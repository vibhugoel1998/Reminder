package com.example.vibhu.reminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by VIBHU on 3/1/2018.
 */

public class customadapter extends BaseAdapter {
  ArrayList<itemdetails> arrayList;
  Context context;

    public customadapter(ArrayList<itemdetails> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @Override

    public int getCount() {
        return arrayList.size();
    }

    @Override
    public itemdetails getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = view;
        if(view==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view1 = inflater.inflate(R.layout.customadapter, viewGroup, false);
            ViewHolder holder=new ViewHolder();
            holder.titleTextView=view1.findViewById(R.id.name);
            view1.setTag(holder);
        }
        ViewHolder holder1=(ViewHolder) view1.getTag();
        itemdetails expense=getItem(i);
        holder1.titleTextView.setText(expense.getName());
        return view1;


    }
    public class ViewHolder{
        TextView titleTextView;

    }
}
