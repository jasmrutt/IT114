package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.Scanner;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddWatch extends AppCompatActivity {
    @Inject
    WatchList watchList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_watch);
    }

    public void showSameBrand(View view) {
        TextView tv;
        EditText et1;
        String infilename;
        try {

            tv = (TextView) findViewById(R.id.text_main);

            // get the file name from the EditText

            et1 = (EditText) findViewById(R.id.edit_add);

            infilename = et1.getText().toString();


            //gets text file from assets folder
            AssetManager assetManager = getAssets();

            //creating scanner to scan asset file
            Scanner fsc = new Scanner(assetManager.open(infilename));

            //create new watch object
            Watch newW = new Watch();

            //add new watch to watchlist
            watchList.add(newW);

            //setting variables
            while(fsc.hasNext()){
                newW.setBrand(fsc.nextLine());
                newW.setSerialN(fsc.nextLine());
                newW.setMovement(fsc.nextLine());

                //after scanning ints, have to scan next Line
                newW.setWaterR(fsc.nextInt());
                fsc.nextLine();

                newW.setYear(fsc.nextInt());
                fsc.nextLine();

                newW.setPrice(fsc.nextDouble());
                fsc.nextLine();

                newW.setImage(fsc.nextLine());

                System.out.println("added successfully");
            }
            hideKeyboard();
            //pop up message notifying the user that the List did not succeed creation
            Snackbar.make(findViewById(R.id.myCoordinatorLayout),
                   "Successfully Added New Watch",
                    Snackbar.LENGTH_SHORT).show();

        }
        catch(IOException a){
            //hides keyboard after pressing button so you can see the snackbar
            hideKeyboard();

            //pop up message notifying the user that the List did not succeed creation
            Snackbar.make(findViewById(R.id.myCoordinatorLayout),
                    "Failed to Add New Watch",
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