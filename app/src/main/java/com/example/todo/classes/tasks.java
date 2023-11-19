package com.example.todo.classes;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class tasks{
    public tasks(task[] list){
        tasksInprogress=new ArrayList<>();
        tasksFinished=new ArrayList<>();
        for(int i=0;i<list.length;i++){
            Log.d("isDone",list[i].taskName+"");
            if(list[i].isDone){
                Log.d("taskFished",list[i].isDone+"");
                tasksFinished.add(list[i]);
            }
            else{
                tasksInprogress.add(new task(list[i].taskName));
            }
        }
    }
    public ArrayList<task> tasksInprogress;
    public ArrayList<task> tasksFinished;
    int elementCount(){
        return tasksInprogress.size();
    }
    void addelement(String x){
        tasksInprogress.add(new task(x));
    }
    List taskSet(){
        return tasksInprogress;
    }
    void  delete(){
        ArrayList<task> copy = new ArrayList<>(tasksInprogress);
        for(int i=0;i<copy.size();i++){
            if(copy.get(i).isDone()){
                tasksInprogress.remove(copy.get(i));
            }
        }
    }
    public void updateArrayList() {
        ArrayList<task> list=new ArrayList();
        list.addAll(tasksInprogress);
        for(int i=0;i<list.size();i++){
            if(list.get(i).isDone){
                tasksFinished.add(list.get(i));
                Log.d("finished",tasksFinished.size()+"");
                tasksInprogress.remove(list.get(i));
            }
        }
        //tasks.addAll(list);
    }
}