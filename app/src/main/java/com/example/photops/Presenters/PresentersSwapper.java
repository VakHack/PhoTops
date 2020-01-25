package com.example.photops.Presenters;

import android.content.Context;
import android.view.View;

import com.example.photops.Models.AppDB;
import com.example.photops.Models.SharedPrefsAppDB;
import com.example.photops.Models.StateChangeActionHandler;

public class PresentersSwapper {
    private View presenterView;
    private AppDB appDB;

    class ReturnViewVisibility implements StateChangeActionHandler{
        private View view;

        public ReturnViewVisibility(View view) {
            this.view = view;
        }

        @Override
        public void performOnStateChange() {
            view.setVisibility(View.VISIBLE);
        }
    }

    public PresentersSwapper(Context context, View presenterView) {
        this.presenterView = presenterView;
        this.appDB = new SharedPrefsAppDB(context);
    }

    public void run(){
        //waiting until the other presenter stop running, than returning to be active
        presenterView.setVisibility(View.INVISIBLE);
        appDB.waitOnAppState(new ReturnViewVisibility(presenterView));
    }
}
