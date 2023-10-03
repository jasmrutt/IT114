package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.io.IOException;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SameBrand extends AppCompatActivity {
    @Inject
    WatchList watchList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_same_brand);
    }

    public void showSameBrand(View view) throws IOException {
        TextView tv;
        EditText et5;
        String brand;
        et5 = (EditText) findViewById(R.id.edit_sameBrand);
        brand = et5.getText().toString();

        tv = (TextView) findViewById(R.id.text_sameWatch);
        tv.setText("");

        // keep track of # of watches with the
        // same brand as the user's input
        int same = 0;

        for(int place = 0; place < watchList.size(); place++){
            if(brand.equals(watchList.get(place).getBrand())){
                //gets brand, serial number, water resistance, and price

                tv.append(watchList.get(place).getBrand() + ", ");
                tv.append(watchList.get(place).getSerialN() + ", ");
                tv.append(watchList.get(place).getWaterR() + " meters, ");
                tv.append("$" + watchList.get(place).getPrice() + "\n");
                //calling method to hide keyboard after pressing button so you can see the snackbar
                //as well as other options like back button


                // if any watches were found I count them
                same++;

            }

        }

        //snack bar to show if the activity was successful or not

        // if any watches were found, show user that it was successful
        // and brand exists
        if(same > 0) {
            hideKeyboard();

            Snackbar.make(findViewById(R.id.myCoordinatorLayout), "Brand Exists. Successfully Found all Watches of the Same Brand", Snackbar.LENGTH_SHORT).show();

        }

        // if they put a brand name that does not exist
        // nothing will be displayed and they'll get an error message
        else{

            hideKeyboard();
            Snackbar.make(findViewById(R.id.myCoordinatorLayout), "Brand does not exist. Failed to Find Watches of the Same Brand", Snackbar.LENGTH_SHORT).show();
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