package edu.quinnipiac.ser210.assignment3;

/*
Authors: Alexandra Martin, Megan Forster
Professor Ruby
SER 210 Android Development
Due: 27 February 2020
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class ChangeBackground extends AppCompatActivity {

    //The view displaying the color buttons
    View changeBackgroundActivity;

    //Intent is used to change the background color of the main and help fragments
    Intent intentback = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_background);
        changeBackgroundActivity = this.getWindow().getDecorView();

    }

    //Change the current view to blue and then tell Main Activity to change the main and help fragments to blue
    public void setLightBlue(View view){
        changeBackgroundActivity.setBackgroundResource(R.color.lightBlue);
        intentback.putExtra("MESSAGE", "blue");
        setResult(2, intentback);
        finish();
    }

    //Change the current view to orange and then tell Main Activity to change the main and help fragments to orange
    public void setLightOrange(View view){
        changeBackgroundActivity.setBackgroundResource(R.color.lightOrange);
        intentback.putExtra("MESSAGE", "orange");
        setResult(2, intentback);
        finish();
    }

    //Change the current view to purple and then tell Main Activity to change the main and help fragments to purple
    public void setLightPurple(View view){
        changeBackgroundActivity.setBackgroundResource(R.color.lightPurple);
        intentback.putExtra("MESSAGE", "purple");
        setResult(2, intentback);
        finish();
    }
}

