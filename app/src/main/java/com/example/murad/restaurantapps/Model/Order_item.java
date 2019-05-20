package com.example.murad.restaurantapps.Model;

import com.example.murad.restaurantapps.Activity.Service_Activity;

import java.util.ArrayList;

public class Order_item {
    String item_name,qty,prize;

    public Order_item(String item_name, String qty, String prize) {
        this.item_name = item_name;
        this.qty = qty;
        this.prize = prize;
    }


    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }
}
