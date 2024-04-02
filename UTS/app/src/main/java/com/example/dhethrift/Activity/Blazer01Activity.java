package com.example.dhethrift.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dhethrift.R;

public class Blazer01Activity extends AppCompatActivity {

    int angka = 0;
    TextView kounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blazer01);

        final TextView h1 = (TextView) findViewById(R.id.dgabanaBlazer);
        final TextView h2 = (TextView) findViewById(R.id.hargaBlazer01);
        final TextView h3 = (TextView) findViewById(R.id.kategori);
        final TextView h4 = (TextView) findViewById(R.id.merk);
        h1.setText("D & GABANA Blazer");
        h2.setText(Integer.toString(290000));
        h3.setText("Blazer");
        h4.setText("D & GABANA");

        kounter = findViewById(R.id.kounter);
    }

    public void Back(View view) {

    }

    public void tombolTambah(View view) {
        angka = angka+1;
        kounter.setText(Integer.toString(angka));
    }

    public void lihatKeranjang(View view) {
        Intent intent = new Intent(Blazer01Activity.this,CartActivity.class);
        startActivity(intent);
    }
}