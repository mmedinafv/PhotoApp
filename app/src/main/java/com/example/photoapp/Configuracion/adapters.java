package com.example.photoapp.Configuracion;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photoapp.R;
import com.example.photoapp.databinding.ViewFotoBinding;

import java.util.List;

public class adapters extends RecyclerView.Adapter<adapters.MyViewHolder> {

    private List<photograh> photos;

    public adapters(List<photograh> photos) {
        this.photos = photos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewFotoBinding binding = ViewFotoBinding.inflate(inflater, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        photograh foto  = photos.get(position);
        holder.bind(foto);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ViewFotoBinding binding;

        public MyViewHolder(ViewFotoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(photograh foto) {
            binding.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Pro adapter", "click en Foto");
                }
            });
            binding.tvFotoNombre.setText(foto.getDescripcion());
            binding.imageView.setImageBitmap(foto.getFoto());
        }
    }

}
