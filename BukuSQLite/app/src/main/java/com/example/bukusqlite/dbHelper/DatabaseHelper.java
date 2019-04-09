package com.example.bukusqlite.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.provider.BaseColumns._ID;
import static com.example.bukusqlite.dbHelper.DatabaseContract.MahasiswaColumns.PENERBIT;
import static com.example.bukusqlite.dbHelper.DatabaseContract.MahasiswaColumns.PENULIS;
import static com.example.bukusqlite.dbHelper.DatabaseContract.TABLE_BUKU;
import static com.example.bukusqlite.dbHelper.DatabaseContract.MahasiswaColumns.JUDUL;
import static com.example.bukusqlite.dbHelper.DatabaseContract.TABLE_PEMINJAMAN;
import static com.example.bukusqlite.dbHelper.DatabaseContract.peminjamanColumns.JUDUL_BUKU;
import static com.example.bukusqlite.dbHelper.DatabaseContract.peminjamanColumns.NAMA;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    public static String CREATE_TABLE_BUKU = "create table " + TABLE_BUKU +
            " (" + _ID + " integer primary key autoincrement, " +
            JUDUL + " text not null, " +
            PENULIS + " text not null, " +
            PENERBIT + " text not null);";


    public static String CREATE_TABLE_PEMINJAMAN = "create table " + TABLE_PEMINJAMAN +
            " (" + _ID + " integer primary key autoincrement, " +
            NAMA + " text not null, " +
            JUDUL_BUKU + " text not null);";

    private static String DATABASE_NAME = "dbPerpustakaan";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_BUKU);
        db.execSQL(CREATE_TABLE_PEMINJAMAN);
    }

    /*
    Method onUpgrade akan di panggil ketika terjadi perbedaan versi
    Gunakan method onUpgrade untuk melakukan proses migrasi data
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
        Drop table tidak dianjurkan ketika proses migrasi terjadi dikarenakan data user akan hilang,
         */
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUKU);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEMINJAMAN);
        onCreate(db);
    }


}
