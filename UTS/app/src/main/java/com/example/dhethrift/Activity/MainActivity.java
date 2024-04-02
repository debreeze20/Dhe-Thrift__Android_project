package com.example.dhethrift.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dhethrift.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dressAksi(View view) {
        Intent intent = new Intent(MainActivity.this,DressActivity.class);
        startActivity(intent);
    }

    public void blazerAksi(View view) {
        Intent intent = new Intent(MainActivity.this,BlazerActivity.class);
        startActivity(intent);
    }

    public void skirtAksi(View view) {
        Intent intent = new Intent(MainActivity.this,SkirtActivity.class);
        startActivity(intent);
    }

    public void trouserAksi(View view) {
        Intent intent = new Intent(MainActivity.this,TrouserActivity.class);
        startActivity(intent);
    }

    public void fab_buka(View view) {
        Intent intent = new Intent(MainActivity.this,DatabaseActivity.class);
        startActivity(intent);
    }
}