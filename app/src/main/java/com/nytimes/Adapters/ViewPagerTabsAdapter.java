package com.nytimes.Adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.nytimes.Tabs.TabEmailed;
import com.nytimes.Tabs.TabShared;
import com.nytimes.Tabs.TabViewed;

public class ViewPagerTabsAdapter extends FragmentStatePagerAdapter {

    private int mNoOfTabs;

    public ViewPagerTabsAdapter(FragmentManager fm, int NumberOfTabs) {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                return new TabViewed();
            case 1:
                return new TabEmailed();
            case 2:
                return new TabShared();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
