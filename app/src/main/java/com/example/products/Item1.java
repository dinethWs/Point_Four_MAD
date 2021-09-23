package com.example.products;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Item1 extends AppCompatActivity {

    Button btn_purachse;
    Button btn_addToCart;

    public static final String name_message = "com.example.nameMessage";
    public static final String price_message = "com.example.priceMessage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item1);
        btn_purachse = findViewById(R.id.btn_purachse);
        btn_addToCart = findViewById(R.id.btn_addToCart);
    }
    @Override
    protected void onResume() {
        super.onResume();
        btn_purachse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPayment();
            }
        });
    }
    public  void openPayment(){
        Intent intent = new Intent(this, payment.class);
        startActivity(intent);

    }
    public void sendName(View view){
        Intent intent = new Intent(this, Cart.class);

        TextView tv_price1 = (TextView) findViewById(R.id.tv_price1);
        String priceMessage = tv_price1.getText().toString();
        intent.putExtra(price_message, priceMessage);
        startActivity(intent);

        TextView tv_Itemname1 = (TextView) findViewById(R.id.tv_Itemname1);
        String nameMessage = tv_Itemname1.getText().toString();
        intent.putExtra(name_message, nameMessage);
        startActivity(intent);

    }
}