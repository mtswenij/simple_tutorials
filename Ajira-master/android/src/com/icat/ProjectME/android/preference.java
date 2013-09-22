package com.icat.ProjectME.android;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

public class preference extends PreferenceActivity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
 
        
        super.onCreate(savedInstanceState);
 
        //add the prefernces.xml layout
        addPreferencesFromResource(R.xml.account_prefs);       
 
         
        //get the specified preferences using the key declared in preferences.xml
        ListPreference dataPref = (ListPreference) findPreference("date");
 
        //get the description from the selected item
        dataPref.setSummary(dataPref.getEntry());
 
            //when the user choose other item the description changes too with the selected item
            dataPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    preference.setSummary(o.toString());
                    return true;
                }
                
            });
 
    }
} 