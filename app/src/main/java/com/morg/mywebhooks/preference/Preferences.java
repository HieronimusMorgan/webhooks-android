package com.morg.mywebhooks.preference;

import android.content.Context;
import android.content.SharedPreferences;


public class Preferences {

    private SharedPreferences sharedPreferences;

    public Preferences(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("SessionWebhooks", 0);
        }
    }

    public Preferences(SharedPreferences thisPreferences) {
        this.sharedPreferences = thisPreferences;
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void setString(String key, String value) {
        if (!getString(key).equals(value)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }
}
