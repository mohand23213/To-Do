package com.example.todo.classes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.todo.R;

import java.util.ArrayList;

public class adapter extends BaseAdapter{
    Context context;
    ArrayList<task> tasks;
    LayoutInflater layoutInflater;
    public adapter(Context context, ArrayList<task> tasks){
        layoutInflater=LayoutInflater.from(context);
        this.tasks=tasks;
    }
    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1=layoutInflater.inflate(R.layout.taskitem,null);
        TextView title=(TextView) view1.findViewById(R.id.title);
        CheckBox checkBox=(CheckBox) view1.findViewById(R.id.checkbox);
        title.setText(tasks.get(i).taskName);
        checkBox.setChecked(tasks.get(i).isDone);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tasks.get(i).isDone=!tasks.get(i).isDone;
            }
        });
        return  view1;
    }
}
