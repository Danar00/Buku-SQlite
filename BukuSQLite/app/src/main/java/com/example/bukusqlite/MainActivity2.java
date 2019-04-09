package com.example.bukusqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bukusqlite.dbHelper.BukuHelper;
import com.example.bukusqlite.dbHelper.PeminjamanHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private EditText nama, judul_buku;
    private Button submit_button;
    private TextView addBuku;

    private PeminjamanAdapter peminjamanAdapter;
    private PeminjamanHelper peminjamanHelper;
    private RecyclerView recyclerView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pinjam_view);
        nama = findViewById(R.id.edit_nama);
        judul_buku = findViewById(R.id.edit_judul_buku);
        submit_button = (Button) findViewById(R.id.pinjam_button);
        addBuku = findViewById(R.id.tambahBuku);
        peminjamanHelper = new PeminjamanHelper(this);
        peminjamanAdapter = new PeminjamanAdapter(this);


        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (submit_button.getText().equals("update")){

                }else {
                    insertDataPinjam();
                    //getAllData();
                }
                Intent intent = new Intent(view.getContext(), PeminjamanActivity.class);
                startActivity(intent);
            }
        });

        addBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });



    }

    private void insertDataPinjam() {
        peminjamanHelper.open();
        PeminjamanModel buku = new PeminjamanModel(nama.getText().toString(), judul_buku.getText().toString());
        peminjamanHelper.insert(buku);
        peminjamanHelper.close();
    }



}
