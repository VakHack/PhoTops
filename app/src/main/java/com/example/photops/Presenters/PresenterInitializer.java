package com.example.photops.Presenters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.photops.Models.Network.APIParams;
import com.example.photops.Models.Network.NetworkCoordinator;
import com.example.photops.Models.Storage.SharedPrefsStorage;
import com.example.photops.Models.Storage.Storage;
import com.example.photops.Presenters.MultiPhoto.MultiPhotoFragment;
import com.example.photops.Presenters.MultiPhoto.MultiPhotoPresenter;
import com.example.photops.Presenters.SinglePhoto.SinglePhotoFragment;
import com.example.photops.Presenters.SinglePhoto.SinglePhotoPresenter;
import com.example.photops.R;
import com.example.photops.UI.FragmentSwapper;

public class PresenterInitializer {
    private Context context;
    private FragmentManager fragmentManager;
    private boolean isMulti;

    //hard-coding the given api headers and params
    private final String MEMBER_ID = "2a49ab04b1534574e578a08b8f9d7441";
    private final String GET_LIKES = "true";
    private final String LIMIT = "50";
    private final String START = "0";
    private final String REQUEST_METHOD = "POST";
    private final String API_VERSION = "20";
    private final String ENV = "ANDROID";
    private final String BASE_URL = "https://api.gurushots.com/";
    private final String SPECIFIC_PATH = "rest_mobile/get_photos_public/";

    public PresenterInitializer(Context context, FragmentManager fragmentManager,
                                boolean isMultiPresenter) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.isMulti = isMultiPresenter;
    }

    public PhotoPresenter init(){
        //init network handler
        APIParams apiParams = new APIParams(REQUEST_METHOD, BASE_URL, SPECIFIC_PATH,
                API_VERSION, ENV, MEMBER_ID, GET_LIKES, LIMIT, START);
        NetworkCoordinator networkCoordinator = new NetworkCoordinator(apiParams);

        //init storage
        Storage storage = new SharedPrefsStorage(context);

        //testing if the requested fragment is multi or single, and creating accordingly
        Fragment fragment = isMulti ? new SinglePhotoFragment() : new MultiPhotoFragment();

        //init swappers (swaps fragments on their activity)
        FragmentSwapper multiFragmentSwapper = new FragmentSwapper(fragment, fragmentManager, R.id.container);
        FragmentSwapper singleFragmentSwapper = new FragmentSwapper(fragment, fragmentManager, R.id.container);

        //testing if the requested presenter is multi or single, and initializing it accordingly
        return isMulti ? new MultiPhotoPresenter(context, singleFragmentSwapper, networkCoordinator, storage)
                        : new SinglePhotoPresenter(context, multiFragmentSwapper, networkCoordinator, storage);
    }
}
