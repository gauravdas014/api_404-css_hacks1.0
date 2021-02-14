package com.example.nirog.MainDestinations.Hospital;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.nirog.R;

public class VacDetailsAdapter extends RecyclerView.Adapter<VacDetailsAdapter.VacdetailsViewHolder>{

    Context context;

    public VacDetailsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public VacdetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VacdetailsViewHolder(LayoutInflater.from(context).
                inflate(R.layout.vaccine_row_qty, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VacdetailsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class VacdetailsViewHolder extends RecyclerView.ViewHolder
    {
        TextView VaccineName;
        TextView qty;

        public VacdetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            VaccineName = itemView.findViewById(R.id.vaccine_name);
            qty = itemView.findViewById(R.id.vaccine_qty);

        }

    }
}
