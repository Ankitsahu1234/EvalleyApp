package android.example.evalleyapp;

import android.content.Context;
import android.content.Intent;
import android.example.evalleyapp.databinding.ItemAreaBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.myHolder> {


    List<ModelArea> areas;
    Context context;
    public AreaAdapter(List<ModelArea> areas, Context applicationContext) {
        this.areas = areas;
    this.context = applicationContext;}

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemAreaBinding binding = ItemAreaBinding.inflate(inflater,parent,false);
        return new myHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
        ModelArea area=areas.get(position);
        holder.itemAreaBinding.setAreaobject(area);
        holder.itemAreaBinding.bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent = new Intent(context,SlotsActivity.class);
             intent.putExtra("ObjectId",area.getId().toString());
             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             context.startActivity(intent);
            }
        });
        holder.itemAreaBinding.buttonReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context,ReviewActivity.class);
                intent.putExtra("ObjectID",area.getId().toString());
                intent.putExtra("username",area.getName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return areas.size();

    }

    class myHolder extends RecyclerView.ViewHolder
    {
        ItemAreaBinding itemAreaBinding;
        public myHolder(@NonNull ItemAreaBinding itemAreaBinding) {
            super(itemAreaBinding.getRoot());
            this.itemAreaBinding=itemAreaBinding;
        }
    }

}

