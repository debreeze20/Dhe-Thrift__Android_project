package com.example.dhethrift.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dhethrift.API.APIRequestData;
import com.example.dhethrift.API.RetroServer;
import com.example.dhethrift.Model.ResponseModel;
import com.example.dhethrift.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahData extends AppCompatActivity {
    private EditText etNama, etHarga, etKategori, etMerk, etStok, etKondisi;
    private Button btnSimpan;
    private String nama, kategori, merk, kondisi;
    private int harga, stok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        etNama = findViewById(R.id.et_Nama);
        etHarga = findViewById(R.id.et_Harga);
        etKategori = findViewById(R.id.et_Kategori);
        etMerk = findViewById(R.id.et_Merk);
        etStok = findViewById(R.id.et_Stok);
        etKondisi = findViewById(R.id.et_Kondisi);
        btnSimpan = findViewById(R.id.btn_Simpan);

        
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama = etNama.getText().toString();
                harga = Integer.parseInt(etHarga.getText().toString());
                kategori = etKategori.getText().toString();
                merk = etMerk.getText().toString();
                stok = Integer.parseInt(etStok.getText().toString());
                kondisi = etKondisi.getText().toString();

                if (nama.trim().equals("")) {
                    etNama.setError("Nama Harus Diisi");
                } else if (String.valueOf(harga).trim().equals("")) {
                    etHarga.setError("Harga Harus Diisi");
                } else if (kategori.trim().equals("")) {
                    etKategori.setError("Kategori Harus Diisi");
                } else if (String.valueOf(stok).trim().equals("")) {
                    etStok.setError("Stok Harus Diisi");
                } else if (kondisi.trim().equals("")) {
                    etKondisi.setError("Kondisi Harus Diisi");
                } else {
                    CreateData();
                }
            }

            public void CreateData() {
                APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
                Call<ResponseModel> simpanData = ardData.ardCreateData(nama, harga, kategori, merk, stok, kondisi);
                simpanData.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        int kode = response.body().getKode();
                        String pesan = response.body().getPesan();

                        Toast.makeText(TambahData.this, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(TambahData.this, "Gagal Menghubungkan Server"+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}