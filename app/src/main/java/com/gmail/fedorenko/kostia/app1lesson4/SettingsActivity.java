package com.gmail.fedorenko.kostia.app1lesson4;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by kfedoren on 05.10.2015.
 */
public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
