
package sockets;

import java.io.*;//para implementar Serializable
import java.util.*;


//de debe serializar la clase para que pueda convertirse en una sucecion de binarios para poderse enviar como un objeto
public class PaqueteEnvio implements Serializable{

    //------------Variables
    private String nick,ip,mensaje;
    
    private ArrayList <String> Ips;//para capturar las ip's de los usuarios conectados

  



    
    //-----------Metodos Setters and Getters
      public ArrayList<String> getIps() {
        return Ips;
    }

    public void setIps(ArrayList<String> Ips) {
        this.Ips = Ips;
    }

    
    
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    //-------------Fina de los Setters and Getters
    
  
    
    
}
