package com.rd.rushit.elixir;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 22-09-2017.
 */

public class FolderFragment extends Fragment {

    View myView;
    BottomNavigationView bottomNavigationView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.folder, container, false);
        return myView;
    }
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("My Account");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle("Manager Records");
    }
}
