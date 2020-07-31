package com.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import com.example.expensemanager.data.MyDbHandler;
import com.example.expensemanager.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ListView listView;
        listView = findViewById(R.id.listView);
        Toast.makeText(this, "View Data", Toast.LENGTH_SHORT).show();
        MyDbHandler db = new MyDbHandler(ViewActivity.this);
        ArrayList<String> itemList = new ArrayList<>();


        List<Item> allItems = db.getAllItems();
        for(Item item : allItems){
            Log.d("dbExpense", "\nId: " + item.getId() + "\n" +
                    "Name: " + item.getName() + "\n"+
                    "Item Price: " + item.getItemPrice() + "\n"+
                    "Date:  " +item.getItemDate()+"\n" );
            itemList.add(item.getId()+"     "+ item.getItemPrice() + "               " + item.getName() + "               "+item.getItemDate());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(arrayAdapter);

    }
}
