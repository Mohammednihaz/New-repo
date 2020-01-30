package com.example.www.mughalai_kitchan_app;

/**
 * Created by Welcome on 14/2/2018.
 */
public class food {
    private String Name;
    private String Image;
    private String menuId;
    private String description;
    private String availability;
    private String price;


    public food() {
    }



    public food(String name, String image, String menuId, String description, String availability) {
        Name = name;
        Image = image;
        this.menuId = menuId;
        this.description = description;
        this.availability = availability;
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

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}