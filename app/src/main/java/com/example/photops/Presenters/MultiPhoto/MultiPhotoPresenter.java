package com.example.photops.Presenters.MultiPhoto;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photops.Data.Storage;
import com.example.photops.Network.PhotosGetter;
import com.example.photops.Network.UnsplashClientHandler;
import com.example.photops.Data.Photo;
import com.example.photops.Data.SharedPrefsStorage;
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
    private PhotosGetter dataService;
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
        dataService = UnsplashClientHandler.getUnsplashClient().create(PhotosGetter.class);

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
                        Toast.makeText(context, "Failed to fetch photo",
                                Toast.LENGTH_LONG).show();
                    }
                });

    }
}
