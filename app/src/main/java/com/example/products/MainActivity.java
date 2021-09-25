package com.example.products;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    Button btn_extra;
    Button btn_facial;
    ImageButton imgBtn_set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_extra = findViewById(R.id.btn_extra);
        btn_facial = findViewById(R.id.btn_facial);
        imgBtn_set = findViewById(R.id.imgBtn_set);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_extra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItem1();
            }
        });
        btn_facial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItem2();
            }
        });
        imgBtn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdmin();
            }
        });
    }
    public  void openItem1(){
        Intent intent = new Intent(this, Item1.class);
        startActivity(intent);
    }
    public  void openItem2(){
        Intent intent = new Intent(this, Item2.class);
        startActivity(intent);
    }
    public  void openAdmin(){
        Intent intent = new Intent(this, addItem.class);
        startActivity(intent);
    }
}