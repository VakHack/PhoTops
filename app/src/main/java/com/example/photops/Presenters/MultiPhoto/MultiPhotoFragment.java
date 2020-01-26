package com.example.photops.Presenters.MultiPhoto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.photops.Presenters.FragmentSwapper;
import com.example.photops.R;
import com.example.photops.Presenters.SinglePhoto.SinglePhotoFragment;

public class MultiPhotoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.multi_photo_layout, container, false);

        //creating and sending the other fragment object - so the presenter will be able to
        //swap to it whenever necessary
        Fragment singlePhotoFragment = new SinglePhotoFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentSwapper fragmentSwapper = new FragmentSwapper(singlePhotoFragment, fragmentManager, R.id.container);

        MultiPhotoPresenter multiPhotoPresenter = new MultiPhotoPresenter(getActivity(), fragmentSwapper);
        multiPhotoPresenter.setView(root);
        multiPhotoPresenter.present();

        return root;
    }
}
