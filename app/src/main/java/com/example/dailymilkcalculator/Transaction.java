package com.example.dailymilkcalculator;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import dto.CollectionDto;

public class Transaction extends AppCompatActivity implements AdapterView.OnItemClickListener {

    DatabaseHelper myDb;
    Button btnSave, btnCancel, btnUom,btnSession, btnItem, btnSupplier, btnReading, btnTest;
    EditText et_kg,et_uom,et_comment;
    Spinner spinner,spinner1,spinner2,spinner3,spinner4;
    int primaryId;

    private static final String[] time = {"AM", "PM"};
    private static final String[] item = {"Cow Milk", "Cheese","Curd", "Yogurt"};
    private static final String[] supplier = {"Supplier1", "Supplier2"};
    private static final String[] reading = {"Pass", "Fail"};
    private static final String[] test = {"Test1", "Test2"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        myDb = new DatabaseHelper(this);

        spinner = (Spinner) findViewById(R.id.spinnerSession);
        spinner1 = (Spinner) findViewById(R.id.spinnerItem);
        spinner2 = (Spinner) findViewById(R.id.spinnerSupplier);
        spinner3 = (Spinner) findViewById(R.id.spinnerReading);
        spinner4 = (Spinner) findViewById(R.id.spinnerTest);

        et_kg = (EditText) findViewById(R.id.et_kg);
        et_uom = (EditText) findViewById(R.id.et_uom);
        et_comment = (EditText) findViewById(R.id.et_comment);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnUom = (Button) findViewById(R.id.btnuom);
        btnSession = (Button) findViewById(R.id.btnsession);
        btnItem = (Button) findViewById(R.id.btnitem);
        btnSupplier = (Button) findViewById(R.id.btnsupplier);
        btnReading = (Button) findViewById(R.id.btnreading);
        btnTest = (Button) findViewById(R.id.btntest);
        btnCancel = (Button) findViewById(R.id.btn_cancel);


        ViewCollectionData();
        deleteData();
        addData();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, time);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        adapter.add("AM");
        adapter.add("PM");
        adapter.notifyDataSetChanged();

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, item);
        adapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        adapter1.add("Cow Milk");
        adapter1.add("Cheese");
        adapter1.add("Curd");
        adapter1.add("Yogurt");
        adapter1.notifyDataSetChanged();


        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, supplier);
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        adapter2.add("Supplier1");
        adapter2.add("Supplier2");
        adapter2.notifyDataSetChanged();

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, reading);
        adapter3.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        adapter3.add("Pass");
        adapter3.add("Fail");
        adapter3.notifyDataSetChanged();

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, test);
        adapter4.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);
        adapter4.add("Test1");
        adapter4.add("Test2");
        adapter4.notifyDataSetChanged();

        btnSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CollectionDto c1 = new CollectionDto();
                c1.setSession(spinner.getSelectedItem().toString());
                c1.setItem(spinner1.getSelectedItem().toString());
                c1.setSupplier(spinner2.getSelectedItem().toString());
                c1.setReading(spinner3.getSelectedItem().toString());
                c1.setTest(spinner4.getSelectedItem().toString());
                c1.setQty(et_kg.getText().toString());
                c1.setUOM(et_uom.getText().toString());
                c1.setComment(et_comment.getText().toString());

                boolean status = myDb.updateCollectionData(c1);
                if(status==true){
                    Context context = getApplicationContext();
                    CharSequence text = "Updated SuccessFully";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else{
                    Context context = getApplicationContext();
                    CharSequence text = "Updated Fail";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                Intent intent = new Intent(Transaction.this, Sync_settings.class);
                startActivity(intent);
            }
        });

        btnItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CollectionDto c1 = new CollectionDto();
                c1.setSession(spinner.getSelectedItem().toString());
                c1.setItem(spinner1.getSelectedItem().toString());
                c1.setSupplier(spinner2.getSelectedItem().toString());
                c1.setReading(spinner3.getSelectedItem().toString());
                c1.setTest(spinner4.getSelectedItem().toString());
                c1.setQty(et_kg.getText().toString());
                c1.setUOM(et_uom.getText().toString());
                c1.setComment(et_comment.getText().toString());

                boolean status = myDb.updateCollectionData(c1);
                if(status==true){
                    Context context = getApplicationContext();
                    CharSequence text = "Updated SuccessFully";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else{
                    Context context = getApplicationContext();
                    CharSequence text = "Updated Fail";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                Intent intent = new Intent(Transaction.this, Sync_settings.class);
                startActivity(intent);
            }
        });

        btnSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CollectionDto c1 = new CollectionDto();
                c1.setSession(spinner.getSelectedItem().toString());
                c1.setItem(spinner1.getSelectedItem().toString());
                c1.setSupplier(spinner2.getSelectedItem().toString());
                c1.setReading(spinner3.getSelectedItem().toString());
                c1.setTest(spinner4.getSelectedItem().toString());
                c1.setQty(et_kg.getText().toString());
                c1.setUOM(et_uom.getText().toString());
                c1.setComment(et_comment.getText().toString());

                boolean status = myDb.updateCollectionData(c1);
                if(status==true){
                    Context context = getApplicationContext();
                    CharSequence text = "Updated SuccessFully";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else{
                    Context context = getApplicationContext();
                    CharSequence text = "Updated Fail";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                Intent intent = new Intent(Transaction.this, Sync_settings.class);
                startActivity(intent);
            }
        });

        btnReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CollectionDto c1 = new CollectionDto();
                c1.setSession(spinner.getSelectedItem().toString());
                c1.setItem(spinner1.getSelectedItem().toString());
                c1.setSupplier(spinner2.getSelectedItem().toString());
                c1.setReading(spinner3.getSelectedItem().toString());
                c1.setTest(spinner4.getSelectedItem().toString());
                c1.setQty(et_kg.getText().toString());
                c1.setUOM(et_uom.getText().toString());
                c1.setComment(et_comment.getText().toString());

                boolean status = myDb.updateCollectionData(c1);
                if(status==true){
                    Context context = getApplicationContext();
                    CharSequence text = "Updated SuccessFully";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else{
                    Context context = getApplicationContext();
                    CharSequence text = "Updated Fail";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                Intent intent = new Intent(Transaction.this, Sync_settings.class);
                startActivity(intent);
            }
        });


        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CollectionDto c1 = new CollectionDto();
                c1.setSession(spinner.getSelectedItem().toString());
                c1.setItem(spinner1.getSelectedItem().toString());
                c1.setSupplier(spinner2.getSelectedItem().toString());
                c1.setReading(spinner3.getSelectedItem().toString());
                c1.setTest(spinner4.getSelectedItem().toString());
                c1.setQty(et_kg.getText().toString());
                c1.setUOM(et_uom.getText().toString());
                c1.setComment(et_comment.getText().toString());

                boolean status = myDb.updateCollectionData(c1);
                if(status==true){
                    Context context = getApplicationContext();
                    CharSequence text = "Updated SuccessFully";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else{
                    Context context = getApplicationContext();
                    CharSequence text = "Updated Fail";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                Intent intent = new Intent(Transaction.this, Sync_settings.class);
                startActivity(intent);
            }
        });

    }
        public void addData (){
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    CollectionDto c1 = new CollectionDto();
                    c1.setSession(spinner.getSelectedItem().toString());
                    c1.setItem(spinner1.getSelectedItem().toString());
                    c1.setSupplier(spinner2.getSelectedItem().toString());
                    c1.setReading(spinner3.getSelectedItem().toString());
                    c1.setTest(spinner4.getSelectedItem().toString());
                    c1.setQty(et_kg.getText().toString());
                    c1.setUOM(et_uom.getText().toString());
                    c1.setComment(et_comment.getText().toString());

                    boolean isInserted = myDb.insertCollection(c1);

                    if (isInserted == true) {
                        Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getApplicationContext(), "Data not Inserted", Toast.LENGTH_LONG).show();
                }
            });

        }


    public void deleteData(){
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isDeleted = myDb.deleteCollectionData(primaryId);

                if(isDeleted==true){

                    Context context = getApplicationContext();
                    CharSequence text = "Deleted SuccessFully";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    et_kg.setText(" ");
                    et_comment.setText(" ");
                    et_uom.setText(" ");

                }else{

                    Context context = getApplicationContext();
                    CharSequence text = "Deleted Fail";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }


    public void ViewCollectionData(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllCollectionData();
                if (res.getCount() == 0){
                    Toast.makeText(getApplicationContext(),"Nothing to show",Toast.LENGTH_LONG).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("session | "+res.getString(1)+ "item |"+res.getString(2)+ "supplier |"+res.getString(3)+
                            "qty "+res.getString(7));
                }

                Intent intent = new Intent(Transaction.this,Collection_List.class);
                startActivity(intent);
            }
        });
    }




    public void DeleteData(){
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {

            String time = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(adapterView.getContext(), "Selected: " + time,  Toast.LENGTH_LONG).show();

            String item = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(adapterView.getContext(), "Selected: " + item,Toast.LENGTH_LONG).show();

            String supplier = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(adapterView.getContext(), "Selected: " + supplier,  Toast.LENGTH_LONG).show();

            String reading = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(adapterView.getContext(), "Selected: " + reading, Toast.LENGTH_LONG).show();

            String test = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(adapterView.getContext(), "Selected: " + test, Toast.LENGTH_LONG).show();


    }




}
