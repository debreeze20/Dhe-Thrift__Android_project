package com.example.dhethrift.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dhethrift.API.APIRequestData;
import com.example.dhethrift.API.RetroServer;
import com.example.dhethrift.Adapter.AdapterData;
import com.example.dhethrift.Model.DataModel;
import com.example.dhethrift.Model.ResponseModel;
import com.example.dhethrift.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatabaseActivity extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;

    private List<DataModel> listData = new ArrayList<>();

    private SwipeRefreshLayout srlData;
    private ProgressBar pbData;

    private FloatingActionButton fabTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        rvData = findViewById(R.id.rv_data);
        srlData = findViewById(R.id.srl_data);
        pbData = findViewById(R.id.pb_data);
        fabTambah = findViewById(R.id.fab_tambah);

        lmData = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvData.setLayoutManager(lmData);
        retrieveData();
        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                retrieveData();
                srlData.setRefreshing(false);
            }
        });

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DatabaseActivity.this,TambahData.class));
            }
        });
    }

    public void retrieveData() {
        pbData.setVisibility(View.VISIBLE);
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData = ardData.ardRetrieveData();
        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(DatabaseActivity.this, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();

                listData = response.body().getData();
                adData = new AdapterData(DatabaseActivity.this,listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
                pbData.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(DatabaseActivity.this, "Gagal Terhubung ke Server"+t.getMessage(), Toast.LENGTH_SHORT).show();
                pbData.setVisibility(View.INVISIBLE);
            }
        });
    }
}