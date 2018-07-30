package com.test.moneytap.wikiapp;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {

    public static final String PREFERENCE_SEARCH = "SEARCH_CACHE";
    public static final String PREFERENCE_KEY_SEARCH = "SEARCH_CACHE";

    // Prevent instantiation
    private PreferenceUtils() {

    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCE_SEARCH, Context.MODE_PRIVATE);
    }

    public static SharedPreferences.Editor getSharedPrefEditor(Context context) {
        return getSharedPreferences(context).edit();
    }

    public static void setRecentSearches(Context context,String string) {
        SharedPreferences.Editor prefEditor=getSharedPrefEditor(context);
        prefEditor.putString(PREFERENCE_KEY_SEARCH,string).apply();
    }

    public static String getRecentSearches(Context context){
        SharedPreferences preferences=getSharedPreferences(context);
        return preferences.getString(PREFERENCE_KEY_SEARCH,"");
    }
    public static void clearAll(Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.clear().apply();
    }
}
