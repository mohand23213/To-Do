package com.example.todo.classes;


public class task{
    public String taskName;
    boolean isDone;
    public task(String taskName){
        this.taskName=taskName;
        this.isDone=false;
    };
    void done(boolean done){
        this.isDone=done;
    }
    public boolean isDone(){
        return isDone;
    }

}