package com.gmail.fedorenko.kostia.app1lesson4;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ShowItemFragment extends Fragment {
    private Item item;

    public static ShowItemFragment newInstance(Item item) {

        ShowItemFragment fragment = new ShowItemFragment();

        Bundle args = new Bundle();
        args.putParcelable("item", item);

        fragment.setArguments(args);

        return fragment;
    }

    public ShowItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        item = getArguments().getParcelable("item");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_item, container, false);

        TextView textView_place = (TextView) view.findViewById(R.id.textShow_item);
        textView_place.setText(item.getPlace());
        return view;

    }


}
