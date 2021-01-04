package Clases;

import java.io.Serializable;

public class Cifradora{

    private  String mensajeclaro;
    private  String mensajecifrado;
    private  Integer valordesplazamiento;

    public String getMensajecifrado() {
        return mensajecifrado;
    }

    public void setMensajecifrado(String mensajecifrado) {
        this.mensajecifrado = mensajecifrado;
    }

    public String getMensajeclaro() {
        return mensajeclaro;
    }

    public void setMensajeclaro(String mensajeclaro) {
        this.mensajeclaro = mensajeclaro;
    }

    public int getValordesplazamiento() {
        return valordesplazamiento;
    }

    public void setValordesplazamiento(Integer valordesplazamiento) {
        this.valordesplazamiento = valordesplazamiento;
    }

    public String CifrarMensajedesplazamiento(){

        int longitud = getMensajeclaro().length();
        char[] vectorcifrado = new char[longitud];

        char[] mimatriz = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ', ',', ';', ':', '.', '_', '-', '*', '!', '"', '$', '%', '(', ')', '=', '?', '¡', '¿', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','Ú','Ó','Í','É','Á','ú','ó','í','é','á','/', (char)13 };

        int posicion = 0;
        int nuevaposicion = 0;

        char letra_obtenida;
        //char [] letrica = new char[1];

        for (int i = 0; i < longitud; i++)
        {

          char []  letrica = (getMensajeclaro().substring(i,i+1)).toCharArray();

            for (int b = 0; b < 94; b++)
            {
                if (  mimatriz[b]==letrica[0])
                {
                    posicion = b; break;
                }
            }

            nuevaposicion = (posicion + getValordesplazamiento()) % 94;
            letra_obtenida = mimatriz[nuevaposicion];
            vectorcifrado[i] = letra_obtenida;
        }

        String  texto_msj_crifrado = new String(vectorcifrado);
        return texto_msj_crifrado;
    }

