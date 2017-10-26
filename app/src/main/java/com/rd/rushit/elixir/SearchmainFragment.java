package com.rd.rushit.elixir;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by admin on 22-09-2017.
 */

public class SearchmainFragment extends Fragment {

    ImageView m,d,s;
    View myView;
    BottomNavigationView bottomNavigationView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.searchfragment, container, false);

        d = (ImageView) myView.findViewById(R.id.doc_img);
        s = (ImageView) myView.findViewById(R.id.store_img);
        m = (ImageView) myView.findViewById(R.id.blood_img);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HospitalFragment hospitalFragment = new HospitalFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content,hospitalFragment,hospitalFragment.getTag()).addToBackStack("abhisaarimg").commit();
            }
        });
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoresFragment storesFragment = new StoresFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content,storesFragment,storesFragment.getTag()).addToBackStack("abhisaarimg").commit();
            }
        });
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodFragment bloodFragment = new BloodFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content,bloodFragment,bloodFragment.getTag()).addToBackStack("abhisaarimg").commit();
            }
        });
        return myView;
    }
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Search");
    }
}
