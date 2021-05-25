package com.example.doit.Model;

public class ToDoModel {
    // class variables - each task has three attributes
    // queries of database done on the basis of id, status as int : 1 for done 0 for not done
    private int id, status;
    private String task;

    //getter and setter functions using inbuilt android studio functions
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
