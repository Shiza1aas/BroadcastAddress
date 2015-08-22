package com.example.shahjahan.broadcastaddress;

import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    TextView ip_address;
    TextView subnet_mask;
    TextView broadcast_address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ip_address = (TextView)findViewById(R.id.ip_address);
        subnet_mask = (TextView)findViewById(R.id.subnet_mask);
        broadcast_address = ( TextView)findViewById(R.id.broadcast_address);
        getIpAddress();


    }

    private void getIpAddress() {

        WifiManager wifiManager = (WifiManager)getSystemService(WIFI_SERVICE);

        DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();

        try {
            ip_address.setText(ip_address.getText().toString() + "is: " + InetAddress.getByAddress(getAddressByte(dhcpInfo.ipAddress)));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


        try {
            subnet_mask.setText(subnet_mask.getText().toString() + " is : " + InetAddress.getByAddress(getAddressByte(dhcpInfo.netmask)));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            broadcast_address.setText(broadcast_address.getText().toString() + " is : " + InetAddress.getByAddress(getAddressByte((dhcpInfo.ipAddress) | (~dhcpInfo.netmask))));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


    }

    private byte[] getAddressByte(int ipAddress) {

        byte[] addressBytes = { (byte)(0xff & ipAddress),
                (byte)(0xff & (ipAddress >> 8)),
                (byte)(0xff & (ipAddress >> 16)),
                (byte)(0xff & (ipAddress >> 24)) };
        return addressBytes;

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
