package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;



@AndroidEntryPoint
public class loadlist extends AppCompatActivity {
    @Inject
    WatchList watchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadlist);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void createList(View view) throws IOException {
        //instantiating variables
        EditText et1;
        String urlName;
        et1 = (EditText) findViewById(R.id.edit_url);

        try{
        //get url from user
        urlName = et1.getText().toString();

        //get url and open files
        URL file_url = new URL(urlName);
        Scanner fsc = new Scanner(file_url.openStream());

        //creating an index value
        int pos = 0;


            //creating temp watch object

            //adding temp watch to watchList at position
            while(fsc.hasNext()){

                Watch tempW= new Watch();
                watchList.add(tempW);
                //setting variables
                tempW.setBrand(fsc.nextLine());
                tempW.setSerialN(fsc.nextLine());
                tempW.setMovement(fsc.nextLine());

                //after scanning ints, have to scan next Line
                tempW.setWaterR(fsc.nextInt());
                fsc.nextLine();

                tempW.setYear(fsc.nextInt());
                fsc.nextLine();

                tempW.setPrice(fsc.nextDouble());
                fsc.nextLine();

                tempW.setImage(fsc.nextLine());

                //last line of txt file is an empty line
                if(!fsc.hasNext()){
                    fsc.nextLine();
                };

                pos++;


            }
            //calling method to hide keyboard after pressing button so you can see the snackbar
            //as well as other options like back button
            hideKeyboard();

            //pop up message notifying the user that the List creation was successful
            Snackbar.make(findViewById(R.id.myCoordinatorLayout), "List Successfully Created", Snackbar.LENGTH_SHORT).show();

        }
        
        //catces exception wen URL is not foind
        //displays error message
        catch(IOException e){
            //hides keyboard after pressing button so you can see the snackbar
            hideKeyboard();

            //pop up message notifying the user that the List did not succeed creation
            Snackbar.make(findViewById(R.id.myCoordinatorLayout),
                    "Failed to Create List",
                    Snackbar.LENGTH_SHORT).show();
        }
    }

    //method to hide keyboard
    private void hideKeyboard()
    {
        // This method dismisses the soft keyboard.
        // Code derived from developer.android.com and
        // StackOverflow

        Context context = getCurrentFocus().getContext();

        InputMethodManager inputMethodManager =
                (InputMethodManager)
                        context.getSystemService(Activity.INPUT_METHOD_SERVICE);

        if (inputMethodManager != null)
            inputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

    } // end hideKeyboard






}

