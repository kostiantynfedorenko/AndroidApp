package com.gmail.fedorenko.kostia.app1lesson4;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Created by kfedoren on 17.09.2015.
 */

//GITTEST
public class Item implements Parcelable {
    //Items table name
    public static final String TABLE_NAME = "items";
    //Items Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_PLACE = "place";
    public static final String KEY_TIME = "time";
    public static final String KEY_DATE = "date";
    public static final String KEY_REGION = "region";
    public static final String KEY_URI = "uri";

    public static final String[] COLUMNS = {KEY_ID, KEY_PLACE, KEY_TIME, KEY_DATE, KEY_REGION, KEY_URI};

    private int id;
    private String place;
    private String time;
    private String date;
    private String region;
    private String uri;

    public Item() {
    }

    public Item(String place, String time, String date, String region, String uri) {
        this.place = place;
        this.time = time;
        this.date = date;
        this.region = region;
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time == null ? "" : time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date == null ? "" : date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Item: Description - " + this.place + ";Date - " + this.date + ";Time - " + this.time + ";ID - " + this.getId() + ";Region - " + this.region + ";URI - " + this.uri;
    }

    @Override
    public boolean equals(Object o) {
        Item item = (Item) o;
        if ((item.getPlace().equals(this.getPlace())) && (item.getDate().equals(this.getDate())) && (item.getTime().equals(this.getTime())) && (item.getRegion().equals(this.getRegion())))
            return true;
        else return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Item(Parcel in) {
        String[] dataString = new String[5];
        in.readStringArray(dataString);
        this.place = dataString[0];
        this.time = dataString[1];
        this.date = dataString[2];
        this.region = dataString[3];
        this.uri = dataString[4];
        int[] dataInt = new int[1];
        in.readIntArray(dataInt);
        this.id = dataInt[0];
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{
                this.place,
                this.time,
                this.date,
                this.region,
                this.uri
        });
        dest.writeIntArray(new int[]{
                this.id});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
