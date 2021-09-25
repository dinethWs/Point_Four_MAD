package com.example.products;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class addedItems extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore db;
    private ItemAdapter itemAdapter;
    private List<ItemModel> iList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_added_items);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        iList = new ArrayList<>();
        itemAdapter = new ItemAdapter(this, iList);
        recyclerView.setAdapter(itemAdapter);

        ItemTouchHelper touchHelper1 = new ItemTouchHelper(new TouchHelperI(itemAdapter));
        touchHelper1.attachToRecyclerView(recyclerView);

        showItemData();

    }

    public void showItemData(){
        db.collection("Documents").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        iList.clear();
                        for(DocumentSnapshot snapshot : task.getResult()){
                            ItemModel imodel = new ItemModel(snapshot.getString("id"), snapshot.getString("title"), snapshot.getString("price"), snapshot.getString("desc"));
                            iList.add(imodel);
                        }
                        itemAdapter.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(addedItems.this, "Something went wrong..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}











