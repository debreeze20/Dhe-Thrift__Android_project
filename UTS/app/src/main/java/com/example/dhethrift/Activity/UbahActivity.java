package com.example.dhethrift.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class UbahActivity extends AppCompatActivity {
    private int xId, xHarga, xStok;
    private String xNama, xKategori, xMerk, xKondisi;
    private EditText etNama, etHarga, etKategori, etMerk, etStok, etKondisi;
    private Button btnUbah;
    private int yHarga, yStok;
    private String yNama, yKategori, yMerk, yKondisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        Intent terima = getIntent();
        xId = terima.getIntExtra("xId",-1);
        xNama = terima.getStringExtra("xNama");
        xHarga = terima.getIntExtra("xHarga",-1);
        xKategori = terima.getStringExtra("xKategori");
        xMerk = terima.getStringExtra("xMerk");
        xStok = terima.getIntExtra("xStok",-1);
        xKondisi = terima.getStringExtra("xKondisi");

        etNama = findViewById(R.id.et_Nama);
        etHarga = findViewById(R.id.et_Harga);
        etKategori = findViewById(R.id.et_Kategori);
        etMerk = findViewById(R.id.et_Merk);
        etStok = findViewById(R.id.et_Stok);
        etKondisi = findViewById(R.id.et_Kondisi);
        btnUbah = findViewById(R.id.btn_Ubah);

        etNama.setText((xNama));
        etHarga.setText(String.valueOf(xHarga));
        etKategori.setText(xKategori);
        etMerk.setText(xMerk);
        etStok.setText(String.valueOf(xStok));
        etKondisi.setText(xKondisi);
        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yNama = etNama.getText().toString();
                yHarga = Integer.parseInt(etHarga.getText().toString());
                yKategori = etKategori.getText().toString();
                yMerk = etMerk.getText().toString();
                yStok = Integer.parseInt(etStok.getText().toString());
                yKondisi = etKondisi.getText().toString();

                updateData();
            }

            private void updateData() {
                APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
                Call<ResponseModel> ubahData = ardData.ardUpdateData(xId, yNama, yHarga, yKategori, yMerk, yStok, yKondisi);
                ubahData.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        int kode = response.body().getKode();
                        String pesan = response.body().getPesan();

                        Toast.makeText(UbahActivity.this, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(UbahActivity.this, "Gagal Menghubungkan Server"+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}