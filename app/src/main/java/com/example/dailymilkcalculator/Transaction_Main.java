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

import dto.MainDto;

public class Transaction_Main extends AppCompatActivity implements AdapterView.OnItemClickListener {

    DatabaseHelper myDb;
    Button btnSave, btnCancel,btnuom,btnCenter, btnItem, btnTrip, btnTruck, btnCompartment, btnTest, btnReading, btnVat;
    EditText et_uom,et_qty,et_cmmnt;
    Spinner spinner,spinner1,spinner2,spinner3,spinner4,spinner5,spinner6,spinner7;
    int primaryId;

    private static final String[] center = {"Center1", "Center2"};
    private static final String[] item = {"Cow Milk", "Cheese","Curd", "Yogurt"};
    private static final String[] trip = {"Trip1", "Trip2"};
    private static final String[] truck = {"Truck1", "Truck2"};
    private static final String[] compartment = {"Compartment1", "Compartment2"};
    private static final String[] test = {"Test1", "Test2"};
    private static final String[] reading = {"Pass", "Fail"};
    private static final String[] vat = {"Vat1", "Vat2","Vat3"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction__main);

        myDb = new DatabaseHelper(this);


        spinner = (Spinner) findViewById(R.id.spinnerCollection);
        spinner1 = (Spinner) findViewById(R.id.spinnerItem);
        spinner2 = (Spinner) findViewById(R.id.spinnerTrip);
        spinner3 = (Spinner) findViewById(R.id.spinnerTruck);
        spinner4 = (Spinner) findViewById(R.id.spinnerCompartment);
        spinner5 = (Spinner) findViewById(R.id.spinnerTest);
        spinner6 = (Spinner) findViewById(R.id.spinnerReading);
        spinner7 = (Spinner) findViewById(R.id.spinnerVat);

        btnSave = (Button)findViewById(R.id.btn_save);
        btnCancel = (Button)findViewById(R.id.btn_cancel);
        et_cmmnt = (EditText)findViewById(R.id.et_comment);
        et_qty = (EditText)findViewById(R.id.et_qty);
        et_uom = (EditText)findViewById(R.id.et_uom);
        btnuom = (Button)findViewById(R.id.btnuom);
        btnCenter = (Button)findViewById(R.id.btncollection);
        btnItem = (Button)findViewById(R.id.btnitem);
        btnTrip = (Button)findViewById(R.id.btntrip);
        btnTruck = (Button)findViewById(R.id.btntruck);
        btnCompartment = (Button)findViewById(R.id.btncomp);
        btnTest = (Button)findViewById(R.id.btntest);
        btnReading = (Button)findViewById(R.id.btnreading);
        btnVat = (Button) findViewById(R.id.btnvat);


