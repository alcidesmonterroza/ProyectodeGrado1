package com.example.ud.proyectodegrado1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import Clases.Mensaje;

public class ConsultaMensaje extends AppCompatActivity {
    private RecyclerView contenedor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_mensaje);

        contenedor = findViewById(R.id.reciclerview);
        contenedor.setHasFixedSize(true);

        LinearLayoutManager milayoutmanager = new LinearLayoutManager(this);
        contenedor.setLayoutManager(milayoutmanager);

        List<Mensaje> mensajes = new ArrayList<Mensaje>();


        RecyclerView.Adapter  adaptador = new RecyclerView.Adapter () {

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        };

        contenedor.setAdapter(adaptador);




    }
}