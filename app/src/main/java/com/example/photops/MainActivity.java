package com.example.photops;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.photops.Data.Storage.SharedPrefsStorage;
import com.example.photops.Data.Storage.Storage;
import com.example.photops.Network.APIParams;
import com.example.photops.Network.NetworkCoordinator;
import com.example.photops.Presenters.MultiPhoto.MultiPhotoFragment;
import com.example.photops.Presenters.MultiPhoto.MultiPhotoPresenter;
import com.example.photops.Presenters.PhotoPresenter;
import com.example.photops.Presenters.SinglePhoto.SinglePhotoFragment;
import com.example.photops.Presenters.SinglePhoto.SinglePhotoPresenter;
import com.example.photops.UI.FragmentSwapper;

public class MainActivity extends FragmentActivity {
    //hard-coding the given api headers and params for later use
    private final String MEMBER_ID = "2a49ab04b1534574e578a08b8f9d7441";
    private final String GET_LIKES = "true";
    private final String LIMIT = "50";
    private final String START = "0";
    private final String REQUEST_METHOD = "POST";
    private final String API_VERSION = "20";
    private final String ENV = "ANDROID";
    private final String BASE_URL = "https://api.gurushots.com/";
    private final String SPECIFIC_PATH = "rest_mobile/get_photos_public/";
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
