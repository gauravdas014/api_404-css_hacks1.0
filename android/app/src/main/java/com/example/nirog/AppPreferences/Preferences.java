package com.example.nirog.AppPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

public class Preferences implements SharedPreferencesHelper {

    private  Context context;
    private static Preferences instance;

    private static final String BABY_ID = null;
    private static final String USER_ID = null;

    private SharedPreferences sharedPreferences;

    public Preferences(Context context){
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static Preferences getInstance(Context context){
        if(instance == null){
            synchronized (Preferences.class){
                if(instance == null){
                    instance = new Preferences(context);
                }
            }
        }

        return instance;
    }


    @Override
    public void AddBabyId(String id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(BABY_ID, id);
        editor.apply();

    }

    @Override
    public void AddUserId(String id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_ID, id);
        editor.apply();
    }

    @Override
    public String RetrieveBabyId() {
        return sharedPreferences.getString(BABY_ID, null);
    }

    @Override
    public String RetrieveUserId() {
        return sharedPreferences.getString(USER_ID, null);
    }
}
