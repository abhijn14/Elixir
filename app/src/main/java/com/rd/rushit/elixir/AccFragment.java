package com.rd.rushit.elixir;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by admin on 22-09-2017.
 */

public class AccFragment extends Fragment {

    View myView;
    BottomNavigationView bottomNavigationView;
    Button m;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.accfragment, container, false);
        m = (Button) myView.findViewById(R.id.view_1);
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FolderFragment folderFragment = new FolderFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content,folderFragment,folderFragment.getTag()).addToBackStack("abhisaarimg").commit();
            }
        });
        return myView;
    }
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("My Account");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle(null);
    }
}
