package com.example.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.myapplication.dto.response.UserResponse;
import com.example.myapplication.dto.response.UserSettingsResponse;
import com.google.gson.Gson;

import static com.example.myapplication.util.PreferencesUtility.LOGGED_IN_PREF;
import static com.example.myapplication.util.PreferencesUtility.LOGGED_IN_USER_INFO;
import static com.example.myapplication.util.PreferencesUtility.LOGGED_IN_USER_SETTINGS;

public class SaveSharedPreference {

    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Set the Login Status
     * @param context
     * @param loggedIn
     */
    public static void setLoggedIn(Context context, boolean loggedIn , UserResponse object) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.apply();
        Gson gson = new Gson();
        String json = gson.toJson(object);
        editor.putString(LOGGED_IN_USER_INFO, json);
        editor.commit();
    }

    /**
     * Get the Login Status
     * @param context
     * @return boolean: login status
     */
    public static boolean getLoggedStatus(Context context) {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }

    public static UserResponse getLoggedObject(Context context) {
        Gson gson = new Gson();
        String json = getPreferences(context).getString(LOGGED_IN_USER_INFO, "");
        UserResponse obj = gson.fromJson(json, UserResponse.class);
        return obj;
    }

    public static void setSettings(Context context, UserSettingsResponse object) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(object);
        editor.putString(LOGGED_IN_USER_SETTINGS, json);
        editor.commit();
    }

    public static UserSettingsResponse getSettings(Context context) {
        Gson gson = new Gson();
        String json = getPreferences(context).getString(LOGGED_IN_USER_SETTINGS, "");
        return gson.fromJson(json, UserSettingsResponse.class);
    }
}
