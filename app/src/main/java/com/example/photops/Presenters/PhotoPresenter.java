package com.example.photops.Presenters;

import android.content.Context;
import android.view.View;

import com.example.photops.Data.Storage.Storage;
import com.example.photops.Network.Networker;
import com.example.photops.UI.FragmentSwapper;

import java.io.Serializable;

public abstract class PhotoPresenter implements Serializable {
    protected Context context;
    protected FragmentSwapper swapper;
    protected Networker networker;
    protected Storage storage;

    public PhotoPresenter(Context context, FragmentSwapper swapper,
                          Networker networker, Storage storage) {
        this.context = context;
        this.swapper = swapper;
        this.networker = networker;
        this.storage = storage;
    }

    public abstract void setView(View view);
    public abstract void present();
}
