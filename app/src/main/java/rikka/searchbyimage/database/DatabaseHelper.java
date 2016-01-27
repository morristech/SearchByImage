package rikka.searchbyimage.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import rikka.searchbyimage.database.table.CustomEngineTable;

/**
 * Created by Rikka on 2016/1/24.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "search_engines.db";

    private static DatabaseHelper instance;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper instance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CustomEngineTable.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == 2) {
            db.execSQL("ALTER TABLE " + CustomEngineTable.TABLE_NAME + " ADD " + CustomEngineTable.COLUMN_ENABLED + " integer NOT NULL DEFAULT(1)");
        }
    }
}