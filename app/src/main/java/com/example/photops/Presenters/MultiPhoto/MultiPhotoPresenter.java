package com.example.photops.Presenters.MultiPhoto;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photops.Models.Photo.Item;
import com.example.photops.Models.Photo.ItemsList;
import com.example.photops.Models.Storage.Storage;
import com.example.photops.Models.Network.ItemsGetter;
import com.example.photops.Models.Storage.SharedPrefsStorage;
import com.example.photops.Models.Network.NetworkCoordinator;
import com.example.photops.UI.FragmentSwapper;
import com.example.photops.Presenters.PhotoPresenter;
import com.example.photops.R;
import com.example.photops.UI.PhotoAdapter;
import com.example.photops.UI.RecyclerViewScroller;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MultiPhotoPresenter extends PhotoPresenter {
    private PhotoAdapter adapter;
    private PhotoAdapter.OnPhotoClickedListener photoClickListener;
    private RecyclerView recyclerView;
    private int page = 1;
    private ItemsGetter itemsGetter;

    public MultiPhotoPresenter(Context context, FragmentSwapper swapper,
                               NetworkCoordinator networkCoordinator, Storage storage) {
        super(context, swapper, networkCoordinator, storage);
    }

    @Override
    public void setView(View root){
        recyclerView = root.findViewById(R.id.recyclerView);
    }

    @Override
    public void present(){
        itemsGetter = networkCoordinator.getClient().create(ItemsGetter.class);

        photoClickListener = (photo, imageView) -> {
            storage.setActivePhoto(photo);
            swapper.swap();
        };

        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PhotoAdapter(new ArrayList<>(), context, photoClickListener, new SharedPrefsStorage(context));
        recyclerView.setAdapter(adapter);
        recyclerView.smoothScrollToPosition(0);

        recyclerView.addOnScrollListener(new RecyclerViewScroller(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadPhotos();
            }
        });

        loadPhotos();
    }

    private void loadPhotos() {
        itemsGetter.getItems()
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
