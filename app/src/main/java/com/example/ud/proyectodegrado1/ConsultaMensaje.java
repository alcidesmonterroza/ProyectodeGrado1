package com.example.ud.proyectodegrado1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private TextView  msjclaro, mostrarusuario;
    private EditText llaveclara;
    private String fechasel,remitesel,menssel,llavesel,estado,idmsj;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_mensaje);

        mostrarusuario = findViewById(R.id.textViewUsuario);
        mostrarusuario.setText("Usuario: "+UsuarioLogeado.nombrecompleto);
        toolbar = (Toolbar) findViewById(R.id.appbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        Fuente: https://www.iteramos.com/pregunta/35473/como-se-puede-eliminar-el-texto-del-titulo-de-la-android-actionbar

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
                estado = mensajes.get(position).getLeido().toString();
                idmsj = mensajes.get(position).getId_mensaje().toString();

                if(estado.replace("\"","").equals("0")){
                    try {
                        cambiarestado();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }

    public void cambiarestado() throws ExecutionException, InterruptedException {
        Mensaje m = new Mensaje(idmsj.replace("\"",""),"","","","","","");
        m.Cambiarestado();
    }

    public void actualizarmensajes(View v){
        cargardatos();
    }

    public void cargardatos(){

        Mensaje msj = new Mensaje("","","",UsuarioLogeado.idusuariologeado,"","","");
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
                cargardatos();

            }

        }

    }

    //funciones del menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menumensajes, menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        String per = UsuarioLogeado.perfil.replace("\"", "");
        //  Toast.makeText(this, "" + per, Toast.LENGTH_SHORT).show();
        if (per.equals("Usuario")) {
            menu.removeItem(R.id.admin);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                //Toast.makeText(this, "Mis Datos", Toast.LENGTH_LONG ).show();
                Intent intent02 = new Intent(this, DatosActivity.class);
                startActivity(intent02);
                break;
            case R.id.nuevomensaje:
                //   Toast.makeText(this, "NUEVO MENSAJE", Toast.LENGTH_LONG).show();
                Intent intent03 = new Intent(this, EnviaMensaje.class);
                startActivity(intent03);
                break;
            //return true;
            case R.id.recibidos:
                //   Toast.makeText(this, "RECIBIDOS", Toast.LENGTH_LONG).show();
                Intent intent04 = new Intent(this,ConsultaMensaje.class);
                startActivity(intent04);
                //return true;
                break;
            case R.id.enviados:
                //    Toast.makeText(this, "ENVIADOS", Toast.LENGTH_LONG).show();
                break;

            case R.id.admin:
                //    Toast.makeText(this, "ENVIADOS", Toast.LENGTH_LONG).show();
                Intent intent06 = new Intent(this,Administrar.class);
                startActivity(intent06);
                break;
            case R.id.cerrar:
                //   Toast.makeText(this, "Cerrando Sesión", Toast.LENGTH_LONG).show();
                Intent intent07 = new Intent(this,MainActivity.class);

                startActivity(intent07);
                UsuarioLogeado.idusuariologeado=null;
                UsuarioLogeado.clave=null;
                UsuarioLogeado.nombrecompleto=null;
                UsuarioLogeado.perfil=null;

                break;
        }

        return super.onOptionsItemSelected(item);
    }


}