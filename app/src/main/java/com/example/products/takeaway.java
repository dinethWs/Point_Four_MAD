package com.example.products;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class takeaway extends AppCompatActivity {

    EditText et_takename, et_takeemail, et_takephone;

    Button btn_takeplaceorder;
    DatabaseReference dbRef;
    takepay tp1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takeaway);

        Intent intent = getIntent();
        String priceTakeawayMessage = intent.getStringExtra(payment.priceTakeaway_message);
        TextView tv_takesubtot = (TextView) findViewById(R.id.tv_takesubtot);
        tv_takesubtot.setText(priceTakeawayMessage);

        et_takename = findViewById(R.id.et_takename);
        et_takeemail = findViewById(R.id.et_takeemail);
        et_takephone = findViewById(R.id.et_takephone);


        btn_takeplaceorder = findViewById(R.id.btn_takeplaceorder);

        tp1 = new takepay();
    }

    //method to clear all user inputs
    private void clearControls(){
        et_takename.setText("");
        et_takeemail.setText("");
        et_takephone.setText("");

    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_takeplaceorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertDetails();
            }
        });
    }

    public void insertDetails(){
        dbRef = FirebaseDatabase.getInstance().getReference().child("TakeawayPayment");

        try {
            if (TextUtils.isEmpty(et_takename.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please enter an ID", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(et_takeemail.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please enter name", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(et_takephone.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please enter address", Toast.LENGTH_SHORT).show();
            else {
                tp1.setName(et_takename.getText().toString().trim());
                tp1.setEmail(et_takeemail.getText().toString().trim());
                tp1.setConNo(Integer.parseInt(et_takephone.getText().toString().trim()));



                dbRef.push().setValue(tp1);

                Toast.makeText(getApplicationContext(),"Data saved successfully", Toast.LENGTH_SHORT).show();
                clearControls();
            }
        }
        catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalid contact number", Toast.LENGTH_SHORT).show();
        }
    }
}