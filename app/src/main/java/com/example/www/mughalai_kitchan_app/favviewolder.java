package com.example.www.mughalai_kitchan_app;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Welcome on 16/3/2018.
 */

public class favviewolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       private ItemonClickListener itemonClickListener;
       TextView txtname,txtprice;
    public favviewolder(View itemView) {
        super(itemView);
        txtname=(TextView)itemView.findViewById(R.id.favname);

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
