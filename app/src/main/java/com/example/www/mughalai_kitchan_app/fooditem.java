package com.example.www.mughalai_kitchan_app;

/**
 * Created by Welcome on 15/3/2018.
 */

public class fooditem {
   private String name,description,image;
   private int price;

    public fooditem() {
    }

    public fooditem(String name, String description, String image, int price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
