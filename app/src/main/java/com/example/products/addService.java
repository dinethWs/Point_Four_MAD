package com.example.products;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

public class addService extends AppCompatActivity {

    private EditText mService, mDescription;
    private Button mSaveServiceBtn, mShowALlServiceBtn;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        mService = findViewById(R.id.edit_service);
        mDescription = findViewById(R.id.edit_descrip);
        mSaveServiceBtn = findViewById(R.id.saveService_btn);
        mShowALlServiceBtn = findViewById(R.id.showAllService_btn);

        db = FirebaseFirestore.getInstance();

        mSaveServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String service = mService.getText().toString();
                String description = mDescription.getText().toString();
                String id = UUID.randomUUID().toString();

                saveToFireStore(id, service, description);
            }
        });
    }
    private void saveToFireStore(String id, String service, String description){
        if(!service.isEmpty() && !description.isEmpty()){
            HashMap<String, Object> map = new HashMap<>();
            map.put("id" , id);
            map.put("service", service);
            map.put("description", description);

            db.collection("DocumentServices").document(id).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull  Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(addService.this, "Data Saved!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull  Exception e) {
                    Toast.makeText(addService.this, "Failed!!", Toast.LENGTH_SHORT).show();
                }
            });

        }else
            Toast.makeText(this,"Empty Fields not Allowed",Toast.LENGTH_SHORT).show();
    }
}