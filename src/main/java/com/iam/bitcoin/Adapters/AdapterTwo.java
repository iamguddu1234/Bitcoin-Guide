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

public class AdapterTwo extends RecyclerView.Adapter<AdapterTwo.ViewHolder> {

    private List<Model> models;

    public AdapterTwo(List<Model> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.two_text,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.titleText.setText(models.get(position).getTitle());
        holder.subtitleText1.setText(models.get(position).getSubtitle1());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleText;
        TextView subtitleText1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.title);
            subtitleText1 = itemView.findViewById(R.id.subtitle1);
        }
    }
}
