package com.example.products;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_extra = findViewById(R.id.btn_extra);
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
    }
    public  void openItem1(){
        Intent intent = new Intent(this, Item1.class);
        startActivity(intent);

    }
}