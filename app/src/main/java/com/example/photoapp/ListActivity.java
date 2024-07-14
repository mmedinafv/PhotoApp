package com.example.photoapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photoapp.Configuracion.FotosObtener;
import com.example.photoapp.Configuracion.SQLiteConexion;
import com.example.photoapp.Configuracion.Trans;
import com.example.photoapp.Configuracion.adapters;
import com.example.photoapp.Configuracion.photograh;
import com.example.photoapp.databinding.ActivityListBinding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    Button btnatras;
        private ActivityListBinding binding;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityListBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            btnatras = (Button) findViewById(R.id.btnatras);

            btnatras.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ListActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });

            RecyclerView recView = binding.recViewPhoto;
            recView.setLayoutManager(new GridLayoutManager(this, 2));

            FotosObtener fotosObtener = new FotosObtener(this);
            List<photograh> lista = fotosObtener.getAll();
            recView.setAdapter(new adapters(lista));
        }

    }




