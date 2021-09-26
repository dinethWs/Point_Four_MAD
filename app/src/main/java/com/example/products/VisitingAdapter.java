package com.example.products;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
public class VisitingAdapter extends RecyclerView.Adapter<VisitingAdapter.MyVisitingHolder> {

    private Activity5 activity5;
    private List<VisitingModel> mlist;
    private FirebaseFirestore DB= FirebaseFirestore.getInstance();


    public VisitingAdapter(Activity5 activity5 , List<VisitingModel> mlist){
        this.activity5=activity5;
        this.mlist=mlist;
    }

    //update
    public void updateVisitings(int position){
        VisitingModel visitings = mlist.get(position);
        Bundle bundle=new Bundle();
        bundle.putString("vid",visitings.getId());
        bundle.putString("vname",visitings.getName());
        bundle.putString("vPhone",visitings.getPhone());
        bundle.putString("vaddress",visitings.getAddress());
        bundle.putString("vDate",visitings.getDate());
        bundle.putString("vTime",visitings.getTime());

        Intent intent=new Intent(activity5, Activity3.class);
        intent.putExtras(bundle);
        activity5.startActivity(intent);


    }
//update finished

    //delete data
    public void deleteVisitings(int position){
        VisitingModel visitings =mlist.get(position);
        DB.collection("VisitingBookings").document(visitings.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            notifyRemoved(position);
                            Toast.makeText(activity5, "Visit Booking Deleted!", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(activity5, "Error "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void notifyRemoved(int position){
        mlist.remove(position);
        notifyItemRemoved(position);
        activity5.showVisitings();
    }
//delete finished





    @NonNull
    @Override
    public VisitingAdapter.MyVisitingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(activity5).inflate(R.layout.visitings,parent,false);
        return new VisitingAdapter.MyVisitingHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitingAdapter.MyVisitingHolder holder, int position) {
        holder.name.setText(mlist.get(position).getName());
        holder.Phone.setText(mlist.get(position).getPhone());
        holder.address.setText(mlist.get(position).getAddress());
        holder.Date.setText(mlist.get(position).getDate());
        holder.Time.setText(mlist.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class MyVisitingHolder extends RecyclerView.ViewHolder{

        TextView name,Phone,address,Date,Time;

        public MyVisitingHolder(@NonNull View visitingsView) {
            super(visitingsView);

            name=visitingsView.findViewById(R.id.name3);
            Phone=visitingsView.findViewById(R.id.Phone3);
            address=visitingsView.findViewById(R.id.address3);
            Date=visitingsView.findViewById(R.id.Date3);
            Time=visitingsView.findViewById(R.id.Time3);

        }
    }


}

