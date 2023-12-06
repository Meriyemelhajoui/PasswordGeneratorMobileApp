package com.GI3GL.mypasswordgenerator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getStartedButton = findViewById(R.id.btn_get_started);
        getStartedButton.setOnClickListener(view ->  {
            // Redirecting to the HomePage
            Intent myintent=new Intent(MainActivity.this,HomePage.class);
            startActivity(myintent);

        });
    }
}

