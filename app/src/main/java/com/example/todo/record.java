package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;
import com.example.todo.classes.pageTwoAdapter;
import com.example.todo.classes.task;
import com.example.todo.classes.tasks;
import com.google.gson.Gson;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class record extends AppCompatActivity {
    ListView listView;

    SharedPreferences sharedPreferences;
    pageTwoAdapter pageTwoAdapter;
    TextView numtasks;
    tasks tasks=MainActivity.tasksInfo;
    public static String data="data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        sharedPreferences=getSharedPreferences("MySharedPref",MODE_PRIVATE);
        listView=findViewById(R.id.listview);
        numtasks=findViewById(R.id.numTasks);
        numtasks.setText("num:"+tasks.tasksFinished.size());
        pageTwoAdapter=new pageTwoAdapter(getApplicationContext(),tasks.tasksFinished);
        listView.setAdapter(pageTwoAdapter);
    }
}