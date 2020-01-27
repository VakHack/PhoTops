package com.example.photops.Presenters.MultiPhoto;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photops.Data.Photo.Item;
import com.example.photops.Data.Photo.ItemsList;
import com.example.photops.Data.Storage.Storage;
import com.example.photops.Network.PhotosGetter;
import com.example.photops.Network.RetrofitClientHandler;
import com.example.photops.Data.Storage.SharedPrefsStorage;
import com.example.photops.Presenters.FragmentSwapper;
import com.example.photops.Presenters.PhotoPresenter;
import com.example.photops.R;
import com.example.photops.UI.PhotoAdapter;
import com.example.photops.UI.RecyclerViewScroller;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MultiPhotoPresenter implements PhotoPresenter {
    private PhotoAdapter adapter;
    private PhotoAdapter.OnPhotoClickedListener photoClickListener;
    private RecyclerView recyclerView;
    private int page = 1;
    private FragmentSwapper swapper;
    private PhotosGetter photosGetter;
    private Context context;
    private Storage storage;


    public MultiPhotoPresenter(Context context, FragmentSwapper swapper) {
        this.context = context;
        this.swapper = swapper;
        storage = new SharedPrefsStorage(context);
    }

    @Override
    public void setView(View root){
        recyclerView = root.findViewById(R.id.recyclerView);
    }

    @Override
    public void present(){
        photosGetter = RetrofitClientHandler.getClient().create(PhotosGetter.class);

        photoClickListener = (photo, imageView) -> {
            storage.setActivePhoto(photo);
            swapper.swap();
        };

        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PhotoAdapter(new ArrayList<>(), context, photoClickListener, new SharedPrefsStorage(context));
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerViewScroller(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadPhotos();
            }
        });

        loadPhotos();
    }

    private void loadPhotos() {
        photosGetter.getPhotos()
            .enqueue(new Callback<ItemsList>() {
                @Override
                public void onResponse(Call<ItemsList> call, Response<ItemsList> response) {
                    List<Item> photos = response.body().getItems();
                    page++;
                    adapter.addPhotos(photos);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<ItemsList> call, Throwable t) {
                    Toast.makeText(context, "Failed to fetch photos",
                            Toast.LENGTH_LONG).show();
                }
            });

    }
}
