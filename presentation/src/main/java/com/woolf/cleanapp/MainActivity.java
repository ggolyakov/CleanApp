package com.woolf.cleanapp;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.woolf.cleanapp.ui.photos.PhotosFragment;

public class MainActivity extends MvpAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,new PhotosFragment()).commitAllowingStateLoss();
        }

    }
}
