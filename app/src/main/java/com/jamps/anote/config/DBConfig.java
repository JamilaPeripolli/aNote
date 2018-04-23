package com.jamps.anote.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConfig extends SQLiteOpenHelper {

    private static final String DB_NAME = "anote";

    private static final int VERSION = 1;

    public DBConfig(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE note(");
        sql.append("id INTEGER PRIMARY KEY AUTOINCREMENT");
        sql.append(", title VARCHAR(100)");
        sql.append(", description VARCHAR(280)");
        sql.append(", important INTEGER);");
        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE note;");

        onCreate(db);
    }
}
