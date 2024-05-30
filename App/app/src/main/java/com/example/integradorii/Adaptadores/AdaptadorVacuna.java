package com.example.integradorii.Adaptadores;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AndroidException;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.integradorii.R;
import com.example.integradorii.estructura.Animal;
import com.example.integradorii.estructura.Post;
import com.example.integradorii.estructura.Vacuna;
import com.example.integradorii.vista.vacunas.Vacunas;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorVacuna extends RecyclerView.Adapter<AdaptadorVacuna.ViewHolder> {

    private List<Vacuna> mData;
    private OnItemClickListener mListener;


    public AdaptadorVacuna(List<Vacuna> data, OnItemClickListener listener) {
        this.mData = data;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vacuna, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Vacuna item = mData.get(position);
        holder.nextVacuna.setText("Nombre de la siguiente vacuna: " + item.getNext_vaccine());
        holder.desVacuna.setText("Descripción de la vacuna: " + item.getDescription());
        holder.nombreVacuna.setText("Nombre de la vacuna: " + item.getName());
    }

    public void updateData(List<Vacuna> newData) {
        mData = new ArrayList<>();
        mData.addAll(newData);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombreVacuna, desVacuna, nextVacuna;
        private LinearLayout buttonPet;

        public ViewHolder(View itemView) {
            super(itemView);
            nombreVacuna = itemView.findViewById(R.id.vacuna_nombre);
            desVacuna = itemView.findViewById(R.id.vacuna_des);
            nextVacuna = itemView.findViewById(R.id.vacuna_next);
        }
    }
}
