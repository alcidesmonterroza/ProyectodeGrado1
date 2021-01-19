package com.example.ud.proyectodegrado1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
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
    private Spinner Spinerperfiles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar);

        salida= findViewById(R.id.txt_salida);
        usuario =findViewById(R.id.textusuario);
        nombre = findViewById(R.id.text_nombre);
        apellido = findViewById(R.id.text_ape);
        alias = findViewById(R.id.text_alias);
        email = findViewById(R.id.text_email);
        telefono = findViewById(R.id.text_tel);
        clave = findViewById(R.id.text_clave);
        Spinerperfiles = findViewById(R.id.spinner_perfil);
        //perfil = findViewById(R.id.text_perfil);
        //listausuarios=findViewById(R.id.List_usuarios);
        btnEliminar =findViewById(R.id.button_eliminar);
        btnModificar = findViewById(R.id.button_modi);

        mostrarusuario = findViewById(R.id.textViewUsuario);
        mostrarusuario.setText("Usuario: "+ UsuarioLogeado.nombrecompleto);
        toolbar = (Toolbar) findViewById(R.id.appbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        llenarspiner();



    }

    public void llenarspiner(){

        List<String> perfil = new ArrayList<String>();
        perfil.add("Usuario");
        perfil.add("Admin");

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,perfil);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinerperfiles.setAdapter(adaptador);
    }

    public void buscarusuario(View v) throws ExecutionException, InterruptedException {

       if(usuario.getText().toString().isEmpty() || usuario.getText().toString().trim().length()<1){
           Toast.makeText(this, "Digite el Id del Usuario", Toast.LENGTH_SHORT).show();
       }
       else{

           Usuario u = new Usuario(usuario.getText().toString(),"","","","","","","","");
           usuarios=u.administrar_usuario();
           //Toast.makeText(this, ""+usuarios, Toast.LENGTH_SHORT).show();
           if(usuarios.isEmpty()){
               Toast.makeText(this, "Usuario No Existe", Toast.LENGTH_SHORT).show();
               salida.setText("Usuario No Existe");
           }
           else{
               Usuario encontrado = usuarios.get(0);
               nombre.setText(encontrado.getNombre().replace("\"",""));
               apellido.setText(encontrado.getApellido().replace("\"",""));
               alias.setText(encontrado.getAlias().replace("\"",""));
               email.setText(encontrado.getEmail().replace("\"",""));
               telefono.setText(encontrado.getTelefono().replace("\"",""));
               clave.setText(encontrado.getClave().replace("\"",""));
               if(encontrado.getPerfil().replace("\"","").equals("Usuario")){
                   Spinerperfiles.setSelection(0);
               }
               else {
                   Spinerperfiles.setSelection(1);
               }


           }

       }

    }

    public void eliminarusuario(View v) throws ExecutionException, InterruptedException {

        if(usuario.getText().toString().isEmpty() || usuario.getText().toString().trim().length()<1){
            Toast.makeText(this, "Digite el Id del Usuario", Toast.LENGTH_SHORT).show();
            salida.setText("");
        }
        else{
            Usuario ue = new Usuario(usuario.getText().toString(),"","","","","","","","");
            usuarios=ue.Consultar_usuarios();
            //Toast.makeText(this, ""+usuarios, Toast.LENGTH_SHORT).show();
            if(usuarios.isEmpty()){
                Toast.makeText(this, "Usuario No Existe", Toast.LENGTH_SHORT).show();
                salida.setText("Usuario No Existe");
            }
            else{

                salida.setText("");
                Usuario u = new Usuario(usuario.getText().toString(), "","", "", "","","","","");
                String respuesta = u.Eliminar_Usuario();
                String resp = respuesta.replace("\"", "");
                if (resp.equals("Todo OK")) {
                    salida.setText("Usuario Eliminado");
                    Toast.makeText(Administrar.this, "Usuario Eliminado", Toast.LENGTH_LONG).show();
                    nombre.setText("");
                    apellido.setText("");
                    alias.setText("");
                    email.setText("");
                    telefono.setText("");
                    clave.setText("");
                    perfil.setText("");

                }

            }

        }

    }

    public void modificarusuario(View v) throws ExecutionException, InterruptedException {

        if(usuario.getText().toString().isEmpty() || usuario.getText().toString().trim().length()<1){
            Toast.makeText(this, "Digite el Id del Usuario", Toast.LENGTH_SHORT).show();
        }
        else{

            if(nombre.getText().toString().isEmpty() && apellido.getText().toString().isEmpty() && alias.getText().toString().isEmpty()
                    && email.getText().toString().isEmpty() && telefono.getText().toString().isEmpty() && clave.getText().toString().isEmpty()
                    && perfil.getText().toString().isEmpty()){
                Toast.makeText(this, "Campos en blanco", Toast.LENGTH_SHORT).show();

            }
            else{
                Usuario ue = new Usuario(usuario.getText().toString(),"","","","","","","","");
                usuarios=ue.Consultar_usuarios();
                //Toast.makeText(this, ""+usuarios, Toast.LENGTH_SHORT).show();
                if(usuarios.isEmpty()){
                    Toast.makeText(this, "Usuario No Existe", Toast.LENGTH_SHORT).show();
                    salida.setText("Usuario No Existe");
                }
                else{

                    salida.setText("");
                    Usuario u = new Usuario(usuario.getText().toString(), nombre.getText().toString(),
                            apellido.getText().toString(), alias.getText().toString(), email.getText().toString(),
                            telefono.getText().toString(), clave.getText().toString(), Spinerperfiles.getSelectedItem().toString(),usuarios.get(0).getToken().toString());
                    String respuesta = u.Actualizar_Usuario();
                    if(respuesta.replace("\"","").equals("Todo OK")){
                        salida.setText("Usuario Actualizado");
                        Toast.makeText(this, "Usuario Actualizado", Toast.LENGTH_SHORT).show();
                    }



                }

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
              //  Toast.makeText(this, "NUEVO MENSAJE", Toast.LENGTH_LONG).show();
                Intent intent03 = new Intent(this, EnviaMensaje.class);
                startActivity(intent03);
                break;
            //return true;
            case R.id.recibidos:
               // Toast.makeText(this, "RECIBIDOS", Toast.LENGTH_LONG).show();
                Intent intent04 = new Intent(this,ConsultaMensaje.class);
                startActivity(intent04);
                //return true;
                break;
            case R.id.enviados:
             //   Toast.makeText(this, "ENVIADOS", Toast.LENGTH_LONG).show();
                break;

            case R.id.admin:
             //   Toast.makeText(this, "ENVIADOS", Toast.LENGTH_LONG).show();
                Intent intent06 = new Intent(this,Administrar.class);
                startActivity(intent06);
                break;

            case R.id.cerrar:
            //    Toast.makeText(this, "Cerrando Sesión", Toast.LENGTH_LONG).show();
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