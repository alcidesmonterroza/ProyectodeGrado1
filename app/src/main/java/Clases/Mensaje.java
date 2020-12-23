package Clases;

import android.os.AsyncTask;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ud.proyectodegrado1.WebService;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

public class Mensaje implements Serializable {

  //  private  String id_mensaje;
    private  String fecha;
    private  String remitente;
    private  String destinatario;
    private  String mensaje;
    private  String llave;

    public Mensaje(String f, String remi,String dest, String mens, String lla )
    {
      //  id_mensaje = id;
        fecha = f;
        remitente = remi;
        destinatario = dest;
        mensaje = mens;
        llave = lla;
    }

  //  public String getId_mensaje(){   return id_mensaje;    }
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
    public String getLlave(){
        return llave;
    }

    public String EnviarMensaje() throws ExecutionException, InterruptedException {

        hilo_enviarmensaje a = new hilo_enviarmensaje();
        String resp = a.execute().get();
        return resp;

    }





    private class hilo_enviarmensaje extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            String Miurl = "https://testud.azurewebsites.net/api/Mensaje?fecha="+getFecha()+"&remitente="+getRemitente()+"&destinatario="+
                    getDestinatario()+"&mensajecifrado="+getMensaje()+"&llave="+getLlave();
            String a = WebService.MyWebservice(Miurl); //el Ws sirve para este caso//
            return  a;
        }
    }

}
