package com.example.products;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;


public class Activity3 extends AppCompatActivity {

    private Button back3;
    //private Button book3;
    private EditText mname3 ,mPhone3 ,maddress3 ,mDate3 ,mTime3 ;
    private Button mbook3;
    private FirebaseFirestore DB;
    private String vid, vname, vPhone,vaddress, vDate,vTime;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        mname3 = findViewById(R.id.name3);
        mPhone3 =findViewById(R.id.Phone3);
        maddress3=findViewById(R.id.address3);
        mDate3=findViewById(R.id.Date3);
        mTime3=findViewById(R.id.Time3);
        mbook3=findViewById(R.id.book3);

        DB= FirebaseFirestore.getInstance();

        //update
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            mbook3.setText("Update");

            vid=bundle.getString("vid");
            vname=bundle.getString("vname");
            vPhone=bundle.getString("vPhone");
            vaddress=bundle.getString("vaddress");
            vDate=bundle.getString("vDate");
            vTime=bundle.getString("vTime");

            mname3.setText(vname);
            mPhone3.setText(vPhone);
            maddress3.setText(vaddress);
            mDate3.setText(vDate);
            mTime3.setText(vTime);

        }else{
            mbook3.setText("Book");
        }

        mbook3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=mname3.getText().toString();
                String Phone=mPhone3.getText().toString();
                String address=maddress3.getText().toString();
                String Date=mDate3.getText().toString();
                String Time=mTime3.getText().toString();

                //update
                Bundle bundle1=getIntent().getExtras();
                if (bundle1 !=null){
                    String id=vid;
                    updateToFireStore(id,name,Phone,address,Date,Time);

                }else{
                    String id = UUID.randomUUID().toString();
                    saveToFireStore(name,Phone,address,Date,Time,id);

                }

            }
        });


        //back button
        back3 =(Button) findViewById(R.id.back3);
        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });



    }
    public void openActivity1(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }



    //update
    private void updateToFireStore(String id, String name, String Phone,String address, String Date, String Time){
        DB.collection("VisitingBookings").document(id).update("name",name,"Phone",Phone,"address",address,"Date",Date,"Time",Time)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Activity3.this,"Data updated!",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Activity3.this, "Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Activity3.this,e.getMessage() ,Toast.LENGTH_SHORT).show();
            }
        });
    }
//update finished



    //save
    private void saveToFireStore(String name, String Phone, String address, String Date, String Time, String id){

        if(!name.isEmpty() && !Phone.isEmpty() && !address.isEmpty() && !Date.isEmpty() && !Time.isEmpty()){
            HashMap<String, Object> map=new HashMap<>();
            map.put("id",id);
            map.put("name",name);
            map.put("Phone",Phone);
            map.put("address",address);
            map.put("Date",Date);
            map.put("Time",Time);

            DB.collection("VisitingBookings").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Activity3.this,"Date saved",Toast.LENGTH_SHORT).show();

                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Activity3.this, "Failed !", Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }

}
