package com.example.bukusqlite;

public class PeminjamanModel {
    private int id;
    private String nama, judul_buku;

    public PeminjamanModel(){};

    public PeminjamanModel(String nama, String judul_buku) {
        this.nama = nama;
        this.judul_buku = judul_buku;
    }

    public PeminjamanModel(int id, String nama, String judul_buku) {
        this.id = id;
        this.nama = nama;
        this.judul_buku = judul_buku;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJudul_buku() {
        return judul_buku;
    }

    public void setJudul_buku(String judul_buku) {
        this.judul_buku = judul_buku;
    }
}
