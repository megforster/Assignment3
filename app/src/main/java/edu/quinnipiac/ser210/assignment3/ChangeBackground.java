package edu.quinnipiac.ser210.assignment3;

/*
Authors: Alexandra Martin, Megan Forster
Professor Ruby
SER 210 Android Development
Due: 27 February 2020

This is the ChangeBackground Activity class which holds method to help change background color the app
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class ChangeBackground extends AppCompatActivity {


    View changeBackgroundActivity;
    Intent intentback = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_background);
        changeBackgroundActivity = this.getWindow().getDecorView();

    }
    //Methods to change the background color with buttons corresponding to the color
    public void setLightBlue(View view){
        changeBackgroundActivity.setBackgroundResource(R.color.lightBlue);
        intentback.putExtra("MESSAGE", "blue");
        setResult(2, intentback);
        finish();

    }
    public void setLightOrange(View view){
        changeBackgroundActivity.setBackgroundResource(R.color.lightOrange);
        intentback.putExtra("MESSAGE", "orange");
        setResult(2, intentback);
        finish();
    }

    public void setLightPurple(View view){
        changeBackgroundActivity.setBackgroundResource(R.color.lightPurple);
        intentback.putExtra("MESSAGE", "purple");
        setResult(2, intentback);
        finish();
    }
}

