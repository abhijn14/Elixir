package com.rd.rushit.elixir;

//import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

/**
 * Created by admin on 22-09-2017.
 */

public class HomeFragment extends Fragment {

    int[] images = {R.mipmap.ebola,R.mipmap.maxresdefault,R.mipmap.health_myth};
    View myView;
    ViewPager viewPager;
    ViewPageAdapter adapter;
    BottomNavigationView bottomNavigationView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.homefragment, container, false);
        viewPager = (ViewPager) myView.findViewById(R.id.viewPager);
        adapter = new ViewPageAdapter(getActivity(),images);
        viewPager.setAdapter(adapter);
        return myView;
    }
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Home");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle(null);
    }
}
