package com.example.www.mughalai_kitchan_app;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Welcome on 14/3/2018.
 */

public class cartlistholder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView txtname,txttotal;
    ImageButton btnremove;
    CardView cartrm;
        public cartlistholder(View itemView) {
        super(itemView);
        txtname=(TextView)itemView.findViewById(R.id.cartfood);
        txttotal=(TextView)itemView.findViewById(R.id.carttotal);

         cartrm=(CardView)itemView.findViewById(R.id.cartremove);
           cartrm.setOnClickListener(this);

                      }

   private ItemonClickListener itemonClickListener;

    public void setItemonClickListener(ItemonClickListener itemonClickListener) {
        this.itemonClickListener = itemonClickListener;
   }


    @Override
    public void onClick(View v) {
       itemonClickListener.onClick(v,getAdapterPosition(),false);
    }
}
