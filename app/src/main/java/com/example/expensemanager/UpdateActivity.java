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

import java.util.Calendar;

import static java.lang.Integer.parseInt;
public class UpdateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView mTextView;
    private String mdate;
    private String itemName;
    private Button updateBtn;
    private EditText idText;
    private EditText amountText;
    private int number;
    MyDbHandler db = new MyDbHandler(UpdateActivity.this);
    Item item=new Item();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        mTextView=(TextView) findViewById(R.id.dateTextView1);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(
                        UpdateActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,                mDateSetListener,year,month,day);
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
        spinner=(Spinner) findViewById(R.id.spinner);
        updateBtn=(Button) findViewById(R.id.button);
        idText=(EditText) findViewById(R.id.idText);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UpdateActivity.this, "Update Activity", Toast.LENGTH_SHORT).show();
                number=Integer.parseInt(idText.getText().toString());
        item.setId(number);
        item.setName(itemName.toString());
        item.setItemPrice(amountText.getText().toString());
        item.setItemDate(mTextView.getText().toString());
        int affectedRows = db.updateContact(item);
        Log.d("dbUpdate", "No of affected rows are: " + affectedRows );
            }
        });
        amountText=(EditText) findViewById(R.id.amountText);
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

