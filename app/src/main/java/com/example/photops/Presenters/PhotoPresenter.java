package com.example.photops.Presenters;

import android.content.Context;
import android.view.View;

import com.example.photops.Models.Network.NetworkCoordinator;
import com.example.photops.Models.Storage.Storage;
import com.example.photops.UI.FragmentSwapper;

public abstract class PhotoPresenter {
    protected Context context;
    protected FragmentSwapper swapper;
    protected NetworkCoordinator networkCoordinator;
    protected Storage storage;

    public PhotoPresenter(Context context, FragmentSwapper swapper,
                          NetworkCoordinator networkCoordinator, Storage storage) {
        this.context = context;
        this.swapper = swapper;
        this.networkCoordinator = networkCoordinator;
        this.storage = storage;
    }

    public abstract void setView(View view);
    public abstract void present();
}
