package com.example.ud.proyectodegrado1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import Clases.Usuario;
import Clases.UsuarioLogeado;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    private EditText usuario, contrasena,cedula,nombre,apellido,alias,correo,telefono,clave;
    private Button validar,registrar;
    private TextView salida,salida2;
    String reciborespuesta,filausuario,resp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.usu);
        contrasena = findViewById(R.id.clave);
        cedula = findViewById(R.id.ced);
        nombre = findViewById(R.id.nom);
        apellido = findViewById(R.id.ape);
        alias = findViewById(R.id.ali);
        correo = findViewById(R.id.editTextTextEmailAddress2);
        telefono = findViewById(R.id.editTextPhone);
        clave = findViewById(R.id.editTextTextPassword);
        salida = findViewById(R.id.textView);
        salida2 = findViewById(R.id.textView5);
        validar = findViewById(R.id.button2);
        registrar = findViewById(R.id.button3);

    }

    public void registrarusuario(View v) throws ExecutionException, InterruptedException {

        String iden = cedula.getText().toString();
        String name = nombre.getText().toString();
        String apell = apellido.getText().toString();
        String nick = alias.getText().toString();
        String email = correo.getText().toString();
        String tele = telefono.getText().toString();
        String clav = clave.getText().toString();
        String perfil = "Usuario";

        Usuario usu = new Usuario(iden,name,apell,nick,email,tele,clav,perfil);

        salida2.setText(usu.Registrar_Usuario());
        salida2.setVisibility(VISIBLE);

    }

    public void validaringreso(View v) throws ExecutionException, InterruptedException {

        String Usu = usuario.getText().toString();
        String Key = contrasena.getText().toString();
        Usuario usu = new Usuario(Usu,"","","","","",Key,"Usuario");
        reciborespuesta = usu.Validar_Usuario();

        resp = reciborespuesta.replace("\"", "");

        if (resp.equals("No")||resp.equals("")){
            salida.setText("No Existe");
        }
        else{
            String [] datosusuario = resp.split(",");
            UsuarioLogeado.idusuariologeado = datosusuario[0];
            UsuarioLogeado.clave = datosusuario[6];
            salida.setText(UsuarioLogeado.idusuariologeado);
            String nombrecompleto = datosusuario[1] + " " + datosusuario[2];
            Intent int01 = new Intent(MainActivity.this, MainActivity2.class);
            int01.putExtra("USUARIO", nombrecompleto);
            startActivity(int01);
        }


    }
/*
     private class usuariologeado extends AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... strings) {

            String Miurl1 = "https://testud.azurewebsites.net/api/Usuariot?idusuario="+UsuarioLogeado.idusuariologeado;
            //Toast.makeText(MainActivity.this, " "+ Miurl1 , Toast.LENGTH_LONG).show();
            filausuario = WebService.MyWebservice(Miurl1);
            return null;
        }

        @Override
        protected void onPostExecute(Void Result)  {
            String [] datosusuario = filausuario.split(",");
           String nombrecompleto = datosusuario[1] + " " + datosusuario[2];

         //Toast.makeText(MainActivity.this, " "+ nombre, Toast.LENGTH_LONG).show();
            Intent int01 = new Intent(MainActivity.this, MainActivity2.class);
            int01.putExtra("USUARIO", nombrecompleto);
            startActivity(int01);
        }

        @Override
        protected void onPreExecute() {
          // salida2.setText("Guardando...");
        }


    }*/






}

