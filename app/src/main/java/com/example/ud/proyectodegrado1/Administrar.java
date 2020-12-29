package com.example.ud.proyectodegrado1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import Clases.Mensaje;
import Clases.Usuario;
import Utilidades.UsuarioLogeado;

public class Administrar extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView mostrarusuario,salida;
    private EditText nombre,apellido,alias,email,telefono,clave, perfil,usuario;
    private ListView listausuarios;
    private Button btnEliminar, btnModificar;
    private ArrayList<Usuario> usuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar);

        salida= findViewById(R.id.txt_salida);
        usuario =findViewById(R.id.textusuario);
        nombre = findViewById(R.id.text_nombre);
        apellido = findViewById(R.id.text_nombre);
        alias = findViewById(R.id.text_nombre);
        email = findViewById(R.id.text_nombre);
        telefono = findViewById(R.id.text_nombre);
        clave = findViewById(R.id.text_nombre);
        perfil = findViewById(R.id.text_nombre);
        listausuarios=findViewById(R.id.List_usuarios);
        btnEliminar =findViewById(R.id.button_eliminar);
        btnModificar = findViewById(R.id.button_modi);

        mostrarusuario = findViewById(R.id.textViewUsuario);
        mostrarusuario.setText("Usuario: "+ UsuarioLogeado.nombrecompleto);
        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);


    }

    public void buscarusuario(View v) throws ExecutionException, InterruptedException {

        Usuario u = new Usuario(usuario.getText().toString(),"","","","","","","");
        usuarios=u.Consultar_usuarios();

        nombre.setText(usuarios.get(0).getNombre());
        apellido.setText(usuarios.get(0).getApellido());
        alias.setText(usuarios.get(0).getAlias());
        email.setText(usuarios.get(0).getEmail());
        telefono.setText(usuarios.get(0).getTelefono());
        clave.setText(usuarios.get(0).getClave());
        perfil.setText(usuarios.get(0).getPerfil());

    }
    public void eliminarusuario(View v){



    }
    public void modificarusuario(View v){



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
        Toast.makeText(this, "" + per, Toast.LENGTH_SHORT).show();
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
}