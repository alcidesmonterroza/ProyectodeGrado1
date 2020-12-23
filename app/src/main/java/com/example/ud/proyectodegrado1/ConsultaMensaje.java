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
import android.widget.ListView;

import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Clases.Mensaje;
import Clases.UsuarioLogeado;

public class ConsultaMensaje extends AppCompatActivity {
    private RecyclerView contenedor;
    private ListView listaMensajes;
    private ArrayList<Mensaje> mensajes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_mensaje);

        listaMensajes = findViewById(R.id.listviewMensajes);
        Mensaje msj = new Mensaje("","",UsuarioLogeado.idusuariologeado,"","");
        try {
           mensajes = msj.ConsultarMensajes();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayAdapter<Mensaje> adapter = new ArrayAdapter<Mensaje>(this, android.R.layout.simple_list_item_1, mensajes);
        listaMensajes.setAdapter(adapter);

        //contenedor.setHasFixedSize(true);

      // LinearLayoutManager milayoutmanager = new LinearLayoutManager(this);
       // contenedor.setLayoutManager(milayoutmanager);




        /*RecyclerView.Adapter  adaptador = new RecyclerView.Adapter () {

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
        };*/

       // contenedor.setAdapter(adaptador);




    }
}