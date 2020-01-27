package com.example.photops;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.photops.Models.Storage.SharedPrefsStorage;
import com.example.photops.Models.Storage.Storage;
import com.example.photops.Models.Network.APIParams;
import com.example.photops.Models.Network.NetworkCoordinator;
import com.example.photops.Presenters.MultiPhoto.MultiPhotoFragment;
import com.example.photops.Presenters.MultiPhoto.MultiPhotoPresenter;
import com.example.photops.Presenters.PhotoPresenter;
import com.example.photops.Presenters.SinglePhoto.SinglePhotoFragment;
import com.example.photops.Presenters.SinglePhoto.SinglePhotoPresenter;
import com.example.photops.UI.FragmentSwapper;

public class AppInitializer {
    private Context context;
    private FragmentManager fragmentManager;

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

    public AppInitializer(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    public void init(){
        //init network handler
        APIParams apiParams = new APIParams(REQUEST_METHOD, BASE_URL, SPECIFIC_PATH,
                API_VERSION, ENV, MEMBER_ID, GET_LIKES, LIMIT, START);
        NetworkCoordinator networkCoordinator = new NetworkCoordinator(apiParams);

        //init storage
        Storage storage = new SharedPrefsStorage(context);

        //init fragments
        Fragment multiPhotoFragment = new MultiPhotoFragment();
        Fragment singlePhotoFragment = new SinglePhotoFragment();

        //init swappers (swaps fragments on their activity)
        FragmentSwapper multiFragmentSwapper = new FragmentSwapper(multiPhotoFragment, fragmentManager, R.id.container);
        FragmentSwapper singleFragmentSwapper = new FragmentSwapper(singlePhotoFragment, fragmentManager, R.id.container);

        //init presenters
        PhotoPresenter multiPhotoPresenter = new MultiPhotoPresenter(context, singleFragmentSwapper, networkCoordinator, storage);
        PhotoPresenter singlePhotoPresenter = new SinglePhotoPresenter(context, multiFragmentSwapper, networkCoordinator, storage);

        //assigning presenters via bundle to their fragments
        Bundle bundle = new Bundle();

        bundle.putSerializable("SinglePresenter", singlePhotoPresenter);
        singlePhotoFragment.setArguments(bundle);
        bundle.putSerializable("MultiPresenter", multiPhotoPresenter);
        multiPhotoFragment.setArguments(bundle);

        //setting the first view to be multi-photo
        fragmentManager.beginTransaction()
        .add(R.id.container, multiPhotoFragment)
        .commit();
    }
}
