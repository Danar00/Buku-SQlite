package com.example.bukusqlite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bukusqlite.dbHelper.PeminjamanHelper;

import java.util.ArrayList;

public class PeminjamanAdapter extends RecyclerView.Adapter<PeminjamanAdapter.CustomViewHolder>{

    private LayoutInflater mInflater;
    private ArrayList<PeminjamanModel> pinjam;
    private Context context;
    private PeminjamanHelper peminjamanHelper;

    public PeminjamanAdapter(Context context){
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        peminjamanHelper = new PeminjamanHelper(context);
    }

    @NonNull
    @Override
    public PeminjamanAdapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.row_view_peminjaman, viewGroup, false);
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        final String nama = pinjam.get(position).getNama();
        final String judul_buku = pinjam.get(position).getJudul_buku();
        holder.edit_nama.setText(nama);
        holder.edit_judul_buku.setText(judul_buku);


        holder.button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pinjam.get(position).setNama(holder.edit_nama.getText().toString());
                pinjam.get(position).setJudul_buku(holder.edit_judul_buku.getText().toString());


                peminjamanHelper.open();
                peminjamanHelper.update(pinjam.get(position));
                peminjamanHelper.close();
                Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteItem(pinjam.get(position).getId());
                pinjam.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    public void deleteItem(int id){
        peminjamanHelper.open();
        peminjamanHelper.delete(id);
        peminjamanHelper.close();
        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return pinjam.size();
    }

    public void addItem(ArrayList<PeminjamanModel> mData){
        this.pinjam = mData;
        notifyDataSetChanged();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private EditText edit_nama, edit_judul_buku;

        private Button button_update, button_delete;
        private CardView cv;

        public CustomViewHolder (View itemView){
            super(itemView);

            edit_nama = itemView.findViewById(R.id.et_nama);
            edit_judul_buku = itemView.findViewById(R.id.et_judul_buku);


            button_update = itemView.findViewById(R.id.btn_update);
            button_delete = itemView.findViewById(R.id.btn_delete);
            cv = itemView.findViewById(R.id.cv);
        }
    }

    public void setFilter(ArrayList<PeminjamanModel> newList){
        pinjam = new ArrayList<>();
        pinjam.addAll(newList);
        notifyDataSetChanged();
    }
}
