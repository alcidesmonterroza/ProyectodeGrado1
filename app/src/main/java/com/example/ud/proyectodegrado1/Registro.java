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

import static android.view.View.VISIBLE;

public class Registro extends AppCompatActivity {


    private EditText cedula,nombre,apellido,alias,correo,telefono,clave;
    private Button validar,registrar;
    private TextView salida,salida2;
    String reciborespuesta,filausuario,resp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        cedula = findViewById(R.id.ced);
        nombre = findViewById(R.id.nom);
        apellido = findViewById(R.id.ape);
        alias = findViewById(R.id.ali);
        correo = findViewById(R.id.editTextTextEmailAddress2);
        telefono = findViewById(R.id.editTextPhone);
        clave = findViewById(R.id.editTextTextPassword);
        salida = findViewById(R.id.textView);
        salida2 = findViewById(R.id.textView_salida);
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

    public void vuelvelogin(View v){
        Intent principal = new Intent(Registro.this,MainActivity.class);
        startActivity(principal);
    }
}