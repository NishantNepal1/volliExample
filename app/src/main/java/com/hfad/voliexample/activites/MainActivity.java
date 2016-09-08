package com.hfad.voliexample.activites;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hfad.voliexample.R;
import com.hfad.voliexample.adapter.InfoAdapter;
import com.hfad.voliexample.modal.Contact;
import com.hfad.voliexample.modal.ContactWrapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    Button webButton;
    String url = "http://api.androidhive.info/contacts/";
    ListView listView;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lvContact);
        webButton = (Button) findViewById(R.id.webButton);
        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkNetworkAvailability(MainActivity.this)) {
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Connecting...");
                    progressDialog.show();
                    connnectWebService();
                } else {
                    ArrayList<Contact> contacts = myApplication.getDbDatabaseManager().fetchContact();
                    InfoAdapter infoAdapter = new InfoAdapter(MainActivity.this, R.layout.listdata, contacts);
                    listView.setAdapter(infoAdapter);
                }
            }
        });
    }

    public void connnectWebService() {
        JSONObject param = new JSONObject();
        try {
            param.put("name", "some name");
            param.put("password", "test");
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, param, onSuccess, onError);
            Volley.newRequestQueue(this).add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    Response.Listener<JSONObject> onSuccess = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            progressDialog.dismiss();
            Log.e("Response", response + "");
            Gson gson = new GsonBuilder().create();
            ContactWrapper contacts = gson.fromJson(response.toString(), ContactWrapper.class);
            Log.e("Size", contacts.getContactsArrayList().size() + "");


            InfoAdapter infoAdapter = new InfoAdapter(MainActivity.this, R.layout.listdata, contacts.getContactsArrayList());
            listView.setAdapter(infoAdapter);
            save(contacts.getContactsArrayList());
        }
    };
    Response.ErrorListener onError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            Log.e("error", "error");
        }


    };

    private void save(ArrayList<Contact> contactArrayList) {
        myApplication.getDbDatabaseManager().addContact(contactArrayList);
    }

    public static boolean checkNetworkAvailability(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && (networkInfo.isConnected())) {
            return true;
        }

        return false;

    }
}
