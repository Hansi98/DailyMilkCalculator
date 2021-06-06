package com.example.dailymilkcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    EditText et_username, et_password;
    Button btn_register;
    String uname, pwd,type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //databaseHelper = new DatabaseHelper(this);
        et_username = (EditText)findViewById(R.id.et_username);
        et_password = (EditText)findViewById(R.id.et_password);
        btn_register = (Button)findViewById(R.id.btn_register);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    public void register(){
        DatabaseHelper db = new DatabaseHelper(this);
        uname = et_username.getText().toString();
        pwd = et_password.getText().toString();

        long x = db.register(uname,pwd,type);
        if(x > 0)
            Toast.makeText(getApplicationContext(),"Register success" , Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"Register not success" , Toast.LENGTH_SHORT).show();


    }
}
