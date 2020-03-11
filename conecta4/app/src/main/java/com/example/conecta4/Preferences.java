package com.example.conecta4;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Preferences extends PreferenceActivity {
    @Override
    public void onCreate(Bundle saveInstaceState){
        super.onCreate(saveInstaceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}