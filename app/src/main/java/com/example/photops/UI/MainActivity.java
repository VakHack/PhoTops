package com.example.photops.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.photops.Presenters.FragmentSwapper;
import com.example.photops.Presenters.MultiPhoto.MultiPhotoFragment;
import com.example.photops.R;

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
        //back press should always send you back to the scrolling screen
        Fragment singlePhotoFragment = new MultiPhotoFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentSwapper fragmentSwapper = new FragmentSwapper(singlePhotoFragment, fragmentManager, R.id.container);
        fragmentSwapper.swap();
    }
}
