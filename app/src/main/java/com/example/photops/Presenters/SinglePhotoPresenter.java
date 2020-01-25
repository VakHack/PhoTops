package com.example.photops.Presenters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.photops.Models.AppDB;
import com.example.photops.Models.SharedPrefsAppDB;
import com.example.photops.Models.StateChangeActionHandler;
import com.example.photops.R;
import com.squareup.picasso.Picasso;

public class SinglePhotoPresenter implements StateChangeActionHandler{
    private ImageView imageView;
    private Context context;
    private AppDB appDB;

    public SinglePhotoPresenter(Context context) {
        this.context = context;
        appDB = new SharedPrefsAppDB(context);
    }

    public void setView(View root){
        imageView = root.findViewById(R.id.singlePhoto);
    }

    public void present(){
        appDB.waitOnAppState(this);
    }

    @Override
    public void performOnStateChange() {
        Log.e("test", appDB.getActivePhoto()+"");

        Picasso.with(context)
                .load(appDB.getActivePhoto().getUrls().getFull())
                .into(imageView);
    }
}
