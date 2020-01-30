package com.example.www.mughalai_kitchan_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Welcome on 9/2/2018.
 */

public class FoodViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView name;
    ImageView image;
    Context mcontext;

     private ItemonClickListener itemonClickListener;
    public FoodViewholder(View itemView) {
        super(itemView);

        image=(ImageView)itemView.findViewById(R.id.menuimage);
        name=(TextView)itemView.findViewById(R.id.menuname);

        itemView.setOnClickListener(this);

    }

    public void setItemonClickListener(ItemonClickListener itemonClickListener) {
        this.itemonClickListener = itemonClickListener;
    }





    @Override
    public void onClick(View view) {
       itemonClickListener.onClick(view,getAdapterPosition(),false);
    }
}
