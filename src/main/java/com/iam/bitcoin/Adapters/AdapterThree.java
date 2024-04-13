package com.iam.bitcoin.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iam.bitcoin.Model.Model;
import com.iam.bitcoin.R;

import java.util.List;

public class AdapterThree extends RecyclerView.Adapter<AdapterThree.ViewHolder> {

    private List<Model> models;

    public AdapterThree(List<Model> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.three_text,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTextView.setText(models.get(position).getTitle());
        holder.subtitle1TextView.setText(models.get(position).getSubtitle1());
        holder.subtitle2TextView.setText(models.get(position).getSubtitle2());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView;
        TextView subtitle1TextView;
        TextView subtitle2TextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.title);
            subtitle1TextView = itemView.findViewById(R.id.subtitle1);
            subtitle2TextView = itemView.findViewById(R.id.subtitle2);
        }
    }
}
