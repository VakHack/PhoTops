package com.example.photops;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppInitializer appInitializer = new AppInitializer(this, getSupportFragmentManager());
        appInitializer.init();
    }

    @Override
    public void onBackPressed() {
        //back press should always send you back to the scrolling screen
    }
}
