package com.example.expensemanager.data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.expensemanager.model.Item;
import com.example.expensemanager.param.Params;
import java.util.ArrayList;
import java.util.List;

import static com.example.expensemanager.param.Params.KEY_NAME;

public class MyDbHandler extends SQLiteOpenHelper {
    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = " CREATE TABLE " + Params.DB_TABLE + "("
                + Params.KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME
                + " TEXT,  " + Params.KEY_PRICE + " TEXT, "
                +Params.KEY_DATE + " TEXT "+ ")";
        Log.d("dbDemo1", "Query being run is : "+ create);
        db.execSQL(create);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void addItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.getName());
        values.put(Params.KEY_PRICE, item.getItemPrice());
        values.put(Params.KEY_DATE, item.getItemDate());
       db.insert(Params.DB_TABLE, null, values);
        Log.d("dbDemo2", "Successfully inserted"+item.getItemDate());
        db.close();
    }
    public Cursor viewData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Params.DB_NAME;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
    public List<Item> getAllItems(){
        List<Item> itemList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        // Generate the query to read from the database
        String select = "SELECT * FROM " + Params.DB_TABLE;
        Cursor cursor = db.rawQuery(select, null);
        //Loop through now
        if(cursor.moveToFirst()){
            do{
                Item item = new Item();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setName(cursor.getString(1));
                item.setItemPrice(cursor.getString(2));
                item.setItemDate(cursor.getString(3));
                itemList.add(item);
            }while(cursor.moveToNext());
        }
        return itemList;
    }

    public int updateContact(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.getName());
        values.put(Params.KEY_PRICE, item.getItemPrice());
        values.put(Params.KEY_DATE,item.getItemDate());
        //Lets update now
        return db.update(Params.DB_TABLE, values, Params.KEY_ID + "=?",
                new String[]{String.valueOf(item.getId())});
    }
    public void deleteItemById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.DB_TABLE, Params.KEY_ID +"=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public int getCount(String name){

        String query = "SELECT  item_price FROM  " + Params.DB_TABLE +" where item_name =?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{name});
        return cursor.getCount();

    }
    public int getTotalCount(){
        String query = "SELECT  item_price FROM  " + Params.DB_TABLE ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();

    }
}