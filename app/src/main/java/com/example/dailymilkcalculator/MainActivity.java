package com.example.dailymilkcalculator;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Button btn_lregister, btn_llogin;
    EditText uname, password;
    RadioButton radioButton, radioMain, radioCollection;
    String username,passwords;



    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        databaseHelper = new DatabaseHelper(this);

        uname = (EditText) findViewById(R.id.et_lusername);
        password = (EditText) findViewById(R.id.et_lpassword);
        radioMain = (RadioButton) findViewById(R.id.radioMain);
        radioCollection = (RadioButton) findViewById(R.id.radioCollection);

        btn_lregister = (Button) findViewById(R.id.register);
        btn_llogin = (Button) findViewById(R.id.login);


        btn_lregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }

        });

        btn_llogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();

            }
        });

    }


    public void login(){

        Cursor c = databaseHelper.readUser();
        username = uname.getText().toString();
        passwords= password.getText().toString();

        String typee = databaseHelper.CheckUserAndGetType(username,passwords);

        if(typee.equals("MainCenter")) {
            Intent i = new Intent(this, Main_center.class);
            i.putExtra("uname",username);
            startActivity(i);
        }
        else if(typee.equals("CollectionCenter")){
            Intent in = new Intent(this,Collection_List.class);
            in.putExtra("uname",username);
            startActivity(in);
        }
        else {
            Toast.makeText(getApplicationContext(), "Invalid username or password!", Toast.LENGTH_SHORT).show();
            uname.setText(null);
            password.setText(null);
        }

    }


    public void getSelectedRadio(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioCollection:
                if (checked) {
                    Intent intent = new Intent(MainActivity.this, Collection_List.class);
                    startActivity(intent);
                }
                break;
            case R.id.radioMain:
                if (checked) {
                    Intent intent = new Intent(MainActivity.this, Main_center.class);
                    startActivity(intent);
                }
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
