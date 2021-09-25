package com.example.products;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ItemDelivery extends AppCompatActivity {

    TextView tv_delsub;

    String price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_delivery);

        tv_delsub = findViewById(R.id.tv_delsub);

        Intent intent = getIntent();
        String priceDeliveryMessage = intent.getStringExtra(payment.priceDelivery_message);
        TextView tv_delsub = (TextView) findViewById(R.id.tv_delsub);
        tv_delsub.setText(priceDeliveryMessage);
        

    }
}