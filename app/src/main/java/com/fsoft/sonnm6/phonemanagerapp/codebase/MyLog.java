package com.fsoft.sonnm6.phonemanagerapp.codebase;


import android.util.Log;

/**
 * Created by SonNM6 on 2/23/2017.
 */

public class MyLog {
    public static boolean sIsStateDebug = true;

    public enum eStatusLog {
        ERROR, DEBUG, ASSET, VERBOSE, WARN, INFO
    }

    public static void error(String tag, Object value) {
        setLog(eStatusLog.ERROR, tag, value);
    }

    public static void debug(String tag, Object value) {
        setLog(eStatusLog.DEBUG, tag, value);
    }

    public static void asset(String tag, Object value) {
        setLog(eStatusLog.ASSET, tag, value);
    }

    public static void verbose(String tag, Object value) {
        setLog(eStatusLog.VERBOSE, tag, value);
    }

    public static void warn(String tag, Object value) {
        setLog(eStatusLog.WARN, tag, value);
    }

    public static void info(String tag, Object value) {
        setLog(eStatusLog.INFO, tag, value);
    }

    private static void setLog(eStatusLog log, String tag, Object value) {
        switch (log) {

            case ERROR:

                Log.e(tag, " : " + value.toString());
                break;
            case DEBUG:
                if (sIsStateDebug) {
                    Log.d(tag, " : " + value.toString());
                }

                break;
            case VERBOSE:
                if (sIsStateDebug) {
                    Log.v(tag, " : " + value.toString());
                }

                break;
            case WARN:
                Log.w(tag, " : " + value.toString());
                break;
            case INFO:
                if (sIsStateDebug) {
                    Log.i(tag, " : " + value.toString());
                }

                break;
        }
    }
}
