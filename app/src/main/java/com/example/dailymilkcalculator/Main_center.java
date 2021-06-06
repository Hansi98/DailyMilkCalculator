package com.example.dailymilkcalculator;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main_center extends AppCompatActivity {

    Map<Integer,String> data = new HashMap<>();
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_center);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        lv = (ListView) findViewById(R.id.main_list);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(Main_center.this, Transaction_Main.class);
                startActivity(intent);
            }
        });

    }

         @Override
        protected void onResume () {
            super.onResume();
            File folder = new File(getFilesDir() + File.separator + "MilkList");
            if (folder.exists()) {

                File[] files = folder.listFiles();
                List<String> list = new ArrayList<>();
                int index = 0;

                for (File file : files) {

                    data.put(index, file.getPath());
                    index++;

                    try {
                        FileInputStream fis = new FileInputStream(file);
                        byte[] chars = new byte[fis.available()];

                        fis.read(chars);
                        fis.close();

                        String note = new String(chars);
                        list.add(note);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Error Reading..", Toast.LENGTH_SHORT).show();
                    }

                }

                final ListView lv = findViewById(R.id.main_list);

                int layout = android.R.layout.simple_list_item_1;

                ArrayAdapter adapter = new ArrayAdapter(this, layout, list);
                lv.setAdapter(adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String filename = data.get(position);
                        Object o = lv.getItemAtPosition(position);
                        String note = (String) o;

                        Intent intent = new Intent(Main_center.this, Transaction_Main.class);
                        startActivity(intent);
                    }
                });

            } else {
                Toast.makeText(this, "App has some serious issue.", Toast.LENGTH_SHORT).show();
            }

        }

    }

