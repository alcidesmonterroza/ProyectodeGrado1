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

public class WebService {

    private static Object RuntimeException;

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
            //System.out.println("error"+ e);
            // Toast.makeText(MainActivity.this, "error" + e, Toast.LENGTH_SHORT).show();
            resTxT1 = "esto : "+e.getMessage();
        }

        return resTxT1;
    }

    public static ArrayList<String> MyWebserviceusuarios(String urlservicio) {

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
            //System.out.println("error"+ e);
            // Toast.makeText(MainActivity.this, "error" + e, Toast.LENGTH_SHORT).show();
               resTxT1.set(0,"mierdita"+e.getMessage()+e.getStackTrace());
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
                    String fecha =  elemento.getAsJsonObject().get("fecha").toString();
                    String remitente = elemento.getAsJsonObject().get("remitente").toString();;
                    String destinatario = elemento.getAsJsonObject().get("destinatario").toString();;
                    String mensaje = elemento.getAsJsonObject().get("mensaje").toString();;
                    String llave = elemento.getAsJsonObject().get("llave").toString();;
                    Mensaje msj = new Mensaje(fecha,remitente,destinatario,mensaje,llave);

                    vector.add(msj);
                }

                resTxT1 =  vector;

            }



            con.disconnect();
        } catch (IOException | java.lang.RuntimeException e) {

            //System.out.println("error"+ e);
            // Toast.makeText(MainActivity.this, "error" + e, Toast.LENGTH_SHORT).show();
           // resTxT1.set(0,"mierdita"+e.getMessage()+e.getStackTrace());
            Mensaje ms = new Mensaje("1","2","3","4","5");
            resTxT1.add(0, ms);
            return resTxT1;
        }

        return resTxT1;


    }
}
