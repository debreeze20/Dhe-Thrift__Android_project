package com.example.dhethrift.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dhethrift.R;

public class TrouserActivity extends AppCompatActivity {

    Spinner Urutkan, Kategori, Saring;

    public String menu_Sort [] = {"Sort", "Popular", "Higher-Lower", "Lower-Higher"};
    public String menu_Kategori [] = {"Category", "Blazer", "Dress", "Skirt", "Trouser"};
    public String menu_Filter [] = {"Filter", "Color", "Size", "Brand"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trouser);

        Urutkan = (Spinner) findViewById(R.id.sort);
        Kategori = (Spinner) findViewById(R.id.kategori);
        Saring = (Spinner) findViewById(R.id.filter);

        ArrayAdapter adapter_sort = new ArrayAdapter(TrouserActivity.this,R.layout.support_simple_spinner_dropdown_item, menu_Sort);
        ArrayAdapter adapter_kate = new ArrayAdapter(TrouserActivity.this,R.layout.support_simple_spinner_dropdown_item, menu_Kategori);
        ArrayAdapter adapter_filter = new ArrayAdapter(TrouserActivity.this,R.layout.support_simple_spinner_dropdown_item, menu_Filter);

        Urutkan.setAdapter(adapter_sort);
        Kategori.setAdapter(adapter_kate);
        Saring.setAdapter(adapter_filter);

        final TextView h1 = (TextView) findViewById(R.id.hargaTrouser01);
        h1.setText(Integer.toString(199000));

    }

    public void Back(View view) {
        Intent intent = new Intent(TrouserActivity.this,MainActivity.class);
        startActivity(intent);
    }
}