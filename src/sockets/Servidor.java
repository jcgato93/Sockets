
package sockets;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.io.*;


public class Servidor extends javax.swing.JFrame implements Runnable{

    
    public Servidor() {
        initComponents();
        Thread mihilo=new Thread(this);//Se crea un nuevo hilo para poner a la escucha el SocketServer
        mihilo.start();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Areatxt_areatexto = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Areatxt_areatexto.setColumns(20);
        Areatxt_areatexto.setRows(5);
        jScrollPane2.setViewportView(Areatxt_areatexto);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Servidor().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Areatxt_areatexto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        
        try {
            //------------------------------------------------------
            
            //----1)
            ServerSocket servidor=new ServerSocket(9999);//se instancia un obj de ServerSocket especificando el puerto que estara a la escucha
            
            String nick,ip,mensaje;
            
            PaqueteEnvio paquete_recibido;
            
            while(true){//se crea un bucle infinito para cada vez que se cierre la conexion , se ejecuete nuevamente
                

            //----2)
            Socket misocket=servidor.accept();//se crea un obj Socket aceptando cualquier conexion del exterior
            
            //----3) rescata los datos enviados desde la aplicacion cliente
            ObjectInputStream paquete_datos=new ObjectInputStream(misocket.getInputStream());
            
            paquete_recibido=(PaqueteEnvio) paquete_datos.readObject();
         
            nick=paquete_recibido.getNick();
            ip=paquete_recibido.getIp();
            mensaje=paquete_recibido.getMensaje();
            
            this.Areatxt_areatexto.setText(this.Areatxt_areatexto.getText()+"\n"+nick+" : "+mensaje+" para "+ip);
            
            
            Socket envia_destinatario=new Socket(ip,9090);//Socket por el cual se reenviara el mensaje al destinatario
            
            ObjectOutputStream paqueteReenvio=new ObjectOutputStream(envia_destinatario.getOutputStream());
            
            paqueteReenvio.writeObject(paquete_recibido);//se sobre escribe el flujo de salida con la misma informacion del paquete_recibido
            
            paqueteReenvio.close();//se cierra el flojo de datos
            
            envia_destinatario.close();//se cierra la conexion o el puente de datos
            
            //----5)
            misocket.close();//se cierra la conexion
            }
            //--------------------------------------------------------------------------------
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
