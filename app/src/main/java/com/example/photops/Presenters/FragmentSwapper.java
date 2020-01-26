package com.example.photops.Presenters;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentSwapper {
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private int id;

    public FragmentSwapper(Fragment fragment, FragmentManager fragmentManager, int id) {
        this.fragment = fragment;
        this.fragmentManager = fragmentManager;
        this.id = id;
    }

    public void swap(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.commit();
    }
}
