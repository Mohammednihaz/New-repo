package com.example.www.mughalai_kitchan_app;

/**
 * Created by Welcome on 14/3/2018.
 */

public class cartelement {
   private String name;
   private int total;
   private String qty;

    public cartelement() {
    }

    public cartelement(String name, int total, String qty) {
        this.name = name;
        this.total = total;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
