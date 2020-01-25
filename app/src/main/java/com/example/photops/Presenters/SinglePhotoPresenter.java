package com.example.photops.Presenters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.photops.Models.AppDB;
import com.example.photops.Models.SharedPrefsAppDB;
import com.example.photops.R;
import com.squareup.picasso.Picasso;

public class SinglePhotoPresenter {
    private ImageView imageView;
    private Context context;
    private AppDB appDB;

    public SinglePhotoPresenter(Context context) {
        this.context = context;
        appDB = new SharedPrefsAppDB(context);
    }

    public void setView(View root){
        imageView = root.findViewById(R.id.photoView);
    }

    public void present(){
        Log.e("test", "here");

        Picasso.with(context)
                .load(appDB.getActivePhoto().getUrls().getFull())
                .into(imageView);
    }
}
