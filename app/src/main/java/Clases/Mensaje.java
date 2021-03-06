package Clases;

import android.os.AsyncTask;
import com.example.ud.proyectodegrado1.WebService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Mensaje implements Serializable {

    private  String id_mensaje;
    private  String fecha;
    private  String remitente;
    private  String destinatario;
    private  String mensaje;
    private  String llave;
    private String leido;

    public Mensaje(String id, String f, String remi,String dest, String mens, String lla,String lei )
    {
        id_mensaje = id;
        fecha = f;
        remitente = remi;
        destinatario = dest;
        mensaje = mens;
        llave = lla;
        leido = lei;
    }

    public String getId_mensaje(){ return id_mensaje; }
    public String getFecha(){
        return fecha;
    }
    public String getRemitente(){
        return remitente;
    }
    public String getDestinatario(){
        return destinatario;
    }
    public String getMensaje(){
        return mensaje;
    }
    public String getLlave(){ return llave;}
    public String getLeido(){ return leido;}

    public String EnviarMensaje() throws ExecutionException, InterruptedException {

        hilo_enviarmensaje a = new hilo_enviarmensaje();
        String resp = a.execute().get();
        return resp;
    }

    public String Cambiarestado() throws ExecutionException, InterruptedException {

        hilo_cambiarestado a = new hilo_cambiarestado();
        String resp = a.execute().get();
        return resp;

    }

    public ArrayList<Mensaje> ConsultarMensajes() throws ExecutionException, InterruptedException {

        hilo_consultarmensajes a = new hilo_consultarmensajes();
        ArrayList<Mensaje> resp = a.execute().get();
        return resp;

    }

    private class hilo_enviarmensaje extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            String Miurl = "https://testud.azurewebsites.net/api/Mensaje?fecha="+getFecha()+"&remitente="+getRemitente()+"&destinatario="+
                    getDestinatario()+"&mensajecifrado="+getMensaje()+"&llave="+getLlave()+"&leido="+getLeido();
            String a = WebService.MyWebservice(Miurl); //el Ws sirve para este caso//
            return  a;
        }
    }

    private class hilo_cambiarestado extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            String Miurl = "https://testud.azurewebsites.net/api/Mensaje?leido=1&idmensaje="+getId_mensaje();
            String a = WebService.MyWebservice(Miurl); //el Ws sirve para este caso//
            return  a;
        }
    }

    private class hilo_consultarmensajes extends AsyncTask<String,String, ArrayList<Mensaje>> {
        @Override
        protected ArrayList<Mensaje> doInBackground(String... strings) {
            String Miurl = "https://testud.azurewebsites.net/api/Mensaje?destinatario1="+getDestinatario();
            ArrayList<Mensaje> a = WebService.MyWebservicemensajes(Miurl); //el Ws sirve para este caso//
            return  a;
        }
    }
}
