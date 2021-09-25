package com.example.products;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class TouchHelperI extends ItemTouchHelper.SimpleCallback {

    private ItemAdapter itemAdapter;

    public TouchHelperI(ItemAdapter itemAdapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.itemAdapter = itemAdapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        if(direction == ItemTouchHelper.LEFT){
            itemAdapter.updateIData(position);
            itemAdapter.notifyDataSetChanged();
        }else {
            itemAdapter.deleteData(position);
        }
    }
}
