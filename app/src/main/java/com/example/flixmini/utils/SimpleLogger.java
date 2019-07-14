package com.example.flixmini.utils;

public class SimpleLogger implements Logger {

    @Override
    public void d(String tag, String message) {
        android.util.Log.d(tag, message);
    }

    @Override
    public void e(String tag, String message) {
        android.util.Log.e(tag, message);
    }

}
