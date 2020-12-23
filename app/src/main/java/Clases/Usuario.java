package Clases;

import android.os.AsyncTask;
import android.util.Log;
import com.example.ud.proyectodegrado1.WebService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Usuario implements Serializable {

    private  String id_usuario;
    private  String nombre;
    private  String apellido;
    private  String alias;
    private  String email;
    private  String telefono;
    private  String clave;
    private  String perfil;
    public String respuesta2;

    public Usuario(String id, String n, String ape,String ali, String em, String tel, String cla, String per )
    {
       id_usuario = id;
       nombre = n;
       apellido = ape;
       alias = ali;
       email = em;
       telefono = tel;
       clave = cla;
       perfil = per;
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

    public ArrayList<String>   Consultar_Usuario() throws ExecutionException, InterruptedException {

        hilo_consultardestinatarios a = new hilo_consultardestinatarios();
        ArrayList<String>  resp = a.execute().get();
        return resp;


    }

    private class hilo_registrarusuario extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            String Miurl = "https://testud.azurewebsites.net/api/Usuariot?cedula="+getid()
                    +"&nombre="+getNombre()+"&apellido="+getApellido()+"&alias="+getAlias()+"&email="+getEmail()
                    +"&telefono="+getTelefono()+"&clave="+getClave()+"&perfil="+getPerfil();

            String a = WebService.MyWebservice(Miurl); //el Ws sirve para este caso//
            return  a;
        }
    }


    private class hilo_actualizarusuario extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            String Miurl ="https://testud.azurewebsites.net/api/Usuariot?cedula1="+UsuarioLogeado.idusuariologeado
                    +"&nombre1="+getNombre()+"&apellido1="+ getApellido()+"&alias1="+getAlias()
                    +"&email1="+getEmail()+"&telefono1="+getTelefono()+"&clave1="+getClave()+"&perfil1=Usuario";

            String a = WebService.MyWebservice(Miurl); //el Ws sirve para este caso//
            return  a;
        }
    }

    private class hilo_eliminarusuario extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {

            String Miurl ="https://testud.azurewebsites.net/api/Usuariot?cedula2="+UsuarioLogeado.idusuariologeado;
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
            ArrayList<String>  a = WebService.MyWebserviceusuarios(Miurl); //el Ws sirve para este caso//
            return  a;
        }
    }
}