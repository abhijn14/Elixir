package com.rd.rushit.elixir;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.app.Fragment;


/**
 * Created by admin on 23-09-2017.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }



    @Override
    public android.support.v4.app.Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new HospitalFragment();
            case 1:
                // Games fragment activity
                return new StoresFragment();
            case 2:
                // Movies fragment activity
                return new BloodFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
}
