package com.vingcoz.ordersystem.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "JALAM_DB";

    private static final String TABLE_PARAMS = "PARAMS";
    private static final String TABLE_USERS = "L_USERS";
    private static final String TABLE_CUSTOMERS = "CUSTOMERS";
    private static final String TABLE_SALES = "SALES";
    private static final String TABLE_CANCEL = "S_CANCEL";
    private static final String TABLE_LOGIN_DET = "LOGIN_DET";


    public static final String PARAMS_TABLE_NAME = "PARAMS";
    public static final String PARAMS_VARNM = "VARNM";
    public static final String PARAMS_VARCHAR = "VARCHAR";
    public static final String PARAMS_VARINT = "VARINT";


    private static final String CREATE_TABLE = "create table " + PARAMS_TABLE_NAME + "(" +
            "PARAMS_ID  INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PARAMS_VARNM + " TEXT ,"
            + PARAMS_VARCHAR + " TEXT ,"
            + PARAMS_VARINT + " TEXT);";

    private static final String CREATE_TABLE_PARAMS = "CREATE TABLE IF NOT EXISTS "
            + TABLE_PARAMS + "("
            + "_ID" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "VarName" + " TEXT,"
            + "VarChar" + " TEXT,"
            + "VarInt" + " TEXT,"
            + "VarType" + " TEXT )";

    private static final String CREATE_TABLE_lOGIN_DET = "CREATE TABLE IF NOT EXISTS "
            + TABLE_LOGIN_DET + "("
            + "_ID" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "L_DATE" + " TEXT,"
            + "L_TIME" + " TEXT,"
            + "L_USER" + " TEXT)";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS "
            + TABLE_USERS + "("
            + "_ID" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "NAME" + " TEXT,"
            + "PASSWORD" + " TEXT,"
            + "HINT" + " TEXT,"
            + "ROLE" + " TEXT )";

    private static final String CREATE_TABLE_CUSTOMERS = "CREATE TABLE IF NOT EXISTS "
            + TABLE_CUSTOMERS + "("
            + "_ID" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "NAME" + " TEXT,"
            + "ADDRESS" + " TEXT,"
            + "HOUSENO" + " TEXT,"
            + "GPSX" + " TEXT,"
            + "GPSY" + " TEXT,"
            + "USER" + " TEXT,"
            + "L_READ" + " REAL,"
            + "PHONE" + " INTEGER )";

    private static final String CREATE_TABLE_SALES = "CREATE TABLE IF NOT EXISTS "
            + TABLE_SALES + "("
            + "_ID" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "BILLNO" + " INTEGER,"
            + "CUSTID" + " INTEGR,"
            + "ACCDT" + " TEXT,"
            + "TIME" + " TEXT,"
            + "QTY" + " DOUBLE,"
            + "RATE" + " DOUBLE,"
            + "TOTAL" + " DOUBLE,"
            + "USER" + " TEXT,"
            + "C_READING" + " REAL,"
            + "CANCEL" + " TEXT )";

    private static final String CREATE_TABLE_CANCEL = "CREATE TABLE IF NOT EXISTS "
            + TABLE_CANCEL + "("
            + "_ID" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "BILLNO" + " TEXT,"
            + "ACCDT" + " TEXT,"
            + "AMOUNT" + " TEXT,"
            + "REASON" + " TEXT,"
            + "USER" + " TEXT,"
            + "VarInt" + " INTEGER )";

    Context mCtx;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.mCtx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_PARAMS);
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_CUSTOMERS);
        db.execSQL(CREATE_TABLE_SALES);
        db.execSQL(CREATE_TABLE_CANCEL);
        db.execSQL(CREATE_TABLE_lOGIN_DET);

        setDefaultParams(db, "TimesOpen", "", 0, "I");
        setDefaultParams(db, "BillNo", "", 0, "I");
        setDefaultParams(db, "Activated", "N", 0, "C");
        setDefaultParams(db, "Company", "Nill", 0, "C");
        setDefaultParams(db, "Rkey", "Nill", 0, "C");
        setDefaultParams(db, "L_User", "Nill", 0, "C");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  /*      db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARAMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CANCEL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN_DET);
*/

        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_CUSTOMERS + " ADD COLUMN L_READ REAL");
            db.execSQL(" UPDATE  " + TABLE_CUSTOMERS + " SET L_READ = 0.00");

            db.execSQL("ALTER TABLE " + TABLE_SALES + " ADD COLUMN C_READING REAL");
            db.execSQL(" UPDATE  " + TABLE_SALES + " SET C_READING = 0.00");
        }
