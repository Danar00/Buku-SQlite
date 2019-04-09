package com.example.bukusqlite.dbHelper;
import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_BUKU = "table_buku";
    static final class MahasiswaColumns implements BaseColumns {

        static String JUDUL = "judul";
        static String PENULIS = "penulis";
        static String PENERBIT = "penerbit";



    }

    static String TABLE_PEMINJAMAN = "table_peminjaman";
    static final class peminjamanColumns implements BaseColumns{
        static String NAMA = "nama";
        static String JUDUL_BUKU = "judul_buku";
    }



}
