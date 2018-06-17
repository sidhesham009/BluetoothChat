package com_sid.example.felix_its.bluetoothapp;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.view.View;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.UUID;

public class ThreadConnectBTdevice extends Thread{
    private UUID myUUID;
    private BreakIterator textStatus;
    private BluetoothSocket bluetoothSocket = null;
    private final BluetoothDevice bluetoothDevice;


    ThreadConnectBTdevice(BluetoothDevice device) {
        myUUID = myUUID;
        textStatus = textStatus;
        bluetoothDevice = device;

        try {
            bluetoothSocket = device.createRfcommSocketToServiceRecord(this.myUUID);
          //  this.textStatus.setText("bluetoothSocket: \n" + bluetoothSocket);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        boolean success = false;
        try {
            bluetoothSocket.connect();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();

            final String eMessage = e.getMessage();
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    textStatus.setText("something wrong bluetoothSocket.connect(): \n" + eMessage);
                }
            });

            try {
                bluetoothSocket.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        if(success){
            //connect successful
            final String msgconnected = "connect successful:\n"
                    + "BluetoothSocket: " + bluetoothSocket + "\n"
                    + "BluetoothDevice: " + bluetoothDevice;

            runOnUiThread(new Runnable(){

                public View inputPane;
                public View listViewPairedDevice;

                @Override
                public void run() {
                    textStatus.setText(msgconnected);

                    listViewPairedDevice.setVisibility(View.GONE);
                    inputPane.setVisibility(View.VISIBLE);
                }});

            startThreadConnected(bluetoothSocket);
        }else{
            //fail
        }
    }

    private void runOnUiThread(Runnable runnable) {

    }

    private void startThreadConnected(BluetoothSocket bluetoothSocket) {
        
    }

    public void cancel() {

       // Toast.makeText("close bluetoothSocket",Loader(), Toast.LENGTH_LONG).show();

        try {
            bluetoothSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
