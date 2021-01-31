package com.soluware.song.DAO;

import android.content.Context;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Luciano on 06/06/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "RECORDE";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CREATE =
            "CREATE TABLE " + RecordeContract.TABLE_NAME + " (" +
                    RecordeContract.Columns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "  +
                    RecordeContract.Columns.PONTOS + " PONTOS TEXT);";
    private static final String TABLE_DROP = "DROP TABLE IF EXISTS " + RecordeContract.TABLE_NAME;
    private static DatabaseHelper instance;

    //singleton design pattern, protege a instância e é instânciado apenas apartir da primeira chamada da aclasse
    public static DatabaseHelper getInstance(Context context){
        if(instance == null){
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    //construtor deve receber 2 param db name e db version
    DatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_DROP);
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }


}
