package com.android.eddi.testapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textViewBlue, textViewWiFi;
    Switch aSwitchWiFi;
    WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewWiFi = findViewById(R.id.textViewWiFi);
        aSwitchWiFi = findViewById(R.id.switchWiFi);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (wifiManager.isWifiEnabled() == true){
            aSwitchWiFi.setChecked(true);
            textViewWiFi.setText("Wi-Fi ON");
        }

        aSwitchWiFi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    textViewWiFi.setText("Wi-Fi ON");
                    wifiManager.setWifiEnabled(true);
                }else {
                    textViewWiFi.setText("Wi-Fi OFF");
                    wifiManager.setWifiEnabled(false);
                }
            }
        });
    }
}