package com.gjh.learn.android.coolweather.util;

import android.content.Context;
import android.util.Log;

public class LogUtil {
    private static final Context CONTEXT = MyApplication.getContext();
    public static final String TAG = "Learn";
    public static final int TRADE = 1;
    public static final int DEBUG = 2;
    public static final int WARN = 3;
    public static final int INFO = 4;
    public static final int ERROR = 5;
    private static final int level = 2;

    public static void debug(String msg, Object... format) {
        if (level <= DEBUG) {
            Log.d(TAG, String.format(msg, format));
        }
    }

    public static void warn(String msg, Object... format) {
        if (level <= WARN) {
            Log.w(TAG, String.format(msg, format));
        }
    }

    public static void info(String msg, Object... format) {
        if (level <= INFO) {
            Log.i(TAG, String.format(msg, format));
        }
    }

    public static void error(String msg, Object... format) {
        if (level <= ERROR) {
            Log.e(TAG, String.format(msg, format));
        }
    }

}
