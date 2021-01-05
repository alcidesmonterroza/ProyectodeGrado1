package com.example.ud.proyectodegrado1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Clases.Cifradora;
import Utilidades.AdaptadorMensajes;
import Utilidades.AdaptadorUsuarios;
import Utilidades.Destinatarios;
import Clases.Mensaje;
import Clases.Usuario;
import Utilidades.UsuarioLogeado;


public class EnviaMensaje extends AppCompatActivity {

    private Spinner listausuarios;
    private ListView listadestinatarios;
    private ArrayList<Usuario> usuarios;
    private TextView salida,fechaactual,mostrarusuario;
    private EditText textomensaje, llaveprivada,buscar;
    private Toolbar toolbar;
    private String nombreusu,apeusu,idusu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envia_mensaje);

        mostrarusuario = findViewById(R.id.textViewUsuario);
        mostrarusuario.setText("Usuario: "+UsuarioLogeado.nombrecompleto);
        toolbar = (Toolbar) findViewById(R.id.appbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

       // listausuarios = findViewById(R.id.spinner_destino);
        listadestinatarios = findViewById(R.id.listadestino);
        salida = findViewById(R.id.textView18);
        fechaactual = findViewById(R.id.textView14);
        textomensaje = findViewById(R.id.editTextTextMultiLine);
        llaveprivada = findViewById(R.id.text_llaveprivada);
        buscar =findViewById(R.id.text_buscar);

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

        listadestinatarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                idusu = usuarios.get(position).getid().toString();
                nombreusu =  usuarios.get(position).getNombre().toString();
                apeusu = usuarios.get(position).getApellido().toString();
            }
        });

        buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                try {
                    datosdeinicio();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

    }
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
              //  Toast.makeText(this, "NUEVO MENSAJE", Toast.LENGTH_LONG).show();
                Intent intent03 = new Intent(this, EnviaMensaje.class);
                startActivity(intent03);
                break;
            //return true;
            case R.id.recibidos:
            //    Toast.makeText(this, "RECIBIDOS", Toast.LENGTH_LONG).show();
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

    public void datosdeinicio() throws ExecutionException, InterruptedException {

      /*  List<String> usuarios = new ArrayList<String>();
        Usuario usu = new Usuario(UsuarioLogeado.idusuariologeado, "", "", "", "", "", UsuarioLogeado.clave, "Usuario");
        usuarios = usu.Consultar_destinatarios();
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
        listausuarios.setAdapter(adaptador);*/

        Usuario usu = new Usuario(UsuarioLogeado.idusuariologeado,buscar.getText().toString(),"","","","","","");

        try {
            usuarios = usu.Consultar_usuarios();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        AdaptadorUsuarios adaptadorUsuarios;
        adaptadorUsuarios = new AdaptadorUsuarios(this,0,usuarios);
        listadestinatarios.setAdapter(adaptadorUsuarios);


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
                        // String destinotemp = listausuarios.getSelectedItem().toString();
                        // String [] dest = destinotemp.split(";");
                        // String destino = dest[0];
                         String destino = idusu.replace("\"","");

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
                         String resp = nuevomensaje.EnviarMensaje();
                         if(resp.replace("\"","").equals("Todo OK")){
                             salida.setText("Mensaje Enviado");
                             Toast.makeText(this, "Mensaje Enviado Correctamente", Toast.LENGTH_SHORT).show();
                         }


                     }
            }
        }

        public void limpiar(View v){

        salida.setText("");
        textomensaje.setText("");
        llaveprivada.setText("");

        }

}

