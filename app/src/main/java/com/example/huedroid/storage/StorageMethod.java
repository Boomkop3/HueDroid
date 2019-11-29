package com.example.huedroid.storage;

public interface StorageMethod {
    public void setCurrentConnection(String ip, int port, boolean emulator, String session);
    public SQLiteStorage.dbConnectionResponse getCurrentConnection();
}
