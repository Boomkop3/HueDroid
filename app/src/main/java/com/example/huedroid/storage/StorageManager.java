package com.example.huedroid.storage;

import android.content.Context;

public class StorageManager {
    public static StorageMethod getDetaultStorage(Context context){
        return SQLiteStorage.getInstance(context);
    }
}
