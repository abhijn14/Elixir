package com.rd.rushit.elixir;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin on 22-09-2017.
 */

public class FAQFragment extends Fragment {


    ArrayList<String> namelist = new ArrayList<>();
    ArrayList<String> mobilenolist = new ArrayList<>();
    View myView;
    BottomNavigationView bottomNavigationView;
    ListView l;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.faqfragment, container, false);
        l = (ListView) myView.findViewById(R.id.list_faq);
        namelist.add("How do I edit My Profile Details?");
        mobilenolist.add("Go to MyProfile from the bottom menu and click on the pencil icon above your profile picture.");
        namelist.add("How do I add medical records?");
        mobilenolist.add("Go to my profile and click on view besides medical records and then click on add button");
        namelist.add("How do I check my General Info?");
        mobilenolist.add("Go to my profile and click on view button besides general info.");
        namelist.add("How do I search for hospitals/blood bank/pharmacy?");
        mobilenolist.add("Go to the search page from bottom menu and select the category and either enter the name or area of the place.");

        MyAdapter adapter = new MyAdapter(getActivity(), namelist,mobilenolist);
        l.setAdapter(adapter);
        return myView;
    }
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("FAQs");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle(null);
    }
    class MyAdapter extends BaseAdapter {
        ArrayList<String> namelist = new ArrayList<>();
        ArrayList<String> mobilenolist = new ArrayList<>();
        LayoutInflater inflater = null;
        Context context;

        public MyAdapter(Context context, ArrayList<String> namelist,ArrayList<String> mobilenolist) {
            this.namelist = namelist;
            this.mobilenolist = mobilenolist;
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

                vi = inflater.inflate(R.layout.custom_faq, null);

            TextView myName = (TextView) vi.findViewById(R.id.heading);
            TextView myMobile = (TextView) vi.findViewById(R.id.subheading);

            myName.setText(namelist.get(position));
            myMobile.setText(mobilenolist.get(position));
            return vi;
        }
    }

}
