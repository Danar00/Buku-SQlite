package com.example.bukusqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bukusqlite.dbHelper.BukuHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText judul, penulis, penerbit;
    private Button submit;

    private BukuAdapter bukuAdapter;
    private BukuHelper bukuHelper;
    private RecyclerView recyclerView;

    private TextView show_pinjam;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_view);

        judul = findViewById(R.id.edit_judul);
        penulis = findViewById(R.id.edit_penulis);
        penerbit = findViewById(R.id.edit_penerbit);
        show_pinjam = findViewById(R.id.showPeminjaman);

        submit = (Button) findViewById(R.id.register_button);
        bukuHelper = new BukuHelper(this);
        bukuAdapter = new BukuAdapter(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (submit.getText().equals("update")){

                }else {
                    insertData();
                    //getAllData();
                }

                Intent intent = new Intent(view.getContext(), BukuActivity.class);
                startActivity(intent);
            }
        });

        show_pinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), MainActivity2.class);
                startActivity(intent);
            }
        });




    }

    public void insertData() {
        bukuHelper.open();
        BukuModel buku = new BukuModel(judul.getText().toString(), penulis.getText().toString(), penerbit.getText().toString());
        bukuHelper.insert(buku);
        bukuHelper.close();
    }

//    public void getAllData() {
//        bukuHelper.open();
//        // Ambil semua data mahasiswa di database
//        ArrayList<BukuModel> bukuModels = bukuHelper.getAllData();
//        bukuHelper.close();
//        bukuAdapter.addItem(bukuModels);
//        recyclerView.setAdapter(bukuAdapter);
//    }
}
