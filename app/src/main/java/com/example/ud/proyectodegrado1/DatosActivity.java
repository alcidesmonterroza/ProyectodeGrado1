package com.example.ud.proyectodegrado1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private TextView salida,mostrarusuario;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        mostrarusuario = findViewById(R.id.textViewUsuario);
        mostrarusuario.setText("Usuario: "+UsuarioLogeado.nombrecompleto);
        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menumensajes, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        String per = UsuarioLogeado.perfil.replace("\"", "");
       // Toast.makeText(this, "" + per, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, "NUEVO MENSAJE", Toast.LENGTH_LONG).show();
                Intent intent03 = new Intent(this, EnviaMensaje.class);
                startActivity(intent03);
                break;
            //return true;
            case R.id.recibidos:
                Toast.makeText(this, "RECIBIDOS", Toast.LENGTH_LONG).show();
                Intent intent04 = new Intent(this,ConsultaMensaje.class);
                startActivity(intent04);
                //return true;
                break;
            case R.id.enviados:
                Toast.makeText(this, "ENVIADOS", Toast.LENGTH_LONG).show();
                break;

            case R.id.admin:
                Toast.makeText(this, "ENVIADOS", Toast.LENGTH_LONG).show();
                Intent intent06 = new Intent(this,Administrar.class);
                startActivity(intent06);
                break;
        }

        return super.onOptionsItemSelected(item);
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
        if(respuesta.replace("\"","").equals("Todo OK")){
            salida.setText(respuesta);
            Toast.makeText(this, "Datos Actualizados", Toast.LENGTH_SHORT).show();
        }

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

        }
    }

}




