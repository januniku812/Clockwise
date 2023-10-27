package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.*;

import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONException;
import org.json.simple.parser.ParseException;


public class MainActivity extends AppCompatActivity {
    ArrayList<TodoItem> todoItems;
    ListView todoListView;
    TodoItemAdapter todoItemAdapter;

    public void addItemDialog(){
        AlertDialog alertDialog = new
    }

    public void update(){
        try {
            todoItems = QueryUtils.loadTodoItems(getApplicationContext());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        todoItemAdapter = new TodoItemAdapter(getApplicationContext(), todoItems);
        todoListView.setAdapter(todoItemAdapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todoListView = findViewById(R.id.todo_list_view);
        try {
            todoItems = QueryUtils.loadTodoItems(getApplicationContext());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        todoItemAdapter = new TodoItemAdapter(getApplicationContext(), todoItems);
        Constants.jsonStr = PreferenceManager.getDefaultSharedPreferences(this).getString("jsonStr", "{\n" +
                "\"todo_items\":[]\n" +
                "}");
        todoListView.setAdapter(todoItemAdapter);
        todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Button doneButton = view.findViewById(R.id.done_button);
                TodoItem todoItem = todoItemAdapter.getItem(position);
                doneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            QueryUtils.setTodoItemAsComplete(todoItem.getName(), getApplicationContext());
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        update();

                    }
                });
            }
        });


    }
}