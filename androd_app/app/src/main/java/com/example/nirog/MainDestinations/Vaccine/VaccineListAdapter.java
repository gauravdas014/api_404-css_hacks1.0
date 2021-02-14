package com.example.nirog.MainDestinations.Vaccine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nirog.MainDestinations.Hospital.HospitalListAdapter;
import com.example.nirog.R;
import com.example.nirog.data.model.VaccineDetails;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class VaccineListAdapter extends RecyclerView.Adapter<VaccineListAdapter.VaccineViewHolder> {


    List<VaccineDetails> vaccineRowItem;
    Context context;

    public VaccineListAdapter(List<VaccineDetails> vaccineRowItem, Context context) {
        this.vaccineRowItem = vaccineRowItem;
        this.context = context;
    }

    @NonNull
    @Override
    public VaccineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VaccineViewHolder(LayoutInflater.from(context).
                inflate(R.layout.vaccine_row_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VaccineViewHolder holder, int position) {
        VaccineDetails currentItem = vaccineRowItem.get(position);
        holder.vaccineName.setText(currentItem.getName());
        holder.whenToGive.setText(currentItem.getWhenToGive());
    }

    @Override
    public int getItemCount() {
        return vaccineRowItem.size();
    }

    public class VaccineViewHolder extends RecyclerView.ViewHolder
    {
        MaterialCardView vaccineCard;
        TextView vaccineName, whenToGive;

        public VaccineViewHolder(@NonNull View itemView) {
            super(itemView);
            vaccineCard = itemView.findViewById(R.id.materialCardView);
            vaccineName = itemView.findViewById(R.id.vaccine_name);
            whenToGive = itemView.findViewById(R.id.when_to_get);
        }
    }

    public interface OnVaccineCardClick{
        public void onClickListener(int position, int vaccineId, String vaccineName, String whenToGive);
    }
}
