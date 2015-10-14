package com.gmail.fedorenko.kostia.app1lesson4;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Kostia on 14.10.2015.
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private int count = 0;
    private ArrayList<Item> ItemList;
    private ArrayList<String> titles;

    public MyPagerAdapter(FragmentManager fm, ArrayList<Item> itemList, ArrayList<String> titles) {
        super(fm);
        ItemList = itemList;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        Item item = ItemList.get(position);
        ShowItemFragment fragment = ShowItemFragment.newInstance(item);
        return fragment;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles.get(position);
    }
}
