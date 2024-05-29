package com.example.integradorii.Adaptadores;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.integradorii.R;
import com.example.integradorii.estructura.Animal;
import com.example.integradorii.estructura.Post;

import java.util.List;

public class AdaptadorMascota extends RecyclerView.Adapter<AdaptadorMascota.ViewHolder> {

    private List<Animal> mData;
    private OnItemClickListener mListener;

    public AdaptadorMascota(List<Animal> data, OnItemClickListener listener) {
        this.mData = data;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_mascota, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Animal item = mData.get(position);
        holder.nombre.setText(item.getName());
        holder.tipo.setText(item.getType());
        holder.raza.setText(item.getLocation());
        holder.peso.setText(item.getOwner());
        holder.tamaño.setText(item.getSize());
        holder.genero.setText(item.getGender());
        holder.edad.setText((CharSequence) item.getBirthdate());
        holder.estado.setCursorVisible(item.isActive());
        if (item.getImg() != null && !item.getImg().isEmpty()) {
            Bitmap bitmap = base64ToBitmap(item.getImg());
            Glide.with(holder.imagen.getContext())
                    .load(bitmap)
                    .into(holder.imagen);
        }

        holder.itemView.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.onItemClick(position);
            }
        });
    }

    public void updateData(List<Animal> newData) {
        mData.clear();
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
        private TextView raza, tipo, nombre, peso, tamaño,genero,edad, estado;
        private ImageView imagen;


        public ViewHolder(View itemView) {
            super(itemView);
            raza = itemView.findViewById(R.id.razaAnimal);
            tipo = itemView.findViewById(R.id.tipoAnimal);
            nombre = itemView.findViewById(R.id.nombreMascota);
            peso = itemView.findViewById(R.id.pesoMascota);
            genero = itemView.findViewById(R.id.tamañoMascota);
            edad = itemView.findViewById(R.id.gender);
            estado = itemView.findViewById(R.id.edadMascota);
            imagen = itemView.findViewById(R.id.imageAnimal);
        }
    }

    public Bitmap base64ToBitmap(String base64String) {
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
