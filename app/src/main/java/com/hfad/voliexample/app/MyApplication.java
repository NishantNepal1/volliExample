package com.hfad.voliexample.app;

import android.app.Application;

import com.hfad.voliexample.database.DataBaseManager;

/**
 * Created by user on 9/7/2016.
 */
public class MyApplication extends Application{
    private DataBaseManager dbDatabaseManager;
    @Override
    public void onCreate() {
        super.onCreate();
        dbDatabaseManager= DataBaseManager.newInstance(this);
    }
    public DataBaseManager getDbDatabaseManager()
    {
        return dbDatabaseManager;
    }



}
