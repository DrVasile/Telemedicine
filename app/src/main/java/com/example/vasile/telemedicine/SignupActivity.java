package com.example.vasile.telemedicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "abs";
    private EditText name;
    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;
    //private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Set the toolbar
        Toolbar registerToolbar = (Toolbar) findViewById(R.id.register_toolbar);
        setSupportActionBar(registerToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        FirebaseApp.initializeApp(this);

        //progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.email_edit);
        password = (EditText) findViewById(R.id.password_edit);

        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_text = email.getText().toString();
                String password_text = password.getText().toString();

                if (TextUtils.isEmpty(email_text)) {
                    Toast.makeText(getApplicationContext(), "Enter Email Id", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password_text)) {
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email_text, password_text)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Signup success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If signup fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignupActivity.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
