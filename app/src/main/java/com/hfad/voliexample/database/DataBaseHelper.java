package com.hfad.voliexample.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 9/7/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final int dbversion = 2;
    public static final String db_name = "db_contact";
    public static final String TBL_CONTACT = "contact";
    public static final String CONTACT_ID = "contact_id";
    public static final String CONTACT_NAME = "contact_name";
    public static final String CONTACT_ADDRESS = "address";
    public static final String GENDER = "contact_gender";
    public static final String CONTACT_EMAIL = "contact_email";
    public static final String PHONE_MOBILE = "contact_mobile";
    public static final String PHONE_HOME = "contact_home";
    public static final String PHONE_OFFICE = "contact_office";
    public static final String CREATE_CONTACT_TABLE = "CREATE TABLE  "
            + TBL_CONTACT
            + "(id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CONTACT_ID + " String UNIQUE, "
            + CONTACT_NAME + " TEXT, "
            + CONTACT_ADDRESS + " TEXT, "
            + GENDER + " TEXT,"
            + CONTACT_EMAIL + " TEXT, "
            + PHONE_HOME + " TEXT, "
            + PHONE_MOBILE + " TEXT, "
            + PHONE_OFFICE + " TEXT )";


    public DataBaseHelper(Context context) {
        super(context, db_name, null, dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
