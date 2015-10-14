package com.gmail.fedorenko.kostia.app1lesson4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Kostia on 14.10.2015.
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Item> ItemList;
    private ArrayList<String> titles;

    public MyPagerAdapter(FragmentManager fm, ArrayList<Item> itemList, ArrayList<String> titles) {
        super(fm);
        this.ItemList = itemList;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment showItemFragment =new ShowItemFragment();

        Bundle args=new Bundle();
        args.putParcelable(Item.class.getName(), ItemList.get(position));
        Log.i("PagerAdapter", ItemList.get(position).getPlace());

        showItemFragment.setArguments(args);
        return (Fragment)showItemFragment;
    }

    @Override
    public int getCount() {
        return this.ItemList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles.get(position);
    }
}
