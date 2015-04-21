package com.example.administrator.aday38_demo01_bluetooth;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.Set;

public class MainActivity extends ActionBarActivity {

    private BluetoothAdapter adapter;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayAdapter discoweryarrayAdapter;
    private AlertDialog alertDialog;

    @Override
    protected void onStart() {
        super.onStop();
        IntentFilter filter=new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        registerReceiver(blueToothReceiver,filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(blueToothReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView= (ListView) findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2);
        listView.setAdapter(arrayAdapter);

        adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter!=null){
            if (adapter.isEnabled()){
                //直接打开蓝牙
                //adapter.enable();
                //关闭蓝牙
                //adapter.disable();
                //通过设置打开
                Intent i=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(i,0);
            }else {
                //获得已经绑定的设备
                getboundDevices();
            }
        }
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView listView= (ListView) findViewById(R.id.listView);
                discoweryarrayAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1);
                //搜索设备
                adapter.startDiscovery();
                alertDialog = new AlertDialog.Builder(MainActivity.this).setTitle("搜索设备...")
                         .setAdapter(discoweryarrayAdapter,new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {

                             }
                         })
                         .setNegativeButton("取消",new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                 //取消搜索
                                 adapter.cancelDiscovery();
                                 dialog.cancel();
                             }
                         })
                         .setCancelable(false)
                         .show();
            }
        });
    }

    //注册广播
    private BroadcastReceiver blueToothReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
           String action= intent.getAction();
            switch (action){
                case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:
                    alertDialog.cancel();
                    break;
                case BluetoothDevice.ACTION_FOUND:
                    BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    discoweryarrayAdapter.add(device.getName()+"\n"+device.getAddress());

                    break;
            }
        }
    };

    //获得已经绑定的设备
    public void getboundDevices(){
        Set<BluetoothDevice> bluetoothDevices=
                adapter.getBondedDevices();
        for (BluetoothDevice d:bluetoothDevices){
            arrayAdapter.add(d.getName()+"\n"+d.getAddress());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==0&&resultCode==RESULT_OK){
            if (adapter.isEnabled()){
                //获得已经绑定的设备
                getboundDevices();
            }
        }
    }
}
