package com.example.daru.tessuitmedia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class GuestActivity extends Activity {
    private GridView listguest;
    private ArrayList<ResponGuest> namabirthdateguest;
    private Context context;
    private int positionSelected;
    private GuestList adapter;

    private class requestListGuest extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... endpoint_url) {
            // Buat objek http dan url
            URL endpoint = null;
            try {
                endpoint = new URL(endpoint_url[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection askEndpoint = null;
            try {
                assert endpoint != null;
                askEndpoint = (HttpURLConnection) endpoint.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Baca response dari request endpoint dalam bentuk buffer stream
            BufferedReader reader = null;
            try {
                assert askEndpoint != null;
                reader = new BufferedReader(new InputStreamReader(askEndpoint.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Ubah stream buffer dari response ke string
            // String response doInBackground
            String response = "";
            String inputLineReader;
            try {
                assert reader != null;
                while ((inputLineReader = reader.readLine()) != null) {
                    response += inputLineReader;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Tutup koneksi endpoint
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }

        private void getListGuestJSON (String response) {
            // Parse string response dalam format json, masukkan ke list vector listGuest
            JSONArray guest = null;
            try {
                guest = new JSONArray(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            assert guest != null;
            for (int i=0; i<guest.length(); i++) {
                JSONObject itemGuest = null;
                try {
                    itemGuest = guest.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String nama = "";
                try {
                    assert itemGuest != null;
                    nama = itemGuest.getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String birthday = "";
                try {
                    birthday = itemGuest.getString("birthday");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                namabirthdateguest.add(new ResponGuest(nama, birthday));
                //tempRequestResult.add(i, new dataStructureGuest(nama, birthday));
                //adapter.add(new dataStructureGuest(nama, birthday));
            }
        }

        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
            getListGuestJSON(result);

            // Bind list nameBirthdayGuest ke listview list of guest beserta setting click listener
            adapter = new GuestList(context, namabirthdateguest);
            listguest.setAdapter(adapter);

            //adapter.add(new dataStructureGuest("Joko", "2014-02-01"));

            listguest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    positionSelected = position;
                    finish();
                }
            });
            listguest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    positionSelected = position;
                    finish();
                }
            });

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        listguest = (GridView) findViewById(R.id.listguest);
        namabirthdateguest = new ArrayList<>();
        context = GuestActivity.this;

        requestListGuest requestList = new requestListGuest();
        requestList.execute("http://dry-sierra-6832.herokuapp.com/api/people");
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("nama guest", namabirthdateguest.get(positionSelected).getNama());
        intent.putExtra("birthday guest", namabirthdateguest.get(positionSelected).getBirthday());
        setResult(RESULT_OK, intent);
        // adapter.clear();
        super.finish();
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_guest, menu);
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
