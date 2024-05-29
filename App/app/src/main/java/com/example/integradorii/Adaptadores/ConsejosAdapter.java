package com.example.integradorii.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.integradorii.R;
import com.example.integradorii.estructura.Careful;

import java.util.List;

public class ConsejosAdapter extends RecyclerView.Adapter<ConsejosAdapter.ConsejoViewHolder> {
    private List<Careful> consejos;

    public ConsejosAdapter(List<Careful> consejos) {
        this.consejos = consejos;
    }

    @Override
    public ConsejoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consejo, parent, false);
        return new ConsejoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ConsejoViewHolder holder, int position) {
        Careful consejo = consejos.get(position);
        holder.feeding.setText(consejo.getFeeding());
        holder.bathroom.setText(consejo.getBathroom());
        holder.raceName.setText(consejo.getRace().getName());
        //holder.activityName.setText(consejo.getActivity().getName());
        //holder.durationTime.setText(consejo.getActivity().getDurationTime());
        //holder.implements.setText(consejo.getActivity().getImplementss());
    }

    @Override
    public int getItemCount() {
        return consejos.size();
    }

    public static class ConsejoViewHolder extends RecyclerView.ViewHolder {
        TextView feeding, bathroom, raceName, activityName, durationTime, implementss;

        public ConsejoViewHolder(View itemView) {
            super(itemView);
            feeding = itemView.findViewById(R.id.feeding);
            bathroom = itemView.findViewById(R.id.bathroom);
            raceName = itemView.findViewById(R.id.race_name);
            activityName = itemView.findViewById(R.id.activity_name);
            durationTime = itemView.findViewById(R.id.duration_time);
            implementss = itemView.findViewById(R.id.implementss);
        }
    }
}
