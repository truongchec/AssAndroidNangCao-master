package com.example.dung.ass.database;

public interface Constant {
    String TABLE_USER = "User";
    String TB_COLUMN_ID = "Id";
    String TB_COLUMN_NAME = "Name";
    String TB_COLUMN_PHONE = "PhoneNumber";

    String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "(" +
            ""+ TB_COLUMN_ID + " PRIMARY KEY NOT NULL," +
            ""+ TB_COLUMN_NAME + " NVARCHAR(50)," +
            ""+ TB_COLUMN_PHONE + " NVARCHAR" +
            ")";
}
