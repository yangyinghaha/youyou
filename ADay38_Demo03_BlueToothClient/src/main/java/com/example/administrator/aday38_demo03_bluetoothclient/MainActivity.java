package com.example.administrator.aday38_demo03_bluetoothclient;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;


public class MainActivity extends ActionBarActivity {

    private BluetoothAdapter bluetoothAdapter;
    private boolean isExit = false;
    private BluetoothSocket s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        final Set<BluetoothDevice> boundDevices =
                bluetoothAdapter.getBondedDevices();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1
        );
        //将绑定的设备添加到适配器中
        for (BluetoothDevice d : boundDevices) {
            arrayAdapter.add(d.getName() + "\n" + d.getAddress());
        }

        new AlertDialog.Builder(this)
                .setTitle("已经绑定的设备")
                .setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BluetoothDevice d = (BluetoothDevice)
                                boundDevices.toArray()[which];

                        try {
                            s = d.
                                    createInsecureRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                            //与服务器连接
                            s.connect();

                            new SocketThread(s).start();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();



        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SendThread(s,"天王盖地虎").start();
            }
        });

    }

    //发送数据
    class SendThread extends Thread {
        private DataOutputStream dataOutputStream;
        private DataInputStream dataInputStream;
        private BluetoothSocket bs;
        private String data;

        SendThread(BluetoothSocket bs, String data) {
            this.data = data;
            this.bs = bs;
            try {
                dataInputStream = new DataInputStream(bs.getInputStream());
                dataOutputStream = new DataOutputStream(bs.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            super.run();
            try {
                dataOutputStream.writeUTF(data);
                dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    //接收数据
    class SocketThread extends Thread {

        private DataOutputStream dataOutputStream;
        private DataInputStream dataInputStream;
        BluetoothSocket bs;

        SocketThread(BluetoothSocket bs) {
            this.bs = bs;
            try {
                dataInputStream = new DataInputStream(bs.getInputStream());
                dataOutputStream = new DataOutputStream(bs.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            super.run();
            while (!isExit) {
                try {
                    //接收服务器发过来的数据
                    final String data = dataInputStream.readUTF();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,
                                    data, Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
