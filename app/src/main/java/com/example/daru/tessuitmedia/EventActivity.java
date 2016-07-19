package com.example.daru.tessuitmedia;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class EventActivity extends Activity {
    private ListView listevent;
    private ArrayList<ResponEvent> namatanggalevent;
    private int positionSelected = 0;
    private EventList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        listevent = (ListView) findViewById(R.id.listevent);

        namatanggalevent = new ArrayList<ResponEvent>();
        adapter = new EventList(this, namatanggalevent);
        listevent.setAdapter(adapter);

        listevent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positionSelected = position;
                finish();
            }
        });

        adapter.add(new ResponEvent("acara buka bersama", "25 Juni 2016"));
        adapter.add(new ResponEvent("acara liburan", "20 Juni 2016"));
        adapter.add(new ResponEvent("acara seminar", "18 Juli 2016"));
        adapter.add(new ResponEvent("acara ulang tahun", "6 September 2016"));
        adapter.add(new ResponEvent("acara musik", "29 Juli 2016"));
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("nama event", namatanggalevent.get(positionSelected).getNama());
        setResult(RESULT_OK, intent);
        adapter.clear();
        super.finish();
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
