package com.hfad.voliexample.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hfad.voliexample.R;
import com.hfad.voliexample.modal.Contact;
import com.hfad.voliexample.modal.Phone;

public class FormActivity extends BaseActivity {
    EditText name;
    EditText address;
    EditText contact;
    EditText mobile;
    EditText email;
    EditText home;
    EditText office;
    EditText gender;
    Button btnSave;
    Contact info;
    boolean isFromUpdate = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        init();
        if (getIntent() != null) {
            isFromUpdate = true;
            info = (Contact) getIntent().getExtras().getSerializable("info");
            btnSave.setText("update");
            name.setText(info.getName());
            address.setText(info.getAddress());
            contact.setText(info.getId());
            gender.setText(info.getGender());
            Phone phone = info.getPhone();
            mobile.setText(phone.getMobile());
            email.setText(info.getEmail());
            home.setText(phone.getHome());
            office.setText(phone.getOffice());
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFromUpdate) {
                    myApplication.getDbDatabaseManager().updateContact(info.getId(), name.getText().toString(), gender.getText().toString(),

                            address.getText().toString(), email.getText().toString(), mobile.getText().toString(), home.getText().toString(), office.getText().toString());


                } else {

                    myApplication.getDbDatabaseManager().addContact(createDataFromForm());
                }
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private Contact updateDataBase() {
        Contact info = new Contact();
        info.setAddress(address.getText().toString());
        info.setGender(gender.getText().toString());
        info.setName(name.getText().toString());
        info.setEmail(email.getText().toString());

        Phone phone =new Phone();
        phone.setMobile(mobile.getText().toString());
        phone.setHome(home.getText().toString());
        phone.setOffice(office.getText().toString());
        info.setPhone(phone);
        return info;
    }

    private Contact createDataFromForm() {
        Contact info = new Contact();
        info.setAddress(address.getText().toString());
        info.setId(contact.getText().toString());
        info.setGender(gender.getText().toString());
        info.setName(name.getText().toString());
        info.setEmail(email.getText().toString());
        Phone phone =new Phone();
        phone.setMobile(mobile.getText().toString());
        phone.setHome(home.getText().toString());
        phone.setOffice(office.getText().toString());
        info.setPhone(phone);
        return info;
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void init() {
        name = (EditText) findViewById(R.id.etName);
        address = (EditText) findViewById(R.id.etAddress);
        contact = (EditText) findViewById(R.id.contactId);
        gender = (EditText) findViewById(R.id.etGender);
        email = (EditText) findViewById(R.id.etEmail);
        mobile = (EditText) findViewById(R.id.etMobile);
        home = (EditText) findViewById(R.id.etHome);
        office = (EditText) findViewById(R.id.etOffice);
        btnSave = (Button) findViewById(R.id.submit);
    }
}
