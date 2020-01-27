package com.example.photops.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.photops.Data.Storage.SharedPrefsStorage;
import com.example.photops.Data.Storage.Storage;
import com.example.photops.Network.APIParams;
import com.example.photops.Network.Networker;
import com.example.photops.Presenters.MultiPhoto.MultiPhotoFragment;
import com.example.photops.Presenters.MultiPhoto.MultiPhotoPresenter;
import com.example.photops.Presenters.PhotoPresenter;
import com.example.photops.Presenters.SinglePhoto.SinglePhotoFragment;
import com.example.photops.Presenters.SinglePhoto.SinglePhotoPresenter;
import com.example.photops.R;

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

    private FragmentSwapper multiFragmentSwapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init network handler
        Networker networker =
                new Networker(new APIParams(
                        REQUEST_METHOD, BASE_URL, SPECIFIC_PATH,
                        API_VERSION, ENV, MEMBER_ID, GET_LIKES, LIMIT, START));

        //init storage
        Storage storage = new SharedPrefsStorage(this);

        Bundle bundle = new Bundle();
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment multiPhotoFragment = new MultiPhotoFragment();
        Fragment singlePhotoFragment = new SinglePhotoFragment();

        multiFragmentSwapper = new FragmentSwapper(multiPhotoFragment, fragmentManager, R.id.container);
        FragmentSwapper singleFragmentSwapper = new FragmentSwapper(singlePhotoFragment, fragmentManager, R.id.container);

        PhotoPresenter multiPhotoPresenter = new MultiPhotoPresenter(this, singleFragmentSwapper, networker, storage);
        PhotoPresenter singlePhotoPresenter = new SinglePhotoPresenter(this, multiFragmentSwapper, networker, storage);

        bundle.putSerializable("SinglePresenter", singlePhotoPresenter);
        singlePhotoFragment.setArguments(bundle);

        bundle.putSerializable("MultiPresenter", multiPhotoPresenter);
        multiPhotoFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, multiPhotoFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        //back press should always send you back to the scrolling screen
        multiFragmentSwapper.swap();
    }
}
