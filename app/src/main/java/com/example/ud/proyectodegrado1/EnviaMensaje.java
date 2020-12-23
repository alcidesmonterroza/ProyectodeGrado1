package com.example.ud.proyectodegrado1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Clases.Cifradora;
import Utilidades.Destinatarios;
import Clases.Mensaje;
import Clases.Usuario;
import Utilidades.UsuarioLogeado;


public class EnviaMensaje extends AppCompatActivity {
    private Spinner listausuarios;
    private TextView salida,fechaactual;
    private EditText textomensaje, llaveprivada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envia_mensaje);
        listausuarios = findViewById(R.id.spinner_destino);
        salida = findViewById(R.id.textView18);
        fechaactual = findViewById(R.id.textView14);
        textomensaje = findViewById(R.id.editTextTextMultiLine);
        llaveprivada = findViewById(R.id.text_llaveprivada);


        //CAPTURA DE FECHA ACTUAL
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        fechaactual.setText(dateString);

        //LLAMAR DATOS DE INICIO PARA CARGAR EL SPINNER
        try {
            datosdeinicio();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void datosdeinicio() throws ExecutionException, InterruptedException {
        List<String> usuarios = new ArrayList<String>();
        Usuario usu = new Usuario(UsuarioLogeado.idusuariologeado, "", "", "", "", "", UsuarioLogeado.clave, "Usuario");
        usuarios = usu.Consultar_Usuario();
       // salida.setText(usuarios.get(0).toString()+ usuarios.get(1).toString()+usuarios.get(2).toString());
        List<Destinatarios> temporal =  new ArrayList<>();
        List<String>temp = new ArrayList<String>();

        String [] elemento = null;
        for(int i=0;i<usuarios.size();i++ ) {

            elemento=usuarios.get(i).split(",");
            String id= elemento[0].replace("\"", "");
            String nombrecompleto = elemento[1]+" "+elemento[2];
            Destinatarios d = new Destinatarios(id,nombrecompleto);
         //   temporal.add(i,d);
            temp.add(i,d.getid() + "; " +d.getNombrecompleto());
        }
            //temporal.add(i, usuarios.get(1)+" "+usuarios.get(2));
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,temp);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//simple_spinner_item
        listausuarios.setAdapter(adaptador);

        }

        public void Enviarmenjaje(View v) throws ExecutionException, InterruptedException {

            if( textomensaje.getText().toString().isEmpty() || textomensaje.getText().toString().trim().length()<1
            ||llaveprivada.getText().toString().isEmpty() || llaveprivada.getText().toString().trim().length()<1 ){

                Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show();
            }
            else {
                    boolean continuar=true;
                    for(int i = 0 ; i< textomensaje.getText().length(); i++ ){

                        char a = textomensaje.getText().charAt(i);
                        if(a == '+'||a=='#'||a=='&' ) {
                            continuar=false;
                        }

                    }

                     if(continuar==false){

                         Toast.makeText(this, "No se permiten #,+,&", Toast.LENGTH_SHORT).show();
                     }
                     else {

                         //Definir el destinatario
                         String destinotemp = listausuarios.getSelectedItem().toString();
                         String [] dest = destinotemp.split(";");
                         String destino = dest[0];

                         //Se toma asgina valor a mensaje en claro el cual se toma del texto del mensaje
                         Cifradora cifra = new Cifradora();
                         cifra.setMensajeclaro(textomensaje.getText().toString());

                         //Se asigna el valor a la llave de desplazamiento
                         String llave = llaveprivada.getText().toString();
                         Integer llaveaentero = Integer.parseInt(llave);
                         cifra.setValordesplazamiento(llaveaentero);

                         //se cifra el mensaje por desplazamiento
                         String mensajecifrado = cifra.CifrarMensajedesplazamiento();
                         //se cifra la llave por sustitucion
                         String llavecifrada = cifra.CifrarLlaveporSustitucion();

                         salida.setText(mensajecifrado);

                         Mensaje nuevomensaje = new Mensaje(fechaactual.getText().toString(), UsuarioLogeado.idusuariologeado, destino, mensajecifrado,
                                 llavecifrada);
                         salida.setText(nuevomensaje.EnviarMensaje());
                     }

            }


        }

    }

