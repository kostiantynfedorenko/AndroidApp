package com.gmail.fedorenko.kostia.app1lesson4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//GITTEST
/**
 * Created by kfedoren on 23.09.2015.
 */
public class Util {
    public static byte[] bitmapToByteArray(Bitmap bitmap){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
        return os.toByteArray();
    }

    public static Bitmap byteArrayToBitmap(byte[] bytearray) {
        return BitmapFactory.decodeByteArray(bytearray, 0, bytearray.length);
    }

    public static String formatTime (String input){
        String output = input;
        SimpleDateFormat fromInput = new SimpleDateFormat("HH:mm");
        SimpleDateFormat toOutput = new SimpleDateFormat("HH:mm");
        try {
            String reformattedTime = toOutput.format(fromInput.parse(input));
            output = reformattedTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return output;
    }

    private static final String LOG_TAG = "Util";

    /* Перевірка, чи доступне зовнішнє сховище для читання та запису */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Перевірка, чи доступне зовнішнє сховище принаймі для читання */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
    public File getAlbumStorageDir(String albumName) {
        // Отримати каталог для публічного каталогу фотографій користувача.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }

    public File getAlbumStorageDir(Context context, String albumName) {
        // Отримати каталог для приватного каталогу зображень додатку.
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }
}
