package com.example.dailymilkcalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import dto.CollectionDto;
import dto.MainDto;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MilkCollector.db";


    private static final String TABLE_NAME="users";
    private  static final String COL_1="id";
    private static final String COL_2="username";
    private static final String COL_3="password";
    private static final String COL_4="type";




    private static final String TABLE_LABELS="collection_center";
    private  static final String KEY_ID="id";
    private static final String COL_SESSION="session";
    private static final String COL1_item="item";
    private static final String COL2_supplier="supplier";
    private static final String COL3_reading="reading";
    private static final String COL4_test="test";
    private static final String COL5_Qty="qty";
    private static final String COL6_uom="UOM";
    private static final String COL7_comment="comment";


    private static final String TABLE_main="main_center";
    private  static final String ID="id";
    private static final String COL_CENTER="center";
    private static final String COL1_item1="item";
    private static final String COL2_TRIP="trip";
    private static final String COL3_TRUCK="truck";
    private static final String COL4_COMPARTMENT="compartment";
    private static final String COL5_TEST="test";
    private static final String COL6_READING="reading";
    private static final String COL7_VAT="vat";
    private static final String COL7_QTY="qty";
    private static final String COL7_UOM="uom";
    private static final String COL7_COMMENT="comment";
    private String id;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +" ( id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT,type TEXT)");

        db.execSQL( "CREATE TABLE " + TABLE_LABELS +" (id INTEGER PRIMARY KEY AUTOINCREMENT, session TEXT, item TEXT, supplier TEXT, reading TEXT," +
                "test TEXT, qty TEXT, UOM TEXT, comment TEXT)");

        db.execSQL( "CREATE TABLE " + TABLE_main +" (id INTEGER PRIMARY KEY AUTOINCREMENT, center TEXT, item TEXT, trip TEXT, truck TEXT," +
                "compartment TEXT, test TEXT, reading TEXT, vat TEXT,qty TEXT, uom TEXT, comment TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " +TABLE_LABELS);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " +TABLE_main);
        onCreate(db);
    }

    public long register(String username, String password, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, password);
        contentValues.put(COL_4,type);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result;
    }


    public boolean insertCollection(CollectionDto c){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_SESSION,c.getSession());
        contentValues.put(COL1_item,c.getItem());
        contentValues.put(COL2_supplier,c.getSupplier());
        contentValues.put(COL3_reading,c.getReading());
        contentValues.put(COL4_test,c.getTest());
        contentValues.put(COL5_Qty,c.getQty());
        contentValues.put(COL6_uom,c.getUOM());
        contentValues.put(COL7_comment,c.getComment());
        long result = db.insert(TABLE_LABELS,null,contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }


    public boolean insertMain(MainDto m){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_CENTER,m.getCenter());
        contentValues.put(COL1_item1,m.getItem());
        contentValues.put(COL2_TRIP,m.getTrip());
        contentValues.put(COL3_TRUCK,m.getTruck());
        contentValues.put(COL4_COMPARTMENT,m.getCompartment());
        contentValues.put(COL5_TEST,m.getTest());
        contentValues.put(COL6_READING,m.getReading());
        contentValues.put(COL7_VAT,m.getVat());
        contentValues.put(COL7_QTY,m.getQty());
        contentValues.put(COL7_UOM,m.getUom());
        contentValues.put(COL7_COMMENT,m.getComment());

        long result = db.insert(TABLE_main,null,contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }


    public Cursor getAllCollectionData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_LABELS,null);
        return res;
    }

    public Cursor getAllMainData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_main,null);
        return res;
    }


    public boolean updateCollectionData(CollectionDto c){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID,c.getId());
        contentValues.put(COL_SESSION,c.getSession());
        contentValues.put(COL1_item,c.getItem());
        contentValues.put(COL2_supplier,c.getSupplier());
        contentValues.put(COL3_reading,c.getReading());
        contentValues.put(COL4_test,c.getTest());
        contentValues.put(COL5_Qty,c.getQty());
        contentValues.put(COL6_uom,c.getUOM());

        String selection = id + " LIKE ?";
        String[] selectionArgs = { c.getId() };

        int count = db.update(
                TABLE_LABELS,
                contentValues,
                selection,
                selectionArgs);

        if(count>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean updateMainData(MainDto m){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,m.getId());
        contentValues.put(COL_CENTER,m.getCenter());
        contentValues.put(COL1_item1,m.getItem());
        contentValues.put(COL2_TRIP,m.getTrip());
        contentValues.put(COL3_TRUCK,m.getTruck());
        contentValues.put(COL4_COMPARTMENT,m.getCompartment());
        contentValues.put(COL5_TEST,m.getTest());
        contentValues.put(COL6_READING,m.getReading());
        contentValues.put(COL7_VAT,m.getVat());
        String selection = id + " LIKE ?";
        String[] selectionArgs = { m.getId() };
        int count = db.update(
                TABLE_main,
                contentValues,
                selection,
                selectionArgs);

        if(count>0){
            return true;
        }else{
            return false;
        }
    }


    public Cursor readUser(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                COL_1,
                COL_2,
                COL_3,
                COL_4

        };

        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        return cursor;
    }

    public String CheckUserAndGetType(String username,String password){

        String type = "";

        String[] pro = {COL_4};
        String selectQuery = "SELECT type FROM "+ TABLE_NAME+" WHERE username =? AND password =?";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,new String[] {username,password});

        // looping through all rows and adding to list
        if (cursor.getCount()>0) {
            cursor.moveToNext();
            type = cursor.getString(0);
            cursor.close();
        }
        System.out.println(type);
        return type;
    }



    public boolean deleteCollectionData(int CollId){
        SQLiteDatabase db = getReadableDatabase();
        String selection = KEY_ID + " LIKE ?";

        String[] selectionArgs = { Integer.toString(CollId) };
        // Issue SQL statement.
        int deletedRows = db.delete( TABLE_LABELS, selection, selectionArgs);


        if(deletedRows>0){

            return true;

        }else{

            return false;

        }
    }


    public boolean deleteMainData(int MainId){
        SQLiteDatabase db = getReadableDatabase();
        String selection = ID + " LIKE ?";

        String[] selectionArgs = { Integer.toString(MainId) };
        // Issue SQL statement.
        int deletedRows = db.delete( TABLE_main, selection, selectionArgs);


        if(deletedRows>0){

            return true;

        }else{

            return false;

        }
    }

    public Boolean CheckUsername(String username){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE username=?", new String[]{username});
        if(cursor.getCount() > 0){
            return false;
        }else{
            return true;
        }
    }

    public Boolean CheckLogin(String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE username=? AND password=?", new String[]{username, password});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

}
