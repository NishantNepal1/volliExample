package com.hfad.voliexample.activites;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.ActionMenuView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hfad.voliexample.R;
import com.hfad.voliexample.adapter.InfoAdapter;
import com.hfad.voliexample.modal.Contact;

import java.util.ArrayList;

public class DisplayActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    ListView lvList;
    Contact info;
    InfoAdapter infoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        init();
        ArrayList<Contact> infos=myApplication.getDbDatabaseManager().fetchContact();
        infoAdapter=new InfoAdapter(this,R.layout.activity_display,infos);
        lvList.setAdapter(infoAdapter);
        lvList.setOnClickListener((View.OnClickListener) this);
    }

    public void init() {
        lvList = (ListView) findViewById(R.id.lvList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuAdd) {
            // MainActivity.start(ContactInfo.this);
            Intent intent = new Intent(DisplayActivity.this, MainActivity.class);
            startActivityForResult(intent, 101);
        }
        return super.onOptionsItemSelected(item);
    }

    public void showPopup(View anchorView) {

        final View popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);

        final PopupWindow popupWindow = new PopupWindow(popupView,
                ActionMenuView.LayoutParams.MATCH_PARENT, ActionMenuView.LayoutParams.WRAP_CONTENT);
        final PopupWindow pw=popupWindow;
        // Example: If you have a TextView inside `popup_layout.xml`
        TextView tv1 = (TextView) popupView.findViewById(R.id.pop1);

        TextView tv2 = (TextView) popupView.findViewById(R.id.pop2);

        TextView tv3 = (TextView) popupView.findViewById(R.id.pop3);

        tv1.setText("update");
        tv2.setText("delete");
        tv3.setText("cancel");
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DisplayActivity.this,FormActivity.class);
                intent.putExtra("info", (Parcelable) info);
                startActivityForResult(intent,101);
                pw.dismiss();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myApplication.getDbDatabaseManager().deleteContact(info.getId());
                infoAdapter.remove(info);
                infoAdapter.notifyDataSetChanged();
                pw.dismiss();

            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();

            }
        });

        // If the PopupWindow should be focusable
        popupWindow.setFocusable(true);

        int location[] = new int[2];

        // Get the View's(the one that was clicked in the Fragment) location
        anchorView.getLocationOnScreen(location);

        // Using location, the PopupWindow will be displayed right under anchorView
        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY,
                location[0], location[1] + anchorView.getHeight());

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showPopup(view);
        info= (Contact) parent.getAdapter().getItem(position);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode && requestCode == 101) {
            ArrayList<Contact> list = myApplication.getDbDatabaseManager().fetchContact();
            infoAdapter.notifyData(list);

        }
    }


}
