package com.nytimes.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import com.nytimes.Adapters.ViewPagerTabsAdapter;
import com.nytimes.R;
import com.nytimes.Retrofit.Common;
import com.nytimes.Tabs.TabEmailed;
import com.nytimes.Tabs.TabShared;
import com.nytimes.Tabs.TabViewed;



public class MainActivity extends AppCompatActivity implements
        TabViewed.OnFragmentInteractionListener,
        TabEmailed.OnFragmentInteractionListener,
        TabShared.OnFragmentInteractionListener {

    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!Common.isNetworkAvailable(this))
            Toast.makeText(this, "network connection is: "+ Common.isNetworkAvailable(this), Toast.LENGTH_LONG).show();

        mTabLayout = findViewById(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.tab_most_viewed)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.tab_most_emailed)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.tab_most_shared)));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager mPager = findViewById(R.id.pager);
        final PagerAdapter mAdapter = new ViewPagerTabsAdapter(getSupportFragmentManager(),mTabLayout.getTabCount());
        mPager.setAdapter(mAdapter);

        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
