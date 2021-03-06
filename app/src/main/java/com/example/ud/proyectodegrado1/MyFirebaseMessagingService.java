package com.example.ud.proyectodegrado1;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingRegistrar;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import Utilidades.UsuarioLogeado;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MFBMS";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getData().size()>0){

            String titulo = remoteMessage.getData().get("MiTitulo");
            String detalle =remoteMessage.getData().get("MiDetalle");
            notificar(titulo,detalle);

         }

        if(remoteMessage.getNotification() != null){

         }

    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.e("MI TOKEN: ","mi token es: " + s);
     }

    public void notificar(String tit, String det) {

        String ns = Context.NOTIFICATION_SERVICE;
        String CHannel_ID = "com.example.ud.proyectodegrado1";
        NotificationManager ntManager = (NotificationManager) getSystemService(ns);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "ANDROID CHANNEL";
            String descripcion = "CANAL DE NOTIFICACIONES ANDROID UD";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHannel_ID, name, importance);
            channel.setDescription(descripcion);
            ntManager.createNotificationChannel(channel);
        }

        int icono = R.drawable.mensaje3;
        CharSequence textEstado = "MENSAJERÍA CIFRADA";
        CharSequence titulo = tit; // remoteMessage.getNotification().getTitle() ;
        CharSequence descripcion = det;// remoteMessage.getNotification().getBody();
        long hora = System.currentTimeMillis();
        Context contexto = getApplicationContext();

            Intent notIntent = new Intent(contexto, MainActivity.class);
            PendingIntent contIntent = PendingIntent.getActivity(contexto, 0, notIntent, 0);
            NotificationCompat.Builder mBuilder = (NotificationCompat.Builder)
                    new NotificationCompat.Builder(getApplicationContext(), CHannel_ID)
                            .setSmallIcon(icono)
                            .setLargeIcon((((BitmapDrawable) getResources().getDrawable(R.drawable.mensaje3)).getBitmap()))
                            .setContentTitle(titulo)
                            .setContentText(descripcion)
                            .setContentInfo(textEstado)
                            .setWhen(hora)
                            .setContentIntent(contIntent)
                            .setAutoCancel(true)
                            .setColor(getResources().getColor(R.color.colorPrimaryDark))
                            .setVibrate(new long[]{100, 250, 100, 500})
                            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

            ntManager.notify(1, mBuilder.build());
        }

    }

