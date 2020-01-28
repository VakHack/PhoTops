package com.example.photops;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.photops.Presenters.MultiPhoto.MultiPhotoFragment;
import com.example.photops.Presenters.MultiPhoto.MultiPhotoPresenter;
import com.example.photops.Presenters.PresenterInitializer;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting the default view to be multi-photo
        runMultiPhotoFragment();
    }

    @Override
    public void onBackPressed() {
        //setting the first view to be multi-photo
        runMultiPhotoFragment();
    }

    private void runMultiPhotoFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .add(R.id.container, new MultiPhotoFragment())
                .commit();
    }
}