/*
        onCreate(db);*/


    }


    public void setDefaultParams(SQLiteDatabase db, String strVarName, String VarValue, int intValue, String strType) {
        // create default label
        ContentValues values = new ContentValues();
        values.put("VarName", strVarName);
        values.put("VarChar", VarValue);
        values.put("VarInt", intValue);
        values.put("VarType", strType);
        db.insert(TABLE_PARAMS, null, values);
    }

    public boolean AddUser(String strName, String strPassword, String strHint, String strRole) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NAME", strName);
        values.put("PASSWORD", strPassword);
        values.put("HINT", strHint);
        values.put("ROLE", strRole);
        db.insert(TABLE_USERS, null, values);
        db.close();
        return true;
    }


    public boolean SaveLoginDetails(String strDate, String strTime, String strUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("L_DATE", strDate);
        values.put("L_TIME", strTime);
        values.put("L_USER", strUser);
        db.insert(TABLE_LOGIN_DET, null, values);
        db.close();
        return true;
    }


    public void AddParams(String strVarName, String VarValue, int intValue, String strType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("VarName", strVarName);
        values.put("VarChar", VarValue);
        values.put("VarInt", intValue);
        values.put("VarType", strType);
        db.insert(TABLE_PARAMS, null, values);
        db.close();
    }


    public Long AddCustomers(Long lng_id, String strCusName, String strCusAddress, String strCusHouseNo, String strGpsx, String strGpsy, String strUser, String strPhoneNo, Double dblReading) {
        Long SaveId = (long) 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NAME", strCusName);
        values.put("ADDRESS", strCusAddress);
        values.put("HOUSENO", strCusHouseNo);
        values.put("GPSX", strGpsx);
        values.put("GPSY", strGpsy);
        values.put("USER", strUser);
        values.put("PHONE", strPhoneNo);
        values.put("L_READ", dblReading);

        if (lng_id <= 0) {
            SaveId = db.insert(TABLE_CUSTOMERS, null, values);
            return SaveId;
        } else {

            db.update(TABLE_CUSTOMERS, values, "_ID = " + lng_id + "", null);
            SaveId = (long) 1;
        }
        db.close();
        return SaveId;

    }

    public Long UpdateReading(Long lng_id, Double dblReading) {
        Long SaveId = (long) 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("L_READ", dblReading);

        db.update(TABLE_CUSTOMERS, values, "_ID = " + lng_id + "", null);
        SaveId = (long) 1;

        db.close();
        return SaveId;

    }

    public void UpdateParams(String strVarName, String VarValue, int intValue) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("VarChar", VarValue);
        values.put("VarInt", intValue);
        db.update(TABLE_PARAMS, // table
                values, // column/value
                "VarName = '" + strVarName + "'", null);
        db.close();
    }


    public String GetCharParams(String strVarName) {

        String strValue = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select VarChar  from " + TABLE_PARAMS + " where VarName = '" + strVarName + "' ", null); // h. limit

        if (cursor != null) {
            cursor.moveToFirst();
            strValue = cursor.getString(cursor.getColumnIndex("VarChar"));
        }
        return strValue;
    }

    public boolean Login(String strUserName, String strPassword) {

        String strValue = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select NAME, PASSWORD  from " + TABLE_USERS + " where NAME = '" + strUserName + "' AND PASSWORD = '" + strPassword + "' ", null); // h. limit
        int CountUser = cursor.getCount();

        if (CountUser > 0) {

            return true;
        } else {
            return false;
        }


    }


    public int GetIntParams(String strVarName) {

        int intValue = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select VarInt  from " + TABLE_PARAMS + " where VarName = '" + strVarName + "' ", null); // h. limit

        if (cursor != null) {
            cursor.moveToFirst();
            intValue = cursor.getInt(cursor.getColumnIndex("VarInt"));
        }
        return intValue;

    }

    public Cursor GetQuery(String strQuery) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(strQuery, null);
        Log.e("Query string", strQuery);
        return cursor;

    }

    public ArrayList<String> GetCustomersLabel() {
        ArrayList<String> labels = new ArrayList<String>();

        String selectQuery = "SELECT  NAME FROM " + TABLE_CUSTOMERS + " order by NAME";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }


    public boolean SaveSales(Long lng_Id, int intBillNo, int intCustomerID, String strDate, String strTime,
                             Double dblQty, Double dblRate, Double dblTotal,
                             String strUser, String strCancel, Double dblCReading) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("BILLNO", intBillNo);
        values.put("CUSTID", intCustomerID);
        values.put("ACCDT", strDate);
        values.put("TIME", strTime);
        values.put("QTY", dblQty);
        values.put("RATE", dblRate);
        values.put("TOTAL", dblTotal);
        values.put("USER", strUser);
        values.put("CANCEL", strCancel);
        values.put("C_READING", dblCReading);
        if (lng_Id <= 0) {

            db.insert(TABLE_SALES, null, values);
        } else {
            db.update(TABLE_SALES, values, "where _id = " + lng_Id + " ", null);
        }

        db.close();
        return true;
    }

}
