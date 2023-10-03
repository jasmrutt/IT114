package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import java.io.IOException;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

///////////////////////////////////////////////////////////////
//
//  This app demonstrates the specification and use of
//  an app's (main) app bar (also called the "action bar").
//  The app bar entries for the Main Activity are defined
//  in the XML file menu_main.xml (in the directory "menu").
//  In that file, each entry is associated with the method
//  that handles the action below.  For example, entry
//  "Option 1" invokes the "onOption1" method in this class.
//
//  At the moment, each option simply puts a message in the
//  Main Activity's TextView box.  In a "real" app, the method
//  would do more interesting processing, perhaps invoking a
//  new activity of its own.
//
//  Author: M. Halper
//
///////////////////////////////////////////////////////////////


//https://web.njit.edu/~halper/it114/awd.txt
//https://web.njit.edu/~halper/it114/wn.txt
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    @Inject
    WatchList watchList;
    // make a reference to the "main" TextView
    // available to all activity methods



    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // set the reference to the "main" TextView object so
        // we do not have to retrieve it in every method below

        tv = (TextView) findViewById(R.id.text_main);




    } // end onCreate


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    }

    // Note: Can launch separate activities for each option, if desired.
    // Here, we're just displaying a simple message for the user.

    public void onOption1(MenuItem i)
    {
        tv.setText("Display List" + "\n");
        int place;

        //goes through all watches in watchLIst
        for(place = 0; place < watchList.size(); place++){
            //gets watch from watchList
            //gets brand, serial number, movement, water resistance, and price
            //from watces in watchList
            tv.append(watchList.get(place).getBrand() + ", ");
            tv.append(watchList.get(place).getMovement() + ", ");
            tv.append(watchList.get(place).getSerialN() + ", ");
            tv.append(watchList.get(place).getWaterR() + " meters, ");
            tv.append("$" + watchList.get(place).getPrice() + "\n");



        }
    }

    public void onOption2 (MenuItem i) throws IOException
    {
        //create edit text so user can enter url

        tv.setText("Load list from web files ");
        startActivity(new Intent(this, loadlist.class));


    }

    public void onOption3(MenuItem i)
    {
        tv.setText("Add a new watch");
        startActivity(new Intent(this, AddWatch.class));
    }

    public void onOption4(MenuItem i)
    {
        tv.setText("Show watch details");
        startActivity(new Intent(this, WatchDetails.class));

    }

    public void onOption5(MenuItem i)
    {
        tv.setText("Remove a watch");
        startActivity(new Intent(this, RemoveActivity.class));

    }

    public void onOption6(MenuItem i)
    {
        tv.setText("Show average price of all dive watches ");
        int spot = 0; // keeps track of position in watchlist
        double avg = 0;
        int totalDive = 0; // keeps track of all watches that have a water resistance of 200+ meters
        while(spot < watchList.size()){
            if(watchList.get(spot).isDiveWatch() == true){
                avg += watchList.get(spot).getPrice();
                totalDive++;
            }
            spot++;
        }
        avg = avg / totalDive;
        tv.append("\n Average price of dive watches: $" + String.format("%.2f", avg));
        // answer should be $2889.78
        // avg changes if watches are added or removed
    }

    public void onOption7(MenuItem i)
    {
        tv.setText("Show the number of automatic watches");
        int position;
        int totalAuto = 0;
        for(position = 0; position < watchList.size(); position++){
            if(watchList.get(position).getMovement().equals("automatic")){
                totalAuto++;
            }
        }
        tv.append("\nTotal number of automatic watches: " + totalAuto);

        }

    public void onOption8(MenuItem i)
    {
        tv.setText("Show all watches of a given brand" + "\n");
        startActivity(new Intent(this, SameBrand.class));


    }




} // end MainActivity
