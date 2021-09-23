package com.example.products;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Item2 extends AppCompatActivity {

    Button btn_IPurchase2;
    Button btn_Icart2;

    public static final String name2_message = "com.example.name2Message";
    public static final String price2_message = "com.example.price2Message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item2);
        btn_IPurchase2 = findViewById(R.id.btn_IPurchase2);
        btn_Icart2 = findViewById(R.id.btn_Icart2);
    }
    @Override
    protected void onResume() {
        super.onResume();
        btn_IPurchase2.setOnClickListener(new View.OnClickListener() {
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
    public void sendItem2(View view){
        Intent intent = new Intent(this, Cart.class);

        TextView tv_Itemprice2 = (TextView) findViewById(R.id.tv_Itemprice2);
        String price2Message = tv_Itemprice2.getText().toString();
        intent.putExtra(price2_message, price2Message);
        startActivity(intent);

        TextView tv_ItemName2 = (TextView) findViewById(R.id.tv_ItemName2);
        String name2Message = tv_ItemName2.getText().toString();
        intent.putExtra(name2_message, name2Message);
        startActivity(intent);

    }
}