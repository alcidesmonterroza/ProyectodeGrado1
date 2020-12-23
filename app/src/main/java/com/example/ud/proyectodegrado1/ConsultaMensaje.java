package com.example.ud.proyectodegrado1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import Clases.Mensaje;
import Utilidades.AdaptadorMensajes;
import Utilidades.UsuarioLogeado;

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

       // String prueba = "Mensaje: " + mensajes.get(1).getMensaje().toString() +" Fecha: "+ mensajes.get(1).getFecha()+ " Remite: "+ mensajes.get(1).getRemitente() +
       //         " Clave: "+ mensajes.get(1).getLlave();
       // Toast.makeText(this, ""+prueba, Toast.LENGTH_LONG).show();
      //  ArrayAdapter<Mensaje> adapter = new ArrayAdapter<Mensaje>(this, android.R.layout.simple_list_item_1, mensajes);
        AdaptadorMensajes adaptadorMensajes;
        adaptadorMensajes = new AdaptadorMensajes(this,0,mensajes);
        listaMensajes.setAdapter(adaptadorMensajes);

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