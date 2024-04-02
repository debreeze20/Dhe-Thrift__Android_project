package com.example.dhethrift.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dhethrift.API.APIRequestData;
import com.example.dhethrift.API.RetroServer;
import com.example.dhethrift.Activity.DatabaseActivity;
import com.example.dhethrift.Activity.UbahActivity;
import com.example.dhethrift.Model.DataModel;
import com.example.dhethrift.Model.ResponseModel;
import com.example.dhethrift.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<DataModel> listdata;
    private int idProduk;
    private List<DataModel> listProduk;

    public AdapterData(Context ctx, List<DataModel> listdata) {
        this.ctx = ctx;
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listdata.get(position);
        holder.tvId.setText(String.valueOf(dm.getId()));
        holder.tvNama.setText(dm.getNama());
        holder.tvHarga.setText(String.valueOf(dm.getHarga()));
        holder.tvKategori.setText(dm.getKategori());
        holder.tvMerk.setText(dm.getMerk());
        holder.tvStok.setText(String.valueOf(dm.getStok()));
        holder.tvKondisi.setText(dm.getKondisi());
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvId, tvNama, tvHarga, tvKategori, tvMerk, tvStok, tvKondisi;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvHarga = itemView.findViewById(R.id.tv_harga);
            tvKategori = itemView.findViewById(R.id.tv_kategori);
            tvMerk = itemView.findViewById(R.id.tv_merk);
            tvStok = itemView.findViewById(R.id.tv_stok);
            tvKondisi = itemView.findViewById(R.id.tv_kondisi);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                    dialogPesan.setMessage("Pilih Operasi yang Akan dilakukan");
                    dialogPesan.setCancelable(true);
                    idProduk = Integer.parseInt(tvId.getText().toString());

                    dialogPesan.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            deleteData();
                            dialogInterface.dismiss();
                            ((DatabaseActivity) ctx).retrieveData();
                        }

                        private void deleteData() {
                            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
                            Call<ResponseModel> hapusData = ardData.ardDeleteData(idProduk);
                            hapusData.enqueue(new Callback<ResponseModel>() {
                                @Override
                                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                    int kode = response.body().getKode();
                                    String pesan = response.body().getPesan();

                                    Toast.makeText(ctx, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<ResponseModel> call, Throwable t) {
                                    Toast.makeText(ctx, "Gagal Menghubungkan Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                    dialogPesan.setNegativeButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getData();
                            dialogInterface.dismiss();
                        }
                    });

                    dialogPesan.show();
                    return false;
                }
            });
        }

        private void getData() {
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> ambilData = ardData.ardGetData(idProduk);
            ambilData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    listProduk = response.body().getData();

                    int varIdProduk = listProduk.get(0).getId();
                    String varNamaProduk = listProduk.get(0).getNama();
                    int varHargaProduk = listProduk.get(0).getHarga();
                    String varKategoriProduk = listProduk.get(0).getKategori();
                    String varMerkProduk = listProduk.get(0).getMerk();
                    int varStokProduk = listProduk.get(0).getStok();
                    String varKondisiProduk = listProduk.get(0).getKondisi();

                    Intent kirim = new Intent(ctx, UbahActivity.class);
                    kirim.putExtra("xId",varIdProduk);
                    kirim.putExtra("xNama",varNamaProduk);
                    kirim.putExtra("xHarga",varHargaProduk);
                    kirim.putExtra("xKategori",varKategoriProduk);
                    kirim.putExtra("xMerk",varMerkProduk);
                    kirim.putExtra("xStok",varStokProduk);
                    kirim.putExtra("xKondisi",varKondisiProduk);
                    ctx.startActivity(kirim);

                    Toast.makeText(ctx, "Kode : "+kode+" | Pesan : "+pesan+" | "+varNamaProduk, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungkan Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
