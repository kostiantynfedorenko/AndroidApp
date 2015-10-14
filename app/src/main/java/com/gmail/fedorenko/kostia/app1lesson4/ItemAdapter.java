package com.gmail.fedorenko.kostia.app1lesson4;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

//GITTEST
/**
 * Created by kfedoren on 17.09.2015.
 */
public class ItemAdapter extends ArrayAdapter<Item> {
    private final Context context;
    private final ArrayList<Item> values;

    public ItemAdapter(Context context, ArrayList<Item> values) {
        super(context, R.layout.activity_main, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.item_list, parent, false);

        TextView place = (TextView) rowView.findViewById(R.id.place);
        TextView dateTime = (TextView) rowView.findViewById(R.id.date_time);
        ImageView icon = (ImageView) rowView.findViewById(R.id.icon);

        String placeStr = values.get(position).getPlace();
        String dateStr = values.get(position).getDate();
        String timeStr = values.get(position).getTime();

        place.setText(placeStr);
        dateTime.setText("On: " + dateStr + "; at: " + timeStr);

 //       new LoadImageAsT(icon, context).execute(values.get(position).getUri().toString());
        icon.setImageURI(Uri.parse(values.get(position).getUri()));

        return rowView;
    }

    class LoadImageAsT extends AsyncTask<String,Void,Bitmap>{
        private final WeakReference<ImageView> imageViewReference;
        private final Context context;

        public LoadImageAsT(ImageView imageView, Context context) {

            this.context = context;
            this.imageViewReference = new WeakReference<ImageView>(imageView);

        }

        @Override
        protected Bitmap doInBackground(String... params) {

            String filepath = params[0];

            try {

                File photo = new File(filepath);
                Uri mImageUri = Uri.fromFile(photo);
                return Util.getBitmapFromUri(context, mImageUri, 4);

            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView = imageViewReference.get();
                if (imageView != null)
                    imageView.setImageBitmap(bitmap);

            }

        }
    }
}
