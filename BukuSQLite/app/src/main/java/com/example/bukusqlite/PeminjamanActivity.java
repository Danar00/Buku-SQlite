package com.example.bukusqlite;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.bukusqlite.dbHelper.PeminjamanHelper;

import java.util.ArrayList;

public class PeminjamanActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private EditText nama;
    private Button update, delete;
    private EditText judul_buku;

    private PeminjamanAdapter peminjamanAdapter;
    private PeminjamanHelper peminjamanHelper;
    ArrayList<PeminjamanModel> peminjamanModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_peminjaman_view);

        recyclerView = findViewById(R.id.recyclerView_peminjaman);
        nama = findViewById(R.id.edit_nama);
        judul_buku = findViewById(R.id.edit_judul_buku);

        update = findViewById(R.id.btn_update);
        delete = findViewById(R.id.btn_delete);

        peminjamanHelper = new PeminjamanHelper(this);
        peminjamanAdapter = new PeminjamanAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getAllData();


    }

    private void getAllData() {
        peminjamanHelper.open();
        // Ambil semua data buku di database
        peminjamanModelList = peminjamanHelper.getAllData();
        peminjamanHelper.close();
        peminjamanAdapter.addItem(peminjamanModelList);
        recyclerView.setAdapter(peminjamanAdapter);
        Log.d("apa gtu", "getAllData: "+peminjamanModelList.size());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<PeminjamanModel> newList = new ArrayList<>();
        for (PeminjamanModel peminjamanModel : peminjamanModelList) {
            String nama = peminjamanModel.getNama().toLowerCase();
            String judul_buku = peminjamanModel.getJudul_buku().toLowerCase();
            if (nama.contains(newText)) {
                newList.add(peminjamanModel);
            } else if (judul_buku.contains(newText)) {
                newList.add(peminjamanModel);
            }
        }

        peminjamanAdapter.setFilter(newList);
        return true;
    }
}
