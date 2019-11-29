package com.example.huedroid.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLiteStorage extends SQLiteOpenHelper implements StorageMethod {
    private static final String DB_NAME = "DATABASE";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE_NAME = "CONNECTION";
    private static final String KEY_IP = "ip";
    private static final String KEY_PORT = "port";
    private static final String KEY_EMULATED = "emulated";
    private static final String KEY_SESSION = "session";



    private static final String CREATE_TABLE =
            "CREATE TABLE " + DB_TABLE_NAME + " (" +
                    KEY_IP + " TEXT," +
                    KEY_SESSION + " TEXT," +
                    KEY_EMULATED + " INTEGER," +
                    KEY_PORT + " INTEGER);";


    private static SQLiteStorage instance;

    private SQLiteStorage(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static SQLiteStorage getInstance(Context context){
        if (instance == null) instance = new SQLiteStorage(context);
        return instance;
    }

    public class dbConnectionResponse {
        public dbConnectionResponse(String ip, int port, boolean emulated, String session) {
            this.ip = ip;
            this.port = port;
            this.emulated = emulated;
            this.session = session;
        }

        public String ip;
        public int port;
        public boolean emulated;
        public String session;
    }

    public dbConnectionResponse getCurrentConnection(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select " + KEY_IP + ", " + KEY_SESSION + ", " + KEY_EMULATED + ", " + KEY_PORT + " from " + DB_TABLE_NAME, new String[] {});
        cursor.moveToNext();
        String ip = cursor.getString(0);
        String session = cursor.getString(1);
        boolean emulated = cursor.getInt(2)==1;
        int port = cursor.getInt(3);

        return new dbConnectionResponse(ip, port, emulated, session);
    }

    public void setCurrentConnection(String ip, int port, boolean emulated, String session){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DB_TABLE_NAME, "", new String[] {});

        if (session == null) session = "";

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_IP, ip);
        contentValues.put(KEY_SESSION, session);
        contentValues.put(KEY_EMULATED, emulated?1:0);
        contentValues.put(KEY_PORT, port);
        db.insert(DB_TABLE_NAME, null, contentValues);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // nothing to upgrade
    }
}
