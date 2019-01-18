package com.example.vasile.telemedicine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button signupButton;
    private Button loginButton;
    private Button urgentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.TelemedicineTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initiate buttons
        signupButton = (Button) findViewById(R.id.signup_button);
        loginButton = (Button) findViewById(R.id.login_button);
        urgentButton = (Button) findViewById(R.id.urgent_button);

        // define the click listeners
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup(v);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });

        urgentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urgent(v);
            }
        });
    }

    public void signup(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void urgent(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
