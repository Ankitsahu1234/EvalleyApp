package android.example.evalleyapp;

import android.content.Intent;
import android.example.evalleyapp.databinding.ItemAreaBinding;
import android.example.evalleyapp.databinding.ItemSlotsBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SlotsAdapter extends RecyclerView.Adapter<SlotsAdapter.myHolder>{

    List<Slots> slots;
    public SlotsAdapter(List<Slots> slots) {
        this.slots = slots;
    }

    @NonNull
    @Override
    public SlotsAdapter.myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemSlotsBinding binding = ItemSlotsBinding.inflate(inflater, parent,false);
        return new myHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SlotsAdapter.myHolder holder, int position) {
        Slots slot=slots.get(position);
        holder.binding.setSlotObject(slot);
        holder.binding.slotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),PaymentPage.class);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return slots.size();
    }

    class myHolder extends RecyclerView.ViewHolder{

        ItemSlotsBinding binding;
        public myHolder(@NonNull ItemSlotsBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
