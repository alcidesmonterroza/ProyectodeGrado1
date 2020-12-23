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
          //  resTxT1 = jsonObject.get("Tabla").getAsString();
         //   for(int i = 0 ; i<1; i++){
                ArrayList<String> vector = new ArrayList<String>() ;
                if (jsonObject.has("Tabla")) {

                    JsonArray ja= jsonObject.get("Tabla").getAsJsonArray();

                    for (JsonElement elemento: ja) {
                        String prueba = elemento.getAsJsonObject().get("usu").toString();
                        vector.add(prueba);
                    }

                    resTxT1 =  vector;

                }
           // }
            //JsonElement raiz = (JsonElement) parseador.parse(in);
            //JsonObject jsonObject = (JsonObject) parseador.parse(in);
            //Gson json = new Gson();
          //  JsonArray lista = jsonObject.getAsJsonArray();
        //   JsonArray lista = (JsonArray) parseador.parse(in);
         //  for(int i = 0;i<lista.size();i++) {
          //  for (JsonElement elemento : lista){
                   // elemento.getAsString();
                   // json.newJsonReader(lector);
           //     resTxT1 = lista.get(i).getAsString();
             /*JsonObject jsonObject = lista.get(i).getAsJsonObject();
               if (jsonObject.has("Tabla")) {
                  resTxT1  = jsonObject.getAsString();
                }*/
           //    else{
           //        resTxT1 [i]="nada";
           //    }

         //   }
          //  resTxT1 = jsonObject.get("usu").getAsString();


               
                
            

            /*String output;
            JSONObject jsonObject;
            lector.beginObject();
            while ((output = lector) != null) {
                resTxT1  = output;
                //    i++;
            }*/
        /*  lector.hasNext() == usu
            JSONArray jsonArray = new JSONArray(in);
            for(int i = 0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(jsonObject.has("usu")){
                     resTxT1 = jsonObject.getString("usu");
                }
            }*/
            //JsonParser parseador = new JsonParser();
            //  JsonElement raiz = parseador.parse();

         /*   Gson json = new Gson();
            JsonArray lista = raiz.getAsJsonArray();
            for (JsonElement elemento : lisJta) {}
                Persona persona = json.fromJson(elemento, Persona.class);
          //  BufferedReader br = new BufferedReader(in);
            String output;
           // int i=0;
            while ((output = br.readLine()) != null) {
                resTxT1  = output;
            //    i++;
            }*/

            con.disconnect();
        } catch (IOException | java.lang.RuntimeException e) {
            //System.out.println("error"+ e);
            // Toast.makeText(MainActivity.this, "error" + e, Toast.LENGTH_SHORT).show();
               resTxT1.set(0,"mierdita"+e.getMessage()+e.getStackTrace());
            return null;
        }

        return resTxT1;


    }
}

