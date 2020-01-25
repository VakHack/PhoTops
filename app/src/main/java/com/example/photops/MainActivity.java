package com.example.photops;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.photops.Presenters.FragmentSwapper;
import com.example.photops.Views.MultiPhotoFragment;
import com.example.photops.Views.SinglePhotoFragment;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new MultiPhotoFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        Fragment singlePhotoFragment = new MultiPhotoFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentSwapper fragmentSwapper = new FragmentSwapper(singlePhotoFragment, fragmentManager, R.id.container);
        fragmentSwapper.swap();
    }
}
