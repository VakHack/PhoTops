package com.example.photops.Presenters.SinglePhoto;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.photops.Data.Photo.PhotoUrlBuilder;
import com.example.photops.Data.Storage.SharedPrefsStorage;
import com.example.photops.Data.Storage.Storage;
import com.example.photops.Presenters.PhotoPresenter;
import com.example.photops.R;
import com.squareup.picasso.Picasso;

public class SinglePhotoPresenter implements PhotoPresenter {
    private ImageView image;
    private Context context;
    private Storage storage;
    private ImageButton likeButton;
    private ProgressBar progressBar;


    public SinglePhotoPresenter(Context context) {
        this.context = context;
        storage = new SharedPrefsStorage(context);
    }

    @Override
    public void setView(View root){
        image = root.findViewById(R.id.photoView);
        likeButton = root.findViewById(R.id.likeButton);
        progressBar = root.findViewById(R.id.progressBar);

        if(storage.isPhotoLiked(storage.getActivePhoto())){
            likeButton.setImageResource(R.drawable.large_like_full);
        }

        likeButton.setOnClickListener(v -> toggleLike());
    }

    @Override
    public void present(){
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        double REQUIRED_IMG_HEIGHT = 0.5;

        //set the picture to be on N * screen height
        Picasso.with(context)
                .load(PhotoUrlBuilder.build(storage.getActivePhoto()))
                .resize(screenWidth, (int)(screenHeight * REQUIRED_IMG_HEIGHT))
                .centerCrop()
                .into(image, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(context, "Failed to fetch photo",
                                Toast.LENGTH_LONG).show();
                    }
                });
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
