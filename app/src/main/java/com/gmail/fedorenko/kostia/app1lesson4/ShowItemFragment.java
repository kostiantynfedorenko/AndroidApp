package com.gmail.fedorenko.kostia.app1lesson4;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ShowItemFragment extends Fragment {
    private Item itemS;
    private static TextView showtime;
    private static TextView showdate;
    private static TextView showdesc;
    private static TextView showregion;
    private static ImageView showimage;
    private String desc;
    private String date;
    private String time;
    private Bitmap bmp;
    private String region;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_item, container, false);
        setHasOptionsMenu(true);
        Bundle args = getArguments();
        itemS = args.getParcelable(Item.class.getName());
        TextView textView_place = (TextView) view.findViewById(R.id.textShow_item);
        showimage = (ImageView) view.findViewById(R.id.show_image);
        showtime = (TextView) view.findViewById(R.id.show_time);
        showdate = (TextView) view.findViewById(R.id.show_date);
        showdesc = (TextView) view.findViewById(R.id.show_desc);
  //      Log.i("ItemFragment", "Changing text in text view from: " + textView_place.getText() + " to: " + itemS.getRegion());
        textView_place.setText(itemS.getRegion());
        showimage.setImageURI(Uri.parse(itemS.getUri()));
        showtime.setText(itemS.getTime());
        showdate.setText(itemS.getDate());
        showdesc.setText(itemS.getPlace());

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.i("ItemFragment", "Menu inflated");
        inflater.inflate(R.menu.menu_delete, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                Toast.makeText(this.getActivity(), "To write object deletion propperly!", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }


}
