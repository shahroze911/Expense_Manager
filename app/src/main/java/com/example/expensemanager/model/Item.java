package com.example.expensemanager.model;
public class Item {
    private int id;
    private String itemName;
    private String itemPrice;
   private String itemDate;
    public Item(String name, String price,String date) {
        this.itemName = name;
        this.itemPrice = price;
        this.itemDate=date;
    }
    public Item(int id, String name, String phoneNumber,String date) {
        this.id = id;
        this.itemName = name;
        this.itemPrice = phoneNumber;
        this.itemDate=date;
    }
    public Item() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return itemName;
    }
    public void setName(String name) {
        this.itemName = name;
    }
    public String getItemPrice() {
        return itemPrice;
    }
    public void setItemPrice(String price) {
        this.itemPrice = price;
    }
    public void setItemDate(String itemDate) {
        this.itemDate = itemDate;
    }
    public String getItemDate() {
        return itemDate;
    }
}