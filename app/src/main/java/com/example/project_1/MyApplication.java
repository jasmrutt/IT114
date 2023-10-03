package com.example.project_1;
import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import dagger.hilt.android.HiltAndroidApp;

import android.app.Application;
import dagger.hilt.android.HiltAndroidApp;
import com.facebook.drawee.backends.pipeline.Fresco;

@HiltAndroidApp
public class MyApplication extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}