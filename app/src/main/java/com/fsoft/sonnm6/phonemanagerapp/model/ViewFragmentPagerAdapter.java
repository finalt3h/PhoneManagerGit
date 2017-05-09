package com.fsoft.sonnm6.phonemanagerapp.model;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.ActionBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SonNM6 on 8/3/2016.
 */
public class ViewFragmentPagerAdapter extends FragmentPagerAdapter {
    ActionBar mActionBar;
    private ArrayList<Fragment> mFragments;

    public ViewFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void setActionBar(ActionBar bar) {
        mActionBar = bar;
    }
}