    public String DescifrarMensajedesplazamiento(){
        char[] mimatriz = new char[] { (char)13, '/','á','é','í','ó','ú','Á','É','Í','Ó','Ú', '9', '8', '7', '6', '5', '4', '3', '2', '1', '0', '¿', '¡', '?', '=', ')', '(', '%', '$', '"', '!', '*', '-', '_', '.', ':', ';', ',', ' ', 'z', 'y', 'x', 'w', 'v', 'u', 't', 's', 'r', 'q', 'p', 'o', 'ñ', 'n', 'm', 'l', 'k', 'j', 'i', 'h', 'g', 'f', 'e', 'd', 'c', 'b', 'a', 'Z', 'Y', 'X', 'W', 'V', 'U', 'T', 'S', 'R', 'Q', 'P', 'O', 'Ñ', 'N', 'M', 'L', 'K', 'J', 'I', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A' };

        int longitud = getMensajecifrado().length();
        char[] vectordescifrado = new char[longitud];
        int posicion = 0;
        int nuevaposicion = 0;
        char [] letrica_cifrada;
        char letra_obtenida;
        for (int i = 0; i < longitud; i++)
        {
            letrica_cifrada = getMensajecifrado().substring(i, i+1).toCharArray();
            //int asciiCode = (int) letrica_cifrada;

            for (int b = 0; b < 94; b++)
            {
                if (mimatriz[b] == letrica_cifrada[0])
                {
                    posicion = b; break;
                }
            }

            nuevaposicion = (posicion + getValordesplazamiento()) % 94;
            letra_obtenida = mimatriz[nuevaposicion];
            vectordescifrado[i] = letra_obtenida;
        }


        String texto_msj_descifrado = new String(vectordescifrado);

        return texto_msj_descifrado;
    }

    public String CifrarLlaveporSustitucion(){

        //  mensaje = Mensaje;

        int longitud = String.valueOf(getValordesplazamiento()).length();
        char[] letras = new char[longitud];//vector para guardar el mesaje cifrado

        for (int i = 0; i < longitud; i++)
        {

            char []  letrica = String.valueOf(getValordesplazamiento()).substring(i, i+1).toCharArray();

            switch (letrica [0])
            {
                case 'A': letras[i] = 'z'; break;
                case 'B': letras[i] = 'y'; break;
                case 'C': letras[i] = 'x'; break;
                case 'D': letras[i] = 'w'; break;
                case 'E': letras[i] = 'v'; break;
                case 'F': letras[i] = 'u'; break;
                case 'G': letras[i] = 't'; break;
                case 'H': letras[i] = 's'; break;
                case 'I': letras[i] = 'r'; break;
                case 'J': letras[i] = 'q'; break;
                case 'K': letras[i] = 'p'; break;
                case 'L': letras[i] = 'o'; break;
                case 'M': letras[i] = 'ñ'; break;
                case 'N': letras[i] = 'n'; break;
                case 'Ñ': letras[i] = 'm'; break;
                case 'O': letras[i] = 'l'; break;
                case 'P': letras[i] = 'k'; break;
                case 'Q': letras[i] = 'j'; break;
                case 'R': letras[i] = 'i'; break;
                case 'S': letras[i] = 'h'; break;
                case 'T': letras[i] = 'g'; break;
                case 'U': letras[i] = 'f'; break;
                case 'V': letras[i] = 'e'; break;
                case 'W': letras[i] = 'd'; break;
                case 'X': letras[i] = 'c'; break;
                case 'Y': letras[i] = 'b'; break;
                case 'Z': letras[i] = 'a'; break;
                case 'a': letras[i] = 'Z'; break;
                case 'b': letras[i] = 'Y'; break;
                case 'c': letras[i] = 'X'; break;
                case 'd': letras[i] = 'W'; break;
                case 'e': letras[i] = 'V'; break;
                case 'f': letras[i] = 'U'; break;
                case 'g': letras[i] = 'T'; break;
                case 'h': letras[i] = 'S'; break;
                case 'i': letras[i] = 'R'; break;
                case 'j': letras[i] = 'Q'; break;
                case 'k': letras[i] = 'P'; break;
                case 'l': letras[i] = 'O'; break;
                case 'm': letras[i] = 'Ñ'; break;
                case 'n': letras[i] = 'N'; break;
                case 'ñ': letras[i] = 'M'; break;
                case 'o': letras[i] = 'L'; break;
                case 'p': letras[i] = 'K'; break;
                case 'q': letras[i] = 'J'; break;
                case 'r': letras[i] = 'I'; break;
                case 's': letras[i] = 'H'; break;
                case 't': letras[i] = 'G'; break;
                case 'u': letras[i] = 'F'; break;
                case 'v': letras[i] = 'E'; break;
                case 'w': letras[i] = 'D'; break;
                case 'x': letras[i] = 'C'; break;
                case 'y': letras[i] = 'B'; break;
                case 'z': letras[i] = 'A'; break;
                case ' ': letras[i] = '/'; break;
                case '/': letras[i] = ' '; break;
                case ',': letras[i] = '¿'; break;
                case ';': letras[i] = '¡'; break;
                case ':': letras[i] = '?'; break;
                case '.': letras[i] = '='; break;
                case '_': letras[i] = ')'; break;
                case '-': letras[i] = '('; break;
                case '*': letras[i] = 'á'; break;
                case 'ó': letras[i] = '%'; break;
                case '!': letras[i] = '$'; break;
                case '"': letras[i] = 'í'; break;
                case 'í': letras[i] = '"'; break;
                case '$': letras[i] = '!'; break;
                case '%': letras[i] = 'ó'; break;
                case 'á': letras[i] = '*'; break;
                case '(': letras[i] = '-'; break;
                case ')': letras[i] = '_'; break;
                case '=': letras[i] = '.'; break;
                case '?': letras[i] = ':'; break;
                case '¡': letras[i] = ';'; break;
                case '¿': letras[i] = ','; break;
                case '0': letras[i] = '9'; break;
                case '1': letras[i] = '8'; break;
                case '2': letras[i] = '7'; break;
                case '3': letras[i] = '6'; break;
                case '4': letras[i] = '5'; break;
                case '5': letras[i] = '4'; break;
                case '6': letras[i] = '3'; break;
                case '7': letras[i] = '2'; break;
                case '8': letras[i] = '1'; break;
                case '9': letras[i] = '0'; break;
                default: letras[i] = ' '; break;
            }

        }
        String texto_msj_crifrado = new String(letras);
        return texto_msj_crifrado;
    }


   /* public String DescifrarMensajeSustitucion(String Mensaje){

    } // El mismo metodo que cifra sirve para descifrar*/
}
