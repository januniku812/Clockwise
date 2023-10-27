package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TodoItemAdapter extends ArrayAdapter<TodoItem> {
    ArrayList<TodoItem> todoItems;
    public TodoItemAdapter(@NonNull Context context, ArrayList<TodoItem> todoItems){
        super(context, 0, todoItems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View newItemView = convertView;

        if(newItemView != null){
            newItemView = LayoutInflater.from(getContext()).inflate(R.layout.todo_item_layout, parent, false);
        }

        TodoItem todoItem = getItem(position);

        TextView nameTextView = (TextView) newItemView.findViewById(R.id.todo_item_name);
        nameTextView.setText(todoItem.getName());

        Button doneButton = (Button) newItemView.findViewById(R.id.done_button);

        if(todoItem.isCompleted()){
            doneButton.setText("Completed");
        } else{
            doneButton.setText("Done");
        }


        return super.getView(position, convertView, parent);
    }
}
