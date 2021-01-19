package com.example.ud.proyectodegrado1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.concurrent.ExecutionException;

import Clases.Usuario;
import Utilidades.UsuarioLogeado;

import static android.view.View.VISIBLE;

public class Registro extends AppCompatActivity {

    private EditText cedula,nombre,apellido,alias,correo,telefono,clave;
    private Button validar,registrar;
    private TextView salida,salida2;
    String reciborespuesta,filausuario,resp;
    public String tokencito,tokencito1,iden;
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

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener( new OnCompleteListener<String>() {

            @Override
            public void onComplete(@NonNull Task<String> task) {
                tokencito1 = task.getResult();

                //  getString(Integer.parseInt(tokencito),token1);
                // Toast.makeText(Registro.this, "pordentro: "+tokencito1, Toast.LENGTH_LONG).show();
            }
        });

    }

    public void registrarusuario(View v) throws ExecutionException, InterruptedException {

        iden = cedula.getText().toString();
        String name = nombre.getText().toString();
        String apell = apellido.getText().toString();
        String nick = alias.getText().toString();
        String email = correo.getText().toString();
        String tele = telefono.getText().toString();
        String clav = clave.getText().toString();
        String perfil = "Usuario";
      //  tokencito =  MyFirebaseMessagingService.getToken(Registro.this);
      //  Usuario u = new Usuario("","","","","","","","","");
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener( new OnCompleteListener<String>() {

            @Override
            public void onComplete(@NonNull Task<String> task) {
                tokencito1 = task.getResult();

                //  getString(Integer.parseInt(tokencito),token1);
               // Toast.makeText(Registro.this, "pordentro: "+tokencito1, Toast.LENGTH_LONG).show();
            }
        });

     /* if (tokencito1.isEmpty()){
          FirebaseMessaging.getInstance().getToken().addOnCompleteListener( new OnCompleteListener<String>() {

              @Override
              
              public void onComplete(@NonNull Task<String> task) {
                  tokencito1 = task.getResult();

                  //  getString(Integer.parseInt(tokencito),token1);
                  Toast.makeText(Registro.this, "pordentro: "+tokencito1, Toast.LENGTH_LONG).show();
              }
          });
       }*/

        Toast toast1 = Toast.makeText(Registro.this, "por fuera: "+ tokencito1,Toast.LENGTH_LONG);
        toast1.setGravity(Gravity.CENTER|Gravity.LEFT,20,10);
        toast1.show();
       // Toast.makeText(this, "porfuera" + tokencito1, Toast.LENGTH_LONG).show();


     /*  FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( this,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                tokencito = instanceIdResult.getToken();

            }
        });

         FirebaseMessaging.getInstance().getToken().addOnSuccessListener(this, new OnSuccessListener<String>() {
             @Override
             public void onSuccess(String s) {
             //    tokencito1 = s ;
             }
         });*/





     /*  FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {

            @Override
            public void onComplete(@NonNull Task<String> task) {
            String token1 = task.getResult();
             tokencito = token1;
                Toast.makeText(Registro.this, "pordentro: "+token1, Toast.LENGTH_LONG).show();
            }


        });*/


      //  Toast.makeText(Registro.this, "Usuariologeado: "+ UsuarioLogeado.tokendispositivo, Toast.LENGTH_LONG).show();


        if(iden.isEmpty() || iden.trim().length()<1 || name.isEmpty() || name.trim().length()<1 ){
            Toast.makeText(this, "Debe digitar Identificación y nombre", Toast.LENGTH_SHORT).show();
        }
        else{
            Usuario usu = new Usuario(iden,name,apell,nick,email,tele,clav,perfil,tokencito1);
            String resp = usu.Registrar_Usuario();
          //  Toast.makeText(this, ""+resp, Toast.LENGTH_LONG).show();

            if(resp.substring(1,3).equals("Se")){

                salida2.setText("El Usuario ya existe en la Base de datos");
                Toast.makeText(this, "El Usuario ya existe en la Base de datos", Toast.LENGTH_SHORT).show();
            }
            else{

                if(resp.equals("\"Todo OK\"")){
                    Toast.makeText(this, "El Usuario ha sido creado", Toast.LENGTH_SHORT).show();

                    salida2.setText("El Usuario ha sido creado");

                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("token");
                    ref.child(""+iden).setValue(tokencito1);

                }
            }

            salida2.setVisibility(VISIBLE);
        }
        }

    public void vuelvelogin(View v){

        Intent principal = new Intent(Registro.this,MainActivity.class);
        startActivity(principal);
    }
}