package com.example.products;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Cart extends AppCompatActivity {

    Button btn_cont;
    Button btn_shopping;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        btn_cont = findViewById(R.id.btn_cont);
        btn_shopping = findViewById(R.id.btn_shopping);

        Intent intent = getIntent();
        //item1
        String nameMessage = intent.getStringExtra(Item1.name_message);
        TextView tv_item1 = (TextView) findViewById(R.id.tv_item1);
        tv_item1.setText(nameMessage);

        String priceMessage = intent.getStringExtra(Item1.price_message);
        TextView tv_p1 = (TextView) findViewById(R.id.tv_p1);
        tv_p1.setText(priceMessage);

        //Item2
        String name2Message = intent.getStringExtra(Item2.name2_message);
        TextView tv_item2 = (TextView) findViewById(R.id.tv_item2);
        tv_item2.setText(name2Message);

        String price2Message = intent.getStringExtra(Item2.price2_message);
        TextView tv_p2 = (TextView) findViewById(R.id.tv_p2);
        tv_p2.setText(price2Message);

        //Sub Total
        TextView tv_p4 = (TextView) findViewById(R.id.tv_p4);
        tv_p4.setText(priceMessage);

        //discounted total
        TextView tv_disSubTotal = (TextView) findViewById(R.id.tv_disSubTotal);
        tv_disSubTotal.setText(priceMessage);

    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPaymentInCart();
            }
        });
        btn_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });

    }

    public void openPaymentInCart(){
        Intent intent = new Intent(this, payment.class);
        startActivity(intent);
    }
    public void backToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}