package Utilidades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ud.proyectodegrado1.R;

import java.util.ArrayList;
import java.util.List;

import Clases.Mensaje;
import Clases.Usuario;

public class AdaptadorUsuarios extends ArrayAdapter<Usuario> {

    private Context activity;
    private ArrayList<Usuario> lUsuarios;
    private static LayoutInflater inflater = null;

    public AdaptadorUsuarios( Context context, int textViewResourceId,  ArrayList<Usuario> lusuarios) {
        super(context, textViewResourceId, lusuarios);

        try{

            this.activity = context;
            this.lUsuarios = lusuarios;
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }catch (Exception e){

        }
    }

    public int getCount(){
        return lUsuarios.size();
    }

    public Usuario getItem(Usuario posicion) {
        return posicion;
    }

    public long getItemId(int posicion){
        return posicion;
    }

    public static class ViewHolder {

        public TextView usuariodestino;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {

                vi = inflater.inflate(R.layout.layout_list_usuarios, null);
                holder = new ViewHolder();
                holder.usuariodestino = (TextView) vi.findViewById(R.id.textView_usudestino);
                vi.setTag(holder);

            } else {

                holder = (ViewHolder) vi.getTag();
            }

            holder.usuariodestino.setText(lUsuarios.get(position).getNombre().replace("\"","") +" "+
                    lUsuarios.get(position).getApellido().replace("\"",""));


        } catch (Exception e) {

        }

        return vi;
    }

}

