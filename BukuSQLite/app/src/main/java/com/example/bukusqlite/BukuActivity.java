package com.example.bukusqlite;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.widget.SearchView;


import com.example.bukusqlite.dbHelper.BukuHelper;
import com.example.bukusqlite.MainActivity;

import java.util.ArrayList;

public class BukuActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    private EditText penulis, penerbit, judul;
    private Button update, delete;
    private BukuAdapter bukuAdapter;
    private BukuHelper bukuHelper;
    private ArrayList<BukuModel> bukuModelsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_book_view);

        recyclerView = findViewById(R.id.recyclerView);
        penulis = findViewById(R.id.edit_penulis);
        penerbit = findViewById(R.id.edit_penerbit);
        judul = findViewById(R.id.edit_judul);
        update = findViewById(R.id.btn_update);
        delete = findViewById(R.id.btn_delete);

        bukuHelper = new BukuHelper(this);
        bukuAdapter = new BukuAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getAllData();

    }

    private void getAllData() {
        bukuHelper.open();
        // Ambil semua data buku di database
        bukuModelsList = bukuHelper.getAllData();
        bukuHelper.close();
        bukuAdapter.addItem(bukuModelsList);
        recyclerView.setAdapter(bukuAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
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
        ArrayList<BukuModel> newList = new ArrayList<>();
        for (BukuModel bukuModel : bukuModelsList ){
            String judul = bukuModel.getJudul().toLowerCase();
            String penulis = bukuModel.getPenulis().toLowerCase();
            String penerbit = bukuModel.getPenerbit().toLowerCase();
            if (judul.contains(newText)){
                newList.add(bukuModel);
            }else if(penulis.contains(newText)){
                newList.add(bukuModel);
            }else if(penerbit.contains(newText)){
                newList.add(bukuModel);
            }
        }

        bukuAdapter.setFilter(newList);
        return true;
    }
}
