package com.gmail.fedorenko.kostia.app1lesson4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
//GITTEST
/**
 * Created by kfedoren on 23.09.2015.
 */
public class ShowItemActivity extends ActionBarActivity {
    private static final String TAG = "ShowItemActivity";
    private Item itemShow;
    private ViewPager viewPager;
    private ArrayList<Item> ItemList;
    private ArrayList<String> titles;
    private MySQLiteHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_item);
        Bundle data = getIntent().getExtras();
        itemShow = (Item) data.getParcelable("item");
        db = new MySQLiteHelper(this);
        ItemList = db.getAllItems();
        titles = db.getItemPlaces();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                Intent intent = new Intent();
                intent.putExtra("item", itemShow);
                Toast.makeText(getApplicationContext(), "Deleting item....!", Toast.LENGTH_LONG).show();
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
        return true;
    }
    private void setupViewPager(ViewPager viewPager) {

       MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), ItemList, titles);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(itemShow.getId());

    }
}
