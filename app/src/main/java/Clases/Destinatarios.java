package Clases;

import java.io.Serializable;

public class Destinatarios implements Serializable {

    private  String id_usuario;
    private  String nombrecompleto;


    public Destinatarios(String id, String n)
    {
        id_usuario = id;
        nombrecompleto = n;


    }

    public String getid()
    {
        return id_usuario;
    }
    public String getNombrecompleto()
    {
        return nombrecompleto;
    }




}
