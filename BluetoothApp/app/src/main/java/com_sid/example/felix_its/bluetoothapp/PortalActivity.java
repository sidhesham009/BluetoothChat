package com_sid.example.felix_its.bluetoothapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PortalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal);

    }
    protected void onStart() {
        super.onStart();
        Button chat = (Button) findViewById(R.id.btnchat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatIntent = new Intent(getApplicationContext(), Chat.class);
                startActivity(chatIntent);

                Toast.makeText(getApplicationContext(), "Click on Connect Device to start chat", Toast.LENGTH_LONG).show();
            }
        });
        final Button call = (Button) findViewById(R.id.btncall);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent= new Intent(getApplicationContext(),Call.class);
                startActivity(callIntent);
                Toast.makeText(getApplicationContext(), "Enjoy Calling", Toast.LENGTH_LONG).show();
            }
        });
        final Button share = (Button) findViewById(R.id.btnshare);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent= new Intent(getApplicationContext(),Share.class);
                startActivity(shareIntent);
                Toast.makeText(getApplicationContext(), "Share files", Toast.LENGTH_LONG).show();
            }
        });
    }
}
