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

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    private addedItems iActivity;
    private List<ItemModel> iMList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ItemAdapter(addedItems iActivity, List<ItemModel> iMList){
        this.iActivity = iActivity;
        this.iMList = iMList;
    }
    //pass data to addItem activity
    public void updateIData(int position){
        ItemModel item = iMList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("uId", item.getId());
        bundle.putString("uTitle", item.getTitle());
        bundle.putString("uPrice", item.getPrice());
        bundle.putString("uDesc", item.getDesc());
        Intent intent = new Intent(iActivity, addItem.class);
        intent.putExtras(bundle);
        iActivity.startActivity(intent);
    }

    public void deleteData(int position){
        ItemModel item = iMList.get(position);
        db.collection("Documents").document(item.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        notifyRemoved1(position);
                        if(task.isSuccessful()){
                            Toast.makeText(iActivity, "Data Deleted!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(iActivity, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void notifyRemoved1(int position){
        iMList.remove(position);
        notifyItemRemoved(position);
        iActivity.showItemData();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(iActivity).inflate(R.layout.productitem, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
        holder.title.setText(iMList.get(position).getTitle());
        holder.price.setText(iMList.get(position).getPrice());
        holder.desc.setText(iMList.get(position).getDesc());


    }

    @Override
    public int getItemCount() {
        return iMList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView title, price ,desc;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_text);
            price = itemView.findViewById(R.id.price_text);
            desc = itemView.findViewById(R.id.desc_text);
        }
    }
}
