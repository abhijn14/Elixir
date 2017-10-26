package com.rd.rushit.elixir;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin on 22-09-2017.
 */

public class BloodavailableFragment extends Fragment {


    ArrayList<String> namelist = new ArrayList<>();
    View myView;
    BottomNavigationView bottomNavigationView;
    ListView l;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.bloodavailable, container, false);
        l = (ListView) myView.findViewById(R.id.list_appo);
        namelist.add("A+");
        namelist.add("B+");
        namelist.add("AB+");
        namelist.add("B-");
        namelist.add("A-");
        namelist.add("O+");
        namelist.add("O-");
        MyAdapter adapter = new MyAdapter(getActivity(), namelist);
        l.setAdapter(adapter);
        return myView;
    }
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Hospitals");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle("Appointment");
    }
    class MyAdapter extends BaseAdapter {
        ArrayList<String> namelist = new ArrayList<>();
        LayoutInflater inflater = null;
        Context context;

        public MyAdapter(Context context, ArrayList<String> namelist) {
            this.namelist = namelist;
            this.context = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return namelist.size();
        }

        @Override
        public Object getItem(int position) {
            return namelist;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View vi = convertView;
            if (convertView == null)

                vi = inflater.inflate(R.layout.custom_appo, null);

            TextView myName = (RadioButton) vi.findViewById(R.id.appo_r);

            myName.setText(namelist.get(position));

            return vi;
        }
    }

}
