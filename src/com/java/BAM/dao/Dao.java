package com.java.BAM.dao;

public abstract class Dao {
    public int lastId;

    public int getLastId() {
        return lastId + 1;
    }
}
