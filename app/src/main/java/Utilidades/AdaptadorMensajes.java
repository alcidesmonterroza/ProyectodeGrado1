package Utilidades;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ud.proyectodegrado1.R;

import java.util.ArrayList;

import Clases.Mensaje;



public class AdaptadorMensajes extends ArrayAdapter<Mensaje> {

    private Context activity;
    private ArrayList<Mensaje> lMensajes;
    private static LayoutInflater inflater = null;

    public AdaptadorMensajes( Context context, int textViewResourceId,  ArrayList<Mensaje> lmensajes) {
        super(context, textViewResourceId, lmensajes);
        try{

            this.activity = context;
            this.lMensajes = lmensajes;
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }catch (Exception e){

        }
    }

    public int getCount(){
        return lMensajes.size();
    }

    public Mensaje getItem(Mensaje posicion) {
        return posicion;
    }

    public long getItemId(int posicion){
        return posicion;
    }

    public static class ViewHolder {
        public TextView IdMensaje;
        public TextView FechaMensaje;
        public TextView De;
        public TextView ContenidoMensaje;
        public TextView Clave;
        public TextView Leido;
        public TextView Color;
        public ImageView Imagen;

    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.layout_list_mensajes, null);

                holder = new ViewHolder();
                holder.Imagen = (ImageView) vi.findViewById(R.id.imagen);
                holder.IdMensaje = (TextView) vi.findViewById(R.id.textidmensaje);
                holder.FechaMensaje = (TextView) vi.findViewById(R.id.textfecha);
                holder.De = (TextView) vi.findViewById(R.id.textremitente);
                holder.ContenidoMensaje = (TextView) vi.findViewById(R.id.textmensaje);
                holder.Clave = (TextView) vi.findViewById(R.id.textClave);
                holder.Leido = (TextView) vi.findViewById(R.id.leido);

                vi.setTag(holder);

            } else {
                holder = (ViewHolder) vi.getTag();
            }

            holder.IdMensaje.setText(lMensajes.get(position).getId_mensaje());
            holder.FechaMensaje.setText(lMensajes.get(position).getFecha());
            holder.De.setText(lMensajes.get(position).getRemitente());
            holder.ContenidoMensaje.setText(lMensajes.get(position).getMensaje());
            holder.Clave.setText(lMensajes.get(position).getLlave());
            holder.Leido.setText(lMensajes.get(position).getLeido());
            if(lMensajes.get(position).getLeido().replace("\"","").equals("0")){


                holder.Imagen.setImageResource(R.drawable.mensaje3);
            }
            else{


                holder.Imagen.setImageResource(R.drawable.mensaje);
            }

        } catch (Exception e) {

        }

        return vi;
    }

}
