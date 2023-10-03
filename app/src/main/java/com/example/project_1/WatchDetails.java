package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import com.facebook.drawee.view.SimpleDraweeView;


@AndroidEntryPoint
public class WatchDetails extends AppCompatActivity {
    @Inject
    WatchList watchList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_details);
    }
    public void showWatch(View view)throws IOException{
        TextView tv;
        EditText et2;
        String serialN;

        // creating an int to
        // see if a watch is found
        // if it is trueW will be 1
        int trueW = 0;
        et2 = (EditText) findViewById(R.id.edit_details);
        serialN = et2.getText().toString();

        tv = (TextView) findViewById(R.id.text_details);
        tv.setText("");
        for (int watchd = 0; watchd < watchList.size(); watchd++) {
            if (serialN.equals(watchList.get(watchd).getSerialN())) {

                // displays brand
                tv.append("Brand: " + watchList.get(watchd).getBrand() + "\n");

                // displays movement
                tv.append("Movement: " + watchList.get(watchd).getMovement() + "\n");

                // displays water resistance in meters
                tv.append("Water resistance depth: " + watchList.get(watchd).getWaterR() + " meters \n");

                // displays year
                tv.append("Year: " + watchList.get(watchd).getYear() + "\n");

                // displays price
                tv.append("Price: $" + watchList.get(watchd).getPrice() + "\n");

                // displays age
                if(watchList.get(watchd).getAge() == 0){

                    // if age is 0 then new is displayed
                    tv.append("Age: New \n");
                }
                else{
                   // if age is not 0, age is displayed as a number
                    tv.append("Age: " + watchList.get(watchd).getAge() + " years old\n");
                }
                // displays if dive watch
                if(watchList.get(watchd).isDiveWatch()){
                    tv.append("Watch is a dive watch");
                }

                // displays image of watch

                draw(watchList.get(watchd).getImage());

                trueW++;

            }

            // if trueW is 1
            // indicates the watch was found
            // and snackbar will show success message
            if(trueW == 1){
                //calling method to hide keyboard after pressing button so you can see the snackbar
                //as well as other options like back button
                hideKeyboard();

                Snackbar.make(findViewById(R.id.myCoordinatorLayout), "Serial Number Exists. Watch Successfully Displayed.", Snackbar.LENGTH_SHORT).show();

            }
            else{
                hideKeyboard();

                //pop up message notifying the user that the List did not succeed creation
                Snackbar.make(findViewById(R.id.myCoordinatorLayout), "Serial Number Does Not Exist. Failed to Show Watch Details.", Snackbar.LENGTH_SHORT).show();
            }

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


    public void draw(String urlName) {


        Uri uri = Uri.parse(String.valueOf(urlName));
        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.image_area);
        draweeView.setImageURI(uri);


    }

}