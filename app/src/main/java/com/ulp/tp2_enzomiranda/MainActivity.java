package com.ulp.tp2_enzomiranda;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnInciar, btnDetener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        solicitarPermiso();
        Intent intent = new Intent(this, verSmsService.class);

        btnInciar = findViewById(R.id.btnIniciar);
        btnInciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(intent);
                btnInciar.setEnabled(false);
                btnDetener.setEnabled(true);
            }
        });

        btnDetener = findViewById(R.id.btnDetener);
        btnDetener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(intent);
                btnInciar.setEnabled(true);
                btnDetener.setEnabled(false);
            }
        });


    }

    private void solicitarPermiso() {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_SMS},1000);
        }
    }




}