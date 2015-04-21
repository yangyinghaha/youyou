package com.example.administrator.aday38_demo02_bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;


public class MainActivity extends ActionBarActivity {

    private BluetoothAdapter bluetoothAdapter;
    private boolean isExit=true;
    //所有客户端线程
    private ArrayList<ClientThread> allClientThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allClientThread=new ArrayList<ClientThread>();
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter!=null&bluetoothAdapter.isEnabled()){
            //蓝牙可用
            startBlueToothService();
        }else {
            Intent i=new Intent(bluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(i,0);
        }

    }

    public void startBlueToothService(){
        try {
            BluetoothServerSocket serversocket=bluetoothAdapter.
                    listenUsingInsecureRfcommWithServiceRecord("com.example.buletooth",
                            UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            new ServerThread(serversocket).start();
            Toast.makeText(this,"服务器已连接",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ServerThread extends Thread{

        private BluetoothServerSocket bss;
        ServerThread(BluetoothServerSocket bss){
            this.bss=bss;
        }
        @Override
        public void run() {
            super.run();
            while (isExit){
                try {
                    //等待客户端连接,会阻塞线程
                    BluetoothSocket s=bss.accept();
                    //接受客户端消息
                    ClientThread ct=new ClientThread(s);
                    ct.start();
                    //添加到所有客户端线程集合中
                    allClientThread.add(ct);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                //关闭服务
                bss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //发送数据
    class sendThread extends Thread{
        private String data;
        private String name;

        sendThread(String data, String name) {
            this.data = data;
            this.name = name;
        }

        @Override
        public void run() {
            super.run();
            for (int i = 0; i <allClientThread.size() ; i++) {
                ClientThread ct=allClientThread.get(i);
                ct.sendData(name+"--->说:"+data);

            }
        }
    }

    //客户端接收消息
    class ClientThread extends Thread{
        private BluetoothSocket socket;
        private DataInputStream dataInputStream;
        private DataOutputStream dataOutputStream;
        private String name;

        ClientThread(BluetoothSocket socket){
            this.socket=socket;
            try {
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            super.run();
            //接收数据
            while (!isExit){
                try {
                    //阻塞线程，等待接收消息
                    String data=dataInputStream.readUTF();

                    new sendThread(data,name).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //发送数据
        public void sendData(String data){
            try {
                dataOutputStream.writeUTF(data);
                dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0&&requestCode==RESULT_OK){
            if (bluetoothAdapter.isEnabled()){

                startBlueToothService();
            }
        }

    }
}
