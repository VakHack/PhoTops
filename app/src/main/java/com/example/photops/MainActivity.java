package com.example.photops;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.photops.Views.MultiPhotoFragment;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new MultiPhotoFragment())
                .commit();
    }
}
