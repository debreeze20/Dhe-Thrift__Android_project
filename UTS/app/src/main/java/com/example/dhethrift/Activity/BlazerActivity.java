package com.example.dhethrift.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dhethrift.R;

public class BlazerActivity extends AppCompatActivity {

    Spinner Urutkan, Kategori, Saring;

    public String menu_Sort [] = {"Sort", "Popular", "Higher-Lower", "Lower-Higher"};
    public String menu_Kategori [] = {"Category", "Blazer", "Dress", "Skirt", "Trouser"};
    public String menu_Filter [] = {"Filter", "Color", "Size", "Brand"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blazer);

        Urutkan = (Spinner) findViewById(R.id.sort);
        Kategori = (Spinner) findViewById(R.id.kategori);
        Saring = (Spinner) findViewById(R.id.filter);

        ArrayAdapter adapter_sort = new ArrayAdapter(BlazerActivity.this,R.layout.support_simple_spinner_dropdown_item, menu_Sort);
        ArrayAdapter adapter_kate = new ArrayAdapter(BlazerActivity.this,R.layout.support_simple_spinner_dropdown_item, menu_Kategori);
        ArrayAdapter adapter_filter = new ArrayAdapter(BlazerActivity.this,R.layout.support_simple_spinner_dropdown_item, menu_Filter);

        Urutkan.setAdapter(adapter_sort);
        Kategori.setAdapter(adapter_kate);
        Saring.setAdapter(adapter_filter);

        final TextView h1 = (TextView) findViewById(R.id.hargaBlazer01);
        h1.setText(Integer.toString(290000));

        final TextView h2 = (TextView) findViewById(R.id.hargaBlazer02);
        h2.setText(Integer.toString(599000));

    }

    public void Back(View view) {
        Intent intent = new Intent(BlazerActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void blazer01(View view) {
        Intent intent = new Intent(BlazerActivity.this,Blazer01Activity.class);
        startActivity(intent);
    }
}