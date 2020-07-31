package com.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.expensemanager.data.MyDbHandler;
import com.example.expensemanager.model.Item;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private Button addBtn;
    private Button viewBtn;
    private Button updateBtn;
    private Button deleteBtn;
    public TextView food;
    private TextView fuel;
    private TextView education;
    private TextView misc;
    MyDbHandler db = new MyDbHandler(MainActivity.this);
    Item data = new Item();
    public double count;
    public double totalCount;
    DecimalFormat df = new DecimalFormat("#.##");


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        food=(TextView) findViewById(R.id.foodText);
        fuel=(TextView) findViewById(R.id.fuelText);
        education=(TextView) findViewById(R.id.educationText);
        misc=(TextView) findViewById(R.id.miscText);
        addBtn=(Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        viewBtn=(Button) findViewById(R.id.viewBtn);
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                startActivity(intent);
            }
        });
        updateBtn=(Button) findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                startActivity(intent);
            }
        });
        deleteBtn=(Button)findViewById(R.id.deleteBtn00);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
                startActivity(intent);
            }
        });
        totalCount=db.getTotalCount();

        food.setText("");
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=db.getCount("Food");
                count=count/totalCount;
                count=count*100;
                Log.d("getCount", "New Data is being Added"+count);
                count = Double.valueOf(df.format(count));
                food.setText(Double.toString(count)+"%");
            }
        });

        fuel.setText("");
        fuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=db.getCount("Fuel");
                count=count/totalCount;
                count=count*100;
                Log.d("getCount", "New Data is being Added"+count);
                count = Double.valueOf(df.format(count));
                fuel.setText(Double.toString(count)+"%");
            }
        });

        education.setText("");
        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=db.getCount("Education");
                count=count/totalCount;
                count=count*100;
                Log.d("getCount", "New Data is being Added"+count);
                count = Double.valueOf(df.format(count));
                education.setText(Double.toString(count)+"%");
            }
        });

        misc.setText("");
        misc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=db.getCount("Misc");
                count=count/totalCount;
                count=count*100;
                Log.d("getCount", "New Data is being Added"+count);
                count = Double.valueOf(df.format(count));
                misc.setText(Double.toString(count)+"%");

            }
        });





    }
}
