
package sockets;

import java.io.*;//para implementar Serializable



//de debe serializar la clase para que pueda convertirse en una sucecion de binarios para poderse enviar como un objeto
public class PaqueteEnvio implements Serializable{

    //------------Variables
    private String nick,ip,mensaje;
    
    //-----------Metodos Setters and Getters
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
