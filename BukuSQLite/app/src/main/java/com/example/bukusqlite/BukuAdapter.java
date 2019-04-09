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
import android.widget.TextView;
import android.widget.Toast;

import com.example.bukusqlite.dbHelper.BukuHelper;

import java.util.ArrayList;

public class BukuAdapter extends RecyclerView.Adapter<BukuAdapter.CustomViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<BukuModel> buku;
    private Context context;
    private BukuHelper bukuHelper;

    public BukuAdapter(Context context){
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        bukuHelper = new BukuHelper(context);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.row_view, viewGroup, false);
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        final String judul = buku.get(position).getJudul();
        final String penulis = buku.get(position).getPenulis();
        final String penerbit = buku.get(position).getPenerbit();
        holder.text_judul.setText(judul);
        holder.edit_penulis.setText(penulis);
        holder.edit_penerbit.setText(penerbit);

        holder.button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buku.get(position).setJudul(holder.text_judul.getText().toString());
                buku.get(position).setPenulis(holder.edit_penulis.getText().toString());
                buku.get(position).setPenerbit(holder.edit_penerbit.getText().toString());

                bukuHelper.open();
                bukuHelper.update(buku.get(position));
                bukuHelper.close();
                Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteItem(buku.get(position).getId());
                buku.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    public void deleteItem(int id){
        bukuHelper.open();
        bukuHelper.delete(id);
        bukuHelper.close();
        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return buku.size();
    }

    public void addItem(ArrayList<BukuModel> mData){
        this.buku = mData;
        notifyDataSetChanged();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private EditText edit_penulis, edit_penerbit;
        private TextView text_judul;
        private Button button_update, button_delete;
        private CardView cv;

        public CustomViewHolder (View itemView){
            super(itemView);

            edit_penulis = itemView.findViewById(R.id.et_penulis);
            edit_penerbit = itemView.findViewById(R.id.et_penerbit);
            text_judul = itemView.findViewById(R.id.txt_judul);
            button_update = itemView.findViewById(R.id.btn_update);
            button_delete = itemView.findViewById(R.id.btn_delete);
            cv = itemView.findViewById(R.id.cv);
        }
    }

    public void setFilter(ArrayList<BukuModel> newList){
        buku = new ArrayList<>();
        buku.addAll(newList);
        notifyDataSetChanged();
    }

}
