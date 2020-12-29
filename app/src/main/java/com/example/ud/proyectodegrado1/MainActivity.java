package com.example.ud.proyectodegrado1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import Clases.Usuario;
import Utilidades.UsuarioLogeado;

public class MainActivity extends AppCompatActivity {

    private EditText usuario, contrasena;
    private Button validar;
    private TextView salida;
    String reciborespuesta,resp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.usu);
        contrasena = findViewById(R.id.clave);
        salida = findViewById(R.id.textView_salida);
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
            UsuarioLogeado.nombrecompleto= datosusuario[1] + " " + datosusuario[2];
            UsuarioLogeado.perfil = datosusuario[7];
            Intent int01 = new Intent(MainActivity.this, ConsultaMensaje.class);
           // int01.putExtra("USUARIO", nombrecompleto);
            startActivity(int01);
        }


    }

    public void registrarusuario(View v){
        Intent registro = new Intent(MainActivity.this,Registro.class);
        startActivity(registro);
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

