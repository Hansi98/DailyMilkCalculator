package com.example.dailymilkcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Sync_settings extends AppCompatActivity {

    DatabaseHelper myDb;
    Button btn_InitData,btnSync;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_settings);

        myDb = new DatabaseHelper(this);
        btn_InitData=(Button) findViewById(R.id.getInit);
        btnSync = (Button) findViewById(R.id.btnSync);



        btn_InitData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Sync_settings.this , initData.class);
                startActivity(intent);
            }
        });

    }




    }
