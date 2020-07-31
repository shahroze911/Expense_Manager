package com.example.expensemanager;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expensemanager.data.MyDbHandler;
import com.example.expensemanager.model.Item;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView mTextView;
    private Button addBtn;
    private String mdate;
    private EditText pPrice;
    private String itemName;
    MyDbHandler db = new MyDbHandler(AddActivity.this);
    Item data = new Item();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Spinner spinner = (Spinner) findViewById(R.id.categorySpinner);
        addBtn=(Button) findViewById(R.id.btnAdd);
        pPrice=(EditText) findViewById(R.id.amount);
        mTextView=(TextView) findViewById(R.id.dateTextView);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(
                        AddActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,                mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mdate=month+"/"+dayOfMonth+"/"+year;
                mTextView.setText(mdate);
            }
        };

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setItemPrice(pPrice.getText().toString());
                data.setName(itemName.toString());
                data.setItemDate(mTextView.getText().toString());
                db.addItem(data);
                Toast.makeText(AddActivity.this, "Data Successfully Added in Database", Toast.LENGTH_SHORT).show();
                Log.d("insertDemo123", "New Data is being Added"+mTextView.getText().toString());
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String category=parent.getItemAtPosition(position).toString();
        itemName=category;
        Toast.makeText(this, category, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
