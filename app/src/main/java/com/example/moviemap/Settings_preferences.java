package com.example.moviemap;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;


public class Settings_preferences extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

        addPreferencesFromResource(R.xml.preferences);

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int count = preferenceScreen.getPreferenceCount();

        for (int i = 0; i < count; i++) {

            Preference preference = preferenceScreen.getPreference(i);
            if (preference instanceof ListPreference) {

                setSummary(preference, sharedPreferences.getString(preference.getKey(), ""));

            }

//
//            ListPreference listPreference =
//                    (ListPreference) findPreference(getResources().getString(R.string.sort_key));
//
//            Log.d(MainActivity.TAG, listPreference.getValue());

        }


    }

    public void setSummary(Preference preference, String value) {

        ListPreference listPreference = (ListPreference) preference;
        int index = listPreference.findIndexOfValue(value);
        //not invalid
        if (index >= 0)
            listPreference.setSummary(listPreference.getEntries()[index]);

    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {


        Preference preference = findPreference(key);
        if (preference instanceof ListPreference) {
            String value = sharedPreferences.getString(key, "");
            setSummary(preference, value);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //register the listner
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
