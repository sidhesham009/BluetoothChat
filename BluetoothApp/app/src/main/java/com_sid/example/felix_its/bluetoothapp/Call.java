package com_sid.example.felix_its.bluetoothapp;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Set;


public class Call extends AppCompatActivity {
    private Button scan, pairedDevice;
    private ListView listView;
    private BluetoothAdapter bluetoothAdapter;
    private static final int REQUEST_DISCOVERABLE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

    }

    @Override
    protected void onStart() {
        super.onStart();

        scan=(Button)findViewById(R.id.scanDev);
        pairedDevice= (Button)findViewById(R.id.paired);
        listView= (ListView)findViewById(R.id.list);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bluetoothAdapter.isDiscovering()){
                    Intent intent= new Intent(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
                    startActivityForResult(intent, REQUEST_DISCOVERABLE);
                }
            }
        });

        pairedDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

                ArrayList<String> devices= new ArrayList<String>();

                for (BluetoothDevice bt : pairedDevices){
                    devices.add(bt.getName());
                }
                ArrayAdapter arrayAdapter= new ArrayAdapter(Call.this, android.R.layout.simple_list_item_1, devices);

                listView.setAdapter(arrayAdapter);
            }
        });
    }
}
