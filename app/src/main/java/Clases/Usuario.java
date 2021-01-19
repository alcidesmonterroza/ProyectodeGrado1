package Clases;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.example.ud.proyectodegrado1.WebService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import Utilidades.UsuarioLogeado;

public class Usuario implements Serializable {

    private  String id_usuario;
    private  String nombre;
    private  String apellido;
    private  String alias;
    private  String email;
    private  String telefono;
    private  String clave;
    private  String perfil;
    private  String token;
   // public String respuesta2;

    public Usuario(String id, String n, String ape,String ali, String em, String tel, String cla, String per, String tok )
    {
       id_usuario = id;
       nombre = n;
       apellido = ape;
       alias = ali;
       email = em;
       telefono = tel;
       clave = cla;
       perfil = per;
       token = tok;

    }

    public String getid()
    {
        return id_usuario;
    }
    public String getNombre()
    {
        return nombre;
    }
    public String getApellido()
    {
        return apellido;
    }
    public String getAlias()
    {
        return alias;
    }
    public String getEmail()
    {
        return email;
    }
    public String getTelefono()
    {
        return telefono;
    }
    public String getClave()
    {
        return clave;
    }
    public String getPerfil()
    {
        return perfil;
    }
    public String getToken()
    {
        return token;
    }

    public String Registrar_Usuario() throws ExecutionException, InterruptedException {

        hilo_registrarusuario a = new hilo_registrarusuario();
        String resp = a.execute().get();
        return resp;
    }

    public String Actualizar_Usuario() throws ExecutionException, InterruptedException {

        hilo_actualizarusuario a = new hilo_actualizarusuario();
        String resp = a.execute().get();
        return  resp;
    }

    public String Eliminar_Usuario() throws ExecutionException, InterruptedException {

        hilo_eliminarusuario a = new hilo_eliminarusuario();
        String resp = a.execute().get();
        return  resp;

    }

    public String Validar_Usuario() throws ExecutionException, InterruptedException {

        hilo_validarusuario a = new hilo_validarusuario();
        String resp = a.execute().get();
        return  resp;

    }

    public ArrayList<Usuario> administrar_usuario() throws ExecutionException, InterruptedException {

        hilo_administrarusuarios a = new hilo_administrarusuarios();
        ArrayList<Usuario> resp = a.execute().get();
        return resp;

    }


    public ArrayList<String>   Consultar_destinatarios() throws ExecutionException, InterruptedException {

        hilo_consultardestinatarios a = new hilo_consultardestinatarios();
        ArrayList<String>  resp = a.execute().get();
        return resp;
    }

    public ArrayList<Usuario>  Consultar_usuarios() throws ExecutionException, InterruptedException {

        hilo_consultarusuarios a = new hilo_consultarusuarios();
        ArrayList<Usuario>  resp = a.execute().get();
        return resp;
    }

    //Esto no esta funcionando
    public String consultatoken() throws ExecutionException, InterruptedException {

        hilo_token a = new hilo_token();
        String resp = a.execute().get();
        return  resp;
    }

    private class hilo_registrarusuario extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            String Miurl = "https://testud.azurewebsites.net/api/Usuariot?cedula="+getid()
                    +"&nombre="+getNombre()+"&apellido="+getApellido()+"&alias="+getAlias()+"&email="+getEmail()
                    +"&telefono="+getTelefono()+"&clave="+getClave()+"&perfil="+getPerfil()+"&token="+getToken();

            String a = WebService.MyWebservice(Miurl); //el Ws sirve para este caso//
            return  a;
        }
    }

    private class hilo_actualizarusuario extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            String Miurl ="https://testud.azurewebsites.net/api/Usuariot?cedula1="+getid()
                    +"&nombre1="+getNombre()+"&apellido1="+ getApellido()+"&alias1="+getAlias()
                    +"&email1="+getEmail()+"&telefono1="+getTelefono()+"&clave1="+getClave()+"&perfil1="+getPerfil()+"&token="+getToken();

            String a = WebService.MyWebservice(Miurl); //el Ws sirve para este caso//
            return  a;
        }
    }

    private class hilo_eliminarusuario extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {

            String Miurl ="https://testud.azurewebsites.net/api/Usuariot?cedula2="+getid();
            String a = WebService.MyWebservice(Miurl); //el Ws sirve para este caso//
            return  a;
        }
    }

    private class hilo_validarusuario extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {

            String Miurl = "https://testud.azurewebsites.net/api/Usuariot?cedula3="+getid()+"&clave3="+getClave();
            String a = WebService.MyWebservice(Miurl); //el Ws sirve para este caso//
            return  a;
        }
    }

    private class hilo_consultardestinatarios extends AsyncTask<String,String, ArrayList<String>> {
        @Override
        protected ArrayList<String>   doInBackground(String... strings) {

            String Miurl = "https://testud.azurewebsites.net/api/Mensaje?remitente1="+getid();
            ArrayList<String>  a = WebService.MyWebservicedestinatarios(Miurl); //el Ws sirve para este caso//
            return  a;
        }
    }

    private class hilo_consultarusuarios extends AsyncTask<String,String, ArrayList<Usuario>> {
        @Override
        protected ArrayList<Usuario> doInBackground(String... strings) {
            String Miurl = "https://testud.azurewebsites.net/api/Usuariot/"+getid()+"?nombre="+getNombre();
            ArrayList<Usuario> a = WebService.MyWebserviceusuario(Miurl);
            return  a;
        }
    }

    private class hilo_administrarusuarios extends AsyncTask<String,String, ArrayList<Usuario>> {
        @Override
        protected ArrayList<Usuario> doInBackground(String... strings) {
            String Miurl = "https://testud.azurewebsites.net/api/Usuariot?id_usuario="+getid();
            ArrayList<Usuario> a = WebService.MyWebserviceusuario(Miurl);
            return  a;
        }
    }

    //esto no esta funcionando
    private class hilo_token extends AsyncTask<String,String, String> {
        @Override
        protected String doInBackground(String... strings) {

            String Miurl = "https://testud.azurewebsites.net/api/Usuariot?cedula4="+getid();
            String a = WebService.MyWebservice(Miurl); //el Ws sirve para este caso//
            return a;
        }
    }
}
