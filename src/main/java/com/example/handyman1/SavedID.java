package com.example.handyman1;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SavedID  {

    static  String id = "ID";

    public static void setId(String id) {
        SavedID.id = id;
    }

    public static String getId() {
        return id;
    }
    }

