package com.example.dhethrift.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dhethrift.R;

public class CartActivity extends AppCompatActivity {

    public TextView judul, harga, ttl;
    int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        judul = findViewById(R.id.judulProduk);
        harga = findViewById(R.id.hargaProduk);
        ttl = findViewById(R.id.totalHarga);

        judul.setText("D & GABANA Balzer");
        harga.setText(Integer.toString(290000));
        ttl.setText(Integer.toString(290000));
    }

    public void addAgain(View view) {

        int i = Integer.parseInt(harga.getText().toString());
        int j = Integer.parseInt(ttl.getText().toString());
        total = j+i;
        ttl.setText(Integer.toString(total));
    }

    public void Back(View view) {
        Intent intent = new Intent(CartActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void delete(View view) {
    }

    public void checkout(View view) {
        Intent intent = new Intent(CartActivity.this,CheckoutActivity.class);
        startActivity(intent);
    }
}