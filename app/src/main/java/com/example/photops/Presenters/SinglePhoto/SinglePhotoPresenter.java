package com.example.photops.Presenters.SinglePhoto;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.photops.Data.PhotoUrlBuilder;
import com.example.photops.Data.SharedPrefsStorage;
import com.example.photops.Data.Storage;
import com.example.photops.Presenters.PhotoPresenter;
import com.example.photops.R;
import com.squareup.picasso.Picasso;

public class SinglePhotoPresenter implements PhotoPresenter {
    private ImageView imageView;
    private Context context;
    private Storage storage;
    private ImageButton likeButton;
    private double REQUIRED_IMG_HEIGHT = 0.5;


    public SinglePhotoPresenter(Context context) {
        this.context = context;
        storage = new SharedPrefsStorage(context);
    }

    @Override
    public void setView(View root){
        imageView = root.findViewById(R.id.photoView);
        likeButton = root.findViewById(R.id.likeButton);

        if(storage.isPhotoLiked(storage.getActivePhoto())){
            likeButton.setImageResource(R.drawable.large_like_full);
        }

        likeButton.setOnClickListener(v -> toggleLike());
    }

    @Override
    public void present(){
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

        //set the picture to be on N * screen height
        Picasso.with(context)
                .load(PhotoUrlBuilder.build(storage.getActivePhoto()))
                .resize(screenWidth, (int)(screenHeight * REQUIRED_IMG_HEIGHT))
                .centerCrop()
                .into(imageView);
    }

    public void toggleLike(){
        storage.toggleLikePhoto(storage.getActivePhoto());

        boolean isActivePhotoLiked = storage.isPhotoLiked(storage.getActivePhoto());
        if(isActivePhotoLiked){
            likeButton.setImageResource(R.drawable.large_like_full);
        } else {
            likeButton.setImageResource(R.drawable.large_like_empty);
        }
    }
}
