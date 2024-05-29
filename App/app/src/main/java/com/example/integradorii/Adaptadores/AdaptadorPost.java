package com.example.integradorii.adaptadores;

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
import com.example.integradorii.estructura.Post;
import java.util.List;

public class AdaptadorPost extends RecyclerView.Adapter<AdaptadorPost.ViewHolder> {

    private List<Post> mData;
    private OnItemClickListener mListener;


    public AdaptadorPost(List<Post> data, OnItemClickListener listener) {
        this.mData = data;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_adaptador, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Post item = mData.get(position);
        holder.user.setText(item.getUserName());
        holder.status.setText(item.getStatus());
        holder.title.setText(item.getTitle());
        holder.des.setText(item.getBody());
        if (item.getImg() == null || item.getImg().isEmpty()) {
            return;
        }
        Bitmap bitmap = base64ToBitmap(item.getImg());
        Glide.with(holder.img_menu.getContext())
                .load(bitmap)
                .into(holder.img_menu);
//        holder.button1.setOnClickListener(view -> {
//            if (mListener != null) {
//                mListener.onItemClick(position);
//            }
//        });
    }

    public void updateData(List<Post> newData) {
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
        private TextView title, user, des, status;
        private ImageView img_menu;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_view);
            status = itemView.findViewById(R.id.status_view);
            user = itemView.findViewById(R.id.user_view);
            des = itemView.findViewById(R.id.des_view);
            img_menu = itemView.findViewById(R.id.pet_view);
        }
    }

    public Bitmap base64ToBitmap(String base64String) {
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
