package com.example.products;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class Activity5 extends AppCompatActivity {

    private Button new5;
    private RecyclerView recyclerView;
    private FirebaseFirestore DB;
    private VisitingAdapter visitingAdapter;
    private List<VisitingModel> visitinglist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        recyclerView=findViewById(R.id.recycleview5);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DB= FirebaseFirestore.getInstance();
        visitinglist=new ArrayList<>();
        visitingAdapter=new VisitingAdapter(this,visitinglist);
        recyclerView.setAdapter(visitingAdapter);

        //update
        ItemTouchHelper touchHelper = new ItemTouchHelper(new TouchHelperVisiting(visitingAdapter));
        touchHelper.attachToRecyclerView(recyclerView);

     //   showVisitings();



        new5 =(Button) findViewById(R.id.new5);
        new5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity001();
            }
        });


    }

    public void openActivity001(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }


    public void showVisitings(){

        DB.collection("VisitingBookings").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        visitinglist.clear();
                        for (DocumentSnapshot snapshot : task.getResult()){

                            VisitingModel visitingModel=new VisitingModel(snapshot.getString("id"),snapshot.getString("name"),
                                    snapshot.getString("address"),snapshot.getString("Phone"),snapshot.getString("Date"), snapshot.getString("Time"));

                            visitinglist.add(visitingModel);

                        }
                        visitingAdapter.notifyDataSetChanged();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Activity5.this, "Oops!! something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });

    }


}