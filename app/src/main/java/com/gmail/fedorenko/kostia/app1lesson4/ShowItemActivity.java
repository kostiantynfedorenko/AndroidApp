package com.gmail.fedorenko.kostia.app1lesson4;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;



import java.util.ArrayList;
//GITTEST
/**
 * Created by kfedoren on 23.09.2015.
 */
public class ShowItemActivity extends AppCompatActivity implements android.support.v7.app.ActionBar.TabListener {
    private static final String TAG = "ShowItemActivity";
    private Item itemShow;
    private ViewPager viewPager;
    private android.support.v7.app.ActionBar actionBar;
    private ArrayList<Item> ItemList;
    private ArrayList<String> titles;
    private MySQLiteHelper db;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_item);
        Bundle data = getIntent().getExtras();
        itemShow = (Item) data.getParcelable("item");
        db = new MySQLiteHelper(this);
        ItemList = db.getAllItems();
        titles = db.getItemPlaces();
        Log.i(TAG, "Starting fragment adapter...! currently with item " + itemShow.getPlace());
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), ItemList, titles);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS);
        viewPager.setAdapter(adapter);
        // Adding Tabs
        for (String title : titles) {
            actionBar.addTab(actionBar.newTab().setText(title)
                    .setTabListener(this));
        }

        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }


    @Override
    public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }
}
