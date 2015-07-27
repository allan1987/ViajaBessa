package br.com.mnidersoft.viajabessa;


import android.app.Application;

import com.activeandroid.ActiveAndroid;

public class ViajabessaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
