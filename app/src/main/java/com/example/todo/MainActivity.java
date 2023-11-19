package com.example.todo;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.todo.classes.adapter;
import com.example.todo.classes.tasks;
import com.example.todo.classes.task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static tasks tasksInfo;
    adapter adapter;
    ListView listView;
    Button addTask;
    Button recordPage;
    TextView taskOnList;
    Button done;
    SharedPreferences sharedPreferences;
    BottomSheetDialog dialog;
    public static String data="data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences=getSharedPreferences("MySharedPref",MODE_PRIVATE);
        tasksInfo=new tasks(getArrayfromSharedPref());
        recordPage=findViewById(R.id.records);
        done=findViewById(R.id.DoneButto);
        taskOnList=findViewById(R.id.taskOnList);
        listView=(ListView) findViewById(R.id.list_item);
        adapter=new adapter(getApplicationContext(),tasksInfo.tasksInprogress);
        listView.setAdapter(adapter);
        addTask=findViewById(R.id.addTaskButto);
        dialog=new BottomSheetDialog(this);
        dialog.create();
        dialog.setContentView(showBottomSheet());
        taskOnList.setText(tasksInfo.tasksInprogress.size()+" Task");
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        recordPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),record.class);
                startActivity(intent);
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tasksInfo.updateArrayList();
                listView.setAdapter(new adapter(getApplicationContext(),tasksInfo.tasksInprogress));
                taskOnList.setText(tasksInfo.tasksInprogress.size()+" Task");
                storeChanges(tasksInfo.tasksInprogress,tasksInfo.tasksFinished);
            }
        });

    }
    void storeChanges(ArrayList finished,ArrayList inprogress){/**/
        ArrayList list=new ArrayList();
        list.addAll(inprogress);
        list.addAll(finished);
        Gson gson=new Gson();
        String gsonFormat=gson.toJson(list);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(data,gsonFormat);
        editor.apply();
    }
    task[] getArrayfromSharedPref(){
        Gson gson=new Gson();
        String string=sharedPreferences.getString(data,"");
        if(!string.isEmpty()){
            task[] s=gson.fromJson(string,task[].class);
            return s;
        }
        task[] tasks= {};
        return tasks;
    }
    View showBottomSheet(){
        View view=getLayoutInflater().inflate(R.layout.bottomsheet,null);
        view.setMinimumHeight(600);
        Button submit=view.findViewById(R.id.submit);
        EditText editText=view.findViewById(R.id.addTaskFeild);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().length()!=0){
                    tasksInfo.tasksInprogress.add(new task(editText.getText().toString()));
                    listView.setAdapter(new adapter(getApplicationContext(),tasksInfo.tasksInprogress));
                    taskOnList.setText(tasksInfo.tasksInprogress.size()+" Task");
                    dialog.hide();
                    storeChanges(tasksInfo.tasksFinished,tasksInfo.tasksInprogress);
                    editText.setText("");
                }
            }
        });
        return view;
    }

}