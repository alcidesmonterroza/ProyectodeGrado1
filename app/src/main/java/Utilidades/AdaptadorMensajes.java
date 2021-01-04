package Utilidades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        public TextView FechaMensaje;
        public TextView De;
        public TextView ContenidoMensaje;
        public TextView Clave;

    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.layout_list_mensajes, null);

                holder = new ViewHolder();
                holder.FechaMensaje = (TextView) vi.findViewById(R.id.textfecha);
                holder.De = (TextView) vi.findViewById(R.id.textremitente);
                holder.ContenidoMensaje = (TextView) vi.findViewById(R.id.textmensaje);
                holder.Clave = (TextView) vi.findViewById(R.id.textClave);
                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }

            holder.FechaMensaje.setText(lMensajes.get(position).getFecha());
            holder.De.setText(lMensajes.get(position).getRemitente());
            holder.ContenidoMensaje.setText(lMensajes.get(position).getMensaje());
            holder.Clave.setText(lMensajes.get(position).getLlave());

        } catch (Exception e) {

        }

        return vi;
    }

}
