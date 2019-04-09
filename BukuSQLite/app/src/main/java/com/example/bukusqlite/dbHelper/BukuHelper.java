package com.example.bukusqlite.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.bukusqlite.BukuModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.bukusqlite.dbHelper.DatabaseContract.MahasiswaColumns.JUDUL;
import static com.example.bukusqlite.dbHelper.DatabaseContract.MahasiswaColumns.PENERBIT;
import static com.example.bukusqlite.dbHelper.DatabaseContract.MahasiswaColumns.PENULIS;
import static com.example.bukusqlite.dbHelper.DatabaseContract.TABLE_BUKU;

public class BukuHelper {

    private Context context;
    private DatabaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public BukuHelper(Context context){this.context = context;}

    public BukuHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }

    public ArrayList<BukuModel> getAllData(){
        Cursor cursor = database.query(TABLE_BUKU, null, null, null, null,
                null, _ID +" DESC", null);
        cursor.moveToFirst();
        ArrayList<BukuModel> arrayList = new ArrayList<>();
        BukuModel bukuModel;
        if (cursor.getCount() > 0){
            do {
                bukuModel = new BukuModel();
                bukuModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                bukuModel.setJudul(cursor.getString(cursor.getColumnIndexOrThrow(JUDUL)));
                bukuModel.setPenulis(cursor.getString(cursor.getColumnIndexOrThrow(PENULIS)));
                bukuModel.setPenerbit(cursor.getString(cursor.getColumnIndexOrThrow(PENERBIT)));
                arrayList.add(bukuModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(BukuModel bukuModel){
        ContentValues initialValue = new ContentValues();
        initialValue.put(JUDUL, bukuModel.getJudul());
        initialValue.put(PENULIS, bukuModel.getPenulis());
        initialValue.put(PENERBIT, bukuModel.getPenerbit());
        return database.insert(TABLE_BUKU, null, initialValue);
    }

    public int update(BukuModel bukuModel){
        ContentValues args = new ContentValues();
        args.put(JUDUL, bukuModel.getJudul());
        args.put(PENULIS, bukuModel.getPenulis());
        args.put(PENERBIT, bukuModel.getPenerbit());
        return database.update(TABLE_BUKU, args, _ID + "= '" + bukuModel.getId() + "'", null);
    }

    public int delete(int id){
        return database.delete(TABLE_BUKU, _ID + "= '" + id + "'", null);
    }

}
