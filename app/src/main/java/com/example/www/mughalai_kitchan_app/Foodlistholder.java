package com.example.www.mughalai_kitchan_app;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Welcome on 20/2/2018.
 */

public class  Foodlistholder extends RecyclerView.ViewHolder implements View.OnClickListener {
   private ItemonClickListener itemonClickListener;
   public TextView txtname;
public ImageView imagefood;

    public Foodlistholder(View itemView) {
        super(itemView);


         txtname=(TextView)itemView.findViewById(R.id.foodname);

        imagefood=(ImageView)itemView.findViewById(R.id.foodimage);

        itemView.setOnClickListener(this);
    }


    public void setItemonClickListener(ItemonClickListener itemonClickListener) {
        this.itemonClickListener = itemonClickListener;
    }

    @Override
    public void onClick(View v) {
        itemonClickListener.onClick(v,getAdapterPosition(),false);

    }
}
