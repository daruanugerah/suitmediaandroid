package com.example.daru.tessuitmedia;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainMenuActivity extends AppCompatActivity {
    private Button eventbutton;
    private Button guestbutton;
    private TextView textnama;
    private static final int REQUEST_CODE_EVENT = 1;
    private static final int REQUEST_CODE_GUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        eventbutton = (Button) findViewById(R.id.eventbutton);
        guestbutton = (Button) findViewById(R.id.guestbutton);
        textnama = (TextView) findViewById(R.id.nameview);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("nama");
        textnama.setText(name);

        // Cek nama apakah palindrom atau bukan
        if (isNamaPalindrom(name)) {
            Toast.makeText(getBaseContext(), "Nama Anda berbentuk palindrom", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getBaseContext(),"Nama Anda bukan berbentuk palindrom",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNamaPalindrom (String nama) {
        int indexDepan = 0;
        int indexBelakang = nama.length() - 1;
        boolean palindrom = true;

        do {
            if (nama.charAt(indexDepan) != nama.charAt(indexBelakang)) {
                palindrom = false;
            }
            indexDepan++;
            indexBelakang--;
        } while (indexDepan <= indexBelakang);

        return palindrom;
    }

    private boolean isMonthPrime(int month) {
        boolean Prime;
        switch (month) {
            case 2 :
            case 3 :
            case 5 :
            case 7 :
            case 11 :
                Prime = true; break;
            default :
                Prime = false; break;
        }
        return Prime;
    }

    public void onEventButtonClick(View view) {
        Intent intent = new Intent(this, EventActivity.class);
        startActivityForResult(intent, REQUEST_CODE_EVENT);
    }

    public void onGuestButtonClick(View view) {
        Intent intent = new Intent(this, GuestActivity.class);
        startActivityForResult(intent, REQUEST_CODE_GUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_EVENT) {                                            // Event activity, request code 1
                if (data.hasExtra("nama event")) {
                    String namaEvent = data.getExtras().getString("nama event");
                    eventbutton.setText(namaEvent);
                }
            } else if (requestCode == REQUEST_CODE_GUEST) {                                     // Guest Activity, request code 2
                if (data.hasExtra("nama guest")) {
                    String namaGuest = data.getExtras().getString("nama guest");
                    guestbutton.setText(namaGuest);
                    if (data.hasExtra("birthday guest")) {
                        String birthdateGuest = data.getExtras().getString("birthday guest");
                        String[] parseBirthdateGuest = birthdateGuest.split("[-]");
                        int dayBirthdateGuest = Integer.parseInt(parseBirthdateGuest[2]);         // Dapat hari dari birthday guest
                        int monthBirthdateGuest = Integer.parseInt(parseBirthdateGuest[1]);       // Dapat bulan dari birthday guest

                        String kategoriDay;
                        if ((dayBirthdateGuest % 2 == 0) && (dayBirthdateGuest % 3 == 0)) {
                            kategoriDay = "iOS";
                        } else {
                            if (dayBirthdateGuest % 2 == 0) {
                                kategoriDay = "blackberry";
                            } else if (dayBirthdateGuest % 3 == 0) {
                                kategoriDay = "android";
                            } else {
                                kategoriDay = "feature phone";
                            }
                        }

                        String kategoriMonth;
                        if (isMonthPrime(monthBirthdateGuest)) {
                            kategoriMonth = "prima";
                        } else {
                            kategoriMonth = "bukan prima";
                        }

                        String toastMessage = kategoriDay + " dan " + kategoriMonth;
                        Toast.makeText(getBaseContext(), toastMessage, Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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
