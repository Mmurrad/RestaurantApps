package com.example.murad.restaurantapps.Model;

public class RestaurantItem {
    String item,price,active;

    public RestaurantItem(String item, String price, String active) {
        this.item = item;
        this.price = price;
        this.active = active;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
