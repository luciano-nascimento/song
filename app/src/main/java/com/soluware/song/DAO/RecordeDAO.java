package com.soluware.song.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Luciano on 06/06/2016.
 */
public class RecordeDAO implements IRecorde{

    private static RecordeDAO instance;
    private SQLiteDatabase db;

    private RecordeDAO(Context context) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        //"conecta" no banco
        db = databaseHelper.getWritableDatabase();
    }

    public static RecordeDAO getInstance(Context context){
        if(instance == null){
            instance = new RecordeDAO(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public long gravar(Recorde rec) {

        ContentValues values = new ContentValues();
        values.put(RecordeContract.Columns.PONTOS, rec.getPontos());
        long id = db.insert(RecordeContract.TABLE_NAME, null, values);
        return id;
    }

    private static Recorde fromCursor(Cursor c){
        int id = c.getInt(c.getColumnIndex(RecordeContract.Columns._ID));
        int pontos = c.getInt(c.getColumnIndex(RecordeContract.Columns.PONTOS));
        return new Recorde(id, pontos);
    }

    @Override
    public void alterar(Recorde rec) {
        ContentValues values = new ContentValues();
        values.put(RecordeContract.Columns.PONTOS, rec.getPontos());
        db.update(RecordeContract.TABLE_NAME,
                values, RecordeContract.Columns._ID + "=?", new String[]{"1"}
        );
    }

    @Override
    public Recorde recuperar() {
        String[] columns = new String[]{RecordeContract.Columns._ID, RecordeContract.Columns.PONTOS};
        Recorde rec = null;

        try (Cursor c = db.query(RecordeContract.TABLE_NAME, columns, "1", null, null, null, null)) {
            if (c.moveToFirst()) {
                do {
                    //objeto com id e pontos
                    rec = RecordeDAO.fromCursor(c);
                } while (c.moveToNext());
            }
        }

        return rec;
    }
}
