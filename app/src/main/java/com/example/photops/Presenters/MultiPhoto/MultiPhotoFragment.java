package com.example.photops.Presenters.MultiPhoto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.photops.Presenters.PresenterInitializer;
import com.example.photops.Presenters.SinglePhoto.SinglePhotoPresenter;
import com.example.photops.R;

public class MultiPhotoFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.multi_photo_layout, container, false);

        PresenterInitializer initializer = new PresenterInitializer(getContext(), getFragmentManager(), true);
        MultiPhotoPresenter multiPhotoPresenter = (MultiPhotoPresenter) initializer.init();
        multiPhotoPresenter.setView(root);
        multiPhotoPresenter.present();

        return root;
    }
}
