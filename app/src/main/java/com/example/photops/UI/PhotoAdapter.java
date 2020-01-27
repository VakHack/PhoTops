package com.example.photops.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.photops.Data.Photo.Item;
import com.example.photops.Data.Photo.PhotoUrlBuilder;
import com.example.photops.Data.Storage.Storage;
import com.example.photops.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private final List<Item> photos;
    private Context context;
    private OnPhotoClickedListener listener;
    private Storage storage;

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView gridImage;
        final ImageView likeIndicator;
        final TextView numOfLikes;
        final TextView photoBy;
        final ProgressBar progressBar;

        public ViewHolder(View view) {
            super(view);
            gridImage = view.findViewById(R.id.gridImage);
            likeIndicator =  view.findViewById(R.id.likeIndicator);
            numOfLikes =  view.findViewById(R.id.numOfLikes);
            photoBy =  view.findViewById(R.id.photoBy);
            progressBar = view.findViewById(R.id.gridProgressBar);
        }
    }

    public PhotoAdapter(List<Item> photos, Context context,
                        OnPhotoClickedListener listener, Storage storage) {
        this.photos = photos;
        this.context = context;
        this.listener = listener;
        this.storage = storage;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final int HOLDER_WIDTH = 300;
        final int HOLDER_HEIGHT = 400;

        //positioning the bounded photo
        Item photo = photos.get(position);
        holder.gridImage.setOnClickListener(v ->
                listener.photoClicked(photos.get(holder.getAdapterPosition()),(ImageView)v));

        //calling the relevant photo into the view with picasso
        Picasso.with(context)
                .load(PhotoUrlBuilder.build(photo))
                .resize(HOLDER_WIDTH, HOLDER_HEIGHT)
                .centerCrop()
                .into(holder.gridImage, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.GONE);
                        loadPhotoDetails(holder, photo);
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(context, "Failed to fetch photo",
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.grid_item, parent, false);

        return new ViewHolder(view);
    }

    public void addPhotos(List<Item> photos){
        int lastCount = getItemCount();
        this.photos.addAll(photos);
        notifyItemRangeInserted(lastCount, photos.size());
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public interface OnPhotoClickedListener {
        void photoClicked(Item photo, ImageView imageView);
    }

    private void loadPhotoDetails(ViewHolder holder, Item photo){
        //setting the descriptive text relevant to the photo
        String likeNumText = "Likes: " + photo.getLikes();
        holder.numOfLikes.setVisibility(View.VISIBLE);
        holder.numOfLikes.setText(likeNumText);

        String byText = "Views: " + photo.getViews();
        holder.photoBy.setVisibility(View.VISIBLE);
        holder.photoBy.setText(byText);

        //adding color to the "like" indicator, in case it was liked by the user
        holder.likeIndicator.setVisibility(View.VISIBLE);
        if(storage.isPhotoLiked(photo))
            holder.likeIndicator.setImageResource(R.drawable.like_full);
    }
}
