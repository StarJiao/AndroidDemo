package com.star.demo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

public class SPTester {
    public static final String SP_KEY_RESPONSE = "sp_key_response";
    public static final String KEY_RESPONSE = "key_response";

    private SharedPreferences preferences;

    public SPTester(Context context) {
        preferences = context.getSharedPreferences(SP_KEY_RESPONSE, Activity.MODE_PRIVATE);
    }

    public SPTester(Activity context) {
        preferences = context.getPreferences(Activity.MODE_PRIVATE);
    }

    public Set<String> getStringSet() {
        Set<String> set = preferences.getStringSet(SPTester.KEY_RESPONSE, new HashSet<String>());
        Log.i("SPTester", "old data: " + set.toString());
        return set;
    }

    public boolean addCustomResponse(String newResponse) {
        Set<String> set = preferences.getStringSet(SPTester.KEY_RESPONSE, new HashSet<String>());
        Log.i("SPTester", "old data: " + set.toString());
        Set<String> newSet = new HashSet<>(set);
        newSet.add(newResponse);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(SPTester.KEY_RESPONSE, newSet);
        return editor.commit();
    }

    public boolean addCustomResponseSet(Set<String> newResponseSet) {
        Set<String> set = preferences.getStringSet(SPTester.KEY_RESPONSE, new HashSet<String>());
        Log.i("SPTester", "old data: " + set.toString());
        set.addAll(newResponseSet);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(SPTester.KEY_RESPONSE, set);
        return editor.commit();
    }

    public boolean removeCustomResponse(String oldResponse) {
        Set<String> set = preferences.getStringSet(SPTester.KEY_RESPONSE, new HashSet<String>());
        Log.i("SPTester", "old data: " + set.toString());
        set.remove(oldResponse);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(SPTester.KEY_RESPONSE, set);
        return editor.commit();
    }
}
