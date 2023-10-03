package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RemoveActivity extends AppCompatActivity {
    @Inject
    WatchList watchList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);
    }
    public void removeWatch(View view) throws IOException {
        EditText et2;
        String removeW;
        et2 = (EditText) findViewById(R.id.edit_remove);
        removeW = et2.getText().toString();

        for (int value = 0; value < watchList.size(); value++) {
            if (removeW.equals(watchList.get(value).getSerialN())) {
                watchList.remove(watchList.get(value));

                //calling method to hide keyboard after pressing button so you can see the snackbar
                //as well as other options like back button
                hideKeyboard();

                Snackbar.make(findViewById(R.id.myCoordinatorLayout), "Watch Successfully Removed", Snackbar.LENGTH_SHORT).show();
                break;
            }
            else{
                hideKeyboard();

                //pop up message notifying the user that the List did not succeed creation
                Snackbar.make(findViewById(R.id.myCoordinatorLayout), "Failed to Remove a Watch", Snackbar.LENGTH_SHORT).show();
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
}