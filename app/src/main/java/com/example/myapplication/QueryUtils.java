package com.example.myapplication;
import android.content.Context;
import android.preference.PreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class QueryUtils {
    public static String removeAllWhiteSpaces(String str){
        return str.replace(" ", "").trim();
    }

    public static void setTodoItemAsComplete(String todoItemName, Context context) throws JSONException, ParseException{
        String jsonData = Constants.jsonStr;
        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(jsonData);

        ArrayList<TodoItem> todoItemsToReturn = new ArrayList<>();

        JSONObject jsonObject = (JSONObject) object;
        JSONArray todoItems = null;
        JSONArray todoItemsArrayList = (JSONArray) jsonObject.getJSONArray("todo_items");

        for(int i = 0; i < todoItemsArrayList.length(); i++){
            JSONObject todoItem = (JSONObject) todoItemsArrayList.get(i);
            String name = todoItem.getString("todo_item_name");
            if(removeAllWhiteSpaces(name).equalsIgnoreCase(todoItemName)){
                todoItem.put("if_completed", true);
            }
        }
        Constants.jsonStr = jsonObject.toString();
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("jsonData", Constants.jsonStr.toString()).apply();


    }
    public static ArrayList<TodoItem> loadTodoItems(Context context) throws JSONException, ParseException {
        String jsonData = Constants.jsonStr;
        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(jsonData);

        ArrayList<TodoItem> todoItemsToReturn = new ArrayList<>();

        JSONObject jsonObject = (JSONObject) object;
        JSONArray todoItems = null;
        JSONArray todoItemsArrayList = (JSONArray) jsonObject.getJSONArray("todo_items");

        for(int i = 0; i < todoItemsArrayList.length(); i++){
            JSONObject todoItem = (JSONObject) todoItemsArrayList.get(i);
            TodoItem todoItemToAdd = new TodoItem(todoItem.getString("todo_item_name"), todoItem.getBoolean("if_completed"));
            todoItemsToReturn.add(todoItemToAdd);
        }
        Constants.jsonStr = jsonObject.toString();
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("jsonData", Constants.jsonStr.toString()).apply();
        return todoItemsToReturn;
    }
    public static void addTodoItem(String todoItemName, Context context) throws JSONException, ParseException {
        String jsonData = Constants.jsonStr;
        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(jsonData);

        ArrayList<TodoItem> todoItemsToReturn = new ArrayList<>();

        JSONObject jsonObject = (JSONObject) object;
        JSONArray todoItemsArrayList = (JSONArray) jsonObject.getJSONArray("todo_items");
        JSONObject todoItemToAdd = new JSONObject();
        todoItemToAdd.put("todo_item_name", todoItemName);
        todoItemToAdd.put("if_completed", false);
        todoItemsArrayList.put(todoItemToAdd);
        Constants.jsonStr = jsonObject.toString();
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("jsonData", Constants.jsonStr.toString()).apply();
    }
    public static void deleteTodoItem(String todoItemName, Context context) throws JSONException, ParseException {
        String jsonData = Constants.jsonStr;
        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(jsonData);

        ArrayList<TodoItem> todoItemsToReturn = new ArrayList<>();

        JSONObject jsonObject = (JSONObject) object;
        JSONArray todoItemsArrayList = (JSONArray) jsonObject.getJSONArray("todo_items");

        for(int i = 0; i < todoItemsArrayList.length(); i++){
            JSONObject todoItem = (JSONObject) todoItemsArrayList.get(i);
            String name = todoItem.getString("todo_item_name");
            if(removeAllWhiteSpaces(name).equalsIgnoreCase(todoItemName)){
                todoItemsArrayList.remove(i);
            }
        }
        Constants.jsonStr = jsonObject.toString();
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("jsonData", Constants.jsonStr.toString()).apply();
    }
}
