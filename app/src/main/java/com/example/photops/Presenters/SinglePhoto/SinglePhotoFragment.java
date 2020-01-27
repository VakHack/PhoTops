package com.example.photops.Presenters.SinglePhoto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.photops.Presenters.PresenterInitializer;
import com.example.photops.R;

public class SinglePhotoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.single_photo_layout, container, false);

        PresenterInitializer initializer = new PresenterInitializer(getContext(), getFragmentManager(), false);
        SinglePhotoPresenter singlePhotoPresenter = (SinglePhotoPresenter) initializer.init();
        singlePhotoPresenter.setView(root);
        singlePhotoPresenter.present();

        return root;
    }
}
