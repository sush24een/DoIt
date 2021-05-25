package com.example.doit.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.doit.AddNewTask;
import com.example.doit.MainActivity;
import com.example.doit.Model.ToDoModel;
import com.example.doit.R;
import com.example.doit.Utils.DatabaseHandler;

import android.widget.CheckBox;
import android.widget.CompoundButton;


import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
    // list of tasks in todoModel
    private List<ToDoModel> todoList;
    private MainActivity activity;
    private DatabaseHandler db;
//constructor for adapter
    public ToDoAdapter(DatabaseHandler db, MainActivity activity){
        this.db = db;
        this.activity = activity;
    }
//view holder function
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout, parent, false);
        return new ViewHolder(itemView);
    }
// implementation of a recyclerViewAdapter
    public void onBindViewHolder(ViewHolder holder, int position){
        db.openDatabase();
//        get item and search for its position
        ToDoModel item = todoList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if checkbox checked then update the status to 1
                if(isChecked){
                    db.updateStatus(item.getId(), 1);
                }
                else{
                    db.updateStatus(item.getId(), 0);
                }
            }
        });
    }
    // to convert int to bool as SQLite doesn't take bool vals
    private boolean toBoolean(int n) {
        return n != 0;
    }
    // recyclerview knows how many tasks
    public int getItemCount() {
        return todoList.size();
    }

    public Context getContext() {
        return activity;
    }

//    to set recyclerview to all the tasks
    public void setTasks(List<ToDoModel> todoList) {
//        passing in the list to the adapter
        this.todoList = todoList;
        notifyDataSetChanged();
    }
// to delete task
    public void deleteItem(int position) {
        ToDoModel item = todoList.get(position);
//        id passed to database and the database handler will delete the task
        db.deleteTask(item.getId());
//        remove from todolistarray
        todoList.remove(position);
        notifyItemRemoved(position);
    }
    // to edit task
    public void editItem(int position) {
        ToDoModel item = todoList.get(position);
//        passing to bottom sheet fragment and passing id and task
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;
        ViewHolder(View view){
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
        }
    }
}
