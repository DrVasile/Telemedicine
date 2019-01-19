package com.example.vasile.telemedicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    public static final String NAME = "Name";
    public static final String DIS = "Disease";
    public static final String LOC = "Location";
    public static final String DESC = "Description";

    private Button requestButton;
    private String name;
    private String disease;
    private String location;
    private String description;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set the toolbar
        Toolbar registerToolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(registerToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        // initialize FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                }
            }
        };

        // initialize Request button
        requestButton = (Button) findViewById(R.id.request_button);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request(v);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void logout() {
        mAuth.signOut();
    }

    public void request(View view) {
        Intent intent = new Intent(this, ApprovedRequestActivity.class);
        // get references to the texts
        EditText nameEdit = (EditText) findViewById(R.id.name_edit);
        EditText diseaseEdit = (EditText) findViewById(R.id.disease_edit);
        EditText locationEdit = (EditText) findViewById(R.id.location_edit);
        EditText descriptionEdit = (EditText) findViewById(R.id.description_edit);

        // get the text
        name = nameEdit.getText().toString();
        disease = diseaseEdit.getText().toString();
        location = locationEdit.getText().toString();
        description = descriptionEdit.getText().toString();

        // put texts in intent
        intent.putExtra(NAME, name);
        intent.putExtra(DIS, disease);
        intent.putExtra(LOC, location);
        intent.putExtra(DESC, description);
        startActivity(intent);
    }
}
