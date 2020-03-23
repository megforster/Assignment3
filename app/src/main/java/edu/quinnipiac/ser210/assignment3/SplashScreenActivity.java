package edu.quinnipiac.ser210.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    //Alter this to have it move to
    public void onEnter(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}