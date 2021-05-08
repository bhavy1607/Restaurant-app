package com.example.restaurant_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class staffdetailadepter extends FragmentPagerAdapter {

    int tabcount;

    public staffdetailadepter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount = behavior;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new AdminManagerFragment();
            case 1 : return new AdminCookFragment();
            case 2 : return new AdminWaiterFragment();
            default:return null;

        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
