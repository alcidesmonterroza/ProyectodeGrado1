package com.example.ud.proyectodegrado1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import Clases.Cifradora;
import Clases.Mensaje;
import Utilidades.AdaptadorMensajes;
import Utilidades.UsuarioLogeado;

public class ConsultaMensaje extends AppCompatActivity {
    private ListView listaMensajes;
    private ArrayList<Mensaje> mensajes;
    private TextView  msjclaro,llaveclara;
    private String fechasel,remitesel,menssel,llavesel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_mensaje);
        msjclaro = findViewById(R.id.textmensajeclaro);
        llaveclara = findViewById(R.id.text_llaveclara);

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

        listaMensajes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                menssel =  mensajes.get(position).getMensaje().toString();
                llavesel = mensajes.get(position).getLlave().toString();
               //Toast.makeText(ConsultaMensaje.this, "Has pulsado: "+ menssel + " "+llavesel, Toast.LENGTH_LONG).show();

            }
        });

    }

    public void desencriptallave(View v){
        Cifradora descifra = new Cifradora();

        Integer llavecita = Integer.parseInt(llavesel.replace("\"", ""));


        //  Toast.makeText(ConsultaMensaje.this, "esto"+prueba, Toast.LENGTH_SHORT).show();
        descifra.setValordesplazamiento(llavecita);
        llaveclara.setText(descifra.CifrarLlaveporSustitucion());

    }


    public void desencriptamsj(View v){
        if(llaveclara.getText().toString().isEmpty() || llaveclara.getText().toString().trim().length()<1){
             Toast.makeText(this, " Ojo no hay llave para desencriptar", Toast.LENGTH_SHORT).show();
        }
        else{

            if(menssel.toString().isEmpty()) {
                Toast.makeText(this, " Ojo no hay Mensaje para desencriptar", Toast.LENGTH_SHORT).show();
            }
            else{
                Cifradora descifra1 = new Cifradora();
                // Mensaje mensajeseleccionado = (Mensaje) listaMensajes.getSelectedItem();
                // Toast.makeText(this, ""+ mensajeseleccionado.getMensaje(), Toast.LENGTH_SHORT).show();
                int longi = menssel.length();
                String msjlimpiacomilla1 = menssel.substring(1,longi-1);

                // Toast.makeText(ConsultaMensaje.this, ""+ prueba, Toast.LENGTH_LONG).show();
                String msjlimpio = msjlimpiacomilla1;

                descifra1.setMensajecifrado(msjlimpio);
                descifra1.setValordesplazamiento(Integer.parseInt(llaveclara.getText().toString()));
                String mensajeclaro = descifra1.DescifrarMensajedesplazamiento();
                msjclaro.setText(mensajeclaro);
            }


        }

    }


}