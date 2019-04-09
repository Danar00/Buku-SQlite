package com.example.bukusqlite.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.bukusqlite.PeminjamanModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.bukusqlite.dbHelper.DatabaseContract.TABLE_BUKU;
import static com.example.bukusqlite.dbHelper.DatabaseContract.TABLE_PEMINJAMAN;
import static com.example.bukusqlite.dbHelper.DatabaseContract.peminjamanColumns.JUDUL_BUKU;
import static com.example.bukusqlite.dbHelper.DatabaseContract.peminjamanColumns.NAMA;

public class PeminjamanHelper {
    private Context context;
    private DatabaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public PeminjamanHelper(Context context){this.context = context;};

    public PeminjamanHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }

    public ArrayList<PeminjamanModel> getAllData(){
        Cursor cursor = database.query(TABLE_PEMINJAMAN, null, null, null, null, null, _ID + " DESC", null);
        cursor.moveToFirst();
        ArrayList<PeminjamanModel> arrayList = new ArrayList<>();
        PeminjamanModel peminjamanModel;
        if (cursor.getCount() > 0) {
            do {
                peminjamanModel = new PeminjamanModel();
                peminjamanModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                peminjamanModel.setNama(cursor.getString(cursor.getColumnIndexOrThrow(NAMA)));
                peminjamanModel.setJudul_buku(cursor.getString(cursor.getColumnIndexOrThrow(JUDUL_BUKU)));
                arrayList.add(peminjamanModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(PeminjamanModel peminjamanModel) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(NAMA, peminjamanModel.getNama());
        initialValues.put(JUDUL_BUKU, peminjamanModel.getJudul_buku());
        return database.insert(TABLE_PEMINJAMAN, null, initialValues);
    }

    public int update(PeminjamanModel peminjamanModel){
        ContentValues args = new ContentValues();
        args.put(NAMA, peminjamanModel.getNama());
        args.put(JUDUL_BUKU, peminjamanModel.getJudul_buku());
        return database.update(TABLE_PEMINJAMAN, args, _ID + "= '" + peminjamanModel.getId() + "'", null);
    }

    public int delete(int id) {
        return database.delete(TABLE_PEMINJAMAN, _ID + " = '" + id + "'", null);
    }



}
