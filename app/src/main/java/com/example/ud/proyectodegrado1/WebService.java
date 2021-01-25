package com.example.ud.proyectodegrado1;
import android.util.JsonReader;
import android.util.JsonWriter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import Clases.Mensaje;
import Clases.Usuario;

public class WebService {

     public static String MyWebservice(String urlservicio) {

        String resTxT1 = null;
        try {
            java.net.URL url = new URL(urlservicio);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            if (con.getResponseCode() != 200) {
                // throw new RuntimeException("Failed:HTTP Error code :" + con.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(con.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                resTxT1 = output;
            }

            con.disconnect();
        } catch (IOException | RuntimeException e) {

            resTxT1 = "esto1 : "+e.getMessage();
        }

        return resTxT1;
    }

    public static ArrayList<String> MyWebservicedestinatarios(String urlservicio) {

        ArrayList<String> resTxT1 = new ArrayList<String>();
        try {
            URL url = new URL(urlservicio);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            if (con.getResponseCode() != 200) {
                // throw new RuntimeException("Failed:HTTP Error code :" + con.getResponseCode());
            }

            InputStreamReader in = new InputStreamReader(con.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String json = br.readLine();
            //JsonReader lector = new JsonReader(in);
            JsonParser parseador = new JsonParser();
            JsonObject jsonObject = (JsonObject) parseador.parse(json);

                ArrayList<String> vector = new ArrayList<String>() ;
                if (jsonObject.has("Tabla")) {

                    JsonArray ja= jsonObject.get("Tabla").getAsJsonArray();

                    for (JsonElement elemento: ja) {
                        String prueba = elemento.getAsJsonObject().get("usu").toString();
                        vector.add(prueba);
                    }

                    resTxT1 =  vector;

                }

            con.disconnect();
        } catch (IOException | java.lang.RuntimeException e) {

               resTxT1.set(0,"error"+e.getMessage()+e.getStackTrace());
            return null;
        }

        return resTxT1;

    }

    public static ArrayList<Mensaje> MyWebservicemensajes(String urlservicio) {

        ArrayList<Mensaje> resTxT1 = new ArrayList<Mensaje>();
        try {
            URL url = new URL(urlservicio);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            if (con.getResponseCode() != 200) {
                // throw new RuntimeException("Failed:HTTP Error code :" + con.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(con.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String json = br.readLine();
            //JsonReader lector = new JsonReader(in);
            JsonParser parseador = new JsonParser();
            JsonObject jsonObject = (JsonObject) parseador.parse(json);

            ArrayList<Mensaje> vector = new ArrayList<Mensaje>() ;
            if (jsonObject.has("Tabla")) {

                JsonArray ja= jsonObject.get("Tabla").getAsJsonArray();

                for (JsonElement elemento: ja) {
                    String fechat0 =  elemento.getAsJsonObject().get("fecha").toString();
                    String fecha = fechat0.replaceAll("T00:00:00","");
                    String idmensaje = elemento.getAsJsonObject().get("id_mensaje").toString();
                    String remitente = elemento.getAsJsonObject().get("nombreremite").toString();
                    String destinatario = elemento.getAsJsonObject().get("destinatario").toString();
                    String mensaje = elemento.getAsJsonObject().get("mensaje").toString();
                    String llave = elemento.getAsJsonObject().get("llave").toString();
                    String leido = elemento.getAsJsonObject().get("leido").toString();
                    Mensaje msj = new Mensaje(idmensaje,fecha,remitente,destinatario,mensaje,llave,leido);

                    vector.add(msj);
                }

                resTxT1 =  vector;

            }

            con.disconnect();
        } catch (IOException | java.lang.RuntimeException e) {

            //System.out.println("error"+ e);
            // Toast.makeText(MainActivity.this, "error" + e, Toast.LENGTH_SHORT).show();
           // resTxT1.set(0,"mierdita"+e.getMessage()+e.getStackTrace());
            Mensaje ms = new Mensaje("1","2","3","4","5","6","7");
            resTxT1.add(0, ms);
            return resTxT1;
        }

        return resTxT1;

    }


    public static ArrayList<Usuario> MyWebserviceusuario(String urlservicio) {

        ArrayList<Usuario> resTxT1 = new ArrayList<Usuario>();
        try {
            URL url = new URL(urlservicio);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            if (con.getResponseCode() != 200) {
                // throw new RuntimeException("Failed:HTTP Error code :" + con.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(con.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String json = br.readLine();
            //JsonReader lector = new JsonReader(in);
            JsonParser parseador = new JsonParser();
            JsonObject jsonObject = (JsonObject) parseador.parse(json);

            ArrayList<Usuario> vector = new ArrayList<Usuario>() ;
            if (jsonObject.has("Tabla")) {

                JsonArray ja= jsonObject.get("Tabla").getAsJsonArray();

                for (JsonElement elemento: ja) {


                    String id = elemento.getAsJsonObject().get("id_usuario").toString();
                    String nombre = elemento.getAsJsonObject().get("nombre").toString();
                    String apellido = elemento.getAsJsonObject().get("apellido").toString();
                    String alias = elemento.getAsJsonObject().get("alias").toString();
                    String email = elemento.getAsJsonObject().get("email").toString();
                    String telefono = elemento.getAsJsonObject().get("telefono").toString();
                    String clave = elemento.getAsJsonObject().get("clave").toString();
                    String perfil = elemento.getAsJsonObject().get("perfil").toString();
                    String token = elemento.getAsJsonObject().get("token").toString();

                    Usuario usuario = new Usuario(id,nombre,apellido,alias,email,telefono,clave,perfil,token);

                    vector.add(usuario);
                }

                resTxT1 =  vector;

            }

            con.disconnect();
        } catch (IOException | java.lang.RuntimeException e) {

            Usuario us = new Usuario("","","","","","","","","");
            resTxT1.add(0, us);
            return resTxT1;
        }

        return resTxT1;

    }
}

