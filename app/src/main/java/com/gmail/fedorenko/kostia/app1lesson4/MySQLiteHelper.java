package com.gmail.fedorenko.kostia.app1lesson4;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
//GITTEST

/**
 * Created by kfedoren on 22.09.2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "testtable";
    private static final String TAG = "MySQLiteHelper";

    MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL create table
        String CREATE_ITEM_TABLE = "CREATE TABLE " + Item.TABLE_NAME + " ( " +
                Item.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Item.KEY_PLACE + " TEXT, " +
                Item.KEY_TIME + " TEXT, " +
                Item.KEY_DATE + " TEXT, " +
                Item.KEY_REGION + " TEXT, " +
                Item.KEY_URI + " TEXT)";

        //create table
        db.execSQL(CREATE_ITEM_TABLE);
    }

    public void addItem(Item item) {
        //get writeable DB
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            //create ContentValues to add
            ContentValues values = new ContentValues();
            values.put(item.KEY_PLACE, item.getPlace());
            values.put(item.KEY_TIME, item.getTime());
            values.put(item.KEY_DATE, item.getDate());
            values.put(item.KEY_REGION, item.getRegion());
            values.put(item.KEY_URI, item.getUri());
            //Insert
            db.insert(item.TABLE_NAME, null, values);
            //close
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        } finally {
            if (null != db) {
                db.close();
            }
        }

    }

    public ArrayList<Item> getAllItems() {
        ArrayList<Item> items = new ArrayList<>();
        String query = "SELECT * FROM " + Item.TABLE_NAME;
        SQLiteDatabase db = null;
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            Item item = null;
            if (cursor.moveToFirst()) {
                do {
                    item = new Item();
                    item.setId(Integer.parseInt(cursor.getString(0)));
                    item.setPlace(cursor.getString(1));
                    item.setTime(cursor.getString(2));
                    item.setDate(cursor.getString(3));
                    item.setRegion(cursor.getString(4));
                    item.setUri(cursor.getString(5));
                    items.add(item);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        } finally {
            if (null != db) {
                db.close();
            }
        }
        return items;
    }

    public void deleteItem(Item item) {
        // 1. get reference to writable DB
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();

            // 2. delete
            db.delete(item.TABLE_NAME, item.KEY_ID + " = ?", new String[]{String.valueOf(item.getId())});

            // 3. close
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        } finally {
            if (null != db) {
                db.close();
            }
        }
        Log.d("deleteItem(" + item.getId() + ")", item.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS items");
        // create fresh table
        this.onCreate(db);
    }

    public Item getItem(int id) {
        // 1. get reference to readable DB
        SQLiteDatabase db = null;
        Item item = null;
        try {
            db = this.getReadableDatabase();

            // 2. build query
            Cursor cursor = db.query(Item.TABLE_NAME, // a. table
                    Item.COLUMNS, // b. column names
                    " id = ?", // c. selections
                    new String[]{String.valueOf(id)}, // d. selections args
                    null, // e. group by - how to group rows
                    null, // f. having - which row groups to include (filter)
                    null, // g. order by
                    null); // h. limit

            // 3. if we got results get the first one
            if (cursor != null)
                cursor.moveToFirst();

            // 4. build ad object
            item = new Item();
            item.setId(Integer.parseInt(cursor.getString(0)));
            item.setPlace(cursor.getString(1));
            item.setTime(cursor.getString(2));
            item.setDate(cursor.getString(3));
            item.setRegion(cursor.getString(4));
            item.setUri(cursor.getString(5));

            Log.d("getItem(" + id + ")", item.toString());
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        } finally {
            if (null != db) {
                db.close();
            }
        }
        return item;
    }

    public Item getItemByDesc(String desc) {
        // 1. get reference to readable DB
        SQLiteDatabase db = null;
        Item item = null;
        try {
            db = this.getReadableDatabase();

            // 2. build query
            Cursor cursor = db.query(Item.TABLE_NAME, // a. table
                    Item.COLUMNS, // b. column names
                    " place = ?", // c. selections
                    new String[]{desc}, // d. selections args
                    null, // e. group by - how to group rows
                    null, // f. having - which row groups to include (filter)
                    null, // g. order by
                    null); // h. limit

            // 3. if we got results get the first one
            if (cursor != null)
                cursor.moveToFirst();

            // 4. build ad object
            item = new Item();
            item.setId(Integer.parseInt(cursor.getString(0)));
            item.setPlace(cursor.getString(1));
            item.setTime(cursor.getString(2));
            item.setDate(cursor.getString(3));
            item.setRegion(cursor.getString(4));
            item.setUri(cursor.getString(5));

            Log.d("getItem(" + desc + ")", item.toString());
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        } finally {
            if (null != db) {
                db.close();
            }
        }
        return item;
    }

    public ArrayList<String> getItemPlaces() {
        ArrayList<String> places = new ArrayList<>();
        String query = "SELECT * FROM " + Item.TABLE_NAME;
        SQLiteDatabase db = null;
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    places.add(cursor.getString(1));
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        } finally {
            if (null != db) {
                db.close();
            }
        }
        return places;
    }

}
