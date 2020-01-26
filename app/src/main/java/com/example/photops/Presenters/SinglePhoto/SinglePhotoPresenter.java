package com.example.photops.Presenters.SinglePhoto;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.photops.Data.Storage;
import com.example.photops.Data.SharedPrefsStorage;
import com.example.photops.R;
import com.squareup.picasso.Picasso;

public class SinglePhotoPresenter {
    private ImageView imageView;
    private Context context;
    private Storage storage;
    ImageButton likeButton;


    public SinglePhotoPresenter(Context context) {
        this.context = context;
        storage = new SharedPrefsStorage(context);
    }

    public void setView(View root){
        imageView = root.findViewById(R.id.photoView);
        likeButton = root.findViewById(R.id.likeButton);

        if(storage.isPhotoLiked(storage.getActivePhoto())){
            likeButton.setImageResource(R.drawable.large_like_full);
        }

        likeButton.setOnClickListener(v -> toggleLike());
    }

    public void present(){
        Picasso.with(context)
                .load(storage.getActivePhoto().getUrls().getFull())
                .into(imageView);
    }

    public void toggleLike(){
        storage.toggleLikePhoto(storage.getActivePhoto());

        boolean isActivePhotoLike = storage.isPhotoLiked(storage.getActivePhoto());
        if(isActivePhotoLike){
            likeButton.setImageResource(R.drawable.large_like_full);
        } else {
            likeButton.setImageResource(R.drawable.large_like_empty);
        }
    }
}
