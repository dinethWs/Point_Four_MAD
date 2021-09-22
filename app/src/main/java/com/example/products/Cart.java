package com.example.products;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Cart extends AppCompatActivity {
//abcd
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        Intent intent = getIntent();
        String nameMessage = intent.getStringExtra(Item1.name_message);
        TextView tv_item1 = (TextView) findViewById(R.id.tv_item1);
        tv_item1.setText(nameMessage);

        String priceMessage = intent.getStringExtra(Item1.price_message);
        TextView tv_p1 = (TextView) findViewById(R.id.tv_p1);
        tv_p1.setText(priceMessage);

    }
}