package com.example.www.mughalai_kitchan_app;

/**
 * Created by Welcome on 8/2/2018.
 */

public class category {
    private String Name;
    private String Image;

    public category() {
    }

    public category(String name, String image) {
        Name = name;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
