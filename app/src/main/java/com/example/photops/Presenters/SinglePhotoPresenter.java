package com.example.photops.Presenters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.photops.Models.ActivePhotoHandler;
import com.example.photops.Models.SharedPrefsActivePhotoHandler;
import com.example.photops.R;
import com.squareup.picasso.Picasso;

public class SinglePhotoPresenter {
    private ImageView imageView;
    private Context context;
    private ActivePhotoHandler activePhotoHandler;

    public SinglePhotoPresenter(Context context) {
        this.context = context;
        activePhotoHandler = new SharedPrefsActivePhotoHandler(context);
    }

    public void setView(View root){
        imageView = root.findViewById(R.id.photoView);
    }

    public void present(){
        Picasso.with(context)
                .load(activePhotoHandler.getActivePhoto().getUrls().getFull())
                .into(imageView);
    }
}
