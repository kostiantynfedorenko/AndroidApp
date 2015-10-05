package com.gmail.fedorenko.kostia.app1lesson4;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by kfedoren on 05.10.2015.
 */
public class MyPreferencesFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

}
