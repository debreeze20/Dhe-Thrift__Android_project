package com.example.dhethrift.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dhethrift.R;

public class CheckoutActivity extends AppCompatActivity {

    ListView lV;
    public TextView checkout;

    public String menu_ekspedisi [] ={"JNE", "J&T", "SiCepat", "Ninja Express", "POS Indonesia", "GRAB Same Day", "Gojek Next Day"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        lV = (ListView) findViewById(R.id.ekspedisi);
        checkout = findViewById(R.id.hrgCO);

        checkout.setText(Integer.toString(580000));

        ArrayAdapter adapter = new ArrayAdapter(CheckoutActivity.this,R.layout.support_simple_spinner_dropdown_item,menu_ekspedisi);

        lV.setAdapter(adapter);
    }

    public void Back(View view) {
        Intent intent = new Intent(CheckoutActivity.this,CartActivity.class);
        startActivity(intent);
    }

    public void order(View view) {
        Intent intent = new Intent(CheckoutActivity.this,OrderedActivity.class);
        startActivity(intent);
    }
}