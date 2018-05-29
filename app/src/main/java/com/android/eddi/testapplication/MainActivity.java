package com.android.eddi.testapplication;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Formatter;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textViewWiFi, textViewInfo;
    Switch aSwitchWiFi;
    WifiManager wifiManager;
    WifiInfo wifiInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewWiFi = findViewById(R.id.textViewWiFi);
        aSwitchWiFi = findViewById(R.id.switchWiFi);
        textViewInfo = findViewById(R.id.textViewInfo);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (wifiManager.isWifiEnabled()) {
            writeWiFiInformation();
            aSwitchWiFi.setChecked(true);
            textViewWiFi.setText("Wi-Fi ON");
        }

        aSwitchWiFi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textViewWiFi.setText("Wi-Fi ON");
                    textViewInfo.setText("Loading Wi-Fi Information");
                    wifiManager.setWifiEnabled(true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            writeWiFiInformation();
                        }
                    }, 6000);
                } else {
                    textViewWiFi.setText("Wi-Fi OFF");
                    textViewInfo.setText("No Wi-Fi Information");
                    wifiManager.setWifiEnabled(false);
                }
            }
        });
    }

    public void writeWiFiInformation() {
        wifiInfo = wifiManager.getConnectionInfo();
        int ip = wifiInfo.getIpAddress();
        @SuppressLint("HardwareIds") String macAddress = wifiInfo.getMacAddress();
        String bssid = wifiInfo.getBSSID();
        int rssi = wifiInfo.getRssi();
        int linkSpeed = wifiInfo.getLinkSpeed();
        String ssid = wifiInfo.getSSID();
        int networkId = wifiInfo.getNetworkId();
        String ipAddress = android.text.format.Formatter.formatIpAddress(ip);
        String info = "Wi-Fi Information: " +
                "\n" + "IpAddress: " + ipAddress +
                "\n" + "MAC Address: " + macAddress +
                "\n" + "BSSID: " + bssid +
                "\n" + "RSSI: " + rssi +
                "\n" + "Link Speed: " + linkSpeed +
                "\n" + "SSID: " + ssid +
                "\n" + "Network ID: " + networkId;
        textViewInfo.setText(info);
    }
}