package com.example.ud.proyectodegrado1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import Clases.Usuario;
import Utilidades.UsuarioLogeado;

public class DatosActivity extends AppCompatActivity {

    private EditText nombre, apellido, alias, email, telefono, clave;
    private String respuesta, respuesta1, respuesta2;
    private TextView salida;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        nombre = findViewById(R.id.nombretext);
        apellido = findViewById(R.id.apetext);
        alias = findViewById(R.id.aliastext);
        email = findViewById(R.id.emailtext);
        telefono = findViewById(R.id.teltext);
        clave = findViewById(R.id.clavetext);
        salida = findViewById(R.id.textView11);

        try {
            cargardatos();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void cargardatos() throws ExecutionException, InterruptedException {
        Usuario usu = new Usuario(UsuarioLogeado.idusuariologeado, "", "", "", "", "", UsuarioLogeado.clave, "Usuario");
        String reciborespuesta = usu.Validar_Usuario();
        String resp = reciborespuesta.replace("\"", "");
        if (resp.equals("No") || resp.equals("")) {
            salida.setText(resp);
        } else {
            String[] datosusuario = reciborespuesta.split(",");
            nombre.setText(datosusuario[1]);
            apellido.setText(datosusuario[2]);
            alias.setText(datosusuario[3]);
            email.setText(datosusuario[4]);
            telefono.setText(datosusuario[5]);
            clave.setText(datosusuario[6]);

        }

    }

    public void actualizar(View v) throws ExecutionException, InterruptedException {

        Usuario u = new Usuario(UsuarioLogeado.idusuariologeado, nombre.getText().toString(),
                apellido.getText().toString(), alias.getText().toString(), email.getText().toString(),
                telefono.getText().toString(), clave.getText().toString(), "Usuario");
        String respuesta = u.Actualizar_Usuario();
        salida.setText(respuesta);
        Toast.makeText(this, "resp: " + respuesta, Toast.LENGTH_SHORT).show();
        //actualizardatos a = new actualizardatos();
        // a.execute();

    }

    public void eliminar(View v) throws ExecutionException, InterruptedException {

        Usuario u = new Usuario(UsuarioLogeado.idusuariologeado, nombre.getText().toString(),
                apellido.getText().toString(), alias.getText().toString(), email.getText().toString(),
                telefono.getText().toString(), clave.getText().toString(), "Usuario");
        String respuesta = u.Eliminar_Usuario();
        String resp = respuesta.replace("\"", "");
        if (resp.equals("Todo OK")) {
            salida.setText("Usuario Eliminado");
            Toast.makeText(DatosActivity.this, "Usuario Eliminado", Toast.LENGTH_LONG).show();
            Intent intent03 = new Intent(DatosActivity.this, MainActivity.class);
            startActivity(intent03);

            // eliminarusuario e = new eliminarusuario();
            // e.execute();

        }
    }
}




