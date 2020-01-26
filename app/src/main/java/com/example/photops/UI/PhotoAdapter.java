package com.example.photops.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.photops.Data.Photo;
import com.example.photops.Data.Storage;
import com.example.photops.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private final List<Photo> photos;
    private Context context;
    private OnPhotoClickedListener listener;
    private Storage storage;

    public PhotoAdapter(List<Photo> photos, Context context,
                        OnPhotoClickedListener listener, Storage storage) {
        this.photos = photos;
        this.context = context;
        this.listener = listener;
        this.storage = storage;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //positioning the bounded photo
        Photo photo = photos.get(position);
        holder.gridPhoto.setOnClickListener(v -> listener.photoClicked(photos.get(holder.getAdapterPosition()),(ImageView)v));

        //adding color to the "like" indicator, in case it was liked by the user
        if(storage.isPhotoLiked(photos.get(holder.getAdapterPosition())))
            holder.likeIndicator.setImageResource(R.drawable.like_full);

        //calling the relevant view with picasso
        Picasso.with(context)
                .load(photo.getUrls().getRegular())
                .resize(300, 300)
                .centerCrop()
                .into(holder.gridPhoto);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    public void addPhotos(List<Photo> photos){
        int lastCount = getItemCount();
        this.photos.addAll(photos);
        notifyItemRangeInserted(lastCount, photos.size());
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView gridPhoto;
        public final ImageView likeIndicator;

        public ViewHolder(View view) {
            super(view);
            gridPhoto = view.findViewById(R.id.gridImage);
            likeIndicator =  view.findViewById(R.id.likeIndicator);
        }
    }

    public interface OnPhotoClickedListener {
        void photoClicked(Photo photo, ImageView imageView);
    }
}
