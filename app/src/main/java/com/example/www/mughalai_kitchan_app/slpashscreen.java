package com.example.www.mughalai_kitchan_app;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

/**
 * Created by Welcome on 15/8/2018.
 */

public class slpashscreen extends AppCompatActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        EasySplashScreen config=new EasySplashScreen(this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(5000)
                .withBackgroundColor(5)

                .withHeaderText("welcome to Resturent")
                .withFooterText("Copyright 2017")
                .withAfterLogoText("mughalai_kitchan Restaurant");
        config.getHeaderTextView().setTextColor(1);
        config.getFooterTextView().setTextColor(1);
        config.getAfterLogoTextView().setTextColor(1);
        View view=config.create();
        setContentView(view);
    }
}
