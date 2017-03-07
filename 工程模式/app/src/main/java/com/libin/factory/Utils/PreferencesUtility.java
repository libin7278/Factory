package com.libin.factory.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by doudou on 2017/3/7.
 */

public class PreferencesUtility {
    private static final String LAST_ERR_EXIT = "last_err_exit";

    private static PreferencesUtility sInstance;

    private static SharedPreferences mPreferences;

    private PreferencesUtility(final Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static final PreferencesUtility getInstance(final Context context) {
        if (sInstance == null) {
            sInstance = new PreferencesUtility(context.getApplicationContext());
        }
        return sInstance;
    }

    public long lastExit(){
        return mPreferences.getLong(LAST_ERR_EXIT, 0);
    }

    public void setExitTime(){
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putLong(LAST_ERR_EXIT, System.currentTimeMillis());
        editor.commit();
    }
}