        ViewAllData();
        addData();
        deleteData();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, center);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, item);
        adapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, trip);
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);


        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, truck);
        adapter3.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, compartment);
        adapter4.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, test);
        adapter5.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);

        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, reading);
        adapter6.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter6);

        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, vat);
        adapter7.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner7.setAdapter(adapter7);



        btnuom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainDto m1 = new MainDto();
                m1.setCenter(spinner.getSelectedItem().toString());
                m1.setItem(spinner1.getSelectedItem().toString());
                m1.setTrip(spinner2.getSelectedItem().toString());
                m1.setTruck(spinner3.getSelectedItem().toString());
                m1.setCompartment(spinner4.getSelectedItem().toString());
                m1.setTest(spinner5.getSelectedItem().toString());
                m1.setReading(spinner6.getSelectedItem().toString());
                m1.setVat(spinner7.getSelectedItem().toString());
                m1.setUom(et_uom.getText().toString());
                m1.setQty(et_qty.getText().toString());
                m1.setComment(et_cmmnt.getText().toString());

                boolean status = myDb.updateMainData(m1);
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
                Intent intent = new Intent(Transaction_Main.this,Sync_settings.class);
                startActivity(intent);
            }
        });

        btnCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainDto m1 = new MainDto();
                m1.setCenter(spinner.getSelectedItem().toString());
                m1.setItem(spinner1.getSelectedItem().toString());
                m1.setTrip(spinner2.getSelectedItem().toString());
                m1.setTruck(spinner3.getSelectedItem().toString());
                m1.setCompartment(spinner4.getSelectedItem().toString());
                m1.setTest(spinner5.getSelectedItem().toString());
                m1.setReading(spinner6.getSelectedItem().toString());
                m1.setVat(spinner7.getSelectedItem().toString());
                m1.setUom(et_uom.getText().toString());
                m1.setQty(et_qty.getText().toString());
                m1.setComment(et_cmmnt.getText().toString());

                boolean status = myDb.updateMainData(m1);
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

                Intent intent = new Intent(Transaction_Main.this,Sync_settings.class);
                startActivity(intent);
            }
        });

        btnItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainDto m1 = new MainDto();
                m1.setCenter(spinner.getSelectedItem().toString());
                m1.setItem(spinner1.getSelectedItem().toString());
                m1.setTrip(spinner2.getSelectedItem().toString());
                m1.setTruck(spinner3.getSelectedItem().toString());
                m1.setCompartment(spinner4.getSelectedItem().toString());
                m1.setTest(spinner5.getSelectedItem().toString());
                m1.setReading(spinner6.getSelectedItem().toString());
                m1.setVat(spinner7.getSelectedItem().toString());
                m1.setUom(et_uom.getText().toString());
                m1.setQty(et_qty.getText().toString());
                m1.setComment(et_cmmnt.getText().toString());

                boolean status = myDb.updateMainData(m1);
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
                Intent intent = new Intent(Transaction_Main.this,Sync_settings.class);
                startActivity(intent);
            }
        });

        btnTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainDto m1 = new MainDto();
                m1.setCenter(spinner.getSelectedItem().toString());
                m1.setItem(spinner1.getSelectedItem().toString());
                m1.setTrip(spinner2.getSelectedItem().toString());
                m1.setTruck(spinner3.getSelectedItem().toString());
                m1.setCompartment(spinner4.getSelectedItem().toString());
                m1.setTest(spinner5.getSelectedItem().toString());
                m1.setReading(spinner6.getSelectedItem().toString());
                m1.setVat(spinner7.getSelectedItem().toString());
                m1.setUom(et_uom.getText().toString());
                m1.setQty(et_qty.getText().toString());
                m1.setComment(et_cmmnt.getText().toString());

                boolean status = myDb.updateMainData(m1);
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
                Intent intent = new Intent(Transaction_Main.this,Sync_settings.class);
                startActivity(intent);
            }
        });

        btnTruck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainDto m1 = new MainDto();
                m1.setCenter(spinner.getSelectedItem().toString());
                m1.setItem(spinner1.getSelectedItem().toString());
                m1.setTrip(spinner2.getSelectedItem().toString());
                m1.setTruck(spinner3.getSelectedItem().toString());
                m1.setCompartment(spinner4.getSelectedItem().toString());
                m1.setTest(spinner5.getSelectedItem().toString());
                m1.setReading(spinner6.getSelectedItem().toString());
                m1.setVat(spinner7.getSelectedItem().toString());
                m1.setUom(et_uom.getText().toString());
                m1.setQty(et_qty.getText().toString());
                m1.setComment(et_cmmnt.getText().toString());

                boolean status = myDb.updateMainData(m1);
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
                Intent intent = new Intent(Transaction_Main.this,Sync_settings.class);
                startActivity(intent);
            }
        });


        btnCompartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainDto m1 = new MainDto();
                m1.setCenter(spinner.getSelectedItem().toString());
                m1.setItem(spinner1.getSelectedItem().toString());
                m1.setTrip(spinner2.getSelectedItem().toString());
                m1.setTruck(spinner3.getSelectedItem().toString());
                m1.setCompartment(spinner4.getSelectedItem().toString());
                m1.setTest(spinner5.getSelectedItem().toString());
                m1.setReading(spinner6.getSelectedItem().toString());
                m1.setVat(spinner7.getSelectedItem().toString());
                m1.setUom(et_uom.getText().toString());
                m1.setQty(et_qty.getText().toString());
                m1.setComment(et_cmmnt.getText().toString());

                boolean status = myDb.updateMainData(m1);
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
                Intent intent = new Intent(Transaction_Main.this,Sync_settings.class);
                startActivity(intent);
            }
        });



        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainDto m1 = new MainDto();
                m1.setCenter(spinner.getSelectedItem().toString());
                m1.setItem(spinner1.getSelectedItem().toString());
                m1.setTrip(spinner2.getSelectedItem().toString());
                m1.setTruck(spinner3.getSelectedItem().toString());
                m1.setCompartment(spinner4.getSelectedItem().toString());
                m1.setTest(spinner5.getSelectedItem().toString());
                m1.setReading(spinner6.getSelectedItem().toString());
                m1.setVat(spinner7.getSelectedItem().toString());
                m1.setUom(et_uom.getText().toString());
                m1.setQty(et_qty.getText().toString());
                m1.setComment(et_cmmnt.getText().toString());

                boolean status = myDb.updateMainData(m1);
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
                Intent intent = new Intent(Transaction_Main.this,Sync_settings.class);
                startActivity(intent);
            }
        });


        btnReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainDto m1 = new MainDto();
                m1.setCenter(spinner.getSelectedItem().toString());
                m1.setItem(spinner1.getSelectedItem().toString());
                m1.setTrip(spinner2.getSelectedItem().toString());
                m1.setTruck(spinner3.getSelectedItem().toString());
                m1.setCompartment(spinner4.getSelectedItem().toString());
                m1.setTest(spinner5.getSelectedItem().toString());
                m1.setReading(spinner6.getSelectedItem().toString());
                m1.setVat(spinner7.getSelectedItem().toString());
                m1.setUom(et_uom.getText().toString());
                m1.setQty(et_qty.getText().toString());
                m1.setComment(et_cmmnt.getText().toString());

                boolean status = myDb.updateMainData(m1);
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
                Intent intent = new Intent(Transaction_Main.this,Sync_settings.class);
                startActivity(intent);
            }
        });



        btnVat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainDto m1 = new MainDto();
                m1.setCenter(spinner.getSelectedItem().toString());
                m1.setItem(spinner1.getSelectedItem().toString());
                m1.setTrip(spinner2.getSelectedItem().toString());
                m1.setTruck(spinner3.getSelectedItem().toString());
                m1.setCompartment(spinner4.getSelectedItem().toString());
                m1.setTest(spinner5.getSelectedItem().toString());
                m1.setReading(spinner6.getSelectedItem().toString());
                m1.setVat(spinner7.getSelectedItem().toString());
                m1.setUom(et_uom.getText().toString());
                m1.setQty(et_qty.getText().toString());
                m1.setComment(et_cmmnt.getText().toString());

                boolean status = myDb.updateMainData(m1);
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
                Intent intent = new Intent(Transaction_Main.this,Sync_settings.class);
                startActivity(intent);
            }
        });

    }


    public void addData(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainDto m1 = new MainDto();
                m1.setCenter(spinner.getSelectedItem().toString());
                m1.setItem(spinner1.getSelectedItem().toString());
                m1.setTrip(spinner2.getSelectedItem().toString());
                m1.setTruck(spinner3.getSelectedItem().toString());
                m1.setCompartment(spinner4.getSelectedItem().toString());
                m1.setTest(spinner5.getSelectedItem().toString());
                m1.setReading(spinner6.getSelectedItem().toString());
                m1.setVat(spinner7.getSelectedItem().toString());
                m1.setUom(et_uom.getText().toString());
                m1.setQty(et_qty.getText().toString());
                m1.setComment(et_cmmnt.getText().toString());

                boolean isInserted = myDb.insertMain(m1);
                if (isInserted == true) {
                    Toast.makeText(getApplicationContext(),"Data inserted",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(),"Data not Inserted",Toast.LENGTH_LONG).show();
            }
        });

    }



    public void ViewAllData(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllMainData();
                if (res.getCount() == 0){
                    Toast.makeText(getApplicationContext(),"Nothing to show",Toast.LENGTH_LONG).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("center | "+res.getString(1)+ "item |"+res.getString(2)+ "qty |"+res.getString(10));
                }

                Intent intent = new Intent(Transaction_Main.this,Main_center.class);
                startActivity(intent);
            }
        });
    }


    public void deleteData(){
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isDeleted = myDb.deleteMainData(primaryId);

                if(isDeleted==true){

                    Context context = getApplicationContext();
                    CharSequence text = "Deleted SuccessFully";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    et_qty.setText(" ");
                    et_cmmnt.setText(" ");
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




    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        String center = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), "Selected: " + center,  Toast.LENGTH_LONG).show();

        String item = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), "Selected: " + item,Toast.LENGTH_LONG).show();

        String trip = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), "Selected: " + trip,  Toast.LENGTH_LONG).show();

        String truck = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), "Selected: " + truck, Toast.LENGTH_LONG).show();

        String compartment = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), "Selected: " + compartment, Toast.LENGTH_LONG).show();

        String test = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), "Selected: " + test, Toast.LENGTH_LONG).show();

        String reading = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), "Selected: " + reading, Toast.LENGTH_LONG).show();

        String vat = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), "Selected: " + vat, Toast.LENGTH_LONG).show();

    }
}


