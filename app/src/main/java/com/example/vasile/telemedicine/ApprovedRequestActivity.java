package com.example.vasile.telemedicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ApprovedRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approved_request);

        Intent incoming = getIntent();
        String name = incoming.getStringExtra(HomeActivity.NAME);
        String disease = incoming.getStringExtra(HomeActivity.DIS);
        String location = incoming.getStringExtra(HomeActivity.LOC);
        String description = incoming.getStringExtra(HomeActivity.DESC);

        TextView nameText = (TextView) findViewById(R.id.name_text);
        TextView diseaseText = (TextView) findViewById(R.id.disease_text);
        TextView locationText = (TextView) findViewById(R.id.location_text);
        TextView descriptionText = (TextView) findViewById(R.id.description_text);

        nameText.setText(name);
        diseaseText.setText(disease);
        locationText.setText(location);
        descriptionText.setText(description);
    }

}
