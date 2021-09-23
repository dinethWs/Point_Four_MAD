package com.example.products;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class payment extends AppCompatActivity {

    Button btn_take;
    Button btn_deli;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        btn_take = findViewById(R.id.btn_take);
        btn_deli = findViewById(R.id.btn_deli);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTake();
            }
        });
        btn_deli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeli();
            }
        });
    }
    public void openTake(){
        Intent intent = new Intent(this, takeaway.class);
        startActivity(intent);
    }
    public void openDeli(){
        Intent intent = new Intent(this, ItemDelivery.class);
        startActivity(intent);
    }
}