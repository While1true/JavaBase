package com.common.Util;

import android.util.Log;

import com.common.BuildConfig;

/**
 * Created by 不听话的好孩子 on 2018/4/8.
 */

public class LogUtil {
    public static void Log(String message) {
        String tag = BuildConfig.APPLICATION_ID;
        Log(tag, message);
    }

    public static void Log(String tag, String message) {
        if (BuildConfig.showlog) {
            Log.i(tag, "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            Log.i(tag, "┃ ");
            Log.i(tag, "┃ message: " + message);
            Log.i(tag, "┃ ");
            Log.i(tag, "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        }
    }
}
