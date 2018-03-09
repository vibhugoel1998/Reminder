package com.example.vibhu.reminder;

import java.util.ArrayList;

/**
 * Created by VIBHU on 3/1/2018.
 */

public class itemdetails {
    private String name;
    private int id;

    public itemdetails(String name) {
        this.name = name;
        this.id=-1;
    }

    public itemdetails(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    static ArrayList<itemdetails> getItems(int size)
    {
        ArrayList<itemdetails> newList=new ArrayList<>();
        for(int i=0;i<size;i++)
        {
            String add=" new title"+i;
            itemdetails newitem= new itemdetails(add);
            newList.add(newitem);
        }
        return newList;
    }
}
