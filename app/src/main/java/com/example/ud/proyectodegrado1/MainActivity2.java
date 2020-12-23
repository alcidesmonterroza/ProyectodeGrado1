package com.example.ud.proyectodegrado1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import Clases.Usuario;
import Clases.UsuarioLogeado;

public class MainActivity2 extends AppCompatActivity {

    private TextView mostrarusuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mostrarusuario = findViewById(R.id.usulogeado);
        Bundle bundle = this.getIntent().getExtras();
        String parametro = (String) bundle.get("USUARIO");
        mostrarusuario.setText(" Bienvenido:  " + parametro);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menumensajes, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

     /*   int id = item.getItemId();

        if (id == R.id.action_settings) {
            // lo ideal aquí sería hacer un intent para abrir una nueva clase como lo siguiente
            // Log.i("ActionBar", "Settings!");
            Intent about = new Intent(this, DatosActivity.class);
            startActivity(about);

           // return true;
        }

        return super.onOptionsItemSelected(item);*/


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

               // return true;
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}