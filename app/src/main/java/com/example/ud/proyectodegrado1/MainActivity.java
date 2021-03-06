package com.example.ud.proyectodegrado1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        Usuario usu = new Usuario(Usu,"","","","","",Key,"Usuario","");
        reciborespuesta = usu.Validar_Usuario();

        resp = reciborespuesta.replace("\"", "");

        if (resp.equals("No")||resp.equals("")){
            salida.setText("El Usuario No Existe o Clave Errada");
            Toast.makeText(this, "El Usuario No Existe o Clave Errada", Toast.LENGTH_SHORT).show();
        }
        else{
            String [] datosusuario = resp.split(",");
            UsuarioLogeado.idusuariologeado = datosusuario[0];
            UsuarioLogeado.clave = datosusuario[6];
            salida.setText(UsuarioLogeado.idusuariologeado);
            UsuarioLogeado.nombrecompleto= datosusuario[1] + " " + datosusuario[2];
            UsuarioLogeado.perfil = datosusuario[7];
            UsuarioLogeado.tokenusuario = datosusuario[8];
            Intent int01 = new Intent(MainActivity.this, ConsultaMensaje.class);

            startActivity(int01);
        }


    }

    public void registrarusuario(View v){
        Intent registro = new Intent(MainActivity.this,Registro.class);
        startActivity(registro);
    }

}

