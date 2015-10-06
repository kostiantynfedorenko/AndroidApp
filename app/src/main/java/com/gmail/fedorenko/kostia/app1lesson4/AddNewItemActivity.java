package com.gmail.fedorenko.kostia.app1lesson4;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//GITTEST
/**
 * Created by kfedoren on 17.09.2015.
 */
public class AddNewItemActivity extends ActionBarActivity implements RegionPickerFragment.RegionPickerListener {
    private static final int REQUEST_CODE_NEW_IMAGE = 1;
    private static final String TAG = "AddNewItemActivity";
    private Boolean useSpinner;
    private ImageView picture;
    private Button setDate;
    private Button setTime;
    private Button setRegion;
    private static TextView time;
    private static TextView date;
    private EditText desc;
    private Bitmap image;
    private TextView regionView;
    private Spinner spinner;
    private String regionFromPicker;
    private Uri mImageUri = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_item);

        picture = (ImageView) findViewById(R.id.add_image);
        setDate = (Button) findViewById(R.id.set_date);
        setTime = (Button) findViewById(R.id.set_time);
        setRegion = (Button) findViewById(R.id.set_region);
        time = (TextView) findViewById(R.id.time);
        date = (TextView) findViewById(R.id.date);
        desc = (EditText) findViewById(R.id.desc);
        regionView = (TextView) findViewById(R.id.region);

        picture.setOnClickListener(onImageClick());
        setDate.setOnClickListener(onSetDateClick());
        setTime.setOnClickListener(onSetTimeClick());
        setRegion.setOnClickListener(onSetRegionClick());

        SharedPreferences preferencesSet = PreferenceManager.getDefaultSharedPreferences(this);
        useSpinner = preferencesSet.getBoolean("use_spinner", false);

        if (useSpinner) {
            spinner = (Spinner) findViewById(R.id.region_spinner);
            spinner.setVisibility(View.VISIBLE);
            setRegion.setVisibility(View.GONE);
            ArrayAdapter<CharSequence> adapterSp = ArrayAdapter.createFromResource(this,
                    R.array.regions_array, android.R.layout.simple_spinner_item);
            adapterSp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapterSp);
            regionFromPicker = spinner.getSelectedItem().toString();
        }
    }

    private View.OnClickListener onImageClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        };
    }

    private View.OnClickListener onSetTimeClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        };
    }

    private View.OnClickListener onSetDateClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        };
    }

    private View.OnClickListener onSetRegionClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new RegionPickerFragment();
                newFragment.show(getFragmentManager(), "regionPicker");
            }
        };
    }

    public void takePicture() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo;
        try
        {
            // place where to store camera taken picture
            photo = Util.createTemporaryFile("picture_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()), ".jpg");
            photo.delete();
        }
        catch(Exception e)
        {
            Log.v(TAG, "Can't create file to take picture!");
            Toast.makeText(getApplicationContext(), "Please check SD card! Image shot is impossible!", Toast.LENGTH_LONG);
            return;
        }
        mImageUri = Uri.fromFile(photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
        //start camera intent
        startActivityForResult(intent, REQUEST_CODE_NEW_IMAGE);

 //       intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
  //      if (intent.resolveActivity(getPackageManager()) != null) {
   //         startActivityForResult(intent, REQUEST_CODE_NEW_IMAGE);
    //    }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_NEW_IMAGE && resultCode == RESULT_OK) {
            Util.grabImage(getApplicationContext(),picture,mImageUri);
            Toast.makeText(getApplicationContext(), mImageUri.toString(), Toast.LENGTH_LONG).show();
            image = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(mImageUri.getPath()), 100, 100);
        }
    }

    @Override
    public void onDialogPositiveClick(String string) {
        regionFromPicker = string;
        regionView.setText("Chosen region: " + regionFromPicker);
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String timeString = hourOfDay + ":" + minute;
            time.setText(Util.formatTime(timeString));
        }
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            date.setText(year + "/" + month + "/" + day);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                Intent intent = new Intent();
                if (image != null && date.getText().toString().length() > 0 && time.getText().toString().length() > 0 && desc.getText().toString().length() > 0) {
                    intent.putExtra("item", new Item(desc.getText().toString(), time.getText().toString(), date.getText().toString(), image, regionFromPicker, mImageUri.toString()));
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill in all info !", Toast.LENGTH_LONG).show();
                }
                break;
        }
        return true;
    }
}
