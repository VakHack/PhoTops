package com.example.photops.Presenters;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photops.Models.ActivePhotoHandler;
import com.example.photops.Models.Networking.PhotosGetter;
import com.example.photops.Models.Networking.UnsplashClientHandler;
import com.example.photops.Models.Photo;
import com.example.photops.Models.SharedPrefsActivePhotoHandler;
import com.example.photops.R;
import com.example.photops.Views.PhotosAdapter;
import com.example.photops.Views.RecyclerViewScroller;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MultiPhotoPresenter {
    private PhotosAdapter adapter;
    private PhotosAdapter.OnPhotoClickedListener photoClickListener;
    private PhotosGetter dataService;
    private RecyclerView recyclerView;
    private int page = 1;

    private Context context;
    private ActivePhotoHandler activePhotoHandler;

    private FragmentSwapper swapper;

    public MultiPhotoPresenter(Context context, FragmentSwapper swapper) {
        this.context = context;
        this.swapper = swapper;
        activePhotoHandler = new SharedPrefsActivePhotoHandler(context);
    }

    public void setView(View root){
        recyclerView = root.findViewById(R.id.recyclerView);
    }

    private void loadPhotos() {
        dataService.getPhotos(page,null,"latest")
            .enqueue(new Callback<List<Photo>>() {
                @Override
                public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                    List<Photo> photos = response.body();
                    page++;
                    adapter.addPhotos(photos);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<Photo>> call, Throwable t) {
                }
            });

    }


    public void present(){
        dataService = UnsplashClientHandler.getUnsplashClient().create(PhotosGetter.class);
        photoClickListener = (photo, imageView) -> {
            activePhotoHandler.setActivePhoto(photo);
            swapper.swap();
        };

        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);

        recyclerView.setLayoutManager(layoutManager);
        adapter = new PhotosAdapter(new ArrayList<>(), context, photoClickListener);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerViewScroller(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadPhotos();
            }
        });

        loadPhotos();
    }
}
