package com.example.dhethrift.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dhethrift.R;

public class OrderedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered);
    }

    public void Back(View view) {
        Intent intent = new Intent(OrderedActivity.this,MainActivity.class);
        startActivity(intent);
    }
}