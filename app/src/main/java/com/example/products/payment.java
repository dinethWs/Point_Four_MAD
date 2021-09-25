package com.example.products;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class payment extends AppCompatActivity {

    Button btn_take;
    Button btn_deli;
    TextView tv_eee;

    public static final String priceTakeaway_message = "com.example.priceTakeawayMessage";
    public static final String priceDelivery_message = "com.example.priceDeliveryMessage";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        btn_take = findViewById(R.id.btn_take);
        btn_deli = findViewById(R.id.btn_deli);
        tv_eee = findViewById(R.id.tv_eee);

        Intent intent = getIntent();
        String pricePayMessage = intent.getStringExtra(Item1.pricePay_message);
        TextView tv_eee = (TextView) findViewById(R.id.tv_eee);
        tv_eee.setText(pricePayMessage);
    }

    public void sendToTakeaway(View view){
        Intent intent = new Intent(this, takeaway.class);

        TextView tv_eee = (TextView) findViewById(R.id.tv_eee);
        String priceTakeawayMessage = tv_eee.getText().toString();
        intent.putExtra(priceTakeaway_message, priceTakeawayMessage);
        startActivity(intent);

    }
    public void sendToDelivery(View view){
        Intent intent = new Intent(this, ItemDelivery.class);

        TextView tv_eee = (TextView) findViewById(R.id.tv_eee);
        String priceDeliveryMessage = tv_eee.getText().toString();
        intent.putExtra(priceDelivery_message, priceDeliveryMessage);


        startActivity(intent);


    }
}