package com.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.expensemanager.data.MyDbHandler;
import com.example.expensemanager.model.Item;

public class DeleteActivity extends AppCompatActivity {
    private Button delete;
    MyDbHandler db = new MyDbHandler(DeleteActivity.this);
    Item item=new Item();
    private EditText idText;
    private int number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        delete=(Button) findViewById(R.id.buttonDlt);
        idText=(EditText) findViewById(R.id.deleteText);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number=Integer.parseInt(idText.getText().toString());
                db.deleteItemById(number);
            }
        });

    }
}
