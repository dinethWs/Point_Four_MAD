package com.example.products;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }

    public void changeFragment(View view){
        Fragment fragment;
        if (view == findViewById(R.id.btn_takeaway)){
            fragment = new takeaway();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_Default,fragment);
            ft.commit();
        }
        if (view == findViewById(R.id.btn_delivery)){
            fragment = new delivery();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_Default,fragment);
            ft.commit();
        }
    }
}