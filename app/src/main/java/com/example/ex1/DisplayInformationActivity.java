package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DisplayInformationActivity extends AppCompatActivity {

    TextView name, destination, description, dot, requireRisk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_information);

        name = findViewById(R.id.nameOut);
        destination = findViewById(R.id.destinationOut);
        description = findViewById(R.id.description_out);
        dot = findViewById(R.id.dotOut);
        requireRisk = findViewById(R.id.riskOut);

        Intent receiveData = getIntent();
        name.setText("Name: "+ receiveData.getStringExtra("getName"));
        destination.setText("Destination: "+ receiveData.getStringExtra("getDestination"));
        description.setText("Description: "+ receiveData.getStringExtra("getDescription"));
        dot.setText("Day of the trip: "+ receiveData.getStringExtra("getDot"));
        requireRisk.setText("Risk Assignment: "+ receiveData.getStringExtra("getRequireRisk"));

        Button close_Button = (Button) findViewById(R.id.close_btn);
        close_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DisplayInformationActivity.this, MainActivity.class));
            }
        });
    }
}