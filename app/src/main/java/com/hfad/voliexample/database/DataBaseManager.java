package com.hfad.voliexample.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.hfad.voliexample.modal.Contact;
import com.hfad.voliexample.modal.Phone;
import java.util.ArrayList;
public class DataBaseManager {
    private static DataBaseManager INSTANCE;
    SQLiteDatabase db;

    private DataBaseManager(Context mContext) {
        DataBaseHelper dbHelper = new DataBaseHelper(mContext);
        this.db = dbHelper.getWritableDatabase();
    }

    public static DataBaseManager newInstance(Context mContext) {
        if (INSTANCE == null) {
            INSTANCE = new DataBaseManager(mContext);
        }
        return INSTANCE;
    }


    public void addContact(ArrayList<Contact> contacts) {
        for (Contact contact : contacts) {

            if (!isContactExists(contact)) {
                addContact(contact);
            }
        }
    }

    private boolean isContactExists(Contact contact) {
        String query = "SELECT * From " + DataBaseHelper.TBL_CONTACT + " WHERE " + DataBaseHelper.CONTACT_ID + " = '" + contact.getId() + "'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount() > 0;

    }

    public void addContact(Contact contact) {
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.CONTACT_NAME, contact.getName());
        cv.put(DataBaseHelper.CONTACT_ID, contact.getId());
        cv.put(DataBaseHelper.CONTACT_EMAIL, contact.getEmail());
        cv.put(DataBaseHelper.CONTACT_ADDRESS, contact.getAddress());
        cv.put(DataBaseHelper.GENDER, contact.getGender());
        cv.put(DataBaseHelper.PHONE_HOME, contact.getPhone().getHome());
        cv.put(DataBaseHelper.PHONE_OFFICE, contact.getPhone().getOffice());
        cv.put(DataBaseHelper.PHONE_MOBILE, contact.getPhone().getMobile());
        db.insert(DataBaseHelper.TBL_CONTACT, null, cv);
    }

    public void deleteContact(String contactId) {
        int i = db.delete(DataBaseHelper.TBL_CONTACT, DataBaseHelper.CONTACT_ID + "='" + contactId + "='", null);
        Log.e("tag", i + "");
    }

    public void updateContact(String contactId, String updateName, String updateEmail, String updateAddress, String updateGender, String updateHome, String updateOffice, String updateMobile) {
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.CONTACT_NAME, updateName);
        cv.put(DataBaseHelper.CONTACT_EMAIL, updateEmail);
        cv.put(DataBaseHelper.CONTACT_ADDRESS, updateAddress);
        cv.put(DataBaseHelper.GENDER, updateGender);
        cv.put(DataBaseHelper.PHONE_HOME, updateHome);
        cv.put(DataBaseHelper.PHONE_OFFICE, updateOffice);
        cv.put(DataBaseHelper.PHONE_MOBILE, updateMobile);
        db.update(DataBaseHelper.TBL_CONTACT, cv, DataBaseHelper.CONTACT_ID + "='" + contactId + "='", null);
    }

    public ArrayList<Contact> fetchContact() {
        String query = "SELECT * FROM " + DataBaseHelper.TBL_CONTACT;
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Contact> infoArrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Contact info = new Contact();
            info.setName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.CONTACT_NAME)));
            info.setEmail(cursor.getString(cursor.getColumnIndex(DataBaseHelper.CONTACT_EMAIL)));
            info.setAddress(cursor.getString(cursor.getColumnIndex(DataBaseHelper.CONTACT_ADDRESS)));
            info.setGender(cursor.getString(cursor.getColumnIndex(DataBaseHelper.GENDER)));
            Phone phone = new Phone();
            phone.setHome(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PHONE_HOME)));
            phone.setOffice(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PHONE_OFFICE)));
            phone.setMobile(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PHONE_MOBILE)));
            info.setPhone(phone);
            infoArrayList.add(info);
        }
        return infoArrayList;
    }
}
