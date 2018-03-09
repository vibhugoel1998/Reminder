package com.example.vibhu.reminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by VIBHU on 3/3/2018.
 */

public class commentscustomadapter extends BaseAdapter {
    ArrayList<CommentsObject> CommentsArray;
    Context context;

    public commentscustomadapter(ArrayList<CommentsObject> commentsArray, Context context) {
        CommentsArray = commentsArray;
        this.context = context;
    }

    @Override
    public int getCount() {
        return CommentsArray.size();
    }

    @Override
    public CommentsObject getItem(int i) {
        return CommentsArray.get(i);
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
            view1 = inflater.inflate(R.layout.commentscustomadapter, viewGroup, false);
            ViewHolder holder=new ViewHolder();
            holder.titleTextView=view1.findViewById(R.id.commentstextview);
            view1.setTag(holder);
        }
        ViewHolder holder1=(ViewHolder) view1.getTag();
        CommentsObject expense=getItem(i);
        holder1.titleTextView.setText(expense.getComment());
        return view1;
    }
    public class ViewHolder{
        TextView titleTextView;

    }
}
