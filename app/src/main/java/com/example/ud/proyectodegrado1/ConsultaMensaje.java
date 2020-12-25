package com.example.ud.proyectodegrado1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
    private TextView  msjclaro;
    private EditText llaveclara;
    private String fechasel,remitesel,menssel,llavesel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_mensaje);
        msjclaro = findViewById(R.id.textmensajeclaro);
        llaveclara = findViewById(R.id.text_llaveclara);
        menssel =null;
        llavesel=null;

        listaMensajes = findViewById(R.id.listviewMensajes);

        cargardatos();


        listaMensajes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                menssel =  mensajes.get(position).getMensaje().toString();
                llavesel = mensajes.get(position).getLlave().toString();
               //Toast.makeText(ConsultaMensaje.this, "Has pulsado: "+ menssel + " "+llavesel, Toast.LENGTH_LONG).show();


            }
        });

    }

    public void actualizarmensajes(View v){
        cargardatos();
    }
    public void cargardatos(){

        Mensaje msj = new Mensaje("","",UsuarioLogeado.idusuariologeado,"","");
        try {
            mensajes = msj.ConsultarMensajes();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        AdaptadorMensajes adaptadorMensajes;
        adaptadorMensajes = new AdaptadorMensajes(this,0,mensajes);
        listaMensajes.setAdapter(adaptadorMensajes);

    }

    public void desencriptallave(View v){
        if(llavesel!=null){

            Cifradora descifra = new Cifradora();

            Integer llavecita = Integer.parseInt(llavesel.replace("\"", ""));

            descifra.setValordesplazamiento(llavecita);
            llaveclara.setText(descifra.CifrarLlaveporSustitucion());
        }
        else{
            Toast.makeText(this, "Seleccione un Mensaje", Toast.LENGTH_SHORT).show();
        }

    }


    public void desencriptamsj(View v){
        if(llaveclara.getText().toString().isEmpty() || llaveclara.getText().toString().trim().length()<1 || menssel == null){
             Toast.makeText(this, " Ojo no hay llave para desencriptar", Toast.LENGTH_SHORT).show();
        }
        else{

            if(menssel.toString().isEmpty()) {
                Toast.makeText(this, " Ojo no hay Mensaje para desencriptar", Toast.LENGTH_SHORT).show();
            }
            else{
                Cifradora descifra1 = new Cifradora();

                int longi = menssel.length();
                String msjlimpiacomilla1 = menssel.substring(1,longi-1);

                String msjlimpio = msjlimpiacomilla1.replace("\\","");

                descifra1.setMensajecifrado(msjlimpio);
                descifra1.setValordesplazamiento(Integer.parseInt(llaveclara.getText().toString()));
                String mensajeclaro = descifra1.DescifrarMensajedesplazamiento();
                msjclaro.setText(mensajeclaro);
            }


        }

    }


}