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
import java.util.List;

public class AdaptadorElegirMascota extends RecyclerView.Adapter<AdaptadorElegirMascota.ViewHolder> {

    private List<Animal> mData;
    private OnItemClickListener mListener;


    public AdaptadorElegirMascota(List<Animal> data, OnItemClickListener listener) {
        this.mData = data;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pets, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Animal item = mData.get(position);
        holder.petName.setText(item.getName());
        holder.buttonPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(item.getIdAnimal());
                }
            }
        });
    }

    public void updateData(List<Animal> newData) {
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
        private TextView petName;
        private LinearLayout buttonPet;

        public ViewHolder(View itemView) {
            super(itemView);
            petName = itemView.findViewById(R.id.nombre_de_mascota);
            buttonPet = itemView.findViewById(R.id.eachPet);
        }
    }
}
